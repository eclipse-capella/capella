/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.filters.ju.cdb;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.LabelFilterTestCase;

public class HideRoleLabels extends LabelFilterTestCase {

  private final String ALLOCATION_ID = "50a629b0-6c6c-4529-85a0-b20f00045154";
  private final String COMPOSITION_ID = "7ed898ef-bbff-4623-8498-49df17c8d8ef";
  private final String AGGREGATION_ID = "24ab00f5-fc14-4c6d-bf8d-6bc5e9a085b6";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "Class Diagram Blank Filters Test Diagram";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_CDB_HIDE_ROLE_LABELS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { ALLOCATION_ID, COMPOSITION_ID, AGGREGATION_ID });
  }

  @Override
  protected List<String> getExpectedElementLabels() {
    return Arrays.asList(new String[] { "; ;", "; ;", "; ;" });
  }
}
