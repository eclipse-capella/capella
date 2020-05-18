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

  protected DynamicCommandParameter createPhysicalActorDescriptor(EReference containerRef,
      PhysicalComponentNature nature) {
    PhysicalComponent actor = ComponentExt.createPhysicalActor();
    actor.setNature(nature);

    return new DynamicCommandParameter(null, containerRef, actor, Messages.CreationMenuLabel_PhysicalActor);
  }

  protected DynamicCommandParameter createPhysicalComponentDecriptor(EReference reference,
      PhysicalComponentNature nature) {
    PhysicalComponent nodeComponent = ComponentExt.createPhysicalComponent();
    nodeComponent.setNature(nature);

    String menuLabel = nature == PhysicalComponentNature.NODE ? Messages.CreationMenuLabel_PhysicalComponent_Node
        : Messages.CreationMenuLabel_PhysicalComponent_Behaviour;

    return new DynamicCommandParameter(null, reference, nodeComponent, menuLabel);
  }

}
