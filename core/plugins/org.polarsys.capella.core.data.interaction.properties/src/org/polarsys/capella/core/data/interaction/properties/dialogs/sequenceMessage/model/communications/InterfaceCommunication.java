/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.communications;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.data.modellingcore.AbstractConstraint;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;
import org.polarsys.capella.core.data.interaction.SequenceMessage;

public class InterfaceCommunication extends AbstractCommunication implements ExchangeItemAllocation {
	
	public InterfaceCommunication(ExchangeItemAllocation exchangeItemAllocation, Interface interfaze, boolean isPartial) {
		super(exchangeItemAllocation.getAllocatedItem(), isPartial);
		this.exchangeItemAllocation = exchangeItemAllocation;
		this.interfaze = interfaze;		
	}

	@Override
  public CommunicationInfo toCommunicationInfo() {
    if (exchangeItemAllocation != null && exchangeItem != null)
      return new CommunicationInfo(exchangeItemAllocation.getSendProtocol(), exchangeItem.getExchangeMechanism(),
          exchangeItemAllocation.getReceiveProtocol());
    return null;
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

  @Override
  public EList<ModelElement> getOwnedMigratedElements() {
    return getRepresentativeElement().getOwnedMigratedElements();
  }
}
