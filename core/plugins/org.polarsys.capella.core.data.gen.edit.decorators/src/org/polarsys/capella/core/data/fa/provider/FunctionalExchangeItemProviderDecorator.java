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
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.gen.edit.decorators.ItemProviderAdapterDecorator;
import org.polarsys.capella.core.data.oa.OperationalActivity;

public class FunctionalExchangeItemProviderDecorator extends
		ItemProviderAdapterDecorator implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider,
		IItemLabelProvider, IItemPropertySource {

	public FunctionalExchangeItemProviderDecorator(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

  @Override
  public String getText(Object object) {
    return super.getText(object);
  }

	@Override
	public Object getImage(Object object) {
	    FunctionalExchange item = (FunctionalExchange)object;
	    String imagePath = "full/obj16/FunctionalExchange"; //$NON-NLS-1$
	    if (item.getSource() instanceof OperationalActivity
	     && item.getTarget() instanceof OperationalActivity)
	    {
	      imagePath = "full/obj16/FunctionalExchange_OA"; //$NON-NLS-1$
	    }

	    return overlayImage(object, CapellaModellerEditPlugin.INSTANCE.getImage(imagePath));
	}
}
