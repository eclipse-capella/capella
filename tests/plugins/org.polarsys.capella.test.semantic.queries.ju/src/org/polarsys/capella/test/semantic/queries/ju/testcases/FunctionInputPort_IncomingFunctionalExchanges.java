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

/**
 * Test for <i>ExchangeItem realized EI</i> query
 */
public class FunctionInputPort_IncomingFunctionalExchanges extends SemanticQueries {
  /**
   * The Query under test.
   */
  String QUERY = "org.polarsys.capella.core.semantic.queries.Pin_incomingDataflows";

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
    testQuery(PA__PF1_FOP1);
    testQuery(PA__PF2_FIP1, PA__FE1);
  }

}
