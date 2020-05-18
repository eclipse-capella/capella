/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeCategory;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FaPackage;

public class CreateCECategoriesController extends CreateCategoriesController {

  /**
   * {@inheritDoc}
   */
  @Override
  public void createAndAttachCategory(List<EObject> selection, String categoryNameToUse) {
    List<EClass> containerEClasses = Arrays.asList(FaPackage.eINSTANCE.getAbstractFunctionalBlock(), CsPackage.eINSTANCE.getComponentPkg());
    EObject categoryContainer = getBestContainerForCategory(selection, containerEClasses);
    if (isNullOrNotInstanceOf(categoryContainer, containerEClasses)) {
      return;
    }

    ComponentExchangeCategory exchangeCategory = (ComponentExchangeCategory) createCategory(categoryContainer);

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
      if (categoryContainer instanceof AbstractFunctionalBlock) {
        ((AbstractFunctionalBlock) categoryContainer).getOwnedComponentExchangeCategories().add(exchangeCategory);
      }
      if (categoryContainer instanceof ComponentPkg) {
        ((ComponentPkg) categoryContainer).getOwnedComponentExchangeCategories().add(exchangeCategory);
      }

      for (EObject ce : selection) {
        if (ce instanceof ComponentExchange) {
          exchangeCategory.getExchanges().add((ComponentExchange) ce);
        }
      }
      logResults(Messages.CreateCECategoriesController_creation_msg, exchangeCategory);
    }
  }

  /**
   * @param container
   * @return
   */
  @Override
  protected EObject createCategory(EObject container) {
    String defaultName;
    EStructuralFeature feature = null;
    if (container instanceof AbstractFunctionalBlock) {
      feature = FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGE_CATEGORIES;
    } else if (container instanceof ComponentPkg) {
      feature = CsPackage.Literals.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_CATEGORIES;
    }
    // create the category
    ComponentExchangeCategory exchangeCategory = FaFactory.eINSTANCE.createComponentExchangeCategory();
    defaultName =
        EcoreUtil2.getUniqueName(exchangeCategory, container, feature, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME,
            Messages.CreateCECategoriesController_prefix);
    exchangeCategory.setName(defaultName);
    return exchangeCategory;
  }

}
