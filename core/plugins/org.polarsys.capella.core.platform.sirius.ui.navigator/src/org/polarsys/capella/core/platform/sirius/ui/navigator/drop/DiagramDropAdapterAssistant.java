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

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.diagram.helpers.naming.DiagramDescriptionConstants;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

/**
 * Drop adapter assistant to handle diagram moves in the Capella Explorer.
 */
public class DiagramDropAdapterAssistant extends AbstractCapellaDropAdapterAssistant {
  private CommonViewer _commonViewer;

  /**
   * {@inheritDoc}
   */
  @Override
  protected void doInit() {
    // Only way found to get the Capella Navigator viewer
    _commonViewer =
        ((CommonNavigator) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(CapellaCommonNavigator.ID)).getCommonViewer();
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  @Override
  public IStatus handleDrop(Object target_p, int operation_p, DropTargetEvent dropTargetEvent_p) {
    IStatus status = null;
    if (DND.DROP_MOVE == operation_p) {
      // Get dropable elements.
      IStructuredSelection selection = (IStructuredSelection) LocalSelectionTransfer.getTransfer().getSelection();
      List<Object> dropableElements = selection.toList();
      // Create a compound command that contains all SetCommand to change the target of all dropable diagrams.
      CompoundCommand dropCommand = new CompoundCommand();
      for (Object object : dropableElements) {
        // valideDrop(..) does not ensure that all dropped ones are DSemanticDiagrams.
        if (object instanceof DSemanticDiagram) {
          DSemanticDiagram diagram = (DSemanticDiagram) object;
          // Get the editing domain.
          TransactionalEditingDomain editingDomain = TransactionHelper.getEditingDomain(diagram);
          // Create a command to set the new diagram target element.
          Command setTargetCommand = new SetCommand(editingDomain, diagram, ViewpointPackage.Literals.DSEMANTIC_DECORATOR__TARGET, target_p) {
            /**
             * Overridden to force a viewer refresh on new & old values. {@inheritDoc}
             */
            @Override
            public void doExecute() {
              super.doExecute();
              updateViewer();
            }

            /**
             * Force to update the viewer.
             */
            @SuppressWarnings("synthetic-access")
            private void updateViewer() {
              _commonViewer.refresh(getOldValue(), false);
              _commonViewer.refresh(getValue(), false);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void doRedo() {
              super.doRedo();
              updateViewer();
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void doUndo() {
              super.doUndo();
              updateViewer();
            }
          };
          dropCommand.append(setTargetCommand);
          // Execute the command this way to enable undo redo against the updateViewer methods.
          // The undo / redo of a RecordingCommand only undo or redo model changes, that's not enough here.
          editingDomain.getCommandStack().execute(dropCommand);
        }
      }
    }
    return status;
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  @Override
  public IStatus validateDrop(Object target_p, int operation_p, TransferData transferType_p) {
    IStatus result = Status.CANCEL_STATUS;
    // Drop adapter assistant registration ensures target element kind is an ExtensibleElement.
    EObject targetElement = (EObject) target_p;
    // Get dropable elements.
    IStructuredSelection selection = (IStructuredSelection) LocalSelectionTransfer.getTransfer().getSelection();
    List<Object> dropableElements = selection.toList();
    // Loop over dropable elements to only keep DSemanticDiagram.
    for (Object dropableElement : dropableElements) {
      if (dropableElement instanceof DSemanticDiagram) {
        DSemanticDiagram dropableDiagram = (DSemanticDiagram) dropableElement;
        // Get its description.
        DiagramDescription description = dropableDiagram.getDescription();
        // Get its domain class.
        String domainClass = description.getDomainClass();
        // Get its diagram id (based on name).
        String diagramId = description.getName();
        // Check drop conditions :
        // Check diagram id condition.
        boolean isValid =
            DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME.equals(diagramId) || IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(diagramId)
                || IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME.equals(diagramId)
                || IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME.equals(diagramId) || diagramId.contains("Data Flow Blank"); //$NON-NLS-1$
        // Check target element type is compatible with diagram target element type.
        if (isValid) {
          if (targetElement.eClass().getName().equals(domainClass)) {
            result = Status.OK_STATUS;
          } else {
            for (EClass aClass : targetElement.eClass().getEAllSuperTypes()) {
              if (domainClass.equals(aClass.getName())) {
                result = Status.OK_STATUS;
                break;
              }
            }
          }

        }
      }
    }
    return result;
  }

}
