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
package org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.communications.CommunicationInfo;

public class CompatibilityDefinition {

	private static final CommunicationLinkProtocol UNSET = CommunicationLinkProtocol.UNSET;
	private static final CommunicationLinkProtocol SYNCHRONOUS = CommunicationLinkProtocol.SYNCHRONOUS;
	private static final CommunicationLinkProtocol ASYNCHRONOUS = CommunicationLinkProtocol.ASYNCHRONOUS;
	private static final CommunicationLinkProtocol UNICAST = CommunicationLinkProtocol.UNICAST;
	private static final CommunicationLinkProtocol MULTICAST = CommunicationLinkProtocol.MULTICAST;
	private static final CommunicationLinkProtocol BROADCAST = CommunicationLinkProtocol.BROADCAST;
	private static final CommunicationLinkProtocol ACCEPT = CommunicationLinkProtocol.ACCEPT;
	private static final CommunicationLinkProtocol READ = CommunicationLinkProtocol.READ;
	private static final String SENDER = "0"; //$NON-NLS-1$
	private static final String RECEIVER = "1"; //$NON-NLS-1$
	private static final boolean WITH_RETURN = true;
	
	private static final List<CommunicationLinkProtocol> COMPATIBLE_SENDER___FOR_UNSET_____SYNCHRONOUS__WITH____RETURN = Arrays.asList(new CommunicationLinkProtocol[] {UNSET});
	private static final List<CommunicationLinkProtocol> COMPATIBLE_RECEIVER_FOR_UNSET_____SYNCHRONOUS__WITH____RETURN = Arrays.asList(new CommunicationLinkProtocol[] {UNSET});
	private static final List<CommunicationLinkProtocol> COMPATIBLE_SENDER___FOR_UNSET_____ASYNCHRONOUS_WITH____RETURN = Arrays.asList(new CommunicationLinkProtocol[] {UNSET});
	private static final List<CommunicationLinkProtocol> COMPATIBLE_RECEIVER_FOR_UNSET_____ASYNCHRONOUS_WITH____RETURN = Arrays.asList(new CommunicationLinkProtocol[] {UNSET});
	private static final List<CommunicationLinkProtocol> COMPATIBLE_SENDER___FOR_UNSET_____ASYNCHRONOUS_WITHOUT_RETURN = Arrays.asList(new CommunicationLinkProtocol[] {UNSET});
	private static final List<CommunicationLinkProtocol> COMPATIBLE_RECEIVER_FOR_UNSET_____ASYNCHRONOUS_WITHOUT_RETURN = Arrays.asList(new CommunicationLinkProtocol[] {UNSET});

	private static final List<CommunicationLinkProtocol> COMPATIBLE_SENDER___FOR_EVENT_____SYNCHRONOUS__WITH____RETURN = Arrays.asList(new CommunicationLinkProtocol[] {});
	private static final List<CommunicationLinkProtocol> COMPATIBLE_RECEIVER_FOR_EVENT_____SYNCHRONOUS__WITH____RETURN = Arrays.asList(new CommunicationLinkProtocol[] {});
	private static final List<CommunicationLinkProtocol> COMPATIBLE_SENDER___FOR_EVENT_____ASYNCHRONOUS_WITH____RETURN = Arrays.asList(new CommunicationLinkProtocol[] {});
	private static final List<CommunicationLinkProtocol> COMPATIBLE_RECEIVER_FOR_EVENT_____ASYNCHRONOUS_WITH____RETURN = Arrays.asList(new CommunicationLinkProtocol[] {});
	private static final List<CommunicationLinkProtocol> COMPATIBLE_SENDER___FOR_EVENT_____ASYNCHRONOUS_WITHOUT_RETURN = Arrays.asList(new CommunicationLinkProtocol[] {UNSET, UNICAST, MULTICAST, BROADCAST});
	private static final List<CommunicationLinkProtocol> COMPATIBLE_RECEIVER_FOR_EVENT_____ASYNCHRONOUS_WITHOUT_RETURN = Arrays.asList(new CommunicationLinkProtocol[] {UNSET});

	private static final List<CommunicationLinkProtocol> COMPATIBLE_SENDER___FOR_FLOW______SYNCHRONOUS__WITH____RETURN = Arrays.asList(new CommunicationLinkProtocol[] {UNSET});
	private static final List<CommunicationLinkProtocol> COMPATIBLE_RECEIVER_FOR_FLOW______SYNCHRONOUS__WITH____RETURN = Arrays.asList(new CommunicationLinkProtocol[] {UNSET});
	private static final List<CommunicationLinkProtocol> COMPATIBLE_SENDER___FOR_FLOW______ASYNCHRONOUS_WITH____RETURN = Arrays.asList(new CommunicationLinkProtocol[] {UNSET});
	private static final List<CommunicationLinkProtocol> COMPATIBLE_RECEIVER_FOR_FLOW______ASYNCHRONOUS_WITH____RETURN = Arrays.asList(new CommunicationLinkProtocol[] {UNSET});
	private static final List<CommunicationLinkProtocol> COMPATIBLE_SENDER___FOR_FLOW______ASYNCHRONOUS_WITHOUT_RETURN = Arrays.asList(new CommunicationLinkProtocol[] {UNSET});
	private static final List<CommunicationLinkProtocol> COMPATIBLE_RECEIVER_FOR_FLOW______ASYNCHRONOUS_WITHOUT_RETURN = Arrays.asList(new CommunicationLinkProtocol[] {UNSET});

	private static final List<CommunicationLinkProtocol> COMPATIBLE_SENDER___FOR_OPERATION_SYNCHRONOUS__WITH____RETURN = Arrays.asList(new CommunicationLinkProtocol[] {SYNCHRONOUS, UNSET});
	private static final List<CommunicationLinkProtocol> COMPATIBLE_RECEIVER_FOR_OPERATION_SYNCHRONOUS__WITH____RETURN = Arrays.asList(new CommunicationLinkProtocol[] {UNSET});
	private static final List<CommunicationLinkProtocol> COMPATIBLE_SENDER___FOR_OPERATION_ASYNCHRONOUS_WITH____RETURN = Arrays.asList(new CommunicationLinkProtocol[] {ASYNCHRONOUS, UNSET});
	private static final List<CommunicationLinkProtocol> COMPATIBLE_RECEIVER_FOR_OPERATION_ASYNCHRONOUS_WITH____RETURN = Arrays.asList(new CommunicationLinkProtocol[] {UNSET});
	private static final List<CommunicationLinkProtocol> COMPATIBLE_SENDER___FOR_OPERATION_ASYNCHRONOUS_WITHOUT_RETURN = Arrays.asList(new CommunicationLinkProtocol[] {ASYNCHRONOUS, UNSET});
	private static final List<CommunicationLinkProtocol> COMPATIBLE_RECEIVER_FOR_OPERATION_ASYNCHRONOUS_WITHOUT_RETURN = Arrays.asList(new CommunicationLinkProtocol[] {UNSET});

	private static final List<CommunicationLinkProtocol> COMPATIBLE_SENDER___FOR_DATA______SYNCHRONOUS__WITH____RETURN = Arrays.asList(new CommunicationLinkProtocol[] {UNSET});
	private static final List<CommunicationLinkProtocol> COMPATIBLE_RECEIVER_FOR_DATA______SYNCHRONOUS__WITH____RETURN = Arrays.asList(new CommunicationLinkProtocol[] {READ, UNSET});
	private static final List<CommunicationLinkProtocol> COMPATIBLE_SENDER___FOR_DATA______ASYNCHRONOUS_WITH____RETURN = Arrays.asList(new CommunicationLinkProtocol[] {UNSET});
	private static final List<CommunicationLinkProtocol> COMPATIBLE_RECEIVER_FOR_DATA______ASYNCHRONOUS_WITH____RETURN = Arrays.asList(new CommunicationLinkProtocol[] {ACCEPT, UNSET});
	private static final List<CommunicationLinkProtocol> COMPATIBLE_SENDER___FOR_DATA______ASYNCHRONOUS_WITHOUT_RETURN = Arrays.asList(new CommunicationLinkProtocol[] {UNSET});
	private static final List<CommunicationLinkProtocol> COMPATIBLE_RECEIVER_FOR_DATA______ASYNCHRONOUS_WITHOUT_RETURN = Arrays.asList(new CommunicationLinkProtocol[] {ACCEPT, UNSET});

	private static final List<ExchangeMechanism> COMPATIBLE_EXCHANGE_MECHANISM_FOR_WITH_RETURN = Arrays.asList(new ExchangeMechanism[] {
			ExchangeMechanism.OPERATION,
			ExchangeMechanism.UNSET,
			ExchangeMechanism.FLOW,
			ExchangeMechanism.SHARED_DATA,
	});

	private static final List<ExchangeMechanism> COMPATIBLE_EXCHANGE_MECHANISM_FOR_WITHOUT_RETURN = Arrays.asList(new ExchangeMechanism[] { 
			ExchangeMechanism.OPERATION,
			ExchangeMechanism.UNSET,
			ExchangeMechanism.EVENT,
			ExchangeMechanism.FLOW,
			ExchangeMechanism.SHARED_DATA,
	});
	
	protected Hashtable<String, List<CommunicationLinkProtocol>> compatibilityTable;

	public static final CompatibilityDefinition INSTANCE = new CompatibilityDefinition();
	
	//initialize the table of definitions to speed up compatibility checks and element updates
	public CompatibilityDefinition() {
		super();
		compatibilityTable = new Hashtable<String, List<CommunicationLinkProtocol>>();
		
		compatibilityTable.put(SENDER   + ExchangeMechanism.UNSET + MessageKind.SYNCHRONOUS_CALL  + WITH_RETURN, COMPATIBLE_SENDER___FOR_UNSET_____SYNCHRONOUS__WITH____RETURN);
		compatibilityTable.put(RECEIVER + ExchangeMechanism.UNSET + MessageKind.SYNCHRONOUS_CALL  + WITH_RETURN, COMPATIBLE_RECEIVER_FOR_UNSET_____SYNCHRONOUS__WITH____RETURN);
		compatibilityTable.put(SENDER   + ExchangeMechanism.UNSET + MessageKind.ASYNCHRONOUS_CALL + WITH_RETURN, COMPATIBLE_SENDER___FOR_UNSET_____ASYNCHRONOUS_WITH____RETURN);
		compatibilityTable.put(RECEIVER + ExchangeMechanism.UNSET + MessageKind.ASYNCHRONOUS_CALL + WITH_RETURN, COMPATIBLE_RECEIVER_FOR_UNSET_____ASYNCHRONOUS_WITH____RETURN);
		compatibilityTable.put(SENDER   + ExchangeMechanism.UNSET + MessageKind.ASYNCHRONOUS_CALL + !WITH_RETURN, COMPATIBLE_SENDER___FOR_UNSET_____ASYNCHRONOUS_WITHOUT_RETURN);
		compatibilityTable.put(RECEIVER + ExchangeMechanism.UNSET + MessageKind.ASYNCHRONOUS_CALL + !WITH_RETURN, COMPATIBLE_RECEIVER_FOR_UNSET_____ASYNCHRONOUS_WITHOUT_RETURN);
		
		compatibilityTable.put(SENDER   + ExchangeMechanism.EVENT + MessageKind.SYNCHRONOUS_CALL  + WITH_RETURN, COMPATIBLE_SENDER___FOR_EVENT_____SYNCHRONOUS__WITH____RETURN);
		compatibilityTable.put(RECEIVER + ExchangeMechanism.EVENT + MessageKind.SYNCHRONOUS_CALL  + WITH_RETURN, COMPATIBLE_RECEIVER_FOR_EVENT_____SYNCHRONOUS__WITH____RETURN);
		compatibilityTable.put(SENDER   + ExchangeMechanism.EVENT + MessageKind.ASYNCHRONOUS_CALL + WITH_RETURN, COMPATIBLE_SENDER___FOR_EVENT_____ASYNCHRONOUS_WITH____RETURN);
		compatibilityTable.put(RECEIVER + ExchangeMechanism.EVENT + MessageKind.ASYNCHRONOUS_CALL + WITH_RETURN, COMPATIBLE_RECEIVER_FOR_EVENT_____ASYNCHRONOUS_WITH____RETURN);
		compatibilityTable.put(SENDER   + ExchangeMechanism.EVENT + MessageKind.ASYNCHRONOUS_CALL + !WITH_RETURN, COMPATIBLE_SENDER___FOR_EVENT_____ASYNCHRONOUS_WITHOUT_RETURN);
		compatibilityTable.put(RECEIVER + ExchangeMechanism.EVENT + MessageKind.ASYNCHRONOUS_CALL + !WITH_RETURN, COMPATIBLE_RECEIVER_FOR_EVENT_____ASYNCHRONOUS_WITHOUT_RETURN);
		
		compatibilityTable.put(SENDER   + ExchangeMechanism.FLOW + MessageKind.SYNCHRONOUS_CALL  + WITH_RETURN, COMPATIBLE_SENDER___FOR_FLOW______SYNCHRONOUS__WITH____RETURN);
		compatibilityTable.put(RECEIVER + ExchangeMechanism.FLOW + MessageKind.SYNCHRONOUS_CALL  + WITH_RETURN, COMPATIBLE_RECEIVER_FOR_FLOW______SYNCHRONOUS__WITH____RETURN);
		compatibilityTable.put(SENDER   + ExchangeMechanism.FLOW + MessageKind.ASYNCHRONOUS_CALL + WITH_RETURN, COMPATIBLE_SENDER___FOR_FLOW______ASYNCHRONOUS_WITH____RETURN);
		compatibilityTable.put(RECEIVER + ExchangeMechanism.FLOW + MessageKind.ASYNCHRONOUS_CALL + WITH_RETURN, COMPATIBLE_RECEIVER_FOR_FLOW______ASYNCHRONOUS_WITH____RETURN);
		compatibilityTable.put(SENDER   + ExchangeMechanism.FLOW + MessageKind.ASYNCHRONOUS_CALL + !WITH_RETURN, COMPATIBLE_SENDER___FOR_FLOW______ASYNCHRONOUS_WITHOUT_RETURN);
		compatibilityTable.put(RECEIVER + ExchangeMechanism.FLOW + MessageKind.ASYNCHRONOUS_CALL + !WITH_RETURN, COMPATIBLE_RECEIVER_FOR_FLOW______ASYNCHRONOUS_WITHOUT_RETURN);

		compatibilityTable.put(SENDER   + ExchangeMechanism.OPERATION + MessageKind.SYNCHRONOUS_CALL  + WITH_RETURN, COMPATIBLE_SENDER___FOR_OPERATION_SYNCHRONOUS__WITH____RETURN);
		compatibilityTable.put(RECEIVER + ExchangeMechanism.OPERATION + MessageKind.SYNCHRONOUS_CALL  + WITH_RETURN, COMPATIBLE_RECEIVER_FOR_OPERATION_SYNCHRONOUS__WITH____RETURN);
		compatibilityTable.put(SENDER   + ExchangeMechanism.OPERATION + MessageKind.ASYNCHRONOUS_CALL + WITH_RETURN, COMPATIBLE_SENDER___FOR_OPERATION_ASYNCHRONOUS_WITH____RETURN);
		compatibilityTable.put(RECEIVER + ExchangeMechanism.OPERATION + MessageKind.ASYNCHRONOUS_CALL + WITH_RETURN, COMPATIBLE_RECEIVER_FOR_OPERATION_ASYNCHRONOUS_WITH____RETURN);
		compatibilityTable.put(SENDER   + ExchangeMechanism.OPERATION + MessageKind.ASYNCHRONOUS_CALL + !WITH_RETURN, COMPATIBLE_SENDER___FOR_OPERATION_ASYNCHRONOUS_WITHOUT_RETURN);
		compatibilityTable.put(RECEIVER + ExchangeMechanism.OPERATION + MessageKind.ASYNCHRONOUS_CALL + !WITH_RETURN, COMPATIBLE_RECEIVER_FOR_OPERATION_ASYNCHRONOUS_WITHOUT_RETURN);

		compatibilityTable.put(SENDER   + ExchangeMechanism.SHARED_DATA + MessageKind.SYNCHRONOUS_CALL  + WITH_RETURN, COMPATIBLE_SENDER___FOR_DATA______SYNCHRONOUS__WITH____RETURN);
		compatibilityTable.put(RECEIVER + ExchangeMechanism.SHARED_DATA + MessageKind.SYNCHRONOUS_CALL  + WITH_RETURN, COMPATIBLE_RECEIVER_FOR_DATA______SYNCHRONOUS__WITH____RETURN);
		compatibilityTable.put(SENDER   + ExchangeMechanism.SHARED_DATA + MessageKind.ASYNCHRONOUS_CALL + WITH_RETURN, COMPATIBLE_SENDER___FOR_DATA______ASYNCHRONOUS_WITH____RETURN);
		compatibilityTable.put(RECEIVER + ExchangeMechanism.SHARED_DATA + MessageKind.ASYNCHRONOUS_CALL + WITH_RETURN, COMPATIBLE_RECEIVER_FOR_DATA______ASYNCHRONOUS_WITH____RETURN);
		compatibilityTable.put(SENDER   + ExchangeMechanism.SHARED_DATA + MessageKind.ASYNCHRONOUS_CALL + !WITH_RETURN, COMPATIBLE_SENDER___FOR_DATA______ASYNCHRONOUS_WITHOUT_RETURN);
		compatibilityTable.put(RECEIVER + ExchangeMechanism.SHARED_DATA + MessageKind.ASYNCHRONOUS_CALL + !WITH_RETURN, COMPATIBLE_RECEIVER_FOR_DATA______ASYNCHRONOUS_WITHOUT_RETURN);
	}
	
	// fields "senderProtocol" and "receiverProtocol" can be null since we treat possibly partial communications
	public boolean isCompatible(ExchangeMechanism mechanism, CommunicationLinkProtocol senderProtocol, CommunicationLinkProtocol receiverProtocol, MessageKind messageKind, boolean withReturn) {
		String tableKey = mechanism.toString()+messageKind+withReturn;
		boolean compatible = true;
		if (senderProtocol != null) {
			List<CommunicationLinkProtocol> possibleSenderProtocols = compatibilityTable.get(SENDER+tableKey);
			compatible = possibleSenderProtocols != null ? possibleSenderProtocols.contains(senderProtocol) : false;
		}
		if (compatible && receiverProtocol != null) {
			List<CommunicationLinkProtocol> possibleReceiverProtocols = compatibilityTable.get(RECEIVER+tableKey);
			compatible = possibleReceiverProtocols != null ? possibleReceiverProtocols.contains(receiverProtocol) : false;
		}
		return compatible;
	}
	
	public boolean isCompatible(CommunicationInfo comInfo, MessageKind messageKind, boolean withReturn) {
		return isCompatible(comInfo.mechanism, comInfo.senderProtocol, comInfo.receiverProtocol, messageKind, withReturn);
	}
	
	public boolean isCompatible(ExchangeItem item, MessageKind messageProtocol, boolean withReturn) {
		ExchangeMechanism mechanism = item.getExchangeMechanism();
		if (mechanism == ExchangeMechanism.UNSET) {
			return false;
		} else if (mechanism == ExchangeMechanism.EVENT && messageProtocol == MessageKind.ASYNCHRONOUS_CALL && !withReturn) {
			return true;
		} else if (mechanism == ExchangeMechanism.FLOW) {
			return true;
		} else if (mechanism == ExchangeMechanism.OPERATION) {
			return true;
		} else if (mechanism == ExchangeMechanism.SHARED_DATA) {
			return true;
		}
		return false;
	}

	/** get a compatible protocol (for sender or receiver). Try to not return UNSET if possible. */
	public CommunicationLinkProtocol getCompatibleProtocol(boolean protocolIsSender, ExchangeMechanism mechanism, MessageKind messageKind, boolean withReturn) {
		String protocolDirection = RECEIVER;
		if (protocolIsSender) {
			protocolDirection = SENDER;
		}
		String tableKey = mechanism.toString()+messageKind+withReturn;
		List<CommunicationLinkProtocol> possibleProtocols = compatibilityTable.get(protocolDirection+tableKey);
		CommunicationLinkProtocol protocol = null;
		if (possibleProtocols.size() > 0) {
			for (int i = 0; i < possibleProtocols.size(); i++) {
				protocol = possibleProtocols.get(i);
				if (protocol != UNSET) {
					return protocol;
				}
			}			
		}
		return protocol;
	}
		
	public List<ExchangeMechanism> getCompatibleExchangeMechanismFor(boolean withReturn) {
		if (withReturn) {
			return COMPATIBLE_EXCHANGE_MECHANISM_FOR_WITH_RETURN;
		} else {
			return COMPATIBLE_EXCHANGE_MECHANISM_FOR_WITHOUT_RETURN;			
		}
	}
	
}
