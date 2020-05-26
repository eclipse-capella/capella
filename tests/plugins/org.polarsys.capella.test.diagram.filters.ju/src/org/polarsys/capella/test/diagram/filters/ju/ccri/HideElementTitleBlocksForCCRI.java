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
package org.polarsys.capella.test.diagram.filters.ju.ccri;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DiagramTitleBlockFilterTestCase;

public class HideElementTitleBlocksForCCRI extends DiagramTitleBlockFilterTestCase {

  private final String ELEMENT_TITLE_BLOCK_LOGICAL_ACTOR_ID = "_qn75EJQaEeqmRd48Y6X5KA";
  private final String ELEMENT_TITLE_BLOCK_CAPABILITY_REALIZATION_ID = "_qMKjEJQaEeqmRd48Y6X5KA";
  private final String ELEMENT_TITLE_BLOCK_LOGICAL_COMPONENT_ID = "_q726oJQaEeqmRd48Y6X5KA";

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_COMMON_HIDE_ELEMENT_TITLE_BLOCKS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { ELEMENT_TITLE_BLOCK_LOGICAL_ACTOR_ID,
        ELEMENT_TITLE_BLOCK_CAPABILITY_REALIZATION_ID, ELEMENT_TITLE_BLOCK_LOGICAL_COMPONENT_ID});
  }
  
  @Override
  protected String getDiagramName() {
    return "[CRI] CapabilityRealization Title Blocks";
  }
}
