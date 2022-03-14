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

public class Scenario_RelatedStates extends SemanticQueries {

  String QUERY = "org.polarsys.capella.core.semantic.queries.basic.queries.scenario.RelatedStates";

  @Override
  protected String getQueryCategoryIdentifier() {
    return QUERY;
  }

  @Override
  public void test() throws Exception {
      testQuery(OA__SCENARIO_OAS, OA__MODE_1_1_1, OA__MODE_1_1, OA__STATE_1_1);
      testQuery(OA__SCENARIO_OES);
      testQuery(SA__SCENARIO_OC1_FS, SA__MODE_1_1_1, SA__STATE_1_1_1);
      testQuery(SA__SCENARIO_CAPA1_ES_2);
      testQuery(SA__SCENARIO_IS, SA__STATE_ENTRYPOINT_1);
      testQuery(PA__SCENARIO_ES_COPY, PA__MODE_2);
  }
}
