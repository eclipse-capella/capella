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

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.fa.FunctionalExchange;

public class CreateFECategoriesController extends CreateCategoriesController {

  /**
   * {@inheritDoc}
   */
  @Override
  public void createAndAttachCategory(List<EObject> selection, String categoryNameToUse) {

    List<EClass> funcPkgEClass = Collections.singletonList(FaPackage.eINSTANCE.getFunctionPkg());

    EObject categoryContainer = getBestContainerForCategory(selection, funcPkgEClass);

    if (isNullOrNotInstanceOf(categoryContainer, funcPkgEClass)) {
      return;
    }
    FunctionPkg pkgContainer = (FunctionPkg) categoryContainer;

    ExchangeCategory exchangeCategory = createCategory(pkgContainer);

    // Get category name
    String categoryName = null;
    // Use given name if not null, else ask user
    if (categoryNameToUse != null) {
      categoryName = categoryNameToUse;
    } else {
      categoryName = askCategoryName(exchangeCategory.getName());
    }
    
    if (categoryName != null) {
      exchangeCategory.setName(categoryName);
      pkgContainer.getOwnedCategories().add(exchangeCategory);
      
      for (EObject fe : selection) {
        if (fe instanceof FunctionalExchange) {
          exchangeCategory.getExchanges().add((FunctionalExchange) fe);
        }
      }
      logResults(Messages.CreateFECategoriesController_creation_msg, exchangeCategory);
    }
  }

  @Override
  protected ExchangeCategory createCategory(EObject container) {
    ExchangeCategory exchangeCategory = FaFactory.eINSTANCE.createExchangeCategory();
    EReference feature = FaPackage.eINSTANCE.getFunctionPkg_OwnedCategories();
    String defaultName = EcoreUtil2.getUniqueName(exchangeCategory, container, feature, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME,
        Messages.CreateFECategoriesController_prefix);
    exchangeCategory.setName(defaultName);
    return exchangeCategory;
  }

}
