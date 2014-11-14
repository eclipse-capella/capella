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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;

/**
 */
public class FunctionalChainInvolvement_ExchangeItems implements IBusinessQuery {

  /**
   * {@inheritDoc}
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    List<CapellaElement> result = new ArrayList<CapellaElement>();
    if (element_p instanceof FunctionalChainInvolvement) {
      InvolvedElement involvedElement = ((FunctionalChainInvolvement) element_p).getInvolved();
      if (involvedElement instanceof FunctionalExchange) {
        for (AbstractExchangeItem exchangeItem : ((FunctionalExchange) involvedElement).getExchangedItems()) {
          result.add((ExchangeItem) exchangeItem);
        }
      }
    }
    return result;
  }  

  /**
   * {@inheritDoc}
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
    if (element_p instanceof FunctionalChainInvolvement) {
      for (ExchangeItem exchangeItem : ((FunctionalChainInvolvement) element_p).getExchangedItems()) {
          currentElements.add(exchangeItem);
      }
    }
    return currentElements;
  }

  /**
   * {@inheritDoc}
   */
  public EClass getEClass() {
    return FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT;
  }
 
  /**
   * {@inheritDoc}
   */
  public List<EReference> getEStructuralFeatures() {
      return Collections.singletonList(FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT__EXCHANGED_ITEMS);
  }
}
