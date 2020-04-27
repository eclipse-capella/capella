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

public class CapabilityRealization_InvolvedFunctions extends SemanticQueries {
	String QUERY = "org.polarsys.capella.core.semantic.queries.AbstractCapabilityInvolvedFunctionsLA";

	@Override
	protected String getQueryCategoryIdentifier() {
		return QUERY;
	}

	@Override
	public void test() throws Exception {
		testQuery(LA__CAPABILITIES__CAPABILITY_1, LA__ROOT_LF__LOGICALFUNCTION_1, LA__ROOT_LF__LOGICALFUNCTION_2);
	}

  public static Test suite() {
    return new CapabilityRealization_InvolvedFunctions();
  }
}
