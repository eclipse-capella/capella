/*******************************************************************************
 * Copyright (c) 2024 Thales LAS France SAS.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.summaryAndDescription;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class SummaryAndDescriptionTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new SummaryAndDescriptionTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new Rule_DS_1_1_TestCase());
    tests.add(new Rule_DS_1_2_TestCase());
    tests.add(new Rule_DS_1_3_TestCase());
    tests.add(new Rule_DS_1_4_TestCase());
    tests.add(new Rule_DS_1_5_TestCase());
    tests.add(new Rule_DS_1_6_TestCase());
    tests.add(new Rule_DS_1_7_TestCase());
    tests.add(new Rule_DS_1_8_TestCase());
    tests.add(new Rule_DS_1_9_TestCase());
    tests.add(new Rule_DS_1_10_TestCase());
    tests.add(new Rule_DS_1_11_TestCase());
    
    tests.add(new Rule_DS_2_1_TestCase());
    tests.add(new Rule_DS_2_2_TestCase());
    tests.add(new Rule_DS_2_3_TestCase());
    tests.add(new Rule_DS_2_4_TestCase());
    
    return tests;
  }

}
