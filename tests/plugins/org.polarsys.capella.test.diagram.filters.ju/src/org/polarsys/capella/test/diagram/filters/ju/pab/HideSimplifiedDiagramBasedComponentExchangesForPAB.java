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
package org.polarsys.capella.test.diagram.filters.ju.pab;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DefaultActivatedFilterTestCase;

public class HideSimplifiedDiagramBasedComponentExchangesForPAB extends DefaultActivatedFilterTestCase {

  private final String COMPONENT_PORT_1_ID = "5c63b5e2-8532-441f-b518-89918c90316f";
  private final String COMPONENT_PORT_3_ID = "ba6cf3a4-c03e-422d-8021-c8965b51e00f";
  private final String COMPONENT_PORT_7_ID = "d9a3f460-9dfb-462a-aa4e-0a10b4d7e1ad";
  private final String COMPONENT_PORT_5_ID = "86a60dd2-ccaa-43bb-bf98-ab6d7ec3cd05";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[PAB] Physical System";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_PAB_HIDE_SIMPLIFIED_DIAGRAM_BASED_COMPONENT_EXCHANGES;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays
        .asList(new String[] { COMPONENT_PORT_1_ID, COMPONENT_PORT_3_ID, COMPONENT_PORT_7_ID, COMPONENT_PORT_5_ID });
  }

}