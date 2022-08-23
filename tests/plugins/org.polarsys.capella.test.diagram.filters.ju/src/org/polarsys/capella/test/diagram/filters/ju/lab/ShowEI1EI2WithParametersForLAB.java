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
package org.polarsys.capella.test.diagram.filters.ju.lab;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.LabelFilterTestCase;

public class ShowEI1EI2WithParametersForLAB extends LabelFilterTestCase {

  private final String FUNCTIONAL_EXCHANGE_6_ID = "4eb36193-c206-4600-88a3-0e8b7cf98b13";
  private final String FUNCTIONAL_EXCHANGE_7_ID = "3ec6edf3-5863-41fa-9be5-640a454887ed";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[LAB] Logical System 2";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_LAB_SHOW_EXCHANGEITEMS_PARAMETERS_ON_FUNCTIONAL_EXCHANGES_ID;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { FUNCTIONAL_EXCHANGE_6_ID, FUNCTIONAL_EXCHANGE_7_ID });
  }

  @Override
  protected List<String> getExpectedElementLabels() {
    return Arrays.asList(
        new String[] {
            ";[ExchangeItem 1(ExchangeItemElement 1 : Boolean), ExchangeItem 2()];",
            ";DEdge;" });
  }

}
