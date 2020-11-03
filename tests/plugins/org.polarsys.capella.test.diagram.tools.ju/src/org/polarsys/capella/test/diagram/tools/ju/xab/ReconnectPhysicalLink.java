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

public class ReconnectPhysicalLink extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnXAB(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA,
        SA__SAB_PHYSICAL_LINK_PL1, SA__SAB_PHYSICAL_PORT_A1_PP1,
        SA__SAB_PHYSICAL_PORT_A2_PP1, SA__SAB_PHYSICAL_PORT_A2_PP1,
        SA__SAB_PHYSICAL_PORT_A3_PP1);
    testOnXAB(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA,
        LA__LAB_PHYSICAL_LINK_PL1, LA__LAB_PHYSICAL_PORT_A1_PP1,
        LA__LAB_PHYSICAL_PORT_A2_PP1, LA__LAB_PHYSICAL_PORT_A2_PP1,
        LA__LAB_PHYSICAL_PORT_A3_PP1);
    testOnXAB(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA,
        PA__PAB_PHYSICAL_LINK_PL1, PA__PAB_PHYSICAL_PORT_PC1_PP1,
        PA__PAB_PHYSICAL_PORT_PC2_PP1, PA__PAB_PHYSICAL_PORT_PC2_PP1,
        PA__PAB_PHYSICAL_PORT_PC6_PP1, PA__PAB_COMPONENT_PORT_1);
  }

  public void testOnXAB(SessionContext context, String diagramName, BlockArchitectureExt.Type type,
      String ceId, String oldSourceId, String newSourceId, String oldTargetId, String newTargetId, String ...invalidEnds) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);

    diagram.reconnectPhysicalLink(ceId, oldTargetId, newTargetId, BlockArchitectureExt.LinkDirection.TARGET);
    diagram.reconnectPhysicalLink(ceId, oldSourceId, newSourceId, BlockArchitectureExt.LinkDirection.SOURCE);
    
    if (invalidEnds != null) {
      for (String id : invalidEnds) {
        diagram.cannotReconnectPhysicalLink(ceId, newTargetId, id, BlockArchitectureExt.LinkDirection.TARGET);
        diagram.cannotReconnectPhysicalLink(ceId, newSourceId, id, BlockArchitectureExt.LinkDirection.SOURCE);
      }
    }
  }

  
}
