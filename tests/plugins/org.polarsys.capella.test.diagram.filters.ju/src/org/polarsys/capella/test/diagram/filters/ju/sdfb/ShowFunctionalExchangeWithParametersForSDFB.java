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
package org.polarsys.capella.test.diagram.filters.ju.sdfb;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.LabelFilterTestCase;

public class ShowFunctionalExchangeWithParametersForSDFB extends LabelFilterTestCase {

  private final String FUNCTIONAL_EXCHANGE_2_ID = "6d3330df-ba65-4be5-a78b-2f2ce54cbc61";
  private final String FUNCTIONAL_EXCHANGE_3_ID = "1f957ec0-8f03-4774-89ad-769fa7778e5d";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[SDFB] Root System Function";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_SDFB_SHOW_FUNCTIONAL_EXCHANGES_EXCHANGEITEMS_PARAMETERS_ID;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { FUNCTIONAL_EXCHANGE_2_ID, FUNCTIONAL_EXCHANGE_3_ID });
  }

  @Override
  protected List<String> getExpectedElementLabels() {
    return Arrays.asList(
        new String[] { ";FunctionalExchange 2 [ExchangeItem 1(), ExchangeItem 3()];", ";FunctionalExchange 3 [];" });
  }

}
