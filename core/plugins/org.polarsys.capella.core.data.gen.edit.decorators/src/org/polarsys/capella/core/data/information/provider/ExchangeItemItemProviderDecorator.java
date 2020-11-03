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
package org.polarsys.capella.core.data.information.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.polarsys.capella.core.data.capellamodeller.provider.CapellaModellerEditPlugin;
import org.polarsys.capella.core.data.gen.edit.decorators.ItemProviderAdapterDecorator;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeMechanism;

public class ExchangeItemItemProviderDecorator extends
		ItemProviderAdapterDecorator implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider,
		IItemLabelProvider, IItemPropertySource {

	public ExchangeItemItemProviderDecorator(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public Object getImage(Object object) {
	    ExchangeItem item = (ExchangeItem)object;
	    String imagePath = "full/obj16/ExchangeItem"; //$NON-NLS-1$
	    if (item.getExchangeMechanism()==ExchangeMechanism.EVENT) {
	      imagePath = "full/obj16/ExchangeItemEvent"; //$NON-NLS-1$
	    } else if (item.getExchangeMechanism()==ExchangeMechanism.FLOW) {
	      imagePath = "full/obj16/ExchangeItemFlow"; //$NON-NLS-1$
	    } else if (item.getExchangeMechanism()==ExchangeMechanism.OPERATION) {
	      imagePath = "full/obj16/ExchangeItemOperation"; //$NON-NLS-1$
	    } else if (item.getExchangeMechanism()==ExchangeMechanism.SHARED_DATA) { 
	      imagePath = "full/obj16/ExchangeItemData"; //$NON-NLS-1$
	    }
	    
	    return overlayImage(object, CapellaModellerEditPlugin.INSTANCE.getImage(imagePath));
	}
}
