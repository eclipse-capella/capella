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
package org.polarsys.capella.core.sirius.analysis.refresh.extension;

import static org.polarsys.capella.common.helpers.cache.ModelCache.getCache;

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
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.sirius.analysis.CsServices;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;

/**
 */
public class BreakdownRefreshExtension extends AbstractCacheAwareRefreshExtension {

  /**
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#beforeRefresh(org.eclipse.sirius.DDiagram)
   */
  @Override
  public void beforeRefresh(DDiagram dDiagram) {

    super.beforeRefresh(dDiagram);
    repairCollapsedElements(dDiagram);
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
   * @param parent
   * @return
   */
  private boolean isCollapsedParent(DDiagramElement parent) {

    if (!getCache(this::isDirectlyCollapsed, parent)) {
      if (parent instanceof EdgeTarget) {
        EdgeTarget edgeTarget = (EdgeTarget) parent;
        for (DEdge edge : edgeTarget.getIncomingEdges()) {
          if (edge.getSourceNode() instanceof DDiagramElement) {
            if (getCache(this::isDirectlyCollapsed, (DDiagramElement) edge.getSourceNode())) {
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
   * @param element
   */
  private void unfold(DDiagramElement element) {
    Collection<GraphicalFilter> filters = new ArrayList<>();

    for (GraphicalFilter filter : element.getGraphicalFilters()) {
      if (filter instanceof FoldingFilter) {
        filters.add(filter);
      }
      if (filter instanceof FoldingPointFilter) {
        filters.add(filter);
      }
    }

    for (GraphicalFilter filter : filters) {
      element.getGraphicalFilters().remove(filter);
    }

  }

  /**
   * @param diagram
   */
  protected void repairCollapsedElements(DDiagram diagram) {
    Map<EObject, DragAndDropTarget> elements = DiagramServices.getDiagramServices().getMapOfDiagramNodes(diagram);

    Collection<DDiagramElement> toUnfold = new HashSet<>();
    Collection<DDiagramElement> toFold = new HashSet<>();

    for (DDiagramElement element : DiagramServices.getDiagramServices().getDiagramElements(diagram)) {
      if (element instanceof AbstractDNode) {

        boolean elementIsCollapsed = getCache(this::isDirectlyCollapsed, element);

        LinkedList<EObject> parents = new LinkedList<>();
        LinkedList<EObject> visitedObjects = new LinkedList<>();

        EObject target = element.getTarget();

        if (target != null) {

          parents.addAll(getCache(this::getContainers, target));

          // for all semantic parents which are not collapsed, find if element needs to be
          // fold or unfold.
          while (!parents.isEmpty()) {
            EObject targetParent = parents.removeFirst();
            if (!visitedObjects.contains(targetParent)) {
              visitedObjects.add(targetParent);
              DragAndDropTarget viewParent = elements.get(targetParent);

              if (viewParent instanceof DDiagramElement) {

                boolean parentIsDirectlyCollapsed = getCache(this::isDirectlyCollapsed, (DDiagramElement) viewParent);

                boolean parentIsUndirectlyCollapsed = getCache(this::isCollapsedParent, (DDiagramElement) viewParent);

                // specific rules
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

                // if no parent found in diagram, reveal it
              } else if (viewParent == null) {
                toUnfold.add(element);
              }

              if (targetParent != null) {

                parents.addAll(getCache(this::getContainers, targetParent));

              }

            }
          }
        }

      }

    }
    // unflod elements which are not correctly graphical-linked according to
    // semantic breakdown
    for (DDiagramElement element : toUnfold) {
      List<EObject> parents = getBreakdownParents(element);

      if (parents.size() > 1) {
        boolean contains = false;
        for (EObject semanticParent : getCache(this::getContainers, element.getTarget())) {
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

    Collection<EObject> parents = new ArrayList<>();

    if (e instanceof Component) {
      parents.addAll(CsServices.getService().getContainersOfParts((Component) e));
    } else {
      parents.add(e.eContainer());
    }
    return parents;
  }

  /**
   * Retrieve graphical parents for the given element
   * 
   * @param element
   * @return
   */
  private List<EObject> getBreakdownParents(DDiagramElement element) {
    LinkedList<DDiagramElement> parents = new LinkedList<>();
    LinkedList<DDiagramElement> visitedElements = new LinkedList<>();
    LinkedList<EObject> visitedObjects = new LinkedList<>();
    parents.add(element);

    while (!parents.isEmpty()) {
      DDiagramElement parent = parents.removeFirst();
      if (!visitedElements.contains(parent)) {
        visitedElements.add(parent);
        visitedObjects.add(parent.getTarget());
        if (parent == element && parent instanceof EdgeTarget) {

          for (DEdge edge : ((EdgeTarget) parent).getOutgoingEdges()) {
            if (edge.getTargetNode() instanceof DDiagramElement) {
              parents.addLast((DDiagramElement) edge.getTargetNode());
            }
          }
        }
      }
    }

    return visitedObjects;
  }
}
