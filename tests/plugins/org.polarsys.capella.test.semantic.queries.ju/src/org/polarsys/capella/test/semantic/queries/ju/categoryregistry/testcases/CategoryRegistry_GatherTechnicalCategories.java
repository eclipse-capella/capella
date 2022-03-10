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

import java.util.Optional;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.ui.toolkit.browser.category.CategoryRegistry;
import org.polarsys.capella.common.ui.toolkit.browser.category.ICategory;
import org.polarsys.capella.test.framework.helpers.EObjectHelper;
import org.polarsys.capella.test.semantic.queries.ju.model.SemanticQueries;

public class CategoryRegistry_GatherTechnicalCategories extends SemanticQueries {
    String QUERY = "org.polarsys.capella.core.semantic.queries.technical.queries.internalLinks";

    @Override
    protected String getQueryCategoryIdentifier() {
        return QUERY;
    }

    @Override
    public void test() throws Exception {

        IModel model = getTestModel(getRequiredTestModels().iterator().next());
        EObject testObject = EObjectHelper.getObject(model, PA__FUNCTIONAL_CHAIN);

        Set<ICategory> categories = CategoryRegistry.getInstance().gatherFilteredCategories(testObject, f -> f.isTechnical());
        Optional<ICategory> found = categories.stream().filter(cat -> cat.getCategoryId().equals(QUERY)).findFirst();
        assertTrue(found.isPresent());
        assertEquals(QUERY, found.get().getCategoryId());
    }
}
