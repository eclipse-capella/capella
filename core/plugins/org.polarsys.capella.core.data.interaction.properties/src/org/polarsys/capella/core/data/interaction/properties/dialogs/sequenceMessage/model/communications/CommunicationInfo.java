/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;

public class CommunicationInfo {

	public CommunicationLinkProtocol senderProtocol;
	public ExchangeMechanism mechanism;
	public CommunicationLinkProtocol receiverProtocol;

	public CommunicationInfo(CommunicationLinkProtocol senderProtocol, ExchangeMechanism mechanism, CommunicationLinkProtocol receiverProtocol) {
		this.senderProtocol = senderProtocol;
		this.mechanism = mechanism;
		this.receiverProtocol = receiverProtocol;
	}
}
