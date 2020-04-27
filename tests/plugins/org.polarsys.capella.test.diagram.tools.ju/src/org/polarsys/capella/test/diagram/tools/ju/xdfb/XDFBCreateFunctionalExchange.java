/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.xdfb;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.test.diagram.common.ju.availableXDFBDiagramTools.XDFBCreateContainerTools;
import org.polarsys.capella.test.diagram.common.ju.availableXDFBDiagramTools.XDFBCreateEdgeTools;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.XDFBToolsTestingModel;
import org.polarsys.capella.test.framework.context.SessionContext;

public class XDFBCreateFunctionalExchange extends XDFBToolsTestingModel {

  @Override
  public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnXDFBDiagram(context, OA__ROOT_OA_DATA_FLOW_DIAGRAM_NAME, Type.OA);
    testOnXDFBDiagram(context, SA__ROOT_SF_DATA_FLOW_DIAGRAM_NAME, Type.SA);
    testOnXDFBDiagram(context, LA__ROOT_LF_DATA_FLOW_DIAGRAM_NAME, Type.LA);
    testOnXDFBDiagram(context, PA__ROOT_PF_DATA_FLOW_DIAGRAM_NAME, Type.PA);
  }

  private void testOnXDFBDiagram(SessionContext context, String diagramName, final Type diagramType) {

    XDFBDiagram xdfb = XDFBDiagram.openDiagram(context, diagramName, diagramType);
    String diagramId = xdfb.getDiagramId();

    String Function1Id = xdfb.createContainer(diagramId, XDFBCreateContainerTools.CREATE_FUNCTION);
    String Function2Id = xdfb.createContainer(diagramId, XDFBCreateContainerTools.CREATE_FUNCTION);

    if (diagramType != Type.OA) {
      String ActorFunction1Id = xdfb.createContainer(diagramId, XDFBCreateContainerTools.CREATE_ACTOR_FUNCTION);
      String ActorFunction2Id = xdfb.createContainer(diagramId, XDFBCreateContainerTools.CREATE_ACTOR_FUNCTION);

      xdfb.createEdge(Function2Id, ActorFunction1Id, XDFBCreateEdgeTools.CREATE_FUNCTIONAL_EXCHANGE);
      xdfb.createEdge(ActorFunction1Id, ActorFunction2Id, XDFBCreateEdgeTools.CREATE_FUNCTIONAL_EXCHANGE);
    }

    xdfb.createEdge(Function1Id, Function2Id, XDFBCreateEdgeTools.CREATE_FUNCTIONAL_EXCHANGE);
  }
}
