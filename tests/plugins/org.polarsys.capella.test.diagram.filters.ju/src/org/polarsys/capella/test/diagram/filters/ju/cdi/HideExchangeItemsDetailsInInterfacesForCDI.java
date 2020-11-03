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
package org.polarsys.capella.test.diagram.filters.ju.cdi;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.LabelFilterTestCase;

public class HideExchangeItemsDetailsInInterfacesForCDI extends LabelFilterTestCase {

  private final String EXCHANGE_ITEM_1_ID = "ca8f2bb1-dc9f-4744-b933-f4be6b2a5e16";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[CDI] Contextual Component Detailed Interfaces";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_CDI_HIDE_EXCHANGE_ITEMS_DETAILS_IN_INTERFACES;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { EXCHANGE_ITEM_1_ID });
  }

  @Override
  protected List<String> getExpectedElementLabels() {
    return Arrays.asList(new String[] { "ExchangeItem 1" });
  }

}
