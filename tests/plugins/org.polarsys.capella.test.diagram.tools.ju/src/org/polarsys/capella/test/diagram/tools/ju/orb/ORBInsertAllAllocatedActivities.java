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
package org.polarsys.capella.test.diagram.tools.ju.orb;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.context.ORBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.DiagramToolsModel;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ORBInsertAllAllocatedActivities extends DiagramToolsModel {

  @Override
  public void test() throws Exception {

    String diagramName = "Test Operational Role Breakdown Diagram";

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    ORBDiagram orb = ORBDiagram.openDiagram(context, diagramName);

    String parentOperationalRole = orb.createOperationalRole(orb.getDiagramId());
    String opActivity1 = orb.createOperationalActivity(parentOperationalRole);
    String opActivity2 = orb.createOperationalActivity(parentOperationalRole);
    String opActivity3 = orb.createOperationalActivity(parentOperationalRole);
    String opActivity4 = orb.createOperationalActivity(parentOperationalRole);

    orb.removeAllocatedActivities(parentOperationalRole, opActivity1, opActivity2, opActivity3, opActivity4);

    orb.insertAllAllocatedActivities(parentOperationalRole);
  }
}
