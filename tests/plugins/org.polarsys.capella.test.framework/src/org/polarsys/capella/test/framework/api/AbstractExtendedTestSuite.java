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

import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Extended JUnit tests suite class.
 * 
 */
public abstract class AbstractExtendedTestSuite extends TestSuite {

  private static boolean _prepareTestSuite = true;

  /**
   * Previous executed test.
   */
  private Object _previousTestResult;

  final public static void disablePrepareTestSuite() {
    _prepareTestSuite = false;
  }

  /**
   * Constructor.
   */
  protected AbstractExtendedTestSuite() {

    if (_prepareTestSuite) {

      try {
        prepareTestSuite();
      } catch (Exception exception_p) {
        exception_p.printStackTrace();
      }

    }

    // Add all contained test cases.
    for (Test test : getTests()) {
      addTest(test);
    }

  }

  @Override
  public String getName() {
    return getClass().getName();
  }

//  @Override
//  public void run(TestResult testResult_p) {
//    preRun();
//    try {
//      setUp();
//    } catch (Throwable e) {
//      e.printStackTrace();
//    }
//
//    try {
//      super.run(testResult_p);
//    } finally {
//      try {
//        tearDown();
//      } catch (Throwable e) {
//        e.printStackTrace();
//      }
//      // to limitate memory leak
//      // we delete generically pointers to objects so that EObject are not keep
//      // in memory
//      TestHelper.cleanFields(this, "org.polarsys.capella.test.common.AbstractExtendedTestSuite"); //$NON-NLS-1$
//    }
//    return;
//  }

  /**
   * Set up this test suite during test execution.<br>
   * Default implementation does nothing
   */
  protected void setUp() {
    // Do nothing
  }

  /**
   * Dispose the test suite environment after its run.<br>
   * Default implementation does nothing
   */
  protected void tearDown() {
    // Do nothing
  }

  /**
   * Prepare test suite environment during its instantiation.<br>
   * Default implementation does nothing.
   */
  protected void prepareTestSuite() {
    // Do nothing.
  }

  /**
   * Load proects for the current TS
   */
  protected void preRun() {
  }

  /**
   * Dispose test suite environment during its instantiation.<br>
   * Default implementation does nothing. In fact, this method is not really
   * useful. just kept to insure compliancy but it is not used, as before
   * changes.
   */
  @Deprecated
  protected void disposeTestSuite() {
    // Do nothing.
  }

  /**
   * Get tests to run.
   * 
   * @return a not <code>null</code> collection.
   */
  protected abstract List<? extends Test> getTests();

  /**
   * Return a string which describes this test suite
   * 
   * @return description about this test suite
   */
  public String getDescription() {
    return CommonTestMessages.noDescriptionAvailable;
  }

  /**
   * Set the previous executed test.
   * 
   * @param previousTest_p
   */
  public void setPreviousTestResult(Object previousTestResult_p) {
    _previousTestResult = previousTestResult_p;
  }

  /**
   * Get the previous executed test.
   * 
   * @return the previousTest
   */
  protected Object getPreviousTestResult() {
    return _previousTestResult;
  }

}
