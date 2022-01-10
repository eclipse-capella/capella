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
 *******************************************************************************/
package org.polarsys.capella.test.migration.ju.testcases.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
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
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;
import org.polarsys.capella.test.migration.ju.helpers.MigrationHelper;

/**
 * Test for EnableFCAndPCFilters Migration Contribution.
 * 
 */
public class EnableFCAndPPFiltersMigrationTest extends BasicTestCase {

  private static final String MODEL_1_4_X = "activate-filters-1_4_x";
  private static final String MODEL_5_0_0 = "activate-filters-5_0_0";
  private static final String MODEL_5_1_0 = "activate-filters-5_1_0";
  private static final String MODEL_6_0_0 = "activate-filters-6_0_0";

  private static final Set<String> FILTER_CANDIDATE_NAMES = new HashSet<>(
      Arrays.asList(IMappingNameConstants.HIDE_OVERLAPPED_PHYSICAL_PATHS_ICON,
          IMappingNameConstants.HIDE_OVERLAPPED_PHYSICAL_PATHS_LABEL,
          IMappingNameConstants.HIDE_OVERLAPPED_FUNCTIONAL_CHAINS_ICON,
          IMappingNameConstants.HIDE_OVERLAPPED_FUNCTIONAL_CHAINS_LABEL));

  private static final String ERROR_MSG = "Assertion error for model {0} diagram {1} and filter {2}";

  BiPredicate<List<FilterDescription>, FilterDescription> isFilterEnabledPredicate = //
      (activatedFilters, filterCandidate) -> activatedFilters.contains(filterCandidate);

  BiPredicate<List<FilterDescription>, FilterDescription> isFilterNotEnabledPredicate = //
      (activatedFilters, filterCandidate) -> !activatedFilters.contains(filterCandidate);

  @Override
  public void test() throws Exception {

    Collection<String> errors = new ArrayList<>();

    assertFilterEnabledAfterMigration(MODEL_1_4_X, errors);
    assertFilterEnabledAfterMigration(MODEL_5_0_0, errors);

    assertFilterNotEnabledAfterMigration(MODEL_5_1_0, errors);
    assertFilterNotEnabledAfterMigration(MODEL_6_0_0, errors);

    assertTrue(errors.stream().collect(Collectors.joining("\n")), errors.isEmpty());
  }

  private void assertFilterEnabledAfterMigration(String modelName, Collection<String> errors) {
    assertFilterPredicateAfterMigration(modelName, isFilterEnabledPredicate, errors);
  }

  private void assertFilterNotEnabledAfterMigration(String modelName, Collection<String> errors) {
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
        .filter(filter -> FILTER_CANDIDATE_NAMES.contains(filter.getName())) //
        .collect(Collectors.toList());
    return expectedFilters;
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(MODEL_1_4_X, MODEL_5_0_0, MODEL_5_1_0, MODEL_6_0_0);
  }

}
