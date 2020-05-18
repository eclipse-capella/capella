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
package org.polarsys.capella.test.diagram.filters.ju.xfcd;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public class HideSequencingInformationSFCDTest extends DiagramObjectFilterTestCase {

  private final String SEQUENCE_LINK_FUNCTION_1_FUNCTION_2_ID = "c24d372e-c3c9-41cf-a569-e774ceff12fe";
  private final String SEQUENCE_LINK_FUNCTION_2_FUNCTION_3_ID = "648ae687-9b65-42c2-90f4-3e7bb072899c";
  private final String SEQUENCE_LINK_FUNCTION_1_AND_NODE_1_ID = "61501e99-2a07-47b7-9d2b-01620fcd20b0";
  private final String SEQUENCE_LINK_FUNCTION_2_AND_NODE_1_ID = "4b52305a-8a50-4fdf-a7e7-dd129a8f59bc";
  private final String SEQUENCE_LINK_FUNCTION_3_OR_NODE_1_ID = "150882d0-ac9e-4ba7-88c3-8d91769a6cc7";
  private final String SEQUENCE_LINK_1_AND_NODE_1_AND_NODE_2_ID = "f5414241-06e9-4f3f-baea-c18f43b1037b";
  private final String SEQUENCE_LINK_2_AND_NODE_1_AND_NODE_2_ID = "cd9d6865-8175-44c2-bfde-4ce42cec36ce";
  private final String SEQUENCE_LINK_AND_NODE_2_FUNCTION_4_ID = "3461cbe6-4234-4db9-a26d-5db1451ec9ac";
  private final String SEQUENCE_LINK_OR_NODE_FUNCTIONAL_CHAIN_3_ID = "f35643eb-538c-4256-8423-831e03b2883d";
  private final String CONTROL_NODE_AND_1_ID = "2dacec08-33a1-4e99-8fa7-5316c4e00e93";
  private final String CONTROL_NODE_AND_2_ID = "fcef1bf3-0ec6-41e9-b4e7-0177c8ab5b00";
  private final String CONTROL_NODE_OR_ID = "f89df4eb-db01-427e-b544-0cc2ce53743c";

  @Override
  protected String getTestProjectName() {
    return "HideSimplifiedLinksFilter";
  }

  @Override
  protected String getDiagramName() {
    return "Functional Chain Description Filters Test";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_FCD_HIDE_SEQUENCING_INFORMATION;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { SEQUENCE_LINK_FUNCTION_1_FUNCTION_2_ID, SEQUENCE_LINK_FUNCTION_2_FUNCTION_3_ID,
        SEQUENCE_LINK_FUNCTION_1_AND_NODE_1_ID, SEQUENCE_LINK_FUNCTION_2_AND_NODE_1_ID,
        SEQUENCE_LINK_FUNCTION_3_OR_NODE_1_ID, SEQUENCE_LINK_1_AND_NODE_1_AND_NODE_2_ID,
        SEQUENCE_LINK_2_AND_NODE_1_AND_NODE_2_ID, SEQUENCE_LINK_AND_NODE_2_FUNCTION_4_ID,
        SEQUENCE_LINK_OR_NODE_FUNCTIONAL_CHAIN_3_ID, CONTROL_NODE_AND_1_ID, CONTROL_NODE_AND_2_ID, CONTROL_NODE_OR_ID});
  }
}
