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
package org.polarsys.capella.core.data.fa.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
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
    String text = super.getText(object);

    for (IItemLabelProvider labelProvider : getDelegatedDecorators((EObject) object)) {
      String position = getDecoratorPosition(labelProvider);
      if (DECORATOR_POSITION_PREFIX.equals(position)) {
        text = labelProvider.getText(object) + text;
      } else if (DECORATOR_POSITION_SUFFIX.equals(position)) {
        text = text + labelProvider.getText(object);
      } else if (DECORATOR_POSITION_OVERRIDES.equals(position)) {
        text = labelProvider.getText(object);
      }
    }

    return text;
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
