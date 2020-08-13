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
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateAbstractDNodeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.framework.context.SessionContext;

public class MissionDiagram extends CapabilityDiagram {

  public MissionDiagram(Type type, SessionContext context, DDiagram diagram) {
    super(type, context, diagram);
  }

  public static MissionDiagram createDiagram(SessionContext executionContext, String targetIdentifier, String name) {
    BlockArchitecture architecture = BlockArchitectureExt
        .getRootBlockArchitecture(executionContext.getSemanticElement(targetIdentifier));
    final BlockArchitectureExt.Type type = BlockArchitectureExt.getBlockArchitectureType(architecture);

    return (MissionDiagram) new CreateDiagramStep(executionContext, targetIdentifier, name) {
      @Override
      public DiagramContext getResult() {
        return new MissionDiagram(type, getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public static MissionDiagram openDiagram(SessionContext executionContext, String name,
      final BlockArchitectureExt.Type type) {
    return (MissionDiagram) new OpenDiagramStep(executionContext, name) {
      @Override
      public DiagramContext getResult() {
        return new MissionDiagram(type, getExecutionContext(), diagram);
      }
    }.run().open();
  }

  @Deprecated
  public void createCapabilityExploitation(String sourceId, String targetId, String id) {
    new CreateDEdgeTool(this, getCapabilityExploitationToolName(), sourceId, targetId, id).run();
  }

  public String createCapabilityExploitation(String sourceId, String targetId) {
    return createEdgeElement(sourceId, targetId, getCapabilityExploitationToolName());
  }

  public void cannotCreateCapabilityExploitation(String sourceId, String targetId) {
    new CreateDEdgeTool(this, getCapabilityExploitationToolName(), sourceId, targetId).shouldFail();
  }

  public String createMission() {
    return createNodeElement(getDiagramId(), getMissionToolName());
  }

  @Deprecated
  public void createMission(String id) {
    new CreateAbstractDNodeTool(this, getMissionToolName(), getDiagramId(), id).run();
  }

  public String createMissionInvolvement(String sourceId, String targetId) {
    return createEdgeElement(sourceId, targetId, getMissionInvolvementToolName());
  }

  public void cannotCreateMissionInvolvement(String sourceId, String targetId) {
    new CreateDEdgeTool(this, getMissionInvolvementToolName(), sourceId, targetId).shouldFail();
  }

  public boolean canInsertMission() {
    if (isA(IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
      return true;

    } else if (isA(IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME)) {
      return true;
    }
    return false;
  }

  public void insertMission(String id) {
    new InsertRemoveTool(this, getMissionsToolName(), getDiagramId()).insert(id);
  }

  public void removeMission(String id) {
    new InsertRemoveTool(this, getMissionsToolName(), getDiagramId()).remove(id);
  }

  private String getMissionToolName() {
    String name = null;
    if (isA(IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MCB_CREATE_MISSION;

    } else if (isA(IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MB_CREATE_MISSION;

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_CC_CREATE_MISSION;

    }
    return name;
  }

  private String getMissionsToolName() {
    String name = null;
    if (isA(IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MCB_INSERT_REMOVE_MISSIONS;

    } else if (isA(IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MB_INSERT_REMOVE_MISSIONS;

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_CC_SHOW_MISSIONS;
    }
    return name;
  }

  private String getCapabilityExploitationToolName() {
    String name = null;
    if (isA(IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MCB_CREATE_CAPABILITY_EXPLOITATION;

    } else if (isA(IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MB_CREATE_CAPABILITY_EXPLOITATION;

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_CC_CREATE_CAPABILITY_EXPLOITATION;

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_MISSION_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_CM_CREATE_CAPABILITY_EXPLOITATION;

    }
    return name;
  }

  private String getMissionInvolvementToolName() {
    String name = null;
    if (isA(IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MCB_CREATE_INVOLVED_ACTOR;

    } else if (isA(IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MB_CREATE_ACTOR_INVOLVEMENT;

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_MISSION_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_CM_CREATE_ACTOR_INVOLVEMENT;
    }
    return name;
  }
}
