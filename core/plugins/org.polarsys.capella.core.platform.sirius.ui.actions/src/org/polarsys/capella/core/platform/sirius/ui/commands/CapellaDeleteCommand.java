/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.commands;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.core.model.handler.command.BasicCapellaDeleteCommand;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.model.preferences.IDeletePreferences;
import org.polarsys.capella.core.platform.sirius.ui.actions.CapellaActionsActivator;
import org.polarsys.capella.core.platform.sirius.ui.actions.CapellaDeleteAction;
import org.polarsys.capella.core.platform.sirius.ui.actions.decorators.ProtectedElementsDecorator;
import org.polarsys.capella.core.ui.toolkit.dialogs.ConfirmDeleteCapellaElementDialog;
import org.polarsys.capella.core.ui.toolkit.dialogs.ImpactAnalysisDialog;

/**
 * Capella delete command facade.
 */
public class CapellaDeleteCommand extends BasicCapellaDeleteCommand {

  private boolean preventProtectedElementsDeletion;

  private IStatus deletionStatus = Status.OK_STATUS;

  /**
   * Equivalent to <code>CapellaDeleteCommand(executionManager, selection, true)</code>.
   * 
   * @param executionManager
   * @param collection
   */
  public CapellaDeleteCommand(ExecutionManager executionManager, Collection<?> selection) {
    this(executionManager, selection, true);
  }

  /**
   * 
   * <h2>Warning: This constructor doesn't support execution manager</i></h2><br>
   * 
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
   * 
   * @param executionManager
   * @param collection
   */
  public CapellaDeleteCommand(ExecutionManager executionManager, Collection<?> selection, boolean ensureTransaction) {
    this(executionManager, selection, ensureTransaction, IDeletePreferences.INSTANCE.isConfirmationRequired(), true);
  }

  /**
   * Constructor.
   * 
   * @param executionManager
   * @param selection
   * @param ensureTransaction
   *          Should it be executed against the specified execution manager directly (<code>true</code>) or not
   *          (<code>false</code>) ?
   * @param confirmDelete
   *          Should the user be asked for confirmation (<code>true</code>) or not (<code>false</code>) ?
   * @param longOperationEvents
   *          Should events about this long running operation flow be sent ? <code>true</code> if so, <code>false</code>
   *          otherwise.
   */
  public CapellaDeleteCommand(ExecutionManager executionManager, Collection<?> selection, boolean ensureTransaction,
      boolean confirmDelete, boolean longOperationEvents) {
    super(executionManager, selection, ensureTransaction, confirmDelete, longOperationEvents);
    this.preventProtectedElementsDeletion = false;
  }

  public void setPreventProtectedElementsDeletion(boolean value) {
    this.preventProtectedElementsDeletion = value;
  }

  /**
   * 
   */
  @Override
  protected Command getDeleteRepresentationCommand(TransactionalEditingDomain editingDomain) {
    return new DeleteRepresentationCommand(editingDomain,
        RepresentationHelper.getAllRepresentationDescriptorsTargetedBy(getExpandedSelection()));
  }

  /**
   * 
   */
  @Override
  protected boolean isConfirmationRequired() {
    return IDeletePreferences.INSTANCE.isConfirmationRequired();
  }

  /**
   * In case we would delete controlled elements, show a dialog to warn the user.
   * 
   * @param controlledElementsToDelete
   */
  @Override
  protected void showAbortDialogForControlledElementsToDelete(final Set<? extends EObject> controlledElementsToDelete) {
    PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
      @Override
      public void run() {
        ImpactAnalysisDialog dialog = new ImpactAnalysisDialog(new ArrayList<EObject>(controlledElementsToDelete),
            Messages.CapellaDeleteCommand_ConfirmLabel, Messages.CapellaDeleteCommand_ControlledElementsError_Message,
            MessageDialog.ERROR,
            new String[] { org.polarsys.capella.common.ui.toolkit.dialogs.Messages.AbstractViewerDialog_OK_Title },
            SWT.COLOR_RED, false);
        dialog.open();
      }
    });
  }

  @Override
  protected IStatus preDeleteChecks() {
    deletionStatus = getStatusMessage(false);
    return deletionStatus;
  }

  @Override
  protected IStatus getInformationMessage() {
    return getStatusMessage(true);
  }

  private IStatus getStatusMessage(boolean isInfoMessage) {
    Set<?> elementsToDelete = getAllElementsToDelete();

    if (preventProtectedElementsDeletion && !CapellaDeleteAction.canDelete(elementsToDelete)) {
      return new Status(Status.ERROR, CapellaActionsActivator.PLUGIN_ID,
          Messages.CapellaDeleteCommand_ProtectedElementsError);
    }

    long nbRepresentations = elementsToDelete.stream().filter(DRepresentationDescriptor.class::isInstance).count();
    long nbSemanticElements = elementsToDelete.size() - nbRepresentations;
    String messageNbSemanticElement = "";
    String messageNbRepresentation = "";

    // compute messages for semantic resources and representations
    if (nbSemanticElements == 1) {
      messageNbSemanticElement = Messages.CapellaDeleteCommand_ConfirmDeletionWithOneSemanticElement;
    } else {
      messageNbSemanticElement = MessageFormat
          .format(Messages.CapellaDeleteCommand_ConfirmDeletionWithManySemanticElement, nbSemanticElements);
    }
    if (nbRepresentations > 0) {
      if (nbRepresentations == 1) {
        messageNbRepresentation = Messages.CapellaDeleteCommand_ConfirmDeletionWithOneRepresentation;
      } else {
        messageNbRepresentation = MessageFormat
            .format(Messages.CapellaDeleteCommand_ConfirmDeletionWithManyRepresentations, nbRepresentations);
      }
    }

    if (isInfoMessage) {
      String message = null;
      if (elementsToDelete.size() == 1) {
        message = MessageFormat.format(Messages.CapellaDeleteCommand_ConfirmDeletionWithOneElementInfo,
            messageNbSemanticElement.length() > 0 ? messageNbSemanticElement : messageNbRepresentation);
      } else {
        message = MessageFormat.format(Messages.CapellaDeleteCommand_ConfirmDeletionWithMultipleDifferentElementsInfo,
            messageNbSemanticElement, messageNbRepresentation);
      }
      return new Status(Status.INFO, CapellaActionsActivator.PLUGIN_ID, message);
    }

    String message = MessageFormat.format(Messages.CapellaDeleteCommand_ConfirmDeletionQuestion,
        messageNbSemanticElement, messageNbRepresentation);
    return new Status(Status.WARNING, CapellaActionsActivator.PLUGIN_ID, message);
  }

  @Override
  protected boolean confirmDeletion() {
    Set<?> elementsToDelete = getAllElementsToDelete();

    ConfirmDeleteCapellaElementDialog confirmDeletionDialog = new ConfirmDeleteCapellaElementDialog(
        new ArrayList<Object>(elementsToDelete), true, getExpandedSelection().toArray()) {

      @Override
      protected IStatus getStatus() {
        return deletionStatus;
      }

      @Override
      protected ILabelProvider createLabelProvider(ILabelProvider provider) {
        if (preventProtectedElementsDeletion && !ProtectedElementsDecorator.isEnabled()) {
          return new DecoratingLabelProvider(provider, new ProtectedElementsDecorator(elementsToDelete));
        }
        return provider;
      }
    };

    PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
      @Override
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
   * Simulate deletion of the given selection up to the point where we show a confirmation dialog to the user. Also
   * performs the check on fragment roots. Needed when deleting elements from a diagram editor for some obscure reason.
   * 
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
