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
package org.polarsys.capella.test.diagram.filters.ju.oab;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.LabelFilterTestCase;

public class ShowEI1EI2WithParametersForOAB extends LabelFilterTestCase {

  private final String INTERACTION_2_ID = "af2a78bd-81f7-44cd-b9f9-2d0a2bdbc701";
  private final String INTERACTION_4_ID = "3e7ee01c-9a6d-4f79-827d-7fb2cd14ea64";
  private final String INTERACTION_5_ID = "9a065225-bfae-4c36-97cc-82a9b5326663";
  private final String INTERACTION_6_ID = "f2be3e58-70cc-406a-b936-0ffa8fc71309";
  private final String INTERACTION_7_ID = "874db564-12de-44a8-9420-de5126a28192";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[OAB] Operational Architecture Blank";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_OAB_SHOW_EXCHANGEITEMS_PARAMETERS_ON_INTERACTIONS_ID;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(
        new String[] { INTERACTION_2_ID, INTERACTION_4_ID, INTERACTION_5_ID, INTERACTION_6_ID, INTERACTION_7_ID });
  }

  @Override
  protected List<String> getExpectedElementLabels() {
    return Arrays
        .asList(new String[] { ";DEdge;", ";[ExchangeItem 3()];", ";DEdge;", ";[ExchangeItem 1()];", ";DEdge;" });
  }

}
