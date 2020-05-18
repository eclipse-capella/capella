/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.polarsys.capella.core.data.capellamodeller.provider.CapellaModellerEditPlugin;
import org.polarsys.capella.core.data.fa.FunctionKind;
import org.polarsys.capella.core.data.gen.edit.decorators.ItemProviderAdapterDecorator;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.la.LogicalFunction;

public class LogicalFunctionItemProviderDecorator extends ItemProviderAdapterDecorator
    implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider,
    IItemPropertySource {

  public LogicalFunctionItemProviderDecorator(AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

  @Override
  public Object getImage(Object object) {
    LogicalFunction fct = (LogicalFunction) object;
    String imagePath = "full/obj16/LogicalFunction"; //$NON-NLS-1$
    if (FunctionKind.DUPLICATE.equals(fct.getKind())) {
      imagePath = "full/obj16/FunctionKind_Duplicate"; //$NON-NLS-1$
    } else if (FunctionKind.GATHER.equals(fct.getKind())) {
      imagePath = "full/obj16/FunctionKind_Gather"; //$NON-NLS-1$
    } else if (FunctionKind.ROUTE.equals(fct.getKind())) {
      imagePath = "full/obj16/FunctionKind_Route"; //$NON-NLS-1$
    } else if (FunctionKind.SELECT.equals(fct.getKind())) {
      imagePath = "full/obj16/FunctionKind_Select"; //$NON-NLS-1$
    } else if (FunctionKind.SPLIT.equals(fct.getKind())) {
      imagePath = "full/obj16/FunctionKind_Split"; //$NON-NLS-1$
    } else if (FunctionExt.isActorFunction(fct))
      imagePath = "full/obj16/ActorLogicalFunction"; //$NON-NLS-1$
    return overlayImage(object, CapellaModellerEditPlugin.INSTANCE.getImage(imagePath));
  }
}
