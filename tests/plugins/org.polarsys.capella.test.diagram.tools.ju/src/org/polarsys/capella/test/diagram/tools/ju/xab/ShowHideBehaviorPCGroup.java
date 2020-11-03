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
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

import org.polarsys.capella.test.diagram.common.ju.context.PABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ShowHideBehaviorPCGroup extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    String[] checkDCIds = { PA__PAB_COMPONENT_PC3_1, LA__LAB_COMPONENT_PORT_1,
        LA__LAB_FUNCTION_1, LA__LAB_COMPONENT_EXCHANGE_C1 };

    testOnXAB_DeployedBehaviorComponent(context, PA__PAB_DIAGRAM,
        BlockArchitectureExt.Type.PA, PA__PAB_COMPONENT_PC3_1,
        PA__PAB_COMPONENT_PC3, PA__PAB_COMPONENT_PORT_PC3_1_CP1);
    
    testOnXAB_BehaviorComponent(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA,
        PA__PAB_COMPONENT_PC3_2, PA__PAB_COMPONENT_PC3,
        PA__PAB_COMPONENT_PC3_2_PORT_PC1);
    
    testOnXAB_DeployedBehaviorComponent(context, PA__PAB_DIAGRAM,
        BlockArchitectureExt.Type.PA, PA__PAB_COMPONENT_PC3,
        PA__PAB_COMPONENT_PC1, checkDCIds);
  }

  public void testOnXAB_DeployedBehaviorComponent(SessionContext context, String diagramName,
      BlockArchitectureExt.Type type, String deployedCompId, String containerId, String... checkIds) {
    PABDiagram pabDiagram = PABDiagram.openDiagram(context, diagramName);

    pabDiagram.removeDeployedBehaviorComponent(deployedCompId, containerId);

    pabDiagram.hasntViews(checkIds);

    pabDiagram.insertDeployedBehaviorComponent(deployedCompId, containerId);
  }

  public void testOnXAB_BehaviorComponent(SessionContext context, String diagramName,
      BlockArchitectureExt.Type type, String behaviorCompID, String containerId, String... checkIds) {
    PABDiagram pabDiagram = PABDiagram.openDiagram(context, diagramName);
    pabDiagram.removeBehaviorComponent(behaviorCompID, pabDiagram.getDiagramId());

    pabDiagram.hasntViews(checkIds);

    pabDiagram.insertBehaviorComponent(behaviorCompID, pabDiagram.getDiagramId());
  }

  
}
