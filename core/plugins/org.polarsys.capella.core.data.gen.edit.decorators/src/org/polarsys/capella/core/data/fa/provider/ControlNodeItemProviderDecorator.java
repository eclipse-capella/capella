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

  protected static final String CONTROL_NODE_TEXT = "[Control Node]";

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

  @Override
  public String getText(Object object) {
    return CONTROL_NODE_TEXT;
  }
}
