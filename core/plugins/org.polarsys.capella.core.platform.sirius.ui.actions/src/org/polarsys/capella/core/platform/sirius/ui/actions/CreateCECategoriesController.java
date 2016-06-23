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

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.AbstractFunctionalStructure;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeCategory;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FaPackage;

public class CreateCECategoriesController extends CreateCategoriesController {

  /**
   * {@inheritDoc}
   */
  @Override
  public void createAndAttachCategory(List<EObject> selection) {
    super.createAndAttachCategory(selection);

    List<EClass> containerEClasses = Arrays.asList(FaPackage.eINSTANCE.getAbstractFunctionalBlock(), FaPackage.eINSTANCE.getAbstractFunctionalStructure());
    EObject categoryContainer = getBestContainerForCategory(selection, containerEClasses);
    if (isNullOrNotInstanceOf(categoryContainer, containerEClasses)) {
      return;
    }

    ComponentExchangeCategory exchangeCategory = (ComponentExchangeCategory) createCategory(categoryContainer);

    // get user input: category name
    InputDialog inputDialog =
        new InputDialog(Display.getDefault().getActiveShell(), Messages.CreateCategoriesController_create_cat, Messages.CreateCategoriesController_cat_name,
            exchangeCategory.getName(), null);

    if (Window.OK == inputDialog.open()) {

      String categoryName = inputDialog.getValue();
      exchangeCategory.setName(categoryName);
      if (categoryContainer instanceof AbstractFunctionalBlock) {
        ((AbstractFunctionalBlock) categoryContainer).getOwnedComponentExchangeCategories().add(exchangeCategory);
      }
      if (categoryContainer instanceof AbstractFunctionalStructure) {
        ((AbstractFunctionalStructure) categoryContainer).getOwnedComponentExchangeCategories().add(exchangeCategory);
      }

      for (EObject ce : selection) {
        if (ce instanceof ComponentExchange) {
          exchangeCategory.getExchanges().add((ComponentExchange) ce);
        }
      }
      logResults(Messages.CreateCECategoriesController_creation_msg, exchangeCategory);
    } else {// User has canceled the inputDialog => delete the created category
      WizardActionHelper.deleteCreatedCategory(exchangeCategory);
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
      feature = FaPackage.eINSTANCE.getAbstractFunctionalBlock_OwnedComponentExchangeCategories();
    } else if (container instanceof AbstractFunctionalStructure) {
      feature = FaPackage.eINSTANCE.getAbstractFunctionalStructure_OwnedComponentExchangeCategories();
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
