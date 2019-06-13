/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.filters.ju.cii;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;

public class HideUseLinksForCII extends FiltersForCII {

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_LCCII_HIDE_USE_LINKS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { LOGICAL_COMPONENT_3_TO_INTERFACE_1_USE_ID,
        LOGICAL_COMPONENT_3_TO_INTERFACE_3_USE_ID, INVOLVED_LOGICAL_COMPONENT_TO_TEST_INTERFACE_ID,
        TEST_LOGICAL_COMPONENT_2_TO_TEST_INTERFACE_ID, LOGICAL_SYSTEM_TO_INTERFACE_1_USE_LINK_ID, DELEGATED_USE_ID });
  }

}
