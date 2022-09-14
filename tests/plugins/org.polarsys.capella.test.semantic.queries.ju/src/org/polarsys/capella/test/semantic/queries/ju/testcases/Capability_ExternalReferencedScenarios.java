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

public class Capability_ExternalReferencedScenarios extends SemanticQueries {
  String QUERY = "org.polarsys.capella.core.semantic.queries.AbstractCapabilityExternalReferencedScenarios";

  @Override
  protected String getQueryCategoryIdentifier() {
    return QUERY;
  }

  @Override
  public void test() throws Exception {
    testQuery(OA__OPERATIONAL_CAPABILITY1, OA__SCENARIO_OAS_OC2);
    testQuery(SA__CAPABILITIES_OPERATIONAL_CAPABILITY_1, SA__SCENARIO_FS_OCAPA2, SA__SCENARIO_FS_OCAPA1);
    testQuery(LA__CAPABILITIES__OPERATIONAL_CAPABILITY_1, LA__SCENARIO_OPCAPA_2);
    testQuery(PA__CAPABILITIES__CAPABILITY_2, PA__SCENARIO_ES, PA__SCENARIO_ES_COPY);
    testQuery(EPBS__CAPABILITIES__CAPABILITY_2, EPBS_SCENARIO_CAPA1_IS, EPBS_SCENARIO_CAPA1_IS_COPY);
  }

  public static Test suite() {
    return new Capability_ExternalReferencedScenarios();
  }
}
