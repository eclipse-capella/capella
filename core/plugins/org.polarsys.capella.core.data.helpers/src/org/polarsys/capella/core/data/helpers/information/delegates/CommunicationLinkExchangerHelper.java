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

  public static CommunicationLinkExchangerHelper getInstance(){
    if(instance == null)
      instance = new CommunicationLinkExchangerHelper();
    return instance;
  }

  public Object doSwitch(CommunicationLinkExchanger element, EStructuralFeature feature){
    Object ret = null;
    if (feature.equals(CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__PRODUCE)) {
      ret = getLinks(element, CommunicationLinkKind.PRODUCE);
    } else if (feature.equals(CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__CONSUME)) {
      ret = getLinks(element, CommunicationLinkKind.CONSUME);
    } else if (feature.equals(CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__SEND)) {
      ret = getLinks(element, CommunicationLinkKind.SEND);
    } else if (feature.equals(CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__RECEIVE)) {
      ret = getLinks(element, CommunicationLinkKind.RECEIVE);
    } else if (feature.equals(CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__CALL)) {
      ret = getLinks(element, CommunicationLinkKind.CALL);
    } else if (feature.equals(CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__EXECUTE)) {
      ret = getLinks(element, CommunicationLinkKind.EXECUTE);
    } else if (feature.equals(CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__WRITE)) {
      ret = getLinks(element, CommunicationLinkKind.WRITE);
    } else if (feature.equals(CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__ACCESS)) {
      ret = getLinks(element, CommunicationLinkKind.ACCESS);
    } else if (feature.equals(CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__ACQUIRE)) {
      ret = getLinks(element, CommunicationLinkKind.ACQUIRE);
    } else if (feature.equals(CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__TRANSMIT)) {
      ret = getLinks(element, CommunicationLinkKind.TRANSMIT);
    }

    return ret;
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
