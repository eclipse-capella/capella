/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.transition.ju.testcases.options;

import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
import org.polarsys.capella.core.transition.common.handlers.merge.CategorySet;
import org.polarsys.capella.core.transition.common.ui.handlers.merge.MergeCategoryManager;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class MergeCategoryManagerTest extends BasicTestCase {

  public void test() {
    MergeCategoryManager manager = new MergeCategoryManager(null, null);
    manager.addCategorySet(new CategorySet("set.1.id", "set.1.text", "set.1.desc"));

    // check that category set is accessible by id, not by text
    assertNotNull(manager.getCategorySet("set.1.id"));
    assertNull(manager.getCategorySet("set.1.text"));

    // check that category added to a set is properly connected to it
    CategoryFilter filter = new CategoryFilter(null, "cat.1", "name", "desc", null);
    filter.setCategorySet("set.1.id");
    manager.addCategory(filter);
    assertNotNull(manager.getCategory("cat.1"));
    assertTrue(manager.getCategory("cat.1").getParent().equals(manager.getCategorySet("set.1.id")));
    assertTrue(!manager.getCategorySet("set.1.id").getChildren().isEmpty());

    // check that category changed of a category is properly connected to it
    filter.setCategorySet("set.2.id");
    manager.addCategory(filter);
    assertTrue(manager.getCategories().size() == 1);
    assertNotNull(manager.getCategory("cat.1"));
    assertTrue(manager.getCategory("cat.1").getParent().equals(manager.getCategorySet("set.2.id")));
    assertTrue(!manager.getCategorySet("set.2.id").getChildren().isEmpty());
    assertTrue(manager.getCategorySet("set.1.id").getChildren().isEmpty());

  }
  
}
