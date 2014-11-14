/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.refinement.merge.ui;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 *
 */
@SuppressWarnings("nls")
public class DesambiguationWizard {

  private Shell sShell = null;

  /**
   * 
   */
  public DesambiguationWizard() {
  }

  /**
   * @param args
   */
  public static void main(String[] args) {

    /* Before this is run, be sure to set up the launch configuration (Arguments->VM Arguments)
     * for the correct SWT library path in order to run with the SWT dlls. 
     * The dlls are located in the SWT plugin jar.  
     * For example, on Windows the Eclipse SWT 3.1 plugin jar is:
     *       installation_directory\plugins\org.eclipse.swt.win32_3.1.0.jar
     */
    Display display = Display.getDefault();
    DesambiguationWizard thisClass = new DesambiguationWizard();
    thisClass.createSShell();
    thisClass.sShell.open();
    while (!thisClass.sShell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();
  }

  /**
   * This method initializes sShell
   */
  private void createSShell() {
    sShell = new Shell();
    sShell.setText("Shell");
    sShell.setSize(new Point(438, 245));
    sShell.setLayout(new GridLayout());
  }

}
