/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.context;

import org.eclipse.sirius.diagram.DDiagram;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateContainerTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveContainerCreation;

public class PABDiagram extends XABDiagram {

  public PABDiagram(SessionContext context, DDiagram diagram) {
    super(Type.PA, context, diagram);
  }

  public static PABDiagram createDiagram(SessionContext executionContext, String targetIdentifier) {

    return (PABDiagram) new CreateDiagramStep(executionContext, targetIdentifier,
        IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME) {
      @Override
      public DiagramContext getResult() {
        return new PABDiagram(getExecutionContext(), _diagram);
      }
    }.run().open();
  }

  public void createNodeComponent(String id, String containerId) {
    new CreateContainerTool(this, IToolNameConstants.TOOL_PAB_CREATE_NODE_PHYSICAL_COMPONENT, containerId, id).run();
  }

  public void createBehaviorComponent(String id, String containerId) {
    new CreateContainerTool(this, IToolNameConstants.TOOL_PAB_CREATE_BEHAVIOR_PHYSICAL_COMPONENT, containerId, id)
        .run();
  }

  public void createDeployedBehaviorComponent(String id, String containerId) {
    new CreateContainerTool(this, IToolNameConstants.TOOL_PAB_DEPLOY_BEHAVIOR_PC, containerId, id).run();
  }

  public void createDeployedNodeComponent(String id, String containerId) {
    new CreateContainerTool(this, IToolNameConstants.TOOL_PAB_DEPLOY_NODE_PC, containerId, id).run();
  }

  public void removeBehaviorComponent(String id, String containerId) {
    new InsertRemoveContainerCreation(this, new String[] {
        IToolNameConstants.TOOL_PAB_INSERT_REMOVE_COMPONENTS_MONOPART,
        IToolNameConstants.TOOL_PAB_INSERT_REMOVE_BEHAVIOR_PCS }, containerId).remove(id);
  }

  public void removeDeployedBehaviorComponent(String id, String containerId) {
    new InsertRemoveContainerCreation(this, IToolNameConstants.TOOL_PAB_INSERT_REMOVE_DEPLOYED_PCS, containerId)
        .remove(id);
  }

  public void removeDeployedNodeComponent(String id, String containerId) {
    new InsertRemoveContainerCreation(this, IToolNameConstants.TOOL_PAB_INSERT_REMOVE_DEPLOYED_PCS, containerId)
        .remove(id);
  }

  public void removeNodeComponent(String id, String containerId) {
    new InsertRemoveContainerCreation(this, new String[] {
        IToolNameConstants.TOOL_PAB_INSERT_REMOVE_COMPONENTS_MONOPART,
        IToolNameConstants.TOOL_PAB_INSERT_REMOVE_NODE_PCS }, containerId).remove(id);

  }

  public void insertNodeComponent(String id, String containerId) {
    new InsertRemoveContainerCreation(this, new String[] {
        IToolNameConstants.TOOL_PAB_INSERT_REMOVE_COMPONENTS_MONOPART,
        IToolNameConstants.TOOL_PAB_INSERT_REMOVE_NODE_PCS }, containerId).insert(id);

  }
}
