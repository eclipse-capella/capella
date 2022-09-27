/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES.
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

import org.eclipse.jface.action.IAction;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.LocateInCapellaExplorerAction;

public class QuickFixNavigationHelper {
  public static void showCapellaElement(CapellaElement abstractExchangeItemToAdd) {

    // Create a navigate action that enables this navigation.
    IAction locatingAction = LocateInCapellaExplorerAction.createLocateTowards(abstractExchangeItemToAdd,
        EObjectLabelProviderHelper.getText(abstractExchangeItemToAdd), true);
    locatingAction.run();

  }
}
