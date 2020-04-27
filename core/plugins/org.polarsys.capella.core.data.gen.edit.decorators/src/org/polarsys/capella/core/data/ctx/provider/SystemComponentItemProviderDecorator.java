/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.ctx.provider;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.polarsys.capella.common.menu.dynamic.util.DynamicCommandParameter;
import org.polarsys.capella.core.data.capellamodeller.provider.CapellaModellerEditPlugin;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.gen.edit.decorators.ItemProviderAdapterDecorator;
import org.polarsys.capella.core.data.gen.edit.decorators.Messages;
import org.polarsys.capella.core.model.helpers.ComponentExt;

public class SystemComponentItemProviderDecorator extends ItemProviderAdapterDecorator
    implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider,
    IItemPropertySource {

  public SystemComponentItemProviderDecorator(AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

  @Override
  public Object getImage(Object object) {
    SystemComponent component = (SystemComponent) object;
    String imagePath = "full/obj16/SystemComponent";

    if (component.isActor()) {
      if (component.isHuman()) {
        imagePath = "full/obj16/SystemActorHuman";
      } else {
        imagePath = "full/obj16/SystemActor";
      }
    }

    return overlayImage(object, CapellaModellerEditPlugin.INSTANCE.getImage(imagePath));
  }

  @SuppressWarnings("unchecked")
  @Override
  public Collection<?> getNewChildDescriptors(Object object, EditingDomain editingDomain, Object sibling) {
    Collection<Object> newChildDescriptors = (Collection<Object>) super.getNewChildDescriptors(object, editingDomain,
        sibling);

    SystemComponent container = (SystemComponent) object;

    if (ComponentExt.canCreateABActor(container)) {
      DynamicCommandParameter descriptor = new DynamicCommandParameter(null,
          CtxPackage.Literals.SYSTEM_COMPONENT__OWNED_SYSTEM_COMPONENTS, ComponentExt.createSystemActor(),
          Messages.CreationMenuLabel_SystemActor);

      newChildDescriptors.add(descriptor);
    }

    if (ComponentExt.isActor(container)) {
      DynamicCommandParameter descriptor = new DynamicCommandParameter(null,
          CtxPackage.Literals.SYSTEM_COMPONENT__OWNED_SYSTEM_COMPONENT_PKGS,
          CtxFactory.eINSTANCE.createSystemComponentPkg(), Messages.CreationMenuLabel_SystemComponentPkg);

      newChildDescriptors.add(descriptor);
    }

    return newChildDescriptors;
  }
}
