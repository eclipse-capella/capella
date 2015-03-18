/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.framework.helpers;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.polarsys.capella.core.sirius.ui.actions.OpenSessionAction;

/**
 * An API gathering together launchers for GUI capella actions. All these
 * actions are headless (they do not block on GUI windows and does not need user
 * interaction).
 * 
 * @author Erwan Brottier
 */
public class GuiActions {

  /**
   * Open a session by using the capella action @see OpenSessionAction.
   * 
   * @param airdFile
   *          the aird file
   */
  public static void openSession(IFile airdFile) {
    OpenSessionAction olsa = new OpenSessionAction();
    olsa.selectionChanged(new StructuredSelection(airdFile));
    olsa.run();
    flushASyncGuiThread();
  }

  /**
   * Prevents that all async thread on UI Thread has been executed before returning.
   * FIXME It is the best implementation to date. May be insufficient.
   */
  public static void flushASyncGuiThread() {
    try {
      Display.getCurrent().update();
      while (Display.getCurrent().readAndDispatch()) {
        // do nothing
      }
    } catch (Exception e) {
      // do nothing
    }
  }

}
