package org.polarsys.capella.core.commandline.core.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.polarsys.capella.core.commandline.core.AbstractCommandLine;

public class AbstractWorkbenchCommandLine extends AbstractCommandLine {

  /**
   * A workbench is needed by some Sirius plugins
   */
  public static void startWorkbench() {
    if (PlatformUI.isWorkbenchRunning()) {
      return;
    }
    
    Display display = PlatformUI.createDisplay();
    PlatformUI.createAndRunWorkbench(display, new WorkbenchAdvisor() {

      /**
       * {@inheritDoc}
       */
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
