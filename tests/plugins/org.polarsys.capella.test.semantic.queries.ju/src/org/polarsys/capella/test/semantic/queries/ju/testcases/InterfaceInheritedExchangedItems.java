/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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

public class InterfaceInheritedExchangedItems extends SemanticQueries {
  String QUERY = "capella.semantic.queries.Interface_inheritedExchangeItem";

  @Override
  protected String getQueryCategoryIdentifier() {
    return QUERY;
  }

  @Override
  public void test() throws Exception {

    testQuery(LA__LOGICAL_INTERFACE_1, LA__LOGICAL_EXCHANGE_ITEM_2, LA__LOGICAL_EXCHANGE_ITEM_3);

    testQuery(LA__LOGICAL_INTERFACE_2, LA__LOGICAL_EXCHANGE_ITEM_3);

    testQuery(LA__LOGICAL_INTERFACE_3);
  }
}
