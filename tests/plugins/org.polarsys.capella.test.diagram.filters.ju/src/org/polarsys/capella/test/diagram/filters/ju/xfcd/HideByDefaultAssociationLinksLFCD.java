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

public class HideByDefaultAssociationLinksLFCD extends DefaultActivatedFilterTestCase {

  private final String ASSOCIATION_LINK_TO_FUNCTIONAL_EXCHANGE_1_ID = "203057c4-3278-4c14-b69b-b4d6026b2311";
  private final String ASSOCIATION_LINK_TO_FUNCTIONAL_EXCHANGE_4_ID = "c42e9b79-2c2b-483a-b020-339bf463f5e1";
  private final String ASSOCIATION_LINK_TO_FUNCTIONAL_EXCHANGE_5_ID = "543daa71-ef89-4e3a-8904-88563e832d99";

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
    return IFilterNameConstants.FILTER_FCD_HIDE_ASSOCIATION_LINKS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { ASSOCIATION_LINK_TO_FUNCTIONAL_EXCHANGE_1_ID,
        ASSOCIATION_LINK_TO_FUNCTIONAL_EXCHANGE_4_ID, ASSOCIATION_LINK_TO_FUNCTIONAL_EXCHANGE_5_ID });
  }

}
