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
package org.polarsys.capella.test.diagram.filters.ju.crb;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DiagramTitleBlockFilterTestCase;

public class HideElementTitleBlocksForCRB extends DiagramTitleBlockFilterTestCase {

  private final String ELEMENT_TITLE_BLOCK_CAPABILITY_REALIZATION_ID = "_Q4q9oI7MEeqR99_BeAxvxA";
  private final String ELEMENT_TITLE_BLOCK_COMPONENT_ID = "_RNbssI7MEeqR99_BeAxvxA";
  private final String ELEMENT_TITLE_BLOCK_ACTOR_ID = "_RfcCsI7MEeqR99_BeAxvxA";

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_COMMON_HIDE_ELEMENT_TITLE_BLOCKS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { ELEMENT_TITLE_BLOCK_CAPABILITY_REALIZATION_ID,
        ELEMENT_TITLE_BLOCK_COMPONENT_ID, ELEMENT_TITLE_BLOCK_ACTOR_ID});
  }
  
  @Override
  protected String getDiagramName() {
    return "[CRB] Capabilities Title Blocks";
  }
}
