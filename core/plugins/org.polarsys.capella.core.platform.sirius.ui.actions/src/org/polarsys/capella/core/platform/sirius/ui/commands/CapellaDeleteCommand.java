/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.core.model.handler.command.BasicCapellaDeleteCommand;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.model.preferences.IDeletePreferences;
import org.polarsys.capella.core.platform.sirius.ui.actions.CapellaActionsActivator;
import org.polarsys.capella.core.platform.sirius.ui.actions.CapellaDeleteAction;
import org.polarsys.capella.core.ui.toolkit.dialogs.ConfirmDeleteCapellaElementDialog;
import org.polarsys.capella.core.ui.toolkit.dialogs.ImpactAnalysisDialog;
import org.polarsys.kitalpha.emde.model.Element;

/**
 * Capella delete command facade.
 */
public class CapellaDeleteCommand extends BasicCapellaDeleteCommand {

  private boolean preventProtectedElementsDeletion;
  
  private IStatus protectedElements = Status.OK_STATUS;
  
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
   * @param confirmDelete Should the user be asked for confirmation (<code>true</code>) or not (<code>false</code>) ?
   * @param longOperationEvents Should events about this long running operation flow be sent ? <code>true</code> if so, <code>false</code> otherwise.
   */
  public CapellaDeleteCommand(ExecutionManager executionManager, Collection<?> selection, boolean ensureTransaction, boolean confirmDelete, boolean longOperationEvents) {
    super(executionManager, selection, ensureTransaction, confirmDelete, longOperationEvents);
    this.preventProtectedElementsDeletion = false;
  }

  public void setPreventProtectedElementsDeletion(boolean value) {
    this.preventProtectedElementsDeletion = value;
  }
  
  /**
   * 
   */
  protected Command getDeleteRepresentationCommand(TransactionalEditingDomain editingDomain) {
    return new DeleteRepresentationCommand(editingDomain, RepresentationHelper.getAllRepresentationDescriptorsTargetedBy(getExpandedSelection()));
  }

  /**
   * 
   */
  protected boolean isConfirmationRequired() {
    return IDeletePreferences.INSTANCE.isConfirmationRequired();
  }

  /**
   * In case we would delete controlled elements, show a dialog to warn the user.
   * @param controlledElementsToDelete
   */
  protected void showAbortDialogForControlledElementsToDelete(final Set<? extends EObject> controlledElementsToDelete) {
    PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
      public void run() {
        ImpactAnalysisDialog dialog =
            new ImpactAnalysisDialog(new ArrayList<EObject>(controlledElementsToDelete), Messages.CapellaDeleteCommand_ConfirmLabel,
                Messages.CapellaDeleteCommand_ControlledElementsError_Message, MessageDialog.ERROR,
                new String[] { org.polarsys.capella.common.ui.toolkit.dialogs.Messages.AbstractViewerDialog_OK_Title }, SWT.COLOR_RED, false);
        dialog.open();
      }
    });
  }
  
  @Override
  protected IStatus preDeleteChecks() {
    if (preventProtectedElementsDeletion) {
      Set<?> elementsToDelete = getAllElementsToDelete();
      if (!CapellaDeleteAction.canDelete(elementsToDelete)) {
        protectedElements = new Status(Status.ERROR, CapellaActionsActivator.PLUGIN_ID,
          Messages.CapellaDeleteCommand_ProtectedElementsError);
      }
    }
    return protectedElements;
  }
  
  protected boolean confirmDeletion() {
    Set<?> elementsToDelete = getAllElementsToDelete();
    ConfirmDeleteCapellaElementDialog confirmDeletionDialog = new ConfirmDeleteCapellaElementDialog(
        new ArrayList<Object>(elementsToDelete), true, getExpandedSelection().toArray()) {
          @Override
          protected IStatus getStatus() {
            return protectedElements;
          }

          @Override
          public Set<Object> getProtectedElements() {
            return elementsToDelete.stream().filter(Element.class::isInstance).map(Element.class::cast)
            .filter(CapellaDeleteAction::isElementProtected).collect(Collectors.toSet());
          }
    };
    PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
      public void run() {
        confirmDeletionDialog.open();
      }
    });
    return confirmDeletionDialog.getReturnCode() == IDialogConstants.OK_ID;
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
