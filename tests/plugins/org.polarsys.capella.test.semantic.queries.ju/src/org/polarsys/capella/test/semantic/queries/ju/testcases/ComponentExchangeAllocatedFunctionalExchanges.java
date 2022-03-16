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

public class ComponentExchangeAllocatedFunctionalExchanges extends SemanticQueries {
  String QUERY = "org.polarsys.capella.core.semantic.queries.Connection_realizedFunctionalExchanges";

  @Override
  protected String getQueryCategoryIdentifier() {
    return QUERY;
  }

  @Override
  public void test() throws Exception {
      testQueryIncludingItemQueries(SA__COMPONENT_EXCHANGE_COMPEXCH1, SA__INTERACTION_2, SA__ROOT_SF_SYSTEMFUNCTION_10, 
              SA__ROOT_SF_SYSTEMFUNCTION_12);
      testQueryIncludingItemQueries(LA__COMPONENT_EXCHANGE_C2, LA__FUNCTIONAL_EXCHANGE_1, LA__ROOT_LF__LOGICALFUNCTION_1, 
              LA__ROOT_LF__LOGICALFUNCTION_2);
      testQueryIncludingItemQueries(PA__COMPONENT_EXCHANGE_C2, PA__FUNCTIONAL_EXCHANGE_1, PA__FUNCTION_1, PA__FUNCTION_2);
  }
}
