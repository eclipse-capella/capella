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
import org.polarsys.capella.test.diagram.common.ju.availableXDFBDiagramTools.XDFBInsertRemoveTools;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.XDFBToolsTestingModel;
import org.polarsys.capella.test.framework.context.SessionContext;

public class XDFBTestCaseFunctions extends XDFBToolsTestingModel {

  @Override
  public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnXDFBDiagram(context, SA__ROOT_SF_DATA_FLOW_DIAGRAM_NAME, Type.SA);
    testOnXDFBDiagram(context, LA__ROOT_LF_DATA_FLOW_DIAGRAM_NAME, Type.LA);
    testOnXDFBDiagram(context, PA__ROOT_PF_DATA_FLOW_DIAGRAM_NAME, Type.PA);
  }

  private void testOnXDFBDiagram(SessionContext context, String diagramName, Type diagramType) {

    XDFBDiagram xdfb = XDFBDiagram.openDiagram(context, diagramName, diagramType);
    String diagramId = xdfb.getDiagramId();

    // Create 2 parent functions and 2 child functions
    String function1Id = xdfb.createContainer(diagramId, XDFBCreateContainerTools.CREATE_FUNCTION);
    String function2Id = xdfb.createContainer(diagramId, XDFBCreateContainerTools.CREATE_FUNCTION);

    String function11Id = xdfb.createContainer(function1Id, XDFBCreateContainerTools.CREATE_FUNCTION);
    String function22Id = xdfb.createContainer(function2Id, XDFBCreateContainerTools.CREATE_FUNCTION);

    // Create FunctionalExchange1 between function11 and function 12
    String fe1Id = xdfb.createEdge(function11Id, function22Id, XDFBCreateEdgeTools.CREATE_FUNCTIONAL_EXCHANGE);

    /*
     * Get semantic objects of interest
     */
    FunctionalExchange semanticExchange = xdfb.getSessionContext().getSemanticElement(fe1Id);
    String fop11Id = semanticExchange.getSource().getId();

    // Remove function11 from diagram
    xdfb.hideElements(function1Id, XDFBInsertRemoveTools.INSERT_REMOVE_FUNCTIONS, function11Id);

    // Check that the FE is still visible
    xdfb.hasView(fe1Id);

    // Insert the child function
    xdfb.showElements(function1Id, XDFBInsertRemoveTools.INSERT_REMOVE_FUNCTIONS, function11Id);

    // Check that the FE is still visible
    xdfb.hasView(fe1Id);

    // Remove parent function
    xdfb.hideElements(diagramId, XDFBInsertRemoveTools.INSERT_REMOVE_FUNCTIONS, function1Id);

    // Child function, function port and functional exchange should be visible
    xdfb.hasViews(function11Id, fop11Id, fe1Id);

    // Remove child function from diagram
    xdfb.hideElements(diagramId, XDFBInsertRemoveTools.INSERT_REMOVE_FUNCTIONS, function11Id);

    // Insert parent function
    xdfb.showElements(diagramId, XDFBInsertRemoveTools.INSERT_REMOVE_FUNCTIONS, function1Id);

    // Child function should not appear
    xdfb.hasntView(function11Id);
  }
}
