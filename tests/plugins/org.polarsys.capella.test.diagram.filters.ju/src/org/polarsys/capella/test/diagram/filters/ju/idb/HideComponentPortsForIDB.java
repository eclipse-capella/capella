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
package org.polarsys.capella.test.diagram.filters.ju.idb;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;

public class HideComponentPortsForIDB extends FiltersForIDB {

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_IDB_HIDE_COMPONENT_PORTS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { OUTFLOW_COMPONENT_PORT_ID, INFLOW_COMPONENT_PORT_ID,
        DELEGATED_PROVIDE_LINK_TO_INTERFACE_1_ID, DELEGATED_REQUIRE_LINK_TO_INTERFACE_1_ID,
        DELEGATED_PROVIDE_LINK_TO_INTERFACE_3_ID, DELEGATED_REQUIRE_LINK_TO_INTERFACE_3_ID,
        LOGICAL_COMPONENT_1_DELEGATED_PROVIDE_LINK_ID, LOGICAL_COMPONENT_1_DELEGATED_REQUIRE_LINK_ID });
  }

}
