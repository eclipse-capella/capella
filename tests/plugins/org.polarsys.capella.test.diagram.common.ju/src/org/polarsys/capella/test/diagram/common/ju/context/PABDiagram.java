/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.sirius.analysis.constants.IDNDToolNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateContainerTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.framework.context.SessionContext;

public class PABDiagram extends XABDiagram {

  public PABDiagram(SessionContext context, DDiagram diagram) {
    super(Type.PA, context, diagram);
  }

  public static PABDiagram openDiagram(SessionContext executionContext, String name) {
    return (PABDiagram) new OpenDiagramStep(executionContext, name) {
      @Override
      public DiagramContext getResult() {
        return new PABDiagram(getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public static PABDiagram createDiagram(SessionContext executionContext, String targetIdentifier) {

    return (PABDiagram) new CreateDiagramStep(executionContext, targetIdentifier,
        IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME) {
      @Override
      public DiagramContext getResult() {
        return new PABDiagram(getExecutionContext(), diagram);
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

  public void removeDeployedBehaviorComponent(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_PAB_INSERT_REMOVE_DEPLOYED_BEHAVIOUR_PCS, containerId)
        .remove(id);
  }

  public void insertDeployedBehaviorComponent(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_PAB_INSERT_REMOVE_DEPLOYED_BEHAVIOUR_PCS, containerId)
        .insert(id);
  }

  public void removeDeployedNodeComponent(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_PAB_INSERT_REMOVE_DEPLOYED_NODE_PCS, containerId).remove(id);
  }

  public void insertDeployedNodeComponent(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_PAB_INSERT_REMOVE_DEPLOYED_NODE_PCS, containerId).insert(id);
  }

  public void removeNodeComponent(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_PAB_INSERT_REMOVE_NODE_COMPONENTS_MONOPART, containerId)
        .remove(id);
  }

  public void insertNodeComponent(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_PAB_INSERT_REMOVE_NODE_COMPONENTS_MONOPART, containerId)
        .insert(id);
  }

  public void reuseBehaviorComponent(String id, String containerId) {
    new InsertRemoveTool(this, new String[] { IToolNameConstants.TOOL_PAB_REUSE_BEHAVIOR_PC }, containerId).insert(id);
  }

  public void removeBehaviorComponent(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_PAB_INSERT_REMOVE_BEHAVIOUR_COMPONENTS_MONOPART, containerId)
        .remove(id);
  }

  public void insertBehaviorComponent(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_PAB_INSERT_REMOVE_BEHAVIOUR_COMPONENTS_MONOPART, containerId)
        .insert(id);
  }

  public void createComponentPortAllocation(String sourceId, String targetId) {
    new CreateDEdgeTool(this, IToolNameConstants.TOOL_PAB_CREATE_COMPONENT_PORT_ALLOCATION, sourceId, targetId).run();
  }

  public void removeComponentPortAllocation(String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_PAB_INSERT_REMOVE_COMPONENT_PORT_ALLOCATION, containerId)
        .remove();
  }

  public void insertComponentPortAllocation(String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_PAB_INSERT_REMOVE_COMPONENT_PORT_ALLOCATION, containerId)
        .insert();
  }

  public void manageManageNodePCsDeployment(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_PAB_MANAGE_NODE_COMP_DEPLOYMENT, containerId).insert(id);
  }

  public void manageBehaviorPCsDeployment(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_PAB_MANAGE_BEHAVIOR_COMP_DEPLOYMENT, containerId).insert(id);
  }

  public void dragAndDropDeployment(String idDraggedElement, String containerId) {
    dragAndDrop(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_PAB_DND_DEPLOYMENT);
  }

}
