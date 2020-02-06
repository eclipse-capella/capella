/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.commandline.core.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.polarsys.capella.core.commandline.core.AbstractCommandLine;

public class AbstractWorkbenchCommandLine extends AbstractCommandLine {

  public static void startWorkbench() {
    if (PlatformUI.isWorkbenchRunning()) {
      return;
    }
    
    Display display = PlatformUI.createDisplay();
    PlatformUI.createAndRunWorkbench(display, new WorkbenchAdvisor() {

      @Override
      public boolean openWindows() {
        return false;
      }

      @Override
      public String getInitialWindowPerspectiveId() {
        return null;
      }
    });
  }

}
