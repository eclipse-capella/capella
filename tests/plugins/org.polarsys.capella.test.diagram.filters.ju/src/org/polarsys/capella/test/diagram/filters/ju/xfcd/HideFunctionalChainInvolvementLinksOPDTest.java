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

public class HideFunctionalChainInvolvementLinksOPDTest extends DiagramObjectFilterTestCase {

  private final String FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_INTERACTION_1 = "913a0add-421f-41ed-91f6-99dd6d9fab6e";
  private final String FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_INTERACTION_2 = "c5d60e89-7219-426e-a66f-9ba637133fed";
  private final String FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_INTERACTION_3 = "ef817a4c-1f38-4975-95f2-fa92833a2a83";
  private final String FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_INTERACTION_4 = "51716ff3-ad37-41c9-b55f-c9d187890849";

  @Override
  protected String getTestProjectName() {
    return "HideSimplifiedLinksFilter";
  }

  @Override
  protected String getDiagramName() {
    return "[OPD] Filters Test Diagram";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_FCD_HIDE_FUNCTIONAL_CHAIN_INVOLVEMENT_LINKS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_INTERACTION_1,
        FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_INTERACTION_2, FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_INTERACTION_3,
        FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_INTERACTION_4 });
  }
}
