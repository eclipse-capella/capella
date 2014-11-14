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
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchangeRealization;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

/**
 */
public class FunctionalExchange_FunctionalExchangeRealizations implements IBusinessQuery {

  @Override
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    List<BlockArchitecture> exploredArchitectures = new ArrayList<BlockArchitecture>();

    EObject currentArchitecture = element_p;
    while (!(currentArchitecture instanceof BlockArchitecture)) {
      currentArchitecture = currentArchitecture.eContainer();
      if (currentArchitecture == null) {
        return availableElements;
      }
    }
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
    if (sysEng == null) {
      return availableElements;
    }
    // Ctx
    if (currentArchitecture instanceof SystemAnalysis) {
      exploredArchitectures.add(SystemEngineeringExt.getOwnedOperationalAnalysis(sysEng));
    }
    // Log
    else if (currentArchitecture instanceof LogicalArchitecture) {
      exploredArchitectures.add(SystemEngineeringExt.getOwnedSystemAnalysis(sysEng));
    }
    // Phy
    else if (currentArchitecture instanceof PhysicalArchitecture) {
      exploredArchitectures.addAll(SystemEngineeringExt.getAllLogicalArchitecture(element_p));
    }

    else {
      return availableElements;
    }

    for (BlockArchitecture anArchitecture : exploredArchitectures) {
      for (EObject aFunctionalExchange : EObjectExt.getAll(anArchitecture, FaPackage.Literals.FUNCTIONAL_EXCHANGE)) {
        availableElements.add((CapellaElement) aFunctionalExchange);
      }
    }

    // remove existing Exchanges
    List<CapellaElement> currentElements = getCurrentElements(element_p, false);
    for (CapellaElement capellaElement : currentElements) {
      availableElements.remove(capellaElement);
    }

    return availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.data.capellacore.CapellaElement,
   *      boolean)
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

    if (element_p instanceof FunctionalExchange) {
      FunctionalExchange ele = (FunctionalExchange) element_p;
      EList<FunctionalExchangeRealization> ownedFunctionalExchangeRealisations = ele.getOwnedFunctionalExchangeRealizations();
      for (FunctionalExchangeRealization functionalExchangeRealisation : ownedFunctionalExchangeRealisations) {
        currentElements.add((CapellaElement) functionalExchangeRealisation.getTargetElement());
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
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEStructuralFeature()
   */
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(FaPackage.Literals.FUNCTIONAL_EXCHANGE__OWNED_FUNCTIONAL_EXCHANGE_REALIZATIONS);
  }

}
