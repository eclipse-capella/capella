/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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

/**
 * Test for <i>AbstractStateParentActiveElements</i> query:
 * 
 * <ul>
 * <li>A parent state/mode is a state/mode that has sub states/modes;</li>
 * </ul>
 * 
 */
public class AbstractStateParentActiveElements extends SemanticQueries {
  /**
   * The Query under test.
   */
  String QUERY = "org.polarsys.capella.core.semantic.queries.AbstractStateParentActiveElements";

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
    //////////////////////////////////// OA ////////////////////////////////////////////////////////////////////////
    // Test STATES/MODES: Parent state shows as Computed Active Elements all the Active Elements in the substates //
    //////////////////////////////////// OA /////////////////////////////////////////////////////////////////////////
    testQuery(OA__STATE_1_1_1);
    testQuery(OA__STATE_1_1, OA__OPERATIONAL_ACTIVITY_OA7, OA__OPERATIONAL_ACTIVITY_12, OA__OPERATIONAL_ACTIVITY_13);
    testQuery(OA__STATE_1, OA__OPERATIONAL_ACTIVITY_OA7, OA__OPERATIONAL_ACTIVITY_OA8, OA__OPERATIONAL_ACTIVITY_12, 
            OA__OPERATIONAL_ACTIVITY_13, OA__OPERATIONAL_PROCESS_OP1, OA__OPERATIONAL_CAPABILITY_OC5);

    testQuery(OA__MODE_1_1_1);
    testQuery(OA__MODE_1_1, OA__OPERATIONAL_ACTIVITY_OA7, OA__OPERATIONAL_ACTIVITY_12, OA__OPERATIONAL_ACTIVITY_13);
    testQuery(OA__MODE_1, OA__OPERATIONAL_ACTIVITY_OA7, OA__OPERATIONAL_ACTIVITY_OA8, OA__OPERATIONAL_ACTIVITY_12, 
            OA__OPERATIONAL_ACTIVITY_13, OA__OPERATIONAL_PROCESS_OP1, OA__OPERATIONAL_CAPABILITY_OC5);

    //////////////////////////////////// SA ////////////////////////////////////////////////////////////////////////
    // Test STATES/MODES: Parent state shows as Computed Active Elements all the Active Elements in the substates //
    //////////////////////////////////// SA /////////////////////////////////////////////////////////////////////////
    testQuery(SA__STATE_1_1_1);
    testQuery(SA__STATE_1_1, SA__SYSTEM_FUNCTION_SF8, SA__ROOT_SF_SYSTEMFUNCTION_5, SA__ROOT_SF_SYSTEMFUNCTION_6, 
            SA__ROOT_SF_SYSTEMFUNCTION_11, SA__OPERATIONAL_CAPABILITY_OC2);
    testQuery(SA__STATE_1, SA__SYSTEM_FUNCTION_SF7, SA__FUNCTIONAL_CHAIN_FC1, SA__SYSTEM_FUNCTION_SF8, 
            SA__ROOT_SF_SYSTEMFUNCTION_5, SA__ROOT_SF_SYSTEMFUNCTION_6, SA__ROOT_SF_SYSTEMFUNCTION_11, 
            SA__OPERATIONAL_CAPABILITY_OC2);
    
    testQuery(SA__MODE_1_1_1);
    testQuery(SA__MODE_1_1, SA__SYSTEM_FUNCTION_SF8, SA__ROOT_SF_SYSTEMFUNCTION_5, SA__ROOT_SF_SYSTEMFUNCTION_13, 
            SA__OPERATIONAL_CAPABILITY_OC2);
    testQuery(SA__MODE_1, SA__SYSTEM_FUNCTION_SF7, SA__FUNCTIONAL_CHAIN_FC1, SA__SYSTEM_FUNCTION_SF8, 
            SA__ROOT_SF_SYSTEMFUNCTION_5, SA__ROOT_SF_SYSTEMFUNCTION_13, SA__OPERATIONAL_CAPABILITY_OC2);
  }
}
