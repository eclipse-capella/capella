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

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.ComponentPortType;

import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class CreateComponentPort extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnXAB(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA,
        SA__SAB_A1);
    testOnXAB(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA,
        LA__LAB_A1);
    testOnXAB(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA,
        PA__PAB_COMPONENT_PC3, PA__PAB_COMPONENT_PC3_1,
        PA__PAB_PHYSICAL_ACTOR1);
  }

  public void testOnXAB(SessionContext context, String diagramName, BlockArchitectureExt.Type type,
      String... containerIds) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);

    for (String containerId : containerIds) {
      diagram.createComponentPort(GenericModel.COMPONENT_PORT_1, ComponentPortType.IN_FLOW_PORT, containerId);
      diagram.createComponentPort(GenericModel.COMPONENT_PORT_2, ComponentPortType.OUT_FLOW_PORT, containerId);
      diagram.createComponentPort(GenericModel.COMPONENT_PORT_3, ComponentPortType.IN_OUT_FLOW_PORT, containerId);
      diagram.createComponentPort(GenericModel.COMPONENT_PORT_4, ComponentPortType.STANDARD_PORT, containerId);
    }
  }

  
}
