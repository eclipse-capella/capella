/*******************************************************************************
 * Copyright (c) 2022 OBEO.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Obeo - 2303 Add computed transitions in M&S diagrams
 *    Thales - EnableFCAndPPFiltersMigrationTest.java initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.migration.ju.testcases.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;

/**
 * Test for ActivateDiagramFilters Migration Contribution. Copied from EnableFCAndPPFiltersMigrationTest.java.
 * 
 */
public class EnableMSMFiltersMigrationTest extends AbstractFiltersMigrationTest {

  private static final String MODEL_5_1_0 = "activate-MSM-filters-5_1_0";
  private static final String MODEL_6_0_0 = "activate-MSM-filters-6_0_0";

  @Override
  public void test() throws Exception {

    Collection<String> errors = new ArrayList<>();

    assertFilterEnabledAfterMigration(MODEL_5_1_0, errors);
    assertFilterEnabledAfterMigration(MODEL_6_0_0, errors);

    assertTrue(errors.stream().collect(Collectors.joining("\n")), errors.isEmpty());
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(MODEL_5_1_0, MODEL_6_0_0);
  }

  @Override
  public Set<String> getFilterCandidatesNames() {
    return new HashSet<>(Arrays.asList(IFilterNameConstants.FILTER_MSM_HIDECOMPUTEDTRANSITIONS_ID));
  }

}
