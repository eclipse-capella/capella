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
import java.util.stream.Collectors;

import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;

/**
 * Test for EnableFCAndPCFilters Migration Contribution.
 * 
 */
public class EnableFCAndPPFiltersMigrationTest extends AbstractFiltersMigrationTest {

  private static final String MODEL_1_4_X = "activate-filters-1_4_x";
  private static final String MODEL_5_0_0 = "activate-filters-5_0_0";
  private static final String MODEL_5_1_0 = "activate-filters-5_1_0";
  private static final String MODEL_6_0_0 = "activate-filters-6_0_0";

  @Override
  public void test() throws Exception {

    Collection<String> errors = new ArrayList<>();

    assertFilterEnabledAfterMigration(MODEL_1_4_X, errors);
    assertFilterEnabledAfterMigration(MODEL_5_0_0, errors);

    assertFilterNotEnabledAfterMigration(MODEL_5_1_0, errors);
    assertFilterNotEnabledAfterMigration(MODEL_6_0_0, errors);

    assertTrue(errors.stream().collect(Collectors.joining("\n")), errors.isEmpty());
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(MODEL_1_4_X, MODEL_5_0_0, MODEL_5_1_0, MODEL_6_0_0);
  }

  @Override
  public Set<String> getFilterCandidatesNames() {
    return new HashSet<>(Arrays.asList(IMappingNameConstants.HIDE_OVERLAPPED_PHYSICAL_PATHS_ICON,
        IMappingNameConstants.HIDE_OVERLAPPED_PHYSICAL_PATHS_LABEL,
        IMappingNameConstants.HIDE_OVERLAPPED_FUNCTIONAL_CHAINS_ICON,
        IMappingNameConstants.HIDE_OVERLAPPED_FUNCTIONAL_CHAINS_LABEL));
  }

}
