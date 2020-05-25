/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.filters.ju;

import java.util.Enumeration;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.GraphicalFilter;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;

/**
 * This class is a generic test case for diagram filter test. To use it, create a test case that inherits from this
 * class and implement abstract methods, that are (see method documentation for more details) :
 * 
 * - getTestProjectName() - getDiagramName() - getFilterName() - getFilteredObjetIds()
 * 
 * Notice that the tested diagram in the model of test must not have the tested filter activated by default. Indeed, the
 * first check of this generic test case is to verify that elements that must be filtered by the tested filter are not
 * filtered firstly.
 */
public abstract class DiagramTitleBlockFilterTestCase extends DiagramObjectFilterTestCase {

  @Override
  protected String getTestProjectName() {
    return "TitleBlocksModel";
  }
  
  protected void getCurrentFilterMappings() {
    filteredMappingNames.add(IMappingNameConstants.DT_TITLE_BLOCK_CONTAINER);
    filteredMappingNames.add(IMappingNameConstants.DT_TITLE_BLOCK_LINE_CONTAINER);
    filteredMappingNames.add(IMappingNameConstants.DT_TITLE_BLOCK_COLUMN_CONTAINER);
    filteredMappingNames.add(IMappingNameConstants.DT_TITLE_BLOCK_CELL);
    filteredMappingNames.add(IMappingNameConstants.DT_TITLE_BLOCK_EDGE);
  }
  
  protected void preRunTest() {

    getCurrentDiagram();
    getCurrentFilterMappings();

    setFilteredObjectMaps();

    for (String id : filteredObjetIDs) {
      if (!toBeFiltered.values().contains(id)) {
        fail("Element with id: " + id + " is not available for diagram: " + diagramName + " from project "
            + projectTestName);
      }
    }
  }

  /**
   * creates a map with elements that shall be filter and another map with elements that shall not be filtered
   */
  protected void setFilteredObjectMaps() {
    for (DDiagramElement elt : diagram.getDiagramElements()) {
      EObject target = elt.getTarget();
      if(target != null && target instanceof DAnnotation) {
        String targetId = ((DAnnotation) target).getUid();
        String elementMappingName = elt.getMapping().getName();
        if (filteredObjetIDs.contains(targetId) && filteredMappingNames.contains(elementMappingName)) {
          toBeFiltered.put(elt, targetId);
        } else {
          notToFilter.put(elt, targetId);
        }
      }
    }
  }
  
  /**
   * Verifies that all elements that should NOT be filtered, are actually NOT filtered by the specified filter
   */
  protected void ensurePresenceOfElementsToNotFilter() {
    Enumeration<DDiagramElement> elementsToNotFilter = notToFilter.keys();
    while (elementsToNotFilter.hasMoreElements()) {
      DDiagramElement currentElementToVerify = elementsToNotFilter.nextElement();
      if (isFilteredByTestedFilter(currentElementToVerify)) {
        fail("Element: " + ((CapellaElement) currentElementToVerify.getTarget()).getId() + " should not be filtered!");
      }
    }
  }

  /**
   * Verifies that all elements that should be filtered, are actually filtered by the specified filter
   */
  protected void ensureAbsenceOfElementsToFilter() {
    Enumeration<DDiagramElement> elementsToFilter = toBeFiltered.keys();
    while (elementsToFilter.hasMoreElements()) {
      DDiagramElement currentElementToVerify = elementsToFilter.nextElement();
      if (!isFilteredByTestedFilter(currentElementToVerify)) {
        fail("Element: " + ((DAnnotation) currentElementToVerify.getTarget()).getUid() + " should be filtered!");
      }
    }
  }

  protected boolean isFilteredByTestedFilter(DDiagramElement elt) {
    for (GraphicalFilter gFilter : elt.getGraphicalFilters()) {
      DDiagramElement container = (DDiagramElement) gFilter.eContainer();
      return !container.isVisible() && 
          toBeFiltered.keySet().contains(gFilter.eContainer());
    }
    return false;
  }

  /**
   * Returns a predicate that will be applied to each of the elements expected to be filtered. This enables a extension
   * mechanism for tests that want to verify specific behaviors, and not only the absence of the element.
   * 
   * @return a predicate that will be applied to each of the elements expected to be filtered
   */
  protected Predicate<DDiagramElement> getElementsToFilterExtraConditionPredicate() {
    return null;
  }

  /**
   * Returns a predicate that will be applied to each of the elements expected to NOT be filtered. This enables a
   * extension mechanism for tests that want to verify specific behaviors, and not only the presence of the element.
   * 
   * @return a predicate that will be applied to each of the elements expected to NOT be filtered
   */
  protected Predicate<DDiagramElement> getElementsToNotFilterExtraConditionPredicate() {
    return null;
  }
}
