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
package org.polarsys.capella.test.framework.api;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.osgi.util.NLS;

/**
 * Extended JUnit class test.
 */
public abstract class AbstractExtendedTest extends TestCase {

  /**
   * Time stamp just before the test is run.
   */
  private long _start;
  private long _elapsedTime;

  /**
   * Constructor
   */
  protected AbstractExtendedTest() {
    super();
  }

  /**
   * @param name_p
   */
  public AbstractExtendedTest(String name_p) {
    super(name_p);
  }

  /**
   * Performs arbitrary actions just before the test is run.<br>
   * Default implementation starts test time computation.
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    _start = System.currentTimeMillis();
  }

  /**
   * Performs arbitrary actions after the test has been run.<br>
   * Default implementation does nothing.
   * @see junit.framework.TestCase#tearDown()
   */
  @Override
  protected void tearDown() throws Exception {
    _elapsedTime = System.currentTimeMillis() - _start;
    
    if (getElapsedTime() > getExpectedMaxTime()) {
      Assert.fail(NLS.bind(CommonTestMessages.timeToRunTestFail, new Object[] { getName(), Long.valueOf(_elapsedTime),
          Long.valueOf(getExpectedMaxTime()) }));
    }
    super.tearDown();
  }

  /**
   * Get elapsed time to run this test i.e time to execute the 'test' method
   * returns by {@link #getTestName()}.
   * 
   * @return the elapsedTime
   */
  final public long getElapsedTime() {
    return _elapsedTime;
  }

  /**
   * Get the expected max time that this test is supposed to consume.
   * 
   * @return a long that represents the time in millisecond.
   */
  protected long getExpectedMaxTime() {
    return Long.MAX_VALUE;
  }

  /**
   * Read expected max time for given key.
   * 
   * @param testExpectedMaxTimeKey_p
   *          key for a test registered through Eclipse I18n mechanism.
   * @return
   */
  final protected long readExpectedMaxTime(String testExpectedMaxTimeKey_p) {
    try {
      long expectedMaxTime = Long.parseLong(testExpectedMaxTimeKey_p);
      return expectedMaxTime < 0 ? Long.MAX_VALUE : expectedMaxTime;
    } catch (NumberFormatException exception_p) {
      Assert.fail(NLS.bind(CommonTestMessages.invalidExpectedTime, getName()));
    }
    return Long.MAX_VALUE;
  }
}
