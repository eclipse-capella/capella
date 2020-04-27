/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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

/**
 * @author Erwan Brottier
 */
public class InvalidMethodToCallTestCaseError implements BQValidationError {

	protected String identifier;
	
	public InvalidMethodToCallTestCaseError(String methodName) {
		this.identifier = methodName;
	}
	
	public String toString() {
		return identifier+" is not a valid method name to call";  //$NON-NLS-1$//$NON-NLS-2$
	}

}
