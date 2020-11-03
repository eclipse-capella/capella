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
package org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.helpers.information.services.CommunicationLinkExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.communications.AbstractCommunication;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.communications.InterfaceCommunication;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.communications.LinkCommunication;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.communications.UndefinedCommunication;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.ExchangeItemExt;

public class SelectionAlgorithms {

	public static List<InterfaceCommunication> getInterfaceCommunications(
			Component c1, Component c2) {
		List<InterfaceCommunication> res = new ArrayList<InterfaceCommunication>();
		List<Interface> usedInterfaces = new ArrayList<Interface>();
		List<Interface> implementedInterfaces = new ArrayList<Interface>();

		usedInterfaces.addAll(ComponentExt.getAllUsedAndRequiredInterfaces(c1));

		implementedInterfaces.addAll(ComponentExt
				.getAllImplementedAndProvidedInterfaces(c2));

		for (Interface interface_ : usedInterfaces) {
			boolean partial = !implementedInterfaces.contains(interface_);
			for (ExchangeItemAllocation allocation : interface_
					.getOwnedExchangeItemAllocations()) {
				res.add(new InterfaceCommunication(allocation, interface_,
						partial));
			}
		}
		implementedInterfaces.removeAll(usedInterfaces);
		for (Interface interface_ : implementedInterfaces) {
			for (ExchangeItemAllocation allocation : interface_
					.getOwnedExchangeItemAllocations()) {
				res.add(new InterfaceCommunication(allocation, interface_, true));
			}
		}
		return res;
	}

	public static List<LinkCommunication> getLinkCommunications(Component c1,
			Component c2) {
		// calculate function table that give for each items the targeting
		// receiver links
		List<ExchangeItem> itemsToProcess = new ArrayList<ExchangeItem>();
		Hashtable<ExchangeItem, List<CommunicationLink>> item2receivers = new Hashtable<ExchangeItem, List<CommunicationLink>>();
		for (CommunicationLink link : CommunicationLinkExt
				.getAllReceiverCommunicationLink(c2)) {
			ExchangeItem item = link.getExchangeItem();
			if (item != null)
			{
  			if (!itemsToProcess.contains(item)) {
  				itemsToProcess.add(item);
  			}
  			List<CommunicationLink> targetingLinks = item2receivers.get(item);
  			if (targetingLinks == null) {
  				targetingLinks = new ArrayList<CommunicationLink>();
  				item2receivers.put(item, targetingLinks);
  			}
  			targetingLinks.add(link);
			}
		}
		// calculate communications based on senders
		List<LinkCommunication> res = new ArrayList<LinkCommunication>();
		for (CommunicationLink senderLink : CommunicationLinkExt
				.getAllSenderCommunicationLink(c1)) {
			ExchangeItem item = senderLink.getExchangeItem();
			if (item != null)
      {
  			itemsToProcess.remove(item);
  			if (item2receivers.containsKey(item)) {
  				for (CommunicationLink receiverLink : item2receivers.get(item)) {
  					res.add(new LinkCommunication(senderLink, item,
  							receiverLink));
  				}
  			} else {
  				res.add(new LinkCommunication(senderLink, item, null));
  			}
      }
		}
		// calculate partial communications with no sender links
		for (ExchangeItem item : itemsToProcess) {
			for (CommunicationLink receiverLink : item2receivers.get(item)) {
				res.add(new LinkCommunication(null, item, receiverLink));
			}
		}
		return res;
	}

	public static List<UndefinedCommunication> getUndefinedCommunications(
			Component c1, Component c2,
			List<AbstractCommunication> existingCommunications) {
		List<ExchangeItem> items = new ArrayList<ExchangeItem>();
		items.addAll(getExchangeItemsVisibleIn(c1));
		for (ExchangeItem item : getExchangeItemsVisibleIn(c2)) {
			if (!items.contains(item)) {
				items.add(item);
			}
		}
		for (AbstractCommunication communication : existingCommunications) {
			items.remove(communication.exchangeItem);
		}
		List<UndefinedCommunication> res = new ArrayList<UndefinedCommunication>();
		for (ExchangeItem item : items) {
			res.add(new UndefinedCommunication(item));
		}
		return res;
	}

	public static Collection<ExchangeItem> getExchangeItemsVisibleIn(
			Component component) {
		List<ExchangeItem> items = new ArrayList<ExchangeItem>();
		for (AbstractExchangeItem item : ExchangeItemExt
				.getAllExchangeItems(component)) {
			if (item instanceof ExchangeItem) {
				items.add((ExchangeItem) item);
			}
		}
		return items;
	}

	public static MessageKind getDefaultMessageKind(boolean withReturn) {
		if (withReturn) {
			return MessageKind.SYNCHRONOUS_CALL;
		} else {
			return MessageKind.ASYNCHRONOUS_CALL;
		}
	}

	public static Interface getTechnicalInterface(Component source,
			Component target) {
		List<Interface> interfacesByCom = new ArrayList<Interface>(
				source.getUsedInterfaces());
		interfacesByCom.retainAll(target.getImplementedInterfaces());
		for (Interface interface_ : interfacesByCom) {
			if (!interface_.isStructural()) {
				return interface_;
			}
		}
		return null;
	}

}
