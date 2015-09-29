/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.suites.ju;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;

import org.polarsys.capella.test.business.queries.ju.testSuites.main.BusinessQueryTestSuite;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.model.ju.testsuites.main.ModelTestSuite;
import org.polarsys.capella.test.recrpl.ju.testsuites.main.RecRplTestSuite;
import org.polarsys.capella.test.refinement.ju.testsuites.main.AllRefinementTestSuites;
import org.polarsys.capella.test.transition.ju.testsuites.main.TransitionTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testsuites.main.ValidationRulesTestSuite;

/**
 *
 */
public class CompleteTestSuite extends BasicTestSuite {

	/**
	 * Returns the suite. This is required to unary launch this test.
	 */
	public static Test suite() {
		return new CompleteTestSuite();
	}

	@Override
	protected List<BasicTestArtefact> getTests() {
		List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
		tests.add(new BusinessQueryTestSuite());
    tests.add(new ValidationRulesTestSuite());
    tests.add(new ModelTestSuite());
    tests.add(new AllRefinementTestSuites());
    tests.add(new TransitionTestSuite());
    tests.add(new RecRplTestSuite());
		return tests;
	}

	@Override
	public List<String> getRequiredTestModels() {
		return null;
	}
}
