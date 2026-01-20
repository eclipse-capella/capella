package org.polarsys.capella.test.diagram.tools.ju.xab;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.DragAndDropTool;
import org.polarsys.capella.test.framework.context.SessionContext;

public class DnDAllocations extends AncestorsDragAndDropProject {
  private static final String testFailMessage = "DragAndDrop was supposed to conserve ports in specific cases, but the expected number was not matched.";

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testPortDND(context, LA__LAB_DIAGRAM, LA__LAB_CP1, LA__LAB_LC111, 1, "port DnD from parent to child");
    testPortDND(context, LA__LAB_DIAGRAM, LA__LAB_CP1, LA__LAB_LC1, 1, "port DnD from child to parent");
    testPortDND(context, LA__LAB_DIAGRAM, LA__LAB_CP3, LA__LAB_LC1, 1, "port DnD from child to grandparent");
    testPortDND(context, LA__LAB_DIAGRAM, LA__LAB_CP5, LA__LAB_LC1, 2, "port DnD with 2 delegations to parent");
    testPortDND(context, LA__LAB_DIAGRAM, LA__LAB_CP5, LA__LAB_LA122, 1, "port DnD with 2, invalidating one");
  }

  private void testPortDND(SessionContext context, String diagramName, String portID, String containerID, int expectedAllocation, String description) {
    EObject portIdEObject = context.getSemanticElement(portID);
    assertTrue("movingPort should be a ComponentPort in test: " + description, portIdEObject instanceof ComponentPort);

    ComponentPort movingPort = (ComponentPort) portIdEObject;
    DiagramContext dndDiagram = new OpenDiagramStep(context, diagramName).run();
    new DragAndDropTool(dndDiagram, IToolNameConstants.TOOL_AB_DND_COMPONENT_PORT, portID, containerID).run();

    assertEquals(testFailMessage + "in test: " + description, expectedAllocation, movingPort.getAllocatedFunctionPorts().size());
  }

}
