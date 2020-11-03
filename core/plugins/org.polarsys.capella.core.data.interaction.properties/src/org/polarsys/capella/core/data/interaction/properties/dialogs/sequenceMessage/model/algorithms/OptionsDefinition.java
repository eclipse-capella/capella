/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.communications.AbstractCommunication;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.communications.InterfaceCommunication;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.communications.LinkCommunication;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.communications.UndefinedCommunication;

public class OptionsDefinition {
	
	protected final static boolean [] optionD_defaultValue = new boolean [] {false, false, false, false};
	protected final static boolean [] optionD_toggable     = new boolean [] {true, true, false, false};

	protected final static boolean [] optionE_defaultValue = new boolean [] {false, false, false, true};
	protected final static boolean [] optionE_toggable     = new boolean [] {true, true, false, false};
	
	public static final OptionsDefinition INSTANCE = new OptionsDefinition();

	public boolean getDefaultValueForOptionD(AbstractCommunication element) {
		if (element == null) {
			return optionD_defaultValue[0];
		}	else if (element instanceof ExchangeItem) {
			return optionD_defaultValue[1]; 
		} else if (element instanceof ExchangeItemAllocation) {
			return optionD_defaultValue[2];
		} else if (element instanceof CommunicationLink) {
			return optionD_defaultValue[3];
		}
		return false;		
	}

	public boolean doesOptionDIsTogglable(AbstractCommunication element) {
		if (element == null) {
			return optionD_toggable[0];
		}	else if (element instanceof ExchangeItem) {
			return optionD_toggable[1]; 
		} else if (element instanceof ExchangeItemAllocation) {
			return optionD_toggable[2];
		} else if (element instanceof CommunicationLink) {
			return optionD_toggable[3];
		}
		return false;
	}

	public boolean getDefaultValueForOptionE(AbstractCommunication element) {
		if (element == null) {
			return optionE_defaultValue[0];
		}	else if (element instanceof ExchangeItem) {
			return optionE_defaultValue[1]; 
		} else if (element instanceof ExchangeItemAllocation) {
			return optionE_defaultValue[2];
		} else if (element instanceof CommunicationLink) {
			return optionE_defaultValue[3];
		}
		return false;		
	}

	public boolean doesOptionEIsTogglable(AbstractCommunication element) {
		if (element == null) {
			return optionE_toggable[0];
		}	else if (element instanceof ExchangeItem) {
			return optionE_toggable[1]; 
		} else if (element instanceof ExchangeItemAllocation) {
			return optionE_toggable[2];
		} else if (element instanceof CommunicationLink) {
			return optionE_toggable[3];
		}
		return false;
	}

}
