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
package org.polarsys.capella.test.diagram.common.ju.api;

import org.polarsys.capella.test.diagram.common.ju.context.BasicExecutionContext;

/**
 *
 */
public abstract class AbstractTestStep implements Runnable {

  private BasicExecutionContext _executionContext;
  
  public AbstractTestStep(BasicExecutionContext executionContext) {
    _executionContext = executionContext;
  }
  
  public abstract Object getResult();

  protected abstract void runTest();

  protected void preRunTest() {
    // basic implementation does nothing, shall be overridden
  }

  protected void postRunTest() {
    // basic implementation does nothing, shall be overridden
  }

  /**
   * 
   */
  public void run() {
    preRunTest();
    runTest();
    postRunTest();
  }
  
  /**
   * 
   */
  protected BasicExecutionContext getExecutionContext() {
    return _executionContext;
  }
}
