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

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.menu.dynamic.util.DynamicCommandParameter;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.gen.edit.decorators.Messages;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.ComponentPkgExt;

public class PhysicalComponentPkgItemProviderDecorator extends AbstractPhysicalComponentItemProviderDecorator {

  public PhysicalComponentPkgItemProviderDecorator(AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

  @SuppressWarnings("unchecked")
  @Override
  public Collection<?> getNewChildDescriptors(Object object, EditingDomain editingDomain, Object sibling) {
    Collection<Object> newChildDescriptors = (Collection<Object>) super.getNewChildDescriptors(object, editingDomain,
        sibling);

    PhysicalComponentPkg container = (PhysicalComponentPkg) object;

    if (ComponentExt.canCreateABActor(container)) {
      DynamicCommandParameter descriptor = new DynamicCommandParameter(null,
          PaPackage.Literals.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENTS, ComponentExt.createPhysicalActor(),
          Messages.CreationMenuLabel_PhysicalActor);

      newChildDescriptors.add(descriptor);
    }

    if (ComponentExt.canCreateABComponent(container)) {
      Component parent = ComponentPkgExt.getParentComponent(container);

      DynamicCommandParameter componentBCDescriptor = createComponentBCDescriptor(
          PaPackage.Literals.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENTS);
      DynamicCommandParameter componentICDescriptor = createComponentICDescriptor(
          PaPackage.Literals.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENTS);

      if (parent == null) {
        newChildDescriptors.add(componentICDescriptor);
        newChildDescriptors.add(componentBCDescriptor);

      } else if (parent instanceof PhysicalComponent) {
        PhysicalComponent parentComponent = (PhysicalComponent) parent;
        PhysicalComponentNature parentNature = parentComponent.getNature();

        switch (parentNature) {
        case BEHAVIOR:
          newChildDescriptors.add(componentBCDescriptor);
          break;

        case NODE:
          newChildDescriptors.add(componentICDescriptor);
          break;

        case UNSET:
          newChildDescriptors.add(componentICDescriptor);
          newChildDescriptors.add(componentBCDescriptor);
          break;

        default:
          break;
        }
      }

    }

    return newChildDescriptors;
  }

}
