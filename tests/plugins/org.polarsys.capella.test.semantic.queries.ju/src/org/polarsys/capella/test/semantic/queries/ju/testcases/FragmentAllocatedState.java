/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.semantic.queries.ju.testcases;

import org.polarsys.capella.test.semantic.queries.ju.model.SemanticQueries;

public class FragmentAllocatedState extends SemanticQueries {
  String QUERY = "org.polarsys.capella.core.semantic.queries.StateFragmentRelatedStates";

  @Override
  protected String getQueryCategoryIdentifier() {
    return QUERY;
  }

  @Override
  public void test() throws Exception {
    testQuery(SA__STATE_FRAGMENT_2, SA__SYSTEM_SYSTEM_STATE_MACHINE_FRAGMENT_ALLOCATED_STATE);
  }
}
