/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.epbs.provider;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.polarsys.capella.common.menu.dynamic.util.DynamicCommandParameter;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.epbs.ConfigurationItemPkg;
import org.polarsys.capella.core.data.epbs.EpbsFactory;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.gen.edit.decorators.ItemProviderAdapterDecorator;
import org.polarsys.capella.core.data.gen.edit.decorators.Messages;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

public class ConfigurationItemPkgProviderDecorator extends ItemProviderAdapterDecorator
    implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider,
    IItemPropertySource {

  public ConfigurationItemPkgProviderDecorator(AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

  @SuppressWarnings("unchecked")
  @Override
  public Collection<?> getNewChildDescriptors(Object object, EditingDomain editingDomain, Object sibling) {
    Collection<Object> newChildDescriptors = (Collection<Object>) super.getNewChildDescriptors(object, editingDomain,
        sibling);

    BlockArchitecture rootBlockArchitecture = BlockArchitectureExt.getRootBlockArchitecture((EObject) object);
    ConfigurationItemPkg rootPackage = (ConfigurationItemPkg) BlockArchitectureExt
        .getComponentPkg(rootBlockArchitecture);

    if (rootPackage != null && !rootPackage.equals(object)) {
      DynamicCommandParameter configurationItemDescriptor = new DynamicCommandParameter(null,
          EpbsPackage.Literals.CONFIGURATION_ITEM_PKG__OWNED_CONFIGURATION_ITEMS,
          EpbsFactory.eINSTANCE.createConfigurationItem(), Messages.CreationMenuLabel_ConfigurationItem);
      newChildDescriptors.add(configurationItemDescriptor);

      DynamicCommandParameter configurationItemPkgDescriptor = new DynamicCommandParameter(null,
          EpbsPackage.Literals.CONFIGURATION_ITEM_PKG__OWNED_CONFIGURATION_ITEM_PKGS,
          EpbsFactory.eINSTANCE.createConfigurationItemPkg(), Messages.CreationMenuLabel_ConfigurationItemPkg);
      newChildDescriptors.add(configurationItemPkgDescriptor);
    }
    return newChildDescriptors;
  }

}
