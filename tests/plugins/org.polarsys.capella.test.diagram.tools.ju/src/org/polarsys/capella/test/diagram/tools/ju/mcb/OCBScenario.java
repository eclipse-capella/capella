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
import org.polarsys.capella.test.diagram.common.ju.context.CapabilityDiagram;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class OCBScenario extends EmptyProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    CapabilityDiagram diagram = CapabilityDiagram.createDiagram(context, OA__OPERATIONAL_CAPABILITIES,
        IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME);
    String diagramId = diagram.getDiagramId();

    String component1 = diagram.createComponent();
    String component2 = diagram.createComponent();
    String component2_1 = diagram.createChildComponent(component2);
    String component3 = diagram.createComponent();

    String actor5 = diagram.createActorInContainer(component2);
    String actor6 = diagram.createActor();

    String capability1 = diagram.createCapability();
    String capability2 = diagram.createCapability();
    String capability3 = diagram.createCapability();

    String cl1 = diagram.createComponentExchange(component2, actor6);
    String cl2 = diagram.createComponentExchange(actor5, component2_1);
    String cl3 = diagram.createComponentExchange(component2, component3);
    String cl4 = diagram.createCapabilityInvolvement(capability1, component3);
    String cl5 = diagram.createCapabilityInvolvement(capability1, component2_1);
    String cl6 = diagram.createCapabilityInvolvement(capability2, actor5);
    String cl7 = diagram.createCapabilityInvolvement(capability2, actor6);

    String cl8 = diagram.createCapabilityExtends(capability1, capability2);
    String cl9 = diagram.createCapabilityIncludes(capability1, capability3);
    String cl10 = diagram.createCapabilityGeneralization(capability2, capability3);

    DiagramHelper.setSynchronized(diagram.getDiagram(), false);
    diagram.removeRelationship(capability1, cl4);
    diagram.insertRelationship(component3, cl4);
    diagram.removeRelationship(capability1, cl5);
    diagram.insertRelationship(component2_1, cl5);
    diagram.removeRelationship(capability2, cl6);
    diagram.insertRelationship(actor5, cl6);
    diagram.removeRelationship(capability2, cl7);
    diagram.insertRelationship(actor6, cl7);
    diagram.removeRelationship(capability1, cl8);
    diagram.insertRelationship(capability1, cl8);
    diagram.removeRelationship(capability1, cl9);
    diagram.insertRelationship(capability1, cl9);
    diagram.removeRelationship(capability2, cl10);
    diagram.insertRelationship(capability2, cl10);
    DiagramHelper.setSynchronized(diagram.getDiagram(), true);

    diagram.removeComponent(component2_1, component2);
    diagram.insertComponent(component2_1, component2);

    diagram.removeActor(actor5, component2);
    diagram.insertActor(actor5, component2);

    diagram.removeCapability(capability1);
    diagram.insertCapability(capability1);

    diagram.createConstraint(GenericModel.CONSTRAINT_1, diagram.getDiagramId());
    diagram.createConstrainedElement(GenericModel.CONSTRAINT_1, component2);
    diagram.createConstraint(GenericModel.CONSTRAINT_2, diagram.getDiagramId());
    diagram.createConstrainedElement(GenericModel.CONSTRAINT_2, cl1);

    diagram.removeConstraint(GenericModel.CONSTRAINT_1, component2);
    diagram.insertConstraint(GenericModel.CONSTRAINT_1, component2);

    diagram.removeConstraint(GenericModel.CONSTRAINT_2, cl1);
    diagram.insertConstraint(GenericModel.CONSTRAINT_2, cl1);

    // drag and drop tests
    CapabilityDiagram initDiagram = CapabilityDiagram.createDiagram(context, OA__OPERATIONAL_CAPABILITIES,
        IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME);
    String constraint = initDiagram.createConstraint(GenericModel.CONSTRAINT_3);
    diagram.dragAndDropConstraintFromExplorer(constraint, diagramId);

    String component = initDiagram.createComponent();
    diagram.dragAndDropComponentFromExplorer(component, diagramId);

    String capability = initDiagram.createCapability();
    diagram.dragAndDropCapabilityFromExplorer(capability, diagramId);
  }
}