/*******************************************************************************
 * Copyright (c) 2021, 2022 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *    Obeo - 2303 Add computed transitions in M&S diagrams
 *******************************************************************************/
package org.polarsys.capella.test.migration.ju.testcases.basic;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IProject;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;
import org.polarsys.capella.test.migration.ju.helpers.MigrationHelper;

/**
 * Abstract Test for Filters Migration Contribution.
 * 
 */
public abstract class AbstractFiltersMigrationTest extends BasicTestCase {

  private static final String ERROR_MSG = "Assertion error for model {0} diagram {1} and filter {2}";

  BiPredicate<List<FilterDescription>, FilterDescription> isFilterEnabledPredicate = //
      (activatedFilters, filterCandidate) -> activatedFilters.contains(filterCandidate);

  BiPredicate<List<FilterDescription>, FilterDescription> isFilterNotEnabledPredicate = //
      (activatedFilters, filterCandidate) -> !activatedFilters.contains(filterCandidate);

  protected void assertFilterEnabledAfterMigration(String modelName, Collection<String> errors) {
    assertFilterPredicateAfterMigration(modelName, isFilterEnabledPredicate, errors);
  }

  protected void assertFilterNotEnabledAfterMigration(String modelName, Collection<String> errors) {
    assertFilterPredicateAfterMigration(modelName, isFilterNotEnabledPredicate, errors);
  }

  private void assertFilterPredicateAfterMigration(String modelName,
      BiPredicate<List<FilterDescription>, FilterDescription> filterPredicate, Collection<String> errors) {

    IProject project = IResourceHelpers.getEclipseProjectInWorkspace(modelName);

    MigrationHelper.migrateProject(project);

    Session session = getSessionForTestModel(modelName);
    assertNotNull(session);

    Collection<DRepresentation> allRepresentations = DialectManager.INSTANCE.getAllRepresentations(session);

    for (DRepresentation representation : allRepresentations) {
      if (representation instanceof DSemanticDiagram) {
        DSemanticDiagram diagram = (DSemanticDiagram) representation;

        List<FilterDescription> expectedFilters = getExpectedFilters(diagram);
        List<FilterDescription> activatedFilters = diagram.getActivatedFilters();

        for (FilterDescription expectedFilter : expectedFilters) {

          if (!filterPredicate.test(activatedFilters, expectedFilter)) {
            String[] errorArgs = { modelName, diagram.getName(), expectedFilter.getName() };
            String errorMsg = NLS.bind(ERROR_MSG, errorArgs);
            errors.add(errorMsg);
          }
        }
      }
    }

    GuiActions.closeSession(session);
  }

  private List<FilterDescription> getExpectedFilters(DSemanticDiagram diagram) {
    DiagramDescription description = diagram.getDescription();
    List<FilterDescription> expectedFilters = description.getFilters() //
        .stream() //
        .filter(filter -> getFilterCandidatesNames().contains(filter.getName())) //
        .collect(Collectors.toList());
    return expectedFilters;
  }

  public abstract Set<String> getFilterCandidatesNames();
}
