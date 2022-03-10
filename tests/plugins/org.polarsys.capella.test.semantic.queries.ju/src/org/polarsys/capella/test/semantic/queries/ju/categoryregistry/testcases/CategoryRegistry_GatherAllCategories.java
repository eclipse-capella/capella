/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.semantic.queries.ju.categoryregistry.testcases;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.ui.toolkit.browser.category.CategoryRegistry;
import org.polarsys.capella.common.ui.toolkit.browser.category.ICategory;
import org.polarsys.capella.test.framework.helpers.EObjectHelper;
import org.polarsys.capella.test.semantic.queries.ju.model.SemanticQueries;

public class CategoryRegistry_GatherAllCategories extends SemanticQueries {
  List<String> QUERIES = List.of("org.polarsys.capella.core.semantic.queries.FunctionFunctionalChains",
      "org.polarsys.capella.core.semantic.queries.AbstractFunction_parentFunction",
      "org.polarsys.capella.core.semantic.queries.PhysicalFunction_outFlowPorts",
      "org.polarsys.capella.core.semantic.queries.AbstractFunction_outGoingInteraction_pf");

  @Override
  protected String getQueryCategoryIdentifier() {
    return "";
  }

  @Override
  public void test() throws Exception {
    IModel model = getTestModel(getRequiredTestModels().iterator().next());
    EObject testObject = EObjectHelper.getObject(model, PA__FUNCTION_1);

    Set<ICategory> gatheredCategories = CategoryRegistry.getInstance().gatherCategories(testObject);
    assertTrue(
        gatheredCategories.stream().map(cat -> cat.getCategoryId()).collect(Collectors.toList()).containsAll(QUERIES));
    assertTrue(gatheredCategories.stream().filter(cat -> cat.isTechnical()).count() == 0);
  }
}
