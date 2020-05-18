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
package org.polarsys.capella.test.diagram.common.ju.wrapper.utils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayService;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager;
import org.eclipse.sirius.diagram.business.api.helper.filter.FilterService;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription;
import org.eclipse.sirius.diagram.description.filter.Filter;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.description.filter.FilterKind;
import org.eclipse.sirius.diagram.description.filter.MappingFilter;
import org.eclipse.sirius.tools.api.interpreter.InterpreterRegistry;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.junit.Assert;
import org.polarsys.capella.common.ef.command.AbstractCommand;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 * Utility for filters applied on a diagram
 */
public class FilterOnDiagramHelper {

  /**
   * Check if the elements are correctly showed (visible in diagram)
   * 
   * @param diagram
   * @param compositeFilterDescription
   */
  public static void checkShowElementsInDiagram(DDiagram diagram,
      CompositeFilterDescription compositeFilterDescription) {
    Map<DiagramElementMapping, String> mappings = getMappingsFromCompositeFilter(compositeFilterDescription);
    EList<DDiagramElement> diagramElements = diagram.getDiagramElements();
    DisplayService displayService = DisplayServiceManager.INSTANCE.getDisplayService();

    for (DDiagramElement dDiagramElement : diagramElements) {
      // if an element mapping match the list of mapping filtered, assert that the element is visible in the diagram
      if (mappings.containsKey(dDiagramElement.getMapping())) {
        boolean visible = displayService.isDisplayed(diagram, dDiagramElement);
        if (!visible) {
          // specific test for UnCollapseFilter
          checkUnCollapseFilter(compositeFilterDescription, dDiagramElement);
        } else {
          Assert.assertTrue(MessageFormat.format(Messages.elementShouldBeVisible, dDiagramElement.getName(),
              compositeFilterDescription.getName()), visible);
        }
      }
    }
  }

  /**
   * Get CollapseFilter from a list of compositeFilterDescription and check if the dDiagramElement is visible
   * 
   * @param compositeFilterDescription
   * @param dDiagramElement
   * @return
   */
  private static boolean checkUnCollapseFilter(CompositeFilterDescription compositeFilterDescription,
      DDiagramElement dDiagramElement) {
    EList<Filter> filters = compositeFilterDescription.getFilters();
    boolean found = false;

    for (int i = 0; (i < filters.size()) && !found; i++) {
      Filter filter = filters.get(i);
      if (filter.getFilterKind().equals(FilterKind.COLLAPSE_LITERAL)) {
        found = true;
        // FIXME: specific check for collapse filter
        boolean visible = FilterService.isVisible(filter, dDiagramElement);
        Assert.assertTrue(MessageFormat.format(Messages.elementNotCollapsedPropertly, dDiagramElement.getName()),
            visible);
      }
    }
    return found;
  }

  /**
   * Apply a filter filterName on the diagram (and refresh the diagram)
   * 
   * @param diagram
   * @param filterName
   * @return the FilterDescription with the name filterName
   */
  public static FilterDescription applyFilterOnDiagram(final DDiagram diagram, String filterName) {
    final FilterDescription filterDescription = getFilterDescription(diagram, filterName);
    Assert.assertNotNull(
        MessageFormat.format("Filter \"{0}\" can not be found for diagram \"{1}\"", filterName, EObjectExt.getText(diagram)),
        filterDescription);
    // Add the filter to the activated filters list & Refresh the diagram
    AbstractCommand cmd = new AbstractReadWriteCommand() {
      @Override
      public void run() {
        EList<FilterDescription> activatedFilters = diagram.getActivatedFilters();
        activatedFilters.add(filterDescription);
        // Refreshes the diagram:
        DialectManager.INSTANCE.refresh(diagram, new NullProgressMonitor());
      }
    };
    // Let's perform the job
    TestHelper.getExecutionManager(diagram).execute(cmd);
    return filterDescription;
  }

  /**
   * Get filter with specified name in specified diagram.
   * 
   * @param diagram
   * @param filterName
   * @return
   */
  public static FilterDescription getFilterDescription(final DDiagram diagram, String filterName) {
    DiagramDescription description = diagram.getDescription();
    List<FilterDescription> filters = description.getFilters();

    for (FilterDescription filterDescription : filters) {
      if (filterDescription.getName().equalsIgnoreCase(filterName)) {
        return filterDescription;
      }
    }
    return null;
  }

  /**
   * Check if the elements are correctly filtered (not visible in diagram)
   * 
   * @param diagram
   * @param compositeFilterDescription
   */
  public static void checkFilteredElementsInDiagram(DDiagram diagram,
      CompositeFilterDescription compositeFilterDescription) {
    Map<DiagramElementMapping, String> mappings = getMappingsFromCompositeFilter(compositeFilterDescription);
    EList<DDiagramElement> diagramElements = diagram.getDiagramElements();
    DisplayService displayService = DisplayServiceManager.INSTANCE.getDisplayService();
    String expression = null;

    for (DDiagramElement dDiagramElement : diagramElements) {
      // if an element mapping match the list of mapping filtered, assert that the element is not visible in the diagram
      if (mappings.containsKey(dDiagramElement.getMapping()) && (dDiagramElement.getTarget() != null)) {

        // get the expression used as a semantic precondition if it exists
        expression = mappings.get(dDiagramElement.getMapping());

        if (isValidExpressiontoEvaluate(expression)) {
          // an element mapping match the list of mapping filtered and an additional condition via an expression
          handleCheckWithSemanticConditionOnDDiagramElement(diagram, compositeFilterDescription, displayService,
              expression, dDiagramElement);
        } else {
          // no expression found, handle without additional condition
          handleCheckWithoutSemanticConditionExpression(diagram, compositeFilterDescription, displayService,
              dDiagramElement);
        }
      }
    }
  }

  /**
   * @param compositeFilterDescription
   * @return the map of DiagramElementMapping and an semantic condition expression attached if it exists
   */
  private static Map<DiagramElementMapping, String> getMappingsFromCompositeFilter(
      CompositeFilterDescription compositeFilterDescription) {
    EList<Filter> filters = compositeFilterDescription.getFilters();
    Map<DiagramElementMapping, String> mappings = new HashMap<DiagramElementMapping, String>();
    for (Filter filter : filters) {
      MappingFilter mp = ((MappingFilter) filter);
      for (DiagramElementMapping aMapping : mp.getMappings()) {
        mappings.put(aMapping, mp.getSemanticConditionExpression());
      }
    }
    Assert.assertFalse(MessageFormat.format(Messages.mappingEmpty, compositeFilterDescription.getName()),
        mappings.isEmpty());
    return mappings;
  }

  /**
   * @param diagram
   * @param compositeFilterDescription
   * @param displayService
   * @param expression
   * @param dDiagramElement
   */
  private static void handleCheckWithSemanticConditionOnDDiagramElement(DDiagram diagram,
      CompositeFilterDescription compositeFilterDescription, DisplayService displayService, String expression,
      DDiagramElement dDiagramElement) {
    InterpreterRegistry interpreterRegistry = SiriusPlugin.getDefault().getInterpreterRegistry();
    IInterpreter acceleoInterpreter = interpreterRegistry.getInterpreter(dDiagramElement.getTarget());

    try {
      if (!acceleoInterpreter.evaluateBoolean(dDiagramElement.getTarget(), expression)) {
        // if the precondition is verified, check that the dDiagramElement is not visible
        additionalCheckOnDDiagramElementWithSemanticCondition(diagram, compositeFilterDescription, displayService,
            dDiagramElement);
      }
    } catch (EvaluationException exception_p) {
      // Error in the evaluation of expression
      System.err.println(exception_p.fillInStackTrace());
      Assert.assertTrue(MessageFormat.format(Messages.evaluationExceptionForExpression, expression), false);
    }
  }

  /**
   * @param diagram
   * @param compositeFilterDescription
   * @param displayService
   * @param dDiagramElement
   */
  private static void additionalCheckOnDDiagramElementWithSemanticCondition(DDiagram diagram,
      CompositeFilterDescription compositeFilterDescription, DisplayService displayService,
      DDiagramElement dDiagramElement) {
    boolean displayed = displayService.isDisplayed(diagram, dDiagramElement);

    if (displayed) {
      // specific test for collapse filter because isDisplayed is broken
      checkCollapseFilter(compositeFilterDescription, dDiagramElement);
    } else {
      // default behavior for displayed
      Assert.assertFalse(MessageFormat.format(Messages.elementNotHiddenPropertly, dDiagramElement.getName()),
          displayed);
    }
  }

  /**
   * @param diagram
   * @param compositeFilterDescription
   * @param displayService
   * @param dDiagramElement
   */
  private static void handleCheckWithoutSemanticConditionExpression(DDiagram diagram,
      CompositeFilterDescription compositeFilterDescription, DisplayService displayService,
      DDiagramElement dDiagramElement) {
    // FIXME: check if the isDisplayed is not broken
    boolean displayed = displayService.isDisplayed(diagram, dDiagramElement);
    if (displayed) {
      // specific test for collapse filter if isDisplayed is broken
      checkCollapseFilter(compositeFilterDescription, dDiagramElement);
    } else {
      Assert.assertFalse(MessageFormat.format(Messages.elementNotHiddenPropertly, dDiagramElement.getName()),
          displayed);
    }
  }

  /**
   * Checks if the expression is a valid to be evaluate
   * 
   * @param expression
   * @return true if the expression is not null and not empty
   */
  private static boolean isValidExpressiontoEvaluate(String expression) {
    return (expression != null) && !(expression.trim().equals(ICommonConstants.EMPTY_STRING));
  }

  /**
   * Specific test for Collapse filter due to display.isDisplayed method not working for collapse filter
   * 
   * @param compositeFilterDescription
   * @param dDiagramElement
   */
  private static void checkCollapseFilter(CompositeFilterDescription compositeFilterDescription,
      DDiagramElement dDiagramElement) {
    EList<Filter> filters = compositeFilterDescription.getFilters();
    boolean found = false;

    for (int i = 0; (i < filters.size()) && !found; i++) {
      Filter filter = filters.get(i);
      if (filter.getFilterKind().equals(FilterKind.COLLAPSE_LITERAL)) {
        found = true;
        // FIXME: Specific check for collapse filter
        boolean visible = FilterService.isVisible(filter, dDiagramElement);
        Assert.assertFalse(MessageFormat.format(Messages.elementNotCollapsedPropertly, dDiagramElement.getName()),
            visible);
      }
    }
  }

  /**
   * Remove a filter filterName on the diagram (and refresh the diagram)
   * 
   * @param diagram
   * @param filterName
   * @return the FilterDescription with the name filterName
   */
  public static FilterDescription removeFilterOnDiagram(final DDiagram diagram, String filterName) {
    final FilterDescription filterDescription = getFilterDescription(diagram, filterName);
    Assert.assertNotNull(
        MessageFormat.format("Filter \"{0}\" can not be found for diagram \"{1}\"", filterName, EObjectExt.getText(diagram)),
        filterDescription);
    AbstractCommand cmd = new AbstractReadWriteCommand() {
      @Override
      public void run() {
        EList<FilterDescription> activatedFilters = diagram.getActivatedFilters();
        activatedFilters.remove(filterDescription);
        DialectManager.INSTANCE.refresh(diagram, new NullProgressMonitor());
      }
    };
    // Let's perform the job
    TestHelper.getExecutionManager(diagram).execute(cmd);
    return filterDescription;
  }
}
