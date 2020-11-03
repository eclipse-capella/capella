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
package org.polarsys.capella.core.data.common.validation.statetransition;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.IState;

/**
 * 
 *
 */
public class MDCHK_State_InvolvedStates extends AbstractValidationRule {

	@Override
	public IStatus validate(IValidationContext ctx) {
		State sourceState = (State) ctx.getTarget();
		List<IState> indirectStates = new ArrayList<IState>();
		for (Region region : sourceState.getOwnedRegions()) {
			for (IState subState : region.getInvolvedStates()) {
				indirectStates.add(subState);	
			}			
		}
		/* compararing both lists */
		if (indirectStates.containsAll(sourceState.getReferencedStates()) &&
				sourceState.getReferencedStates().containsAll(indirectStates)) {
			return ctx.createSuccessStatus();
		} 
		/* error : building the error message */
		return ctx.createFailureStatus(new Object[] {sourceState.getName()});
	}

}
