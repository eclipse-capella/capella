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

public class MissionDiagram extends CapabilityDiagram {

  public MissionDiagram(Type type, SessionContext context, DDiagram diagram) {
    super(type, context, diagram);
  }

  public static MissionDiagram createDiagram(SessionContext executionContext, String targetIdentifier, String name) {
    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(executionContext
        .getSemanticElement(targetIdentifier));
    final BlockArchitectureExt.Type type = BlockArchitectureExt.getBlockArchitectureType(architecture);

    return (MissionDiagram) new CreateDiagramStep(executionContext, targetIdentifier, name) {
      @Override
      public DiagramContext getResult() {
        return new MissionDiagram(type, getExecutionContext(), _diagram);
      }
    }.run().open();
  }

  public void createCapabilityExploitation(String sourceId, String targetId, String id) {
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
    new CreateDEdgeTool(this, name, sourceId, targetId, id).run();
  }

  public void createCapabilityExploitation(String sourceId, String targetId) {
    createCapabilityExploitation(sourceId, targetId, null);
  }

  public void createMission(String id) {
    String name = null;
    if (isA(IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MCB_CREATE_MISSION;

    } else if (isA(IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MB_CREATE_MISSION;

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_CC_CREATE_MISSION;

    }
    new CreateAbstractDNodeTool(this, name, getDiagramId(), id).run();
  }

  public void createMissionInvolvement(String sourceId, String targetId) {
    String name = null;
    if (isA(IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MCB_CREATE_INVOLVED_ACTOR;

    } else if (isA(IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MB_CREATE_ACTOR_INVOLVEMENT;

    }
    new CreateDEdgeTool(this, name, sourceId, targetId).run();
  }

  public void insertMission(String id) {
    String name = null;
    if (isA(IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MCB_INSERT_REMOVE_MISSIONS;

    } else if (isA(IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_MB_INSERT_REMOVE_MISSIONS;

    } else if (isA(IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_CC_SHOW_MISSIONS;

    }
    new InsertRemoveTool(this, name, getDiagramId()).insert(id);
  }

}
