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
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.test.diagram.common.ju.context.OABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.SkeletonHelper;

public class OACompositionTestCase extends CompositionTestCase {

  @Override
  protected void testOn(SessionContext context) {
    checkCompositionOA(context, OA_OPERATIONAL_ENTITIES);

    // test by creating OAB inside a component
    OABDiagram diagram = OABDiagram.createDiagram(context, OA_OPERATIONAL_ENTITIES);
    String comp1Id = diagram.createComponent(getComponentName(), diagram.getDiagramId());
    Component comp1 = context.getSemanticElement(comp1Id);
    checkCompositionOA(context, comp1.getId());

    // test by creating OAB inside a component pkg
    String componentPkgId = "OA Component PKG 1";
    EntityPkg obj = SkeletonHelper.createComponentPkg(OA_OPERATIONAL_ENTITIES, componentPkgId, context);
    checkCompositionOA(context, obj.getId());
  }

  protected void checkCompositionOA(SessionContext context, String diagramContainerId) {
    String structureId = OA_OPERATIONAL_ENTITIES;
    OABDiagram diagram = OABDiagram.createDiagram(context, diagramContainerId);
    CapellaElement diagramContainer = context.getSemanticElement(diagramContainerId);

    // create actor in diagram
    String actor1Id = diagram.createActor(getComponentName());
    Component actor1 = context.getSemanticElement(actor1Id);
    assertTrue(NLS.bind(componentTypeMessage, actor1.getId(), "HUMAN"), actor1.isHuman());
    CapellaElement structure = context.getSemanticElement(structureId);
    assertEquals(NLS.bind(compositionErrorMessage, actor1.getId(), structure.getId()), actor1.eContainer(), structure);

    // cannot create another actor/entity inside an Operational Actor (human)
    diagram.cannotCreateActor(getComponentName(), actor1Id);
    diagram.cannotCreateComponent(getComponentName(), actor1Id);

    // create Entity in diagram
    String comp1Id = diagram.createComponent(getComponentName(), diagram.getDiagramId());
    Component comp1 = context.getSemanticElement(comp1Id);
    assertEquals(NLS.bind(compositionErrorMessage, comp1.getId(), diagramContainer.getId()), comp1.eContainer(),
        diagramContainer);

    // create Entity in Entity
    String comp1_1Id = diagram.createComponent(getComponentName(), comp1Id);
    Component comp1_1 = context.getSemanticElement(comp1_1Id);
    assertEquals(NLS.bind(compositionErrorMessage, comp1_1.getId(), comp1.getId()), comp1_1.eContainer(), comp1);

    // create actor in Entity
    String actor1_1_1Id = diagram.createActor(getComponentName(), comp1_1Id);
    Component actor1_1_1 = context.getSemanticElement(actor1_1_1Id);
    assertEquals(NLS.bind(compositionErrorMessage, actor1_1_1.getId(), comp1_1.getId()), actor1_1_1.eContainer(),
        comp1_1);

    // cannot create actor in Operational Actor (is human)
    diagram.cannotCreateActor(getComponentName(), actor1_1_1Id);
  }
}
