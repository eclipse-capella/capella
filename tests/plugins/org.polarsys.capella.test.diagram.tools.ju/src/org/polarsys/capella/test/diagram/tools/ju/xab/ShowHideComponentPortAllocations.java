package org.polarsys.capella.test.diagram.tools.ju.xab;

import org.eclipse.sirius.business.api.session.Session;

import org.polarsys.capella.test.diagram.common.ju.context.PABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ShowHideComponentPortAllocations extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnXAB(context, PA__PAB_DIAGRAM,
        PA__PAB_COMPONENT_PORT_ALLOCATION);
  }

  public void testOnXAB(SessionContext context, String diagramName, String linkId) {
    PABDiagram pabDiagram = PABDiagram.openDiagram(context, diagramName);

    pabDiagram.removeComponentPortAllocation(linkId);
    pabDiagram.insertComponentPortAllocation(linkId);
  }

  
}
