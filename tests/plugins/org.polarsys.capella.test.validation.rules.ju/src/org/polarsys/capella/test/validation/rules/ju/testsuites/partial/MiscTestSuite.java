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
package org.polarsys.capella.test.validation.rules.ju.testsuites.partial;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testcases.misc.Bug2438TestCase;
import org.polarsys.capella.test.validation.rules.ju.testcases.misc.CheckAllRulesCodeTest;
import org.polarsys.capella.test.validation.rules.ju.testcases.misc.ComponentPortOrientationConsistencyTest;
import org.polarsys.capella.test.validation.rules.ju.testcases.misc.I25_MoreDescriptionValidationTests;
import org.polarsys.capella.test.validation.rules.ju.testcases.misc.InvalidQuickfixRegistration;
import org.polarsys.capella.test.validation.rules.ju.testcases.misc.NoDuplicateRuleIdsTest;
import org.polarsys.capella.test.validation.rules.ju.testcases.misc.NoStackoverflowErrorOnValidation;
import org.polarsys.capella.test.validation.rules.ju.testcases.misc.PackageCycle1Test;
import org.polarsys.capella.test.validation.rules.ju.testcases.misc.PackageCycle2Test;
import org.polarsys.capella.test.validation.rules.ju.testcases.misc.PackageCycle3Test;
import org.polarsys.capella.test.validation.rules.ju.testcases.misc.PackageCycle4Test;
import org.polarsys.capella.test.validation.rules.ju.testcases.misc.PackageCycle5Test;
import org.polarsys.capella.test.validation.rules.ju.testcases.misc.PackageCycle6Test;

import junit.framework.Test;

public class MiscTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new MiscTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new CheckAllRulesCodeTest());
    tests.add(new ComponentPortOrientationConsistencyTest());
    tests.add(new I25_MoreDescriptionValidationTests());
    tests.add(new NoDuplicateRuleIdsTest());
    tests.add(new InvalidQuickfixRegistration());
    tests.add(new NoStackoverflowErrorOnValidation());
    tests.add(new PackageCycle1Test());
    tests.add(new PackageCycle2Test());
    tests.add(new PackageCycle3Test());
    tests.add(new PackageCycle4Test());
    tests.add(new PackageCycle5Test());
    tests.add(new PackageCycle6Test());
    tests.add(new Bug2438TestCase());
    //tests.add(new RulesCoverageTest());
    return tests;
  }

}
