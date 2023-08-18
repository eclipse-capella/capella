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
package org.polarsys.capella.test.validation.rules.ju.testcases.tc_df;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class TCDFRulesTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new TCDFRulesTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new Rule_TC_DF_01());
    tests.add(new Rule_TC_DF_02());
    tests.add(new Rule_TC_DF_03());
    tests.add(new Rule_TC_DF_04());
    tests.add(new Rule_TC_DF_05());
    tests.add(new Rule_TC_DF_06());
    tests.add(new Rule_TC_DF_07());
    tests.add(new Rule_TC_DF_08());
    tests.add(new Rule_TC_DF_09());
    tests.add(new Rule_TC_DF_10());
    tests.add(new Rule_TC_DF_11());
    tests.add(new Rule_TC_DF_12());
    tests.add(new Rule_TC_DF_13());
    tests.add(new Rule_TC_DF_14());
    tests.add(new Rule_TC_DF_15());
    return tests;
  }

  @Override
  public List<String> getRequiredTestModels() {   
    return Arrays.asList(new String [] {"RulesOnTransitionTest"});  //$NON-NLS-1$
  }
}
