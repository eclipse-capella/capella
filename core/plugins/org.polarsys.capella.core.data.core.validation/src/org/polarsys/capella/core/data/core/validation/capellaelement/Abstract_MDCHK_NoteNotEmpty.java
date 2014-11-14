/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.core.validation.capellaelement;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;

public abstract class Abstract_MDCHK_NoteNotEmpty extends AbstractValidationRule {

	@Override
	public IStatus validate(IValidationContext ctx_p) {
		EObject eObj = ctx_p.getTarget();
		if (eObj instanceof CapellaElement && isImpactedByCurrentRule(eObj)) {
			CapellaElement me = (CapellaElement) eObj;
			String desc = getNoteContent(me);
			if (
			    desc == null ||
			    desc.equals(ICommonConstants.EMPTY_STRING)
			) {
				if (me instanceof AbstractNamedElement) {
					AbstractNamedElement ne = (AbstractNamedElement) me;
					return createFailureStatus(ctx_p, new Object[] {me.eClass().getName(), ne.getName()});	
				}
				return createFailureStatus(ctx_p, new Object[] {me.eClass().getName(), ""}); //$NON-NLS-1$
			}
		}
		return ctx_p.createSuccessStatus();
	}

	private boolean isImpactedByCurrentRule (EObject eObj_p) {
		return (eObj_p instanceof FunctionalExchange
		           || eObj_p instanceof ComponentExchange 
		           || eObj_p instanceof AbstractFunction
		           || eObj_p instanceof Component
		           || eObj_p instanceof AbstractActor);
	}
	
	protected abstract String getNoteContent (CapellaElement me_p);

}
