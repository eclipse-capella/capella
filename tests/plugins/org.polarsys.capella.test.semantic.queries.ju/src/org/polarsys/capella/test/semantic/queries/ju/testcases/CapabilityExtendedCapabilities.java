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

public class CapabilityExtendedCapabilities extends SemanticQueries {
  String QUERY = "org.polarsys.capella.core.semantic.queries.Capability_extendedCapabilities";

  @Override
  protected String getQueryCategoryIdentifier() {
    return QUERY;
  }

  @Override
  public void test() throws Exception {
    testQuery(OA__OPERATIONAL_CAPABILITY1, OA__OPERATIONAL_CAPABILITY2, OA__OPERATIONAL_CAPABILITY4);
  }
}
