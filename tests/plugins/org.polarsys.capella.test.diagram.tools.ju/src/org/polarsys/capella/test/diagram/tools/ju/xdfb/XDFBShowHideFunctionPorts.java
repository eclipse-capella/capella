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
import org.polarsys.capella.test.diagram.common.ju.availableXDFBDiagramTools.XDFBCreateNodeTools;
import org.polarsys.capella.test.diagram.common.ju.availableXDFBDiagramTools.XDFBInsertRemoveTools;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.diagram.tools.ju.model.XDFBToolsTestingModel;
import org.polarsys.capella.test.framework.context.SessionContext;

public class XDFBShowHideFunctionPorts extends XDFBToolsTestingModel {

  @Override
  public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnXDFBDiagram(context, SA__ROOT_SF_DATA_FLOW_DIAGRAM_NAME, Type.SA);
    testOnXDFBDiagram(context, LA__ROOT_LF_DATA_FLOW_DIAGRAM_NAME, Type.LA);
    testOnXDFBDiagram(context, PA__ROOT_PF_DATA_FLOW_DIAGRAM_NAME, Type.PA);
  }

  private void testOnXDFBDiagram(SessionContext context, String diagramId, Type diagramType) {

    XDFBDiagram xdfb = XDFBDiagram.openDiagram(context, diagramId, diagramType);

    String functionId = xdfb.createContainer(xdfb.getDiagramId(), XDFBCreateContainerTools.CREATE_FUNCTION);
    String actorFunctionId = xdfb.createContainer(xdfb.getDiagramId(), XDFBCreateContainerTools.CREATE_ACTOR_FUNCTION);

    DiagramHelper.setSynchronized(xdfb.getDiagram(), false);

    String functionInputPortId = xdfb.createNode(functionId, XDFBCreateNodeTools.CREATE_INPUT_PORT);
    String functionOutputPortId = xdfb.createNode(functionId, XDFBCreateNodeTools.CREATE_OUTPUT_PORT);

    xdfb.hideElements(functionId, XDFBInsertRemoveTools.INSERT_REMOVE_FUNCTION_PORT, functionInputPortId,
        functionOutputPortId);
    xdfb.showElements(functionId, XDFBInsertRemoveTools.INSERT_REMOVE_FUNCTION_PORT, functionInputPortId,
        functionOutputPortId);

    String actorFunctionInputPortId = xdfb.createNode(actorFunctionId, XDFBCreateNodeTools.CREATE_INPUT_PORT);
    String actorFunctionOutputPortId = xdfb.createNode(actorFunctionId, XDFBCreateNodeTools.CREATE_OUTPUT_PORT);

    xdfb.hideElements(actorFunctionId, XDFBInsertRemoveTools.INSERT_REMOVE_FUNCTION_PORT, actorFunctionInputPortId,
        actorFunctionOutputPortId);
    xdfb.showElements(actorFunctionId, XDFBInsertRemoveTools.INSERT_REMOVE_FUNCTION_PORT, actorFunctionInputPortId,
        actorFunctionOutputPortId);

    // Hiding a port should hide the functional exchange
    String fe1Id = xdfb.createEdge(functionOutputPortId, actorFunctionInputPortId,
        XDFBCreateEdgeTools.CREATE_FUNCTIONAL_EXCHANGE);
    xdfb.hideElements(actorFunctionId, XDFBInsertRemoveTools.INSERT_REMOVE_FUNCTION_PORT, actorFunctionInputPortId);
    xdfb.hasntViews(fe1Id);

    DiagramHelper.setSynchronized(xdfb.getDiagram(), true);
  }
}
