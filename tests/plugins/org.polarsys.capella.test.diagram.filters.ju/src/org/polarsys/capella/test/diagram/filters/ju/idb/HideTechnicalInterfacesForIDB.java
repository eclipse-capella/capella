/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.filters.ju.idb;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DefaultActivatedFilterTestCase;

public class HideTechnicalInterfacesForIDB extends DefaultActivatedFilterTestCase {

  private final String TECHNICAL_INTERFACE_ID = "cc5f4d6a-63e1-4c1a-bc7b-1c29366bae8a";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[IDB] Interfaces Diagram Blank Filters Test Diagram";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_IDB_HIDE_TECHNICALS_INTERFACES;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { TECHNICAL_INTERFACE_ID });
  }
}
