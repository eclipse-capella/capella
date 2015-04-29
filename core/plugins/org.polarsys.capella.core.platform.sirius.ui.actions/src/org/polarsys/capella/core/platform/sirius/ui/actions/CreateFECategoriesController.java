/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
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
  public void createAndAttachCategory(List<EObject> selection_p) {

    List<EClass> funcPkgEClass = Collections.singletonList(FaPackage.eINSTANCE.getFunctionPkg());

    EObject categoryContainer = getBestContainerForCategory(selection_p, funcPkgEClass);

    if (isNullOrNotInstanceOf(categoryContainer, funcPkgEClass)) {
      return;
    }
    FunctionPkg pkgContainer = (FunctionPkg) categoryContainer;

    ExchangeCategory exchangeCategory = createCategory(pkgContainer);
    pkgContainer.getOwnedCategories().add(exchangeCategory);

    // get user input: category name
    InputDialog inputDialog = new InputDialog(Display.getDefault().getActiveShell(), Messages.CreateCategoriesController_create_cat, Messages.CreateCategoriesController_cat_name,
        exchangeCategory.getName(), null);

    if (Window.OK == inputDialog.open()) {

      String categoryName = inputDialog.getValue();
      exchangeCategory.setName(categoryName);

      for (EObject fe : selection_p) {
        if (fe instanceof FunctionalExchange) {
          exchangeCategory.getExchanges().add((FunctionalExchange) fe);
        }
      }
      logResults(Messages.CreateFECategoriesController_creation_msg, exchangeCategory);
    } else {
      WizardActionHelper.deleteCreatedCategory(exchangeCategory);
    }
  }

  @Override
  protected ExchangeCategory createCategory(EObject container_p) {
    ExchangeCategory exchangeCategory = FaFactory.eINSTANCE.createExchangeCategory();
    EReference feature = FaPackage.eINSTANCE.getFunctionPkg_OwnedCategories();
    String defaultName = EcoreUtil2.getUniqueName(exchangeCategory, container_p, feature, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME,
        Messages.CreateFECategoriesController_prefix);
    exchangeCategory.setName(defaultName);
    return exchangeCategory;
  }

}
