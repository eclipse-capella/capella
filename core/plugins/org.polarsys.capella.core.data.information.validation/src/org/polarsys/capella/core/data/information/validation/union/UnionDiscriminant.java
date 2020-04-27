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
package org.polarsys.capella.core.data.information.validation.union;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.information.Union;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule ensure that a union has a discriminant value
 */
public class UnionDiscriminant extends AbstractValidationRule {
	/**
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		EObject eObj = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();
		if (eType == EMFEventType.NULL) {
			// Handles <code>Union</code> instances
			if (eObj instanceof Union) {
				Union union = (Union) eObj;
				if(null == union.getDiscriminant()){
					return ctx.createFailureStatus(new Object[] {union.getName() });
				}
			}
		}
		// No problem encountered
		return ctx.createSuccessStatus();
	}
}
