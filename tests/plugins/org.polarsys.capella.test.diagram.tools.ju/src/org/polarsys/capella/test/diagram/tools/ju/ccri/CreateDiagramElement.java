/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.ccri;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateNodeTool;
import org.polarsys.capella.test.framework.context.SessionContext;

public class CreateDiagramElement extends AbstractDiagramTestCase {

  private String PHYSICAL_CCRI_DIAGRAM = "Physical Contextual Capability Realization Involvement Diagram";
  private String LOGICAL_CRI_DIAGRAM = "Logical Contextual Capability Realization Involvement Diagram";
  private String elementType;

  public CreateDiagramElement(String elementType) {
    this.elementType = elementType;
  }

  @Override
  protected String getRequiredTestModel() {
    return "DiagramAction";
  }

  @Override
  public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    DiagramContext physicalDiagramContext = new OpenDiagramStep(context, PHYSICAL_CCRI_DIAGRAM).run();
    DDiagram physicalCCRIDiagram = physicalDiagramContext.getDiagram();

    int originalPhysicalDiagramSize = physicalCCRIDiagram.getDiagramElements().size();
    new CreateNodeTool(physicalDiagramContext, elementType, physicalDiagramContext.getDiagramId()).run();

    DiagramContext logicalDiagramContext = new OpenDiagramStep(context, LOGICAL_CRI_DIAGRAM).run();
    DDiagram logicalCCRIDiagram = logicalDiagramContext.getDiagram();

    int originalLogicalDiagramSize = logicalCCRIDiagram.getDiagramElements().size();
    new CreateNodeTool(logicalDiagramContext, elementType, logicalDiagramContext.getDiagramId()).run();

    // Check that our elements have been created
    assertEquals("Wrong number of DiagramElements in the Physical CCRI Diagram", originalPhysicalDiagramSize + 1,
        physicalCCRIDiagram.getDiagramElements().size());
    assertEquals("Wrong number of DiagramElements in the Logical CCRI Diagram", originalLogicalDiagramSize + 1,
        logicalCCRIDiagram.getDiagramElements().size());
  }
}
