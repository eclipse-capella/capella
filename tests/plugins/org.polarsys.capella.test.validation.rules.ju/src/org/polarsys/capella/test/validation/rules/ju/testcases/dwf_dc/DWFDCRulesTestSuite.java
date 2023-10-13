/*******************************************************************************
 * Copyright (c) 2006, 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_dc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class DWFDCRulesTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new DWFDCRulesTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new Rule_DWF_DC_01());
    tests.add(new Rule_DWF_DC_02());
    tests.add(new Rule_DWF_DC_03());
    tests.add(new Rule_DWF_DC_04());
    tests.add(new Rule_DWF_DC_05());
    tests.add(new Rule_DWF_DC_06());
    tests.add(new Rule_DWF_DC_07());
    tests.add(new Rule_DWF_DC_08());
    tests.add(new Rule_DWF_DC_09());
    tests.add(new Rule_DWF_DC_11());
    tests.add(new Rule_DWF_DC_12());
    tests.add(new Rule_DWF_DC_13());
    tests.add(new Rule_DWF_DC_14());
    tests.add(new Rule_DWF_DC_15());
    tests.add(new Rule_DWF_DC_16());
    tests.add(new Rule_DWF_DC_17());
    tests.add(new Rule_DWF_DC_18());
    tests.add(new Rule_DWF_DC_19());
    tests.add(new Rule_DWF_DC_20());
    tests.add(new Rule_DWF_DC_21());
    tests.add(new Rule_DWF_DC_22());
    tests.add(new Rule_DWF_DC_24());
    tests.add(new Rule_DWF_DC_26());
    tests.add(new Rule_DWF_DC_27());
    tests.add(new Rule_DWF_DC_28());
    tests.add(new Rule_DWF_DC_29());
    tests.add(new Rule_DWF_DC_30());
    tests.add(new Rule_DWF_DC_31());
    tests.add(new Rule_DWF_DC_32());
    tests.add(new Rule_DWF_DC_33());
    tests.add(new Rule_DWF_DC_34());
    tests.add(new Rule_DWF_DC_35());
    tests.add(new Rule_DWF_DC_36());
    tests.add(new Rule_DWF_DC_37());
    tests.add(new Rule_DWF_DC_38());
    tests.add(new Rule_DWF_DC_39());
    tests.add(new Rule_DWF_DC_40());
    tests.add(new Rule_DWF_DC_41());
    tests.add(new Rule_DWF_DC_42());
    tests.add(new Rule_DWF_DC_43());
    tests.add(new Rule_DWF_DC_44());
    tests.add(new Rule_DWF_DC_45());
    return tests;
  }

  @Override
  public List<String> getRequiredTestModels() {   
    return Arrays.asList(new String [] {"RulesOnDesignTest"});  //$NON-NLS-1$
  }
}
