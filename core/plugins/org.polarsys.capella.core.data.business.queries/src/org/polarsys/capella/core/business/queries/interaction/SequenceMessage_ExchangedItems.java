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
package org.polarsys.capella.core.business.queries.interaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;

/**
 * 
 */
public class SequenceMessage_ExchangedItems implements IBusinessQuery {

	/**
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    if (element_p instanceof SequenceMessage) {
      AbstractEventOperation op = ((SequenceMessage) element_p).getInvokedOperation();
      if (op instanceof FunctionalExchange) {
        for (AbstractExchangeItem item : ((FunctionalExchange) op).getExchangedItems()) {
          if (item instanceof ExchangeItem) {
            availableElements.add((ExchangeItem) item);
          }
        }
      } else if (op instanceof ComponentExchange) {
        for (AbstractExchangeItem item : ((ComponentExchange) op).getConvoyedInformations()) {
          if (item instanceof ExchangeItem) {
            availableElements.add((ExchangeItem) item);
          }
        }
        for (FunctionalExchange fe : ((ComponentExchange) op).getAllocatedFunctionalExchanges()) {
          for (AbstractExchangeItem item : fe.getExchangedItems()) {
            if (item instanceof ExchangeItem) {
              availableElements.add((ExchangeItem) item);
            }
          }
        }
      }
    }

    return availableElements;
	}


	/**
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,
	 *      boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

		if (element_p instanceof SequenceMessage) {
			currentElements.addAll(((SequenceMessage) element_p).getExchangedItems());
		}

		return currentElements;
	}

	public EClass getEClass() {
		return InteractionPackage.Literals.SEQUENCE_MESSAGE;
	}

	public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGED_ITEMS);
	}
}
