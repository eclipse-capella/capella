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

public class Scenario_RepresentedInstances extends SemanticQueries {

  String QUERY = "org.polarsys.capella.core.semantic.queries.basic.queries.scenario.RepresentedInstances";

  @Override
  protected String getQueryCategoryIdentifier() {
    return QUERY;
  }

  @Override
  public void test() throws Exception {
		testQuery(OA__SCENARIO_OAS, OA__OPERATIONAL_ACTIVITY_10, OA__OPERATIONAL_ACTIVITY_12,
              OA__OPERATIONAL_ACTIVITY_13, OA__OPERATIONAL_ACTIVITIES__ROOT_OA__OPERATIONALACTIVITY_11);
      testQuery(OA__SCENARIO_OES, OA__OPERATIONAL_ENTITIES__ENTITY_1, OA__OPERATIONAL_ENTITIES__ENTITY_3, 
              OA__OPERATIONAL_ENTITIES__ENTITY_5, OA__OPERATIONAL_ENTITIES__OPERATIONALACTOR_2, OA__OPERATIONAL_ENTITIES__OPERATIONALACTOR_4,
              OA__OPERATIONAL_ENTITIES__OPERATIONALACTOR_6, OA__OPERATIONAL_ENTITIES__OPERATIONALACTOR_7,
              OA__ROLES__ROLE_1, OA__ROLES__ROLE_2);
      testQuery(SA__SCENARIO_IS, SA__SYSTEM, SA__ACTORS__A_1, SA__ACTORS__A_2, SA__ACTORS__A_3, SA__ACTORS__A_4,
              SA__DATA__EI_SA_02, SA__INTERFACES__EI1);
      testQuery(SA__SCENARIO_OC1_FS, SA__ROOT_SF_SYSTEMFUNCTION_10,
              SA__ROOT_SF_SYSTEMFUNCTION_11, SA__ROOT_SF_SYSTEMFUNCTION_12, SA__ROOT_SF_SYSTEMFUNCTION_13);
      testQuery(SA__SCENARIO_OC1_ES_1, SA__SYSTEM, SA__ACTORS__A_1, SA__ACTORS__A_2, SA__ACTORS__A_3, SA__ACTORS__A_4);
      testQuery(SA__SCENARIO_CAPA1_ES_1, SA__SYSTEM);
      testQuery(PA__SCENARIO_ES_COPY, PA__PC1, PA__PC2, PA__PC3, PA__PC4, PA__PHYSICAL_SYSTEM);
      testQuery(EPBS_SCENARIO_CAPA1_IS, EPBS__COTSCI1, EPBS__PC2, EPBS__PC1, EPBS__INTERFACECI6, EPBS__SYSTEM, 
              LA__LOGICAL_EXCHANGE_ITEM_1);
      testQuery(EPBS_SCENARIO_CAPA1_IS_COPY, EPBS__COTSCI1, EPBS__PC2, EPBS__PC1, EPBS__INTERFACECI6, EPBS__SYSTEM, 
              LA__LOGICAL_EXCHANGE_ITEM_1);
  }
}
