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

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.framework.context.SessionContext;

import junit.framework.Test;

/**
 * Test Show/Hide on a Physical Link with a Category.
 */
public class ShowHidePortAllocations extends AbstractDiagramTestCase {

  private static String SAB_SYSTEM = "[SAB] System Port Allocations";
  private static String SAB_PORT_ALLOCATION_TO_FIP_1 = "4162b655-e2a5-413d-955d-36678d72042d";

  private static String LAB_LOGICAL_SYSTEM = "[LAB] Logical System Port Allocations";
  private static String LAB_PORT_ALLOCATION_TO_FOP_1 = "34828e65-c465-478c-87b8-d4a7b490b728";

  private static String PAB_PHYSICAL_SYSTEM = "[PAB] Physical System Port Allocations";
  private static String PAB_COMPONENT_PORT_ALLOCATION_TO_CP_1 = "68963aa7-f89a-4a54-96e9-3788c6657b0f";
  private static String PAB_PORT_ALLOCATION_TO_FIP_1 = "91c6bde4-d073-4b1d-849b-69d310ca256f";

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

  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testABShowHidePortAllocation(context, SAB_SYSTEM, SAB_PORT_ALLOCATION_TO_FIP_1);

    testABShowHidePortAllocation(context, LAB_LOGICAL_SYSTEM, LAB_PORT_ALLOCATION_TO_FOP_1);

    testABShowHidePortAllocation(context, PAB_PHYSICAL_SYSTEM, PAB_PORT_ALLOCATION_TO_FIP_1);
    testABShowHidePortAllocation(context, PAB_PHYSICAL_SYSTEM, PAB_COMPONENT_PORT_ALLOCATION_TO_CP_1);
  }

  @Override
  protected String getRequiredTestModel() {
    return "ShowHideExchangesAndLinks";
  }

  public static Test suite() {
    return new ShowHidePortAllocations();
  }
}
