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
package org.polarsys.capella.test.diagram.filters.ju.cii;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;

public class HideRequireLinksForCII extends FiltersForCII {

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_LCCII_HIDE_REQUIRE_LINKS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { INVOLVED_LOGICAL_COMPONENT_TO_REQUIRED_INTERFACE_ID,
        TEST_LOGICAL_COMPONENT_2_TO_REQUIRED_INTERFACE_ID, LOGICAL_COMPONENT_3_TO_INTERFACE_1_REQUIRE_ID,
        LOGICAL_COMPONENT_3_TO_INTERFACE_3__REQUIRE_ID, LOGICAL_SYSTEM_TO_INTERFACE_1_REQUIRE_PORT_ID,
        DELEGATED_REQUIRE_ID, LOGICAL_COMPONENT_1_REQUIRE_ID });
  }

}
