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

package org.polarsys.capella.core.data.pa.provider;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.menu.dynamic.util.DynamicCommandParameter;
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
    PhysicalComponent parentComponent = (PhysicalComponent) ComponentPkgExt.getParentComponent(container);

    if (ComponentExt.canCreateABActor(container)) {
      PhysicalComponentNature nature = parentComponent != null ? parentComponent.getNature()
          : PhysicalComponentNature.NODE;

      DynamicCommandParameter descriptor = createPhysicalActorDescriptor(
          PaPackage.Literals.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENTS, nature);

      newChildDescriptors.add(descriptor);
    }

    if (ComponentExt.canCreateABComponent(container)) {

      if (parentComponent == null || parentComponent.getNature() == PhysicalComponentNature.UNSET) {
        newChildDescriptors.add(createPhysicalComponentDecriptor(
            PaPackage.Literals.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENTS, PhysicalComponentNature.NODE));

        newChildDescriptors.add(createPhysicalComponentDecriptor(
            PaPackage.Literals.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENTS, PhysicalComponentNature.BEHAVIOR));

      } else {
        newChildDescriptors.add(createPhysicalComponentDecriptor(
            PaPackage.Literals.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENTS, parentComponent.getNature()));
      }
    }

    return newChildDescriptors;
  }

}
