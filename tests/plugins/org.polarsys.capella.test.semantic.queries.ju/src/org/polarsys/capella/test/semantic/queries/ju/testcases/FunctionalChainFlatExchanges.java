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

public class FunctionalChainFlatExchanges extends SemanticQueries {
  String QUERY = "org.polarsys.capella.core.semantic.queries.basic.queries.functionalChain.flatExchanges";

  @Override
  protected String getQueryCategoryIdentifier() {
    return QUERY;
  }

  @Override
  public void test() throws Exception {
    testQuery(PA__FUNCTIONAL_CHAIN, PA__FUNCTIONAL_EXCHANGE_1, PA__FUNCTIONAL_EXCHANGE_2, PA__FUNCTIONAL_EXCHANGE_3, PA__FUNCTIONAL_EXCHANGE_5);
  }
}
