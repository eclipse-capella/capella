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
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.AbstractFunctionExt;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.utils.ListExt;

public class Connection_ConnectionFunctionalExchangeAllocations implements IBusinessQuery {

  private List<CapellaElement> getRuleConnectionConnectionFunctionalExchangeAllocation(BlockArchitecture sysEng_p, ComponentExchange element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);

    // Source{Component}
    AbstractFunctionalBlock source = null;
    // Target{Component}
    AbstractFunctionalBlock target = null;

    InformationsExchanger informationsExchangerSource = element_p.getSource();
    InformationsExchanger informationsExchangerTarget = element_p.getTarget();

    if ((informationsExchangerSource != null) && (informationsExchangerTarget != null)) {
      source = ComponentExchangeExt.getSourceComponent(element_p);
      target = ComponentExchangeExt.getTargetComponent(element_p);

        availableElements.addAll(AbstractFunctionExt.getAllAllocatedFunctionalExchangeFiltered(source, target));
        availableElements.addAll(AbstractFunctionExt.getAllAllocatedFunctionalExchangeFiltered(target, source));
    }

    // remove already allocated exchanges 
    List<CapellaElement> allReadyAllocatedExchanges = new ArrayList<CapellaElement>();
    for (CapellaElement capellaElement : availableElements) {
      if (capellaElement instanceof FunctionalExchange) {
        FunctionalExchange exchange = (FunctionalExchange) capellaElement;
        EList<AbstractTrace> incomingTraces = exchange.getIncomingTraces();
        for (AbstractTrace abstractTrace : incomingTraces) {
          if (abstractTrace instanceof ComponentExchangeFunctionalExchangeAllocation) {
            allReadyAllocatedExchanges.add(capellaElement);
          }
        }
      }
    }

    return availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(element_p);

    if (null == arch) {
      return availableElements;
    }

    if (element_p instanceof ComponentExchange) {
      ComponentExchange currentCapabilityUseCase = (ComponentExchange) element_p;
      availableElements.addAll(getRuleConnectionConnectionFunctionalExchangeAllocation(arch, currentCapabilityUseCase));
    }
    availableElements = ListExt.removeDuplicates(availableElements);

    return availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.data.capellacore.CapellaElement, boolean)
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

    if (element_p instanceof ComponentExchange) {
      ComponentExchange ele = (ComponentExchange) element_p;
      EList<FunctionalExchange> funExchanges = ele.getAllocatedFunctionalExchanges();
      for (FunctionalExchange funExc : funExchanges) {
        currentElements.add(funExc);
      }
    }

    return currentElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEClass()
   */
  public EClass getEClass() {
    return FaPackage.Literals.COMPONENT_EXCHANGE;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEStructuralFeature()
   */
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(FaPackage.Literals.COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS);
  }
}
