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
import java.util.Set;

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
import org.polarsys.capella.core.ui.toolkit.dialogs.ConfirmDeleteCapellaElementDialog;
import org.polarsys.capella.core.ui.toolkit.dialogs.ImpactAnalysisDialog;

/**
 * Capella delete command facade.
 */
public class CapellaDeleteCommand extends BasicCapellaDeleteCommand {

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
  }

  /**
   * 
   */
  protected Command getDeleteRepresentationCommand(TransactionalEditingDomain editingDomain) {
    return new DeleteRepresentationCommand(editingDomain, RepresentationHelper.getAllRepresentationsTargetedBy(getExpandedSelection()));
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
        ConfirmDeleteCapellaElementDialog confirmDeletionDialog =
            new ConfirmDeleteCapellaElementDialog(new ArrayList<Object>(getAllElementsToDelete()), true, getExpandedSelection().toArray());
        dialogResult[0] = confirmDeletionDialog.open();
      }
    });
    return (dialogResult[0] == IDialogConstants.OK_ID);
  }

}
