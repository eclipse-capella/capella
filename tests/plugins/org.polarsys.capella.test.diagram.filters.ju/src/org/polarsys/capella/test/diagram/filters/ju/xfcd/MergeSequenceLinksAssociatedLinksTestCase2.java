/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.filters.ju.xfcd;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public class MergeSequenceLinksAssociatedLinksTestCase2 extends DiagramObjectFilterTestCase {
  
  private final String LA_FUNCTIONAL_EXCHANGE_1 = "5d8d7993-e00c-4c51-82a1-09b30a7c8975";
  private final String LA_FUNCTIONAL_EXCHANGE_1_2 = "5d62112f-58ab-4279-85a8-3bf1b26ef4f3";
  
  @Override
  protected String getTestProjectName() {
    return "HideSimplifiedLinksFilter";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_MERGE_ASSOCIATED_FE_AND_SL;
  }
  
  @Override
  protected String getDiagramName() {
    return "[LFCD] FunctionalChain 2";
  }

  @Override
  protected List<String> getFilteredObjetIDs() {  
    return Arrays.asList(new String [] {
        LA_FUNCTIONAL_EXCHANGE_1,
        LA_FUNCTIONAL_EXCHANGE_1_2
    });
  }
}
