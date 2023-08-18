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
package org.polarsys.capella.test.diagram.filters.ju.pdfb;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;

public class HideFunctionPortsWithoutExchangesForPDFB extends FiltersForPDFB {

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_PDFB_HIDE_FUNCTION_PORTS_WITHOUT_EXCHANGES;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(
        new String[] { FUNCTION_PORT_WITHOUT_EXCHANGES_ID, FUNCTION_OUTPUT_PORT_2_ID, FUNCTION_INPUT_PORT_2_ID });
  }

}
