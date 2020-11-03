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
import java.util.function.Predicate;

import org.eclipse.sirius.diagram.DDiagramElement;
import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DiagramFilterUtils;

public class CollapseFunctionPortsForPAB extends FiltersForPAB {

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_PAB_COLLAPSE_FUNCTION_PORTS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(FUNCTION_INPUT_PORT_1_ID, FUNCTION_OUTPUT_PORT_1_ID, FUNCTION_INPUT_PORT_2_ID,
        FUNCTION_OUTPUT_PORT_2_ID, FUNCTION_INPUT_PORT_WITHOUT_EXCHANGES_ID, PORT_ALLOCATION_TO_FIP_2_ID,
        UNKNOWN_COMPONENT_2_ID, UNKNOWN_COMPONENT_3_ID);
  }

  @Override
  protected Predicate<DDiagramElement> getElementsToNotFilterExtraConditionPredicate() {
    return DiagramFilterUtils.FUNCTIONAL_EXCHANGE_EDGE_DECORATOR_PREDICATE;
  }
}