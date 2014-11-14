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
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.PhysicalLinkEnd;
import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class PartNotNull extends AbstractValidationRule {

	@Override
	public IStatus validate(IValidationContext ctx_p) {
		EObject eObj = ctx_p.getTarget();
		if (eObj instanceof CapellaElement && isImpactedByCurrentRule(eObj)) {

			if(eObj instanceof ComponentExchangeEnd){
				ComponentExchangeEnd cee = (ComponentExchangeEnd)eObj;
				if(cee.getPart() == null){
					return ctx_p.createFailureStatus(new Object[] { insertSpaceBetweenWord(cee.eClass().getName()), cee.getPort()});
				}
			}
			if(eObj instanceof PhysicalLinkEnd){
				PhysicalLinkEnd ple = (PhysicalLinkEnd)eObj;
				if(ple.getPart() == null){
					return ctx_p.createFailureStatus(new Object[] { insertSpaceBetweenWord(ple.eClass().getName()), ple.getPort()});
				}
			}
		}
		return null;
	}

	private boolean isImpactedByCurrentRule (EObject eObj_p) {
		return (eObj_p instanceof ComponentExchangeEnd
				|| eObj_p instanceof PhysicalLinkEnd);
	}

	public String insertSpaceBetweenWord(String input) {
        return input.replaceAll("([a-z])([A-Z])", "$1 $2");
}
}
