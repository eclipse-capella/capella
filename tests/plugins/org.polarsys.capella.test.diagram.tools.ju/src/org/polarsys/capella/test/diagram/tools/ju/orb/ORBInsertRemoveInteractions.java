/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.orb;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.context.ORBDiagram;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.diagram.tools.ju.model.DiagramToolsModel;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ORBInsertRemoveInteractions extends DiagramToolsModel {

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

    String interaction_1_2 = orb.createInteraction(opActivity1, opActivity2);
    String interaction_1_3 = orb.createInteraction(opActivity1, opActivity3);
    String interaction_2_3 = orb.createInteraction(opActivity2, opActivity3);

    DiagramHelper.setSynchronized(orb.getDiagram(), false);

    orb.removeInteractions(opActivity1, interaction_1_2, interaction_1_3);
    orb.removeInteractions(opActivity2, interaction_2_3);

    orb.insertInteractions(opActivity1, interaction_1_2, interaction_1_3);
    orb.insertInteractions(opActivity2, interaction_2_3);

    DiagramHelper.setSynchronized(orb.getDiagram(), true);
  }

}
