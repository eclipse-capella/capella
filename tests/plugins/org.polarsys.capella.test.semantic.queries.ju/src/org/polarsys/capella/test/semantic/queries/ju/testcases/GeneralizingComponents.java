/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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

public class GeneralizingComponents extends SemanticQueries {
  String QUERY = "org.polarsys.capella.core.semantic.queries.GeneralizableElementAllSubGC";

  @Override
  protected String getQueryCategoryIdentifier() {
    return QUERY;
  }

  @Override
  public void test() throws Exception {

    testQuery(LA__LOGICAL_ACTORS__LA_3);

    testQuery(LA__LOGICAL_ACTORS__LA_4, LA__LOGICAL_ACTORS__LA_3);

    testQuery(LA__LOGICAL_ACTORS__LA_5,LA__LOGICAL_ACTORS__LA_4, LA__LOGICAL_ACTORS__LA_3);
  }

}
