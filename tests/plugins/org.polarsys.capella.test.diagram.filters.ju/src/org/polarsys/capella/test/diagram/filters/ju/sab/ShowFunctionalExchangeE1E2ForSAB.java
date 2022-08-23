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
package org.polarsys.capella.test.diagram.filters.ju.sab;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.LabelFilterTestCase;

public class ShowFunctionalExchangeE1E2ForSAB extends LabelFilterTestCase {

  private final String FUNCTIONAL_EXCHANGE_5_ID = "5e91eb7d-ba1f-4f99-ac9b-8d07a3634c57";
  private final String FUNCTIONAL_EXCHANGE_6_ID = "aecd20ce-8d4d-4bda-b91f-6dbd6a261c09";

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
    return IFilterNameConstants.FILTER_SAB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_ON_FUNCTIONAL_EXCHANGES_ID;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { FUNCTIONAL_EXCHANGE_5_ID, FUNCTIONAL_EXCHANGE_6_ID });
  }

  @Override
  protected List<String> getExpectedElementLabels() {
    return Arrays.asList(
        new String[] { ";FunctionalExchange 5 [ExchangeItem 1, ExchangeItem 3];", ";FunctionalExchange 6 [];" });
  }

}
