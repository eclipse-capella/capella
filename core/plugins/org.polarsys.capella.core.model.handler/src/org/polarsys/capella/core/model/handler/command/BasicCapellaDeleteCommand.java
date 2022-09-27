/*******************************************************************************
 * Copyright (c) 2017, 2022 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.model.handler.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.operations.LongRunningListenersRegistry;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.tools.report.util.LogExt;
import org.polarsys.capella.core.model.handler.ModelHandlerPlugin;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;

/**
 * @author Joao Barata
 */
public class BasicCapellaDeleteCommand extends AbstractCommand {

  /**
   * The delete helper which performs business rule expansion of the selection.
   */
  private IDeleteHelper deleteHelper = IDeleteHelper.DEFAULT;

  /**
   * Id based-on label to handle this command among recording ones.
   */
  public static final String ID = "Delete"; //$NON-NLS-1$

  /**
   * Hooks to notify prior deletion
   */
  private static ArrayList<AbstractCapellaDeleteHook> deleteCommandHooks;

  /**
   * Real command that modifies the model.
   */
  private Command realCommand;

  /**
   * The original selection of elements to delete.
   */
  private Collection<?> selection;

  /**
   * The cached result of helper.getExpandedSelection(selection)
   */
  private Collection<?> expandedSelection;

  /**
   * The cached result of getAllElementsToDelete(...)
   */
  private Set<Object> allElementsToDelete;

  /**
   * The cached result of getControlledExpandedSelectionContents(...)
   */
  private Set<EObject> _controlledExpandedSelectionContents;

  /**
   * Execute against the transaction manager or not.
   */
  private boolean ensureTransaction;

  /**
   * Show a confirmation dialog? Note: This value only controls whether we show the dialog to confirm the delete.
   * Another dialog may be shown in case the selection contains fragment roots.
   */
  private boolean confirmDelete;

  /**
   * The execution manager.
   */
  private ExecutionManager executionManager;

  /**
   * The editing domain (used when no execution manager is available).
   */
  protected EditingDomain editingDomain;

  /**
   * Are we notifying the long running event registry?
   */
  private boolean sendLongRunningEvents;

  /**
   * Equivalent to <code>BasicCapellaDeleteCommand(executionManager, selection, true)</code>.
   * 
   * @param executionManager
   * @param collection
   */
  public BasicCapellaDeleteCommand(ExecutionManager executionManager, Collection<?> selection) {
    this(executionManager, selection, true);
  }

  /**
   * Equivalent to
   * <code>BasicCapellaDeleteCommand(executionManager, collection, ensureTransaction, IDeletePreferences.INSTANCE.isConfirmationRequired(), true)</code>.
   * 
   * @param executionManager
   * @param collection
   */
  public BasicCapellaDeleteCommand(ExecutionManager executionManager, Collection<?> selection,
      boolean ensureTransaction) {
    this(executionManager, selection, ensureTransaction, false, true);
    this.confirmDelete = isConfirmationRequired();
  }

  /**
   * Constructor.
   * 
   * @param executionManager
   * @param selection
   * @param ensureTransaction
   *          Should it be executed against the specified execution manager directly (<code>true</code>) or not
   *          (<code>false</code>) ?
   * @param monitorDelete
   *          Should the user be asked for confirmation (<code>true</code>) or not (<code>false</code>) ?
   * @param longOperationEvents
   *          Should events about this long running operation flow be sent ? <code>true</code> if so, <code>false</code>
   *          otherwise.
   */
  public BasicCapellaDeleteCommand(ExecutionManager executionManager, Collection<?> selection,
      boolean ensureTransaction, boolean confirmDelete, boolean longOperationEvents) {
    this.executionManager = executionManager;
    this.editingDomain = (executionManager != null) ? executionManager.getEditingDomain() : null;
    this.ensureTransaction = ensureTransaction;
    this.confirmDelete = confirmDelete;
    this.sendLongRunningEvents = longOperationEvents;
    this.selection = new ArrayList<Object>(selection);
  }

  /**
   * @see org.eclipse.emf.edit.command.DeleteCommand#execute()
   */
  @Override
  public void execute() {

    /**
     * Hard constraint on executability: If one of the elements to be deleted is a fragment root, we show an abort
     * dialog and do nothing. FIXME This is expensive. FIXME transactional context?
     */
    Set<? extends EObject> allControlledElementsToDelete = getAllControlledElementsToDelete();
    if (!allControlledElementsToDelete.isEmpty()) {
      showAbortDialogForControlledElementsToDelete(allControlledElementsToDelete);
      return;
    }

    // show message in information window if deletion dialog is now shown
    if (!isConfirmationRequired()) {
      IStatus informationStatus = getInformationMessage();
      if (informationStatus != null)
        LogExt.log(IReportManagerDefaultComponents.MODEL, informationStatus);
    }
    
    /**
     * Preventive checks
     */
    IStatus status = preDeleteChecks();
    
    if (confirmDelete && !confirmDeletion()) {
      return;
    }

    if (status != null && (status.getSeverity() == IStatus.ERROR || status.getSeverity() == IStatus.CANCEL)) {
      return;
    }

    // Should execution take place against the execution manager ?
    if (ensureTransaction) {
      // Execute deletion against the execution manager.
      executionManager.execute(new AbstractReadWriteCommand() {
        /**
         * @see org.polarsys.capella.common.ef.command.AbstractCommand#commandRolledBack()
         */
        @SuppressWarnings("synthetic-access")
        @Override
        public void commandRolledBack() {
          // Send aborted event.
          if (sendLongRunningEvents) {
            LongRunningListenersRegistry.getInstance().operationAborted(BasicCapellaDeleteCommand.class);
          }
        }

        /**
         * @see org.polarsys.capella.common.ef.command.AbstractCommand#getAffectedObjects()
         */
        @SuppressWarnings("synthetic-access")
        @Override
        public Collection<?> getAffectedObjects() {
          if (null != realCommand) {
            return realCommand.getAffectedObjects();
          }
          return super.getAffectedObjects();
        }

        /**
         * @see org.polarsys.capella.common.ef.command.AbstractCommand#getName()
         */
        @Override
        public String getName() {
          return Messages.CapellaDeleteCommand_Label;
        }

        /**
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {
          doExecute();
        }
      });
    } else {
      // Execute deletion within caller transaction.
      try {
        doExecute();
      } catch (OperationCanceledException oce) {
        // If an OperationCanceledException is thrown by the doExecute, we propagate it, to rollback the current
        // command.
        throw oce;
      } catch (Exception re) {
        // For other kind of Exception, we only log them.
        LogExt.log(IReportManagerDefaultComponents.MODEL,
            new Status(IStatus.WARNING, ModelHandlerPlugin.PLUGIN_ID, re.getMessage(), re));
      }
    }
  }

  protected IStatus preDeleteChecks() {
    return Status.OK_STATUS;
  }

  // to be displayed in the information log
  protected IStatus getInformationMessage() {
    return Status.OK_STATUS;
  }

  protected void showAbortDialogForControlledElementsToDelete(final Set<? extends EObject> controlledElementsToDelete) {
    // by default, no dialog is shown
  }

  protected boolean confirmDeletion() {
    return true;
  }

  protected Command getDeleteRepresentationCommand(TransactionalEditingDomain editingDomain) {
    return new BasicRepresentationDeleteCommand(editingDomain,
        RepresentationHelper.getAllRepresentationDescriptorsTargetedBy(getExpandedSelection()),
        new NullProgressMonitor());
  }

  /**
   * Do execute real command. Point of no return.
   */
  protected void doExecute() {
    if (sendLongRunningEvents) {
      LongRunningListenersRegistry.getInstance().operationStarting(BasicCapellaDeleteCommand.class);
    }
    for (AbstractCapellaDeleteHook hook : getDeleteCommandHooks()) {
      if (!hook.preDelete(getAllElementsToDelete()).isOK()) {
        if (sendLongRunningEvents) {
          LongRunningListenersRegistry.getInstance().operationAborted(BasicCapellaDeleteCommand.class);
        }
        // If the preDelete detects something wrong, we abort the current command with an OperationCanceledException.
        throw new OperationCanceledException();
      }
    }
    try {
      realCommand = new DeleteStructureCommand(editingDomain, getExpandedSelection()) {
        @Override
        protected void doPrepare() {
          // Use DeleteRepresentation here since this command handles open representation editors.
          append(getDeleteRepresentationCommand((TransactionalEditingDomain) getEditingDomain()));
          super.doPrepare();
        }
      };
      if (realCommand.canExecute()) {
        realCommand.execute();
      }
    } finally {
      if (sendLongRunningEvents) {
        LongRunningListenersRegistry.getInstance().operationEnded(BasicCapellaDeleteCommand.class);
      }
    }
  }

  /**
   * @see org.eclipse.emf.common.command.AbstractCommand#canUndo()
   */
  @Override
  public boolean canUndo() {
    if (null != realCommand) {
      return realCommand.canUndo();
    }
    // If no real command is available, that was just about predeletion, and it is not undoable.
    return false;
  }

  protected boolean isConfirmationRequired() {
    return false;
  }

  /**
   * @see org.eclipse.emf.common.command.AbstractCommand#getAffectedObjects()
   */
  @Override
  public Collection<?> getAffectedObjects() {
    if (null != realCommand) {
      return realCommand.getAffectedObjects();
    }
    return super.getAffectedObjects();
  }

  /**
   * @see org.eclipse.emf.common.command.AbstractCommand#prepare()
   */
  @Override
  protected boolean prepare() {
    return true;
  }

  /**
   * @see org.eclipse.emf.common.command.Command#redo()
   */
  @Override
  public void redo() {
    if (null != realCommand) {
      realCommand.redo();
    }
  }

  /**
   * @see org.eclipse.emf.common.command.AbstractCommand#undo()
   */
  @Override
  public void undo() {
    if (null != realCommand) {
      realCommand.undo();
    }
  }

  /**
   * The list of delete command hooks we should notify before and after delete. Never null.
   */
  private List<AbstractCapellaDeleteHook> getDeleteCommandHooks() {
    if (deleteCommandHooks == null) {
      deleteCommandHooks = new ArrayList<AbstractCapellaDeleteHook>();
      // Load IDeleteCommandDelegation contributor if any.
      IConfigurationElement[] configurationElements = ExtensionPointHelper
          .getConfigurationElements("org.polarsys.capella.core.platform.sirius.ui.actions", "deleteCommandDelegation"); //$NON-NLS-1$
      // Loop over contributed IDeleteCommandDelegation contributor, must be only one.
      for (IConfigurationElement elem : configurationElements) {
        deleteCommandHooks
            .add((AbstractCapellaDeleteHook) ExtensionPointHelper.createInstance(elem, ExtensionPointHelper.ATT_CLASS));
      }
    }
    return deleteCommandHooks;
  }

  /**
   * Expand the original selection with business rules defined in our delete helper.
   * 
   * @see getDeleteHelper()
   */
  public Collection<?> getExpandedSelection() {
    if (expandedSelection == null) {
      expandedSelection = Collections.unmodifiableCollection(deleteHelper.getExpandedSelection(selection));
    }
    return expandedSelection;
  }

  /**
   * Returns the current delete helper for this command. The default is IDeleteHelper.DEFAULT.
   */
  public IDeleteHelper getDeleteHelper() {
    return deleteHelper;
  }

  /**
   * Sets the delete helper for this command.
   * 
   * @see getExpandedSelection()
   */
  public void setDeleteHelper(IDeleteHelper helper) {
    deleteHelper = helper;
  }

  /**
   * Find controlled elements that are to be deleted.
   * 
   * @see AdapterFactoryEditingDomain.isControlled(..)
   */
  public Set<? extends EObject> getAllControlledElementsToDelete() {
    if (_controlledExpandedSelectionContents == null) {
      Set<EObject> result = new HashSet<EObject>();
      for (Object deletedElement : getAllElementsToDelete()) {
        if (AdapterFactoryEditingDomain.isControlled(deletedElement)) {
          result.add((EObject) deletedElement);
        }
      }
      _controlledExpandedSelectionContents = Collections.unmodifiableSet(result);
    }
    return _controlledExpandedSelectionContents;
  }

  /**
   * Finds _all_ elements that will be deleted when the command is executed. NOTES: a. Two layers of business logic are
   * controlling the result of this operation: 1. The delete helper is used to expand the initial selection 2. Business
   * rules defined in PreDeleteStructureCommand b. All containment children of deleted elements are explicitly contained
   * in the result set.
   */
  public Set<?> getAllElementsToDelete() {
    if (allElementsToDelete == null) {
      // Get a new handler.
      HashSet<Object> result = new HashSet<Object>();
      PreDeleteHandler handler = new PreDeleteHandler();

      // Call predeletion command.
      Command preDeletion = new PreDeleteStructureCommand(editingDomain, getExpandedSelection(), handler);
      if (preDeletion.canExecute()) {
        preDeletion.execute();
      }

      for (Notification notification : handler.notifications) {
        Object notifier = notification.getNotifier();
        if (notifier instanceof EObject) {
          // Get old value (ie removed one).
          Object oldValue = notification.getOldValue();
          int notificationType = notification.getEventType();
          switch (notificationType) {
          // Set case.
          // Handle it as a remove, as long as there is a null new value (and a not null old one, but that part is
          // tested within the remove case
          // directly).
          case Notification.SET:
            if (null != notification.getNewValue()) {
              break;
            }
            //$FALL-THROUGH$
          case Notification.REMOVE:
            if (oldValue instanceof EObject) {
              boolean handleNotification = false;
              try {
                EReference feature = EReference.class.cast(notification.getFeature());
                handleNotification = feature != null ? feature.isContainment() : false;
              } catch (ClassCastException cce) {
                // Could not tell feature, add notification whatever it might be.
                handleNotification = true;
              }
              if (handleNotification) {
                // Add the deleted element.
                EObject deletedObject = (EObject) oldValue;
                result.add(deletedObject);
                // Filter out children of non Capella model elements as DRepresentation for instance.
                if (CapellaResourceHelper.isSemanticElement(deletedObject)) {
                  // Add the deleted element subtree.
                  TreeIterator<EObject> allChildrenOfDeletedObject = deletedObject.eAllContents();
                  while (allChildrenOfDeletedObject.hasNext()) {
                    EObject child = allChildrenOfDeletedObject.next();
                    result.add(child);
                  }
                }
              }
            }
            break;
          default:
            break;
          }
        }
      }
      handler.dispose();
      allElementsToDelete = Collections.unmodifiableSet(result);
    }
    return allElementsToDelete;
  }

  /**
   * FIXME who's using this? Fill parents to children structure for specified elements.
   * 
   * @param parent
   *          The parent object.
   * @param removedElement
   *          The removed element, child of specified parent one.
   * @param parentsToChildren
   *          The resulting parents to children structure.
   */
  protected void fillStructure(EObject parent, EObject removedElement, Map<EObject, Set<EObject>> parentsToChildren) {
    // Precondition.
    if ((null == parent) || (null == removedElement) || (null == parentsToChildren)) {
      return;
    }
    // Get children for specified parent.
    Set<EObject> children = parentsToChildren.get(parent);
    // If there is no child at the time, create structure.
    if (null == children) {
      children = new HashSet<EObject>(1);
      parentsToChildren.put(parent, children);
    }
    // Add removed element.
    children.add(removedElement);
    // Recursively apply filling to contained children.
    for (EObject removed : removedElement.eContents()) {
      fillStructure(removedElement, removed, parentsToChildren);
    }
  }

}
