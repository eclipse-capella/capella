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
			Map<String, Object> parameters_p) {
		InstanceRole predecessorBefore = (InstanceRole) parameters_p.get(PREDECESSOR_BEFORE);
		InstanceRole predecessorAfter = (InstanceRole) parameters_p.get(PREDECESSOR_AFTER);
	
		Scenario scenario = (Scenario) parameters_p.get(SCENARIO);
		

		int indexBefore = 0;
		int indexMoved = 0;
		
		if (predecessorBefore != null){
			indexBefore =scenario.getOwnedInstanceRoles().indexOf(predecessorBefore);
			indexMoved = indexBefore + 1;
		}
		InstanceRole movedIR = scenario.getOwnedInstanceRoles().remove(indexMoved);
		
		int indexAfter = 0;
		int newIndex = 0;
		if (predecessorAfter != null) {
			indexAfter =scenario.getOwnedInstanceRoles().indexOf(predecessorAfter);
			newIndex = indexAfter + 1;
		}
		
		scenario.getOwnedInstanceRoles().add(newIndex, movedIR);
		
	}


}
