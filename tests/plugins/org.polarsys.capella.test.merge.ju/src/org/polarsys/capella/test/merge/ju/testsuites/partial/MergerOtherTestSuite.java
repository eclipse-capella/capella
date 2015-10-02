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
package org.polarsys.capella.test.merge.ju.testsuites.partial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.merge.ju.testcases.other.MultiMerge1;
import org.polarsys.capella.test.merge.ju.testcases.other.MultiMerge2;

/**
 * Test suite in order to test the merger in "stand-alone" mode. 
 */
public class MergerOtherTestSuite extends BasicTestSuite {
  
  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new MergerOtherTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new MultiMerge1());
    tests.add(new MultiMerge2());
    return tests;
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("mergerTest");
  }
}
