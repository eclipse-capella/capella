/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
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
