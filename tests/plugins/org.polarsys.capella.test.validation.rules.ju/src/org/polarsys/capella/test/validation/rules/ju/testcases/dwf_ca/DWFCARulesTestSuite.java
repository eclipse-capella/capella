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
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_ca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testcases.dwf_dc.Rule_DWF_DC_17;

import junit.framework.Test;

public class DWFCARulesTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new DWFCARulesTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new Rule_DWF_CA_01());
    tests.add(new Rule_DWF_CA_02());
    tests.add(new Rule_DWF_CA_03());
    tests.add(new Rule_DWF_CA_04());
    tests.add(new Rule_DWF_CA_06());
    tests.add(new Rule_DWF_CA_07());
    tests.add(new Rule_DWF_CA_08());
    tests.add(new Rule_DWF_CA_09());
    return tests;
  }

  @Override
  public List<String> getRequiredTestModels() {   
    return Arrays.asList(new String [] {"RulesOnDesignTest"});  //$NON-NLS-1$
  }
}
