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

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.MissionDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class CMScenario extends EmptyProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    MissionDiagram initDiagram = MissionDiagram.createDiagram(context, SA__CAPABILITIES,
        IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME);

    initDiagram.createCapability(GenericModel.CAPABILITY_1);
    initDiagram.createMission(GenericModel.MISSION_1);
    initDiagram.createActor(GenericModel.ACTOR_1);

    MissionDiagram diagram = MissionDiagram.createDiagram(context, GenericModel.MISSION_1,
        IDiagramNameConstants.CONTEXTUAL_MISSION_DIAGRAM_NAME);
    diagram.hasView(GenericModel.MISSION_1);
    diagram.hasCountViews(1);

    diagram.insertCapability(GenericModel.CAPABILITY_1);
    diagram.createCapability(GenericModel.CAPABILITY_2);

    diagram.insertActor(GenericModel.ACTOR_1);
    diagram.createActor(GenericModel.ACTOR_2);

    diagram.createCapabilityExploitation(GenericModel.MISSION_1, GenericModel.CAPABILITY_1, GenericModel.CL_1);
    diagram.createCapabilityExploitation(GenericModel.MISSION_1, GenericModel.CAPABILITY_2);
    diagram.createMissionInvolvement(GenericModel.MISSION_1, GenericModel.ACTOR_1);
    diagram.createMissionInvolvement(GenericModel.MISSION_1, GenericModel.ACTOR_2);

    diagram.createActorGeneralization(GenericModel.ACTOR_1, GenericModel.ACTOR_2);

    diagram.createConstraint(GenericModel.CONSTRAINT_1, diagram.getDiagramId());
    diagram.createConstrainedElement(GenericModel.CONSTRAINT_1, GenericModel.CAPABILITY_1);
    diagram.createConstrainedElement(GenericModel.CONSTRAINT_1, GenericModel.CL_1);

  }
}