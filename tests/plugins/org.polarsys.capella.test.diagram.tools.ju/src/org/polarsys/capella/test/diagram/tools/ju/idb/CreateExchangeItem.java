/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.idb;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.IDBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;

public class CreateExchangeItem extends EmptyProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    testOnDiagram(context, IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME);
    testOnDiagram(context, IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME);
    testOnDiagram(context, IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME);
    testOnDiagram(context, IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME);
  }

  private void testOnDiagram(SessionContext context, String diagramKind) {
    IDBDiagram idb = IDBDiagram.createDiagram(context, diagramKind, LA__LOGICAL_SYSTEM);

    idb.createEvent();
    idb.createOperation();
    idb.createFlow();
    idb.createData();
  }

}
