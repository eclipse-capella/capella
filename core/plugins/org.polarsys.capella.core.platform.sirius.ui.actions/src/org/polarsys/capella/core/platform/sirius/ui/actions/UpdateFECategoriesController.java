/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.business.queries.fa.FunctionalExchange_Categories;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.fa.FunctionalExchange;

public class UpdateFECategoriesController extends UpdateCategoriesController {

  @SuppressWarnings("hiding")
  public static final String MESSAGE = "Update functional exchange categories"; //$NON-NLS-1$

  /**
   * {@inheritDoc}
   */
  @Override
  public void updateCategories(List<EObject> selectedElements_p, List<EObject> categoriesToAdd_p, List<EObject> categoriesToRemove_p) {
    for (EObject e : selectedElements_p) {
      if (e instanceof FunctionalExchange) {
        EList<ExchangeCategory> categories = ((FunctionalExchange) e).getCategories();
        categories.addAll((Collection<? extends ExchangeCategory>) categoriesToAdd_p);
        categories.removeAll(categoriesToRemove_p);
      }
    }
    logResults(Messages.UpdateFECategories_add_msg, categoriesToAdd_p);
    logResults(Messages.UpdateFECategories_remove_msg, categoriesToRemove_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<EObject> getAvailableCategories(List<EObject> element_p) {
    List<EObject> result = new ArrayList<EObject>();
    // get all FE categories
    for (EObject fe : element_p) {
      if (fe instanceof FunctionalExchange) {
        List<CapellaElement> feCategories = new FunctionalExchange_Categories().getAvailableElements((FunctionalExchange) fe);
        result.addAll(feCategories);
      }
    }
    return result;

  }

  /**
   * Compute intersection of all FE assigned categories {@inheritDoc}
   */
  @Override
  public List<EObject> getCommonCategories(List<EObject> selection_p) {
    List<EObject> result = new ArrayList<EObject>();
    if (selection_p.isEmpty()) {
      return result;
    }
    EObject first = selection_p.get(0);
    if (first instanceof FunctionalExchange) {
      result.addAll(((FunctionalExchange) first).getCategories());
    }
    for (EObject eObject : selection_p) {
      if (eObject instanceof FunctionalExchange) {
        result.retainAll(((FunctionalExchange) eObject).getCategories());
      }
    }

    return result;
  }
}
