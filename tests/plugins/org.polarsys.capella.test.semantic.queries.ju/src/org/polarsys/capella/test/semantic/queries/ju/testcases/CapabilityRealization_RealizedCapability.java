/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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

public class CapabilityRealization_RealizedCapability extends SemanticQueries {
	String QUERY = "org.polarsys.capella.core.semantic.queries.basic.queries.CRRealizedCapability";

	@Override
	protected String getQueryCategoryIdentifier() {
		return QUERY;
	}

	@Override
	public void test() throws Exception {
		testQuery(LA__CAPABILITIES__CAPABILITY_1, SA__CAPABILITIES__CAPABILITY_1);
	}

  public static Test suite() {
    return new CapabilityRealization_RealizedCapability();
  }
}
