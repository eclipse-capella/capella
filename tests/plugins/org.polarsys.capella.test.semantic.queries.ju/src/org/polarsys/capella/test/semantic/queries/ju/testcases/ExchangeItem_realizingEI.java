/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
 * Test for <i>ExchangeItem realizing EI</i> query
 */
public class ExchangeItem_realizingEI extends SemanticQueries {
	/**
	 * The Query under test.
	 */
	String QUERY = "org.polarsys.capella.core.semantic.queries.ExchangeItem_realizingEI";

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
		testQuery(SA__INTERFACES__EI_SA_01, LA__INTERFACES__EI_LA_01, PA__INTERFACES__EI_PA_01);

		testQuery(SA__DATA__EI_SA_02, LA__INTERFACES__EI_LA_01, PA__DATA__EI_PA_02);

		testQuery(LA__INTERFACES__EI_LA_01, PA__INTERFACES__EI_PA_01);
		
		testQuery(PA__INTERFACES__EI_PA_01);

	}

}
