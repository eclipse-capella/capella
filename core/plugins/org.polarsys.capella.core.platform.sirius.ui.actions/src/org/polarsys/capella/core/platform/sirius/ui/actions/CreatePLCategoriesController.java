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

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
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
    List<EClass> componentEClass = Arrays.asList(CsPackage.Literals.COMPONENT, CsPackage.Literals.COMPONENT_PKG);
    
    EObject categoryContainer = getBestContainerForCategory(selection, componentEClass);

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
      if (categoryContainer instanceof Component) {
        ((Component) categoryContainer).getOwnedPhysicalLinkCategories().add(category);
      } else if (categoryContainer instanceof ComponentPkg) {
        ((ComponentPkg) categoryContainer).getOwnedPhysicalLinkCategories().add(category);
      }

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
    EReference feature;
    if (container instanceof Component) {
      feature = CsPackage.eINSTANCE.getComponent_OwnedPhysicalLinkCategories();
    } else if (container instanceof ComponentPkg) {
      feature = CsPackage.eINSTANCE.getComponentPkg_OwnedPhysicalLinkCategories();
    } else {
      return category;
    }
    
    String defaultName =
        EcoreUtil2.getUniqueName(category, container, feature, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME,
            Messages.CreatePLCategoriesController_prefix);
    category.setName(defaultName);
    return category;
  }

}
