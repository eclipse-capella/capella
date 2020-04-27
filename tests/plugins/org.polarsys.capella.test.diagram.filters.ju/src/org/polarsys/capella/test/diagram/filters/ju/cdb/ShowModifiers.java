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
package org.polarsys.capella.test.diagram.filters.ju.cdb;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.LabelFilterTestCase;

public class ShowModifiers extends LabelFilterTestCase {

  private final String ASSOCIATION_ID = "50a629b0-6c6c-4529-85a0-b20f00045154";
  private final String COMPOSITION_ID = "7ed898ef-bbff-4623-8498-49df17c8d8ef";
  private final String GENERALIZATION_ID = "24ab00f5-fc14-4c6d-bf8d-6bc5e9a085b6";
  private final String EXCHANGE_ITEM_ELEMENT_ID = "313c0f19-abbf-43b9-966f-e252e2df226d";
  private final String PROPERTY_DOUBLE_ID = "318174a2-3bcd-418d-aba3-3046ca2bc094";
  private final String LONG_DERIVED_PROPERTY_ID = "60b28b05-e31e-4c07-a579-f46bf0188214";

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
    return IFilterNameConstants.FILTER_CDB_SHOW_MODIFIERS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { ASSOCIATION_ID, COMPOSITION_ID, GENERALIZATION_ID, EXCHANGE_ITEM_ELEMENT_ID,
        PROPERTY_DOUBLE_ID, LONG_DERIVED_PROPERTY_ID });
  }

  @Override
  protected List<String> getExpectedElementLabels() {
    return Arrays
        .asList(new String[] { "& / {nonUnique}; ;& /union {nonUnique}", " {nonUnique}; ;collection 1 {nonUnique}",
            "&  {nonUnique}; ;class 3 {nonUnique}", "; ExchangeItemElement 1 {nonUnique};",
            " Property 4 : Double {nonUnique}", " /LongDerivedProperty : LongLong {nonUnique}" });
  }
}
