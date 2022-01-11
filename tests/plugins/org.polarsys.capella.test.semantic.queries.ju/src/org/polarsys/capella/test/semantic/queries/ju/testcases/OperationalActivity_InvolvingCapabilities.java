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

public class OperationalActivity_InvolvingCapabilities extends SemanticQueries {
  String QUERY = "org.polarsys.capella.core.semantic.queries.OperationalActivityInvolvingCapabilities";

  @Override
  protected String getQueryCategoryIdentifier() {
    return QUERY;
  }

  @Override
  public void test() throws Exception {
    testQuery(OA__OPERATIONAL_ACTIVITIES__ROOT_OA__MOTHER_ROLE_ACTOR_OK__OPERATIONALACTIVITY_5,
        OA__OPERATIONAL_CAPABILITY1, OA__OPERATIONAL_CAPABILITY2);
    testQuery(OA__OPERATIONAL_ACTIVITIES__ROOT_OA__MOTHER_ROLE_ENTITY_OK__OPERATIONALACTIVITY_7,
        OA__OPERATIONAL_CAPABILITY1, OA__OPERATIONAL_CAPABILITY2);
    testQuery(OA__OPERATIONAL_ACTIVITY_OA8, OA__OPERATIONAL_CAPABILITY1);
    testQuery(OA__OPERATIONAL_ACTIVITIES__ROOT_OA__MOTHER_CHILD_ACTOR_OK__OPERATIONALACTIVITY_1);
  }

  public static Test suite() {
    return new OperationalActivity_InvolvingCapabilities();
  }
}
