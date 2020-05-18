/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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

public class CapellaRelationshipsAbstractCapabilityIncludeSource extends SemanticQueries {

  String QUERY = "org.polarsys.capella.core.semantic.queries.CapellaRelationshipsAbstractCapabilityIncludeSource";

  @Override
  protected String getQueryCategoryIdentifier() {
    return QUERY;
  }

  @Override
  public void test() throws Exception {
    testQuery(OA_ABSTRACT_CAPABILITY_INCLUDE_CAP1_CAP3, OA__OPERATIONAL_CAPABILITY1);
  }
}
