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

public class HideByDefaultSequencingInformationSAB extends DefaultActivatedFilterTestCase {

  private final String SA__SEQUENCE_LINK_EXCHANGE_1 = "c24d372e-c3c9-41cf-a569-e774ceff12fe";
  private final String SA__SEQUENCE_LINK_EXCHANGE_2 = "648ae687-9b65-42c2-90f4-3e7bb072899c";
  private final String SA__SEQUENCE_LINK_FUNCTION_1_AND_NODE_1 = "61501e99-2a07-47b7-9d2b-01620fcd20b0";
  private final String SA__SEQUENCE_LINK_FUNCTION_2_AND_NODE_1 = "4b52305a-8a50-4fdf-a7e7-dd129a8f59bc";
  private final String SA__SEQUENCE_LINK_FUNCTION_3_OR_NODE_1 = "150882d0-ac9e-4ba7-88c3-8d91769a6cc7";
  private final String SA__SEQUENCE_LINK_1_AND_NODE_1_AND_NODE_2 = "cd9d6865-8175-44c2-bfde-4ce42cec36ce";
  private final String SA__SEQUENCE_LINK_2_AND_NODE_1_AND_NODE_2 = "f5414241-06e9-4f3f-baea-c18f43b1037b";
  private final String SA__AND_NODE_1 = "2dacec08-33a1-4e99-8fa7-5316c4e00e93";
  private final String SA__AND_NODE_2 = "fcef1bf3-0ec6-41e9-b4e7-0177c8ab5b00";
  private final String SA__OR_NODE_1 = "f89df4eb-db01-427e-b544-0cc2ce53743c";
  
  @Override
  protected String getTestProjectName() {
    return "HideSimplifiedLinksFilter";
  }

  @Override
  protected String getDiagramName() {
    return "[SAB] Sequencing Information Filter Test";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_FCD_HIDE_SEQUENCING_INFORMATION;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(
        new String[] { 
            SA__SEQUENCE_LINK_EXCHANGE_1,
            SA__SEQUENCE_LINK_EXCHANGE_2,
            SA__SEQUENCE_LINK_FUNCTION_1_AND_NODE_1,
            SA__SEQUENCE_LINK_FUNCTION_2_AND_NODE_1,
            SA__SEQUENCE_LINK_FUNCTION_3_OR_NODE_1,
            SA__SEQUENCE_LINK_1_AND_NODE_1_AND_NODE_2,
            SA__SEQUENCE_LINK_2_AND_NODE_1_AND_NODE_2,
            SA__AND_NODE_1,
            SA__AND_NODE_2,
            SA__OR_NODE_1
        });
    }
 }

