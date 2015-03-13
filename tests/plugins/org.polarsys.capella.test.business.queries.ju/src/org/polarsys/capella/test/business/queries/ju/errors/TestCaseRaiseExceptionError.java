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

/**
 * @author Erwan Brottier
 */
public class TestCaseRaiseExceptionError implements BQValidationError {

	protected Throwable exception;
	
	public TestCaseRaiseExceptionError(Throwable exception) {
		this.exception = exception;
	}
	
	public String toString() {
		return "Test case raises an exception ("+exception.getMessage()+")";  //$NON-NLS-1$//$NON-NLS-2$
	}

}
