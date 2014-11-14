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
package org.polarsys.capella.core.data.interaction.validation.scenario;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class MDCHK_Scenario_Ordering extends AbstractValidationRule {

	@Override
	public IStatus validate(IValidationContext ctx_p) {
		EObject eObj = ctx_p.getTarget();

		if (eObj instanceof Scenario) {
			Scenario sc = (Scenario) eObj;
			boolean result = ScenarioExt.checkOrdering(sc);
			if (! result) {
				return createFailureStatus(ctx_p, new Object[] { sc.getName() });
			}
		}
		return ctx_p.createSuccessStatus();
	}

}
