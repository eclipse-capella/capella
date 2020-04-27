/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;

/**
 * @author Erwan Brottier
 */
public class TestCaseRaiseExceptionError implements BQValidationError {

	protected Throwable exception;
	protected EObject input;
	protected String methodName;
	
	public TestCaseRaiseExceptionError(Throwable exception, EObject input, String methodName) {
		this.exception = exception;
		this.input = input;
		this.methodName = methodName;
	}
	
	public String toString() {
		return "Test case raises an exception (input id : "+input.eResource().getURIFragment(input)+", method : "+methodName+", exception : "+exception.toString()+")";  //$NON-NLS-1$//$NON-NLS-2$
	}

}
