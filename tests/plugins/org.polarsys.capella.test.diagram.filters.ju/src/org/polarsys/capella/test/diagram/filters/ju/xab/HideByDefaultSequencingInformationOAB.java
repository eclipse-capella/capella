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
package org.polarsys.capella.test.diagram.filters.ju.xab;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DefaultActivatedFilterTestCase;

public class HideByDefaultSequencingInformationOAB extends DefaultActivatedFilterTestCase {

  private final String OA__SEQUENCE_LINK_INTERACTION_1_ID = "529b09a3-cdc2-455d-b4d1-7440e6ef09bd";
  private final String OA__SEQUENCE_LINK_INTERACTION_2_ID = "ea593610-e334-4e12-95c5-2d143742a81d";

  private final String OA__SEQUENCE_LINK_INTERACTION_3_ID = "22e60a51-ae37-4624-ac6a-e9ed9a21917c";
  private final String OA__SEQUENCE_LINK_INTERACTION_4_ID = "21881954-b961-43ec-83cc-510aa1f8c143";
  private final String OA__SEQUENCE_LINK_INTERACTION_5_ID = "2a87da69-6ccf-4669-872a-dc9681de9aa1";
  private final String OA__SEQUENCE_LINK_INTERACTION_6_ID = "0c92bfe1-c1e0-4ec5-964a-f5e210406bab";
  private final String OA__SEQUENCE_LINK_INTERACTION_7_ID = "dcbc1784-5782-4f96-a07c-fba0f720628e";

  private final String OA_CONTROL_NODE_1_ID = "7cf52ce1-60ed-402b-b3db-99c4595b992d";
  private final String OA_CONTROL_NODE_2_ID = "b0efb776-44f5-427d-8cc3-fc29b7fdde9d";
  private final String OA_CONTROL_NODE_3_ID = "ea0a7327-e3a8-4555-b2c8-ee1da65dcc3b";

  @Override
  protected String getTestProjectName() {
    return "HideSimplifiedLinksFilter";
  }

  @Override
  protected String getDiagramName() {
    return "[OAB] Sequencing Information Filter Test";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_FCD_HIDE_SEQUENCING_INFORMATION;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(OA__SEQUENCE_LINK_INTERACTION_1_ID, OA__SEQUENCE_LINK_INTERACTION_2_ID,
        OA__SEQUENCE_LINK_INTERACTION_3_ID, OA__SEQUENCE_LINK_INTERACTION_4_ID, OA__SEQUENCE_LINK_INTERACTION_5_ID,
        OA__SEQUENCE_LINK_INTERACTION_6_ID, OA__SEQUENCE_LINK_INTERACTION_7_ID, OA_CONTROL_NODE_1_ID,
        OA_CONTROL_NODE_2_ID, OA_CONTROL_NODE_3_ID);
  }
}
