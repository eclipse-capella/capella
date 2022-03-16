/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.semantic.queries.ju.testcases;

import org.polarsys.capella.test.semantic.queries.ju.model.SemanticQueries;

public class FunctionalExchangeAllocatingComponentExchange extends SemanticQueries {
  String QUERY = "org.polarsys.capella.core.semantic.queries.basic.queries.FunctionalExchangeAllocatingComponentExchange";

  @Override
  protected String getQueryCategoryIdentifier() {
    return QUERY;
  }

  @Override
  public void test() throws Exception {
      testQueryIncludingItemQueries(SA__INTERACTION_2, SA__COMPONENT_EXCHANGE_COMPEXCH1, SA__ACTORS__A_1, SA__SYSTEM);
      testQueryIncludingItemQueries(LA__FUNCTIONAL_EXCHANGE_1, LA__COMPONENT_EXCHANGE_C2, LA__LOGICAL_ACTORS__LA_1, 
              LA__LOGICAL_ACTORS__LA_2);
      testQueryIncludingItemQueries(PA__FUNCTIONAL_EXCHANGE_1, PA__PC3, PA__PC4, PA__COMPONENT_EXCHANGE_C2);
  }
}
