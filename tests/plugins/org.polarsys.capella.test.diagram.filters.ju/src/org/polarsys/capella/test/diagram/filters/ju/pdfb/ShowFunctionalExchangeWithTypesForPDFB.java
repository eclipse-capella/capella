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
import org.polarsys.capella.test.diagram.filters.ju.LabelFilterTestCase;

public class ShowFunctionalExchangeWithTypesForPDFB extends LabelFilterTestCase {

  private final String FUNCTIONAL_EXCHANGE_4_ID = "7ad3bd11-3149-4952-a616-56613e173810";
  private final String FUNCTIONAL_EXCHANGE_3_ID = "7668dce8-674b-4288-9b61-eb6bac01805b";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[PDFB] Root Physical Function";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_PDFB_SHOW_FUNCTIONAL_EXCHANGES_PARAMETERS_ID;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { FUNCTIONAL_EXCHANGE_4_ID, FUNCTIONAL_EXCHANGE_3_ID });
  }

  @Override
  protected List<String> getExpectedElementLabels() {
    return Arrays.asList(new String[] { ";FunctionalExchange 4();", ";FunctionalExchange 3();" });
  }

}
