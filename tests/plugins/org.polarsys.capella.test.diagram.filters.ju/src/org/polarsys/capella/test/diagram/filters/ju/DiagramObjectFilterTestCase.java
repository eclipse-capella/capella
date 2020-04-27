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
package org.polarsys.capella.test.diagram.filters.ju;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.function.Predicate;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.AppliedCompositeFilters;
import org.eclipse.sirius.diagram.CollapseFilter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.GraphicalFilter;
import org.eclipse.sirius.diagram.business.api.query.CompositeFilterDescriptionQuery;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription;
import org.eclipse.sirius.diagram.description.filter.Filter;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.description.filter.MappingFilter;
import org.junit.Assert;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.api.NonDirtyTestCase;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.HelperMessages;

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
public abstract class DiagramObjectFilterTestCase extends NonDirtyTestCase {

  // these values are obtained by using methods defined in concrete test cases
  protected String diagramName = getDiagramName();
  protected String projectTestName = getTestProjectName();
  protected String filterName = getFilterName();
  protected List<String> filteredObjetIDs = getFilteredObjetIDs();

  // internal variables
  protected Session session;
  protected DDiagram diagram;

  @Deprecated
  protected Hashtable<String, DDiagramElement> diagramElement2ObjectID = new Hashtable<String, DDiagramElement>();

  @Deprecated
  protected List<DDiagramElement> notFiltered = new ArrayList<>();

  protected Hashtable<DDiagramElement, String> toBeFiltered = new Hashtable<>();
  protected Hashtable<DDiagramElement, String> notToFilter = new Hashtable<>();
  protected List<String> filteredMappingNames = new ArrayList<>();

  // these methods must be overridden by concrete test cases
  /** returns the name of the test project folder (by default in the folder "model") */
  protected abstract String getTestProjectName();

  /** returns the name of the tested diagram in the test project */
  protected abstract String getDiagramName();

  /** returns the name of the tested filter in the tested diagram */
  protected abstract String getFilterName();

  /** returns the ID list of all objects in the tested diagram that should be filtered by the tested filter */
  protected abstract List<String> getFilteredObjetIDs();

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(projectTestName);
  }

  protected void getCurrentDiagram() {

    session = getSessionForTestModel(projectTestName);
    IFile airdFile = getAirdFileForLoadedModel(projectTestName);
    GuiActions.openSession(airdFile, true);

    diagram = (DDiagram) DiagramHelper.getDRepresentation(session, diagramName);
    Assert.assertNotNull(MessageFormat.format(HelperMessages.diagramNotContainedInSession, diagramName, airdFile),
        diagram);// test case check
  }

  public EList<DiagramElementMapping> getHiddenMappingsForCollapseFilters(CompositeFilterDescriptionQuery filterQuery) {
    EList<DiagramElementMapping> result = new BasicEList<DiagramElementMapping>();
    for (Filter filter : filterQuery.getCollapseFilters()) {
      if (filter instanceof MappingFilter) {
        result.addAll(((MappingFilter) filter).getMappings());
      }

    }
    return result;
  }

  private void getCurrentFilterMappings() {

    FilterDescription currentFilter = DiagramHelper.getFilterForDiagram(diagram, filterName);
    CompositeFilterDescriptionQuery filterQuery = new CompositeFilterDescriptionQuery(
        (CompositeFilterDescription) currentFilter);

    for (DiagramElementMapping hiddenMapping : getHiddenMappingsForCollapseFilters(filterQuery)) {
      filteredMappingNames.add(hiddenMapping.getName());
    }

    for (DiagramElementMapping hiddenMapping : filterQuery.getHiddenMappings()) {
      filteredMappingNames.add(hiddenMapping.getName());
    }
  }

  protected void checkAndInsertFilter() {

    FilterDescription filter = DiagramHelper.getFilterForDiagram(diagram, filterName);
    Assert.assertNotNull(MessageFormat.format(HelperMessages.filterNotFound, filterName, diagramName), filter);

    // Check that the filter is not already in the diagram
    EList<FilterDescription> activeFilters = diagram.getActivatedFilters();
    if (activeFilters.contains(filter)) {
      DiagramHelper.removeFilterInDiagram(diagram, filter);
      DiagramHelper.refreshDiagram(diagram);
    }

    // activate the filter
    DiagramHelper.addFilterInDiagram(diagram, filter);

    // Refresh
    DiagramHelper.refreshDiagram(diagram);

    // check that filter is active
    assertFilterActive(diagram, filterName);
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

  protected void setFilteredObjectMaps() {
    for (DDiagramElement elt : diagram.getDiagramElements()) {

      EObject target = elt.getTarget();
      if (target != null && target instanceof CapellaElement) {

        String targetId = ((CapellaElement) target).getId();
        String elementMappingName = elt.getMapping().getName();
        if (filteredObjetIDs.contains(targetId) && filteredMappingNames.contains(elementMappingName)) {
          toBeFiltered.put(elt, targetId);
        } else {
          notToFilter.put(elt, targetId);
        }
      }
    }
  }

  @Override
  public void test() {
    preRunTest();
    checkAndInsertFilter();
    postRunTest();
  }

  protected void postRunTest() {

    ensureAbsenceOfElementsToFilter();
    ensurePresenceOfElementsToNotFilter();

    checkElementsToFilterExtraConditions();
    checkElementsToNotFilterExtraConditions();
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
      currentElementToVerify.getGraphicalFilters();
      if (!isFilteredByTestedFilter(currentElementToVerify)) {
        fail("Element: " + ((CapellaElement) currentElementToVerify.getTarget()).getId() + " should be filtered!");
      }
    }
  }

  protected boolean isFilteredByTestedFilter(DDiagramElement elt) {

    for (GraphicalFilter gFilter : elt.getGraphicalFilters()) {
      if (gFilter instanceof AppliedCompositeFilters) {
        EList<CompositeFilterDescription> descriptions = ((AppliedCompositeFilters) gFilter)
            .getCompositeFilterDescriptions();
        for (CompositeFilterDescription description : descriptions) {
          if (description.getName().contentEquals(this.filterName)) {
            return true;
          }
        }
      }
      if (gFilter instanceof CollapseFilter) {
        return true;
      }
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

  protected void checkElementsToFilterExtraConditions() {
    Predicate<DDiagramElement> predicate = getElementsToFilterExtraConditionPredicate();
    if (predicate != null) {

      for (DDiagramElement diagramElement : toBeFiltered.keySet()) {
        assertTrue("Element " + ((CapellaElement) diagramElement.getTarget()).getId() + " does not obey the predicate "
            + predicate.toString(), predicate.test(diagramElement));
      }
    }
  }

  protected void checkElementsToNotFilterExtraConditions() {
    Predicate<DDiagramElement> predicate = getElementsToNotFilterExtraConditionPredicate();
    if (predicate != null) {

      for (DDiagramElement diagramElement : notToFilter.keySet()) {
        assertTrue("Element " + ((CapellaElement) diagramElement.getTarget()).getId() + " does not obey the predicate "
            + predicate.toString(), predicate.test(diagramElement));
      }
    }
  }

  // This has been deprecated because it is not important that the element is filtered by something
  // It is important the the element is filtered by the tested filter
  @Deprecated
  protected boolean isFiltered(DDiagramElement elt) {
    return DiagramHelper.isDiagramElementFiltered(elt);
  }

  /**
   * Checks that the given filter is activated on the given diagram.
   * 
   * @param diagram
   * @param filterName
   */
  protected void assertFilterActive(DDiagram diagram, String filterName) {
    FilterDescription filter = DiagramHelper.getFilterForDiagram(diagram, filterName);
    Assert.assertNotNull(MessageFormat.format(HelperMessages.filterNotFound, filterName, EObjectExt.getText(diagram)),
        filter);

    EList<FilterDescription> activatedFilters = diagram.getActivatedFilters();
    assertTrue(activatedFilters.contains(filter));
  }
}
