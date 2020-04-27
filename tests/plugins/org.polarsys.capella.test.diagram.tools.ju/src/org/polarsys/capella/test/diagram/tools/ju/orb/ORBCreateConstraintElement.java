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
import org.polarsys.capella.test.diagram.tools.ju.model.DiagramToolsModel;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class ORBCreateConstraintElement extends DiagramToolsModel {

  @Override
  public void test() throws Exception {

    String diagramName = "Test Operational Role Breakdown Diagram";

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    ORBDiagram orb = ORBDiagram.openDiagram(context, diagramName);

    String parentOperationalRoleId = orb.createOperationalRole(orb.getDiagramId());
    String operationalActivity1Id = orb.createOperationalActivity(parentOperationalRoleId);
    String operationalActivity2Id = orb.createOperationalActivity(parentOperationalRoleId);
    String interaction = orb.createInteraction(operationalActivity1Id, operationalActivity2Id);

    orb.createConstraint(GenericModel.CONSTRAINT_1);
    orb.createConstrainedElement(GenericModel.CONSTRAINT_1, parentOperationalRoleId);
    orb.createConstrainedElement(GenericModel.CONSTRAINT_1, operationalActivity1Id);
    orb.createConstrainedElement(GenericModel.CONSTRAINT_1, operationalActivity2Id);
    orb.createConstrainedElement(GenericModel.CONSTRAINT_1, interaction);
  }
}
