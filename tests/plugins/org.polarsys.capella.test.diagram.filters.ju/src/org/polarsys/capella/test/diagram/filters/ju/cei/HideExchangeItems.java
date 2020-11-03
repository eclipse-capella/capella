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
package org.polarsys.capella.test.diagram.filters.ju.cei;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public class HideExchangeItems extends DiagramObjectFilterTestCase {

  private final String EXCHANGE_ITEM_1_ID = "2ebb2463-9286-4900-8ed2-f380dda3e19c";
  private final String EXCHANGE_ITEM_2_ID = "e0e2a158-efbe-40a9-ab21-df5a3d8591c7";
  private final String EXCHANGE_ITEM_3_ID = "695bbde6-942b-4839-b7fc-371c6398742a";

  // Communication Links are also hidden by this filter
  // Why?
  private final String PRODUCE_COMMUNICATION_LINK_ID = "447b09a5-79f5-473f-9e4e-3de8baab524d";
  private final String CONSUME_COMMUNICATION_LINK_ID = "38e97714-4a8c-4d9f-b21f-07e3b5ccb7b2";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel"; //$NON-NLS-1$
  }

  @Override
  protected String getDiagramName() {
    return "Contextual Component External Interfaces Test Diagram"; //$NON-NLS-1$
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_CEI_HIDE_EXCHANGE_ITEMS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { EXCHANGE_ITEM_1_ID, EXCHANGE_ITEM_2_ID, EXCHANGE_ITEM_3_ID,
        PRODUCE_COMMUNICATION_LINK_ID, CONSUME_COMMUNICATION_LINK_ID });
  }
}
