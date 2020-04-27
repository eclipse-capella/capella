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
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.test.diagram.common.ju.availableXDFBDiagramTools.XDFBCreateContainerTools;
import org.polarsys.capella.test.diagram.common.ju.availableXDFBDiagramTools.XDFBCreateEdgeTools;
import org.polarsys.capella.test.diagram.common.ju.availableXDFBDiagramTools.XDFBCreateNodeTools;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.XDFBToolsTestingModel;
import org.polarsys.capella.test.framework.context.SessionContext;

public class XDFBToolReconnect extends XDFBToolsTestingModel {

  @Override
  public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    // testOnXDFBDiagram(context, OA__ROOT_OA_DATA_FLOW_DIAGRAM_NAME, Type.OA);
    testOnXDFBDiagram(context, SA__ROOT_SF_DATA_FLOW_DIAGRAM_NAME, Type.SA);
    testOnXDFBDiagram(context, LA__ROOT_LF_DATA_FLOW_DIAGRAM_NAME, Type.LA);
    testOnXDFBDiagram(context, PA__ROOT_PF_DATA_FLOW_DIAGRAM_NAME, Type.PA);
  }

  private void testOnXDFBDiagram(SessionContext context, String diagramName, final Type diagramType) {

    XDFBDiagram xdfb = XDFBDiagram.openDiagram(context, diagramName, diagramType);
    String diagramId = xdfb.getDiagramId();

    String function1Id = xdfb.createContainer(diagramId, XDFBCreateContainerTools.CREATE_FUNCTION);
    String function2Id = xdfb.createContainer(diagramId, XDFBCreateContainerTools.CREATE_FUNCTION);

    String edgeToReconnectId = xdfb.createEdge(function1Id, function2Id,
        XDFBCreateEdgeTools.CREATE_FUNCTIONAL_EXCHANGE);

    if (diagramType != Type.OA) {

      String outputPortOnFunction1Id = ((FunctionalExchange) xdfb.getSessionContext()
          .getSemanticElement(edgeToReconnectId)).getSource().getId();
      String inputPortOnFunction2Id = ((FunctionalExchange) xdfb.getSessionContext()
          .getSemanticElement(edgeToReconnectId)).getTarget().getId();

      String inputPort1OnFunction1Id = xdfb.createNode(function1Id, XDFBCreateNodeTools.CREATE_INPUT_PORT);
      String outputPort2OnFunction1Id = xdfb.createNode(function1Id, XDFBCreateNodeTools.CREATE_OUTPUT_PORT);

      String inputPort2OnFunction2Id = xdfb.createNode(function2Id, XDFBCreateNodeTools.CREATE_INPUT_PORT);
      String outputPort1OnFunction2Id = xdfb.createNode(function2Id, XDFBCreateNodeTools.CREATE_OUTPUT_PORT);

      xdfb.reconnectFunctionalExchange(edgeToReconnectId, outputPortOnFunction1Id, outputPort2OnFunction1Id);
      xdfb.reconnectFunctionalExchange(edgeToReconnectId, inputPortOnFunction2Id, inputPort2OnFunction2Id);

      xdfb.cannotReconnectFunctionalExchange(edgeToReconnectId, outputPort2OnFunction1Id, inputPort1OnFunction1Id);
      xdfb.cannotReconnectFunctionalExchange(edgeToReconnectId, inputPort2OnFunction2Id, outputPort1OnFunction2Id);
    }

    else {

      String function3Id = xdfb.createContainer(diagramId, XDFBCreateContainerTools.CREATE_FUNCTION);
      String function4Id = xdfb.createContainer(diagramId, XDFBCreateContainerTools.CREATE_FUNCTION);

      xdfb.reconnectFunctionalExchange(edgeToReconnectId, function1Id, function3Id);
      xdfb.reconnectFunctionalExchange(edgeToReconnectId, function2Id, function4Id);

      String constraintId = xdfb.createNode(diagramId, XDFBCreateNodeTools.CREATE_CONSTRAINT);

      // Cannot reconnect nor the source, nor the target of the Interaction with a Constraint
      xdfb.cannotReconnectFunctionalExchange(edgeToReconnectId, function1Id, constraintId);
      xdfb.cannotReconnectFunctionalExchange(edgeToReconnectId, function2Id, constraintId);
    }
  }
}
