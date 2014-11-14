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
import org.polarsys.capella.core.data.helpers.interaction.services.MessageEndExt;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

/**
 * 
 */
public class SequenceMessage_InvokedOperation implements IBusinessQuery {

	/**
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
	  SequenceMessage message = (SequenceMessage) element_p;
	  AbstractInstance sendingInstance = message.getSendingEnd().getCovered().getRepresentedInstance();
    AbstractInstance receivingInstance = message.getReceivingEnd().getCovered().getRepresentedInstance();
    MessageKind messageKind = message.getKind();


    return ScenarioExt.getAvailableExchangeItemsBetweenInstances(sendingInstance, receivingInstance, messageKind);
	}


	/**
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,
	 *      boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);

		if (null == systemEngineering) {
			SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element_p);
			for (ReuseLink link : sharedPkg.getReuseLinks()) {
				if (SystemEngineeringExt.getSystemEngineering(link) != null) {
					systemEngineering = SystemEngineeringExt.getSystemEngineering(link);
					break;
				}
			}
			if (systemEngineering == null)
				return currentElements;
		}

		if (element_p instanceof SequenceMessage) {
			SequenceMessage currentMessage = (SequenceMessage) element_p;

			MessageEnd receivingEnd = currentMessage.getReceivingEnd();
			AbstractEventOperation receivedOperation = MessageEndExt.getOperation(receivingEnd);
			MessageEnd sendingEnd = currentMessage.getSendingEnd();
			AbstractEventOperation sentOperation = MessageEndExt.getOperation(sendingEnd);

			if ((receivedOperation != null) && (sentOperation != null) && (receivedOperation.equals(sentOperation))) {
				currentElements.add(receivedOperation);
			}
		}

		return currentElements;
	}

	public EClass getEClass() {
		return InteractionPackage.Literals.SEQUENCE_MESSAGE;
	}

	public List<EReference> getEStructuralFeatures() {
	  //FIXME incorrect
    return Collections.singletonList(InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_END);
	}
}
