/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.filters.ju.xdfb;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DiagramTitleBlockFilterTestCase;

public class HideElementTitleBlocksTestCase extends DiagramTitleBlockFilterTestCase {

  private final String ELEMENT_TITLE_BLOCK_OPERATIONAL_ACTIVITY1_ID = "_2JYV8I4aEeqaDKEiylAJ0A";
  private final String ELEMENT_TITLE_BLOCK_OPERATIONAL_ACTIVITY2_ID = "_4Req4I4aEeqaDKEiylAJ0A";

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_COMMON_HIDE_ELEMENT_TITLE_BLOCKS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { ELEMENT_TITLE_BLOCK_OPERATIONAL_ACTIVITY1_ID,
        ELEMENT_TITLE_BLOCK_OPERATIONAL_ACTIVITY2_ID});
  }
  
  @Override
  protected String getDiagramName() {
    return "[OAIB] Root Operational Activity Title Blocks";
  }
}
