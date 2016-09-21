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
import org.polarsys.capella.core.data.interaction.MessageKind;

/**
 */
public class ExchangeMechanismExt {

  public static CommunicationLinkKind getKind(ExchangeMechanism mechanism, boolean isSender) {
    return LinkCompatibilityDefinition.INSTANCE.getKind(mechanism, isSender);
  }

  /**
   * Return the default protocol used for the given mechanism
   */
  public static CommunicationLinkProtocol getProtocol(ExchangeMechanism mechanism, boolean isSender) {
    MessageKind kind = MessageKind.SYNCHRONOUS_CALL;
    boolean withReturn = true;
    if (mechanism == ExchangeMechanism.EVENT) {
      kind = MessageKind.ASYNCHRONOUS_CALL;
      withReturn = false;
    }
    return LinkCompatibilityDefinition.INSTANCE.getCompatibleProtocol(isSender, mechanism, kind, withReturn);
  }
}
