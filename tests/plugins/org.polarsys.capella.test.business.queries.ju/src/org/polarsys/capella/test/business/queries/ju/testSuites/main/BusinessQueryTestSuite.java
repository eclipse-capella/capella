/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.business.queries.ju.testSuites.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

import junit.framework.Test;

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
  public List<String> getRequiredTestModels() {
    return Arrays.asList(new String[] { getProjectForTest(), getLibProjectForTest() });
  }

  public String getProjectForTest() {
    return "sysmodel"; //$NON-NLS-1$
  }

  public String getLibProjectForTest() {
    return "sysModelLib"; //$NON-NLS-1$
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
}
