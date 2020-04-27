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
package org.polarsys.capella.test.diagram.tools.ju.mcb;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.CapabilityDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.MissionDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;

import junit.framework.Test;

/**
 * Test for each architecture phases some basic insert remove on capabilities diagrams
 */
public class InsertRemoveScenario extends EmptyProject {

  String actor1;
  String actor2;
  String capability1;
  String capability2;
  String mission1;
  String mission2;

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    // OperationalAnalysis
    createOperationalAnalysis(context);
    testOn(context, capability1, IDiagramNameConstants.CONTEXTUAL_OPERATIONAL_CAPABILITIES__DIAGRAM_NAME);
    testOn(context, OA__OPERATIONAL_CAPABILITIES,
        IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME);

    // SystemAnalysis
    createSystemAnalysis(context);
    testOn(context, SA__CAPABILITIES, IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME);
    testOn(context, capability1, IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME);
    testOn(context, mission1, IDiagramNameConstants.CONTEXTUAL_MISSION_DIAGRAM_NAME);
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

    diagramContext.insertActor(actor1);
    diagramContext.removeActor(actor1);

    diagramContext.insertCapability(capability2);
    diagramContext.removeCapability(capability2);

    if (diagramContext instanceof MissionDiagram && ((MissionDiagram) diagramContext).canInsertMission()) {
      ((MissionDiagram) diagramContext).insertMission(mission2);
      ((MissionDiagram) diagramContext).removeMission(mission2);
    }

  }

  protected void createOperationalAnalysis(SessionContext context) {
    CapabilityDiagram diagram = CapabilityDiagram.createDiagram(context, OA__OPERATIONAL_CAPABILITIES,
        IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME);
    capability1 = diagram.createCapability();
    capability2 = diagram.createCapability();
    actor1 = diagram.createActor();
  }

  protected void createSystemAnalysis(SessionContext context) {
    MissionDiagram diagram = MissionDiagram.createDiagram(context, SA__CAPABILITIES,
        IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME);
    capability1 = diagram.createCapability();
    capability2 = diagram.createCapability();
    actor1 = diagram.createActor();
    mission1 = diagram.createMission();
    mission2 = diagram.createMission();
  }

  protected void createLogicalArchitecture(SessionContext context) {
    CapabilityDiagram diagram = CapabilityDiagram.createDiagram(context, LA__CAPABILITIES,
        IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK);
    capability1 = diagram.createCapability();
    capability2 = diagram.createCapability();
    actor1 = diagram.createActor();
  }

  protected void createPhysicalArchitecture(SessionContext context) {
    CapabilityDiagram diagram = CapabilityDiagram.createDiagram(context, PA__CAPABILITIES,
        IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK);
    capability1 = diagram.createCapability();
    capability2 = diagram.createCapability();
    actor1 = diagram.createActor();
  }

  public static Test suite() {
    return new InsertRemoveScenario();
  }
}
