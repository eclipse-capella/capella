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

public class OperationalProcess_ExternalReferencedOperationalProcesses extends SemanticQueries {
  String QUERY = "org.polarsys.capella.core.semantic.queries.basic.queries.OperationalProcessExternalReferencedOperationalProcesses";

  @Override
  protected String getQueryCategoryIdentifier() {
    return QUERY;
  }

  @Override
  public void test() throws Exception {
    testQueryOnlyItemQueries(OA__OPERATIONAL_PROCESS_OA8, OA__OPERATIONAL_PROCESS_OP1, OA__OPERATIONAL_PROCESS_2);
    testQueryOnlyItemQueries(SA__FUNCTIONAL_CHAIN_SF8);
    testQueryOnlyItemQueries(LA__FUNCTIONAL_CHAIN_LF2);
    testQueryOnlyItemQueries(PA__FUNCTIONAL_CHAIN_PF2);
    testQueryOnlyItemQueries(EPBS__FUNCTIONAL_CHAIN_1);
  }

  public static Test suite() {
    return new OperationalProcess_ExternalReferencedOperationalProcesses();
  }
}
