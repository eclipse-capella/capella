/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.sdfb;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.availableXDFBDiagramTools.XDFBCreateContainerTools;
import org.polarsys.capella.test.diagram.common.ju.availableXDFBDiagramTools.XDFBCreateEdgeTools;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;
import org.polarsys.capella.test.diagram.common.ju.step.tools.DragAndDropTool;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;

public class DnDWithInternalFE extends EmptyProject {
  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    final SessionContext context = new SessionContext(session);

    // Create a SDFB diagram
    XDFBDiagram sdfbDiagram = XDFBDiagram.createDiagram(context, SA__ROOT_SF);

    // Create 3 SFs
    String function1Id = sdfbDiagram.createContainer(sdfbDiagram.getDiagramId(),
        XDFBCreateContainerTools.CREATE_FUNCTION);
    String function2Id = sdfbDiagram.createContainer(sdfbDiagram.getDiagramId(),
        XDFBCreateContainerTools.CREATE_FUNCTION);
    String function3Id = sdfbDiagram.createContainer(sdfbDiagram.getDiagramId(),
        XDFBCreateContainerTools.CREATE_FUNCTION);

    // Create a FE SF1 -> SF2
    String fe1Id = sdfbDiagram.createEdge(function1Id, function2Id, XDFBCreateEdgeTools.CREATE_FUNCTIONAL_EXCHANGE);
    // Create a FE SF1 -> SF3
    String fe2Id = sdfbDiagram.createEdge(function1Id, function3Id, XDFBCreateEdgeTools.CREATE_FUNCTIONAL_EXCHANGE);

    assertNotNull("FE expected between Function 1 and Function 2", sdfbDiagram.getView(fe1Id));
    assertNotNull("FE expected between Function 1 and Function 3", sdfbDiagram.getView(fe2Id));

    // DnD SF2 in SF1 so we have an internal FE between them
    new DragAndDropTool(sdfbDiagram, IToolNameConstants.TOOL_SDFB_DND_SYSTEM_FUNCTION, function2Id, function1Id).run();

    assertNull("No FE expected between Function 1 and Function 2 (internal exchange)", sdfbDiagram.getView(fe1Id));
    assertNotNull("FE expected between Function 1 and Function 3", sdfbDiagram.getView(fe2Id));
  }
}
