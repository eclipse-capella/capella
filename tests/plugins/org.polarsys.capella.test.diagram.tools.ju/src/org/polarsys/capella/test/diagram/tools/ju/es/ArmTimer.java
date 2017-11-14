/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.es;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.context.ESDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.diagram.tools.ju.model.ESProject;

import junit.framework.Test;

/**
 * Test for basic Arm Timer Tool on ES diagram
 */
public class ArmTimer extends ESProject {

	@Override
	public void test() throws Exception {
		Session session = getSession(getRequiredTestModel());
	    SessionContext context = new SessionContext(session);

	    ESDiagram diagram = ESDiagram.createDiagram(context, LA__CAPABILITIES__CAPABILITYREALIZATION_1__ES_SCENARIO_2);
	    
	    diagram.createArmTimer(LA__CAPABILITIES__CAPABILITYREALIZATION_1__ES_SCENARIO_2__LA_2,
	    		LA__CAPABILITIES__CAPABILITYREALIZATION_1__ES_SCENARIO_2__LA_2);
	}
	
	public static Test suite(){
		return new ArmTimer();
	}
}
