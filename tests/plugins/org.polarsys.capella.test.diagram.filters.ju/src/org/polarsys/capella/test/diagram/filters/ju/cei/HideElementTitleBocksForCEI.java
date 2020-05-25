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
package org.polarsys.capella.test.diagram.filters.ju.cei;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DiagramTitleBlockFilterTestCase;

public class HideElementTitleBocksForCEI extends DiagramTitleBlockFilterTestCase {

  private final String ELEMENT_TITLE_BLOCK_LOGICAL_COMPONENT_ID = "_3pXikJXcEeqIX5DIDYO2NQ";
  private final String ELEMENT_TITLE_BLOCK_LOGICAL_ACTOR_ID = "_5BGxkJXcEeqIX5DIDYO2NQ";
  private final String ELEMENT_TITLE_BLOCK_INTERFACE_ID = "_PH9T8JXdEeqIX5DIDYO2NQ";

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_COMMON_HIDE_ELEMENT_TITLE_BLOCKS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { ELEMENT_TITLE_BLOCK_LOGICAL_COMPONENT_ID,
        ELEMENT_TITLE_BLOCK_LOGICAL_ACTOR_ID, ELEMENT_TITLE_BLOCK_INTERFACE_ID});
  }
  
  @Override
  protected String getDiagramName() {
    return "[CEI] Logical Component Title Blocks";
  }
}
