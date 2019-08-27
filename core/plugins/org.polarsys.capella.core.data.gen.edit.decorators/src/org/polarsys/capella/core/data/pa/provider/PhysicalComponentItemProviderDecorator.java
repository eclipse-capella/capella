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
import org.polarsys.capella.core.data.capellamodeller.provider.CapellaModellerEditPlugin;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.model.helpers.ComponentExt;

public class PhysicalComponentItemProviderDecorator extends AbstractPhysicalComponentItemProviderDecorator {
  public PhysicalComponentItemProviderDecorator(AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

  @Override
  public Object getImage(Object object) {
    PhysicalComponent component = (PhysicalComponent) object;
    String imagePath = "full/obj16/PhysicalComponent";

    if (component.isActor()) {
      if (component.isHuman()) {
        imagePath = "full/obj16/PhysicalActorHuman";
      } else {
        imagePath = "full/obj16/PhysicalActor";
      }
    } else {
      if (component.getNature() == PhysicalComponentNature.NODE) {
        if (component.isHuman()) {
          imagePath = "full/obj16/PhysicalComponentHumanNode";
        } else {
          imagePath = "full/obj16/PhysicalComponentNode";
        }
      } else if (component.getNature() == PhysicalComponentNature.BEHAVIOR) {
        if (component.isHuman()) {
          imagePath = "full/obj16/PhysicalComponentHumanBehavior";
        } else {
          imagePath = "full/obj16/PhysicalComponentBehavior";
        }
      } else {
        if (component.isHuman()) {
          imagePath = "full/obj16/PhysicalComponentHumanUnset";
        }
      }
    }

    return overlayImage(object, CapellaModellerEditPlugin.INSTANCE.getImage(imagePath));
  }

  @SuppressWarnings("unchecked")
  @Override
  public Collection<?> getNewChildDescriptors(Object object, EditingDomain editingDomain, Object sibling) {
    Collection<Object> newChildDescriptors = (Collection<Object>) super.getNewChildDescriptors(object, editingDomain,
        sibling);

    PhysicalComponent container = (PhysicalComponent) object;

    if (ComponentExt.canCreateABActor(container)) {

      DynamicCommandParameter descriptor = createPhysicalActorDescriptor(
          PaPackage.Literals.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS, container.getNature());

      newChildDescriptors.add(descriptor);
    }

    if (ComponentExt.canCreateABComponent(container)) {
      PhysicalComponentNature parentNature = container.getNature();

      if (parentNature == PhysicalComponentNature.UNSET) {
        newChildDescriptors.add(createPhysicalComponentDecriptor(
            PaPackage.Literals.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS, PhysicalComponentNature.NODE));

        newChildDescriptors.add(createPhysicalComponentDecriptor(
            PaPackage.Literals.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS, PhysicalComponentNature.BEHAVIOR));

      } else {
        newChildDescriptors.add(createPhysicalComponentDecriptor(
            PaPackage.Literals.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS, parentNature));
      }

    }

    return newChildDescriptors;
  }

}
