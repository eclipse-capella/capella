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
package org.polarsys.capella.core.data.common.validation.statetransition;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.capellacommon.InitialPseudoState;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.IState;

public class MDCHK_StateMachine_InitialState extends AbstractValidationRule {


	@Override
	public IStatus validate(IValidationContext ctx) {
		int nbInit = 0;
		StateMachine sm = (StateMachine) ctx.getTarget();
		for (Region region : sm.getOwnedRegions()) {
			for (IState state : region.getOwnedStates()) {
				if (state instanceof InitialPseudoState) {
					nbInit++;
				}
			}
		}
		if (nbInit > 1) {
			return ctx.createFailureStatus(new Object[] { sm.getName() });
		}
		return ctx.createSuccessStatus();
	}

}
