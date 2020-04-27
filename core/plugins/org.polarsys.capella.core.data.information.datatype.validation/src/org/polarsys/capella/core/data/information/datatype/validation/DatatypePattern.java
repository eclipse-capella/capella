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
package org.polarsys.capella.core.data.information.datatype.validation;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class DatatypePattern extends AbstractValidationRule {

	@Override
	public IStatus validate(IValidationContext ctx) 
	{
		// Get the target
		EObject eObj = ctx.getTarget();
		// if the target is a DataType
		if(eObj instanceof DataType)
		{
			// Get the pattern of the DataType
			String pattern = ((DataType)eObj).getPattern();
			// if the pattern is not null
			if(null != pattern)
			{
				// Try Catch Block for getting PatternSyntaxException
				try
				{
					// Compile the pattern
					Pattern.compile(pattern);
				}
				// PatternSyntaxException => the pattern syntax is not correct
				catch (PatternSyntaxException e)
				{
					// return error
					return ctx.createFailureStatus(new Object[] { pattern, ((DataType)eObj).getName(), eObj.eClass().getName() });
				}
			}
		}
		// return success
		return ctx.createSuccessStatus();
	}

}
