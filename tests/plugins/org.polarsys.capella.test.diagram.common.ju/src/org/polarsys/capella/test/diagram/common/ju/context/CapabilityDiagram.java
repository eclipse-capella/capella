/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
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

import org.eclipse.sirius.diagram.DDiagram;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IDNDToolNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.core.sirius.analysis.helpers.ToolProviderHelper;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateAbstractDNodeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.ReconnectTool;
import org.polarsys.capella.test.framework.context.SessionContext;

public class CapabilityDiagram extends CommonDiagram {

  BlockArchitectureExt.Type type = null;

  public CapabilityDiagram(BlockArchitectureExt.Type type, SessionContext context, DDiagram diagram) {
    super(context, diagram);
    this.type = type;
  }

  public static CapabilityDiagram createDiagram(SessionContext executionContext, String targetIdentifier, String name) {
    BlockArchitecture architecture = BlockArchitectureExt
        .getRootBlockArchitecture(executionContext.getSemanticElement(targetIdentifier));
    final BlockArchitectureExt.Type type = BlockArchitectureExt.getBlockArchitectureType(architecture);

    if (IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME.equals(name)
        || IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME.equals(name)
        || IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME.equals(name)
        || IDiagramNameConstants.CONTEXTUAL_MISSION_DIAGRAM_NAME.equals(name)) {
      return MissionDiagram.createDiagram(executionContext, targetIdentifier, name);
    }

    return (CapabilityDiagram) new CreateDiagramStep(executionContext, targetIdentifier, name) {
      @Override
      public DiagramContext getResult() {
        return new CapabilityDiagram(type, getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public String createActor() {
    return createActorInContainer(getDiagramId());
  }

  public String createActorInContainer(String containerId) {
    if (isA(IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK)
        || isA(IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME)
        || isA(IDiagramNameConstants.CONTEXTUAL_OPERATIONAL_CAPABILITIES__DIAGRAM_NAME)) {
      return createContainerElement(containerId, getActorToolName());
    }
    return createNodeElement(containerId, getActorToolName());
  }

  @Deprecated
  public void createActor(String id) {
    createActor(id, getDiagramId());
  }

  @Deprecated
  public void createActor(String id, String containerId) {
    new CreateAbstractDNodeTool(this, getActorToolName(), containerId, id).run();
  }

  public String createComponent() {
    return createContainerElement(getDiagramId(), getComponentToolName());
  }

  public String createChildComponent(String containerId) {
    return createContainerElement(containerId, getComponentToolName());
  }

  @Deprecated
  public void createComponent(String id) {
    createComponent(id, getDiagramId());
  }

  @Deprecated
  public void createComponent(String id, String containerId) {
    new CreateAbstractDNodeTool(this, getComponentToolName(), containerId, id).run();
  }

  public String createCapability() {
    return createNodeElement(getDiagramId(), getCapabilityToolName());
  }

  @Deprecated
  public void createCapability(String id) {
    new CreateAbstractDNodeTool(this, getCapabilityToolName(), getDiagramId(), id).run();
  }

  public String createActorGeneralization(String sourceId, String targetId) {
    return createEdgeElement(sourceId, targetId,
        ToolProviderHelper.getToolCreateActorGeneralization(this.getDiagram()));
  }

  @Deprecated
  public void createActorGeneralization(String sourceId, String targetId, String id) {
    new CreateDEdgeTool(this, ToolProviderHelper.getToolCreateActorGeneralization(this.getDiagram()), sourceId,
        targetId, id).run();
  }

  public void cannotCreateActorCapabilityGeneralization(String sourceId, String targetId) {
    new CreateDEdgeTool(this, ToolProviderHelper.getToolCreateActorGeneralization(this.getDiagram()), sourceId,
        targetId).cannotRun();
  }

  public void cannotCreateActorGeneralization(String sourceId, String targetId) {
    new CreateDEdgeTool(this, ToolProviderHelper.getToolCreateActorGeneralization(this.getDiagram()), sourceId,
        targetId).shouldFail();
  }

  public String createCapabilityGeneralization(String sourceId, String targetId) {
    return createEdgeElement(sourceId, targetId, getCapabilityGeneralizationToolName());
  }

  @Deprecated
  public void createCapabilityGeneralization(String sourceId, String targetId, String id) {
    new CreateDEdgeTool(this, getCapabilityGeneralizationToolName(), sourceId, targetId, id).run();
  }

  public void cannotCreateCapabilityGeneralization(String sourceId, String targetId) {
    new CreateDEdgeTool(this, getCapabilityGeneralizationToolName(), sourceId, targetId).shouldFail();
  }

  public String createCapabilityInvolvement(String sourceId, String targetId) {
    return createEdgeElement(sourceId, targetId, getCapabilityInvolvementToolName());
  }

  @Deprecated
  public void createCapabilityInvolvement(String sourceId, String targetId, String id) {
    new CreateDEdgeTool(this, getCapabilityInvolvementToolName(), sourceId, targetId, id).run();
  }

  public String createCapabilityExtends(String sourceId, String targetId) {
    return createEdgeElement(sourceId, targetId, getCapabilityExtendsToolName());
  }

  @Deprecated
  public void createCapabilityExtends(String sourceId, String targetId, String id) {
    new CreateDEdgeTool(this, getCapabilityExtendsToolName(), sourceId, targetId, id).run();
  }

  public void cannotCreateCapabilityExtends(String sourceId, String targetId) {
    new CreateDEdgeTool(this, getCapabilityExtendsToolName(), sourceId, targetId).shouldFail();
  }

  public String createCapabilityIncludes(String sourceId, String targetId) {
    return createEdgeElement(sourceId, targetId, getCapabilityIncudesToolName());
  }

  @Deprecated
  public void createCapabilityIncludes(String sourceId, String targetId, String id) {
    new CreateDEdgeTool(this, getCapabilityIncudesToolName(), sourceId, targetId, id).run();
  }

  public void cannotCreateCapabilityIncludes(String sourceId, String targetId) {
    new CreateDEdgeTool(this, getCapabilityIncudesToolName(), sourceId, targetId).shouldFail();
  }

  public String createComponentExchange(String sourceId, String targetId) {
    return createEdgeElement(sourceId, targetId, getComponentExchangesToolName());
  }

  @Deprecated
  public void createComponentExchange(String sourceId, String targetId, String id) {
    new CreateDEdgeTool(this, getComponentExchangesToolName(), sourceId, targetId, id).run();
  }

  public void insertActor(String id) {
    insertActor(id, getDiagramId());
  }

  public void insertActor(String id, String containerId) {
    new InsertRemoveTool(this, getActorsToolName(), containerId).insert(id);
  }

  public void removeActor(String id) {
    removeActor(id, getDiagramId());
  }

  public void removeActor(String id, String containerId) {
    new InsertRemoveTool(this, getActorsToolName(), containerId).remove(id);
  }

  public void insertComponent(String id) {
    insertComponent(id, getDiagramId());
  }

  public void insertComponent(String id, String containerId) {
    new InsertRemoveTool(this, getComponentsToolName(), containerId).insert(id);
  }

  public void removeComponent(String id) {
    removeComponent(id, getDiagramId());
  }

  public void removeComponent(String id, String containerId) {
    new InsertRemoveTool(this, getComponentsToolName(), containerId).remove(id);
  }

  public void insertCapability(String... id) {
    new InsertRemoveTool(this, getCapabilitiesToolName(), getDiagramId()).insert(id);
  }

  public void removeCapability(String... id) {
    new InsertRemoveTool(this, getCapabilitiesToolName(), getDiagramId()).remove(id);
  }

  public void insertRelationship(String containerId, String... id) {
    new InsertRemoveTool(this, getRelationshipsToolName(), containerId).insert(id);
  }

  public void removeRelationship(String containerId, String... id) {
    new InsertRemoveTool(this, getRelationshipsToolName(), containerId).remove(id);
  }

  public void insertAllRelationships(String containerId) {
    new InsertRemoveTool(this, getRelationshipsToolName(), containerId).insertAll();

  }

  public void reconnectActorGeneralizationSource(String edgeId, String oldSourceId, String newSourceId) {
    new ReconnectTool(this, getReconnectGeneralizationSourceToolName(), edgeId, oldSourceId, newSourceId).run();
  }

  public void reconnectActorGeneralizationTarget(String edgeId, String oldTargetId, String newTargetId) {
    new ReconnectTool(this, getReconnectGeneralizationTargetToolName(), edgeId, oldTargetId, newTargetId).run();
  }

  public void dragAndDropComponentFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId, getDNDComponentFromExplorerToolName());
  }

  public void dragAndDropComponentFromDiagram(String idDraggedElement, String containerId) {
    dragAndDrop(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_DND_COMPONENT);
  }

  public void dragAndDropCapabilityFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId, getDNDCapabilityFromExplorerToolName());
  }

  @Override
  public void dragAndDropConstraintFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId, getDNDConstraintToolName());
  }

  public void dragAndDropMissionFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_DND_MISSIONS_FROM_EXPLORER);
  }

  public void dragAndDropActorFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_DND_ACTORS_FROM_EXPLORER);
  }

  private String getCapabilityToolName() {
    String name = null;
    if (isA(IDiagramNameConstants.CONTEXTUAL_OPERATIONAL_CAPABILITIES__DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_COC_CREATE_OPERATIONAL_CAPABILITY;

    } else if (isA(IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_OCB_CREATE_OPERATIONAL_CAPABILITY;

    } else if (isA(IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MCB_CREATE_CAPABILITY;

    } else if (isA(IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MB_CREATE_CAPABILITY;

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_CC_CREATE_CAPABILITY;

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_MISSION_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_CM_CREATE_CAPABILITY;

    } else if (isA(IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK)) {
      name = IToolNameConstants.TOOL_CRB_CREATE_CAPABILITY_REALIZATION;
    }
    return name;
  }

  private String getComponentToolName() {
    String name = null;
    if (isA(IDiagramNameConstants.CONTEXTUAL_OPERATIONAL_CAPABILITIES__DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_COC_CREATE_OPERATIONAL_ENTITY;

    } else if (isA(IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_OCB_CREATE_OPERATIONAL_ENTITY;

    } else if (isA(IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
      // Tool not defined

    } else if (isA(IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME)) {
      // Tool not defined

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME)) {
      // Tool not defined

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_MISSION_DIAGRAM_NAME)) {
      // Tool not defined

    } else if (isA(IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK)) {
      name = IToolNameConstants.TOOL_CRB_CREATE_COMPONENT;
      if (type == Type.EPBS) {
        name = IToolNameConstants.TOOL_CRB_CREATE_COTS;
      }
    }
    return name;
  }

  protected String getActorToolName() {
    String name = null;
    if (isA(IDiagramNameConstants.CONTEXTUAL_OPERATIONAL_CAPABILITIES__DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_COC_CREATE_OPERATIONAL_ACTOR;

    } else if (isA(IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_OCB_CREATE_OPERATIONAL_ACTOR;

    } else if (isA(IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MCB_CREATE_ACTOR;

    } else if (isA(IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MB_CREATE_ACTOR;

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_CC_CREATE_ACTOR;

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_MISSION_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_CM_CREATE_ACTOR;

    } else if (isA(IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK)) {
      name = IToolNameConstants.TOOL_CRB_CREATE_ACTOR_NAME;
    }
    return name;
  }

  private String getCapabilityExtendsToolName() {
    String name = null;
    if (isA(IDiagramNameConstants.CONTEXTUAL_OPERATIONAL_CAPABILITIES__DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_COC_CREATE_EXTENDS;

    } else if (isA(IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_OCB_CREATE_EXTENDS;

    } else if (isA(IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MCB_CREATE_EXTENDS;

    } else if (isA(IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME)) {
      // Tool not defined

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_CC_CREATE_EXTENDS;

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_MISSION_DIAGRAM_NAME)) {
      // Tool not defined

    } else if (isA(IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK)) {
      name = IToolNameConstants.TOOL_CRB_CREATE_EXTENDS;
    }
    return name;
  }

  private String getCapabilityIncudesToolName() {
    String name = null;
    if (isA(IDiagramNameConstants.CONTEXTUAL_OPERATIONAL_CAPABILITIES__DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_COC_CREATE_INCLUDES;

    } else if (isA(IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_OCB_CREATE_INCLUDES;

    } else if (isA(IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MCB_CREATE_INCLUDES;

    } else if (isA(IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME)) {
      // Tool not defined

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_CC_CREATE_INCLUDES;

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_MISSION_DIAGRAM_NAME)) {
      // Tool not defined

    } else if (isA(IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK)) {
      name = IToolNameConstants.TOOL_CRB_CREATE_INCLUDES;
    }
    return name;
  }

  private String getCapabilityGeneralizationToolName() {
    String name = null;
    if (isA(IDiagramNameConstants.CONTEXTUAL_OPERATIONAL_CAPABILITIES__DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_COC_CREATE_OPERATIONAL_CAPABILITY_GENERALIZATION;

    } else if (isA(IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_OCB_CREATE_OPERATIONAL_CAPABILITY_GENERALIZATION;

    } else if (isA(IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MCB_CREATE_CAPABILITY_GENERALIZATION;

    } else if (isA(IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME)) {
      // Tool not defined

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_CC_CREATE_CAPABILITY_GENERALIZATION;

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_MISSION_DIAGRAM_NAME)) {
      // Tool not defined

    } else if (isA(IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK)) {
      name = IToolNameConstants.TOOL_CRB_CREATE_CAPABILITY_GENERALIZATION;
    }
    return name;
  }

  private String getCapabilityInvolvementToolName() {
    String name = null;
    if (isA(IDiagramNameConstants.CONTEXTUAL_OPERATIONAL_CAPABILITIES__DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_COC_CREATE_INVOLMENT;

    } else if (isA(IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_OCB_CREATE_INVOLMENT;

    } else if (isA(IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MCB_CREATE_INVOLVED_ACTOR;

    } else if (isA(IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MB_CREATE_ACTOR_INVOLVEMENT;

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_CC_CREATE_INVOLVED_ACTOR;

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_MISSION_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_CM_CREATE_ACTOR_INVOLVEMENT;

    } else if (isA(IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK)) {
      name = IToolNameConstants.TOOL_CRB_CREATE_INVOLVEMENT;
    }
    return name;
  }

  private String getComponentExchangesToolName() {
    String name = null;
    if (isA(IDiagramNameConstants.CONTEXTUAL_OPERATIONAL_CAPABILITIES__DIAGRAM_NAME)) {
      // Tool not defined

    } else if (isA(IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_OCB_CREATE_COMMUNICATION_MEAN;

    } else if (isA(IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
      // Tool not defined

    } else if (isA(IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME)) {
      // Tool not defined

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME)) {
      // Tool not defined

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_MISSION_DIAGRAM_NAME)) {
      // Tool not defined

    } else if (isA(IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK)) {
      // Tool not defined

    }
    return name;
  }

  private String getRelationshipsToolName() {
    String name = null;
    if (isA(IDiagramNameConstants.CONTEXTUAL_OPERATIONAL_CAPABILITIES__DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_COC_INSERT_REMOVE_RELATIONSHIPS;

    } else if (isA(IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_OCB_INSERT_REMOVE_RELATIONSHIPS;

    } else if (isA(IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MCB_INSERT_REMOVE_RELATIONSHIPS;

    } else if (isA(IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MB_INSERT_REMOVE_RELATIONSHIPS;

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_CC_INSERT_REMOVE_RELATIONSHIPS;

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_MISSION_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_CM_INSERT_REMOVE_RELATIONSHIPS;

    } else if (isA(IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK)) {
      name = IToolNameConstants.TOOL_CRB_INSERT_REMOVE_RELATIONSHIPS;

    }
    return name;
  }

  private String getCapabilitiesToolName() {
    String name = null;
    if (isA(IDiagramNameConstants.CONTEXTUAL_OPERATIONAL_CAPABILITIES__DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_COC_INSERT_REMOVE_OPERATIONAL_CAPABILITIES;

    } else if (isA(IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_OCB_INSERT_REMOVE_OPERATIONAL_CAPABILITIES;

    } else if (isA(IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MCB_INSERT_REMOVE_CAPABILITIES;

    } else if (isA(IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MB_INSERT_REMOVE_CAPABILITIES;

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_CC_INSERT_REMOVE_CAPABILITIES;

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_MISSION_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_CM_INSERT_CAPABILITIES;

    } else if (isA(IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK)) {
      name = IToolNameConstants.TOOL_CRB_INSERT_REMOVE_CAPABILITY_REALIZATIONS;

    }
    return name;
  }

  private String getComponentsToolName() {
    String name = null;
    if (isA(IDiagramNameConstants.CONTEXTUAL_OPERATIONAL_CAPABILITIES__DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_COC_INSERT_REMOVE_OPERATIONAL_ENTITIES;

    } else if (isA(IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_OCB_INSERT_REMOVE_OPERATIONAL_ENTITIES;

    } else if (isA(IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
      // Tool not defined

    } else if (isA(IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME)) {
      // Tool not defined

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME)) {
      // Tool not defined

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_MISSION_DIAGRAM_NAME)) {
      // Tool not defined

    } else if (isA(IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK)) {
      name = IToolNameConstants.TOOL_CRB_INSERT_REMOVE_COMPONENTS;
    }
    return name;
  }

  private String getActorsToolName() {
    String name = null;
    if (isA(IDiagramNameConstants.CONTEXTUAL_OPERATIONAL_CAPABILITIES__DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_COC_INSERT_REMOVE_OPERATIONAL_ACTORS;

    } else if (isA(IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_OCB_INSERT_REMOVE_OPERATIONAL_ACTORS;

    } else if (isA(IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MCB_INSERT_REMOVE_ACTORS;

    } else if (isA(IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MB_INSERT_REMOVE_ACTORS;

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_CC_INSERT_REMOVE_ACTORS;

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_MISSION_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_CM_INSERT_ACTORS;

    } else if (isA(IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK)) {
      name = IToolNameConstants.TOOL_CRB_INSERT_REMOVE_ACTORS;
    }
    return name;
  }

  private String getReconnectGeneralizationSourceToolName() {
    return IToolNameConstants.TOOL_CC_RECONNECT_GENERALIZATION_SOURCE;
  }

  private String getReconnectGeneralizationTargetToolName() {
    return IToolNameConstants.TOOL_CC_RECONNECT_GENERALIZATION_TARGET;
  }

  private String getDNDConstraintToolName() {
    if (isA(IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK)) {
      return IDNDToolNameConstants.TOOL_DND_CONSTRAINT_FROM_EXPLORER;
    }
    return IDNDToolNameConstants.TOOL_DND_CONSTRAINTS_FROM_EXPLORER;
  }

  private String getDNDComponentFromExplorerToolName() {
    if (isA(IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME)
        || isA(IDiagramNameConstants.CONTEXTUAL_OPERATIONAL_CAPABILITIES__DIAGRAM_NAME)) {
      return IDNDToolNameConstants.TOOL_DND_ENTITIES_FROM_EXPLORER;
    }
    return IDNDToolNameConstants.TOOL_CRB_DND_COMPONENT_FROM_EXPLORER;
  }

  private String getDNDCapabilityFromExplorerToolName() {
    if (isA(IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME)
        || isA(IDiagramNameConstants.CONTEXTUAL_OPERATIONAL_CAPABILITIES__DIAGRAM_NAME)) {
      return IDNDToolNameConstants.TOOL_DND_OPERATIONAL_CAPABILITIES_FROM_EXPLORER;
    }
    return IDNDToolNameConstants.TOOL_DND_CAPABILITIES_FROM_EXPLORER;
  }

}
