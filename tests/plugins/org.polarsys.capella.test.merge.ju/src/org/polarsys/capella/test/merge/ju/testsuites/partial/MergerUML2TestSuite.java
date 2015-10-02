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
package org.polarsys.capella.test.merge.ju.testsuites.partial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.merge.ju.testcases.uml2scenarios.CombinedFragmentDummy;
import org.polarsys.capella.test.merge.ju.testcases.uml2scenarios.CreateDeleteMsgDummy;
import org.polarsys.capella.test.merge.ju.testcases.uml2scenarios.InteractionStateDummy;
import org.polarsys.capella.test.merge.ju.testcases.uml2scenarios.InteractionUseDummy;

public class MergerUML2TestSuite extends BasicTestSuite {

	/**
	 * Returns the suite. This is required to unary launch this test.
	 */
	public static Test suite() {
		return new MergerUML2TestSuite();
	}

	@Override
	protected List<BasicTestArtefact> getTests() {
		List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
		tests.add(new CombinedFragmentDummy());
		tests.add(new InteractionStateDummy());
    tests.add(new InteractionUseDummy());
    tests.add(new CreateDeleteMsgDummy());
		return tests;
	}

	@Override
	public List<String> getRequiredTestModels() {
		return Arrays.asList("UML2Scenarios");
	}
}
