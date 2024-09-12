/*******************************************************************************
 * Copyright (c) 2024, 2024 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.functional;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testcases.functional.functional.*;
import org.polarsys.capella.test.validation.rules.ju.testcases.functional.functionalChains.*;
import org.polarsys.capella.test.validation.rules.ju.testcases.functional.functionalCoherence.*;

import junit.framework.Test;

public class FunctionalTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new FunctionalTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new Rule_F_1_TestCase());
    tests.add(new Rule_FCH_1_TestCase());
    tests.add(new Rule_FC_1_TestCase());
    tests.add(new Rule_FC_2_TestCase());
    tests.add(new Rule_FC_3_TestCase());
    tests.add(new Rule_FC_4_TestCase());
    tests.add(new Rule_FC_5_TestCase());
    tests.add(new Rule_FC_6_TestCase());
    tests.add(new Rule_FC_7_TestCase());
    tests.add(new Rule_FC_8_TestCase());
    tests.add(new Rule_FC_9_TestCase());
    tests.add(new Rule_FC_10_TestCase());
    tests.add(new Rule_FC_11_TestCase());
    tests.add(new Rule_FC_12_TestCase());
    tests.add(new Rule_FC_13_TestCase());
    tests.add(new Rule_FC_14_TestCase());
    tests.add(new Rule_FC_15_TestCase());
    return tests;
  }

}
