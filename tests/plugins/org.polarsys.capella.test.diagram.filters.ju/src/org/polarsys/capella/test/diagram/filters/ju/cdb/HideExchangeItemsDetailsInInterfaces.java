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
package org.polarsys.capella.test.diagram.filters.ju.cdb;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.LabelFilterTestCase;

public class HideExchangeItemsDetailsInInterfaces extends LabelFilterTestCase {

  private final String EXCHANGE_ITEM_ALLOCATION_ID = "749cc69b-cff2-4f80-83cd-1b49bcfcd78b";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "Class Diagram Blank Filters Test Diagram";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_CDB_HIDE_EXCHANGE_ITEMS_DETAILS_IN_INTERFACES;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { EXCHANGE_ITEM_ALLOCATION_ID });
  }

  @Override
  protected List<String> getExpectedElementLabels() {
    return Arrays.asList(new String[] { "ExchangeItem 2" });
  }
}
