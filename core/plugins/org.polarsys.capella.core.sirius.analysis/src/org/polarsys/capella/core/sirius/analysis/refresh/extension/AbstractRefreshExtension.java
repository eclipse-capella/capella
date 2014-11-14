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

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.data.helpers.modellingcore.utils.HoldingResourceFilter;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;

/**
 *
 */
public abstract class AbstractRefreshExtension {

  /**
   * Returns whether the DSemanticDecorator holds a null target or a target hold by the holding resource
   */
  protected boolean isLinkedToUselessTarget(DSemanticDecorator element_p) {
    // ghost elements on diagram are moved when their target are deleted
    EObject target = element_p.getTarget();
    return (target == null) || (target.eResource() == null) || HoldingResourceFilter.getInstance().isHoldByHoldingResource(target);
  }

  /**
   * this method must be override
   * @return the list of mappings that can be moved in the diagram
   */
  @SuppressWarnings("unchecked")
  protected List<AbstractNodeMapping> getListOfMappingsToMove(DDiagram diagram_p) {
    return Collections.EMPTY_LIST;
  }

  /**
   * In contextual diagrams, there is a layer. We have to use display manager service to know if an element is displayed in a diagram
   * In DataFlowBlank diagrams, there is no layer. We use the property "isVisible".
   * @param diagram_p
   * @param anElement
   * @return true if the element is visible in the diagram
   */
  protected boolean isVisibleInDiagram(DDiagram diagram_p, DDiagramElement anElement) {
    return anElement.isVisible();
  }

  /**
   * Reorders all elements with mappings from the {@link getListOfMappingsToMove method} in the given diagram
   * @param diagramContent_p
   */
  protected void reorderElements(DDiagram diagram_p) {
    reorderElements(new DDiagramContents(diagram_p));
  }

  /**
   * Reorders all elements with mappings from the {@link getListOfMappingsToMove method}
   * @param diagramContent_p
   */
  protected void reorderElements(DDiagramContents diagramContent_p) {
    try {

      DDiagram diagram = diagramContent_p.getDDiagram();

      Hashtable<DDiagramElement, DragAndDropTarget> toBeMoved = new Hashtable<DDiagramElement, DragAndDropTarget>(); // diagram elements to be moved

      //Retrieve all mappings to be moved
      List<AbstractNodeMapping> mappingsToMove = getListOfMappingsToMove(diagram);

      //find best container for elements which isn't in a container where element.target.eContainer != element.eContainer.target
      for (DDiagramElement anElement : diagramContent_p.getDiagramElements()) {
        if (mappingsToMove.contains(anElement.getDiagramElementMapping())) {

          if (isReorderable(diagram, anElement)) {
            //Retrieve best container and compare if correct containment
            DragAndDropTarget bestContainer = diagramContent_p.getBestContainer(anElement);
            if (!isWellContained(diagram, anElement, bestContainer)) {
              toBeMoved.put(anElement, bestContainer);
            }
          }
        }
      }

      //move elements into their best container
      for (DDiagramElement key : toBeMoved.keySet()) {
        DragAndDropTarget target = toBeMoved.get(key);
        if (target != null) {
          if (target instanceof DDiagram) {
            if (!((DDiagram) target).getOwnedDiagramElements().contains(key)) {
              ((DDiagram) target).getOwnedDiagramElements().add(key);
            }
          } else if (target instanceof DNodeContainer) {
            if (!((DNodeContainer) target).getOwnedDiagramElements().contains(key)) {
              ((DNodeContainer) target).getOwnedDiagramElements().add(key);
            }
          }
        }
      }

    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM).error(Messages.RefreshExtension_ErrorOnReordering, e);
    }
  }

  /**
   * Returns whether the given anElement_p is contained in the given bestContainer
   * @param diagram_p
   * @param anElement_p
   * @param bestContainer_p
   * @return
   */
  protected boolean isWellContained(DDiagram diagram_p, DDiagramElement anElement_p, EObject bestContainer_p) {
    return (bestContainer_p.equals(anElement_p.eContainer()));
  }

  /**
   * Returns whether the given element_p is an element which can be reordered
   * @param diagram_p
   * @param element_p
   * @return
   */
  protected boolean isReorderable(DDiagram diagram_p, DDiagramElement element_p) {
    if ((element_p != null)) {
      if (!((element_p instanceof AbstractDNode) && (DiagramServices.getDiagramServices().isABorderedNode((AbstractDNode) element_p)))) {
        if ((!isLinkedToUselessTarget(element_p)) && isVisibleInDiagram(diagram_p, element_p)) {
          return true;
        }
      }
    }
    return false;
  }

}
