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

/**
 * Test for <i>ComponentReferencingScenarioCompExc</i> query:
 * 
 * 
 */
public class AbstractFunction_ReferencingScenarios extends SemanticQueries {
	/**
	 * The Query under test.
	 */
	String QUERY = "org.polarsys.capella.core.semantic.queries.Component.ComponentReferencingScenarioCompExc";

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
		
		testQuery(PA__FUNCTION_1,
				PA__FS_CAPABILITY1,//Referenced as Instance Role
				PA__ES_CAPABILITY1_COPY, //Referenced as State Fragment
				PA__ES_CAPABILITY1, //Referenced as State Fragment
				PA__ES_SCENARIO_INVOKING_FUNCTIONS); //Referenced as target of FunctionalExchange invoked in Scenario
		
		
		testQuery(PA__FUNCTION_2,
				PA__FS_CAPABILITY1,//Referenced as Instance Role
				PA__ES_CAPABILITY1_COPY, //Referenced as State Fragment
				PA__ES_CAPABILITY1, //Referenced as State Fragment
				PA__ES_SCENARIO_INVOKING_FUNCTIONS); //Referenced as source of FunctionalExchange invoked in Scenario
	}

}
