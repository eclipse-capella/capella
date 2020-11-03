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
package org.polarsys.capella.test.model.ju.components;

import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.test.diagram.common.ju.context.PABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.SkeletonHelper;

public class PACompositionTestCase extends CompositionTestCase {

  @Override
  protected void testOn(SessionContext context) {
    checkCompositionPA(context, PA__PHYSICAL_CONTEXT, PA__PHYSICAL_SYSTEM);
    checkCompositionPA(context, PA__PHYSICAL_SYSTEM, PA__PHYSICAL_SYSTEM);

    PABDiagram diagram = PABDiagram.createDiagram(context, PA__PHYSICAL_CONTEXT);

    // create a PAB inside an actor and test composition there
    String actorId = diagram.createActor(getComponentName());
    Component actor = (Component) ((Part) (context.getSemanticElement(actorId))).getAbstractType();
    checkCompositionPA(context, actor.getId(), actor.getId());

    // create a PAB inside a node PC and test composition there
    String compId = diagram.createNodeComponent(getComponentName(), diagram.getDiagramId());
    Component comp = (Component) ((Part) (context.getSemanticElement(compId))).getAbstractType();
    checkCompositionPA(context, comp.getId(), comp.getId());

    // create a PAB inside a component PKG and test composition there
    ComponentPkg pkg = SkeletonHelper.createComponentPkg(PA__PHYSICAL_SYSTEM, "PA Component PKG1", context);
    checkCompositionPA(context, pkg.getId(), pkg.getId());
  }

  protected void checkCompositionPA(SessionContext context, String diagramContainerId, String targetContainerId) {
    String structureId = PA__PHYSICAL_CONTEXT;
    String systemId = PA__PHYSICAL_SYSTEM;

    Component componentSystem = context.getSemanticElement(systemId);
    CapellaElement componentContainer = context.getSemanticElement(targetContainerId);
    PABDiagram diagram = PABDiagram.createDiagram(context, diagramContainerId);

    // create actor in diagram
    String actor1Id = diagram.createActor(getComponentName());
    PhysicalComponent actor1 = (PhysicalComponent) ((Part) (context.getSemanticElement(actor1Id))).getAbstractType();
    CapellaElement structure = context.getSemanticElement(structureId);
    assertEquals(NLS.bind(compositionErrorMessage, actor1.getId(), structure.getId()), actor1.eContainer(), structure);

    // check the nature of the actor
    assertEquals(NLS.bind(componentNatureMessage, actor1.getId(), "NODE"), PhysicalComponentNature.NODE,
        actor1.getNature());

    // create actor in actor
    String actor1_1Id = diagram.createActor(getComponentName(), actor1Id);
    PhysicalComponent actor1_1 = (PhysicalComponent) ((Part) (context.getSemanticElement(actor1_1Id)))
        .getAbstractType();
    assertEquals(NLS.bind(compositionErrorMessage, actor1_1.getId(), actor1.getId()), actor1_1.eContainer(), actor1);

    // check the nature of the actor inherits actor1
    assertEquals(NLS.bind(componentNatureMessage, actor1_1.getId(), "NODE"), PhysicalComponentNature.NODE,
        actor1_1.getNature());

    // cannot create actor on a leaf (human)
    setHuman(context, actor1_1, true);
    diagram.cannotCreateActor(getComponentName(), actor1_1Id);

    // cannot create component on a leaf (human actor)
    setHuman(context, actor1_1, true);
    diagram.cannotCreateBehaviorComponent(getComponentName(), actor1_1Id);
    diagram.cannotCreateNodeComponent(getComponentName(), actor1_1Id);

    // can deploy components on a human actor
    diagram.createDeployedBehaviorComponent(getComponentName(), actor1_1Id);
    diagram.createDeployedNodeComponent(getComponentName(), actor1_1Id);

    // create node PC in actor
    String nodePC1Id = diagram.createNodeComponent(getComponentName(), actor1Id);
    PhysicalComponent nodePC1 = (PhysicalComponent) ((Part) (context.getSemanticElement(nodePC1Id))).getAbstractType();
    assertEquals(NLS.bind(compositionErrorMessage, nodePC1.getId(), actor1.getId()), nodePC1.eContainer(), actor1);
    assertEquals(NLS.bind(componentNatureMessage, nodePC1.getId(), "NODE"), PhysicalComponentNature.NODE,
        nodePC1.getNature());

    // create node PC in node PC
    String nodePC1_1Id = diagram.createNodeComponent(getComponentName(), nodePC1Id);
    PhysicalComponent nodePC1_1 = (PhysicalComponent) ((Part) (context.getSemanticElement(nodePC1_1Id)))
        .getAbstractType();
    assertEquals(NLS.bind(compositionErrorMessage, nodePC1_1.getId(), nodePC1.getId()), nodePC1_1.eContainer(),
        nodePC1);

    // deploy behavior component in actor
    String bComp1Id = diagram.createDeployedBehaviorComponent(getComponentName(), actor1Id);
    PhysicalComponent bComp1 = (PhysicalComponent) ((Part) (context.getSemanticElement(bComp1Id))).getAbstractType();
    assertEquals(NLS.bind(compositionErrorMessage, bComp1.getId(), componentSystem.getId()), bComp1.eContainer(),
        componentSystem);
    assertEquals(NLS.bind(componentNatureMessage, nodePC1.getId(), "BEHAVIOR"), PhysicalComponentNature.BEHAVIOR,
        bComp1.getNature());

    // deploy behavior component in node PC
    String bComp2Id = diagram.createDeployedBehaviorComponent(getComponentName(), nodePC1Id);
    PhysicalComponent bComp2 = (PhysicalComponent) ((Part) (context.getSemanticElement(bComp2Id))).getAbstractType();
    assertEquals(NLS.bind(compositionErrorMessage, bComp2.getId(), componentSystem.getId()), bComp2.eContainer(),
        componentSystem);
    assertEquals(NLS.bind(componentNatureMessage, bComp2.getId(), "BEHAVIOR"), PhysicalComponentNature.BEHAVIOR,
        bComp2.getNature());

    // create actor in deployed behavior component
    String actorbComp2Id = diagram.createActor(getComponentName(), bComp2Id);
    PhysicalComponent actorbComp2 = (PhysicalComponent) ((Part) (context.getSemanticElement(actorbComp2Id)))
        .getAbstractType();
    assertEquals(NLS.bind(compositionErrorMessage, actorbComp2.getId(), bComp2.getId()), actorbComp2.eContainer(),
        bComp2);

    // check that the nature of the actor is same as the component on which is deployed
    assertEquals(NLS.bind(componentNatureMessage, actorbComp2.getId(), bComp2.getNature().toString()),
        actorbComp2.getNature(), bComp2.getNature());

    // create node PC in diagram
    String nodePC2Id = diagram.createNodeComponent(getComponentName(), diagram.getDiagramId());
    PhysicalComponent nodePC2 = (PhysicalComponent) ((Part) (context.getSemanticElement(nodePC2Id))).getAbstractType();
    assertEquals(NLS.bind(compositionErrorMessage, nodePC2.getId(), componentContainer.getId()), nodePC2.eContainer(),
        componentContainer);
    assertEquals(NLS.bind(componentNatureMessage, nodePC2.getId(), "NODE"), PhysicalComponentNature.NODE,
        nodePC2.getNature());

    // create node PC in node PC
    String nodePC2_1Id = diagram.createNodeComponent(getComponentName(), nodePC2Id);
    PhysicalComponent nodePC2_1 = (PhysicalComponent) ((Part) (context.getSemanticElement(nodePC2_1Id)))
        .getAbstractType();
    assertEquals(NLS.bind(compositionErrorMessage, nodePC2_1.getId(), nodePC2.getId()), nodePC2_1.eContainer(),
        nodePC2);

    // create actor in node PC
    String actor2_1Id = diagram.createActor(getComponentName(), nodePC2Id);
    PhysicalComponent actor2_1 = (PhysicalComponent) ((Part) (context.getSemanticElement(actor2_1Id)))
        .getAbstractType();
    assertEquals(NLS.bind(compositionErrorMessage, actor2_1.getId(), nodePC2.getId()), actor2_1.eContainer(), nodePC2);

    // check that the nature of the actor is same as its container
    assertEquals(actor2_1.getNature(), nodePC2.getNature());

    // deploy node PC in node PC
    String nodePC2_2Id = diagram.createDeployedNodeComponent(getComponentName(), nodePC2Id);
    Component nodePC2_2 = (Component) ((Part) (context.getSemanticElement(nodePC2_2Id))).getAbstractType();
    assertEquals(NLS.bind(compositionErrorMessage, nodePC2_2.getId(), componentSystem.getId()), nodePC2_2.eContainer(),
        componentSystem);

    // deploy node PC in Actor
    String nodePC2_1_1Id = diagram.createDeployedNodeComponent(getComponentName(), actor2_1Id);
    PhysicalComponent nodePC2_1_1 = (PhysicalComponent) ((Part) (context.getSemanticElement(nodePC2_1_1Id)))
        .getAbstractType();
    assertEquals(NLS.bind(compositionErrorMessage, nodePC2_1_1.getId(), componentSystem.getId()),
        nodePC2_1_1.eContainer(), componentSystem);
    assertEquals(PhysicalComponentNature.NODE, nodePC2_1_1.getNature());

    // create actor in deployed node PC
    String actornodePC2_1_1Id = diagram.createActor(getComponentName(), nodePC2_1_1Id);
    PhysicalComponent actornodePC2_1_1 = (PhysicalComponent) ((Part) (context.getSemanticElement(actornodePC2_1_1Id)))
        .getAbstractType();
    assertEquals(NLS.bind(compositionErrorMessage, actornodePC2_1_1.getId(), nodePC2_1_1.getId()),
        actornodePC2_1_1.eContainer(), nodePC2_1_1);

    // check that the nature of the actor is same as its container
    assertEquals(NLS.bind(componentNatureMessage, actornodePC2_1_1.getId(), nodePC2_1_1.getNature().toString()),
        actornodePC2_1_1.getNature(), nodePC2_1_1.getNature());

    // create node PC in deployed node PC
    String nodePC2_1_1_1Id = diagram.createNodeComponent(getComponentName(), nodePC2_1_1Id);
    Component nodePC2_1_1_1 = (Component) ((Part) (context.getSemanticElement(nodePC2_1_1_1Id))).getAbstractType();
    assertEquals(NLS.bind(compositionErrorMessage, nodePC2_1_1_1.getId(), nodePC2_1_1.getId()),
        nodePC2_1_1_1.eContainer(), nodePC2_1_1);

    // cannot create behavior PC in actor
    diagram.cannotCreateBehaviorComponent(getComponentName(), actornodePC2_1_1Id);

    // cannot create behavior PC in node PC
    diagram.cannotCreateBehaviorComponent(getComponentName(), nodePC2_2Id);

    // cannot deploy node PC in diagram
    diagram.cannotCreateDeployedNodeComponent(getComponentName(), diagram.getDiagramId());

    // cannot deploy behavior PC in diagram
    diagram.cannotCreateDeployedBehaviorComponent(getComponentName(), diagram.getDiagramId());

    if (targetContainerId.equals(systemId)) {
      // create behavior PC in diagram
      String bComp3Id = diagram.createBehaviorComponent(getComponentName(), diagram.getDiagramId());
      Component bComp3 = (Component) ((Part) (context.getSemanticElement(bComp3Id))).getAbstractType();
      assertEquals(NLS.bind(compositionErrorMessage, bComp3.getId(), componentSystem.getId()), bComp3.eContainer(),
          componentSystem);
    } else if (componentContainer instanceof Component) {
      diagram.cannotCreateBehaviorComponent(getComponentName(), diagram.getDiagramId());
    }

    // create behavior PC in deployed behavior PC
    String bComp4Id = diagram.createBehaviorComponent(getComponentName(), bComp2Id);
    Component bComp4 = (Component) ((Part) (context.getSemanticElement(bComp4Id))).getAbstractType();
    assertEquals(NLS.bind(compositionErrorMessage, bComp4.getId(), bComp2.getId()), bComp4.eContainer(), bComp2);

    // cannot deploy behavior PC in diagram
    diagram.cannotCreateDeployedBehaviorComponent(getComponentName(), diagram.getDiagramId());

    // cannot deploy node PC in diagram
    diagram.cannotCreateDeployedNodeComponent(getComponentName(), diagram.getDiagramId());

    // cannot deploy node PC in behavior PC
    diagram.cannotCreateDeployedNodeComponent(getComponentName(), bComp4Id);

    // cannot deploy node PC in deployed behavior PC
    diagram.cannotCreateDeployedNodeComponent(getComponentName(), bComp2Id);
  }
}
