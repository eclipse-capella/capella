/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.data.helpers.information.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkKind;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;

/**
 *
 */
public class CommunicationLinkExchangerHelper {
  private static CommunicationLinkExchangerHelper instance;

  private CommunicationLinkExchangerHelper() {
    //
  }

  public static CommunicationLinkExchangerHelper getInstance() {
    if (instance == null)
      instance = new CommunicationLinkExchangerHelper();
    return instance;
  }

  public Object doSwitch(CommunicationLinkExchanger element, EStructuralFeature feature) {
    Object ret = null;
    if (feature.equals(CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__PRODUCE)) {
      ret = getProduce(element);
    } else if (feature.equals(CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__CONSUME)) {
      ret = getConsume(element);
    } else if (feature.equals(CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__SEND)) {
      ret = getSend(element);
    } else if (feature.equals(CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__RECEIVE)) {
      ret = getReceive(element);
    } else if (feature.equals(CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__CALL)) {
      ret = getCall(element);
    } else if (feature.equals(CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__EXECUTE)) {
      ret = getExecute(element);
    } else if (feature.equals(CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__WRITE)) {
      ret = getWrite(element);
    } else if (feature.equals(CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__ACCESS)) {
      ret = getAccess(element);
    } else if (feature.equals(CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__ACQUIRE)) {
      ret = getAcquire(element);
    } else if (feature.equals(CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__TRANSMIT)) {
      ret = getTransmit(element);
    }
    return ret;
  }

  protected List<CommunicationLink> getProduce(CommunicationLinkExchanger element) {
    return getLinks(element, CommunicationLinkKind.PRODUCE);
  }

  protected List<CommunicationLink> getConsume(CommunicationLinkExchanger element) {
    return getLinks(element, CommunicationLinkKind.CONSUME);
  }

  protected List<CommunicationLink> getSend(CommunicationLinkExchanger element) {
    return getLinks(element, CommunicationLinkKind.SEND);
  }

  protected List<CommunicationLink> getReceive(CommunicationLinkExchanger element) {
    return getLinks(element, CommunicationLinkKind.RECEIVE);
  }

  protected List<CommunicationLink> getCall(CommunicationLinkExchanger element) {
    return getLinks(element, CommunicationLinkKind.CALL);
  }

  protected List<CommunicationLink> getExecute(CommunicationLinkExchanger element) {
    return getLinks(element, CommunicationLinkKind.EXECUTE);
  }

  protected List<CommunicationLink> getWrite(CommunicationLinkExchanger element) {
    return getLinks(element, CommunicationLinkKind.WRITE);
  }

  protected List<CommunicationLink> getAccess(CommunicationLinkExchanger element) {
    return getLinks(element, CommunicationLinkKind.ACCESS);
  }

  protected List<CommunicationLink> getAcquire(CommunicationLinkExchanger element) {
    return getLinks(element, CommunicationLinkKind.ACQUIRE);
  }

  protected List<CommunicationLink> getTransmit(CommunicationLinkExchanger element) {
    return getLinks(element, CommunicationLinkKind.TRANSMIT);
  }

  private List<CommunicationLink> getLinks(CommunicationLinkExchanger element, CommunicationLinkKind kind) {
    List<CommunicationLink> links = new ArrayList<>();
    for (CommunicationLink link : element.getOwnedCommunicationLinks()) {
      if (kind.equals(link.getKind())) {
        links.add(link);
      }
    }
    return links;
  }
}
