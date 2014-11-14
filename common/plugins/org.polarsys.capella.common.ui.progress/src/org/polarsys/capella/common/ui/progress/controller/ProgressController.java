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
package org.polarsys.capella.common.ui.progress.controller;

import java.util.Observer;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import org.polarsys.capella.common.ui.progress.model.BusinessThreadImpl;
import org.polarsys.capella.common.ui.progress.view.InfoProgressShell;

/**
 *
 */
public class ProgressController {
  
  /**
   * 
   */
  public ProgressController() {
    // Nothing to be done
  }
  
  /**
   * Standalone 
   */
  public void startStandAlone() {
    
    // Create display
    Display display = new Display(); 

    // Model
    Observer model = new BusinessThreadImpl();   
    
    // View pointing to model
    InfoProgressShell view = new InfoProgressShell(display, model);
    view.setDisposeAtEnd(true);
    
    // Model thread
    Thread thread = new Thread((Runnable)model);
    thread.start();
    
    // View thread
    view.open();
    
    // Dispose display
    display.dispose();
  }

  /**
   * Standalone 
   */
  public void start() {
    // Create display
    Display display = PlatformUI.getWorkbench().getDisplay();

    // Model
    Observer model = new BusinessThreadImpl();   
    
    // View pointing to model
    InfoProgressShell view = new InfoProgressShell(display, model);
    view.setDisposeAtEnd(true);
    
    // model pointing to view by Observable/Observer
    
    // Model thread
    Thread thread = new Thread((Runnable)model);
    thread.start();
    
    // View thread
    if(thread.isAlive()) {
      view.open();
    }
  }  
  
}
