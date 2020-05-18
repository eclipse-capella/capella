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
package org.polarsys.capella.test.business.queries.ju;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.log.FormatedLogger;
import org.polarsys.capella.test.framework.helpers.log.FormatedSysoutLogger;
import org.polarsys.capella.test.framework.helpers.log.SilentLogger;

/**
 * Generic implementation of test cases for Business Query Test.
 * 
 * @author Erwan Brottier
 */
public abstract class BQTestCase extends BasicTestCase {
	
	protected FormatedLogger logger = new FormatedSysoutLogger();

	@Override
	public List<String> getRequiredTestModels() {
		return Arrays.asList(new String[] { getProjectForTest(), getLibProjectForTest() });
	}

	public abstract String getProjectForTest();
	
	public abstract String getLibProjectForTest();
	
	public abstract String getBQFullQualifiedName();
	
	public String getTestCaseName() {
	  return getBQFullQualifiedName();
	}

	public File getTestSuiteFile() {
		return BQTestHelpers.getTestSuiteFile(getPluginFolder(), getTestCaseName(), getProjectForTest());
	}
	
	@Override
	public void test() throws Exception {
		// Get and check parameters
		String queryIdentifier = getBQFullQualifiedName();

		IBusinessQuery businessQuery = BQTestHelpers.instanciateBQ(FrameworkUtil.getBundle(getClass()), queryIdentifier);
		if (businessQuery == null)
			assertTrue(NLS.bind("Impossible to instanciate business query: {0}", queryIdentifier), false); //$NON-NLS-1$
		
		File testSuiteFile = getTestSuiteFile();
		if (testSuiteFile == null || !testSuiteFile.exists())
			assertTrue(NLS.bind("test suite file does not exist ({0})", testSuiteFile), false); //$NON-NLS-1$ //$NON-NLS-2$

		Session sessionForTest = getSessionForTestModel(getProjectForTest());
		
		// Begin test
		logger.addTextLn(BQTestConstants.PROMPT_STRING+" Test validation for query " + getTestCaseName()); //$NON-NLS-1$
		BQTestCaseValidator validator = new BQTestCaseValidator();
		boolean result = validator.process(new SilentLogger(), getTestCaseName(), businessQuery, testSuiteFile, sessionForTest);		

		// Assertion
		String message = validator.getResultDescription();
		logger.incIndent();
		logger.addTextLn(message);
		logger.decIndent();
		assertTrue(message.toString(), result);
	}
}
