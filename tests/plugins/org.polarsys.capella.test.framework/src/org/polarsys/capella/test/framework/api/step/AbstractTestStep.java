/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.framework.api.step;

import org.polarsys.capella.test.framework.context.SessionContext;

/**
 *
 */
public abstract class AbstractTestStep<A> implements IStep<A> {

  private SessionContext _executionContext;

  public AbstractTestStep(SessionContext executionContext) {
    _executionContext = executionContext;
  }

  public abstract A getResult();

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
  public A run() {
    try {
      preRunTest();
      runTest();
      postRunTest();
      return getResult();

    } finally {
      dispose();
    }

  }

  protected void dispose() {
    // basic implementation does nothing, shall be overridden
  }

  /**
   * 
   */
  protected SessionContext getExecutionContext() {
    return _executionContext;
  }
}
