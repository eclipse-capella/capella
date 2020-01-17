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
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ui.toolkit.browser.category.CategoryRegistry;
import org.polarsys.capella.common.ui.toolkit.browser.category.ICategory;

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
    CategoryRegistry categoryRegistry = CategoryRegistry.getInstance();
    

    EObject initialRowObject = rowObjects.get(0);
    Set<ICategory> commonCategories = categoryRegistry.gatherCategories(initialRowObject);

    for (int i = 1; i < rowObjects.size(); i++) {
      EObject nextRowObject = rowObjects.get(i);
      Set<ICategory> nextCategories = categoryRegistry.gatherCategories(nextRowObject);

      commonCategories.retainAll(nextCategories);
    }

    return commonCategories;
  }

}
