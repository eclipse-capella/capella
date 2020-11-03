/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
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
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.test.diagram.common.ju.availableXDFBDiagramTools.XDFBCreateContainerTools;
import org.polarsys.capella.test.diagram.common.ju.availableXDFBDiagramTools.XDFBCreateEdgeTools;
import org.polarsys.capella.test.diagram.common.ju.availableXDFBDiagramTools.XDFBInsertRemoveTools;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.diagram.layout.ju.layout.compare.ShapeHelper;
import org.polarsys.capella.test.diagram.tools.ju.model.XDFBToolsTestingModel;
import org.polarsys.capella.test.framework.context.SessionContext;

public class XDFBShowHideFunctionalExchange extends XDFBToolsTestingModel {

  @Override
  public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnXDFBDiagram(context, OA__ROOT_OA_DATA_FLOW_DIAGRAM_NAME, Type.OA);
    testOnXDFBDiagram(context, SA__ROOT_SF_DATA_FLOW_DIAGRAM_NAME, Type.SA);
    testOnXDFBDiagram(context, LA__ROOT_LF_DATA_FLOW_DIAGRAM_NAME, Type.LA);
    testOnXDFBDiagram(context, PA__ROOT_PF_DATA_FLOW_DIAGRAM_NAME, Type.PA);
  }

  protected void testOnXDFBDiagram(SessionContext context, String diagramName, Type diagramType) {

    XDFBDiagram xdfb = XDFBDiagram.openDiagram(context, diagramName, diagramType);
    Diagram diagram = DiagramHelper.getDiagram(xdfb.getDiagram());

    String diagramId = xdfb.getDiagramId();

    String Function1Id = xdfb.createContainer(diagramId, XDFBCreateContainerTools.CREATE_FUNCTION);
    String Function2Id = xdfb.createContainer(diagramId, XDFBCreateContainerTools.CREATE_FUNCTION);

    String functionalExchangeToShowHideId = xdfb.createEdge(Function1Id, Function2Id,
        XDFBCreateEdgeTools.CREATE_FUNCTIONAL_EXCHANGE);
    String functionalExchangeWhichShouldNotMoveId = xdfb.createEdge(Function2Id, Function1Id,
        XDFBCreateEdgeTools.CREATE_FUNCTIONAL_EXCHANGE);

    DiagramHelper.setSynchronized(xdfb.getDiagram(), false);
    xdfb.hideElements(Function1Id, XDFBInsertRemoveTools.INSERT_REMOVE_FUNCTIONAL_EXCHANGES,
        functionalExchangeToShowHideId);

    if (diagramType != Type.OA) {

      String outputPort = ((FunctionalExchange) xdfb.getSessionContext()
          .getSemanticElement(functionalExchangeToShowHideId)).getSource().getId();
      String inputPort = ((FunctionalExchange) xdfb.getSessionContext()
          .getSemanticElement(functionalExchangeToShowHideId)).getTarget().getId();

      xdfb.hasntView(inputPort);
      xdfb.hasntView(outputPort);
    }

    DSemanticDecorator FE2view = xdfb.getView(functionalExchangeWhichShouldNotMoveId);
    if (FE2view instanceof DEdge) {

      PointList pointListBefore = ShapeHelper.getRelativePointListForDEdge(diagram, (DEdge) FE2view);
      Point firstPointBefore = pointListBefore.getFirstPoint();
      Point lastPointBefore = pointListBefore.getLastPoint();

      xdfb.showElements(Function1Id, XDFBInsertRemoveTools.INSERT_REMOVE_FUNCTIONAL_EXCHANGES,
          functionalExchangeToShowHideId);

      FE2view = xdfb.getView(functionalExchangeWhichShouldNotMoveId);
      PointList pointListAfter = ShapeHelper.getRelativePointListForDEdge(diagram, (DEdge) FE2view);
      Point firstPointAfter = pointListAfter.getFirstPoint();
      Point lastPointAfter = pointListAfter.getLastPoint();

      assertTrue("FE2 must not move if we insert FE1",
          firstPointAfter.getDistance(firstPointBefore) == 0 && lastPointAfter.getDistance(lastPointBefore) == 0);
    } else {
      fail("FE2 must be visible and must be a DEdge");
    }

    DiagramHelper.setSynchronized(xdfb.getDiagram(), true);
  }
}
