package org.polarsys.capella.test.diagram.tools.ju.idb;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.IDBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;

public class CreateDelegation extends EmptyProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    testOnDiagram(context, IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME);
    testOnDiagram(context, IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME);
  }

  private void testOnDiagram(SessionContext context, String diagramKind) {
    IDBDiagram idb = IDBDiagram.createDiagram(context, diagramKind, LA__LOGICAL_SYSTEM);
    String componentId = idb.createComponent();
    String subComponentId = idb.createSubComponent(componentId);
    idb.createDelegation(componentId, subComponentId);
  }

}
