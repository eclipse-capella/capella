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
package org.polarsys.capella.test.diagram.filters.ju.pab;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.LabelFilterTestCase;

public class ShowExchangeItemsOnFunctionalExchangesForPAB extends LabelFilterTestCase {

  private final String FUNCTIONAL_EXCHANGE_1_ID = "a875d373-e646-41fb-84d3-e4d331cf6ffd";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[PAB] Physical System";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_PAB_SHOW_EXCHANGE_ITEMS_ON_FUNCTIONAL_EXCHANGES_ID;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { FUNCTIONAL_EXCHANGE_1_ID });
  }

  @Override
  protected List<String> getExpectedElementLabels() {
    return Arrays.asList(new String[] { ";[ExchangeItem 1];" });
  }

}
