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
package org.polarsys.capella.test.business.queries.ju.errors;

import java.util.ArrayList;
import java.util.Collection;

import org.polarsys.capella.test.business.queries.ju.QueryResult;

/**
 * @author Erwan Brottier
 */
public class TestCaseFailsError implements BQValidationError {

	protected QueryResult currentResult; 
	protected QueryResult expectedResult;

	protected Collection<String> missing = new ArrayList<String>();
	protected Collection<String> unexpected = new ArrayList<String>();

	public TestCaseFailsError(QueryResult currentResult, QueryResult expectedResult) {
		this.currentResult = currentResult;
		this.expectedResult = expectedResult;
	  for (String expected : expectedResult.getResultIds()) {
      if (!currentResult.getResultIds().contains(expected)) {
        missing.add(expected);
      }
    }  
    for (String current : currentResult.getResultIds()) {
      if (!expectedResult.getResultIds().contains(current)) {
        unexpected.add(current);
      }
    }
	}
	
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("Test case fails for input "+currentResult.getInputId()).append("\n");
	  if (missing.size() > 0) {
	    b.append("Missing expected: \n");
	    for (String m : missing) {
	      b.append(m).append("\n");
	    }
	  }
	  if (unexpected.size() > 0) {
	    b.append("Unexpected: \n");
	    for (String u : unexpected) {
	      b.append(u).append("\n");
	    }
	  }
	  return b.toString();
	}
}
