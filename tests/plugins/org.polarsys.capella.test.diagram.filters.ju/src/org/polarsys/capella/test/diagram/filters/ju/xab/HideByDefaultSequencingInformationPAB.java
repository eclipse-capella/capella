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
package org.polarsys.capella.test.diagram.filters.ju.xab;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DefaultActivatedFilterTestCase;

public class HideByDefaultSequencingInformationPAB extends DefaultActivatedFilterTestCase {

  private final String PA__SEQUENCE_LINK_EXCHANGE_1 = "eaaaa6c6-8675-4414-9448-cad625f9be64";
  private final String PA__SEQUENCE_LINK_EXCHANGE_2 = "c18fd509-6beb-45bf-92ca-815acf3eb612";

  private final String PA__SEQUENCE_LINK_EXCHANGE_3 = "b51b147d-8184-4bdf-ab39-db80e7c53c01";
  private final String PA__SEQUENCE_LINK_EXCHANGE_4 = "3d157c20-302b-4c70-9a9e-009d17dd8bd1";
  private final String PA__SEQUENCE_LINK_EXCHANGE_5 = "0bf0b94d-38c5-4082-b46b-b69a0a626c38";
  private final String PA__SEQUENCE_LINK_EXCHANGE_6 = "989d520f-6576-4326-826b-508466f389a9";
  private final String PA__SEQUENCE_LINK_EXCHANGE_7 = "f8b2c4ce-8094-47d1-8f8d-be1cf8ada5a6";

  private final String PA__CONTROL_NODE_1 = "fbd4c584-4f68-4016-84a2-67c7211ae9c1";
  private final String PA__CONTROL_NODE_2 = "2cb81afe-383e-49d9-bb15-475b1a1201a5";
  private final String PA__CONTROL_NODE_3 = "e5effbf3-78eb-49b4-a7fd-40d12e44a90a";

  @Override
  protected String getTestProjectName() {
    return "HideSimplifiedLinksFilter";
  }

  @Override
  protected String getDiagramName() {
    return "[PAB] Sequencing Information Filter Test";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_FCD_HIDE_SEQUENCING_INFORMATION;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(PA__SEQUENCE_LINK_EXCHANGE_1, PA__SEQUENCE_LINK_EXCHANGE_2, PA__SEQUENCE_LINK_EXCHANGE_3,
        PA__SEQUENCE_LINK_EXCHANGE_4, PA__SEQUENCE_LINK_EXCHANGE_5, PA__SEQUENCE_LINK_EXCHANGE_6,
        PA__SEQUENCE_LINK_EXCHANGE_7, PA__CONTROL_NODE_1, PA__CONTROL_NODE_2, PA__CONTROL_NODE_3);
  }
}
