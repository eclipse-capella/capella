/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.pa.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.polarsys.capella.common.menu.dynamic.util.DynamicCommandParameter;
import org.polarsys.capella.core.data.gen.edit.decorators.ItemProviderAdapterDecorator;
import org.polarsys.capella.core.data.gen.edit.decorators.Messages;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.model.helpers.ComponentExt;

public abstract class AbstractPhysicalComponentItemProviderDecorator extends ItemProviderAdapterDecorator
    implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider,
    IItemPropertySource {

  public AbstractPhysicalComponentItemProviderDecorator(AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

  protected DynamicCommandParameter createComponentBCDescriptor(EReference reference) {
    PhysicalComponent componentBC = ComponentExt.createPhysicalComponent();
    componentBC.setNature(PhysicalComponentNature.BEHAVIOR);

    return new DynamicCommandParameter(null, reference, componentBC, Messages.CreationMenuLabel_PhysicalComponent_BC);
  }

  protected DynamicCommandParameter createComponentICDescriptor(EReference reference) {
    PhysicalComponent componentIC = ComponentExt.createPhysicalComponent();
    componentIC.setNature(PhysicalComponentNature.NODE);

    return new DynamicCommandParameter(null, reference, componentIC, Messages.CreationMenuLabel_PhysicalComponent_IC);
  }

}
