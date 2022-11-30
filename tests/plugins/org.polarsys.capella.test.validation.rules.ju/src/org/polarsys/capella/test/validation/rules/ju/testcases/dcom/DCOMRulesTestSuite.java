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
package org.polarsys.capella.test.validation.rules.ju.testcases.dcom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class DCOMRulesTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new DCOMRulesTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
	@Override
	protected List<BasicTestArtefact> getTests() {
		List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new Rule_DCOM_01());
    tests.add(new Rule_DCOM_02());
		tests.add(new Rule_DCOM_03());
    tests.add(new Rule_DCOM_04());
    tests.add(new Rule_DCOM_05());
    tests.add(new Rule_DCOM_06());
    tests.add(new Rule_DCOM_07());
    tests.add(new Rule_DCOM_08());
    tests.add(new Rule_DCOM_09());
    tests.add(new Rule_DCOM_10());
    tests.add(new Rule_DCOM_11());
    tests.add(new Rule_DCOM_13());
    tests.add(new Rule_DCOM_14());
    tests.add(new Rule_DCOM_15());
    tests.add(new Rule_DCOM_16());
    tests.add(new Rule_DCOM_17());
    tests.add(new Rule_DCOM_18());
    tests.add(new Rule_DCOM_19());
    tests.add(new Rule_DCOM_20());
    tests.add(new Rule_DCOM_21());
    tests.add(new Rule_DCOM_22());
    tests.add(new Rule_DCOM_23());
    tests.add(new Rule_DCOM_24());
		return tests;
	}

	@Override
	public List<String> getRequiredTestModels() {		
		return Arrays.asList(new String [] {"RulesOnDesignTest"});  //$NON-NLS-1$
	}
}
