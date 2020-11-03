/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.semantic.queries.ju.testcases;

import org.polarsys.capella.test.semantic.queries.ju.model.SemanticQueries;

public class RelationshipGeneralizationSource extends SemanticQueries {
  String QUERY = "org.polarsys.capella.core.semantic.queries.CapellaRelationshipsGeneralizationSource";

  @Override
  protected String getQueryCategoryIdentifier() {
    return QUERY;
  }

  @Override
  public void test() throws Exception {
    testQuery(LA__DATA_GENERALIZATION_CLASS_1_CLASS_2, LA__DATA_CLASS_1);
  }
}
