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
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.semantic.queries.ju.testcases;

import org.polarsys.capella.test.semantic.queries.ju.model.SemanticQueries;

public class PhysicalPathInvolvedPhysicalLinks extends SemanticQueries {
  String QUERY = "org.polarsys.capella.core.semantic.queries.PhysicalPath_PhysicalLinks";

  @Override
  protected String getQueryCategoryIdentifier() {
    return QUERY;
  }

  @Override
  public void test() throws Exception {
      testQueryIncludingItemQueries(PA__PHYSICAL_PATH, PA__PL2, PA__PL3, PA__PL4, PA__PC5, PA__PC6, PA__PC7, PA__PC8);
  }
}
