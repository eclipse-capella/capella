/*******************************************************************************
 * Copyright (c) 2018, 2020, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.i;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class IRulesTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new IRulesTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new Rule_I_01());
    tests.add(new Rule_I_07());
    tests.add(new Rule_I_09());
    tests.add(new Rule_I_10());
    tests.add(new Rule_I_12());
    tests.add(new Rule_I_14());
    tests.add(new Rule_I_15());
    tests.add(new Rule_I_16());
    tests.add(new Rule_I_17());
    tests.add(new Rule_I_21());
    tests.add(new Rule_I_22());
    tests.add(new Rule_I_23());
    tests.add(new Rule_I_24());
    tests.add(new Rule_I_25());
    tests.add(new Rule_I_26());
    tests.add(new Rule_I_27());
    tests.add(new Rule_I_29());
    tests.add(new Rule_I_30());
    tests.add(new Rule_I_31());
    tests.add(new Rule_I_32());
    tests.add(new Rule_I_33());
    tests.add(new Rule_I_34());
    tests.add(new Rule_I_34_OnFragment());
    tests.add(new Rule_I_35());
    tests.add(new Rule_I_36());
    tests.add(new Rule_I_37_38_ComponentExchange());
    tests.add(new Rule_I_37_38_FunctionalExchange());
    tests.add(new Rule_I_37_38_PhysicalLink());
    tests.add(new Rule_I_37_38_ComponentFunctionalAllocation());
    tests.add(new Rule_I_40_OnCapability());
    tests.add(new Rule_I_40_OnMission());
    tests.add(new Rule_I_43_ElementReferencesAirdOrProxyElement());
    tests.add(new Rule_I_45());

    return tests;
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(new String[] { "RulesOnIntegrityTest" }); //$NON-NLS-1$
  }
}
