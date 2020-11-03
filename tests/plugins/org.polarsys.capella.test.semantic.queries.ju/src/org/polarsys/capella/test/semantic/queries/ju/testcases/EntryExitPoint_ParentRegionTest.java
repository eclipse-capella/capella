/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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
 * Test for <i>PropertyValueGroup_applying_valued_element</i> query
 */
public class EntryExitPoint_ParentRegionTest extends SemanticQueries {
	/**
	 * The Query under test.
	 */
	String QUERY = "org.polarsys.capella.core.semantic.queries.ParentRegion";

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
	  
    testQuery(SA__SYSTEM__SYSTEM_STATE_MACHINE__DEFAULT_REGION__STATE_1__REGION_2__ENTRYPOINT_1,
        SA__SYSTEM__SYSTEM_STATE_MACHINE__DEFAULT_REGION__STATE_1__REGION_2);
    
    testQuery(SA__SYSTEM__SYSTEM_STATE_MACHINE__DEFAULT_REGION__STATE_1__REGION_2__EXITPOINT_2,
        SA__SYSTEM__SYSTEM_STATE_MACHINE__DEFAULT_REGION__STATE_1__REGION_2);
  }

}
