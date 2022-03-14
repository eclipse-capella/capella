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

/**
 * Test for <i>StateFragment related functions</i> query
 */
public class StateFragment_RelatedFunctions extends SemanticQueries {
	String QUERY = "org.polarsys.capella.core.semantic.queries.StateFragmentRelatedFunctions";

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
		testQuery(SA__STATE_FRAGMENT_3_MODE_1_1_1, SA__ROOT_SF_SYSTEMFUNCTION_12);
	}

}
