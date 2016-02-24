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
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateAbstractDNodeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;

public class CapabilityDiagram extends CommonDiagram {

  BlockArchitectureExt.Type type = null;

  public CapabilityDiagram(BlockArchitectureExt.Type type, SessionContext context, DDiagram diagram) {
    super(context, diagram);
    this.type = type;
  }

  public static CapabilityDiagram createDiagram(SessionContext executionContext, String targetIdentifier, String name) {
    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(executionContext
        .getSemanticElement(targetIdentifier));
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
        return new CapabilityDiagram(type, getExecutionContext(), _diagram);
      }
    }.run().open();
  }

  public void createActor(String id) {
    createActor(id, getDiagramId());
  }

  public void createActor(String id, String containerId) {
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
    new CreateAbstractDNodeTool(this, name, containerId, id).run();
  }

  public void createComponent(String id) {
    createComponent(id, getDiagramId());
  }

  public void createComponent(String id, String containerId) {
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
    new CreateAbstractDNodeTool(this, name, containerId, id).run();
  }

  public void createCapability(String id) {
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
    new CreateAbstractDNodeTool(this, name, getDiagramId(), id).run();
  }

  public void createActorGeneralization(String sourceId, String targetId) {
    createActorGeneralization(sourceId, targetId, null);
  }

  public void createActorGeneralization(String sourceId, String targetId, String id) {
    String name = null;
    if (isA(IDiagramNameConstants.CONTEXTUAL_OPERATIONAL_CAPABILITIES__DIAGRAM_NAME)) {
      // Tool not defined

    } else if (isA(IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME)) {
      // Tool not defined

    } else if (isA(IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MCB_CREATE_ACTOR_GENERALIZATION;

    } else if (isA(IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MB_CREATE_ACTOR_GENERALIZATION;

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_CC_CREATE_ACTOR_GENERALIZATION;

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_MISSION_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_CM_CREATE_ACTOR_GENERALIZATION;

    } else if (isA(IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK)) {
      name = IToolNameConstants.TOOL_CRB_CREATE_ACTOR_GENERALIZATION;

    }
    new CreateDEdgeTool(this, name, sourceId, targetId, id).run();
  }

  public void createCapabilityGeneralization(String sourceId, String targetId) {
    createCapabilityGeneralization(sourceId, targetId, null);
  }

  public void createCapabilityGeneralization(String sourceId, String targetId, String id) {
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
    new CreateDEdgeTool(this, name, sourceId, targetId, id).run();
  }

  public void createCapabilityInvolvement(String sourceId, String targetId) {
    createCapabilityInvolvement(sourceId, targetId, null);
  }

  public void createCapabilityInvolvement(String sourceId, String targetId, String id) {
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
    new CreateDEdgeTool(this, name, sourceId, targetId, id).run();
  }

  public void createCapabilityExtends(String sourceId, String targetId) {
    createCapabilityExtends(sourceId, targetId, null);
  }

  public void createCapabilityExtends(String sourceId, String targetId, String id) {
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
    new CreateDEdgeTool(this, name, sourceId, targetId, id).run();
  }

  public void createCapabilityIncludes(String sourceId, String targetId) {
    createCapabilityIncludes(sourceId, targetId, null);
  }

  public void createCapabilityIncludes(String sourceId, String targetId, String id) {
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
    new CreateDEdgeTool(this, name, sourceId, targetId, id).run();
  }

  public void createComponentExchange(String sourceId, String targetId) {
    createComponentExchange(sourceId, targetId, null);
  }

  public void createComponentExchange(String sourceId, String targetId, String id) {
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
    new CreateDEdgeTool(this, name, sourceId, targetId, id).run();
  }

  public void insertActor(String... id) {
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
    new InsertRemoveTool(this, name, getDiagramId()).insert(id);
  }

  public void removeActor(String... id) {
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
    new InsertRemoveTool(this, name, getDiagramId()).remove(id);
  }

  public void insertComponent(String id) {
    insertComponent(id, getDiagramId());
  }

  public void insertComponent(String id, String containerId) {
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
    new InsertRemoveTool(this, name, containerId).insert(id);
  }

  public void removeComponent(String id) {
    removeComponent(id, getDiagramId());
  }

  public void removeComponent(String id, String containerId) {
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
    new InsertRemoveTool(this, name, containerId).remove(id);
  }

  public void insertCapability(String... id) {
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
    new InsertRemoveTool(this, name, getDiagramId()).insert(id);
  }

  public void removeCapability(String... id) {
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
    new InsertRemoveTool(this, name, getDiagramId()).remove(id);
  }

  public void insertRelationship(String id) {
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
    new InsertRemoveTool(this, name, getDiagramId()).insert(id);
  }

  public void insertAllRelationships(String containerId) {
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

    new InsertRemoveTool(this, name, containerId).insertAll();

  }

}
