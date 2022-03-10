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
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.semantic.queries.ju.testcases;

import org.polarsys.capella.test.semantic.queries.ju.model.SemanticQueries;

public class OperationalProcessFlatOperationalActivities extends SemanticQueries {
  String QUERY = "org.polarsys.capella.core.semantic.queries.basic.queries.operationalProcess.flatOperationalActivities";

  @Override
  protected String getQueryCategoryIdentifier() {
    return QUERY;
  }

  @Override
  public void test() throws Exception {
    testQuery(OA__OPERATIONAL_PROCESS_2, OA__OPERATIONAL_ACTIVITY_9, OA__OPERATIONAL_ACTIVITY_10, OA__OPERATIONAL_ACTIVITY_11, OA__OPERATIONAL_ACTIVITY_12, OA__OPERATIONAL_ACTIVITY_13);
  }
}
