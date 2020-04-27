/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.communications.CommunicationInfo;

public class CompatibilityDefinition extends org.polarsys.capella.core.data.helpers.information.services.LinkCompatibilityDefinition {

  public static final CompatibilityDefinition INSTANCE = new CompatibilityDefinition();

  public boolean isCompatible(CommunicationInfo comInfo, MessageKind messageKind, boolean withReturn) {
    if (comInfo != null && messageKind != null)
      return isCompatible(comInfo.mechanism, comInfo.senderProtocol, comInfo.receiverProtocol, messageKind, withReturn);
    return false;
  }
	
}
