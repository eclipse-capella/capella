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
package org.polarsys.capella.common.ui.progress.model;

import java.util.HashMap;



/**
 *
 */
public class BusinessThreadImpl extends AbstractBusinessThread {

  
  /**
   * 
   */
  public BusinessThreadImpl() {
    // Nothing to be done here
  }
  
  @Override
  @SuppressWarnings("nls")
  public void doRun(HashMap<String, Object> info_p) {
    String message = "Step " + info_p.get("Progress")
      + " on " + info_p.get("Max") 
      + " started at " + info_p.get("Min") 
      + " step is " + info_p.get("Step");
    info_p.put("Message", message);
    info_p.put("DetailedMessage", message);
    
    try {
      Thread.sleep(100);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
  
  @Override
  public int getMinimum() {
    return 0;
  }

  @Override
  public int getMaximum() {
    return 100;
  }

  @Override
  public int getStep() {
    return 1;
  }
}
