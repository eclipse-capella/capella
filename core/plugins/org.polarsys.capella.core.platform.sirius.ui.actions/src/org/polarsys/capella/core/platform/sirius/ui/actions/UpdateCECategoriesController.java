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
import org.polarsys.capella.core.business.queries.fa.ComponentExchange_Categories;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeCategory;
import org.polarsys.capella.core.data.oa.CommunicationMean;

public class UpdateCECategoriesController extends UpdateCategoriesController {
  /**
   * {@inheritDoc}
   */
  @Override
  public void updateCategories(List<EObject> selectedElements, List<EObject> categoriesToAdd, List<EObject> categoriesToRemove) {
    for (EObject e : selectedElements) {
      if (e instanceof ComponentExchange) {
        EList<ComponentExchangeCategory> categories = ((ComponentExchange) e).getCategories();
        categories.addAll((Collection<? extends ComponentExchangeCategory>) categoriesToAdd);
        categories.removeAll(categoriesToRemove);
      }
    }
    if (!selectedElements.isEmpty() && (selectedElements.get(0) instanceof CommunicationMean)) {
      logResults(Messages.UpdateCMCategories_add_msg, categoriesToAdd);
      logResults(Messages.UpdateCMCategories_remove_msg, categoriesToRemove);

    } else {
      logResults(Messages.UpdateCECategories_add_msg, categoriesToAdd);
      logResults(Messages.UpdateCECategories_remove_msg, categoriesToRemove);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<EObject> getAvailableCategories(List<EObject> element) {
    List<EObject> result = new ArrayList<EObject>();
    // get all CE categories
    for (EObject fe : element) {
      if (fe instanceof ComponentExchange) {
        List<EObject> categories = new ComponentExchange_Categories().getAvailableElements((ComponentExchange) fe);
        result.addAll(categories);
      }
    }
    return result;

  }

  /**
   * Compute intersection of all CE assigned categories {@inheritDoc}
   */
  @Override
  public List<EObject> getCommonCategories(List<EObject> selection) {
    List<EObject> result = new ArrayList<EObject>();
    if (selection.isEmpty()) {
      return result;
    }
    EObject first = selection.get(0);
    if (first instanceof ComponentExchange) {
      result.addAll(((ComponentExchange) first).getCategories());
    }
    for (EObject eObject : selection) {
      if (eObject instanceof ComponentExchange) {
        result.retainAll(((ComponentExchange) eObject).getCategories());
      }
    }

    return result;
  }
}
