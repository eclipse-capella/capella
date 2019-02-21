/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.xdfb;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.diagram.layout.ju.layout.compare.ShapeHelper;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

import junit.framework.Test;

public class XDFBShowHideFunctionalExchange extends EmptyProject {

	public static String FE1 = "FE1";
	public static String FE2 = "FE2";
	
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
		Diagram diagram = DiagramHelper.getDiagram(xdfb.getDiagram());
		
		xdfb.createFunction(GenericModel.FUNCTION_1);
		xdfb.createFunction(GenericModel.FUNCTION_1_1, GenericModel.FUNCTION_1);
		xdfb.createFunction(GenericModel.FUNCTION_1_2, GenericModel.FUNCTION_1);
		
		xdfb.createFunctionalExchange(FE1, GenericModel.FUNCTION_1_1, GenericModel.FUNCTION_1_2);
		xdfb.createFunctionalExchange(FE2, GenericModel.FUNCTION_1_2, GenericModel.FUNCTION_1_1);
		
		DSemanticDecorator FE2view = xdfb.getView(FE2);
		if (FE2view instanceof DEdge) {
			DEdge fe2Edge = (DEdge) FE2view;
			PointList pointList = ShapeHelper.getRelativePointListForDEdge(diagram, fe2Edge);
			Point firstPoint = pointList.getFirstPoint();
			Point lastPoint = pointList.getLastPoint();

			DiagramHelper.setSynchronized(xdfb.getDiagram(), false);
			xdfb.removeFunctionalExchange(GenericModel.FUNCTION_1, FE1);
			xdfb.insertFunctionalExchange(GenericModel.FUNCTION_1, FE1);
			
			FE2view = xdfb.getView(FE2);
			PointList pointListAfter = ShapeHelper.getRelativePointListForDEdge(diagram, (DEdge) FE2view);
			
			assertTrue("FE2 must not move if we insert FE1", pointListAfter.getFirstPoint().getDistance(firstPoint) == 0 && pointListAfter.getLastPoint().getDistance(lastPoint) == 0);
		} else {
			fail("FE2 must be visible and must be a DEdge");
		}
	}
	
	public static Test suite() {
		return new XDFBShowHideFunctionalExchange();
	}
}
