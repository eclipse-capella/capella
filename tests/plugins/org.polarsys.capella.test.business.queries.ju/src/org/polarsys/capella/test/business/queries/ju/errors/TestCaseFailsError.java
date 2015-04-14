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
package org.polarsys.capella.test.business.queries.ju.errors;

import org.polarsys.capella.test.business.queries.ju.QueryResult;

/**
 * @author Erwan Brottier
 */
public class TestCaseFailsError implements BQValidationError {

	protected QueryResult currentResult; 
	protected QueryResult expectedResult;
	
	public TestCaseFailsError(QueryResult currentResult, QueryResult expectedResult) {
		this.currentResult = currentResult;
		this.expectedResult = expectedResult;
	}
	
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("Test case fails for input "+currentResult.getInputId() + " {\n"); //$NON-NLS-1$ //$NON-NLS-2$
		b.append("  current result : " + (currentResult.getResultIds() != null ? currentResult.getResultIds() : "EXCEPTION") + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
		b.append("  expected result : " + (expectedResult.getResultIds() != null ? expectedResult.getResultIds() : "EXCEPTION") + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
		b.append("}"); //$NON-NLS-1$
		return b.toString();
	}
}
