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
package org.polarsys.capella.test.diagram.filters.ju.mcb;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DiagramTitleBlockFilterTestCase;

public class HideElementTitleBlocksForMCB extends DiagramTitleBlockFilterTestCase {

  private final String ELEMENT_TITLE_BLOCK_SYSTEM_ACTOR_ID = "_eLrnoI7TEeqR99_BeAxvxA";
  private final String ELEMENT_TITLE_BLOCK_MISSION_ID = "_em488I7TEeqR99_BeAxvxA";
  private final String ELEMENT_TITLE_BLOCK_CAPABILITY_ID = "_fBSZ8I7TEeqR99_BeAxvxA";

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_COMMON_HIDE_ELEMENT_TITLE_BLOCKS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { ELEMENT_TITLE_BLOCK_SYSTEM_ACTOR_ID,
        ELEMENT_TITLE_BLOCK_MISSION_ID, ELEMENT_TITLE_BLOCK_CAPABILITY_ID});
  }
  
  @Override
  protected String getDiagramName() {
    return "[MCB] Capabilities Title Blocks";
  }
}
