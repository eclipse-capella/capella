/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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

/**
 * Test for <i>AbstractFunction_mother_function_allocation</i> query:
 * 
 * <ul>
 * <li>A mother function is a function that has sub functions;</li>
 * <li>A mother function is allocated to a component if and only if all its sub
 * functions are allocated to this component.</li>
 * </ul>
 * 
 */
public class AbstractFunction_mother_function_allocation extends SemanticQueries {
	/**
	 * The Query under test.
	 */
	String QUERY = "org.polarsys.capella.core.semantic.queries.AbstractFunction_mother_function_allocation";

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
		// Test 1: The function has sub-functions and those are allocated to the
		// same component
		testQuery(SA__ROOT_SF__MOTHER_ACTOR_OK, SA__ACTORS__A_1);

		// Test 2: The function has sub-functions but those are allocated to
		// different components
		testQuery(SA__ROOT_SF__MOTHER_ALLOC_KO);

		// Test 3: The function has sub-functions but is allocated to a
		// component
		testQuery(SA__ROOT_SF__MOTHER_ALLOC_ACTOR_OK);
	}

}
