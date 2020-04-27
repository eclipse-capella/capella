/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
import java.util.Arrays;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;

import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkKind;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;
import org.polarsys.capella.core.data.interaction.MessageKind;

public class LinkCompatibilityDefinition {

	private static final CommunicationLinkProtocol UNSET = CommunicationLinkProtocol.UNSET;
	private static final CommunicationLinkProtocol SYNCHRONOUS = CommunicationLinkProtocol.SYNCHRONOUS;
	private static final CommunicationLinkProtocol ASYNCHRONOUS = CommunicationLinkProtocol.ASYNCHRONOUS;
	private static final CommunicationLinkProtocol UNICAST = CommunicationLinkProtocol.UNICAST;
	private static final CommunicationLinkProtocol MULTICAST = CommunicationLinkProtocol.MULTICAST;
	private static final CommunicationLinkProtocol BROADCAST = CommunicationLinkProtocol.BROADCAST;
	private static final CommunicationLinkProtocol ACCEPT = CommunicationLinkProtocol.ACCEPT;
	private static final CommunicationLinkProtocol READ = CommunicationLinkProtocol.READ;

	public static final int SENDER_ID = 0;
	public static final int RECEIVER_ID = 1;
  
	private static final String SENDER = Integer.toString(SENDER_ID);
	private static final String RECEIVER = Integer.toString(RECEIVER_ID);
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
	private static final List<CommunicationLinkProtocol> COMPATIBLE_SENDER___FOR_EVENT_____ASYNCHRONOUS_WITHOUT_RETURN = Arrays.asList(new CommunicationLinkProtocol[] {UNICAST, MULTICAST, BROADCAST, UNSET});
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

  
  protected Hashtable<ExchangeMechanism, List<CommunicationLinkKind>> mechanismKind;
  
  protected Hashtable<CommunicationLinkKind, List<CommunicationLinkProtocol>> kindProtocol;

	protected Hashtable<String, List<CommunicationLinkProtocol>> compatibilityTable;

	public static final LinkCompatibilityDefinition INSTANCE = new LinkCompatibilityDefinition();
	
	
	public List<CommunicationLinkKind> getKinds(ExchangeMechanism mechanism) {
	  return new ArrayList<CommunicationLinkKind>(mechanismKind.get(mechanism));
	} 
	
	public CommunicationLinkKind getKind(ExchangeMechanism mechanism, boolean isSender) {
    if (mechanism != null) {
      if (isSender) {
        return mechanismKind.get(mechanism).get(SENDER_ID);
      }
      return mechanismKind.get(mechanism).get(RECEIVER_ID);
    }
    return CommunicationLinkKind.UNSET;
  }
  
	public ExchangeMechanism getMechanism(CommunicationLinkKind kind) {
	  for (Entry<ExchangeMechanism, List<CommunicationLinkKind>> entry : mechanismKind.entrySet()) {
	    if (entry.getValue().contains(kind)) {
	      return entry.getKey();
	    }
	  }
    return ExchangeMechanism.UNSET;
  }


  public Collection<CommunicationLinkProtocol> getCompatibleReceiveProtocols(ExchangeItemAllocation allocation) {
    return getCompatibleProtocols(allocation, false);
  }
  
  public Collection<CommunicationLinkProtocol> getCompatibleSendProtocols(ExchangeItemAllocation allocation) {
    return getCompatibleProtocols(allocation, true);
  }
  
  private Collection<CommunicationLinkProtocol> getCompatibleProtocols(ExchangeItemAllocation allocation, boolean isSender) {
    ExchangeMechanism mechanism = ((ExchangeItemAllocation) allocation).getAllocatedItem().getExchangeMechanism();
    CommunicationLinkKind expectedKind = LinkCompatibilityDefinition.INSTANCE.getKind(mechanism, isSender);
    return LinkCompatibilityDefinition.INSTANCE.getCompatibleProtocols(expectedKind);
  }
  
	
  public Collection<CommunicationLinkProtocol> getCompatibleProtocols(CommunicationLinkKind kind) {
    return (kindProtocol.get(kind));
  }
  
  public CommunicationLinkKind getKind(CommunicationLinkProtocol protocol) {
    for (Entry<CommunicationLinkKind, List<CommunicationLinkProtocol>> entry : kindProtocol.entrySet()) {
      if (entry.getValue().contains(protocol)) {
        return entry.getKey();
      }
    }
    return CommunicationLinkKind.UNSET;
  }
  
  
	//initialize the table of definitions to speed up compatibility checks and element updates
	public LinkCompatibilityDefinition() {
		super();
		
		//<ExchangeItemMechanism, Sender, Receiver>
		mechanismKind = new Hashtable<ExchangeMechanism, List<CommunicationLinkKind>>();
		mechanismKind.put(ExchangeMechanism.FLOW , Arrays.asList(new CommunicationLinkKind[] {CommunicationLinkKind.PRODUCE, CommunicationLinkKind.CONSUME}));
		mechanismKind.put(ExchangeMechanism.EVENT , Arrays.asList(new CommunicationLinkKind[] {CommunicationLinkKind.SEND, CommunicationLinkKind.RECEIVE}));
		mechanismKind.put(ExchangeMechanism.OPERATION , Arrays.asList(new CommunicationLinkKind[] {CommunicationLinkKind.CALL, CommunicationLinkKind.EXECUTE}));
		mechanismKind.put(ExchangeMechanism.SHARED_DATA, Arrays.asList(new CommunicationLinkKind[] {CommunicationLinkKind.WRITE, CommunicationLinkKind.ACCESS}));
		mechanismKind.put(ExchangeMechanism.UNSET, Arrays.asList(new CommunicationLinkKind[] {CommunicationLinkKind.TRANSMIT, CommunicationLinkKind.ACQUIRE}));
		
		kindProtocol = new Hashtable<CommunicationLinkKind, List<CommunicationLinkProtocol>>();
		kindProtocol.put(CommunicationLinkKind.SEND, Arrays.asList(new CommunicationLinkProtocol[] { CommunicationLinkProtocol.UNICAST, CommunicationLinkProtocol.BROADCAST, CommunicationLinkProtocol.MULTICAST, CommunicationLinkProtocol.UNSET }));
    kindProtocol.put(CommunicationLinkKind.CALL, Arrays.asList(new CommunicationLinkProtocol[] { CommunicationLinkProtocol.ASYNCHRONOUS, CommunicationLinkProtocol.SYNCHRONOUS, CommunicationLinkProtocol.UNSET }));
    kindProtocol.put(CommunicationLinkKind.ACCESS, Arrays.asList(new CommunicationLinkProtocol[] { CommunicationLinkProtocol.READ,  CommunicationLinkProtocol.ACCEPT, CommunicationLinkProtocol.UNSET }));
		
    kindProtocol.put(CommunicationLinkKind.ACQUIRE, Arrays.asList(new CommunicationLinkProtocol[] { CommunicationLinkProtocol.UNSET }));
    kindProtocol.put(CommunicationLinkKind.CONSUME, Arrays.asList(new CommunicationLinkProtocol[] { CommunicationLinkProtocol.UNSET }));
    kindProtocol.put(CommunicationLinkKind.EXECUTE, Arrays.asList(new CommunicationLinkProtocol[] { CommunicationLinkProtocol.UNSET }));
    kindProtocol.put(CommunicationLinkKind.PRODUCE, Arrays.asList(new CommunicationLinkProtocol[] { CommunicationLinkProtocol.UNSET }));
    kindProtocol.put(CommunicationLinkKind.RECEIVE, Arrays.asList(new CommunicationLinkProtocol[] { CommunicationLinkProtocol.UNSET }));
    kindProtocol.put(CommunicationLinkKind.TRANSMIT, Arrays.asList(new CommunicationLinkProtocol[] { CommunicationLinkProtocol.UNSET }));
    kindProtocol.put(CommunicationLinkKind.WRITE, Arrays.asList(new CommunicationLinkProtocol[] { CommunicationLinkProtocol.UNSET }));
    kindProtocol.put(CommunicationLinkKind.UNSET, Arrays.asList(new CommunicationLinkProtocol[] { CommunicationLinkProtocol.UNSET }));
    
		
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

	/** get a compatible protocol (for sender or receiver). */
  public List<CommunicationLinkProtocol> getCompatibleProtocols(boolean protocolIsSender, ExchangeMechanism mechanism, MessageKind messageKind, boolean withReturn) {
    String protocolDirection = RECEIVER;
    if (protocolIsSender) {
      protocolDirection = SENDER;
    }
    String tableKey = mechanism.toString()+messageKind+withReturn;
    List<CommunicationLinkProtocol> possibleProtocols = compatibilityTable.get(protocolDirection+tableKey);
    if (possibleProtocols == null) {
      possibleProtocols = new ArrayList<CommunicationLinkProtocol>();
    }
    return possibleProtocols;
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
		if (possibleProtocols != null) {
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
		}
		return COMPATIBLE_EXCHANGE_MECHANISM_FOR_WITHOUT_RETURN;
	}

  public boolean isDefaultKind(CommunicationLinkKind kind) {
    return kind == CommunicationLinkKind.TRANSMIT || kind == CommunicationLinkKind.ACQUIRE;
  }

	
}
