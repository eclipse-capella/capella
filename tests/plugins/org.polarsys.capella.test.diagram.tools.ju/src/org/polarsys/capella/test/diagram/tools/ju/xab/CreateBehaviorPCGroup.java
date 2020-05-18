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
package org.polarsys.capella.test.diagram.tools.ju.xab;

import org.eclipse.sirius.business.api.session.Session;

import org.polarsys.capella.test.diagram.common.ju.context.PABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class CreateBehaviorPCGroup extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnXAB(context, PA__PAB_DIAGRAM);
  }

  public void testOnXAB(SessionContext context, String diagramName) {
    PABDiagram pabDiagram = PABDiagram.openDiagram(context, diagramName);

    pabDiagram.createBehaviorComponent(GenericModel.BEHAVIOR_PC_1, pabDiagram.getDiagramId());
    pabDiagram.createDeployedBehaviorComponent(GenericModel.DEPLOY_BEHAVIOR_PC_1, GenericModel.BEHAVIOR_PC_1);
    pabDiagram.createBehaviorComponent(GenericModel.BEHAVIOR_PC_1_1, GenericModel.BEHAVIOR_PC_1);
    pabDiagram.createBehaviorComponent(GenericModel.BEHAVIOR_PC_1_1_1, GenericModel.BEHAVIOR_PC_1_1);
    pabDiagram.createBehaviorComponent(GenericModel.BEHAVIOR_PC_1_2, GenericModel.DEPLOY_BEHAVIOR_PC_1);
    pabDiagram.createDeployedBehaviorComponent(GenericModel.DEPLOY_BEHAVIOR_PC_1_2_1, GenericModel.BEHAVIOR_PC_1_2);
  }

  
}
