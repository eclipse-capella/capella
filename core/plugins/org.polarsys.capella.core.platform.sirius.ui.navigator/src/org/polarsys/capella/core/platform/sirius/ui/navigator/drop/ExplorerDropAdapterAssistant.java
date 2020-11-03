/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.navigator.drop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.ui.statushandlers.StatusManager;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.ui.StatusManagerExceptionHandler;
import org.polarsys.capella.core.model.helpers.move.MoveHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPlugin;

/**
 * The Capella explorer drop assistant.
 */
public class ExplorerDropAdapterAssistant extends AbstractCapellaDropAdapterAssistant {

  protected BasicDropConstraints _basicDropConstraints = null;
  private final MoveHelper moveHelper;

  public ExplorerDropAdapterAssistant(MoveHelper moveHelper) {
    this.moveHelper = moveHelper;
  }

  public ExplorerDropAdapterAssistant() {
    this(MoveHelper.getInstance());
  }

  protected BasicDropConstraints getBasicConstraints() {
    if (_basicDropConstraints == null) {
      _basicDropConstraints = new BasicDropConstraints();
    }
    return _basicDropConstraints;
  }

  protected MoveHelper getMoveHelper() {
    return moveHelper;
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
    Command dropCommand = new CapellaDragAndDropCommand(editingDomain, nextContainer, getLocation(event_p), event_p.operations, event_p.detail, modelElements_p);
    new StatusManagerExceptionHandler(StatusManager.SHOW | StatusManager.LOG).installAndExecute(((TransactionalCommandStack)editingDomain.getCommandStack()), dropCommand);
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
