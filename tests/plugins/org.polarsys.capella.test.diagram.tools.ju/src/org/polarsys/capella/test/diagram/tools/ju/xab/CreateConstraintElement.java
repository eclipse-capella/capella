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

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class CreateConstraintElement extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    
    String [] epbsTargets = {EPBS__EAB_CONFIGURATION_ITEM1, EPBS__EAB_CONFIGURATION_ITEM1_1, EPBS__EAB_CONFIGURATION_ITEM2};
    String [] oaTargets = {OA__OAB_FUNCTION_1, OA__OAB_OA1, OA__OAB_OA3};
    String [] saTargets = {SA__SAB_A1, SA__SAB_FUNCTION_1, SA__SAB_FUNCTION_PORT_1, SA__SAB_COMPONENT_PORT_A3_CP1};
    String [] laTargets = {LA__LAB_A1, LA__LAB_COMPONENT_PORT_1, LA__LAB_FUNCTION_1};
    String [] paTargets = {PA__PAB_COMPONENT_PC3, PA__PAB_FUNCTION_1, PA__PAB_COMPONENT_PORT_PC4_CP1,
        PA__PAB_PHYSICAL_PORT_PC1_PP1};
    
    testOnXAB(context, EPBS__EAB_DIAGRAM, BlockArchitectureExt.Type.EPBS, epbsTargets);
    testOnXAB(context, OA__OAB_DIAGRAM, BlockArchitectureExt.Type.OA, oaTargets);
    testOnXAB(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA, saTargets);
    testOnXAB(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA, laTargets);
    testOnXAB(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA, paTargets);
  }

  public void testOnXAB(SessionContext context, String diagramName, BlockArchitectureExt.Type type, String []targets) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);
    
    diagram.createConstraint(GenericModel.CONSTRAINT_1);
    
    for(int i = 0; i < targets.length; i++) {
       diagram.createConstrainedElement(GenericModel.CONSTRAINT_1, targets[i]);
    }
  }
  
  
}
