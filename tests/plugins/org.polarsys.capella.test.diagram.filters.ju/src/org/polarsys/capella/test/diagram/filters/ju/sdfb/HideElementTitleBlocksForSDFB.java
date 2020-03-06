/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.filters.ju.sdfb;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DiagramTitleBlockFilterTestCase;

public class HideElementTitleBlocksForSDFB extends DiagramTitleBlockFilterTestCase {

  private final String ELEMENT_TITLE_BLOCK_SYSTEM_FUNCTION_ID = "_GZJTsI4cEeqaDKEiylAJ0A";
  private final String ELEMENT_TITLE_BLOCK_ACTOR_FUNCTiON_ID = "_GwL6sI4cEeqaDKEiylAJ0A";

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_COMMON_HIDE_ELEMENT_TITLE_BLOCKS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { ELEMENT_TITLE_BLOCK_SYSTEM_FUNCTION_ID,
        ELEMENT_TITLE_BLOCK_ACTOR_FUNCTiON_ID});
  }
  
  @Override
  protected String getDiagramName() {
    return "[SDFB] Root System Function Title Blocks";
  }
}
