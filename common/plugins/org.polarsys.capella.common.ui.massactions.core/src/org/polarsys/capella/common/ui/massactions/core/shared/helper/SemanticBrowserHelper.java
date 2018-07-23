/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.massactions.core.shared.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ui.toolkit.browser.category.CategoryRegistry;
import org.polarsys.capella.common.ui.toolkit.browser.category.ICategory;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.IBrowserContentProvider;

/**
 * A common elements helper, providing some utility methods.
 * 
 * @author Sandu Postaru
 *
 */
public class SemanticBrowserHelper {

  private SemanticBrowserHelper() {
    // Exists only to defeat instantiation.
  }

  /**
   * Extracts all the semantic browser categories for a given row object.
   * 
   * @param rowObject
   *          the row object.
   * @return all the semantic browser categories for the given row object.
   */
  public static Set<ICategory> getObjectCategories(EObject rowObject) {

    Set<ICategory> referencedCategories = CategoryRegistry.getInstance()
        .gatherCategories(IBrowserContentProvider.ID_REFERENCED_CP, rowObject);
    Set<ICategory> referencingCategories = CategoryRegistry.getInstance()
        .gatherCategories(IBrowserContentProvider.ID_REFERENCING_CP, rowObject);
    Set<ICategory> currentCategories = CategoryRegistry.getInstance()
        .gatherCategories(IBrowserContentProvider.ID_CURRENT_CP, rowObject);

    Set<ICategory> categories = new HashSet<>();
    categories.addAll(referencedCategories);
    categories.addAll(referencingCategories);
    categories.addAll(currentCategories);

    return categories;
  }

  /**
   * Extracts all the common semantic browser categories for a collection of elements.
   * 
   * @param data
   *          the collection of elements.
   * @return all the common semantic browser categories for the given elements.
   */
  public static Set<ICategory> getCommonObjectCategories(Collection<EObject> data) {

    if (data.isEmpty()) {
      return Collections.emptySet();
    }

    List<EObject> rowObjects = new ArrayList<>(data);

    EObject initialRowObject = rowObjects.get(0);
    Set<ICategory> commonCategories = getObjectCategories(initialRowObject);

    for (int i = 1; i < rowObjects.size(); i++) {
      EObject nextRowObject = rowObjects.get(i);
      Set<ICategory> nextCategories = getObjectCategories(nextRowObject);

      commonCategories.retainAll(nextCategories);
    }

    return commonCategories;
  }

}
