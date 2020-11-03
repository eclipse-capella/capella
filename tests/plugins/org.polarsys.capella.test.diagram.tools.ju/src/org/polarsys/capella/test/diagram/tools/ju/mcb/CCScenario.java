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
package org.polarsys.capella.test.diagram.tools.ju.mcb;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.MissionDiagram;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class CCScenario extends EmptyProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    MissionDiagram initDiagram = MissionDiagram.createDiagram(context, SA__CAPABILITIES,
        IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME);

    String capability1 = initDiagram.createCapability();
    String capability2 = initDiagram.createCapability();
    String mission1 = initDiagram.createMission();
    String actor1 = initDiagram.createActor();

    MissionDiagram diagram = MissionDiagram.createDiagram(context, capability1,
        IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME);
    String diagramId = diagram.getDiagramId();
    diagram.hasView(capability1);
    diagram.hasCountViews(1);

    diagram.insertCapability(capability2);
    String capability3 = diagram.createCapability();
    String capability4 = diagram.createCapability();
    diagram.removeCapability(capability3, capability4);
    diagram.insertCapability(capability3, capability4);

    String mission2 = diagram.createMission();
    diagram.insertMission(mission1);
    diagram.removeMission(mission2);
    diagram.insertMission(mission2);

    diagram.insertActor(actor1);
    String actor2 = diagram.createActor();
    diagram.removeActor(actor2);
    diagram.insertActor(actor2);

    String cl1 = diagram.createCapabilityExploitation(mission1, capability1);
    String cl2 = diagram.createCapabilityInvolvement(capability1, actor1);
    String cl3 = diagram.createCapabilityExtends(capability1, capability2);
    String cl4 = diagram.createCapabilityIncludes(capability1, capability3);
    String cl5 = diagram.createCapabilityGeneralization(capability1, capability4);
    String cl6 = diagram.createActorGeneralization(actor1, actor2);

    DiagramHelper.setSynchronized(diagram.getDiagram(), false);
    diagram.removeRelationship(mission1, cl1);
    diagram.hasntView(cl1);
    diagram.insertRelationship(capability1, cl1);
    diagram.hasView(cl1);
    diagram.removeRelationship(actor1, cl2);
    diagram.insertRelationship(capability1, cl2);
    diagram.removeRelationship(capability1, cl3);
    diagram.insertRelationship(capability2, cl3);
    diagram.removeRelationship(capability1, cl4);
    diagram.insertRelationship(capability3, cl4);
    diagram.removeRelationship(capability1, cl5);
    diagram.insertRelationship(capability4, cl5);
    diagram.removeRelationship(actor1, cl6);
    diagram.insertRelationship(actor2, cl6);
    DiagramHelper.setSynchronized(diagram.getDiagram(), true);

    diagram.cannotCreateCapabilityExploitation(capability1, mission1);
    diagram.cannotCreateCapabilityExploitation(mission2, mission1);

    diagram.cannotCreateCapabilityExtends(capability1, mission1);
    diagram.cannotCreateCapabilityExtends(mission2, mission1);
    diagram.cannotCreateCapabilityExtends(mission2, capability1);

    diagram.createConstraint(GenericModel.CONSTRAINT_1, diagramId);
    diagram.createConstrainedElement(GenericModel.CONSTRAINT_1, capability1);
    diagram.createConstrainedElement(GenericModel.CONSTRAINT_1, cl6);
    diagram.createConstraint(GenericModel.CONSTRAINT_2, diagramId);
    diagram.createConstrainedElement(GenericModel.CONSTRAINT_2, cl4);

    diagram.removeConstraint(GenericModel.CONSTRAINT_1, capability1);
    diagram.insertConstraint(GenericModel.CONSTRAINT_1, capability1);
    diagram.removeConstraint(GenericModel.CONSTRAINT_1, cl6);
    diagram.insertConstraint(GenericModel.CONSTRAINT_1, cl6);

    diagram.removeConstraint(GenericModel.CONSTRAINT_2, cl4);
    diagram.insertConstraint(GenericModel.CONSTRAINT_2, cl4);

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