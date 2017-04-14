/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
