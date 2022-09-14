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

public class OperationalCapability_ExternalReferencedOperationalProcesses extends SemanticQueries {
	String QUERY = "org.polarsys.capella.core.semantic.queries.OperationalCapabilityExternalReferencedOperationalProcesses";

	@Override
	protected String getQueryCategoryIdentifier() {
		return QUERY;
	}

	@Override
	public void test() throws Exception {
		testQuery(OA__OPERATIONAL_CAPABILITY1,OA__OPERATIONAL_PROCESS_OA7, OA__OPERATIONAL_PROCESS_2,OA__OPERATIONAL_PROCESS_OA8, OA__OPERATIONAL_PROCESS_OP1);
		testQuery(SA__CAPABILITIES__CAPABILITY_1);
		testQuery(LA__CAPABILITIES__CAPABILITY_1);
		testQuery(PA__CAPABILITIES__CAPABILITY_1);
		testQuery(EPBS__CAPABILITIES__CAPABILITY_2);
	}

  public static Test suite() {
    return new OperationalCapability_ExternalReferencedOperationalProcesses();
  }
}
