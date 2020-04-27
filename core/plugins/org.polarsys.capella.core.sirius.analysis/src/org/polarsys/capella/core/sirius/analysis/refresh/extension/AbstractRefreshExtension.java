/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
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
  protected boolean isLinkedToUselessTarget(DSemanticDecorator element) {
    // ghost elements on diagram are moved when their target are deleted
    EObject target = element.getTarget();
    return (target == null) || (target.eResource() == null)
        || HoldingResourceFilter.getInstance().isHoldByHoldingResource(target);
  }

  /**
   * this method must be override
   * 
   * @return the list of mappings that can be moved in the diagram
   */
  protected List<AbstractNodeMapping> getListOfMappingsToMove(DDiagram diagram) {
    return Collections.emptyList();
  }

  /**
   * In contextual diagrams, there is a layer. We have to use display manager service to know if an element is displayed
   * in a diagram In DataFlowBlank diagrams, there is no layer. We use the property "isVisible".
   * 
   * 
   * @param anElement
   * @return true if the element is visible in the diagram
   */
  protected boolean isVisibleInDiagram(DDiagramElement anElement) {
    return anElement.isVisible();
  }

  /**
   * Reorders all elements with mappings from the {@link getListOfMappingsToMove method} in the given diagram
   * 
   * @param diagram
   */
  protected void reorderElements(DDiagram diagram) {
    reorderElements(new DDiagramContents(diagram));
  }

  /**
   * Reorders all elements with mappings from the {@link getListOfMappingsToMove method}
   * 
   * @param diagramContent
   */
  protected void reorderElements(DDiagramContents diagramContent) {
    try {

      DDiagram diagram = diagramContent.getDDiagram();

      HashMap<DDiagramElement, DragAndDropTarget> toBeMoved = new HashMap<>(); // diagram elements to be moved

      // Retrieve all mappings to be moved
      List<AbstractNodeMapping> mappingsToMove = getListOfMappingsToMove(diagram);

      // find best container for elements which isn't in a container where
      // element.target.eContainer != element.eContainer.target
      for (DDiagramElement anElement : diagramContent.getDiagramElements()) {

        if (mappingsToMove.contains(anElement.getDiagramElementMapping()) && isReorderable(anElement)) {

          // Retrieve best container and compare if correct containment
          DragAndDropTarget bestContainer = diagramContent.getBestContainer(anElement);
          if (!isWellContained(anElement, bestContainer)) {
            toBeMoved.put(anElement, bestContainer);
          }

        }
      }

      // move elements into their best container
      for (Entry<DDiagramElement, DragAndDropTarget> element : toBeMoved.entrySet()) {
        DDiagramElement key = element.getKey();
        DragAndDropTarget target = element.getValue();

        EList<DDiagramElement> ownedElements = DiagramServices.getDiagramServices().getOwnedDiagramElements(target);
        if (ownedElements != null && !ownedElements.contains(key)) {
          ownedElements.add(key);
        }
      }

    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM).error(Messages.RefreshExtension_ErrorOnReordering, e);
    }
  }

  /**
   * Returns whether the given anElement is contained in the given bestContainer
   * 
   * @param diagram
   * @param anElement
   * @param bestContainer
   * @return
   */
  protected boolean isWellContained(DDiagramElement anElement, EObject bestContainer) {
    return (bestContainer.equals(anElement.eContainer()));
  }

  /**
   * Returns whether the given element is an element which can be reordered
   * 
   * @param diagram
   * @param element
   * @return
   */
  protected boolean isReorderable(DDiagramElement element) {
    if ((element != null)) {
      if (!((element instanceof AbstractDNode)
          && (DiagramServices.getDiagramServices().isABorderedNode((AbstractDNode) element)))) {

        if ((!isLinkedToUselessTarget(element)) && isVisibleInDiagram(element)) {

          return true;
        }
      }
    }
    return false;
  }

}
