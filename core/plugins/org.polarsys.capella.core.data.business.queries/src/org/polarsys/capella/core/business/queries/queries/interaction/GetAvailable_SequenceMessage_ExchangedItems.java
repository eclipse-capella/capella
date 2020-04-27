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
package org.polarsys.capella.core.business.queries.queries.interaction;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.interaction.SequenceMessage;

public class GetAvailable_SequenceMessage_ExchangedItems extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		if (element instanceof SequenceMessage) {
			AbstractEventOperation op = ((SequenceMessage) element).getInvokedOperation();
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

}