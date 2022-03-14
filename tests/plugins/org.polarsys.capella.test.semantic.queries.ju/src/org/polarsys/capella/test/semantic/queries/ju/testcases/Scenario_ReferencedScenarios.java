/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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

public class Scenario_ReferencedScenarios extends SemanticQueries {

  String QUERY = "org.polarsys.capella.core.semantic.queries.ReferencedScenario";

  @Override
  protected String getQueryCategoryIdentifier() {
    return QUERY;
  }

  @Override
  public void test() throws Exception {
      testQuery(OA__SCENARIO_OAS, OA__SCENARIO_OAS_OC2);
      testQuery(OA__SCENARIO_OES);
      testQuery(SA__SCENARIO_OC1_FS, SA__SCENARIO_FS_OCAPA2);
      testQuery(SA__SCENARIO_OC1_ES_1);
      testQuery(SA__SCENARIO_CAPA1_ES_1);
      testQuery(PA__SCENARIO_ES_COPY, PA__SCENARIO_ES);
      testQuery(EPBS_SCENARIO_CAPA1_IS, EPBS_SCENARIO_CAPA1_IS_COPY);
      testQuery(EPBS_SCENARIO_CAPA1_IS_COPY);
  }
}
