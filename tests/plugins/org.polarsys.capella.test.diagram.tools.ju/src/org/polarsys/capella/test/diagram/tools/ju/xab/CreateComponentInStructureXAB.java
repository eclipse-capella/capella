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
package org.polarsys.capella.test.diagram.tools.ju.xab;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class CreateComponentInStructureXAB extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    XABDiagram diagram = XABDiagram.openDiagram(context, LA__LAB_STRUCTURE_DIAGRAM, BlockArchitectureExt.Type.LA);
    String createdComponentId = diagram.createComponent(GenericModel.COMPONENT_1, LA__LAB_STRUCTURE_DIAGRAM);
    EObject createdComponent = context.getSemanticElement(createdComponentId);
    assertTrue("Newly created component in Structure LAB diagrams must be contained in the Logical System",
        BlockArchitectureExt.getRootBlockArchitecture(createdComponent).getSystem() == createdComponent.eContainer());
  }
}
