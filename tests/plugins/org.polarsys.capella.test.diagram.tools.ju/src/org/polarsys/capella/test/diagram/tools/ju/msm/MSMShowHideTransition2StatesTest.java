/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.msm;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.context.MSMDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

/**
 */
public class MSMShowHideTransition2StatesTest extends EmptyProject {

	protected final String _reusedMode = "reusedMode"; //$NON-NLS-1$ 
	protected final String transition = "[State Transition] to Mode 2"; //$NON-NLS-1$ 

	public void test() throws Exception {

	Session session = getSession(getRequiredTestModel());
	SessionContext context = new SessionContext(session);

	MSMDiagram diagram= MSMDiagram.createDiagram(context, EmptyProject.SA__SYSTEM__SYSTEM_STATE_MACHINE__DEFAULT_REGION);

	MSMDiagram.setUnsynchronized(diagram);

	diagram.createState(diagram.getDiagramId(), GenericModel.STATE_1);
	diagram.createRegion(GenericModel.STATE_1, GenericModel.REGION_1);
	diagram.createState(GenericModel.REGION_1, GenericModel.STATE_2);
	diagram.createRegion(GenericModel.STATE_2, GenericModel.REGION_2);

	diagram.createState(diagram.getDiagramId(), GenericModel.STATE_3);
	diagram.createRegion(GenericModel.STATE_3, GenericModel.REGION_3);
	diagram.createRegion(GenericModel.STATE_3, GenericModel.REGION_4);

	diagram.createTransition(GenericModel.STATE_2, GenericModel.STATE_3, transition);
	
	diagram.hideTransition(diagram.getDiagramId(), transition);
	diagram.showTransition(diagram.getDiagramId(), transition);
	
	diagram.hideStateMode (diagram.getDiagramId(), GenericModel.STATE_3);
	diagram.showStateMode (diagram.getDiagramId(), GenericModel.STATE_3);
	}
}
