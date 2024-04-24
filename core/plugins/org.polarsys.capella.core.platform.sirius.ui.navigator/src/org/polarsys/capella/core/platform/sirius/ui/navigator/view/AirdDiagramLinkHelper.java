/*******************************************************************************
 * Copyright (c) 2006, 2024 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.platform.sirius.ui.navigator.view;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.ILinkHelper;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;

/**
 * The link helper for the aird diagram (or viewpoints).
 */
public class AirdDiagramLinkHelper implements ILinkHelper {
  /**
   * Constructs the aird diagram link helper.
   */
  public AirdDiagramLinkHelper() {
    // Do nothing.
  }

  /**
   * @see org.eclipse.ui.navigator.ILinkHelper#activateEditor(org.eclipse.ui.IWorkbenchPage,
   *      org.eclipse.jface.viewers.IStructuredSelection)
   */
  public void activateEditor(IWorkbenchPage page, IStructuredSelection selection) {
    if (!selection.isEmpty()) {
      Object firstElement = selection.getFirstElement();
      if (firstElement instanceof DSemanticDiagram) {
        DSemanticDiagram semanticDiagram = (DSemanticDiagram) firstElement;
        Session session = SessionManager.INSTANCE.getSession(semanticDiagram.getTarget());
        if (null != session) {
          IEditingSession sessionUI = SessionUIManager.INSTANCE.getUISession(session);
          if (null != sessionUI) {
            IEditorPart editor = sessionUI.getEditor(semanticDiagram);
            if (null != editor) {
              PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().activate(editor);
            }
          }
        }
      }
    }
  }

  /**
   * @see org.eclipse.ui.navigator.ILinkHelper#findSelection(org.eclipse.ui.IEditorInput)
   */
  public IStructuredSelection findSelection(IEditorInput anInput) {
    // Gets the active diagram editor.
    IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();

    // Checks selection provider existence.
    ISelectionProvider selectionProvider = activeEditor.getSite().getSelectionProvider();
    if (null == selectionProvider) {
      return new StructuredSelection();
    }

    ISelection selection = selectionProvider.getSelection();
    if ((selection instanceof IStructuredSelection) && !selection.isEmpty()) {
      IStructuredSelection diagramSelection = (IStructuredSelection) selection;
      Object firstElement = diagramSelection.getFirstElement();

      // Gets GMF model element from graphical element.
      if (firstElement instanceof GraphicalEditPart) {
        GraphicalEditPart editPart = (GraphicalEditPart) firstElement;
        editPart = getDiagramEditPart(editPart);
        if (null != editPart) {
          firstElement = editPart.getModel();
        }
      }

      // Returns the corresponding semantic element to the navigator.
      if (firstElement instanceof View) {
        View gmfView = (View) firstElement;
        EObject element = gmfView.getElement();
        if (null != element) {
          if (element instanceof ModelElement) {
            return new StructuredSelection(element);
          }
          if (element instanceof DRepresentation) {
            DRepresentationDescriptor descriptor = RepresentationHelper
                .getRepresentationDescriptor((DRepresentation) element);
            return descriptor == null ? null : new StructuredSelection(descriptor);
          }
        }
      }
    }
    return null;
  }

  private DiagramEditPart getDiagramEditPart(GraphicalEditPart editPart) {
    if (editPart instanceof DiagramEditPart) {
      return (DiagramEditPart) editPart;
    }
    if (null != editPart) {
      GraphicalEditPart parent = (GraphicalEditPart) editPart.getParent();
      return getDiagramEditPart(parent);
    }
    return null;
  }
}
