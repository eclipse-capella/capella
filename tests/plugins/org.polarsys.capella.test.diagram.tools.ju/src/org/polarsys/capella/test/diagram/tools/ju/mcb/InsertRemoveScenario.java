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
package org.polarsys.capella.test.diagram.tools.ju.mcb;

import junit.framework.Test;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.CapabilityDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.MissionDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

/**
 * Test for each architecture phases some basic insert remove on capabilities diagrams
 */
public class InsertRemoveScenario extends EmptyProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    // OperationalAnalysis
    createOperationalAnalysis(context);
    testOn(context, GenericModel.CAPABILITY_1, IDiagramNameConstants.CONTEXTUAL_OPERATIONAL_CAPABILITIES__DIAGRAM_NAME);
    testOn(context, OA__OPERATIONAL_CAPABILITIES,
        IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME);

    // SystemAnalysis
    createSystemAnalysis(context);
    testOn(context, SA__CAPABILITIES, IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME);
    testOn(context, GenericModel.CAPABILITY_1, IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME);
    testOn(context, GenericModel.MISSION_1, IDiagramNameConstants.CONTEXTUAL_MISSION_DIAGRAM_NAME);
    testOn(context, SA__MISSIONS, IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME);

    // LogicalArchitecture
    createLogicalArchitecture(context);
    testOn(context, LA__CAPABILITIES, IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK);

    // PhysicalArchitecture
    createPhysicalArchitecture(context);
    testOn(context, PA__CAPABILITIES, IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK);

  }

  protected void testOn(SessionContext context, String sourceId, String diagramName) {
    CapabilityDiagram diagramContext = CapabilityDiagram.createDiagram(context, sourceId, diagramName);

    diagramContext.insertActor(GenericModel.ACTOR_1);
    diagramContext.removeActor(GenericModel.ACTOR_1);

    diagramContext.insertCapability(GenericModel.CAPABILITY_2);
    diagramContext.removeCapability(GenericModel.CAPABILITY_2);

    if (diagramContext instanceof MissionDiagram && ((MissionDiagram) diagramContext).canInsertMission()) {
      ((MissionDiagram) diagramContext).insertMission(GenericModel.MISSION_2);
      ((MissionDiagram) diagramContext).removeMission(GenericModel.MISSION_2);
    }

  }

  protected void createOperationalAnalysis(SessionContext context) {
    CapabilityDiagram diagram = CapabilityDiagram.createDiagram(context, OA__OPERATIONAL_CAPABILITIES,
        IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME);
    diagram.createCapability(GenericModel.CAPABILITY_1);
    diagram.createCapability(GenericModel.CAPABILITY_2);
    diagram.createActor(GenericModel.ACTOR_1);
  }

  protected void createSystemAnalysis(SessionContext context) {
    MissionDiagram diagram = MissionDiagram.createDiagram(context, SA__CAPABILITIES,
        IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME);
    diagram.createCapability(GenericModel.CAPABILITY_1);
    diagram.createCapability(GenericModel.CAPABILITY_2);
    diagram.createActor(GenericModel.ACTOR_1);
    diagram.createMission(GenericModel.MISSION_1);
    diagram.createMission(GenericModel.MISSION_2);
  }

  protected void createLogicalArchitecture(SessionContext context) {
    CapabilityDiagram diagram = CapabilityDiagram.createDiagram(context, LA__CAPABILITIES,
        IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK);
    diagram.createCapability(GenericModel.CAPABILITY_1);
    diagram.createCapability(GenericModel.CAPABILITY_2);
    diagram.createActor(GenericModel.ACTOR_1);
  }

  protected void createPhysicalArchitecture(SessionContext context) {
    CapabilityDiagram diagram = CapabilityDiagram.createDiagram(context, PA__CAPABILITIES,
        IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK);
    diagram.createCapability(GenericModel.CAPABILITY_1);
    diagram.createCapability(GenericModel.CAPABILITY_2);
    diagram.createActor(GenericModel.ACTOR_1);
  }

  public static Test suite() {
    return new InsertRemoveScenario();
  }
}
