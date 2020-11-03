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

public class HideFunctionalChainInvolvementLinksPFCDTest extends DiagramObjectFilterTestCase {

  private final String FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_1 = "ddea0742-61fe-4836-a10e-144981fd73fe";
  private final String FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_2 = "5135481a-5af0-4810-ba18-f27afa046ffd";
  private final String FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_3 = "fdac594d-0bd2-44de-82df-9587b3d7d6ca";
  private final String FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_4 = "dced47f4-82cb-4c6d-8839-91c3cee5386f";

  @Override
  protected String getTestProjectName() {
    return "HideSimplifiedLinksFilter";
  }

  @Override
  protected String getDiagramName() {
    return "[PFCD] Filters Test Diagram";
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
