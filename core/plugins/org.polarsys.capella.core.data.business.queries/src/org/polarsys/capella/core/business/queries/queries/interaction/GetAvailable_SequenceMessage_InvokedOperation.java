/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.model.helpers.ScenarioExt;

public class GetAvailable_SequenceMessage_InvokedOperation extends AbstractQuery {

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
		SequenceMessage message = (SequenceMessage) element;
		
		AbstractInstance sendingInstance = null;
		MessageEnd sendingEnd = message.getSendingEnd();
		if (sendingEnd != null)
			sendingInstance = sendingEnd.getCovered().getRepresentedInstance();

		AbstractInstance receivingInstance = null;
		MessageEnd receivingEnd = message.getReceivingEnd();
		if (receivingEnd != null)
			receivingInstance = receivingEnd.getCovered().getRepresentedInstance();		
				
		if (sendingInstance != null && receivingInstance != null)
			return ScenarioExt.getAvailableExchangeItemsBetweenInstances(sendingInstance, receivingInstance, message.getKind());
		return new ArrayList<CapellaElement>(); 
	}

}