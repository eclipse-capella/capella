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

public class ShowHideNodePCGroup extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    String[] checkIds = { PA__PAB_COMPONENT_PC8, PA__PAB_COMPONENT_PC3,
        LA__LAB_PHYSICAL_LINK_PL1 };

    testOnXAB_DeployedNodeComponent(context, PA__PAB_DIAGRAM,
        BlockArchitectureExt.Type.PA, PA__PAB_COMPONENT_PC8,
        PA__PAB_COMPONENT_PC1);
    
    testOnXAB_NodeComponent(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA,
        PA__PAB_COMPONENT_PC1, checkIds);

  }

  public void testOnXAB_NodeComponent(SessionContext context, String diagramName,
      BlockArchitectureExt.Type type, String nodeCompId, String... checkIds) {
    PABDiagram pabDiagram = PABDiagram.openDiagram(context, diagramName);

    pabDiagram.removeNodeComponent(nodeCompId, pabDiagram.getDiagramId());
    pabDiagram.hasntViews(checkIds);
    pabDiagram.insertNodeComponent(nodeCompId, pabDiagram.getDiagramId());
  }

  public void testOnXAB_DeployedNodeComponent(SessionContext context, String diagramName,
      BlockArchitectureExt.Type type, String deployedCompId, String containerId, String ...checkIds) {
    PABDiagram pabDiagram = PABDiagram.openDiagram(context, diagramName);

    pabDiagram.removeDeployedNodeComponent(deployedCompId, containerId);
    pabDiagram.hasntViews(checkIds);
    pabDiagram.insertDeployedNodeComponent(deployedCompId, containerId);
  }

  
}
