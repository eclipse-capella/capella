/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
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
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.operations.LongRunningListenersRegistry;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.command.DeleteStructureCommand;
import org.polarsys.capella.core.model.handler.command.IDeleteHelper;
import org.polarsys.capella.core.model.handler.command.PreDeleteHandler;
import org.polarsys.capella.core.model.handler.command.PreDeleteStructureCommand;
import org.polarsys.capella.core.model.handler.helpers.CrossReferencerHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.platform.sirius.ui.actions.CapellaActionsActivator;
import org.polarsys.capella.core.platform.sirius.ui.preferences.IDeletePreferences;
import org.polarsys.capella.core.ui.toolkit.dialogs.ConfirmDeleteCapellaElementDialog;
import org.polarsys.capella.core.ui.toolkit.dialogs.ImpactAnalysisDialog;

/**
 * Capella delete command facade.
 */
public class CapellaDeleteCommand extends AbstractCommand {

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
   * The execution manager.
   */
  private ExecutionManager executionManager;
  
  /**
   * The editing domain (used when no execution manager is available).
   */
  private EditingDomain editingDomain;
  
  /**
   * Are we notifying the long running event registry?
   */
  private boolean sendLongRunningEvents;

  /**
   * Show a confirmation dialog? Note: This value only controls whether we show the dialog to confirm the delete. Another dialog may be shown in case the
   * selection contains fragment roots.
   */
  private boolean confirmDelete;

  /**
   * Equivalent to <code>CapellaDeleteCommand(executionManager, selection, true)</code>.
   * @param executionManager
   * @param collection
   */
  public CapellaDeleteCommand(ExecutionManager executionManager, Collection<?> selection) {
    this(executionManager, selection, true);
  }
  
  /**
   * 
   * <h2>Warning: This constructor doesn't support execution manager</i></h2><br>
   * @param editingDomain
   * @param selection
   * @param confirmDelete
   */
  public CapellaDeleteCommand(EditingDomain editingDomain, Collection<?> selection, boolean confirmDelete) {
	  this(null, selection, false, confirmDelete, true);
    this.editingDomain = editingDomain;
  }

  /**
   * Equivalent to
   * <code>CapellaDeleteCommand(executionManager, collection, ensureTransaction, IDeletePreferences.INSTANCE.isConfirmationRequired(), true)</code>.
   * @param executionManager
   * @param collection
   */
  public CapellaDeleteCommand(ExecutionManager executionManager, Collection<?> selection, boolean ensureTransaction) {
    this(executionManager, selection, ensureTransaction, IDeletePreferences.INSTANCE.isConfirmationRequired(), true);
  }

  /**
   * Constructor.
   * @param executionManager
   * @param selection
   * @param ensureTransaction Should it be executed against the specified execution manager directly (<code>true</code>) or not (<code>false</code>) ?
   * @param monitorDelete Should the user be asked for confirmation (<code>true</code>) or not (<code>false</code>) ?
   * @param longOperationEvents Should events about this long running operation flow be sent ? <code>true</code> if so, <code>false</code> otherwise.
   */
  public CapellaDeleteCommand(ExecutionManager executionManager, Collection<?> selection, boolean ensureTransaction, boolean confirmDelete, boolean longOperationEvents) {
    this.executionManager = executionManager;
    this.editingDomain = (executionManager != null) ? executionManager.getEditingDomain() : null;
    this.ensureTransaction = ensureTransaction;
    this.confirmDelete = confirmDelete;
    this.sendLongRunningEvents = longOperationEvents;
    this.selection = new ArrayList<Object>(selection);
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

  /**
   * @see org.eclipse.emf.edit.command.DeleteCommand#execute()
   */
  public void execute() {

    /**
     * Hard constraint on executability: If one of the elements to be deleted is a fragment root, we show an abort dialog and do nothing. FIXME This is
     * expensive. FIXME transactional context?
     */
    Set<? extends EObject> allControlledElementsToDelete = getAllControlledElementsToDelete();
    if (allControlledElementsToDelete.size() > 0) {
      showAbortDialogForControlledElementsToDelete(allControlledElementsToDelete);
      return;
    }

    /**
     * Show confirmation/impact analysis if desired.
     */
    if (confirmDelete) {
      final int dialogResult[] = new int[] { 0 };
      PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
        public void run() {
          ConfirmDeleteCapellaElementDialog confirmDeletionDialog =
              new ConfirmDeleteCapellaElementDialog(new ArrayList<Object>(getAllElementsToDelete()), true, getExpandedSelection().toArray());
          dialogResult[0] = confirmDeletionDialog.open();
        }
      });
      if ((dialogResult[0] != IDialogConstants.OK_ID)) {
        return;
      }
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
            LongRunningListenersRegistry.getInstance().operationAborted(CapellaDeleteCommand.class);
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
        public void run() {
          doExecute();
        }
      });
    } else {
      // Execute deletion within caller transaction.
      try {
        doExecute();
      } catch (Exception re) {
        CapellaActionsActivator.getDefault().getLog()
            .log(new Status(IStatus.WARNING, CapellaActionsActivator.getDefault().getPluginId(), re.getMessage(), re));
      }
    }
  }

  /**
   * Do execute real command. Point of no return.
   */
  protected void doExecute() {
    if (sendLongRunningEvents) {
      LongRunningListenersRegistry.getInstance().operationStarting(CapellaDeleteCommand.class);
    }
    for (AbstractCapellaDeleteHook hook : getDeleteCommandHooks()) {
      if (!hook.preDelete(getAllElementsToDelete()).isOK()) {
        if (sendLongRunningEvents) {
          LongRunningListenersRegistry.getInstance().operationAborted(CapellaDeleteCommand.class);
        }
        return;
      }
    }
    try {
    	realCommand = new DeleteStructureCommand(editingDomain, getExpandedSelection(), isDeletingPartTypesForMultiPartProjects()) {
    		@Override
    		protected void doPrepare() {
    			// Use DeleteRepresentation here since this command handles open representation editors.
    			append(new DeleteRepresentationCommand((TransactionalEditingDomain) getEditingDomain(),
    					RepresentationHelper.getAllRepresentationsTargetedBy(getExpandedSelection())));
    			super.doPrepare();
    		}
    	};
      if (realCommand.canExecute()) {
        realCommand.execute();
      }
    } finally {
      if (sendLongRunningEvents) {
        LongRunningListenersRegistry.getInstance().operationEnded(CapellaDeleteCommand.class);
      }
    }
  }

  protected boolean isDeletingPartTypesForMultiPartProjects() {
    return IDeletePreferences.INSTANCE.isDeletingPartType();
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
      IConfigurationElement[] configurationElements =
          ExtensionPointHelper.getConfigurationElements(CapellaActionsActivator.getDefault().getPluginId(), "deleteCommandDelegation"); //$NON-NLS-1$
      // Loop over contributed IDeleteCommandDelegation contributor, must be only one.
      for (IConfigurationElement elem : configurationElements) {
        deleteCommandHooks.add((AbstractCapellaDeleteHook) ExtensionPointHelper.createInstance(elem, ExtensionPointHelper.ATT_CLASS));
      }
    }
    return deleteCommandHooks;
  }

  /**
   * In case we would delete controlled elements, show a dialog to warn the user.
   * @param controlledElementsToDelete
   */
  protected void showAbortDialogForControlledElementsToDelete(final Set<? extends EObject> controlledElementsToDelete) {
    PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
      public void run() {
        ImpactAnalysisDialog dialog =
            new ImpactAnalysisDialog(new ArrayList<EObject>(controlledElementsToDelete), Messages.CapellaDeleteCommand_Label,
                Messages.CapellaDeleteCommand_ControlledElementsError_Message, MessageDialog.ERROR,
                new String[] { org.polarsys.capella.common.ui.toolkit.dialogs.Messages.AbstractViewerDialog_OK_Title }, SWT.COLOR_RED, false);
        dialog.open();
      }
    });
  }

  protected boolean confirmDeletion() {

    final int dialogResult[] = new int[] { 0 };
    PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
      public void run() {
        ImpactAnalysisDialog dialog =
            new ImpactAnalysisDialog(new ArrayList<Object>(getAllElementsToDelete()), Messages.CapellaDeleteCommand_Label,
                Messages.CapellaDeleteCommand_ConfirmDeletionQuestion, MessageDialog.QUESTION, new String[] { IDialogConstants.YES_LABEL,
                                                                                                            IDialogConstants.NO_LABEL }, SWT.COLOR_RED, true) {
              /**
               * Referencing elements viewer to deleted elements.
               */
              private TreeViewer _referencingViewer;

              /**
               * Create a second viewer to display referencing elements related to a deleted element.
               * @param parent
               */
              protected void createReferencingElementViewer(Composite parent) {
                // Create a group to host the referencing elements.
                Group referencingElementsGroup = new Group(parent, SWT.NONE);
                referencingElementsGroup.setText(Messages.CapellaDeleteCommand_ImpactAnalysis_ReferencingElements_Group_Title);
                referencingElementsGroup.setToolTipText(Messages.CapellaDeleteCommand_ImpactAnalysis_ReferencingElements_Group_Tooltip);
                referencingElementsGroup.setLayout(new GridLayout());
                referencingElementsGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
                // Create the referencing element with this group as parent.
                _referencingViewer = super.createViewer(referencingElementsGroup);
                // Set a label provider that allow decorator mechanism.
                _referencingViewer.setLabelProvider(new DecoratingLabelProvider(new ImpactAnalysisLabelProvider(_referencingViewer,
                    DEFAULT_COLOR_FOR_RELEVANT_ELEMENTS), PlatformUI.getWorkbench().getDecoratorManager()));
                // Add a listener to forward the selection from the deleted elements viewer to the referencing one.
                getViewer().addSelectionChangedListener(new ISelectionChangedListener() {
                  /**
                   * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
                   */
                  @SuppressWarnings("synthetic-access")
                  public void selectionChanged(SelectionChangedEvent event) {
                    IStructuredSelection ssel = (IStructuredSelection) event.getSelection();
                    List<?> selectedElements = ssel.toList();
                    Set<EObject> referencingElements = new HashSet<EObject>(0);
                    for (Object currentSelectedElement : selectedElements) {
                      if (((TreeData) getViewer().getInput()).isValid(currentSelectedElement)) {
                        // Be careful, selected element could be an EMF Resource (if displayed).
                        if (currentSelectedElement instanceof EObject) {
                          referencingElements.addAll(CrossReferencerHelper.getReferencingElements((EObject) currentSelectedElement));
                        }
                      }
                    }
                    // Compute the referencing elements.
                    _referencingViewer.setInput(new TreeData(new ArrayList<Object>(referencingElements), null));
                  }
                });
              }

              /**
               * @see org.polarsys.capella.common.ui.toolkit.dialogs.AbstractMessageDialogWithViewer#createViewerArea(org.eclipse.swt.widgets.Composite)
               */
              @Override
              protected void createViewerArea(Composite parent) {
                // Since we add a second viewer to display referencing elements for deleted ones.
                // Let's tweak the UI by the changing the layout data.
                GridLayout layout = (GridLayout) parent.getLayout();
                layout.numColumns = 2;
                layout.makeColumnsEqualWidth = true;
                layout.marginWidth = 0; // To have the status bar nicely displayed.
                // Create a group to host the deleted element viewer.
                Group deletedElementsGroup = new Group(parent, SWT.NONE);
                deletedElementsGroup.setText(Messages.CapellaDeleteCommand_ImpactAnalysis_DeletedElements_Group_Title);
                deletedElementsGroup.setToolTipText(Messages.CapellaDeleteCommand_ImpactAnalysis_DeletedElements_Group_Tooltip);
                deletedElementsGroup.setLayout(new GridLayout());
                deletedElementsGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
                // Create the viewer area with this group as parent.
                super.createViewerArea(deletedElementsGroup);
                // Create a second viewer to display referencing elements related to a deleted element.
                createReferencingElementViewer(parent);
                // Select end-user initial elements to delete and set the focus on this viewer.
                TreeViewer treeViewer = getViewer();
                // Set a label provider that allow decorator mechanism.
                treeViewer.setLabelProvider(new DecoratingLabelProvider(new ImpactAnalysisLabelProvider(treeViewer, SWT.COLOR_RED), PlatformUI.getWorkbench()
                    .getDecoratorManager()));
                treeViewer.setSelection(new StructuredSelection(getExpandedSelection().toArray()), true);
                treeViewer.getControl().setFocus();
              }

              /**
               * @see org.polarsys.capella.common.ui.toolkit.dialogs.AbstractMessageDialogWithViewer#getDialogWidth()
               */
              @Override
              protected int getDialogWidth() {
                return 800; // With 2 viewers, we need more space.
              }

            };
        dialogResult[0] = dialog.open();
      }
    });
    return (IDialogConstants.OK_ID == dialogResult[0]);
  }

  /**
   * Expand the original selection with business rules defined in our delete helper.
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
   * @see getExpandedSelection()
   */
  public void setDeleteHelper(IDeleteHelper helper) {
    deleteHelper = helper;
  }

  /**
   * Find controlled elements that are to be deleted.
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
   * Finds _all_ elements that will be deleted when the command is executed. NOTES: a. Two layers of business logic are controlling the result of this
   * operation: 1. The delete helper is used to expand the initial selection 2. Business rules defined in PreDeleteStructureCommand b. All containment children
   * of deleted elements are explicitly contained in the result set.
   */
  public Set<?> getAllElementsToDelete() {
    if (allElementsToDelete == null) {
      // Get a new handler.
      HashSet<Object> result = new HashSet<Object>();
      PreDeleteHandler handler = new PreDeleteHandler();

      // Call predeletion command.
      Command preDeletion =
          new PreDeleteStructureCommand(editingDomain, getExpandedSelection(), isDeletingPartTypesForMultiPartProjects(), handler);
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
          // Handle it as a remove, as long as there is a null new value (and a not null old one, but that part is tested within the remove case
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
   * @param parent The parent object.
   * @param removedElement The removed element, child of specified parent one.
   * @param parentsToChildren The resulting parents to children structure.
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

  static class DryRun extends CapellaDeleteCommand {
    public DryRun(ExecutionManager executionManager, Collection<?> selection) {
      super(executionManager, selection);
    }

    boolean proceed = false;

    public boolean proceed() {
      return proceed;
    }

    @Override
    public void doExecute() {
      // if we reached here, all obstacles are passed
      // a real capella delete command would now proceed
      proceed = true;
    }
  }

  /**
   * Simulate deletion of the given selection up to the point where we show a confirmation dialog to the user. Also performs the check on fragment roots. Needed
   * when deleting elements from a diagram editor for some obscure reason.
   * @see CapellaDeleteActionHook
   * @param manager
   * @param selection
   * @return true if the deletion would proceed. false otherwise.
   */
  public static boolean confirmDeletion(ExecutionManager manager, Collection<?> selection) {
    DryRun dr = new DryRun(manager, selection);
    dr.execute();
    return dr.proceed();
  }

}
