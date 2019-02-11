/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.xab;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;

import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.DragAndDropFromProjectExplorerTool;
import org.polarsys.capella.test.framework.context.SessionContext;

public class DragAndDropPhysicalComponent extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnXAB(context, PA__PAB_DIAGRAM, PA__PAB_DIAGRAM,
        PA__PC_PART_PC14);
    // drag and drop sub physical component
    testOnXAB(context, PA__PAB_DIAGRAM, PA__PAB_COMPONENT_PC2,
        PA__PC_PART_PC2_1);

    // drag and drop to a diagram created from PC2
    testOnXAB(context, PA__PAB_DIAGRAM_PC2, PA__PAB_DIAGRAM_PC2,
        PA__PC_PART_PC2);
    // drag and drop sub physical component
    testOnXAB(context, PA__PAB_DIAGRAM_PC2, PA__PC_PART_PC2,
        PA__PC_PART_PC2_1);
  }

  public void testOnXAB(SessionContext context, String diagramName, String containerId, String id) {
    DiagramContext diagramContext = new OpenDiagramStep(context, diagramName).run();
    EObject pc = context.getSemanticElement(id);

    new DragAndDropFromProjectExplorerTool(diagramContext, "D&D Components From Project Explorer", pc, containerId)
        .run();
  }

  @Override
  protected String getRequiredTestModel() {
    return "XABDiagrams";
  }
}
