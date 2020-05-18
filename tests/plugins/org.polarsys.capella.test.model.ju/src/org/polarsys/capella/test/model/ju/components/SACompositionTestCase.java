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

public class SACompositionTestCase extends CompositionTestCase {

  @Override
  protected void testOn(SessionContext context) {
    checkCompositionSA(context, SA__SYSTEM_COMPONENT_PKG);
    checkCompositionSA(context, SA__SYSTEM);

    // create a SAB inside an actor and test composition there
    XABDiagram diagram = XABDiagram.createDiagram(context, SA__SYSTEM_COMPONENT_PKG);
    String actorId = diagram.createActor(getComponentName());
    Component actor = (Component) ((Part) (context.getSemanticElement(actorId))).getAbstractType();
    checkCompositionSA(context, actor.getId());

    // create a SAB inside a component PKG and test composition there
    ComponentPkg pkg = SkeletonHelper.createComponentPkg(SA__SYSTEM_COMPONENT_PKG, "SA Component PKG1", context);
    checkCompositionSA(context, pkg.getId());
  }

  protected void checkCompositionSA(SessionContext context, String containerId) {
    String structureId = SA__SYSTEM_COMPONENT_PKG;
    XABDiagram diagram = XABDiagram.createDiagram(context, containerId);

    // create actor in diagram
    String actor1Id = diagram.createActor(getComponentName(), diagram.getDiagramId());
    Component actor1 = (Component) ((Part) (context.getSemanticElement(actor1Id))).getAbstractType();
    CapellaElement structure = context.getSemanticElement(structureId);
    assertEquals(NLS.bind(compositionErrorMessage, actor1.getId(), structure.getId()), actor1.eContainer(), structure);

    // create actors in actor
    String actor1_1Id = diagram.createActor(getComponentName(), actor1Id);
    Component actor1_1 = (Component) ((Part) (context.getSemanticElement(actor1_1Id))).getAbstractType();
    assertEquals(NLS.bind(compositionErrorMessage, actor1_1.getId(), actor1.getId()), actor1_1.eContainer(), actor1);

    diagram.createActor(getComponentName(), actor1_1Id);
    String actor1_2Id = diagram.createActor(getComponentName(), actor1Id);
    Component actor1_2 = (Component) ((Part) (context.getSemanticElement(actor1_2Id))).getAbstractType();
    assertEquals(NLS.bind(compositionErrorMessage, actor1_2.getId(), actor1.getId()), actor1_2.eContainer(), actor1);

    // cannot create sub-actors in Leaf actors (Human)
    setHuman(context, actor1_2, true);
    diagram.cannotCreateActor(getComponentName(), actor1_2Id);

    // cannot create actors inside System
    diagram.failedPreconditionCreateActor(getComponentName(), SA__SYSTEM_CONTEXT__PART_SYSTEM__SYSTEM);
  }

}
