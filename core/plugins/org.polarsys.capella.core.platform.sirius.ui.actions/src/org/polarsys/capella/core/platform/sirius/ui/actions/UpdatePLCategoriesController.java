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
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.business.queries.cs.PhysicalLink_Categories;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkCategory;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeCategory;
import org.polarsys.capella.core.data.fa.FaPackage;

public class UpdatePLCategoriesController extends UpdateCategoriesController {
  /**
   * {@inheritDoc}
   */
  @Override
  public void updateCategories(List<EObject> selectedElements, List<EObject> categoriesToAdd,
      List<EObject> categoriesToRemove) {
    for (EObject e : selectedElements) {
      if (e instanceof PhysicalLink) {
        for (EObject removed : categoriesToRemove) {
          ((PhysicalLinkCategory) removed).getLinks().remove(e);
        }
        for (EObject added : categoriesToAdd) {
          ((PhysicalLinkCategory) added).getLinks().add((PhysicalLink) e);
        }
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
    for (EObject e : element) {
      IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(e.eClass(),
          CsPackage.Literals.PHYSICAL_LINK__CATEGORIES);
      if (query != null) {
        result.addAll(query.getAvailableElements(e));
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
