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
package org.polarsys.capella.core.sirius.analysis.refresh.extension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.FoldingFilter;
import org.eclipse.sirius.diagram.FoldingPointFilter;
import org.eclipse.sirius.diagram.GraphicalFilter;
import org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtension;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.sirius.analysis.CsServices;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;

/**
 */
public class BreakdownRefreshExtension implements IRefreshExtension {

  /**
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#beforeRefresh(org.eclipse.sirius.DDiagram)
   */
  public void beforeRefresh(DDiagram dDiagram_p) {
    repairCollapsedElements(dDiagram_p);
  }

  protected boolean isDirectlyCollapsed(DDiagramElement element) {
    for (GraphicalFilter filter : element.getGraphicalFilters()) {
      if (filter instanceof FoldingFilter) {
        return true;
      }
    }
    return false;
  }

  /**
   * @param parent_p
   * @return
   */
  private boolean isCollapsedParent(DDiagramElement parent_p) {

    if (!isDirectlyCollapsed(parent_p)) {
      if (parent_p instanceof EdgeTarget) {
        EdgeTarget edgeTarget = (EdgeTarget) parent_p;
        for (DEdge edge : edgeTarget.getIncomingEdges()) {
          if (edge.getSourceNode() != null && edge.getSourceNode() instanceof DDiagramElement) {
            if (isDirectlyCollapsed((DDiagramElement) edge.getSourceNode())) {
              return true;
            }
          }
        }
        return false;
      }
    }
    return true;
  }


  /**
   * @param element_p
   */
  private void unfold(DDiagramElement element_p) {
    Collection<GraphicalFilter> filters = new ArrayList<GraphicalFilter>();

    for (GraphicalFilter filter : element_p.getGraphicalFilters()) {
      if (filter instanceof FoldingFilter) {
        filters.add(filter);
      }
      if (filter instanceof FoldingPointFilter) {
        filters.add(filter);
      }
    }

    for (GraphicalFilter filter : filters) {
      element_p.getGraphicalFilters().remove(filter);
    }

  }

  /**
   * @param dDiagram_p
   */
  protected void repairCollapsedElements(DDiagram diagram_p) {
    Map<EObject, DragAndDropTarget> elements = DiagramServices.getDiagramServices().getMapOfDiagramNodes(diagram_p);

    Collection<DDiagramElement> toUnfold = new HashSet<DDiagramElement>();
    Collection<DDiagramElement> toFold = new HashSet<DDiagramElement>();

    for (DDiagramElement element : DiagramServices.getDiagramServices().getDiagramElements(diagram_p)) {
      if (element instanceof AbstractDNode) {

        boolean elementIsCollapsed = isDirectlyCollapsed(element);
        LinkedList<EObject> parents = new LinkedList<EObject>();
        LinkedList<EObject> visitedObjects = new LinkedList<EObject>();

        EObject target = element.getTarget();

        if (target != null) {

          parents.addAll(getContainers(target));

          //for all semantic parents which are not collapsed, find if element needs to be fold or unfold.
          while (parents.size() > 0) {
            EObject targetParent = parents.removeFirst();
            if (!visitedObjects.contains(targetParent)) {
              visitedObjects.add(targetParent);
              DragAndDropTarget viewParent = elements.get(targetParent);

              if (viewParent != null && viewParent instanceof DDiagramElement) {
                boolean parentIsDirectlyCollapsed = isDirectlyCollapsed((DDiagramElement) viewParent);
                boolean parentIsUndirectlyCollapsed = isCollapsedParent((DDiagramElement) viewParent);

                //specific rules
                if (elementIsCollapsed && !parentIsDirectlyCollapsed) {
                  toUnfold.add(element);
                  break;
                } else if (!elementIsCollapsed && parentIsDirectlyCollapsed) {
                  toFold.add(element);
                  break;
                } else if (elementIsCollapsed && parentIsDirectlyCollapsed && !parentIsUndirectlyCollapsed) {
                  toUnfold.add(element);
                  break;
                }

                if (parentIsDirectlyCollapsed) {
                  break;
                }

                //if no parent found in diagram, reveal it
              } else if (viewParent == null) {
                toUnfold.add(element);
              }

              if (targetParent != null) {
                parents.addAll(getContainers(targetParent));
              }

            }
          }
        }

      }

    }
    //unflod elements which are not correctly graphical-linked according to semantic breakdown
    for (DDiagramElement element : toUnfold) {
      List<EObject> parents = getBreakdownParents(element);

      if (parents.size() > 1) {
        boolean contains = false;
        for (EObject semanticParent : getContainers(element.getTarget())) {
          if (parents.contains(semanticParent)) {
            contains = true;
          }
        }
        if (!contains) {
          unfold(element);
        }
      }
    }

  }

  private Collection<EObject> getContainers(EObject e) {
    Collection<EObject> parents = new ArrayList<EObject>();

    if (e instanceof Component) {
      parents.addAll(CsServices.getService().getContainersOfParts((Component) e));
    } else {
      parents.add(e.eContainer());
    }
    return parents;
  }

  /**
   * Retrieve graphical parents for the given element_p
   * @param element_p
   * @return
   */
  private List<EObject> getBreakdownParents(DDiagramElement element_p) {
    LinkedList<DDiagramElement> parents = new LinkedList<DDiagramElement>();
    LinkedList<DDiagramElement> visitedElements = new LinkedList<DDiagramElement>();
    LinkedList<EObject> visitedObjects = new LinkedList<EObject>();
    parents.add(element_p);

    while (parents.size() > 0) {
      DDiagramElement parent = parents.removeFirst();
      if (!visitedElements.contains(parent)) {
        visitedElements.add(parent);
        visitedObjects.add(parent.getTarget());
        EdgeTarget edgeTarget = (EdgeTarget) parent;
        if (edgeTarget == element_p) {
          for (DEdge edge : edgeTarget.getOutgoingEdges()) {
            if (edge.getTargetNode() != null && edge.getTargetNode() instanceof DDiagramElement) {
              parents.addLast((DDiagramElement) edge.getTargetNode());
            }
          }
        }
      } else if (!visitedElements.contains(parent)) {
        visitedElements.add(parent);
        visitedObjects.add(parent.getTarget());
      }
    }

    return visitedObjects;
  }

  /**
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#postRefresh(org.eclipse.sirius.DDiagram)
   */
  public void postRefresh(DDiagram dDiagram_p) {
    // Nothing

  }

}
