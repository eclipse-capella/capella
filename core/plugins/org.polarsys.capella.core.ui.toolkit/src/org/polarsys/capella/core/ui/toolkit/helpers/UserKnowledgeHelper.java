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
package org.polarsys.capella.core.ui.toolkit.helpers;

import org.eclipse.core.runtime.Adapters;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;

public class UserKnowledgeHelper {

  /**
   * Returns whether the user knows concept of 'Part'. If the project is set to multiparts, we guess that user knows
   * 'Part'. Otherwise, it doesn't know this concept, unless if he has explicitly shown parts on project explorer.
   * 
   * For instance, if the user understands part concept, we display 'Part' icon, otherwise the Part's component.
   */
  public static boolean isHandlingParts(EObject object) {
    IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    IUserPartManagement data = Adapters.adapt(window, IUserPartManagement.class);
    return CapellaProjectHelper.isReusableComponentsDriven(object) == TriStateBoolean.True
        || (data != null && data.isPartsVisible(window));
  }
}
