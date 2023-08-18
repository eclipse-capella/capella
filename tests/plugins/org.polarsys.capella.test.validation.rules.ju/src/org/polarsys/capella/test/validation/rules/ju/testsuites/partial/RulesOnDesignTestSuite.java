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
import org.polarsys.capella.test.validation.rules.ju.testcases.dcom.DCOMRulesTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testcases.dcon.DCONRulesTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testcases.dcov.DCOVRulesTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testcases.dwf_ca.DWFCARulesTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testcases.dwf_d.DWFDRulesTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testcases.dwf_dc.DWFDCRulesTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testcases.dwf_df.DWFDFRulesTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testcases.dwf_ds.DWFDSRulesTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testcases.dwf_i.DWFIRulesTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testcases.dwf_sm.DWFSMRulesTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testcases.dwf_uc.DWFUCRulesTestSuite;

import junit.framework.Test;

public class RulesOnDesignTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new RulesOnDesignTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
	@Override
	protected List<BasicTestArtefact> getTests() {
		List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new DCOMRulesTestSuite());
    tests.add(new DCOVRulesTestSuite());
		tests.add(new DWFCARulesTestSuite());
    tests.add(new DWFDRulesTestSuite());
    tests.add(new DWFDCRulesTestSuite());
    tests.add(new DWFDFRulesTestSuite());
    tests.add(new DWFDSRulesTestSuite());
    tests.add(new DWFIRulesTestSuite());
    tests.add(new DWFSMRulesTestSuite());
    tests.add(new DWFUCRulesTestSuite());
    tests.add(new DCONRulesTestSuite());
		return tests;
	}

	@Override
	public List<String> getRequiredTestModels() {		
		return Arrays.asList(new String [] {"RulesOnDesignTest"});  //$NON-NLS-1$
	}
}
