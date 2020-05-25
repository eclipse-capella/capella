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

package org.polarsys.capella.core.data.oa.provider;

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
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.model.helpers.ComponentExt;

public class EntityItemProviderDecorator extends ItemProviderAdapterDecorator implements IEditingDomainItemProvider,
    IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

  public EntityItemProviderDecorator(AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

  @Override
  public Object getImage(Object object) {
    Entity entity = (Entity) object;
    String imagePath = "full/obj16/Entity";

    if (entity.isActor()) {
      if (entity.isHuman()) {
        imagePath = "full/obj16/Actor";
      } else {
        imagePath = "full/obj16/OperationalActor";
      }
    }

    return overlayImage(object, CapellaModellerEditPlugin.INSTANCE.getImage(imagePath));
  }

  @SuppressWarnings("unchecked")
  @Override
  public Collection<?> getNewChildDescriptors(Object object, EditingDomain editingDomain, Object sibling) {
    Collection<Object> newChildDescriptors = (Collection<Object>) super.getNewChildDescriptors(object, editingDomain,
        sibling);

    Entity container = (Entity) object;

    if (ComponentExt.canCreateABActor(container)) {
      DynamicCommandParameter descriptor = new DynamicCommandParameter(null, OaPackage.Literals.ENTITY__OWNED_ENTITIES,
          ComponentExt.createOperationalActor(), Messages.CreationMenuLabel_OperationalActor);

      newChildDescriptors.add(descriptor);
    }

    if (ComponentExt.canCreateABComponent(container)) {
      DynamicCommandParameter descriptor = new DynamicCommandParameter(null, OaPackage.Literals.ENTITY__OWNED_ENTITIES,
          ComponentExt.createEntity(), Messages.CreationMenuLabel_Entity);

      newChildDescriptors.add(descriptor);
    }

    return newChildDescriptors;
  }
}
