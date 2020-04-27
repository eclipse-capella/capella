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

public class ShowAllocatedFunctionalExchangesOnComponentExchangesForPAB extends LabelFilterTestCase {

  private final String COMPONENT_EXCHANGE_1_ID = "63d4dd01-c52f-4a74-85b4-dcece0ece82f";

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
    return IFilterNameConstants.FILTER_PAB_SHOW_ALLOCATED_FUNCTIONAL_EXCHANGES_ON_COMPONENT_EXCHANGES;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { COMPONENT_EXCHANGE_1_ID });
  }

  @Override
  protected List<String> getExpectedElementLabels() {
    return Arrays.asList(new String[] { ";FunctionalExchange 1;" });
  }

}
