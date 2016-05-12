/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.validation.rule;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;

/**
 * Validation rule base class, that allows for the reporting of the rule along
 * with an error/warning.
 */
public abstract class AbstractValidationRule extends AbstractModelConstraint {
	/**
	 * Create failure status.
	 * 
	 * @deprecated Use IValidationContext.createFailureStatus() instead
	 * @param context_p
	 * @param messageArguments_p
	 * @return
	 */
	protected IStatus createFailureStatus(IValidationContext context_p, Object[] messageArguments_p) {
		Assert.isNotNull(context_p);
		return context_p.createFailureStatus(messageArguments_p);
	}
}
