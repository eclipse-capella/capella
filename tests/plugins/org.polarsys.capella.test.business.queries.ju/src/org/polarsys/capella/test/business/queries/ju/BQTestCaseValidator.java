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
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.test.business.queries.ju.errors.BQValidationError;
import org.polarsys.capella.test.business.queries.ju.errors.InputNotFoundError;
import org.polarsys.capella.test.business.queries.ju.errors.InvalidMethodToCallTestCaseError;
import org.polarsys.capella.test.business.queries.ju.errors.MissingTestCaseError;
import org.polarsys.capella.test.business.queries.ju.errors.TestCaseFailsError;
import org.polarsys.capella.test.business.queries.ju.errors.TestCaseRaiseExceptionError;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;
import org.polarsys.capella.test.framework.helpers.log.FormatedLogger;

/**
 * @author Erwan Brottier
 */
public class BQTestCaseValidator {

	private static final int MAX_FEEDBACK_BY_LINE = 40;

	protected FormatedLogger logger;
	protected List<BQValidationError> errors = new ArrayList<BQValidationError>();
	protected int nbFeedBack = 0;
	
	protected int nbTestCaseSucceed = 0;
	protected int nbInputNotFound = 0;
	protected int nbTestCaseFailed = 0;
	protected int nbRaisedExceptions = 0;
	protected int nbMissingTestCases = 0;
	protected int nbInvalidIdentifiers = 0;
	protected int nbTestCases = 0;
	
	public boolean process(FormatedLogger logger, String queryIdentifier, IBusinessQuery businessQuery, File testSuiteFile, Session testSession) {
		this.logger = logger;		

		// validation of inputs that return result
		List<String> testedInputIdsForAvailable = new ArrayList<String>();
		List<String> testedInputIdsForCurrent = new ArrayList<String>();
		Hashtable<String, EObject> id2ObjectTable = BQTestHelpers.getId2ObjectTableInSessionForTest(testSession, businessQuery.getEClass());
		String serializedData = IResourceHelpers.readFileAsString(testSuiteFile);
		List<QueryResult> testCases = QueryResult.deserialize(serializedData);
		nbTestCases = testCases.size();
		for (QueryResult oracleResult : testCases) {
			String ident = oracleResult.getQueryIdentifier();
			EObject input = id2ObjectTable.get(oracleResult.getInputId());
			if (input == null)
				declareInputNotFound(oracleResult.getInputId());
			else {
				List<EObject> res = null;
				if (ident.endsWith("-"+BQTestConstants.GET_AVAILABLE_METHOD_NAME)) { //$NON-NLS-1$
					testedInputIdsForAvailable.add(oracleResult.getInputId());
					try {
						res = businessQuery.getAvailableElements(input);
					} catch (Throwable exception) {
						declareExceptionRaised(exception, input, BQTestConstants.GET_AVAILABLE_METHOD_NAME);
					}
				} else if (ident.endsWith("-"+BQTestConstants.GET_CURRENT_METHOD_NAME)) { //$NON-NLS-1$
					testedInputIdsForCurrent.add(oracleResult.getInputId());
					try {
						res = businessQuery.getCurrentElements(input, false);
					} catch (Throwable exception) {
						declareExceptionRaised(exception, input, BQTestConstants.GET_CURRENT_METHOD_NAME);
					}
				} else {
					declareInvalidMethodToCallInTestCase(ident);
				}
				QueryResult currentResult = QueryResult.createQueryResult(ident, input, res);
				if (!oracleResult.equals(currentResult)) {
					declareTestCaseFails(currentResult, oracleResult);
				} else {
					declareTestOk();
				}
			}
		}
		for (String id : id2ObjectTable.keySet())
			if (!testedInputIdsForAvailable.contains(id))
				declareMissingTestCase(id);
		logger.carriageReturn();
		return errors.size() == 0;
	}
	
	protected void declareInputNotFound(String inputId) {
		nbInputNotFound++;
		addTestCaseFeedBack('?');
		errors.add(new InputNotFoundError(inputId));
	}
	protected void declareExceptionRaised(Throwable exception, EObject input, String methodName) {
		nbRaisedExceptions++;
		errors.add(new TestCaseRaiseExceptionError(exception, input, methodName));
	}
	protected void declareTestCaseFails(QueryResult currentResult, QueryResult oracleResult) {
		nbTestCaseFailed++;
		addTestCaseFeedBack('-');
		errors.add(new TestCaseFailsError(currentResult, oracleResult));
	}
	protected void declareMissingTestCase(String objectId) {
		nbMissingTestCases++;
		addTestCaseFeedBack('0');
		errors.add(new MissingTestCaseError(objectId));
	}
	protected void declareInvalidMethodToCallInTestCase(String methodName) {
		nbInvalidIdentifiers++;
		addTestCaseFeedBack('#');
		errors.add(new InvalidMethodToCallTestCaseError(methodName));
	}	
	protected void declareTestOk() {
		nbTestCaseSucceed++;
		addTestCaseFeedBack('+');
	}		
	protected void addTestCaseFeedBack(char feesback) {
		if (nbFeedBack >= MAX_FEEDBACK_BY_LINE) {
			logger.carriageReturn();
			nbFeedBack = 0;
		}
		nbFeedBack++;
		logger.addText(feesback);
	}	
	public List<BQValidationError> getErrors() {
		return errors;
	}	
	public int getNbTestCaseSucceed() {
		return nbTestCaseSucceed;
	}
	public int getNbInputNotFound() {
		return nbInputNotFound;
	}
	public int getNbTestCaseFailed() {
		return nbTestCaseFailed;
	}
	public int getNbRaisedExceptions() {
		return nbRaisedExceptions;
	}
	public int getNbMissingTestCases() {
		return nbMissingTestCases;
	}
	public int getNbInvalidIdentifiers() {
		return nbInvalidIdentifiers;
	}	
	public int getNbTestCases() {
		return nbTestCases;
	}
	
	public String getResultDescription() {
		StringBuilder message = new StringBuilder();
		if (errors.size() == 0) {
			message.append("PASSED ("+nbTestCaseSucceed+" test cases)"); //$NON-NLS-1$ //$NON-NLS-2$			
		} else {
			List<String> errorDescriptions = new ArrayList<String>();
			if (nbTestCaseFailed > 0)
				errorDescriptions.add(nbTestCaseFailed + " out of " + nbTestCases + " test case(s) fail(s)"); //$NON-NLS-1$
			if (nbInputNotFound > 0)
				errorDescriptions.add(nbInputNotFound + " input not found"); //$NON-NLS-1$
			if (nbRaisedExceptions > 0)
				errorDescriptions.add(nbRaisedExceptions + " test case(s) raised exception(s)"); //$NON-NLS-1$
			if (nbMissingTestCases > 0)
				errorDescriptions.add(nbMissingTestCases + " missing test case(s)"); //$NON-NLS-1$
			if (nbInvalidIdentifiers > 0)
				errorDescriptions.add(nbInvalidIdentifiers + " invalid identifier(s)"); //$NON-NLS-1$
			for (int i = 0; i < errorDescriptions.size(); i++) {
				message.append(errorDescriptions.get(i));
				if (i < errorDescriptions.size() - 1)
					message.append(", "); //$NON-NLS-1$
			}

			for (BQValidationError e : getErrors()) {
			  message.append(e.toString());
			  message.append("\n");
			}

		}
		return message.toString();		
	}
	
}
