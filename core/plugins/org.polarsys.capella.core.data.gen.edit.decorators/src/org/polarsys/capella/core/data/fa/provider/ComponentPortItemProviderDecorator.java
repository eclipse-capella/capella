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
package org.polarsys.capella.core.data.fa.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.polarsys.capella.core.data.capellamodeller.provider.CapellaModellerEditPlugin;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ComponentPortKind;
import org.polarsys.capella.core.data.fa.OrientationPortKind;
import org.polarsys.capella.core.data.gen.edit.decorators.ItemProviderAdapterDecorator;

public class ComponentPortItemProviderDecorator extends
		ItemProviderAdapterDecorator implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider,
		IItemLabelProvider, IItemPropertySource {

	public ComponentPortItemProviderDecorator(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public Object getImage(Object object) {
	    ComponentPort item = (ComponentPort)object;
	    String imagePath = "full/obj16/ComponentPort"; //$NON-NLS-1$
	    if (ComponentPortKind.STANDARD.equals(item.getKind())) {
	      imagePath = "full/obj16/StandardPort"; //$NON-NLS-1$
	    } else if (ComponentPortKind.FLOW.equals(item.getKind())) {
	      imagePath = "full/obj16/FlowPort"; //$NON-NLS-1$
	      if (OrientationPortKind.IN.equals(item.getOrientation())) {
	        imagePath = "full/obj16/InFlowPort"; //$NON-NLS-1$
	      } else if (OrientationPortKind.OUT.equals(item.getOrientation())) {
	        imagePath = "full/obj16/OutFlowPort"; //$NON-NLS-1$
	      }
	    }

	    return overlayImage(object, CapellaModellerEditPlugin.INSTANCE.getImage(imagePath));
	}
}
