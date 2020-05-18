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
import org.polarsys.capella.test.diagram.common.ju.context.MissionDiagram;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class MBScenario extends EmptyProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    MissionDiagram initDiagram = MissionDiagram.createDiagram(context, SA__CAPABILITIES,
        IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME);

    String capability1 = initDiagram.createCapability();
    String mission1 = initDiagram.createMission();
    String actor1 = initDiagram.createActor();

    MissionDiagram diagram = MissionDiagram.createDiagram(context, SA__MISSIONS,
        IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME);
    String diagramId = diagram.getDiagramId();

    diagram.insertCapability(capability1);
    String capability2 = diagram.createCapability();

    diagram.insertMission(mission1);
    String mission2 = diagram.createMission();

    diagram.insertActor(actor1);
    String actor2 = diagram.createActor();

    String cl1 = diagram.createCapabilityExploitation(mission2, capability2);
    String cl2 = diagram.createMissionInvolvement(mission2, actor2);
    String cl3 = diagram.createActorGeneralization(actor1, actor2);

    DiagramHelper.setSynchronized(diagram.getDiagram(), false);
    diagram.removeRelationship(mission2, cl1);
    diagram.insertRelationship(capability2, cl1);
    diagram.removeRelationship(mission2, cl2);
    diagram.insertRelationship(mission2, cl2);
    diagram.removeRelationship(actor1, cl3);
    diagram.insertRelationship(actor1, cl3);
    DiagramHelper.setSynchronized(diagram.getDiagram(), true);

    diagram.createConstraint(GenericModel.CONSTRAINT_1, diagramId);
    diagram.createConstrainedElement(GenericModel.CONSTRAINT_1, mission2);
    diagram.createConstrainedElement(GenericModel.CONSTRAINT_1, cl1);

    diagram.removeConstraint(GenericModel.CONSTRAINT_1, mission2);
    diagram.insertConstraint(GenericModel.CONSTRAINT_1, mission2);

    diagram.removeConstraint(GenericModel.CONSTRAINT_1, cl1);
    diagram.insertConstraint(GenericModel.CONSTRAINT_1, cl1);

    // drag and drop tests
    String constraint = initDiagram.createConstraint(GenericModel.CONSTRAINT_3);
    diagram.dragAndDropConstraintFromExplorer(constraint, diagramId);

    String mission = initDiagram.createMission();
    diagram.dragAndDropMissionFromExplorer(mission, diagramId);

    String actor = initDiagram.createActor();
    diagram.dragAndDropActorFromExplorer(actor, diagramId);

    String capability = initDiagram.createCapability();
    diagram.dragAndDropCapabilityFromExplorer(capability, diagramId);
  }
}