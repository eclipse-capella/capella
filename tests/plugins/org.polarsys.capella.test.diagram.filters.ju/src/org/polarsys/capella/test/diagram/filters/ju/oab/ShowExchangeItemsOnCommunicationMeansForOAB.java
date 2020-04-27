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
package org.polarsys.capella.test.diagram.filters.ju.oab;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.LabelFilterTestCase;

public class ShowExchangeItemsOnCommunicationMeansForOAB extends LabelFilterTestCase {

  private final String COMMUNICATION_MEAN_ID = "9f4e88a5-5980-4622-8717-9a5590a70c49";

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
    return IFilterNameConstants.FILTER_OAB_SHOW_EXCHANGE_ITEM_ON_COMMUNICATION_MEANS_ID;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { COMMUNICATION_MEAN_ID });
  }

  @Override
  protected List<String> getExpectedElementLabels() {
    return Arrays.asList(new String[] { ";ExchangeItem 1, ExchangeItem 3;" });
  }

}
