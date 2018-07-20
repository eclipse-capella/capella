/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.massactions.ju.helpers;

import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ui.massactions.activator.MACapellaActivator;
import org.polarsys.kitalpha.massactions.edit.MEView;
import org.polarsys.kitalpha.massactions.shared.view.MAView;
import org.polarsys.kitalpha.massactions.visualize.MVView;

/**
 * A view helper, providing some utility methods.
 * 
 * @author Sandu Postaru
 *
 */
public class ViewHelper {

  private ViewHelper() {
    // Exists only to defeat instantiation.
  }

  public static MEView getActiveMEView() throws PartInitException {

    IViewPart viewPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
        .showView(MACapellaActivator.ME_VIEW_ID);

    return (MEView) viewPart;
  }

  public static MVView getActiveMVView() throws PartInitException {

    IViewPart viewPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
        .showView(MACapellaActivator.MV_VIEW_ID);

    return (MVView) viewPart;
  }

  public static void resetViews(MAView... views) {

    for (int i = 0; i < views.length; i++) {
      MAView view = views[i];
      view.getData().clear();
      view.getTable().dispose();
    }
  }

}
