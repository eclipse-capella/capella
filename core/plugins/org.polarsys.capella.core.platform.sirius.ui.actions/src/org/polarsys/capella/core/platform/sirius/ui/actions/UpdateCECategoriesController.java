/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.business.queries.fa.ComponentExchange_Categories;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeCategory;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.oa.CommunicationMean;

public class UpdateCECategoriesController extends UpdateCategoriesController {
  /**
   * {@inheritDoc}
   */
  @Override
  public void updateCategories(List<EObject> selectedElements, List<EObject> categoriesToAdd,
      List<EObject> categoriesToRemove) {
    for (EObject e : selectedElements) {
      if (e instanceof ComponentExchange) {
        for (EObject removed : categoriesToRemove) {
          ((ComponentExchangeCategory) removed).getExchanges().remove(e);
        }
        for (EObject added : categoriesToAdd) {
          ((ComponentExchangeCategory) added).getExchanges().add((ComponentExchange) e);
        }
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
    for (EObject fe : element) {
      IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(fe.eClass(),
          FaPackage.Literals.COMPONENT_EXCHANGE__CATEGORIES);
      if (query != null) {
        result.addAll(query.getAvailableElements(fe));
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
