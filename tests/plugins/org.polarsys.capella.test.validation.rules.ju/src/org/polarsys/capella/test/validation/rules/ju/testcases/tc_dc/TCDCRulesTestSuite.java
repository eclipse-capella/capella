/*******************************************************************************
 * Copyright (c) 2006, 2020, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.tc_dc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class TCDCRulesTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new TCDCRulesTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new Rule_TC_DC_01());
    tests.add(new Rule_TC_DC_02());
    tests.add(new Rule_TC_DC_03());
    tests.add(new Rule_TC_DC_04());
    tests.add(new Rule_TC_DC_05());
    tests.add(new Rule_TC_DC_06());
    tests.add(new Rule_TC_DC_07());
    tests.add(new Rule_TC_DC_08());
    tests.add(new Rule_TC_DC_09());
    tests.add(new Rule_TC_DC_10());
    tests.add(new Rule_TC_DC_12());
    tests.add(new Rule_TC_DC_13());
    return tests;
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(new String[] { "RulesOnTransitionTest" }); //$NON-NLS-1$
  }
}
