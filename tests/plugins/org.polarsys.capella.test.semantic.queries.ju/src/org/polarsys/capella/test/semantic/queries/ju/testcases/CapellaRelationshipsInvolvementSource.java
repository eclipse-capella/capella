/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales Global Services - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.semantic.queries.ju.testcases;

import org.polarsys.capella.test.semantic.queries.ju.model.SemanticQueries;

public class CapellaRelationshipsInvolvementSource extends SemanticQueries {

  String QUERY = "org.polarsys.capella.core.semantic.queries.CapellaRelationshipsInvolvementSource";

  @Override
  protected String getQueryCategoryIdentifier() {
    return QUERY;
  }

  @Override
  public void test() throws Exception {
    testQuery(OA_ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT_CAP3_ACTOR7, OA__OPERATIONAL_CAPABILITY3);
  }
}
