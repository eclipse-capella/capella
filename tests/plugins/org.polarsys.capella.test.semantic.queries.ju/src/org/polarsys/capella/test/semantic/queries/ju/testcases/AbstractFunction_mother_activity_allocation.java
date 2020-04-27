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
 * Test for <i>AbstractFunction_mother_activity_allocation</i> query:
 * 
 * <ul>
 * <li>A mother activity is an activity that has sub activity;</li>
 * <li>A mother activity is allocated to an entity/actor/role if and only if all
 * its sub activities are allocated to this entity/actor/role.</li>
 * </ul>
 * 
 */
public class AbstractFunction_mother_activity_allocation extends SemanticQueries {
	/**
	 * The Query under test.
	 */
	String QUERY = "org.polarsys.capella.core.semantic.queries.AbstractFunction_mother_activity_allocation";

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
		// Test 1: The activity has sub-activities but is allocated to an entity
		testQuery(OA__OPERATIONAL_ACTIVITIES__ROOT_OA__MOTHER_ALLOC_ENTITY_OK);

		// Test 2: The activity has sub-activities but is allocated to an actor
		testQuery(OA__OPERATIONAL_ACTIVITIES__ROOT_OA__MOTHER_ALLOC_ACTOR_OK);
		
		// Test 3: The activity has sub-activities that are all allocated to the same entity
		testQuery(OA__OPERATIONAL_ACTIVITIES__ROOT_OA__MOTHER_CHILD_ENTITY_OK, OA__OPERATIONAL_ENTITIES__ENTITY_1);
		
		// Test 4: The activity has sub-activities that are all allocated to the same actor
		testQuery(OA__OPERATIONAL_ACTIVITIES__ROOT_OA__MOTHER_CHILD_ACTOR_OK, OA__OPERATIONAL_ENTITIES__OPERATIONALACTOR_2);
		
		// Test 5: The activity has sub-activities that are all allocated to the same role in an entity
		testQuery(OA__OPERATIONAL_ACTIVITIES__ROOT_OA__MOTHER_ROLE_ACTOR_OK, OA__ROLES__ROLE_2);
		
		// Test 6: The activity has sub-activities that are all allocated to the same role in an actor
		testQuery(OA__OPERATIONAL_ACTIVITIES__ROOT_OA__MOTHER_ROLE_ENTITY_OK, OA__ROLES__ROLE_1);
	}

}
