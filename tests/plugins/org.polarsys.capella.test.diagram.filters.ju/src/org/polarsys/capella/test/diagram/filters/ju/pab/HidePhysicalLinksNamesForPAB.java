/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.filters.ju.pab;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.LabelFilterTestCase;

public class HidePhysicalLinksNamesForPAB extends LabelFilterTestCase {

  protected final String PHYSICAL_LINK_1_ID = "c2cb6453-5612-4546-9466-fbbf6051fedf";
  protected final String PHYSICAL_LINK_2_ID = "ef631259-8baf-436c-a1e7-655a5b0e958b";
  protected final String PHYSICAL_LINK_3_ID = "e9de7e34-ccf7-4b5f-a18b-6cce77799428";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[PAB] Physical System";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_PAB_HIDE_PHYSICAL_LINKS_NAMES;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { PHYSICAL_LINK_1_ID, PHYSICAL_LINK_2_ID, PHYSICAL_LINK_3_ID });
  }

  @Override
  protected List<String> getExpectedElementLabels() {
    return Arrays.asList(new String[] { "; ;", "; ;", "; ;" });
  }

}
