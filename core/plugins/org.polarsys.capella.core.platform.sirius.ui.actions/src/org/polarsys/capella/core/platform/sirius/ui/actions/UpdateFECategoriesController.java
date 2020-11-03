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

package org.polarsys.capella.core.platform.sirius.ui.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.business.queries.fa.FunctionalExchange_Categories;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalExchange;

public class UpdateFECategoriesController extends UpdateCategoriesController {

  @SuppressWarnings("hiding")
  public static final String MESSAGE = "Update functional exchange categories"; //$NON-NLS-1$

  /**
   * {@inheritDoc}
   */
  @Override
  public void updateCategories(List<EObject> selectedElements, List<EObject> categoriesToAdd,
      List<EObject> categoriesToRemove) {
    for (EObject e : selectedElements) {
      if (e instanceof FunctionalExchange) {
        for (EObject removed : categoriesToRemove) {
          ((ExchangeCategory) removed).getExchanges().remove(e);
        }
        for (EObject added : categoriesToAdd) {
          ((ExchangeCategory) added).getExchanges().add((FunctionalExchange) e);
        }
      }
    }
    logResults(Messages.UpdateFECategories_add_msg, categoriesToAdd);
    logResults(Messages.UpdateFECategories_remove_msg, categoriesToRemove);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<EObject> getAvailableCategories(List<EObject> element) {
    List<EObject> result = new ArrayList<EObject>();
    for (EObject fe : element) {
      IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(fe.eClass(),
          FaPackage.Literals.FUNCTIONAL_EXCHANGE__CATEGORIES);
      if (query != null) {
        result.addAll(query.getAvailableElements(fe));
      }
    }
    return result;

  }

  /**
   * Compute intersection of all FE assigned categories {@inheritDoc}
   */
  @Override
  public List<EObject> getCommonCategories(List<EObject> selection) {
    List<EObject> result = new ArrayList<EObject>();
    if (selection.isEmpty()) {
      return result;
    }
    EObject first = selection.get(0);
    if (first instanceof FunctionalExchange) {
      result.addAll(((FunctionalExchange) first).getCategories());
    }
    for (EObject eObject : selection) {
      if (eObject instanceof FunctionalExchange) {
        result.retainAll(((FunctionalExchange) eObject).getCategories());
      }
    }

    return result;
  }
}
