/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.drop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.DragAndDropCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;

import org.polarsys.capella.common.model.copypaste.SharedCopyPasteElements;
import org.polarsys.capella.core.model.helpers.move.MoveHelper;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaCopyToClipboardCommand;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaPasteCommand;
import org.polarsys.capella.core.platform.sirius.ui.commands.PasteCommandHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPlugin;
import org.polarsys.capella.common.helpers.TransactionHelper;

/**
 * The Capella explorer drop assistant.
 */
public class ExplorerDropAdapterAssistant extends AbstractCapellaDropAdapterAssistant {

  protected BasicDropConstraints _basicDropConstraints = null;

  protected BasicDropConstraints getBasicConstraints() {
    if (_basicDropConstraints == null) {
      _basicDropConstraints = new BasicDropConstraints();
    }
    return _basicDropConstraints;
  }

  protected MoveHelper getMoveHelper() {
    return MoveHelper.getInstance();
  }

  /**
   * @see org.eclipse.ui.navigator.CommonDropAdapterAssistant#handleDrop(org.eclipse.ui.navigator.CommonDropAdapter, org.eclipse.swt.dnd.DropTargetEvent,
   *      java.lang.Object)
   */
  @Override
  public IStatus handleDrop(Object target_p, int operation_p, DropTargetEvent dropTargetEvent_p) {

    Object currentTarget = target_p;
    if ((null == currentTarget) || (null == dropTargetEvent_p.data)) {
      return Status.CANCEL_STATUS;
    }

    IStatus status = null;
    List<EObject> modelElements = null;

    TransferData currentTransfer = dropTargetEvent_p.currentDataType;
    if (LocalSelectionTransfer.getTransfer().isSupportedType(currentTransfer)) {
      modelElements = getSelectedElements();
    }

    if ((null != modelElements) && !modelElements.isEmpty()) {
      if (DND.DROP_MOVE == operation_p) {
        status = doHandleDrop(currentTarget, operation_p, dropTargetEvent_p, modelElements);
      } else if (DND.DROP_COPY == operation_p) {
        status = doHandleDrop(currentTarget, operation_p, dropTargetEvent_p, modelElements);
      } else {
        return Status.CANCEL_STATUS;
      }
    }
    return status;
  }

  // Drops the specified model elements from their previous container to the selected target container.
  private IStatus doHandleDrop(final Object target_p, int operation_p, DropTargetEvent event_p, List<EObject> modelElements_p) {
    // Gets the next model element parent.
    Object currentTarget = target_p;
    EObject nextContainer = (EObject) currentTarget;
    // Gets the editing domain.
    TransactionalEditingDomain editingDomain = TransactionHelper.getEditingDomain(nextContainer);
    // Create a drop command.
    Command dropCommand = new DragAndDropCommand(editingDomain, nextContainer, getLocation(event_p), event_p.operations, event_p.detail, modelElements_p) {
      /**
       * {@inheritDoc}
       */
      @Override
      protected boolean prepareDropMoveOn() {
        dropCommand = new CompoundCommand();
        PasteCommandHelper.createPasteCommands(collection, (CompoundCommand) dropCommand, (EObject) owner, null, domain, -1, true);
        dragCommand = RemoveCommand.create(domain, collection);
        boolean result = dragCommand.canExecute() && dropCommand.canExecute();
        return result;
      }

      /**
       * Prepare a drop copy on. We simply copy the selection to the clipboard, followed by
       * an immediate paste. We also clean the clipboard after the operation completes.
       * 
       * TODO: There's no real need to occupy the editing domain clipboard here. We
       * could just use a CopyCommand and use its result for the drop. Unfortunately
       * we're bound to the clipboard method because it incrusts does some semantic tuning
       * (see for example CapellaPasteCommand.prepareAssociationPaste, which I won't touch)
       */
      @Override
      protected boolean prepareDropCopyOn() {
        boolean result = false;
        dragCommand = new CapellaCopyToClipboardCommand(domain, collection, null);
        if (dragCommand.canExecute()) {

          dragCommand.execute();
          isDragCommandExecuted = true;

          dropCommand = new CommandWrapper(new CapellaPasteCommand(domain, owner, null, CommandParameter.NO_INDEX)) {
            @SuppressWarnings("synthetic-access")
            @Override
            public void execute() {
              super.execute();
              domain.getClipboard().clear();
              SharedCopyPasteElements.getInstance().clear();
            }
          };
          if (dropCommand.canExecute()) {
            result = true;
          } else {
            dragCommand.undo();
            isDragCommandExecuted = false;
          }
        }
        return result;
      }
    };

    // Execute it against the TED rather the execution manager, as this command needs to be prepared to be able to run...
    // TED would encapsulate it in a transaction for us.
    editingDomain.getCommandStack().execute(dropCommand);
    return Status.OK_STATUS;
  }

  /**
   * @see org.eclipse.ui.navigator.CommonDropAdapterAssistant#validateDrop(java.lang.Object, int, org.eclipse.swt.dnd.TransferData)
   */
  @Override
  public IStatus validateDrop(Object target_p, int operation_p, TransferData transferType_p) {
    // Checks the target object class.
    if (!(target_p instanceof EObject)) {
      return new Status(IStatus.INFO, CapellaNavigatorPlugin.getDefault().getPluginId(), "Target must be an EObject."); //$NON-NLS-1$
    }

    // Check the target object compatibility.
    EObject targetElement = (EObject) target_p;

    // Drag within Eclipse case.
    if (LocalSelectionTransfer.getTransfer().isSupportedType(transferType_p)) {
      List<EObject> selectedModelElements = getSelectedElements();

      if (selectedModelElements.isEmpty()) {
        return new Status(IStatus.INFO, CapellaNavigatorPlugin.getDefault().getPluginId(), "An error occurred during the drop operation."); //$NON-NLS-1$
      }

      boolean isSemanticallyCorrect = getMoveHelper().checkSemanticRules(selectedModelElements, targetElement).isOK();
      if (!isSemanticallyCorrect) {
        return new Status(IStatus.INFO, CapellaNavigatorPlugin.getDefault().getPluginId(), "Semantic rules failed."); //$NON-NLS-1$
      }

      // Checks all target eReferences compatibility with all selected model elements eClass.
      if (getMoveHelper().checkEMFRules(selectedModelElements, targetElement).isOK()
          && getBasicConstraints().canDrop(selectedModelElements, targetElement, operation_p)) {
        return Status.OK_STATUS;
      }
    }
    return new Status(IStatus.INFO, CapellaNavigatorPlugin.getDefault().getPluginId(), "Needs to be drop onto a right container."); //$NON-NLS-1$
  }

  // Gets the selected model elements.
  private List<EObject> getSelectedElements() {
    ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
    if (selection instanceof IStructuredSelection) {
      List<EObject> modelElements = new ArrayList<EObject>();

      IStructuredSelection structuredSelection = (IStructuredSelection) selection;
      Iterator<?> iterator = structuredSelection.iterator();
      while (iterator.hasNext()) {
        Object object = iterator.next();
        if (object instanceof EObject) {
          EObject element = (EObject) object;
          modelElements.add(element);
        }
      }
      return modelElements;
    }
    return new ArrayList<EObject>(0);
  }

}
