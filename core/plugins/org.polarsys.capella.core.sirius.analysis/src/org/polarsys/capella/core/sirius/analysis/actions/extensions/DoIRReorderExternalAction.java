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
package org.polarsys.capella.core.sirius.analysis.actions.extensions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.Scenario;
	
public class DoIRReorderExternalAction extends AbstractExternalJavaAction {

	private static final String PREDECESSOR_BEFORE = "PREDECESSOR_BEFORE";	 //$NON-NLS-1$
	private static final String PREDECESSOR_AFTER = "PREDECESSOR_AFTER";	 //$NON-NLS-1$
	private static final String SCENARIO = "SCENARIO";                       //$NON-NLS-1$
	
	
	public void execute(Collection<? extends EObject> selections,
			Map<String, Object> parameters) {
		InstanceRole predecessorBefore = (InstanceRole) parameters.get(PREDECESSOR_BEFORE);
		InstanceRole predecessorAfter = (InstanceRole) parameters.get(PREDECESSOR_AFTER);
	
		Scenario scenario = (Scenario) parameters.get(SCENARIO);
	
		
		int currentIndexPredecessorBefore = -1;
		if (predecessorBefore != null){
			currentIndexPredecessorBefore = scenario.getOwnedInstanceRoles().indexOf(predecessorBefore);
		}		
		
		int currentIndexPredecessorAfter = -1;
		if (predecessorAfter != null){
			currentIndexPredecessorAfter = scenario.getOwnedInstanceRoles().indexOf(predecessorAfter);
		}				
		
		if (currentIndexPredecessorBefore < currentIndexPredecessorAfter) {
			// Moving up -> take the place of the PredecessorAfter
			scenario.getOwnedInstanceRoles().move(currentIndexPredecessorAfter, currentIndexPredecessorBefore + 1);
		} else if (currentIndexPredecessorBefore > currentIndexPredecessorAfter) {
			// Moving down -> take the place next to PredecessorAfter
			scenario.getOwnedInstanceRoles().move(currentIndexPredecessorAfter + 1, currentIndexPredecessorBefore + 1);
		}
	}
}
