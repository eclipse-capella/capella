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
import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.MissionDiagram;
import org.polarsys.capella.test.diagram.common.ju.step.filters.FilterStep;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class CRBScenario extends EmptyProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    testOn(context, LA__CAPABILITIES);
    testOn(context, PA__CAPABILITIES);
  }

  private void testOn(SessionContext context, String sourceId) {
    MissionDiagram diagram = MissionDiagram.createDiagram(context, sourceId,
        IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK);

    String capability1 = diagram.createCapability();
    String capability2 = diagram.createCapability();
    String capability3 = diagram.createCapability();
    String capability4 = diagram.createCapability();

    String component1 = diagram.createComponent();
    String component2 = diagram.createChildComponent(component1);

    String actor1 = diagram.createActor();
    String actor2 = diagram.createActor();

    String cl1 = diagram.createCapabilityExtends(capability1, capability2);
    String cl2 = diagram.createCapabilityIncludes(capability1, capability3);
    String cl3 = diagram.createCapabilityGeneralization(capability1, capability4);

    String cl4 = diagram.createCapabilityInvolvement(capability1, component2);
    String cl5 = diagram.createCapabilityInvolvement(capability1, actor1);

    String cl6 = diagram.createActorGeneralization(actor2, actor1);

    new FilterStep(diagram, IFilterNameConstants.FILTER_CRB_HIDE_CAPABILITY_REALIZATIONS).activate(capability1,
        capability2, capability3, capability4);
    new FilterStep(diagram, IFilterNameConstants.FILTER_CRB_HIDE_CAPABILITY_REALIZATIONS).desactivate(capability1,
        capability2, capability3, capability4);

    new FilterStep(diagram, IFilterNameConstants.FILTER_CRB_HIDE_INVOLVEMENTS).activate(cl4);
    new FilterStep(diagram, IFilterNameConstants.FILTER_CRB_HIDE_INVOLVEMENTS).desactivate(cl4);

    new FilterStep(diagram, IFilterNameConstants.FILTER_CRB_HIDE_CAPABILTY_INCLUDES).activate(cl2);
    new FilterStep(diagram, IFilterNameConstants.FILTER_CRB_HIDE_CAPABILTY_INCLUDES).desactivate(cl2);

    new FilterStep(diagram, IFilterNameConstants.FILTER_CRB_HIDE_CAPABILITY_EXTENDS).activate(cl1);
    new FilterStep(diagram, IFilterNameConstants.FILTER_CRB_HIDE_CAPABILITY_EXTENDS).desactivate(cl1);

    new FilterStep(diagram, IFilterNameConstants.FILTER_CRB_HIDE_CAPABILITY_REALIZATION_GENERALIZATIONS).activate(cl3);
    new FilterStep(diagram, IFilterNameConstants.FILTER_CRB_HIDE_CAPABILITY_REALIZATION_GENERALIZATIONS)
        .desactivate(cl3);

    new FilterStep(diagram, IFilterNameConstants.FILTER_CRB_HIDE_COMPONENTS).activate(component1, component2);
    new FilterStep(diagram, IFilterNameConstants.FILTER_CRB_HIDE_COMPONENTS).desactivate(component1, component2);

    new FilterStep(diagram, IFilterNameConstants.FILTER_CRB_HIDE_ACTORS).activate(actor1, actor2);
    new FilterStep(diagram, IFilterNameConstants.FILTER_CRB_HIDE_ACTORS).desactivate(actor1, actor2);

    new FilterStep(diagram, IFilterNameConstants.FILTER_CRB_HIDE_ACTOR_GENERALIZATIONS).activate(cl6);
    new FilterStep(diagram, IFilterNameConstants.FILTER_CRB_HIDE_ACTOR_GENERALIZATIONS).desactivate(cl6);

    DiagramHelper.setSynchronized(diagram.getDiagram(), false);
    diagram.removeRelationship(capability1, cl1);
    diagram.insertRelationship(capability1, cl1);
    diagram.removeRelationship(capability1, cl2);
    diagram.insertRelationship(capability1, cl2);
    diagram.removeRelationship(capability1, cl3);
    diagram.insertRelationship(capability1, cl3);
    diagram.removeRelationship(capability1, cl4);
    diagram.insertRelationship(capability1, cl4);
    diagram.removeRelationship(capability1, cl5);
    diagram.insertRelationship(capability1, cl5);
    diagram.removeRelationship(actor2, cl6);
    diagram.insertRelationship(actor2, cl6);
    DiagramHelper.setSynchronized(diagram.getDiagram(), true);

    diagram.removeCapability(capability1, capability2, capability3, capability4);
    diagram.insertCapability(capability1, capability2, capability3, capability4);

    diagram.removeComponent(component1);
    diagram.insertComponent(component1);
    diagram.insertComponent(component2, component1);

    diagram.removeActor(actor1);
    diagram.removeActor(actor2);
    diagram.insertActor(actor1);
    diagram.insertActor(actor2);

    diagram.removeCapability(capability2);
    diagram.removeCapability(capability3);
    diagram.removeCapability(capability4);
    diagram.removeComponent(component1);
    diagram.removeActor(actor1);
    diagram.removeActor(actor2);

    diagram.insertAllRelationships(capability1);

    diagram.hasView(capability1);
    diagram.hasView(capability2);
    diagram.hasView(capability3);
    diagram.hasView(capability4);
    diagram.hasView(component2);
    diagram.hasView(actor1);

    diagram.createConstraint(GenericModel.CONSTRAINT_1, diagram.getDiagramId());
    diagram.createConstrainedElement(GenericModel.CONSTRAINT_1, component2);
    diagram.createConstraint(GenericModel.CONSTRAINT_2, diagram.getDiagramId());
    diagram.createConstrainedElement(GenericModel.CONSTRAINT_2, cl4);

    diagram.removeConstraint(GenericModel.CONSTRAINT_1, component2);
    diagram.insertConstraint(GenericModel.CONSTRAINT_1, component2);

    diagram.removeConstraint(GenericModel.CONSTRAINT_2, cl4);
    diagram.insertConstraint(GenericModel.CONSTRAINT_2, cl4);
  }
}
