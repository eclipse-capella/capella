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
package org.polarsys.capella.test.diagram.filters.ju.idb;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DiagramTitleBlockFilterTestCase;

public class HideElementTitleBocksForIDB extends DiagramTitleBlockFilterTestCase {

  private final String ELEMENT_TITLE_BLOCK_LOGICAL_COMPONENT_ID = "_s-PnMJXdEeqIX5DIDYO2NQ";
  private final String ELEMENT_TITLE_BLOCK_LOGICAL_ACTOR_ID = "_tQGMMJXdEeqIX5DIDYO2NQ";
  private final String ELEMENT_TITLE_BLOCK_INTERFACE_ID = "_tiBpsJXdEeqIX5DIDYO2NQ";

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
    return "[IDB] Logical Component Title Blocks";
  }
}
