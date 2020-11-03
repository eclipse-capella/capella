/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
	 * @param context
	 * @param messageArguments
	 * @return
	 */
	protected IStatus createFailureStatus(IValidationContext context, Object[] messageArguments) {
		Assert.isNotNull(context);
		return context.createFailureStatus(messageArguments);
	}
}
