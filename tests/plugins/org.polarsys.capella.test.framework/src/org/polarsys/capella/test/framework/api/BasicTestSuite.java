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
 * Generic implementation of a test suite. This implementation supports
 * libraries as test models.
 * 
 * @author Erwan Brottier
 */
public abstract class BasicTestSuite extends TestSuite {

  protected BasicTestSuite() {
    // Add all contained test cases.
    for (Test test : getTests()) {
      addTest(test);
    }
  }

  @Override
  public String getName() {
    return getClass().getName();
  }

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
   * @return description about this test suite
   */
  public String getDescription() {
    return CommonTestMessages.noDescriptionAvailable;
  }	
	
//  // TODO to delete
//  /** Returns the plugin ID of the current test plugin. */
//  protected abstract String getPluginId();

  /** load all the test cases in test cases root package and sub ones. */
  protected abstract List<? extends Test> getTests();
}
