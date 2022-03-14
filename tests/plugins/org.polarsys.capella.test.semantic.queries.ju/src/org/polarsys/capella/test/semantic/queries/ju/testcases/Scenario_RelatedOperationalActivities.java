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

public class Scenario_RelatedOperationalActivities extends SemanticQueries {

  String QUERY = "org.polarsys.capella.core.semantic.queries.basic.queries.scenario.RelatedOperationalActivities";

  @Override
  protected String getQueryCategoryIdentifier() {
    return QUERY;
  }

  @Override
  public void test() throws Exception {
      testQuery(OA__SCENARIO_OAS, OA__OPERATIONAL_ACTIVITIES__ROOT_OA__OPERATIONALACTIVITY_11);
      testQuery(OA__SCENARIO_OES);
      testQuery(SA__SCENARIO_OC1_FS, OA__OPERATIONAL_ACTIVITIES__ROOT_OA__OPERATIONALACTIVITY_11);
      testQuery(SA__SCENARIO_OC1_ES_1, OA__OPERATIONAL_ACTIVITY_13);
      testQuery(SA__SCENARIO_CAPA1_ES_1);
      testQuery(PA__SCENARIO_ES_COPY);
      testQuery(EPBS_SCENARIO_CAPA1_IS, OA__OPERATIONAL_ACTIVITY_13);
      testQuery(EPBS_SCENARIO_CAPA1_IS_COPY);
  }
}
