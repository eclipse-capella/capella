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
package org.polarsys.capella.test.diagram.filters.ju.cdb;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DiagramTitleBlockFilterTestCase;

public class HideElementTitleBocksTest extends DiagramTitleBlockFilterTestCase {

  private final String ELEMENT_TITLE_BLOCK_CLASS_ID = "_48t68IXwEeqWaJq2mNMsRw";
  private final String ELEMENT_TITLE_BLOCK_DATA_PKG_ID = "_6iC6AIXwEeqWaJq2mNMsRw";

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_CDB_HIDE_ELEMENT_TITLE_BLOCKS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { ELEMENT_TITLE_BLOCK_CLASS_ID,
        ELEMENT_TITLE_BLOCK_DATA_PKG_ID});
  }
}
