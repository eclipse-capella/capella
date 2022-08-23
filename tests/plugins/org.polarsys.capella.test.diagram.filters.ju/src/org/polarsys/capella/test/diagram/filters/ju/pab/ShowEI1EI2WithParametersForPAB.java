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
package org.polarsys.capella.test.diagram.filters.ju.pab;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.LabelFilterTestCase;

public class ShowEI1EI2WithParametersForPAB extends LabelFilterTestCase {

  private final String FUNCTIONAL_EXCHANGE_6_ID = "1b2c20b1-8c60-48c6-8535-564b9c889897";
  private final String FUNCTIONAL_EXCHANGE_7_ID = "25f41c03-6ae6-44af-966c-bb831ba9f6e0";

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
    return IFilterNameConstants.FILTER_PAB_SHOW_EXCHANGEITEMS_PARAMETERS_ON_FUNCTIONAL_EXCHANGES_ID;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { FUNCTIONAL_EXCHANGE_6_ID, FUNCTIONAL_EXCHANGE_7_ID });
  }

  @Override
  protected List<String> getExpectedElementLabels() {
    return Arrays.asList(
        new String[] {
            ";[ExchangeItem 1(ExchangeItemElement 1 : Double), ExchangeItem 2(ExchangeItemElement 1 : Double)];",
            ";DEdge;" });
  }

}
