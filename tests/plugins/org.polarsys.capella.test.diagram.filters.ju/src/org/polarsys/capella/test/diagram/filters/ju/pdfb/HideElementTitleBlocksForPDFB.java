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
package org.polarsys.capella.test.diagram.filters.ju.pdfb;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DiagramTitleBlockFilterTestCase;

public class HideElementTitleBlocksForPDFB extends DiagramTitleBlockFilterTestCase {

  private final String ELEMENT_TITLE_BLOCK_PHYSICAL_FUNCTION_ID = "_Wrc6AI4bEeqaDKEiylAJ0A";
  private final String ELEMENT_TITLE_BLOCK_ACTOR_FUNCTiON_ID = "_XiYiAI4bEeqaDKEiylAJ0A";

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_COMMON_HIDE_ELEMENT_TITLE_BLOCKS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { ELEMENT_TITLE_BLOCK_PHYSICAL_FUNCTION_ID,
        ELEMENT_TITLE_BLOCK_ACTOR_FUNCTiON_ID});
  }
  
  @Override
  protected String getDiagramName() {
    return "[PDFB] Root Physical Function Title Blocks";
  }
}
