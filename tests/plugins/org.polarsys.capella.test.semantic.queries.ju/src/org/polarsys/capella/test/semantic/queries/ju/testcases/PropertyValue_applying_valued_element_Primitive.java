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
 *    Thales Global Services - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.semantic.queries.ju.testcases;

import org.polarsys.capella.test.semantic.queries.ju.model.SemanticQueries;

public class PropertyValue_applying_valued_element_Primitive extends SemanticQueries {
  /**
   * The Query under test.
   */
  String QUERY = "org.polarsys.capella.core.semantic.queries.PropertyValue_applying_valued_element_Primitive";

  /**
   * @return the query category identifier.
   */
  @Override
  protected String getQueryCategoryIdentifier() {
    return QUERY;
  }

  /**
   * Test method.
   */
  @Override
  public void test() throws Exception {
    testQueryValue(LA__PROPERTYVALUEPKG__BOOLEANPROPERTYVALUE, "false");
    testQueryValue(LA__PROPERTYVALUEPKG__FLOATPROPERTYVALUE, "0.0");
    testQueryValue(LA__PROPERTYVALUEPKG__INTEGERPROPERTYVALUE, "100");
    testQueryValue(LA__PROPERTYVALUEPKG__STRINGPROPERTYVALUE, "String Test");
  }

}