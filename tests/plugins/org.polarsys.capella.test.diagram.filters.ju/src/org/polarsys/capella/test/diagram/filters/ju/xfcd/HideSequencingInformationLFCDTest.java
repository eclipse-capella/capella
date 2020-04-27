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

public class HideSequencingInformationLFCDTest extends DiagramObjectFilterTestCase {

  private final String SEQUENCE_LINK_FUNCTION_4_FUNCTION_5_ID = "c42e9b79-2c2b-483a-b020-339bf463f5e1";
  private final String SEQUENCE_LINK_FUNCTION_5_FUNCTION_6_ID = "543daa71-ef89-4e3a-8904-88563e832d99";
  private final String SEQUENCE_LINK_FUNCTION_5_AND_NODE_ID = "e589d83d-05fa-40fe-be4f-2e9a21db3977";
  private final String SEQUENCE_LINK_FUNCTION_1_FUNCTION_2_ID = "203057c4-3278-4c14-b69b-b4d6026b2311";
  private final String SEQUENCE_LINK_AND_NODE_FUNCTIONAL_CHAIN_REFERENCE_1_ID = "d5229128-5a0d-481f-867e-9a454bc8bf38";
  private final String AND_NODE_ID = "47fc3533-21af-42d2-a054-db78e62e5fd3";
  
  @Override
  protected String getTestProjectName() {
    return "HideSimplifiedLinksFilter";
  }

  @Override
  protected String getDiagramName() {
    return "[LFCD] Filters Test Diagram";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_FCD_HIDE_SEQUENCING_INFORMATION;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] {
        SEQUENCE_LINK_FUNCTION_1_FUNCTION_2_ID,
        SEQUENCE_LINK_FUNCTION_4_FUNCTION_5_ID,
        SEQUENCE_LINK_FUNCTION_5_FUNCTION_6_ID,
        SEQUENCE_LINK_FUNCTION_5_AND_NODE_ID,
        SEQUENCE_LINK_AND_NODE_FUNCTIONAL_CHAIN_REFERENCE_1_ID,
        AND_NODE_ID});
  }
}
