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

public class MCBScenario extends EmptyProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    MissionDiagram diagram = MissionDiagram.createDiagram(context, SA__CAPABILITIES,
        IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME);

    String diagramId = diagram.getDiagramId();

    String capability1 = diagram.createCapability();
    String capability2 = diagram.createCapability();
    String capability3 = diagram.createCapability();
    String capability4 = diagram.createCapability();
    diagram.removeCapability(capability1, capability2);
    diagram.insertCapability(capability1, capability2);

    String mission1 = diagram.createMission();
    String mission2 = diagram.createMission();
    diagram.removeMission(mission1);
    diagram.insertMission(mission1);

    String actor1 = diagram.createActor();
    String actor2 = diagram.createActor();
    diagram.removeActor(actor1);
    diagram.insertActor(actor1);

    String cl1 = diagram.createCapabilityExtends(capability1, capability2);
    String cl2 = diagram.createCapabilityIncludes(capability1, capability3);
    String cl3 = diagram.createCapabilityGeneralization(capability1, capability4);
    String cl4 = diagram.createCapabilityExploitation(mission1, capability1);
    String cl5 = diagram.createMissionInvolvement(mission1, actor1);
    String cl6 = diagram.createActorGeneralization(actor2, actor1);

    diagram.cannotCreateCapabilityExtends(capability1, mission1);
    diagram.cannotCreateCapabilityExtends(mission2, mission1);
    diagram.cannotCreateCapabilityExtends(mission2, capability1);

    diagram.cannotCreateCapabilityIncludes(capability2, mission1);
    diagram.cannotCreateCapabilityIncludes(mission2, mission1);
    diagram.cannotCreateCapabilityIncludes(mission2, capability1);

    diagram.cannotCreateCapabilityGeneralization(capability2, mission1);
    diagram.cannotCreateCapabilityGeneralization(mission2, mission1);
    diagram.cannotCreateCapabilityGeneralization(mission2, capability1);

    diagram.cannotCreateCapabilityExploitation(capability2, mission1);
    diagram.cannotCreateCapabilityExploitation(mission2, mission1);

    diagram.cannotCreateMissionInvolvement(capability2, mission1);
    diagram.cannotCreateMissionInvolvement(mission2, capability1);

    diagram.cannotCreateActorCapabilityGeneralization(actor1, capability1);
    diagram.cannotCreateActorGeneralization(actor1, mission1);

    DiagramHelper.setSynchronized(diagram.getDiagram(), false);
    diagram.removeRelationship(capability1, cl1);
    diagram.insertRelationship(capability2, cl1);
    diagram.removeRelationship(capability1, cl2);
    diagram.insertRelationship(capability3, cl2);
    diagram.removeRelationship(capability1, cl3);
    diagram.insertRelationship(capability1, cl3);
    diagram.removeRelationship(capability1, cl4);
    diagram.insertRelationship(mission1, cl4);
    diagram.removeRelationship(mission1, cl5);
    diagram.insertRelationship(actor1, cl5);
    diagram.removeRelationship(actor1, cl6);
    diagram.insertRelationship(actor2, cl6);
    DiagramHelper.setSynchronized(diagram.getDiagram(), true);

    diagram.removeCapability(capability2, capability3, capability4);
    diagram.removeMission(mission1);
    diagram.hasntViews(capability2, capability3, mission1, cl1, cl2, cl3, cl4);
    diagram.insertAllRelationships(capability1);
    diagram.hasViews(capability2, capability3, mission1, cl1, cl2, cl3, cl4);

    diagram.createConstraint(GenericModel.CONSTRAINT_1, diagramId);
    diagram.createConstrainedElement(GenericModel.CONSTRAINT_1, mission1);
    diagram.createConstraint(GenericModel.CONSTRAINT_2, diagramId);
    diagram.createConstrainedElement(GenericModel.CONSTRAINT_2, cl1);

    diagram.removeConstraint(GenericModel.CONSTRAINT_1, mission1);
    diagram.insertConstraint(GenericModel.CONSTRAINT_1, mission1);

    // drag and drop tests
    MissionDiagram initDiagram = MissionDiagram.createDiagram(context, SA__CAPABILITIES,
        IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME);
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