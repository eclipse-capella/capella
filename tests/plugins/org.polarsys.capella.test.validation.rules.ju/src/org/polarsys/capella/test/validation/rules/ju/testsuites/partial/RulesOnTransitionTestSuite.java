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
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testcases.tc_dc.TCDCRulesTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testcases.tc_df.TCDFRulesTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testcases.tc_ds.TCDSRulesTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testcases.tc_i.TCIRulesTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testcases.tc_sm.TCSMRulesTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testcases.tj_epbs.TJEPBSRulesTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testcases.tj_g.TJGRulesTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testcases.tj_la.TJLARulesTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testcases.tj_pa.TJPARulesTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testcases.tj_sa.TJSARulesTestSuite;

import junit.framework.Test;

public class RulesOnTransitionTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new RulesOnTransitionTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new TCDCRulesTestSuite());
    tests.add(new TCDFRulesTestSuite());
    tests.add(new TCDSRulesTestSuite());
    tests.add(new TCIRulesTestSuite());
    tests.add(new TCSMRulesTestSuite());
    tests.add(new TJEPBSRulesTestSuite());
    tests.add(new TJGRulesTestSuite());
    tests.add(new TJLARulesTestSuite());
    tests.add(new TJPARulesTestSuite());
    tests.add(new TJSARulesTestSuite());
    return tests;
  }

  @Override
  public List<String> getRequiredTestModels() {   
    return Arrays.asList(new String [] {"RulesOnTransitionTest"});  //$NON-NLS-1$
  }
}
