/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.data.helpers.information.services;

import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkKind;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;

/**
 */
public class ExchangeMechanismExt {

  public static CommunicationLinkKind getKind(ExchangeMechanism mechanism, boolean isSender) {
    CommunicationLinkKind kind = CommunicationLinkKind.PRODUCE;

    if (mechanism == ExchangeMechanism.OPERATION) {
      if (isSender) {
        kind = CommunicationLinkKind.CALL;
      } else {
        kind = CommunicationLinkKind.EXECUTE;
      }
    } else if (mechanism == ExchangeMechanism.EVENT) {
      if (isSender) {
        kind = CommunicationLinkKind.SEND;
      } else {
        kind = CommunicationLinkKind.RECEIVE;
      }
    } else if (mechanism == ExchangeMechanism.FLOW) {
      if (isSender) {
        kind = CommunicationLinkKind.PRODUCE;
      } else {
        kind = CommunicationLinkKind.CONSUME;
      }
    } else if (mechanism == ExchangeMechanism.SHARED_DATA) {
      if (isSender) {
        kind = CommunicationLinkKind.WRITE;
      } else {
        kind = CommunicationLinkKind.ACCESS;
      }
    } else if (mechanism == ExchangeMechanism.UNSET) {
      if (isSender) {
        kind = CommunicationLinkKind.TRANSMIT;
      } else {
        kind = CommunicationLinkKind.ACQUIRE;
      }
    }
    return kind;
  }

  public static CommunicationLinkProtocol getProtocol(ExchangeMechanism mechanism, boolean isSender) {
    CommunicationLinkProtocol protocol = CommunicationLinkProtocol.UNSET;

    if (mechanism == ExchangeMechanism.OPERATION) {
      if (isSender) {
        protocol = CommunicationLinkProtocol.SYNCHRONOUS;
      } else {
        protocol = CommunicationLinkProtocol.UNSET;
      }
    } else if (mechanism == ExchangeMechanism.EVENT) {
      if (isSender) {
        protocol = CommunicationLinkProtocol.UNICAST;
      } else {
        protocol = CommunicationLinkProtocol.UNSET;
      }
    } else if (mechanism == ExchangeMechanism.FLOW) {
      if (isSender) {
        protocol = CommunicationLinkProtocol.UNSET;
      } else {
        protocol = CommunicationLinkProtocol.UNSET;
      }
    } else if (mechanism == ExchangeMechanism.SHARED_DATA) {
      if (isSender) {
        protocol = CommunicationLinkProtocol.UNSET;
      } else {
        protocol = CommunicationLinkProtocol.READ;
      }
    } else if (mechanism == ExchangeMechanism.UNSET) {
      if (isSender) {
        protocol = CommunicationLinkProtocol.UNSET;
      } else {
        protocol = CommunicationLinkProtocol.UNSET;
      }
    }
    return protocol;
  }
}
