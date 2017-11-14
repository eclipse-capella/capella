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
package org.polarsys.capella.test.diagram.tools.ju.xdfb;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

import junit.framework.Test;

public class XDFBShowHideFunctions extends EmptyProject {

	@Override
	public void test() throws Exception {
		Session session = getSession(getRequiredTestModel());
		SessionContext context = new SessionContext(session);

		testOnXDFBDiagram(context, SA__ROOT_SF);
		testOnXDFBDiagram(context, LA__ROOT_LF);
	    testOnXDFBDiagram(context, PA__ROOT_PF);
	}
	
	protected void testOnXDFBDiagram(SessionContext context, String idSource) {
		XDFBDiagram xdfb = XDFBDiagram.createDiagram(context, idSource);

		xdfb.createFunction(GenericModel.FUNCTION_1);
		xdfb.createActorFunction(GenericModel.FUNCTION_2);
		
		xdfb.removeFunction(xdfb.getDiagramId(), GenericModel.FUNCTION_1);
		xdfb.removeFunction(xdfb.getDiagramId(), GenericModel.FUNCTION_2);
		
		xdfb.insertFunction(xdfb.getDiagramId(), GenericModel.FUNCTION_1);
		xdfb.insertFunction(xdfb.getDiagramId(), GenericModel.FUNCTION_2);
	}
	
	public static Test suite() {
		return new XDFBShowHideFunctions();
	}
}
