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

public class Capability_ExternalReferencedFunctionalChains extends SemanticQueries {
  String QUERY = "org.polarsys.capella.core.semantic.queries.AbstractCapabilityExternalReferencedFunctionalChains";

  @Override
  protected String getQueryCategoryIdentifier() {
    return QUERY;
  }

  @Override
  public void test() throws Exception {
    testQuery(OA__OPERATIONAL_CAPABILITY1);
    testQuery(SA__CAPABILITIES__CAPABILITY_1, SA__FUNCTIONAL_CHAIN_FC1, SA__FUNCTIONAL_CHAIN_SF7,
        SA__FUNCTIONAL_CHAIN_SF8);
    testQuery(LA__CAPABILITIES__CAPABILITY_1, LA__FUNCTIONAL_CHAIN_LF1, LA__FUNCTIONAL_CHAIN_LF2);
    testQuery(PA__CAPABILITIES__CAPABILITY_1, PA__FUNCTIONAL_CHAIN, PA__FUNCTIONAL_CHAIN_PF1, PA__FUNCTIONAL_CHAIN_PF2);
    testQuery(EPBS__CAPABILITIES__CAPABILITY_2, EPBS__FUNCTIONAL_CHAIN_1, EPBS__FUNCTIONAL_CHAIN);
  }

  public static Test suite() {
    return new Capability_ExternalReferencedFunctionalChains();
  }
}
