/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.semantic.queries.ju.testcases;

import org.polarsys.capella.test.semantic.queries.ju.model.SemanticQueries;

public class PropertyTypeElements extends SemanticQueries {
  String QUERY = "org.polarsys.capella.core.semantic.queries.basic.queries.PropertyType";

  @Override
  protected String getQueryCategoryIdentifier() {
    return QUERY;
  }

  @Override
  public void test() throws Exception {

    testQuery(LA__DATA_CLASS_2_PROPERTY_2, LA__DATA_PROPERTY_BOOLEAN);

    testQuery(LA__DATA_CLASS_2_PROPERTY_3, LA__DATA_PROPERTY_BYTE);
  }
}
