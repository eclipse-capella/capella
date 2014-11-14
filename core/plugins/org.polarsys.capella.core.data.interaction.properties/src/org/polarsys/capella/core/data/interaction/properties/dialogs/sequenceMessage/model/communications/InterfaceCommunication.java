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
package org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.communications;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.data.modellingcore.AbstractConstraint;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;
import org.polarsys.capella.core.data.interaction.SequenceMessage;

public class InterfaceCommunication extends AbstractCommunication implements ExchangeItemAllocation {
	
	public InterfaceCommunication(ExchangeItemAllocation exchangeItemAllocation_p, Interface interfaze_p, boolean isPartial_p) {
		super(exchangeItemAllocation_p.getAllocatedItem(), isPartial_p);
		this.exchangeItemAllocation = exchangeItemAllocation_p;
		this.interfaze = interfaze_p;		
	}

	@Override
	public CommunicationInfo toCommunicationInfo() {
		return new CommunicationInfo(exchangeItemAllocation.getSendProtocol(), exchangeItem.getExchangeMechanism(), exchangeItemAllocation.getReceiveProtocol());
	}

	@Override
	public ExchangeItemAllocation getRepresentativeElement() {
		return exchangeItemAllocation;
	}

	@Override
	public AbstractInformationFlow getRealizedFlow() {
		return getRepresentativeElement().getRealizedFlow();
	}

	@Override
	public void setRealizedFlow(AbstractInformationFlow value) {
		getRepresentativeElement().setRealizedFlow(value);
	}

	@Override
	public EList<SequenceMessage> getInvokingSequenceMessages() {
		return getRepresentativeElement().getInvokingSequenceMessages();
	}

	@Override
	public String getName() {
		return getRepresentativeElement().getName();
	}

	@Override
	public void setName(String value) {
		getRepresentativeElement().setName(value);
	}

	@Override
	public EList<AbstractConstraint> getOwnedConstraints() {
		return getRepresentativeElement().getOwnedConstraints();
	}

	@Override
	public boolean isFinal() {
		return getRepresentativeElement().isFinal();
	}

	@Override
	public void setFinal(boolean value) {
		getRepresentativeElement().setFinal(value);
	}

	@Override
	public CommunicationLinkProtocol getSendProtocol() {
		return getRepresentativeElement().getSendProtocol();
	}

	@Override
	public void setSendProtocol(CommunicationLinkProtocol value) {
		getRepresentativeElement().setSendProtocol(value);
	}

	@Override
	public CommunicationLinkProtocol getReceiveProtocol() {
		return getRepresentativeElement().getReceiveProtocol();
	}

	@Override
	public void setReceiveProtocol(CommunicationLinkProtocol value) {
		getRepresentativeElement().setReceiveProtocol(value);
	}

	@Override
	public ExchangeItem getAllocatedItem() {
		return getRepresentativeElement().getAllocatedItem();
	}

	@Override
	public void setAllocatedItem(ExchangeItem value) {
		getRepresentativeElement().setAllocatedItem(value);
	}

	@Override
	public Interface getAllocatingInterface() {
		return getRepresentativeElement().getAllocatingInterface();
	}
}
