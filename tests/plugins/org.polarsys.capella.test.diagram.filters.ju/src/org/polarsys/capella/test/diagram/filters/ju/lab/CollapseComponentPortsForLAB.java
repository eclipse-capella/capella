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
package org.polarsys.capella.test.diagram.filters.ju.lab;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;

public class CollapseComponentPortsForLAB extends FiltersForLAB {

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_LAB_COLLAPSE_COMPONENT_PORTS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { COMPONENT_PORT_1_ID, COMPONENT_PORT_2_ID, COMPONENT_PORT_3_ID,
        COMPONENT_PORT_4_ID, COMPONENT_PORT_7_ID, COMPONENT_PORT_WITHOUT_EXCHANGES_ID, PORT_ALLOCATION_TO_FOP_1_ID,
        PORT_ALLOCATION_TO_FIP_1_ID });
  }

}
