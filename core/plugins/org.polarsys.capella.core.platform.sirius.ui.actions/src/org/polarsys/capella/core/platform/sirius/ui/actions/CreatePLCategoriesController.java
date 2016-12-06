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

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkCategory;

public class CreatePLCategoriesController extends CreateCategoriesController {
  /**
   * {@inheritDoc}
   */
  @Override
  public void createAndAttachCategory(List<EObject> selection, String categoryNameToUse) {
    List<EClass> componentEClass = Collections.singletonList(CsPackage.eINSTANCE.getComponent());
    
    Component categoryContainer = (Component) getBestContainerForCategory(selection, componentEClass);

    if (isNullOrNotInstanceOf(categoryContainer, componentEClass)) {
      return;
    }
    PhysicalLinkCategory category = (PhysicalLinkCategory) createCategory(categoryContainer);

    // Get category name
    String categoryName = null;
    // Use given name if not null, else ask user
    if (categoryNameToUse != null) {
      categoryName = categoryNameToUse;
    } else {
      categoryName = askCategoryName(category.getName());
    }
    
    if (categoryName != null) {
      category.setName(categoryName);
      categoryContainer.getOwnedPhysicalLinkCategories().add(category);

      for (EObject fe : selection) {
        if (fe instanceof PhysicalLink) {
          category.getLinks().add((PhysicalLink) fe);
        }
      }
      logResults(Messages.CreatePLCategoriesController_creation_msg, category);
    }
  }

  @Override
  protected EObject createCategory(EObject container) {
    PhysicalLinkCategory category = CsFactory.eINSTANCE.createPhysicalLinkCategory();
    EReference feature = CsPackage.eINSTANCE.getComponent_OwnedPhysicalLinkCategories();
    String defaultName =
        EcoreUtil2.getUniqueName(category, container, feature, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME,
            Messages.CreatePLCategoriesController_prefix);
    category.setName(defaultName);
    return category;
  }

}
