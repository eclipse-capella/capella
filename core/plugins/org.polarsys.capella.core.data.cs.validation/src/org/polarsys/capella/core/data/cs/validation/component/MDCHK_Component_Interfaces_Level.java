/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.cs.validation.component;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class MDCHK_Component_Interfaces_Level extends AbstractValidationRule {

	@Override
	public IStatus validate(IValidationContext ctx) {
		EObject eObj = ctx.getTarget();
		if (eObj instanceof Interface) {
			Interface interf = (Interface) eObj;
			
			ModellingArchitecture iArchi = BlockArchitectureExt.getRootBlockArchitecture(interf);
			for (Component component : interf.getUserComponents()) {
				ModellingArchitecture cArchi = BlockArchitectureExt.getRootBlockArchitecture(component);
				if (!CapellaElementExt.isLegalArchitecture(iArchi, cArchi))
					return fail(component, interf, ctx);
			}
			for (Component component : interf.getImplementorComponents()) {
				ModellingArchitecture cArchi = BlockArchitectureExt.getRootBlockArchitecture(component);
				if (!CapellaElementExt.isLegalArchitecture(iArchi, cArchi))
					return fail(component, interf, ctx);
			}
		}

		return ctx.createSuccessStatus();

	}

	/**
	 * @param cpnt
	 * @param interf
	 * @param context
	 * @return
	 */
	private IStatus fail(Component cpnt, Interface interf,
			IValidationContext context) {
		return createFailureStatus(context, new Object[] { cpnt.getName(),
				interf.getName(), BlockArchitectureExt.getRootBlockArchitecture(interf).eClass().getName() });
	}



}
