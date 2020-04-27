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
package org.polarsys.capella.core.data.fa.ui.quicfix.helpers;

import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.NavigateAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;

public class QuickFixNavigationHelper {
  public static void showCapellaElement(CapellaElement abstractExchangeItemToAdd) {
    IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    CapellaCommonNavigator capellaCommonNavigator = (CapellaCommonNavigator) activePage
        .findView(CapellaCommonNavigator.ID);
    // Create a navigate action that enables this navigation.
    NavigateAction action = new NavigateAction(abstractExchangeItemToAdd, capellaCommonNavigator.getCommonViewer());
    action.setText(EObjectLabelProviderHelper.getText(abstractExchangeItemToAdd));
    action.setImageDescriptor(ExtendedImageRegistry.getInstance().getImageDescriptor(EObjectLabelProviderHelper
        .getImage(abstractExchangeItemToAdd)));
    action.run();
  }
}
