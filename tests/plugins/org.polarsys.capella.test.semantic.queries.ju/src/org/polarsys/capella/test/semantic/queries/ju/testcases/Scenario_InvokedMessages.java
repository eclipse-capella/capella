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

public class Scenario_InvokedMessages extends SemanticQueries {

  String QUERY = "org.polarsys.capella.core.semantic.queries.basic.queries.scenario.InvokedMessages";

  @Override
  protected String getQueryCategoryIdentifier() {
    return QUERY;
  }

  @Override
  public void test() throws Exception {
    testQuery(OA__SCENARIO_OAS, OA__SCENARIO_OAS_INTERACTION_1, OA__SCENARIO_OAS_INTERACTION_2, OA__SCENARIO_OAS_INTERACTION_3, OA__SCENARIO_OAS_INTERACTION_5);
    testQuery(OA__SCENARIO_OES, OA__SCENARIO_OES_COMM_MEAN_1, OA__SCENARIO_OES_COMM_MEAN_2, OA__SCENARIO_OES_COMM_MEAN_2_HIDDEN, OA__SCENARIO_OES_COMM_MEAN_3);
    testQuery(SA__SCENARIO_OC1_FS, SA__SCENARIO_FS_INTERACTION_1, SA__SCENARIO_FS_INTERACTION_2, SA__SCENARIO_FS_INTERACTION_3, SA__SCENARIO_FS_INTERACTION_5);
    testQuery(SA__SCENARIO_CAPA1_ES_2, SA__SCENARIO_ES_COMP_ECH_1);
    testQuery(SA__SCENARIO_IS, SA__SCENARIO_IS_MESSAGE_EI1, SA__SCENARIO_IS_MESSAGE_RECEIVE);
    testQuery(PA__SCENARIO_ES_COPY, PA__SCENARIO_ES_MESSAGE_C1_1, PA__SCENARIO_ES_MESSAGE_C1_2, PA__SCENARIO_ES_MESSAGE_C1_3);
  }
}
