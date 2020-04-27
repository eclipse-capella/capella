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
package org.polarsys.capella.test.diagram.filters.ju.idb;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DefaultActivatedFilterTestCase;

public class HideSimplifiedDiagramBasedInteractionsForIDB extends DefaultActivatedFilterTestCase {

  private final String LOGICAL_COMPONENT_1_SIMPLIFIED_DIAGRAM_INTERACTION_ID = "971c0f72-8e3b-45a6-a1e8-b4488c88c018";
  private final String LOGICAL_COMPONENT_3_SIMPLIFIED_DIAGRAM_INTERACTION_ID = "2b058f13-6581-44de-a3b9-e72e29b06816";

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
    return IFilterNameConstants.FILTER_IDB_HIDE_SIMPLIFIED_DIAGRAM_BASED_INTERACTIONS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { LOGICAL_COMPONENT_3_SIMPLIFIED_DIAGRAM_INTERACTION_ID,
        LOGICAL_COMPONENT_1_SIMPLIFIED_DIAGRAM_INTERACTION_ID });
  }

}
