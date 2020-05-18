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
package org.polarsys.capella.core.data.cs.provider;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.data.capellamodeller.provider.CapellaModellerEditPlugin;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.gen.edit.decorators.ItemProviderAdapterDecorator;

public class PartItemProviderDecorator extends ItemProviderAdapterDecorator implements IEditingDomainItemProvider,
    IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

  public PartItemProviderDecorator(AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

  
  @Override
  public Object getImage(Object object) {
    AbstractType type = ((Part) object).getAbstractType();
    if (type != null) {
      IItemLabelProvider labelProvider = (IItemLabelProvider) getRootAdapterFactory().adapt(type,
          IItemLabelProvider.class);
      return new ComposedImage(Arrays.asList(labelProvider.getImage(type),
          CapellaModellerEditPlugin.INSTANCE.getImage("full/ovr16/PartOverlay")));
    }
    return overlayImage(object, CapellaModellerEditPlugin.INSTANCE.getImage("full/obj16/Part"));
  }
}
