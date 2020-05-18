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
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellamodeller.provider.CapellaModellerEditPlugin;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.gen.edit.decorators.ItemProviderAdapterDecorator;
import org.polarsys.capella.core.data.oa.OperationalProcess;

public class FunctionalChainInvolvementLinkItemProviderDecorator extends ItemProviderAdapterDecorator
    implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider,
    IItemPropertySource {
  
  public static final String ICON_PATH_FCIL_FUNCTION_OA = "full/obj16/FunctionalChainInvolvementLinkOA_ToFunction"; //$NON-NLS-1$
  public static final String ICON_PATH_FCIL_EXCHANGE_OA = "full/obj16/FunctionalChainInvolvementLinkOA_ToExchange"; //$NON-NLS-1$
  public static final String ICON_PATH_FCIL_FUNCTION = "full/obj16/FunctionalChainInvolvementLink_ToFunction"; //$NON-NLS-1$
  public static final String ICON_PATH_FCIL_EXCHANGE = "full/obj16/FunctionalChainInvolvementLink_ToExchange"; //$NON-NLS-1$

  public FunctionalChainInvolvementLinkItemProviderDecorator(AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

  @Override
  public Object getImage(Object object) {
    FunctionalChainInvolvementLink link = (FunctionalChainInvolvementLink) object;
    InvolvedElement involvedElement = link.getInvolved();
    String imagePath = "";

    if (link.eContainer() instanceof OperationalProcess) {
      if (involvedElement instanceof AbstractFunction) {
        imagePath = ICON_PATH_FCIL_FUNCTION_OA;
      } else {
        imagePath = ICON_PATH_FCIL_EXCHANGE_OA;
      }
    } else {
      if (involvedElement instanceof AbstractFunction) {
        imagePath = ICON_PATH_FCIL_FUNCTION;
      } else {
        imagePath = ICON_PATH_FCIL_EXCHANGE;
      }
    }
    return overlayImage(object, CapellaModellerEditPlugin.INSTANCE.getImage(imagePath));
  }
}
