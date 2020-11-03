/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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

public class HideFunctionalChainInvolvementLinksSFCDTest extends DiagramObjectFilterTestCase {

  private final String FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_1 = "4961c474-8bf6-4764-a799-b953ecd53c8e";
  private final String FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_2 = "567041b6-1feb-492f-b6ca-62f0cf385bcd";
  private final String FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_3 = "b2040ade-a85b-4597-a504-00602b716607";
  private final String FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_4 = "23b5f57c-f7b8-42a9-80cf-e8a614f9d393";

  @Override
  protected String getTestProjectName() {
    return "HideSimplifiedLinksFilter";
  }

  @Override
  protected String getDiagramName() {
    return "Functional Chain Description Filters Test";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_FCD_HIDE_FUNCTIONAL_CHAIN_INVOLVEMENT_LINKS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_1,
        FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_2,
        FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_3,
        FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_4 });
  }
}
