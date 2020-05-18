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

package org.polarsys.capella.core.data.la.provider;

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
import org.polarsys.capella.core.data.gen.edit.decorators.ItemProviderAdapterDecorator;
import org.polarsys.capella.core.data.gen.edit.decorators.Messages;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentExt;

public class LogicalComponentItemProviderDecorator extends ItemProviderAdapterDecorator
    implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider,
    IItemPropertySource {

  public LogicalComponentItemProviderDecorator(AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

  @Override
  public Object getImage(Object object) {
    LogicalComponent component = (LogicalComponent) object;
    String imagePath;

    if (component.isActor()) {
      if (component.isHuman()) {
        imagePath = "full/obj16/LogicalActorHuman";
      } else {
        imagePath = "full/obj16/LogicalActor";
      }
    } else {
      if (component.isHuman()) {
        imagePath = "full/obj16/LogicalComponentHuman";
      } else {
        imagePath = "full/obj16/LogicalComponent";
      }
    }

    return overlayImage(object, CapellaModellerEditPlugin.INSTANCE.getImage(imagePath));
  }

  @SuppressWarnings("unchecked")
  @Override
  public Collection<?> getNewChildDescriptors(Object object, EditingDomain editingDomain, Object sibling) {
    Collection<Object> newChildDescriptors = (Collection<Object>) super.getNewChildDescriptors(object, editingDomain,
        sibling);

    LogicalComponent container = (LogicalComponent) object;

    if (ComponentExt.canCreateABActor(container)) {
      DynamicCommandParameter descriptor = new DynamicCommandParameter(null,
          LaPackage.Literals.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENTS, ComponentExt.createLogicalActor(),
          Messages.CreationMenuLabel_LogicalActor);

      newChildDescriptors.add(descriptor);
    }

    if (ComponentExt.canCreateABComponent(container)) {
      DynamicCommandParameter descriptor = new DynamicCommandParameter(null,
          LaPackage.Literals.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENTS, ComponentExt.createLogicalComponent(),
          Messages.CreationMenuLabel_LogicalComponent);

      newChildDescriptors.add(descriptor);
    }

    return newChildDescriptors;
  }
}
