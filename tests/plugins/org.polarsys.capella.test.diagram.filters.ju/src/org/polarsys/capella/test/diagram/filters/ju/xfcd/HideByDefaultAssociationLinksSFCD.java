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
import org.polarsys.capella.test.diagram.filters.ju.DefaultActivatedFilterTestCase;

public class HideByDefaultAssociationLinksSFCD extends DefaultActivatedFilterTestCase {

  private final String ASSOCIATION_LINK_TO_FUNCTIONAL_EXCHANGE_1_ID = "c24d372e-c3c9-41cf-a569-e774ceff12fe";
  private final String ASSOCIATION_LINK_TO_FUNCTIONAL_EXCHANGE_2_ID = "648ae687-9b65-42c2-90f4-3e7bb072899c";

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
    return IFilterNameConstants.FILTER_FCD_HIDE_ASSOCIATION_LINKS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(
        new String[] { ASSOCIATION_LINK_TO_FUNCTIONAL_EXCHANGE_1_ID, ASSOCIATION_LINK_TO_FUNCTIONAL_EXCHANGE_2_ID });
  }

}
