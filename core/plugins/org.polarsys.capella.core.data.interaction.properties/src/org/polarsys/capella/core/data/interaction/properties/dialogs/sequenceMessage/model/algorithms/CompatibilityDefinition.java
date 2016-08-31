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
package org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.algorithms;

import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.communications.CommunicationInfo;

public class CompatibilityDefinition extends org.polarsys.capella.core.data.helpers.information.services.LinkCompatibilityDefinition {

  public static final CompatibilityDefinition INSTANCE = new CompatibilityDefinition();
  
  public boolean isCompatible(CommunicationInfo comInfo, MessageKind messageKind, boolean withReturn) {
    return isCompatible(comInfo.mechanism, comInfo.senderProtocol, comInfo.receiverProtocol, messageKind, withReturn);
  }
	
}
