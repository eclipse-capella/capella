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
	public IStatus validate(IValidationContext ctx) {
		EObject eObj = ctx.getTarget();
		if (eObj instanceof CapellaElement && isImpactedByCurrentRule(eObj)) {

			if(eObj instanceof ComponentExchangeEnd){
				ComponentExchangeEnd cee = (ComponentExchangeEnd)eObj;
				if(cee.getPart() == null){
					return ctx.createFailureStatus(new Object[] { insertSpaceBetweenWord(cee.eClass().getName()), cee.getPort()});
				}
			}
			if(eObj instanceof PhysicalLinkEnd){
				PhysicalLinkEnd ple = (PhysicalLinkEnd)eObj;
				if(ple.getPart() == null){
					return ctx.createFailureStatus(new Object[] { insertSpaceBetweenWord(ple.eClass().getName()), ple.getPort()});
				}
			}
		}
		return null;
	}

	private boolean isImpactedByCurrentRule (EObject eObj) {
		return (eObj instanceof ComponentExchangeEnd
				|| eObj instanceof PhysicalLinkEnd);
	}

	public String insertSpaceBetweenWord(String input) {
        return input.replaceAll("([a-z])([A-Z])", "$1 $2");
}
}
