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

import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ReconnectComponentExchange extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnXAB(context, OA__OAB_DIAGRAM, BlockArchitectureExt.Type.OA,
        OA__OAB_COMMUNICATION_MEAN1, OA__OAB_OA1,
        OA__OAB_ENTITY1, OA__OAB_OA2, OA__OAB_OA3,
        OA__OAB_FUNCTION_1, OA__OAB_ROLE1);
    testOnXAB(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA,
        SA__SAB_COMPONENT_EXCHANGE_C1, SA__SAB_COMPONENT_PORT_A1_CP1,
        SA__SAB_COMPONENT_PORT_OUT_A3_CP3, SA__SAB_COMPONENT_PORT_A2_CP1,
        SA__SAB_COMPONENT_PORT_A3_CP1);
    testOnXAB(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA,
        LA__LAB_COMPONENT_EXCHANGE_C1, LA__LAB_COMPONENT_PORT_A1_CP1,
        LA__LAB_COMPONENT_PORT_OUT_A3_CP1, LA__LAB_COMPONENT_PORT_A2_CP1,
        LA__LAB_COMPONENT_PORT_A3_CP1);
    testOnXAB(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA,
        PA__PAB_COMPONENT_EXCHANGE_1, PA__PAB_COMPONENT_PORT_PC3_CP1,
        PA__PAB_COMPONENT_PORT_OUT_PC7_CP1, PA__PAB_COMPONENT_PORT_PC4_CP1,
        PA__PAB_COMPONENT_PORT_PC7_CP1);
  }

  public void testOnXAB(SessionContext context, String diagramName, BlockArchitectureExt.Type type,
      String ceId, String oldSourceId, String newSourceId, String oldTargetId, String newTargetId,
      String... invalidEnds) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);

    diagram.reconnectComponentExchange(ceId, oldSourceId, newSourceId, BlockArchitectureExt.LinkDirection.SOURCE);
    diagram.reconnectComponentExchange(ceId, oldTargetId, newTargetId, BlockArchitectureExt.LinkDirection.TARGET);
    if (type != BlockArchitectureExt.Type.OA) {
      diagram.cannotReconnectComponentExchange(ceId, newSourceId, oldTargetId,
          BlockArchitectureExt.LinkDirection.SOURCE);
      diagram.cannotReconnectComponentExchange(ceId, newTargetId, newSourceId,
          BlockArchitectureExt.LinkDirection.TARGET);
    }
    if (invalidEnds != null) {
      for (String id : invalidEnds) {
        diagram.cannotReconnectComponentExchange(ceId, newSourceId, id, BlockArchitectureExt.LinkDirection.SOURCE);
        diagram.cannotReconnectComponentExchange(ceId, newTargetId, id, BlockArchitectureExt.LinkDirection.TARGET);
      }
    }
  }

  
}
