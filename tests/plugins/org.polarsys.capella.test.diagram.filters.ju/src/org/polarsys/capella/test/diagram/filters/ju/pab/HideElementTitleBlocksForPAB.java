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
package org.polarsys.capella.test.diagram.filters.ju.pab;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DiagramTitleBlockFilterTestCase;

public class HideElementTitleBlocksForPAB extends DiagramTitleBlockFilterTestCase {

  private final String ELEMENT_TITLE_BLOCK_NODE_PC_ID = "_SipY8I4YEeqaDKEiylAJ0A";
  private final String ELEMENT_TITLE_BLOCK_PHYSICAL_ACTOR_ID = "_S1bLAI4YEeqaDKEiylAJ0A";

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_COMMON_HIDE_ELEMENT_TITLE_BLOCKS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { ELEMENT_TITLE_BLOCK_NODE_PC_ID,
        ELEMENT_TITLE_BLOCK_PHYSICAL_ACTOR_ID});
  }
  
  @Override
  protected String getDiagramName() {
    return "[PAB] Physical System Title Blocks";
  }
}
