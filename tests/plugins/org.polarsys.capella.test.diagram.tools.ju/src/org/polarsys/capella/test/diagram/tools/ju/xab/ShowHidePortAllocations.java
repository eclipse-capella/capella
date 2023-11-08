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
package org.polarsys.capella.test.diagram.tools.ju.xab;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.framework.context.SessionContext;

import junit.framework.Test;

/**
 * Test Show/Hide on a Physical Link with a Category.
 */
public class ShowHidePortAllocations extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testABShowHidePortAllocation(context, SA__SAB_DIAGRAM, SA__SAB_COMPONENT_PORT_ALLOCATION);
    testABShowHidePortAllocation(context, LA__LAB_DIAGRAM, LA__LAB_COMPONENT_PORT_ALLOCATION);
    testABShowHidePortAllocation(context, PA__PAB_DIAGRAM, PA__PAB_COMPONENT_PORT_ALLOCATION);
  }
  
  public void testABShowHidePortAllocation(SessionContext context, String system, String portAllocation) {
    DiagramContext diagramContext = new OpenDiagramStep(context, system).run();

    // Ensure that Physical Link is shown
    diagramContext.hasView(portAllocation);

    // Hide the Physical Link
    DSemanticDecorator pa1 = diagramContext.getView(portAllocation);
    assertTrue(pa1 instanceof DDiagramElement);
    hideDiagramElement((DDiagramElement) pa1);

    // Check that the Physical Link is hidden
    diagramContext.hasHiddenView(portAllocation);

    // Refresh the Diagram
    diagramContext.refreshDiagram();
  }

  public static Test suite() {
    return new ShowHidePortAllocations();
  }
}
