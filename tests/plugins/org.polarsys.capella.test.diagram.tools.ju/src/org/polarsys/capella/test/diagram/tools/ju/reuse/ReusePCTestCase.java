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
package org.polarsys.capella.test.diagram.tools.ju.reuse;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.context.PABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ReusePCTestCase extends ReuseOfComponentsProject {

  @Override
  protected String getRequiredTestModel() {
    return "ReuseOfComponentsModel";
  }

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    PABDiagram diagram = PABDiagram.openDiagram(context, PA__PAB_DIAGRAM);
    String diagramId = diagram.getDiagramId();

    // Reuse PC Node
    diagram.reuseNodeComponent(diagramId, PA__NODE_PC_1);
    diagram.reuseNodeComponent(diagramId, PA__NODE_PC_1, PA__NODE_PC_2, PA__NODE_PC_3, PA__NODE_PC_3_1);
    diagram.reuseNodeComponent(diagramId, PA__NODE_PC_1);
    diagram.cannotReuseNodeComponent(diagramId, PA__BEHAVIOR_PC_5);

    diagram.reuseNodeComponent(PA__PART_1_NODE_PC_1, PA__NODE_PC_2, PA__NODE_PC_3, PA__NODE_PC_3_1);
    diagram.reuseNodeComponent(PA__PART_2_NODE_PC_2, PA__NODE_PC_3, PA__NODE_PC_3_1);

    // Reuse PC Behavior
    diagram.reuseBehaviourComponent(diagramId, PA__BEHAVIOR_PC_5_1, PA__BEHAVIOR_PC_6);
    diagram.reuseBehaviourComponent(diagramId, PA__BEHAVIOR_PC_6);
    diagram.reuseBehaviourComponent(diagramId, PA__BEHAVIOR_PC_5, PA__BEHAVIOR_PC_5_1);
    diagram.cannotReuseBehaviourComponent(diagramId, PA__NODE_PC_1);

    // show/hide reused PC Node components
    diagram.removeDeployedNodeComponent(PA__PART_2_NODE_PC_2, PA__PART_1_NODE_PC_1);
    diagram.insertDeployedNodeComponent(PA__PART_2_NODE_PC_2, PA__PART_1_NODE_PC_1);
    diagram.removeNodeComponent(PA__PART_1_NODE_PC_1, diagramId);
    diagram.insertNodeComponent(PA__PART_1_NODE_PC_1, diagramId);

    // show/hide reused PC Behavior components
    diagram.removeBehaviorComponent(PA__PART_1_BEHAVIOR_PC_5_1, PA__PART_4_BEHAVIOR_PC_5);
    diagram.insertBehaviorComponent(PA__PART_1_BEHAVIOR_PC_5_1, PA__PART_4_BEHAVIOR_PC_5);
    diagram.removeDeployedBehaviorComponent(PA__PART_4_BEHAVIOR_PC_5, PA__PART_3_NODE_PC_3);
    diagram.insertDeployedBehaviorComponent(PA__PART_4_BEHAVIOR_PC_5, PA__PART_3_NODE_PC_3);
  }
}
