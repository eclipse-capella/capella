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
package org.polarsys.capella.core.business.queries.cs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkCategory;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

/**
 */
public class PhysicalLink_Categories implements IBusinessQuery{

  /**
   * Gets all the physical link categories from the BlockArchitecture
   * @param arch_p
   * @return list of PhysicalLinkCategories
   */
  private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    if (arch_p != null) {
      for (EObject obj : EObjectExt.getAll(arch_p, CsPackage.Literals.PHYSICAL_LINK_CATEGORY)) {
        availableElements.add((CapellaElement) obj);
      }
    }
    return availableElements;
  }


  // same level
  private List<CapellaElement> getRule_MQRY_PhysicalLink_Categories_11(PhysicalLink physicalLink_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(physicalLink_p);

    availableElements.addAll(getElementsFromBlockArchitecture(arch));

    return availableElements;
  }

  /**
   * {@inheritDoc}
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    if (element_p instanceof PhysicalLink) {
      PhysicalLink physicalLink = (PhysicalLink) element_p;
      availableElements.addAll(getRule_MQRY_PhysicalLink_Categories_11(physicalLink));

      //remove PhysicalLinkCategory related to current
      List<CapellaElement> currentElements = getCurrentElements(physicalLink, false);
      if (!currentElements.isEmpty() && !availableElements.isEmpty()) {
        availableElements.removeAll(currentElements);
      }
    } 

    return availableElements;
  }

  /**
   * {@inheritDoc}
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

    if (element_p instanceof PhysicalLink) {
      PhysicalLink physicalLink = (PhysicalLink) element_p;
      EList<PhysicalLinkCategory> categories = physicalLink.getCategories();
      for (PhysicalLinkCategory categorie : categories) {
        currentElements.add(categorie);
      }
    }

    return currentElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getEClass()
   */
  public EClass getEClass() {
    return CsPackage.Literals.PHYSICAL_LINK;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getEStructuralFeatures()
   */
  public List<EReference> getEStructuralFeatures() {
      return Collections.singletonList(CsPackage.Literals.PHYSICAL_LINK__CATEGORIES);
  }  
}
