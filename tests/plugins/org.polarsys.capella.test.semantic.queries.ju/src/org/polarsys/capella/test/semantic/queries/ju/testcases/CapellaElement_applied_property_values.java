/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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

/**
 * Test for <i>CapellaElement_applied_property_values</i> query 
 */
public class CapellaElement_applied_property_values extends SemanticQueries {
	/**
	 * The Query under test.
	 */
	String QUERY = "org.polarsys.capella.core.semantic.queries.CapellaElement_applied_property_values";

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
		testQuery(LA__LOGICAL_ACTORS__LA_1, LA__PROPERTYVALUEPKG__BOOLEANPROPERTYVALUE);
	}

}
