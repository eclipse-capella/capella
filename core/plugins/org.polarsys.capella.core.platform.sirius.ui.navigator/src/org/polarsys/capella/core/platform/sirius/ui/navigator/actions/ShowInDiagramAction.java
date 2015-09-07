/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.part.IDiagramDialectGraphicalViewer;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;

/**
 * Show selected {@link ModelElement} in the active diagram editor.
 */
public class ShowInDiagramAction extends BaseSelectionListenerAction implements IViewActionDelegate {
  /**
   * Constructor.
   */
  public ShowInDiagramAction() {
    super(Messages.ShowInDiagramAction_Title);
    setActionDefinitionId("org.polarsys.capella.core.platform.sirius.ui.navigator.showInDiagramCommand"); //$NON-NLS-1$
  }

  /**
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {

    Object selectedElement = getStructuredSelection().getFirstElement();
    // Precondition : ignore null or non ModelElement.
    if (!(CapellaResourceHelper.isSemanticElement(selectedElement))) {
      return;
    }

    DSemanticDecorator view = null;

    boolean allowMultiplePart = TriStateBoolean.True.equals(CapellaProjectHelper
        .isReusableComponentsDriven((EObject) selectedElement));
    if (!allowMultiplePart) {
      if (selectedElement instanceof Component) {
        for (Part part : ComponentExt.getRepresentingParts((Component) selectedElement)) {
          view = getPreferedView(part);
          if (view != null) {
            break;
          }
        }
      }
    }
    if (view == null) {
      view = getPreferedView((EObject) selectedElement);
    }

    if (view == null) {
      MessageDialog.openInformation(Display.getCurrent().getActiveShell(),
          Messages.ShowInDiagramAction_UnknownElement_Title, Messages.ShowInDiagramAction_UnknownElement_Message);
    } else {

      if (view instanceof DDiagramElement) {
        DDiagramElementQuery query = new DDiagramElementQuery((DDiagramElement) view);
        if (query.isFolded()) {
          MessageDialog.openInformation(Display.getCurrent().getActiveShell(),
              Messages.ShowInDiagramAction_UnknownElement_Title, Messages.ShowInDiagramAction_FoldedElement_Message);

        } else if (query.isHidden()) {
          MessageDialog.openInformation(Display.getCurrent().getActiveShell(),
              Messages.ShowInDiagramAction_UnknownElement_Title, Messages.ShowInDiagramAction_HiddenElement_Message);

        } else if (query.isCollapsed()) {
          MessageDialog.openInformation(Display.getCurrent().getActiveShell(),
              Messages.ShowInDiagramAction_UnknownElement_Title, Messages.ShowInDiagramAction_CollapseElement_Message);

        } else if (query.isFiltered()) {
          MessageDialog.openInformation(Display.getCurrent().getActiveShell(),
              Messages.ShowInDiagramAction_UnknownElement_Title, Messages.ShowInDiagramAction_FilteredElement_Message);

        } else {

          IGraphicalEditPart selectedPart = getGraphicalPart(view);
          if (selectedPart == null) {
            MessageDialog.openInformation(Display.getCurrent().getActiveShell(),
                Messages.ShowInDiagramAction_UnknownElement_Title, Messages.ShowInDiagramAction_UnknownElement_Message);
          } else {
            selectPart(selectedPart);
          }
        }

      } else if (view instanceof DDiagram) {
        MessageDialog.openInformation(Display.getCurrent().getActiveShell(),
            Messages.ShowInDiagramAction_UnknownElement_Title, Messages.ShowInDiagramAction_UnknownElement_Message);
      }

    }

  }

  /**
   * @param part
   * @return
   */
  protected DSemanticDecorator getPreferedView(EObject semantic) {
    IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();

    if ((null != activeEditor) && (activeEditor instanceof SiriusDiagramEditor)) {
      SiriusDiagramEditor diagramEditor = (SiriusDiagramEditor) activeEditor;

      DRepresentation diagram = (DRepresentation) diagramEditor.getDiagram().getElement();
      Collection<DSemanticDecorator> views = DiagramServices.getDiagramServices().getDiagramElements(diagram, semantic);

      if (views.size() == 1) {
        return views.iterator().next();
      }

      // Multiple views are found.
      // In some cases, 2 UI representations can point to the same target e.g an DEdge and a AbstractDNode.
      // Priority is done to the AbstractDNode vs an DEdge.
      for (DSemanticDecorator view : views) {
        if (view instanceof AbstractDNode) {
          return view;
        }
      }
      for (DSemanticDecorator view : views) {
        if (view instanceof DEdge) {
          return view;
        }
      }
    }

    return null;
  }

  /**
   * @param selectedElement
   */
  protected IGraphicalEditPart getGraphicalPart(DSemanticDecorator view) {
    // A crossReferencer should be enough to retrieve these elements, but in some buggy case, 2 GMF diagrams for one
    // DDiagram

    IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
    IGraphicalEditPart graphicalEditPart = null;

    if ((null != activeEditor) && (activeEditor instanceof SiriusDiagramEditor)) {
      SiriusDiagramEditor diagramEditor = (SiriusDiagramEditor) activeEditor;

      // Get the graphical viewer.
      IDiagramGraphicalViewer diagramGraphicalViewer = diagramEditor.getDiagramGraphicalViewer();

      // Check it is a Sirius one.
      if (diagramGraphicalViewer instanceof IDiagramDialectGraphicalViewer) {
        IDiagramDialectGraphicalViewer dialectViewer = (IDiagramDialectGraphicalViewer) diagramGraphicalViewer;

        // Search all edit parts linked to selected object.
        List<IGraphicalEditPart> allEditParts = dialectViewer.findEditPartsForElement(view.getTarget(),
            IGraphicalEditPart.class);
        // Iterate over retrieved edit parts to remove the ones related to 'label' edit part.
        for (Iterator<IGraphicalEditPart> iterator = allEditParts.iterator(); iterator.hasNext();) {
          IGraphicalEditPart editPart = iterator.next();
          // Filter out label edit part.
          if (editPart instanceof IDiagramNameEditPart) {
            iterator.remove();
          }
        }

        if (allEditParts.size() == 1) {
          // Select directly this one.
          graphicalEditPart = allEditParts.get(0);
        } else {
          // Multiple edit parts are found.
          // In some cases, 2 UI representations can point to the same target e.g an DEdge and a AbstractDNode.
          // Priority is done to the AbstractDNode vs an DEdge.
          for (IGraphicalEditPart currentGraphicalEditPart : allEditParts) {
            if (!(currentGraphicalEditPart instanceof IDiagramEdgeEditPart)) {
              graphicalEditPart = currentGraphicalEditPart;
              break;
            }
          }
        }
      }
    }
    return graphicalEditPart;
  }

  protected void selectPart(IGraphicalEditPart part) {
    IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
    SiriusDiagramEditor diagramEditor = (SiriusDiagramEditor) activeEditor;

    // Get the graphical viewer.
    IDiagramGraphicalViewer diagramGraphicalViewer = diagramEditor.getDiagramGraphicalViewer();
    // Select the found graphical edit part.
    if (null != part) {
      diagramGraphicalViewer.select(part);
      diagramGraphicalViewer.reveal(part);
    }
  }

  /**
   * @see org.eclipse.ui.actions.BaseSelectionListenerAction#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
   */
  @Override
  protected boolean updateSelection(IStructuredSelection selection) {
    boolean result = false;

    if (!selection.isEmpty()) {
      result = (CapellaResourceHelper.isSemanticElements(selection.toList())) ? true : false;
    }

    IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
    result &= (activeEditor instanceof SiriusDiagramEditor);

    return result;
  }

  /**
   * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
   */
  @Override
  public void init(IViewPart view) {
    // Do nothing.
  }

  /**
   * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
   */
  @Override
  public void run(IAction action) {
    run();
  }

  /**
   * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
   *      org.eclipse.jface.viewers.ISelection)
   */
  @Override
  public void selectionChanged(IAction action, ISelection selection) {
    selectionChanged((IStructuredSelection) selection);
  }
}
