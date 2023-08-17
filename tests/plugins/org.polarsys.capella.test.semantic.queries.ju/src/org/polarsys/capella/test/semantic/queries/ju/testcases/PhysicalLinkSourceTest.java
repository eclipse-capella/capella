/*******************************************************************************
* Copyright (c) 2023 THALES GLOBAL SERVICES.
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

public class PhysicalLinkSourceTest extends SemanticQueries {
  private final String QUERY = "org.polarsys.capella.core.semantic.queries.PhysicalLinkSource";

  @Override
  protected String getQueryCategoryIdentifier() {
    return QUERY;
  }

  @Override
  public void test() throws Exception {
    testQueryIncludingItemQueries(PA__PHYSICAL_LINK_1, PA__PHYSICAL_PORT_1, PA__PC1);
  }

}
