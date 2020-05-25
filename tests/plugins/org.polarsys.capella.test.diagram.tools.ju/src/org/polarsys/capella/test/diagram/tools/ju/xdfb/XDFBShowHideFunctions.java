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
import org.polarsys.capella.test.diagram.common.ju.availableXDFBDiagramTools.XDFBInsertRemoveTools;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.XDFBToolsTestingModel;
import org.polarsys.capella.test.framework.context.SessionContext;

public class XDFBShowHideFunctions extends XDFBToolsTestingModel {

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
    String diagramId = xdfb.getDiagramId();

    String function1Id = xdfb.createContainer(diagramId, XDFBCreateContainerTools.CREATE_FUNCTION);
    String function2Id = xdfb.createContainer(diagramId, XDFBCreateContainerTools.CREATE_FUNCTION);

    hideAndShow(xdfb, function1Id, function2Id);

    if (diagramType != Type.OA) {

      String actorFunction1Id = xdfb.createContainer(diagramId, XDFBCreateContainerTools.CREATE_ACTOR_FUNCTION);
      String actorFunction2Id = xdfb.createContainer(diagramId, XDFBCreateContainerTools.CREATE_ACTOR_FUNCTION);

      hideAndShow(xdfb, actorFunction1Id, actorFunction2Id);
    }
  }

  private void hideAndShow(XDFBDiagram xdfb, String... functions) {

    String diagramId = xdfb.getDiagramId();

    // Hide Both and Show One by One
    xdfb.hideElements(diagramId, XDFBInsertRemoveTools.INSERT_REMOVE_FUNCTIONS, functions);
    xdfb.showElements(diagramId, XDFBInsertRemoveTools.INSERT_REMOVE_FUNCTIONS, functions[0]);
    xdfb.showElements(diagramId, XDFBInsertRemoveTools.INSERT_REMOVE_FUNCTIONS, functions[1]);

    // Hide One by One and Show Both
    xdfb.hideElements(diagramId, XDFBInsertRemoveTools.INSERT_REMOVE_FUNCTIONS, functions[0]);
    xdfb.hideElements(diagramId, XDFBInsertRemoveTools.INSERT_REMOVE_FUNCTIONS, functions[1]);
    xdfb.showElements(diagramId, XDFBInsertRemoveTools.INSERT_REMOVE_FUNCTIONS, functions);
  }
}
