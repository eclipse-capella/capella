/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.ui.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;

public class SelectionHelper {

  public static SelectionHelper eINSTANCE = new SelectionHelper();

  private SelectionHelper() {
  }

  public boolean hasElementsOfSameType(IStructuredSelection selection) {
    return !getElementsOfSameType(selection).isEmpty();
  }

  public Collection<DDiagramElement> getElementsOfSameType(IStructuredSelection selection) {
    List<DSemanticDecorator> semanticDecorators = getDSemanticDecoratorsFromSelection(selection);
    if (semanticDecorators.isEmpty() || semanticDecorators.size() != selection.size())
      return new ArrayList<DDiagramElement>();

    DSemanticDiagram diagram = (DSemanticDiagram) DiagramHelper.getService()
        .getDiagramContainer(semanticDecorators.get(0));
    return DiagramServices.getDiagramServices().getViewsRepresentingSameEType(diagram, semanticDecorators);
  }

  public Collection<DDiagramElement> getRelatedFunctionalChainElements(IStructuredSelection selection) {
    List<DSemanticDecorator> semanticDecorators = getDSemanticDecoratorsFromSelection(selection);
    if (semanticDecorators.isEmpty() || semanticDecorators.size() != selection.size())
      return new ArrayList<DDiagramElement>();

    DSemanticDiagram diagram = (DSemanticDiagram) DiagramHelper.getService()
        .getDiagramContainer(semanticDecorators.get(0));
    return DiagramServices.getDiagramServices().getRelatedFunctionalChainElements(diagram, semanticDecorators);
  }

  public Collection<DDiagramElement> getRelatedPhysicalPathElements(IStructuredSelection selection) {
    List<DSemanticDecorator> semanticDecorators = getDSemanticDecoratorsFromSelection(selection);
    if (semanticDecorators.isEmpty() || semanticDecorators.size() != selection.size())
      return new ArrayList<DDiagramElement>();

    DSemanticDiagram diagram = (DSemanticDiagram) DiagramHelper.getService()
        .getDiagramContainer(semanticDecorators.get(0));
    return DiagramServices.getDiagramServices().getRelatedPhysicalPathElements(diagram, semanticDecorators);
  }

  public boolean hasResemblingElements(IStructuredSelection selection) {
    return !getResemblingElements(selection).isEmpty();
  }

  public Collection<DDiagramElement> getResemblingElements(IStructuredSelection selection) {
    List<DSemanticDecorator> semanticDecorators = getDSemanticDecoratorsFromSelection(selection);
    if (semanticDecorators.isEmpty() || semanticDecorators.size() != selection.size())
      return new ArrayList<DDiagramElement>();

    DSemanticDiagram diagram = (DSemanticDiagram) DiagramHelper.getService()
        .getDiagramContainer(semanticDecorators.get(0));
    return DiagramServices.getDiagramServices().getViewsWithSameMapping(diagram, semanticDecorators);
  }

  protected List<GraphicalEditPart> getGraphicalEditPartsFromSelection(IStructuredSelection selection) {
    List<GraphicalEditPart> editParts = new ArrayList<GraphicalEditPart>();
    for (Object selectedElement : selection.toList()) {
      if (selectedElement instanceof GraphicalEditPart) {
        editParts.add((GraphicalEditPart) selectedElement);
      }
    }
    return editParts;
  }

  protected List<DSemanticDecorator> getDSemanticDecoratorsFromSelection(IStructuredSelection selection) {
    List<GraphicalEditPart> editParts = getGraphicalEditPartsFromSelection(selection);

    List<DSemanticDecorator> semanticDecorators = editParts.stream()
        .filter(selectedElement -> selectedElement instanceof GraphicalEditPart)
        .map(editPart -> ((GraphicalEditPart) editPart).getModel()).filter(model -> model instanceof View)
        .map(model -> ((View) model).getElement()).filter(element -> element instanceof DSemanticDecorator)
        .map(DSemanticDecorator.class::cast).collect(Collectors.toList());

    return semanticDecorators;
  }

  public boolean hasOwnedPorts(IStructuredSelection selection) {
    return !getOwnedPorts(selection).isEmpty();
  }

  public Collection<DNode> getOwnedPorts(IStructuredSelection selection) {
    List<DSemanticDecorator> semanticDecorators = getDSemanticDecoratorsFromSelection(selection);

    DSemanticDiagram diagram = (DSemanticDiagram) DiagramHelper.getService()
        .getDiagramContainer(semanticDecorators.get(0));
    return DiagramServices.getDiagramServices().getAllOwnedPorts(diagram, semanticDecorators);
  }

  public boolean hasOwnedElements(IStructuredSelection selection) {
    return !getOwnedElements(selection).isEmpty();
  }

  public Collection<DDiagramElement> getOwnedElements(IStructuredSelection selection) {
    List<DSemanticDecorator> semanticDecorators = getDSemanticDecoratorsFromSelection(selection);

    if (semanticDecorators.stream().filter(aDecorator -> aDecorator instanceof DNodeContainer).count() == 0) {
      return Collections.emptyList();
    }

    DSemanticDiagram diagram = (DSemanticDiagram) DiagramHelper.getService()
        .getDiagramContainer(semanticDecorators.get(0));
    return DiagramServices.getDiagramServices().getAllOwnedElements(diagram, semanticDecorators);
  }

  public boolean hasRelatedConnections(IStructuredSelection selection) {
    return !getRelatedConnections(selection).isEmpty();
  }

  public Collection<DEdge> getRelatedConnections(IStructuredSelection selection) {
    List<DSemanticDecorator> semanticDecorators = getDSemanticDecoratorsFromSelection(selection);

    DSemanticDiagram diagram = (DSemanticDiagram) DiagramHelper.getService()
        .getDiagramContainer(semanticDecorators.get(0));
    return DiagramServices.getDiagramServices().getAllEdges(diagram, semanticDecorators);
  }
}
