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
package org.polarsys.capella.test.business.queries.ju.testSuites.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;

import org.polarsys.capella.test.business.queries.ju.testSuites.partial.CapellaCommonBusinessQueryTestSuite;
import org.polarsys.capella.test.business.queries.ju.testSuites.partial.CapellaCoreBusinessQueryTestSuite;
import org.polarsys.capella.test.business.queries.ju.testSuites.partial.CapellaModellerBusinessQueryTestSuite;
import org.polarsys.capella.test.business.queries.ju.testSuites.partial.CsBusinessQueryTestSuite;
import org.polarsys.capella.test.business.queries.ju.testSuites.partial.CtxBusinessQueryTestSuite;
import org.polarsys.capella.test.business.queries.ju.testSuites.partial.EpbsBusinessQueryTestSuite;
import org.polarsys.capella.test.business.queries.ju.testSuites.partial.FaBusinessQueryTestSuite;
import org.polarsys.capella.test.business.queries.ju.testSuites.partial.InformationBusinessQueryTestSuite;
import org.polarsys.capella.test.business.queries.ju.testSuites.partial.InteractionBusinessQueryTestSuite;
import org.polarsys.capella.test.business.queries.ju.testSuites.partial.LaBusinessQueryTestSuite;
import org.polarsys.capella.test.business.queries.ju.testSuites.partial.OaBusinessQueryTestSuite;
import org.polarsys.capella.test.business.queries.ju.testSuites.partial.PaBusinessQueryTestSuite;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

/**
 * @author Erwan Brottier
 */
public class BusinessQueryTestSuite extends BasicTestSuite {

	/**
	 * Returns the suite. This is required to unary launch this test.
	 */
	public static Test suite() {
		return new BusinessQueryTestSuite();
	}

	@Override
	protected List<BasicTestArtefact> getTests() {
		List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
		tests.add(new CapellaCommonBusinessQueryTestSuite());
		tests.add(new CapellaCoreBusinessQueryTestSuite());
		tests.add(new CapellaModellerBusinessQueryTestSuite());
		tests.add(new CsBusinessQueryTestSuite());
		tests.add(new CtxBusinessQueryTestSuite());
		tests.add(new EpbsBusinessQueryTestSuite());
		tests.add(new FaBusinessQueryTestSuite());
		tests.add(new InformationBusinessQueryTestSuite());
		tests.add(new InteractionBusinessQueryTestSuite());
		tests.add(new LaBusinessQueryTestSuite());
		tests.add(new OaBusinessQueryTestSuite());
		tests.add(new PaBusinessQueryTestSuite());
		return tests;
	}

	@Override
	public List<String> getRequiredTestModels() {
		return Arrays.asList("sysmodel");
	}
}
