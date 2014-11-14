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
package org.polarsys.capella.core.data.helpers.information.services;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkKind;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;

/**
 *
 */
public class CommunicationLinkExt {

  /**
   * Change the communication link kind and protocol according to the mechanism
   */
  public static void changeExchangeItemMechanism(CommunicationLink link, ExchangeMechanism mechanism) {
    boolean isSender = isSender(link);

    link.setProtocol(ExchangeMechanismExt.getProtocol(mechanism, isSender));
    link.setKind(ExchangeMechanismExt.getKind(mechanism, isSender));
  }

  /**
   * Change the exchange item linked by the link, and performs modifications of kind/protocol
   */
  public static void changeExchangeItem(CommunicationLink link, ExchangeItem new_p) {
    link.setExchangeItem(new_p);
    changeExchangeItemMechanism(link, new_p.getExchangeMechanism());
  }

  /**
   * Retrieves all communication links for an exchanger
   * (include generalization)
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public static Collection<CommunicationLink> getAllCommunicationLinks(CommunicationLinkExchanger sndCpnt_p) {
    HashSet<CommunicationLink> result = new HashSet<CommunicationLink>();

    Collection<NamedElement> components = null;
    if (sndCpnt_p instanceof GeneralizableElement) {
      components = (Collection)GeneralizableElementExt.getAllSuperGeneralizableElements((GeneralizableElement)sndCpnt_p);
      components.add((GeneralizableElement)sndCpnt_p);
    } else {
      components = (Collection)Collections.singleton(sndCpnt_p);
    }

    for (NamedElement snd : components) {
      if (snd!=null && snd instanceof CommunicationLinkExchanger) {
        CommunicationLinkExchanger sndCompo = (CommunicationLinkExchanger)snd;
        result.addAll(sndCompo.getOwnedCommunicationLinks());
      }
    }
    return result;
  }

  /**
   * Filter the given list of CommunicationLink according to the given kind
   * @return a new list
   */
  public static Collection<CommunicationLink> filterByKind(Collection<CommunicationLink> links, CommunicationLinkKind kind) {
    HashSet<CommunicationLink> result = new HashSet<CommunicationLink>();

    for (CommunicationLink snd : links) {
      if (snd!=null && snd.getKind() == kind) {
        result.add(snd);
      }
    }
    return result;
  }

  /**
   * Retrieve exchange items from the given list of CommuncationLink
   */
  public static Collection<AbstractExchangeItem> getExchangeItems(Collection<CommunicationLink> links) {
    HashSet<AbstractExchangeItem> result = new HashSet<AbstractExchangeItem>();

    for (CommunicationLink snd : links) {
      if (snd!=null && snd.getExchangeItem()!=null) {
        result.add(snd.getExchangeItem());
      }
    }
    return result;
  }

  /**
   * Retrieve exchange items from the given list of CommuncationLink filtered by the given kinds
   */
  public static Collection<AbstractExchangeItem> getExchangeItemsByKinds(Collection<CommunicationLink> links, CommunicationLinkKind[] kinds) {
    HashSet<AbstractExchangeItem> result = new HashSet<AbstractExchangeItem>();
    HashSet<CommunicationLinkKind> kinds_set = new HashSet<CommunicationLinkKind>();
    for (CommunicationLinkKind kind : kinds) {
      kinds_set.add(kind);
    }
    for (CommunicationLink snd : links) {
      if (snd!=null && kinds_set.contains(snd.getKind())) {
        result.add(snd.getExchangeItem());
      }
    }
    return result;
  }

  /**
   * Retrieve exchange items from the given list of CommuncationLink filtered by the given kinds
   */
  public static Collection<AbstractExchangeItem> getExchangeItemsByKind(Collection<CommunicationLink> links, CommunicationLinkKind kind) {
    HashSet<AbstractExchangeItem> result = new HashSet<AbstractExchangeItem>();

    for (CommunicationLink snd : links) {
      if (snd!=null && kind.equals(snd.getKind())) {
        result.add(snd.getExchangeItem());
      }
    }
    return result;
  }

  public static AbstractExchangeItem getExchangeItemsByKind(CommunicationLink link, CommunicationLinkKind kind) {
    if (link!=null && kind.equals(link.getKind())) {
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
   * @param link : CommunicationLink
   * @return boolean 
   */
  public static boolean isReceiver(CommunicationLink link) {
    return link.getKind() == CommunicationLinkKind.RECEIVE
        || link.getKind() == CommunicationLinkKind.CONSUME
        || link.getKind() == CommunicationLinkKind.EXECUTE
        || link.getKind() == CommunicationLinkKind.ACCESS
        || link.getKind() == CommunicationLinkKind.ACQUIRE;
  }
}
