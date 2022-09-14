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
 *    Thales Global Services - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.semantic.queries.ju.testcases;

import org.polarsys.capella.test.semantic.queries.ju.model.SemanticQueries;

import junit.framework.Test;

public class FunctionalChain_ExternalReferencedFunctionalChains extends SemanticQueries {
  String QUERY = "org.polarsys.capella.core.semantic.queries.basic.queries.FunctionalChainExternalReferencedFunctionalChains";

  @Override
  protected String getQueryCategoryIdentifier() {
    return QUERY;
  }

  @Override
  public void test() throws Exception {
    testQueryOnlyItemQueries(OA__OPERATIONAL_PROCESS_OA8);
    testQueryOnlyItemQueries(SA__FUNCTIONAL_CHAIN_SF8, SA__FUNCTIONAL_CHAIN_FC1);
    testQueryOnlyItemQueries(LA__CAPABILITIES__CAPABILITY_1__FUNCTIONALCHAIN_1, LA__FUNCTIONAL_CHAIN_LF1,
        LA__ROOT_LF__FUNCTIONALCHAIN_1);
    testQueryOnlyItemQueries(PA__FUNCTIONAL_CHAIN_PF2, PA__FUNCTIONAL_CHAIN);
    testQueryOnlyItemQueries(EPBS__FUNCTIONAL_CHAIN_2, EPBS__FUNCTIONAL_CHAIN_1);
  }

  public static Test suite() {
    return new FunctionalChain_ExternalReferencedFunctionalChains();
  }
}
