/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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

/**
 * Test for <i>StateTransition realized StateTransition</i> query
 */
public class StateTransition_realizedStateTransition extends SemanticQueries {
	String QUERY = "org.polarsys.capella.core.semantic.queries.basic.queries.StateTransitionRealizedStateTransition";

	/**
	 * @return the query category identifier.
	 */
	@Override
	protected String getQueryCategoryIdentifier() {
		return QUERY;
	}

	/**
	 * Test method.
	 */
	@Override
	public void test() throws Exception {
		testQuery(PA_MODE_1_TO_MODE_2, LA_MODE_1_TO_MODE_2);
	}

}
