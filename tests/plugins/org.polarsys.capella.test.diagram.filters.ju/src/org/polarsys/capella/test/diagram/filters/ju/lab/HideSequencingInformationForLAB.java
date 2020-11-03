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
package org.polarsys.capella.test.diagram.filters.ju.lab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DefaultActivatedFilterTestCase;

public class HideSequencingInformationForLAB extends DefaultActivatedFilterTestCase {

  private final String SEQUENCE_LINK_1_ID = "91d399cc-a035-47a9-b8f2-6fef9270eed6";
  private final String SEQUENCE_LINK_2_ID = "40e9195f-3981-411f-9b37-d8fdb7270c0b";
  private final String SEQUENCE_LINK_3_ID = "13fe53bc-5539-417e-9d1a-d782dcad71a9";
  private final String SEQUENCE_LINK_4_ID = "dfd6c63b-bcc8-403a-8af4-a3f290e59806";
  private final String CONTROL_NODE_1_ID = "84070ad7-d40c-4017-9f73-0a759983ab6a";
  private final String CONTROL_NODE_2_ID = "147d79d3-87ec-4a38-a415-826de26f3397";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[LAB] Logical System";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_LAB_HIDE_SEQUENCING_INFORMATION;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    List<String> list = new ArrayList<String>(Arrays.asList(SEQUENCE_LINK_1_ID, SEQUENCE_LINK_2_ID, SEQUENCE_LINK_3_ID,
        SEQUENCE_LINK_4_ID, CONTROL_NODE_1_ID, CONTROL_NODE_2_ID));

    return list;
  }

}
