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
package org.polarsys.capella.core.business.queries.fa;

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
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

/**
 */
public class FunctionalExchange_Categories implements IBusinessQuery {

  @Override
  public List<CapellaElement> getAvailableElements(CapellaElement element) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    if (element instanceof FunctionalExchange) {
      FunctionalExchange functionalExchange = (FunctionalExchange) element;
      availableElements.addAll(getRule_MQRY_FunctionalExchange_Categories_11(functionalExchange));

      // remove ExchangeCategory related to current
      List<CapellaElement> currentElements = getCurrentElements(functionalExchange, false);
      if (!currentElements.isEmpty() && !availableElements.isEmpty()) {
        availableElements.removeAll(currentElements);
      }
    }

    return availableElements;
  }

  /**
   * Gets all the exchange categories from the BlockArchitecture
   * @param arch_p
   * @return list of ExchangeCategories
   */
  private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    if (arch_p != null) {
      for (EObject obj : EObjectExt.getAll(arch_p, FaPackage.Literals.EXCHANGE_CATEGORY)) {
        availableElements.add((CapellaElement) obj);
      }
    }
    return availableElements;
  }

  // same level
  private List<CapellaElement> getRule_MQRY_FunctionalExchange_Categories_11(FunctionalExchange functionalExchange_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(functionalExchange_p);

    availableElements.addAll(getElementsFromBlockArchitecture(arch));

    return availableElements;
  }

  /**
   * {@inheritDoc}
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

    if (element_p instanceof FunctionalExchange) {
      FunctionalExchange functionalExchange = (FunctionalExchange) element_p;
      EList<ExchangeCategory> categories = functionalExchange.getCategories();
      for (ExchangeCategory categorie : categories) {
        currentElements.add(categorie);
      }
    }

    return currentElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEClass()
   */
  public EClass getEClass() {
    return FaPackage.Literals.FUNCTIONAL_EXCHANGE;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEStructuralFeatures()
   */
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(FaPackage.Literals.FUNCTIONAL_EXCHANGE__CATEGORIES);
  }
}
