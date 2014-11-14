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
package org.polarsys.capella.core.data.cs.validation.component;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class MDCHK_Component_Interfaces_Level extends AbstractValidationRule {

	@Override
	public IStatus validate(IValidationContext ctx_p) {
		EObject eObj = ctx_p.getTarget();
		if (eObj instanceof Interface) {
			Interface interf = (Interface) eObj;
			
			ModellingArchitecture iArchi = CapellaElementExt.getArchi(interf);
			for (Component component : interf.getUserComponents()) {
				ModellingArchitecture cArchi = CapellaElementExt.getArchi(component);
				if (!CapellaElementExt.isLegalArchitecture(iArchi, cArchi))
					return fail(component, interf, ctx_p);
			}
			for (Component component : interf.getImplementorComponents()) {
				ModellingArchitecture cArchi = CapellaElementExt.getArchi(component);
				if (!CapellaElementExt.isLegalArchitecture(iArchi, cArchi))
					return fail(component, interf, ctx_p);
			}
		}

		return ctx_p.createSuccessStatus();

	}

	/**
	 * @param cpnt_p
	 * @param interf_p
	 * @param context_p
	 * @return
	 */
	private IStatus fail(Component cpnt_p, Interface interf_p,
			IValidationContext context_p) {
		return createFailureStatus(context_p, new Object[] { cpnt_p.getName(),
				interf_p.getName(), CapellaElementExt.getArchi(interf_p).eClass().getName() });
	}



}
