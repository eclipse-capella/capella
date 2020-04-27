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

public class HideFunctionalChainInvolvementLinksLFCD extends DiagramObjectFilterTestCase {

  private final String FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_4 = "b2c0ffb6-5e0e-4572-b0d3-c9046f18e0ec";
  private final String FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_5 = "11248b7f-d320-4f1b-a119-3ddb4269965a";
  private final String FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_1 = "5d8d7993-e00c-4c51-82a1-09b30a7c8975";

  @Override
  protected String getTestProjectName() {
    return "HideSimplifiedLinksFilter";
  }

  @Override
  protected String getDiagramName() {
    return "[LFCD] Filters Test Diagram";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_FCD_HIDE_FUNCTIONAL_CHAIN_INVOLVEMENT_LINKS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_1,
        FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_5,
        FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_4 });
  }

}
