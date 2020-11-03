/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.xab;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.data.activity.OutputPin;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.test.diagram.common.ju.context.OABDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.PABDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class DragAndDropTest extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnXAB(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA, SA__SAB_SYSTEM);
    testOnXAB(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA, LA__LAB_LOGICAL_SYSTEM);
    testOnXAB(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA, PA__PAB_PHYSICAL_SYSTEM);
    testOnOAB(context, OA__OAB_DIAGRAM, BlockArchitectureExt.Type.OA, OA__OAB_OPERATIONAL_CONTEXT);
  }

  public void testOnXAB(SessionContext context, String diagramName, BlockArchitectureExt.Type type, String targetId) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);
    String actor1 = diagram.createActor(GenericModel.ACTOR_1);
    String function1 = diagram.createFunction(GenericModel.FUNCTION_1, actor1);

    String actor2 = diagram.createActor(GenericModel.ACTOR_2);
    diagram.dragAndDropAbstractFunctionallocation(function1, actor2);

    if (type == BlockArchitectureExt.Type.LA) {
      String component1 = diagram.createComponent(GenericModel.COMPONENT_1, LA__LAB_LOGICAL_SYSTEM_PART);
      String component2 = diagram.createComponent(GenericModel.COMPONENT_2, LA__LAB_LOGICAL_SYSTEM_PART);
      diagram.dragAndDropComponent(component2, component1);

      String targetviewId = LA__LAB_LOGICAL_SYSTEM_PART;
      diagram.dragAndDropComponent(actor1, targetviewId);
      diagram.dragAndDropComponent(actor1, diagram.getDiagramId());
    }
    // PA TOOL_PAB_DND_DEPLOYMENT

    if (type != BlockArchitectureExt.Type.OA) {
      String actor3 = diagram.createActor(GenericModel.ACTOR_3);
      diagram.createPhysicalLink(actor1, actor3, GenericModel.LA_1);
      Part partActor1Obj = diagram.getSessionContext().getSemanticElement(actor1);
      Component actor1Obj = (Component) partActor1Obj.getAbstractType();
      PhysicalPort physicalPort = (PhysicalPort) actor1Obj.getOwnedFeatures().iterator().next();
      diagram.dragAndDropPhysicalPort(physicalPort.getId(), actor2);

      diagram.createComponentExchange(actor1, actor3, GenericModel.LA_2);
      ComponentPort compPort = (ComponentPort) actor1Obj.getOwnedFeatures().iterator().next();
      diagram.dragAndDropComponentPort(compPort.getId(), actor2);

      String function1Id = diagram.createFunction(GenericModel.FUNCTION_1, actor3);
      String function2Id = diagram.createFunction(GenericModel.FUNCTION_2, actor3);
      String function3Id = diagram.createFunction(GenericModel.FUNCTION_3, actor3);
      diagram.createFunctionalExchange(function1Id, function3Id, GenericModel.FUNCTIONAL_EXCHANGE_1);

      AbstractFunction f1 = diagram.getSessionContext().getSemanticElement(function1Id);
      EList<OutputPin> ownedPins = f1.getOutputs();
      FunctionOutputPort outPort = (FunctionOutputPort) ownedPins.iterator().next();
      diagram.dragAndDropFunctionPort(outPort.getId(), function2Id);
    }

    if (type == BlockArchitectureExt.Type.PA) {
      PABDiagram pabDiagram = PABDiagram.createDiagram(context, targetId);

      pabDiagram.createBehaviorComponent(GenericModel.BEHAVIOR_PC_1_1, pabDiagram.getDiagramId());
      pabDiagram.createDeployedBehaviorComponent(GenericModel.DEPLOY_BEHAVIOR_PC_1, GenericModel.BEHAVIOR_PC_1_1);
      pabDiagram.createBehaviorComponent(GenericModel.BEHAVIOR_PC_1_2, pabDiagram.getDiagramId());
      pabDiagram.createDeployedBehaviorComponent(GenericModel.DEPLOY_BEHAVIOR_PC_1_2_1, GenericModel.BEHAVIOR_PC_1_2);

      pabDiagram.dragAndDropDeployment(GenericModel.DEPLOY_BEHAVIOR_PC_1_2_1, GenericModel.BEHAVIOR_PC_1_1);

      String constraint1 = pabDiagram.createConstraint(GenericModel.CONSTRAINT_1);
      diagram.dragAndDropConstraintsFromExplorer(constraint1, diagram.getDiagramId());

      String targetviewId = pabDiagram.dragAndDropComponentsFromExplorer(targetId, pabDiagram.getDiagramId());
      String pabactor1 = pabDiagram.createActor("actor1");
      pabDiagram.dragAndDropComponent(pabactor1, targetviewId);
      pabDiagram.dragAndDropComponent(pabactor1, pabDiagram.getDiagramId());
      pabDiagram.dragAndDropComponent(pabactor1, targetviewId);

    } else {
      XABDiagram diagramSetup = XABDiagram.createDiagram(context, targetId);
      String constraint1 = diagramSetup.createConstraint(GenericModel.CONSTRAINT_1);
      diagram.dragAndDropConstraintsFromExplorer(constraint1, diagram.getDiagramId());
    }

    String actor4 = diagram.createActor(GenericModel.ACTOR_4);
    String actor5 = diagram.createActor(GenericModel.ACTOR_5);
    String actor6 = diagram.createActor(GenericModel.ACTOR_5, actor4);
    diagram.dragAndDropComponent(actor5, actor4);
    diagram.dragAndDropComponent(actor5, diagram.getDiagramId());
    diagram.dragAndDropComponent(actor5, actor6);
    diagram.dragAndDropComponent(actor5, actor4);
    diagram.dragAndDropComponent(actor5, actor6);
  }

  public void testOnOAB(SessionContext context, String diagramName, BlockArchitectureExt.Type type, String targetId) {
    OABDiagram diagram = OABDiagram.openDiagram(context, diagramName);
    OABDiagram diagramSetup = OABDiagram.createDiagram(context, targetId);
    String containerId = diagram.getDiagramId();

    String actor1 = diagram.createActor(GenericModel.ACTOR_1);
    String function1 = diagram.createFunction(GenericModel.FUNCTION_1, actor1);
    String actor2 = diagram.createActor(GenericModel.ACTOR_2);
    diagram.dragAndDropAbstractFunctionallocation(function1, actor2);

    String entity1 = diagram.createComponent(GenericModel.ENTITY_1, containerId);
    String entity2 = diagram.createComponent(GenericModel.ENTITY_2, containerId);
    diagram.dragAndDropComponent(entity2, entity1);
    diagram.dragAndDropComponent(actor1, entity1);
    diagram.dragAndDropComponent(actor1, entity2);
    diagram.dragAndDropComponent(actor1, diagram.getDiagramId());
    diagram.dragAndDropComponent(actor1, entity2);

    String entity3 = diagramSetup.createComponent(GenericModel.ENTITY_3, diagramSetup.getDiagramId());
    String role1 = diagramSetup.createRole(GenericModel.ROLE_1_1_3, entity3);
    String operationalActivity1 = diagramSetup.createFunction(GenericModel.FUNCTION_2, entity3);
    String operationalActivity2 = diagramSetup.createFunction(GenericModel.FUNCTION_2, entity3);

    diagram.dragAndDropOperationalActivitiesFromExplorer(operationalActivity1, entity1);
    diagram.dragAndDropRolesFromExplorer(role1, entity1);
    diagram.dragAndDropOperationalActivitiesFromExplorerToRole(operationalActivity2, role1);

    String constraint1 = diagramSetup.createConstraint(GenericModel.CONSTRAINT_1);
    diagram.dragAndDropConstraintsFromExplorer(constraint1, diagram.getDiagramId());
  }

}
