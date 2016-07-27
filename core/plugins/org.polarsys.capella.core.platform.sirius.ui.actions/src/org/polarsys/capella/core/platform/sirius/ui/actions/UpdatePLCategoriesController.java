/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.platform.sirius.ui.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.business.queries.cs.PhysicalLink_Categories;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkCategory;

public class UpdatePLCategoriesController extends UpdateCategoriesController {
  /**
   * {@inheritDoc}
   */
  @Override
  public void updateCategories(List<EObject> selectedElements, List<EObject> categoriesToAdd, List<EObject> categoriesToRemove) {
    for (EObject e : selectedElements) {
      if (e instanceof PhysicalLink) {
        EList<PhysicalLinkCategory> categories = ((PhysicalLink) e).getCategories();
        categories.addAll((Collection<? extends PhysicalLinkCategory>) categoriesToAdd);
        categories.removeAll(categoriesToRemove);
      }
    }
    logResults(Messages.UpdatePLCategories_add_msg, categoriesToAdd);
    logResults(Messages.UpdatePLCategories_remove_msg, categoriesToRemove);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<EObject> getAvailableCategories(List<EObject> element) {
    List<EObject> result = new ArrayList<EObject>();
    // get all CE categories
    for (EObject e : element) {
      if (e instanceof PhysicalLink) {
        List<EObject> categories = new PhysicalLink_Categories().getAvailableElements((PhysicalLink) e);
        result.addAll(categories);
      }
    }
    return result;

  }

  /**
   * Compute intersection of all PL assigned categories {@inheritDoc}
   */
  @Override
  public List<EObject> getCommonCategories(List<EObject> selection) {
    List<EObject> result = new ArrayList<EObject>();
    if (selection.isEmpty()) {
      return result;
    }
    EObject first = selection.get(0);
    if (first instanceof PhysicalLink) {
      result.addAll(((PhysicalLink) first).getCategories());
    }
    for (EObject eObject : selection) {
      if (eObject instanceof PhysicalLink) {
        result.retainAll(((PhysicalLink) eObject).getCategories());
      }
    }

    return result;
  }
}
