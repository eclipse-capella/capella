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

package org.polarsys.capella.core.data.helpers.information.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkKind;

/**
 *
 */
public class CommunicationLinkExt {

	/**
	 * Change the communication link kind and protocol according to the
	 * mechanism
	 */
	public static void changeExchangeItemMechanism(CommunicationLink link,
			ExchangeMechanism mechanism) {
		boolean isSender = isSender(link);

		link.setProtocol(ExchangeMechanismExt.getProtocol(mechanism, isSender));
		link.setKind(ExchangeMechanismExt.getKind(mechanism, isSender));
	}

	/**
	 * Change the exchange item linked by the link, and performs modifications
	 * of kind/protocol
	 */
	public static void changeExchangeItem(CommunicationLink link,
			ExchangeItem newEI) {
		link.setExchangeItem(newEI);
		changeExchangeItemMechanism(link, newEI.getExchangeMechanism());
	}

	/**
	 * Retrieves all communication links for an exchanger (include
	 * generalization)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Collection<CommunicationLink> getAllCommunicationLinks(
			CommunicationLinkExchanger sndCpnt) {
		HashSet<CommunicationLink> result = new HashSet<>();

		Collection<NamedElement> components = null;
		if (sndCpnt instanceof GeneralizableElement) {
			components = (Collection) GeneralizableElementExt
					.getAllSuperGeneralizableElements((GeneralizableElement) sndCpnt);
			components.add((GeneralizableElement) sndCpnt);
		} else {
			components = (Collection) Collections.singleton(sndCpnt);
		}

		for (NamedElement snd : components) {
			if (snd instanceof CommunicationLinkExchanger) {
				CommunicationLinkExchanger sndCompo = (CommunicationLinkExchanger) snd;
				result.addAll(sndCompo.getOwnedCommunicationLinks());
			}
		}
		return result;
	}

	/**
	 * Filter the given list of CommunicationLink according to the given kind
	 * 
	 * @return a new list
	 */
	public static Collection<CommunicationLink> filterByKind(
			Collection<CommunicationLink> links, CommunicationLinkKind kind) {
		HashSet<CommunicationLink> result = new HashSet<>();

		for (CommunicationLink snd : links) {
			if (snd != null && snd.getKind() == kind) {
				result.add(snd);
			}
		}
		return result;
	}

	/**
	 * Retrieve exchange items from the given list of CommuncationLink
	 */
	public static Collection<AbstractExchangeItem> getExchangeItems(
			Collection<CommunicationLink> links) {
		HashSet<AbstractExchangeItem> result = new HashSet<>();

		for (CommunicationLink snd : links) {
			if (snd != null && snd.getExchangeItem() != null) {
				result.add(snd.getExchangeItem());
			}
		}
		return result;
	}

	/**
	 * Retrieve exchange items from the given list of CommuncationLink filtered
	 * by the given kinds
	 */
	public static Collection<AbstractExchangeItem> getExchangeItemsByKinds(
			Collection<CommunicationLink> links, CommunicationLinkKind[] kinds) {
		HashSet<AbstractExchangeItem> result = new HashSet<>();
		HashSet<CommunicationLinkKind> kinds_set = new HashSet<>();
		for (CommunicationLinkKind kind : kinds) {
			kinds_set.add(kind);
		}
		for (CommunicationLink snd : links) {
			if (snd != null && kinds_set.contains(snd.getKind())) {
				ExchangeItem exchangeItem = snd.getExchangeItem();
				if(exchangeItem != null) {
				  result.add(exchangeItem);				  
				}
			}
		}
		return result;
	}

	/**
	 * Retrieve exchange items from the given list of CommuncationLink filtered
	 * by the given kinds
	 */
	public static Collection<AbstractExchangeItem> getExchangeItemsByKind(
			Collection<CommunicationLink> links, CommunicationLinkKind kind) {
		HashSet<AbstractExchangeItem> result = new HashSet<>();

		for (CommunicationLink snd : links) {
			if (snd != null && kind.equals(snd.getKind())) {
				ExchangeItem exchangeItem = snd.getExchangeItem();
				if(exchangeItem != null) {
				  result.add(exchangeItem);				  
				}
			}
		}
		return result;
	}

	public static Component getSource(CommunicationLink link) {
		return (Component) link.eContainer();
	}

	public static AbstractExchangeItem getExchangeItemsByKind(
			CommunicationLink link, CommunicationLinkKind kind) {
		if (link != null && kind.equals(link.getKind())) {
			return link.getExchangeItem();
		}
		return null;
	}

	public static boolean isSender(CommunicationLink link) {
		return link.getKind() == CommunicationLinkKind.PRODUCE
				|| link.getKind() == CommunicationLinkKind.SEND
				|| link.getKind() == CommunicationLinkKind.CALL
				|| link.getKind() == CommunicationLinkKind.WRITE
				|| link.getKind() == CommunicationLinkKind.TRANSMIT;
	}

	/**
	 * return true if communication link if of kind receiving
	 * 
	 * @param link
	 *            : CommunicationLink
	 * @return boolean
	 */
	public static boolean isReceiver(CommunicationLink link) {
		return link.getKind() == CommunicationLinkKind.RECEIVE
				|| link.getKind() == CommunicationLinkKind.CONSUME
				|| link.getKind() == CommunicationLinkKind.EXECUTE
				|| link.getKind() == CommunicationLinkKind.ACCESS
				|| link.getKind() == CommunicationLinkKind.ACQUIRE;
	}

	/**
	 * return the list of all Communication Link contained by the given
	 * component that are senders.
	 * 
	 * @param component
	 *            : Component
	 * @return List<CommunicationLink>
	 */
	public static List<CommunicationLink> getSenderCommunicationLink(
			Component component) {
		List<CommunicationLink> senderLinks = new ArrayList<>();
		for (CommunicationLink link : component.getOwnedCommunicationLinks()) {
			if (isSender(link)) {
				senderLinks.add(link);
			}
		}
		return senderLinks;
	}

	/**
	 * return the list of all Communication Link contained by the given
	 * component and its ancestors that are senders.
	 * 
	 * @param component
	 *            : Component
	 * @return List<CommunicationLink>
	 */
	public static List<CommunicationLink> getAllSenderCommunicationLink(
      Component component) {
    List<CommunicationLink> senderLinks = new ArrayList<>();
    List<GeneralizableElement> components = GeneralizableElementExt.getAllSuperGeneralizableElements(component);
    components.add(component);

    for (GeneralizableElement ge : components) {
      if (ge instanceof Component)
        for (CommunicationLink link : ((Component) ge).getOwnedCommunicationLinks()) {
          if (isSender(link)) {
            senderLinks.add(link);
          }
        }
    }
    return senderLinks;
  }

	/**
	 * return the list of all Communication Link contained by the given
	 * component that are receivers.
	 * 
	 * @param component
	 *            : Component
	 * @return List<CommunicationLink>
	 */
	public static List<CommunicationLink> getReceiverCommunicationLink(
			Component component) {
		List<CommunicationLink> receiverLinks = new ArrayList<>();
		for (CommunicationLink link : component.getOwnedCommunicationLinks()) {
			if (isReceiver(link)) {
				receiverLinks.add(link);
			}
		}
		return receiverLinks;
	}

	/**
	 * return the list of all Communication Link contained by the given
	 * component and its ancestor that are receivers.
	 * 
	 * @param component
	 *            : Component
	 * @return List<CommunicationLink>
	 */
	public static List<CommunicationLink> getAllReceiverCommunicationLink(
      Component component) {
    List<CommunicationLink> receiverLinks = new ArrayList<>();
    List<GeneralizableElement> components = GeneralizableElementExt.getAllSuperGeneralizableElements(component);
    components.add(component);

    for (GeneralizableElement ge : components) {
      if (ge instanceof Component)
        for (CommunicationLink link : ((Component) ge).getOwnedCommunicationLinks()) {
          if (isReceiver(link)) {
            receiverLinks.add(link);
          }
        }
    }

    return receiverLinks;
  }

	/**
	 * True if link1 and link2 target the same Exchange Item, have the same kind
	 * and the same protocol.
	 */
	public static boolean isSameCommunication(CommunicationLink link1,
			CommunicationLink link2) {
		return link1.getExchangeItem() == link2.getExchangeItem()
				&& link1.getProtocol() == link2.getProtocol()
				&& link1.getKind() == link2.getKind();
	}
}
