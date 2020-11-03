/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.common.ju.context;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IDNDToolNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateContainerTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.xab.ReuseComponentTool;
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

  public String createNodeComponent(String id, String containerId) {
    DDiagramElementContainer element = new CreateContainerTool(this,
        IToolNameConstants.TOOL_PAB_CREATE_NODE_PHYSICAL_COMPONENT, containerId, id).run();
    return ((CapellaElement) element.getTarget()).getId();
  }

  public void cannotCreateNodeComponent(String id, String containerId) {
    new CreateContainerTool(this, IToolNameConstants.TOOL_PAB_CREATE_NODE_PHYSICAL_COMPONENT, containerId, id)
        .cannotRun();
  }

  public void cannotReuseNodeComponent(String containerId, String id) {
    new ReuseComponentTool(this, IToolNameConstants.TOOL_PAB_REUSE_NODE_PC, containerId) {
      @Override
      protected void postRunTest() {
        for (EObject component : getDiagramContext().getSessionContext().getSemanticElements(selectedElements)) {
          if (component instanceof Component) {
            List<Part> parts = ((Component) component).getRepresentingParts();
            getDiagramContext().hasView(parts.get(parts.size() - 1).getId());

            assertTrue("A new part referencing " + ((Component) component).getId() + " shouldn't have been created",
                parts.size() == representingParts.get(component).size());
          }
        }
      }
    }.select(id);
  }

  public String createBehaviorComponent(String id, String containerId) {
    DDiagramElementContainer element = new CreateContainerTool(this,
        IToolNameConstants.TOOL_PAB_CREATE_BEHAVIOR_PHYSICAL_COMPONENT, containerId, id).run();
    return ((CapellaElement) element.getTarget()).getId();
  }

  public void cannotCreateBehaviorComponent(String id, String containerId) {
    new CreateContainerTool(this, IToolNameConstants.TOOL_PAB_CREATE_BEHAVIOR_PHYSICAL_COMPONENT, containerId, id)
        .cannotRun();
  }

  public void cannotReuseBehaviourComponent(String containerId, String id) {
    new ReuseComponentTool(this, IToolNameConstants.TOOL_PAB_REUSE_BEHAVIOR_PC, containerId) {
      @Override
      protected void postRunTest() {
        for (EObject component : getDiagramContext().getSessionContext().getSemanticElements(selectedElements)) {
          if (component instanceof Component) {
            List<Part> parts = ((Component) component).getRepresentingParts();
            getDiagramContext().hasView(parts.get(parts.size() - 1).getId());

            assertTrue("A new part referencing " + ((Component) component).getId() + " shouldn't have been created",
                parts.size() == representingParts.get(component).size());
          }
        }
      }
    }.select(id);
  }

  public void failedCreateBehaviorComponent(String id, String containerId) {
    new CreateContainerTool(this, IToolNameConstants.TOOL_PAB_CREATE_BEHAVIOR_PHYSICAL_COMPONENT, containerId, id)
        .shouldFail();
  }

  public String createDeployedBehaviorComponent(String id, String containerId) {
    DDiagramElementContainer element = new CreateContainerTool(this, IToolNameConstants.TOOL_PAB_DEPLOY_BEHAVIOR_PC,
        containerId, id).run();
    return ((CapellaElement) element.getTarget()).getId();
  }

  public void cannotCreateDeployedBehaviorComponent(String id, String containerId) {
    new CreateContainerTool(this, IToolNameConstants.TOOL_PAB_DEPLOY_BEHAVIOR_PC, containerId, id).cannotRun();
  }

  public String createDeployedNodeComponent(String id, String containerId) {
    DDiagramElementContainer element = new CreateContainerTool(this, IToolNameConstants.TOOL_PAB_DEPLOY_NODE_PC,
        containerId, id).run();
    return ((CapellaElement) element.getTarget()).getId();
  }

  public void cannotCreateDeployedNodeComponent(String id, String containerId) {
    new CreateContainerTool(this, IToolNameConstants.TOOL_PAB_DEPLOY_NODE_PC, containerId, id).cannotRun();
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
    String toolName = IToolNameConstants.TOOL_PAB_INSERT_REMOVE_NODE_COMPONENTS_MONOPART;
    if (TriStateBoolean.True
        .equals(CapellaProjectHelper.isReusableComponentsDriven(getDiagramDescriptor().getTarget()))) {
      toolName = IToolNameConstants.TOOL_PAB_INSERT_REMOVE_NODE_COMPONENTS_MULTIPART;
    }
    new InsertRemoveTool(this, toolName, containerId).remove(id);
  }

  public void insertNodeComponent(String id, String containerId) {
    String toolName = IToolNameConstants.TOOL_PAB_INSERT_REMOVE_NODE_COMPONENTS_MONOPART;
    if (TriStateBoolean.True
        .equals(CapellaProjectHelper.isReusableComponentsDriven(getDiagramDescriptor().getTarget()))) {
      toolName = IToolNameConstants.TOOL_PAB_INSERT_REMOVE_NODE_COMPONENTS_MULTIPART;
    }
    new InsertRemoveTool(this, toolName, containerId).insert(id);
  }

  public void reuseNodeComponent(String containerId, String... ids) {
    new ReuseComponentTool(this, IToolNameConstants.TOOL_PAB_REUSE_NODE_PC, containerId).select(ids);
  }

  public void reuseBehaviourComponent(String containerId, String... ids) {
    new ReuseComponentTool(this, IToolNameConstants.TOOL_PAB_REUSE_BEHAVIOR_PC, containerId).select(ids);
  }

  public void removeBehaviorComponent(String id, String containerId) {
    String toolName = IToolNameConstants.TOOL_PAB_INSERT_REMOVE_BEHAVIOUR_COMPONENTS_MONOPART;
    if (TriStateBoolean.True
        .equals(CapellaProjectHelper.isReusableComponentsDriven(getDiagramDescriptor().getTarget()))) {
      toolName = IToolNameConstants.TOOL_PAB_INSERT_REMOVE_BEHAVIOUR_COMPONENTS_MULTIPART;
    }

    new InsertRemoveTool(this, toolName, containerId).remove(id);
  }

  public void insertBehaviorComponent(String id, String containerId) {
    String toolName = IToolNameConstants.TOOL_PAB_INSERT_REMOVE_BEHAVIOUR_COMPONENTS_MONOPART;
    if (TriStateBoolean.True
        .equals(CapellaProjectHelper.isReusableComponentsDriven(getDiagramDescriptor().getTarget()))) {
      toolName = IToolNameConstants.TOOL_PAB_INSERT_REMOVE_BEHAVIOUR_COMPONENTS_MULTIPART;
    }
    new InsertRemoveTool(this, toolName, containerId).insert(id);
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
