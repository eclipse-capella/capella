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
package org.polarsys.capella.test.diagram.filters.ju.ldfb;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.LabelFilterTestCase;

public class HideFunctionalExchangesNamesForLDFB extends LabelFilterTestCase {

  private final String FUNCTIONAL_EXCHANGE_3_ID = "8cf09c8b-08f1-40b7-a624-03ba8a89769d";
  private final String FUNCTIONAL_EXCHANGE_5_ID = "ed5417a3-b973-41b3-b7d5-e71b2c251ebe";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[LDFB] Root Logical Function";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_LDFB_HIDE_FUNCTIONAL_EXCHANGES_NAMES;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { FUNCTIONAL_EXCHANGE_3_ID, FUNCTIONAL_EXCHANGE_5_ID });
  }

  @Override
  protected List<String> getExpectedElementLabels() {
    return Arrays.asList(new String[] { "; ;", "; ;" });
  }

}
