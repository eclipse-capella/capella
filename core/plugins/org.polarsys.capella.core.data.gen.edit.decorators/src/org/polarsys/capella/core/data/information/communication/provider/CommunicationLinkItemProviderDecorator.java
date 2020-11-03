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
package org.polarsys.capella.core.data.information.communication.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.polarsys.capella.core.data.capellamodeller.provider.CapellaModellerEditPlugin;
import org.polarsys.capella.core.data.gen.edit.decorators.ItemProviderAdapterDecorator;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkKind;

public class CommunicationLinkItemProviderDecorator extends
		ItemProviderAdapterDecorator implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider,
		IItemLabelProvider, IItemPropertySource {

	public CommunicationLinkItemProviderDecorator(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public Object getImage(Object object) {
	    CommunicationLink item = (CommunicationLink)object;
	    String imagePath = "full/obj16/CommunicationLink"; //$NON-NLS-1$
	    if (item.getKind()==CommunicationLinkKind.PRODUCE) {
	      imagePath = "full/obj16/CommunicationLinkProduce"; //$NON-NLS-1$
	    } else if (item.getKind()==CommunicationLinkKind.CONSUME) {
	      imagePath = "full/obj16/CommunicationLinkConsume"; //$NON-NLS-1$
	    } else if (item.getKind()==CommunicationLinkKind.SEND) {
	      imagePath = "full/obj16/CommunicationLinkSend"; //$NON-NLS-1$
	    } else if (item.getKind()==CommunicationLinkKind.RECEIVE) { 
	      imagePath = "full/obj16/CommunicationLinkReceive"; //$NON-NLS-1$
	    } else if (item.getKind()==CommunicationLinkKind.CALL) { 
	      imagePath = "full/obj16/CommunicationLinkCall"; //$NON-NLS-1$
	    } else if (item.getKind()==CommunicationLinkKind.EXECUTE) { 
	      imagePath = "full/obj16/CommunicationLinkExecute"; //$NON-NLS-1$
	    } else if (item.getKind()==CommunicationLinkKind.WRITE) { 
	      imagePath = "full/obj16/CommunicationLinkWrite"; //$NON-NLS-1$
	    } else if (item.getKind()==CommunicationLinkKind.ACCESS) { 
	      imagePath = "full/obj16/CommunicationLinkAccess"; //$NON-NLS-1$
	    } else if (item.getKind()==CommunicationLinkKind.ACQUIRE) { 
	      imagePath = "full/obj16/CommunicationLinkAcquire"; //$NON-NLS-1$
	    } else if (item.getKind()==CommunicationLinkKind.TRANSMIT) { 
	      imagePath = "full/obj16/CommunicationLinkTransmit"; //$NON-NLS-1$
	    }
	    
	    return overlayImage(object, CapellaModellerEditPlugin.INSTANCE.getImage(imagePath));
	}
}
