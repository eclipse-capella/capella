/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class DWFDRulesTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new DWFDRulesTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new Rule_DWF_D_01());
    tests.add(new Rule_DWF_D_02());
    tests.add(new Rule_DWF_D_03());
    tests.add(new Rule_DWF_D_04());
    tests.add(new Rule_DWF_D_05());
    tests.add(new Rule_DWF_D_06());
    tests.add(new Rule_DWF_D_08());
    tests.add(new Rule_DWF_D_10());
    tests.add(new Rule_DWF_D_11());
    tests.add(new Rule_DWF_D_12());
    tests.add(new Rule_DWF_D_13());
    tests.add(new Rule_DWF_D_14());
    tests.add(new Rule_DWF_D_15());
    tests.add(new Rule_DWF_D_16());
    tests.add(new Rule_DWF_D_17());
    tests.add(new Rule_DWF_D_18());
    tests.add(new Rule_DWF_D_19());
    tests.add(new Rule_DWF_D_21());
    tests.add(new Rule_DWF_D_22());
    tests.add(new Rule_DWF_D_23());
    tests.add(new Rule_DWF_D_24());
    tests.add(new Rule_DWF_D_25());
    tests.add(new Rule_DWF_D_26());
    tests.add(new Rule_DWF_D_28());
    tests.add(new Rule_DWF_D_29());
    tests.add(new Rule_DWF_D_30());
    tests.add(new Rule_DWF_D_32());
    tests.add(new Rule_DWF_D_33());
    tests.add(new Rule_DWF_D_34());
    tests.add(new Rule_DWF_D_35());
    tests.add(new Rule_DWF_D_36());
    tests.add(new Rule_DWF_D_37());
    tests.add(new Rule_DWF_D_38());
    tests.add(new Rule_DWF_D_39());
    tests.add(new Rule_DWF_D_40());
    tests.add(new Rule_DWF_D_41());
    tests.add(new Rule_DWF_D_42());
    tests.add(new Rule_DWF_D_43());
    tests.add(new Rule_DWF_D_44());
    tests.add(new Rule_DWF_D_45());
    tests.add(new Rule_DWF_D_46());
    tests.add(new Rule_DWF_D_47());
    tests.add(new Rule_DWF_D_48());
    tests.add(new Rule_DWF_D_49());
    tests.add(new Rule_DWF_D_50());
    tests.add(new Rule_DWF_D_51());
    tests.add(new Rule_DWF_D_52());
    tests.add(new Rule_DWF_D_53());
    tests.add(new Rule_DWF_D_54());
    tests.add(new Rule_DWF_D_56());
    return tests;
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(new String[] { "RulesOnDesignTest" }); //$NON-NLS-1$
  }
}
