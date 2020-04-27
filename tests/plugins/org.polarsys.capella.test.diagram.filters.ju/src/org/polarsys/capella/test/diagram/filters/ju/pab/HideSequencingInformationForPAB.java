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
package org.polarsys.capella.test.diagram.filters.ju.pab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;

public class HideSequencingInformationForPAB extends FiltersForPAB {

  private final String SEQUENCE_LINK_1_ID = "1def59bc-c0c9-4878-b3b0-9cf85e4dd989";
  private final String SEQUENCE_LINK_2_ID = "57af930e-02c2-4f33-bf33-35e40fd166ac";
  private final String SEQUENCE_LINK_3_ID = "9175a67c-f00f-4e18-ac09-ce8f5f2dcaa2";
  private final String SEQUENCE_LINK_4_ID = "0c2fc316-3539-4c88-afe4-b339cd674505";
  private final String CONTROL_NODE_1_ID = "d07b12a1-65ea-42d1-8379-c0bbe6924780";
  private final String CONTROL_NODE_2_ID = "b701337f-a1ea-426a-8978-14fc5b4da4e6";

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
    return IFilterNameConstants.FILTER_PAB_HIDE_SEQUENCING_INFORMATION;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    List<String> list = new ArrayList<String>(Arrays.asList(SEQUENCE_LINK_1_ID, SEQUENCE_LINK_2_ID, SEQUENCE_LINK_3_ID,
        SEQUENCE_LINK_4_ID, CONTROL_NODE_1_ID, CONTROL_NODE_2_ID));

    return list;

  }

}
