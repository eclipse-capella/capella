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

public class HideSequencingInformationOPDTest extends DiagramObjectFilterTestCase {

  private final String SEQUENCE_LINK_FUNCTION_1_FUNCTION_2_ID = "529b09a3-cdc2-455d-b4d1-7440e6ef09bd";
  private final String SEQUENCE_LINK_FUNCTION_2_FUNCTION_3_ID = "ea593610-e334-4e12-95c5-2d143742a81d";
  private final String SEQUENCE_LINK_FUNCTION_1_AND_NODE_1_ID = "22e60a51-ae37-4624-ac6a-e9ed9a21917c";
  private final String SEQUENCE_LINK_FUNCTION_2_AND_NODE_1_ID = "0c92bfe1-c1e0-4ec5-964a-f5e210406bab";
  private final String SEQUENCE_LINK_FUNCTION_3_OR_NODE_1_ID = "dcbc1784-5782-4f96-a07c-fba0f720628e";
  private final String SEQUENCE_LINK_1_AND_NODE_1_AND_NODE_2_ID = "21881954-b961-43ec-83cc-510aa1f8c143";
  private final String SEQUENCE_LINK_2_AND_NODE_1_AND_NODE_2_ID = "2a87da69-6ccf-4669-872a-dc9681de9aa1";
  private final String SEQUENCE_LINK_AND_NODE_2_FUNCTION_4_ID = "628af29a-5617-420d-b18b-c48cc67c39f9";
  private final String SEQUENCE_LINK_OR_NODE_FUNCTIONAL_CHAIN_3_ID = "fc1e29b1-6e14-4e34-81ce-5967512febb6";
  private final String CONTROL_NODE_AND_1_ID = "b0efb776-44f5-427d-8cc3-fc29b7fdde9d";
  private final String CONTROL_NODE_AND_2_ID = "7cf52ce1-60ed-402b-b3db-99c4595b992d";
  private final String CONTROL_NODE_OR_ID = "ea0a7327-e3a8-4555-b2c8-ee1da65dcc3b";
  private final String HIDDEN_SEQUENCE_LINK_OR_NODE_FUNCTION_6_ID = "fc1e29b1-6e14-4e34-81ce-5967512febb6";

  @Override
  protected String getTestProjectName() {
    return "HideSimplifiedLinksFilter";
  }

  @Override
  protected String getDiagramName() {
    return "[OPD] Filters Test Diagram";
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
        SEQUENCE_LINK_OR_NODE_FUNCTIONAL_CHAIN_3_ID, CONTROL_NODE_AND_1_ID, CONTROL_NODE_AND_2_ID, CONTROL_NODE_OR_ID,
        HIDDEN_SEQUENCE_LINK_OR_NODE_FUNCTION_6_ID });
  }

}
