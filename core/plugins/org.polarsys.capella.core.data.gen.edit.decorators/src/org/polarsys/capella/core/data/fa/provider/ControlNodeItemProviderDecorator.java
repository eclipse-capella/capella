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
package org.polarsys.capella.core.data.fa.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.polarsys.capella.core.data.capellamodeller.provider.CapellaModellerEditPlugin;
import org.polarsys.capella.core.data.fa.ControlNode;
import org.polarsys.capella.core.data.gen.edit.decorators.ItemProviderAdapterDecorator;

public class ControlNodeItemProviderDecorator extends ItemProviderAdapterDecorator
    implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider,
    IItemPropertySource {

  public ControlNodeItemProviderDecorator(AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

  @Override
  public Object getImage(Object object) {
    ControlNode node = (ControlNode) object;
    String imagePath = "full/obj16/ControlNode"; //$NON-NLS-1$

    switch (node.getKind()) {
    case AND:
      imagePath = "full/obj16/ControlNode_And"; //$NON-NLS-1$
      break;
    case OR:
      imagePath = "full/obj16/ControlNode_Or"; //$NON-NLS-1$
      break;
    case ITERATE:
      imagePath = "full/obj16/ControlNode_Iterate"; //$NON-NLS-1$
      break;
    }

    return overlayImage(object, CapellaModellerEditPlugin.INSTANCE.getImage(imagePath));
  }
}
