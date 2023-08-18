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
package org.polarsys.capella.test.validation.rules.ju.testsuites.main;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testcases.dc_cl.DCCLRulesTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testcases.naming.NameConflictTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testsuites.partial.MiscTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testsuites.partial.RulesOnDesignTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testsuites.partial.RulesOnIntegrityTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testsuites.partial.RulesOnQualityTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testsuites.partial.RulesOnTransitionTestSuite;

import junit.framework.Test;

public class ValidationRulesTestSuite extends BasicTestSuite {

	/**
	 * Returns the suite. This is required to unary launch this test.
	 */
	public static Test suite() {
		return new ValidationRulesTestSuite();
	}

	@Override
	protected List<BasicTestArtefact> getTests() {
		List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new DCCLRulesTestSuite());
    tests.add(new MiscTestSuite());
    tests.add(new NameConflictTestSuite());
    tests.add(new RulesOnDesignTestSuite());
    tests.add(new RulesOnIntegrityTestSuite());
    tests.add(new RulesOnQualityTestSuite());
    tests.add(new RulesOnTransitionTestSuite());
		return tests;
	}

}
