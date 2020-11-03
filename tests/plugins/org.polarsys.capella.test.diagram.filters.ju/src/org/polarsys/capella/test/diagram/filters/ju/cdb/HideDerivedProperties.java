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
package org.polarsys.capella.test.diagram.filters.ju.cdb;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public class HideDerivedProperties extends DiagramObjectFilterTestCase {

  private final String DERIVED_PROPERTY_ID = "60b28b05-e31e-4c07-a579-f46bf0188214";
  private final String ASSOCIATION_WITH_DERIVED_ROLE = "50a629b0-6c6c-4529-85a0-b20f00045154";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "Class Diagram Blank Filters Test Diagram";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_CDB_HIDE_DERIVED_PROPERTIES;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { DERIVED_PROPERTY_ID, ASSOCIATION_WITH_DERIVED_ROLE });
  }
}
