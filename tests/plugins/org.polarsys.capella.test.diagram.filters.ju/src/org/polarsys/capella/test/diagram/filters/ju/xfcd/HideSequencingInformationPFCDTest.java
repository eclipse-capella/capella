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

public class HideSequencingInformationPFCDTest extends DiagramObjectFilterTestCase {

  private final String SEQUENCE_LINK_FUNCTION_1_FUNCTION_2_ID = "eaaaa6c6-8675-4414-9448-cad625f9be64";
  private final String SEQUENCE_LINK_FUNCTION_2_FUNCTION_3_ID = "c18fd509-6beb-45bf-92ca-815acf3eb612";
  private final String SEQUENCE_LINK_FUNCTION_1_AND_NODE_1_ID = "989d520f-6576-4326-826b-508466f389a9";
  private final String SEQUENCE_LINK_FUNCTION_2_AND_NODE_1_ID = "b51b147d-8184-4bdf-ab39-db80e7c53c01";
  private final String SEQUENCE_LINK_FUNCTION_3_OR_NODE_1_ID = "f8b2c4ce-8094-47d1-8f8d-be1cf8ada5a6";
  private final String SEQUENCE_LINK_1_AND_NODE_1_AND_NODE_2_ID = "0bf0b94d-38c5-4082-b46b-b69a0a626c38";
  private final String SEQUENCE_LINK_2_AND_NODE_1_AND_NODE_2_ID = "3d157c20-302b-4c70-9a9e-009d17dd8bd1";
  private final String SEQUENCE_LINK_AND_NODE_2_FUNCTION_4_ID = "bb069c34-9857-49ca-bfbf-841cbe3edd0c";
  private final String SEQUENCE_LINK_OR_NODE_FUNCTIONAL_CHAIN_3_ID = "ea167db4-79d2-4183-b295-313d00dfd63b";
  private final String CONTROL_NODE_AND_1_ID = "fbd4c584-4f68-4016-84a2-67c7211ae9c1";
  private final String CONTROL_NODE_AND_2_ID = "e5effbf3-78eb-49b4-a7fd-40d12e44a90a";
  private final String CONTROL_NODE_OR_ID = "2cb81afe-383e-49d9-bb15-475b1a1201a5";

  @Override
  protected String getTestProjectName() {
    return "HideSimplifiedLinksFilter";
  }

  @Override
  protected String getDiagramName() {
    return "[PFCD] Filters Test Diagram";
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
