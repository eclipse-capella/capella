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

import org.polarsys.capella.core.business.abstractqueries.RefactorDebugger;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

/**
 */
public class FunctionInputPort_InComingExchangeItems extends AbstractFunctionalExchangeItems {

  @Override
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    return RefactorDebugger.callAndTestQuery("GetAvailable_FunctionInputPort_InComingExchangeItems__Lib", element_p, getOldAvailableElements(element_p),//$NON-NLS-1$
        getEClass(), getClass());
  }

  @Override
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

    if (element_p instanceof FunctionInputPort) {
      FunctionInputPort ele = (FunctionInputPort) element_p;
      for (ExchangeItem abstractExchangeItem : ele.getIncomingExchangeItems()) {
        currentElements.add(abstractExchangeItem);
      }
    }
    return currentElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEClass()
   */
  @Override
  public EClass getEClass() {
    return FaPackage.Literals.FUNCTION_INPUT_PORT;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEStructuralFeatures()
   */
  @Override
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(FaPackage.Literals.FUNCTION_INPUT_PORT__INCOMING_EXCHANGE_ITEMS);
  }

}
