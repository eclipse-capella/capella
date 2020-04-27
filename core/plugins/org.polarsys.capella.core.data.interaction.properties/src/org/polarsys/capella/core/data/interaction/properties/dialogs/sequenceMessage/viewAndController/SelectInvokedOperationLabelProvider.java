/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.viewAndController;

import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataLabelProvider;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.ISelectInvokedOperationModel;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.algorithms.CompatibilityDefinition;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.communications.AbstractCommunication;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.communications.LinkCommunication;

public class SelectInvokedOperationLabelProvider extends DataLabelProvider {

	protected ISelectInvokedOperationModel model;
	
	public SelectInvokedOperationLabelProvider(ISelectInvokedOperationModel model) {
		super();
		this.model = model;
	}
	
  @Override
  public Image getImage(Object object) {
  	if (object instanceof AbstractCommunication) {
  		return super.getImage(((AbstractCommunication) object).getRepresentativeElement());
  	}
  	return super.getImage(object);
  }
		
  @Override
  public String getText(Object object) {
    // we  hide the name of technical interfaces if the option is active
		if (object instanceof Interface) {
			Interface interfaze = (Interface) object;
			if (!interfaze.isStructural() && model.doesHideTechnicalInterfaceNames()) {
				return ""; //$NON-NLS-1$				
			}
		} else if (object instanceof LinkCommunication) {
			LinkCommunication com = (LinkCommunication) object;
			String senderProtocolString = null;
			String receiverProtocolString = null;
			if (com.senderLink != null) {
				senderProtocolString = com.senderLink.getProtocol().toString();
			} else {
				CommunicationLinkProtocol protocol = CompatibilityDefinition.INSTANCE.getCompatibleProtocol(true, com.exchangeItem.getExchangeMechanism(), model.getMessageKind(), model.doesTheMessageReturnAValue());
				senderProtocolString = protocol != null ? "+"+protocol.toString() : ""; //$NON-NLS-1$ //$NON-NLS-2$				
			}
			if (com.receiverLink != null) {
				receiverProtocolString = com.receiverLink.getProtocol().toString();
			} else {
				CommunicationLinkProtocol protocol = CompatibilityDefinition.INSTANCE.getCompatibleProtocol(false, com.exchangeItem.getExchangeMechanism(), model.getMessageKind(), model.doesTheMessageReturnAValue());
				receiverProtocolString = protocol != null ? "+"+protocol.toString() : ""; //$NON-NLS-1$ //$NON-NLS-2$
			}
			CommunicationLink link = (CommunicationLink) com.getRepresentativeElement();
			return super.getText(link)+" ("+link.getExchangeItem().getExchangeMechanism()+") ["+senderProtocolString+"/"+receiverProtocolString+"]";  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		}
		return super.getText(object);
	}
}
