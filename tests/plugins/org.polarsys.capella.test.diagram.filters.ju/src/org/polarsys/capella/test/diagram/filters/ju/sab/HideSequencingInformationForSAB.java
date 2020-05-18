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
package org.polarsys.capella.test.diagram.filters.ju.sab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DefaultActivatedFilterTestCase;

public class HideSequencingInformationForSAB extends DefaultActivatedFilterTestCase {

  private final String SEQUENCE_LINK_1_ID = "02e001d5-653e-444f-8be2-5df814ff96df";
  private final String SEQUENCE_LINK_2_ID = "90a934ac-0e36-436b-9e67-74c47d1a417d";
  private final String SEQUENCE_LINK_3_ID = "dfaeb0f6-f3bc-490b-b853-92ebd06a1a28";
  private final String SEQUENCE_LINK_4_ID = "6b9e3733-e299-40b9-889d-1e1316b88454";
  private final String CONTROL_NODE_1_ID = "198dd56f-c627-4775-b570-30850eb4eca2";
  private final String CONTROL_NODE_2_ID = "deb06880-4643-4fda-9086-5d0dd17b99b7";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[SAB] System";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_SAB_HIDE_SEQUENCING_INFORMATION;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    List<String> list = new ArrayList<String>(Arrays.asList(SEQUENCE_LINK_1_ID, SEQUENCE_LINK_2_ID, SEQUENCE_LINK_3_ID,
        SEQUENCE_LINK_4_ID, CONTROL_NODE_1_ID, CONTROL_NODE_2_ID));

    return list;

  }

}
