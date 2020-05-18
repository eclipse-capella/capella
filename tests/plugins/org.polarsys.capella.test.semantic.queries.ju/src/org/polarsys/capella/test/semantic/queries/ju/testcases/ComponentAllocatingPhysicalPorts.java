/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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

public class ComponentAllocatingPhysicalPorts extends SemanticQueries {
  String QUERY = "org.polarsys.capella.core.semantic.queries.basic.queries.ComponentPortAllocatingPhysicalPorts";

  @Override
  protected String getQueryCategoryIdentifier() {
    return QUERY;
  }

  @Override
  public void test() throws Exception {

    testQuery(PA__COMPONENT_PORT_1, PA__PHYSICAL_PORT_1);

    testQuery(PA__COMPONENT_PORT_2, PA__PHYSICAL_PORT_2);

    testQuery(PA__COMPONENT_PORT_3, PA__PHYSICAL_PORT_2);
  }
}
