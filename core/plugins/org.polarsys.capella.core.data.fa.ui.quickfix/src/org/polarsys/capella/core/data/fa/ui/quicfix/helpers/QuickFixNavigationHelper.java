/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.fa.ui.quicfix.helpers;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.ui.services.helper.EObjectImageProviderHelper;
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
    action.setImageDescriptor(ImageDescriptor.createFromImage(EObjectImageProviderHelper
        .getImage(abstractExchangeItemToAdd)));
    action.run();
  }
}
