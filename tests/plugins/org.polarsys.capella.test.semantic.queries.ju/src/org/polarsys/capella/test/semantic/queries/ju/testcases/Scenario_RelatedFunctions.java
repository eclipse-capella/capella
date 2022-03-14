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

public class Scenario_RelatedFunctions extends SemanticQueries {

  String QUERY = "org.polarsys.capella.core.semantic.queries.basic.queries.scenario.RelatedFunctions";

  @Override
  protected String getQueryCategoryIdentifier() {
    return QUERY;
  }

  @Override
  public void test() throws Exception {
      testQuery(OA__SCENARIO_OAS);
      testQuery(OA__SCENARIO_OES);
      testQuery(SA__SCENARIO_OC1_FS, SA__ROOT_SF_SYSTEMFUNCTION_12);
      testQuery(SA__SCENARIO_OC1_ES_1, SA__ROOT_SF__FRAGMENT_ALLOCATED_FUNCTION, 
              SA__ROOT_SF__MOTHER_ACTOR_OK__SYSTEMFUNCTION_1, SA__ROOT_SF__MOTHER_ACTOR_OK__SYSTEMFUNCTION_2, 
              SA__ROOT_SF_SYSTEMFUNCTION_5);
      testQuery(SA__SCENARIO_CAPA1_ES_1, SA__ROOT_SF__FRAGMENT_ALLOCATED_FUNCTION);
      testQuery(SA__SCENARIO_IS);
      testQuery(PA__SCENARIO_ES_COPY, PA__FUNCTION_1, PA__FUNCTION_2);
      testQuery(EPBS_SCENARIO_CAPA1_IS);
      testQuery(EPBS_SCENARIO_CAPA1_IS_COPY, PA__FUNCTION_3);
  }
}
