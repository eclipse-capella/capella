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

public class HidePropertyValues extends DiagramObjectFilterTestCase {

  private final String CLASS_APPLIED_PROPERTY_VALUE_ID = "c5e262f4-0a41-4551-a3ff-43c107510448";
  private final String EXCHANGE_ITEM_APPLIED_PROPERTY_VALUE_ID = "8647c3f3-ae0d-42ee-9614-a189d4ffd7c8";

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
    return IFilterNameConstants.FILTER_CDB_HIDE_PROPERTY_VALUES;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { CLASS_APPLIED_PROPERTY_VALUE_ID, EXCHANGE_ITEM_APPLIED_PROPERTY_VALUE_ID });
  }

}
