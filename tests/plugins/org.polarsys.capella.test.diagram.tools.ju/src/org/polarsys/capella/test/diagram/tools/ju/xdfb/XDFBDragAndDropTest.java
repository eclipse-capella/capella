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

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.data.activity.OutputPin;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.test.diagram.common.ju.availableXDFBDiagramTools.XDFBCreateContainerTools;
import org.polarsys.capella.test.diagram.common.ju.availableXDFBDiagramTools.XDFBCreateEdgeTools;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.XDFBToolsTestingModel;
import org.polarsys.capella.test.framework.context.SessionContext;

public class XDFBDragAndDropTest extends XDFBToolsTestingModel {

  @Override
  public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnXDFBDiagram(context, OA__ROOT_OA_DATA_FLOW_DIAGRAM_NAME, Type.OA, OA__ROOT_OA_ID);
    testOnXDFBDiagram(context, SA__ROOT_SF_DATA_FLOW_DIAGRAM_NAME, Type.SA, SA__ROOT_SF_ID);
    testOnXDFBDiagram(context, LA__ROOT_LF_DATA_FLOW_DIAGRAM_NAME, Type.LA, LA__ROOT_LF_ID);
    testOnXDFBDiagram(context, PA__ROOT_PF_DATA_FLOW_DIAGRAM_NAME, Type.PA, PA__ROOT_PF_ID);
  }

  private void testOnXDFBDiagram(SessionContext context, String diagramId, Type diagramType, String rootFunctionId) {
    XDFBDiagram xdfbSetup = XDFBDiagram.createDiagram(context, rootFunctionId);
    XDFBDiagram xdfb = XDFBDiagram.openDiagram(context, diagramId, diagramType);
    String containerId = xdfb.getDiagramId();

    String function1Id = xdfbSetup.createContainer(xdfbSetup.getDiagramId(), XDFBCreateContainerTools.CREATE_FUNCTION);
    String function2Id = xdfb.createContainer(containerId, XDFBCreateContainerTools.CREATE_FUNCTION);
    String constraintId = xdfbSetup.createConstraint("c1");

    // drag and drop
    xdfb.dragAndDropFunctionFromExplorer(function1Id, containerId);
    xdfb.dragAndDropAbstractFunction(function2Id, function1Id);

    if (diagramType != Type.OA) {
      String function3Id = xdfb.createContainer(containerId, XDFBCreateContainerTools.CREATE_FUNCTION);
      xdfb.createEdge(function1Id, function3Id, XDFBCreateEdgeTools.CREATE_FUNCTIONAL_EXCHANGE);

      AbstractFunction f1 = xdfb.getSessionContext().getSemanticElement(function1Id);
      EList<OutputPin> ownedPins = f1.getOutputs();
      FunctionOutputPort outPort = (FunctionOutputPort) ownedPins.iterator().next();
      xdfb.dragAndDropFunctionPort(outPort.getId(), function2Id);
    }

    xdfb.dragAndDropConstraintsFromExplorer(constraintId, containerId);
  }

}
