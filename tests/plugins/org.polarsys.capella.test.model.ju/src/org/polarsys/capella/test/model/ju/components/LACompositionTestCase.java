/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.SkeletonHelper;

public class LACompositionTestCase extends CompositionTestCase {

  @Override
  protected void testOn(SessionContext context) {
    checkCompositionLA(context, LA__LOGICAL_CONTEXT, LA__LOGICAL_SYSTEM);
    checkCompositionLA(context, LA__LOGICAL_SYSTEM, LA__LOGICAL_SYSTEM);

    // create one component and one actor then test creating LAB in component/actor
    XABDiagram diagram = XABDiagram.createDiagram(context, LA__LOGICAL_SYSTEM);
    String compId = diagram.createComponent(getComponentName(), diagram.getDiagramId());
    Component comp = (Component) ((Part) (context.getSemanticElement(compId))).getAbstractType();
    String actorId = diagram.createActor(getComponentName(), diagram.getDiagramId());
    Component actor = (Component) ((Part) (context.getSemanticElement(actorId))).getAbstractType();

    checkCompositionLA(context, comp.getId(), comp.getId());
    checkCompositionLA(context, actor.getId(), actor.getId());

    // create a component package and test creating LAB in the component package
    String componentPkgId = "LA Component PKG 1";
    ComponentPkg pkg = SkeletonHelper.createComponentPkg(LA__LOGICAL_CONTEXT, componentPkgId, context);
    checkCompositionLA(context, pkg.getId(), LA__LOGICAL_SYSTEM);
  }

  protected void checkCompositionLA(SessionContext context, String diagramContainerId, String targetContinerId) {
    String structureId = LA__LOGICAL_CONTEXT;
    XABDiagram diagram = XABDiagram.createDiagram(context, diagramContainerId);

    // create actors inside actors and components and components inside actors and components
    String actor1Id = diagram.createActor(getComponentName());
    // check that the actor is created inside the Structure Pkg
    Component actor1 = (Component) ((Part) (context.getSemanticElement(actor1Id))).getAbstractType();
    CapellaElement structure = context.getSemanticElement(structureId);
    assertEquals(NLS.bind(compositionErrorMessage, actor1.getId(), structure.getId()), actor1.eContainer(), structure);

    // create actor in actor
    String actor1_1Id = diagram.createActor(getComponentName(), actor1Id);
    Component actor1_1 = (Component) ((Part) (context.getSemanticElement(actor1_1Id))).getAbstractType();
    assertEquals(NLS.bind(compositionErrorMessage, actor1_1.getId(), actor1.getId()), actor1_1.eContainer(), actor1);

    // create component in actor
    String comp1_2Id = diagram.createComponent(getComponentName(), actor1Id);
    Component comp1_2 = (Component) ((Part) (context.getSemanticElement(comp1_2Id))).getAbstractType();
    assertEquals(NLS.bind(compositionErrorMessage, comp1_2.getId(), actor1.getId()), comp1_2.eContainer(), actor1);

    // create actor in component
    String actor1_2_1Id = diagram.createActor(getComponentName(), comp1_2Id);
    Component actor1_2_1 = (Component) ((Part) (context.getSemanticElement(actor1_2_1Id))).getAbstractType();
    assertEquals(NLS.bind(compositionErrorMessage, actor1_2_1.getId(), comp1_2.getId()), actor1_2_1.eContainer(),
        comp1_2);

    // create component in diagram
    String comp1Id = diagram.createComponent(getComponentName(), diagram.getDiagramId());
    // check that the component is created inside the System
    Component comp1 = (Component) ((Part) (context.getSemanticElement(comp1Id))).getAbstractType();
    CapellaElement targetContainer = context.getSemanticElement(targetContinerId);
    assertEquals(NLS.bind(compositionErrorMessage, comp1.getId(), targetContainer.getId()), comp1.eContainer(),
        targetContainer);

    // cannot create components/actors inside a human component (leaf)
    String actor1_1_1Id = diagram.createActor(getComponentName(), comp1Id);
    Component actor1_1_1 = (Component) ((Part) (context.getSemanticElement(actor1_1_1Id))).getAbstractType();
    setHuman(context, actor1_1_1, true);
    diagram.cannotCreateActor(getComponentName(), actor1_1_1Id);
    diagram.cannotCreateComponent(getComponentName(), actor1_1_1Id);
    setHuman(context, actor1_1_1, false);
    diagram.createActor(getComponentName(), actor1_1_1Id);
    diagram.createComponent(getComponentName(), actor1_1_1Id);
  }
}
