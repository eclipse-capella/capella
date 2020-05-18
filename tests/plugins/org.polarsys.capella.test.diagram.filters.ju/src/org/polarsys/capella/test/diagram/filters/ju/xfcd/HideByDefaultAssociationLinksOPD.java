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

public class HideByDefaultAssociationLinksOPD extends DefaultActivatedFilterTestCase {

  private final String ASSOCIATION_LINK_TO_FUNCTIONAL_EXCHANGE_1_ID = "529b09a3-cdc2-455d-b4d1-7440e6ef09bd";
  private final String ASSOCIATION_LINK_TO_FUNCTIONAL_EXCHANGE_2_ID = "ea593610-e334-4e12-95c5-2d143742a81d";

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
    return IFilterNameConstants.FILTER_FCD_HIDE_ASSOCIATION_LINKS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(
        new String[] { ASSOCIATION_LINK_TO_FUNCTIONAL_EXCHANGE_1_ID, ASSOCIATION_LINK_TO_FUNCTIONAL_EXCHANGE_2_ID });
  }
}
