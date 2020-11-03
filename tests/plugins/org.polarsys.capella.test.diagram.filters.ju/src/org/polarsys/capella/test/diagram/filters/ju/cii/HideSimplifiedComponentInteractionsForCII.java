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
import org.polarsys.capella.test.diagram.filters.ju.DefaultActivatedFilterTestCase;

public class HideSimplifiedComponentInteractionsForCII extends DefaultActivatedFilterTestCase {

  protected final String SIMPLIFIED_TEST_LOGICAL_COMPONENT_2_ID = "7f42e70a-0601-4eeb-b21c-ed2d2bb475ce";
  protected final String SIMPLIFIED_LOGICAL_COMPONENT_3_ID = "2b058f13-6581-44de-a3b9-e72e29b06816";
  protected final String SIMPLIFIED_LOGICAL_SYSTEM_ID = "1771e1cb-41af-49da-81a2-5c2bf33df9ac";
  protected final String SIMPLIFIED_LOGICAL_COMPONENT_1_ID = "0f22a5a8-3ef1-454f-94e3-ba27fed582de";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[CII] Contextual Component Internal Interfaces";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_LCCII_HIDE_SIMPLIFIED_COMPONENT_INTERACTIONS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { SIMPLIFIED_LOGICAL_COMPONENT_3_ID, SIMPLIFIED_TEST_LOGICAL_COMPONENT_2_ID,
        SIMPLIFIED_LOGICAL_SYSTEM_ID, SIMPLIFIED_LOGICAL_COMPONENT_1_ID });
  }

}
