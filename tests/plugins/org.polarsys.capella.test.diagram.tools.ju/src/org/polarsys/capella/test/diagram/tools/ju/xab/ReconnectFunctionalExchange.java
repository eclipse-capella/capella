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

public class ReconnectFunctionalExchange extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnXAB(context, OA__OAB_DIAGRAM, BlockArchitectureExt.Type.OA,
        OA__OAB_INTERACTION1, OA__OAB_FUNCTION_1,
        OA__OAB_FUNCTION_3, OA__OAB_FUNCTION_2,
        OA__OAB_FUNCTION_1, OA__OAB_OA3, OA__OAB_ROLE1);
    testOnXAB(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA,
        SA__SAB_FUNCTIONAL_EXCHANGE1, SA__SAB_SYSTEM_FUNCTION1_FOP1,
        SA__SAB_SYSTEM_FUNCTION3_FOP1, SA__SAB_SYSTEM_FUNCTION2_FIP1,
        SA__SAB_SYSTEM_FUNCTION2_FIP2, SA__SAB_COMPONENT_PORT_A1_CP1);
    testOnXAB(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA,
        LA__LAB_FUNCTIONAL_EXCHANGE1, LA__LAB_LOGICAL_FUNCTION1_FOP1,
        LA__LAB_LOGICAL_FUNCTION3_FOP1, LA__LAB_LOGICAL_FUNCTION2_FIP1,
        LA__LAB_LOGICAL_FUNCTION2_FIP2, LA__LAB_COMPONENT_PORT_A1_CP1);
    testOnXAB(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA,
        PA__PAB_FUNCTIONAL_EXCHANGE1, PA__PAB_PHYSICAL_FUNCTION1_FOP1,
        PA__PAB_PHYSICAL_FUNCTION3_FOP1, PA__PAB_PHYSICAL_FUNCTION2_FIP1,
        PA__PAB_PHYSICAL_FUNCTION2_FIP2, PA__PAB_COMPONENT_PORT_1);
  }

  public void testOnXAB(SessionContext context, String diagramName, BlockArchitectureExt.Type type,
      String ceId, String oldSourceId, String newSourceId, String oldTargetId, String newTargetId,
      String... invalidEnds) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);
    diagram.reconnectFunctionalExchange(ceId, oldSourceId, newSourceId);
    diagram.reconnectFunctionalExchange(ceId, oldTargetId, newTargetId);

    if (type != BlockArchitectureExt.Type.OA) {
      diagram.cannotReconnectFunctionalExchange(ceId, newSourceId, newTargetId);
      diagram.cannotReconnectFunctionalExchange(ceId, newTargetId, newSourceId);
    }

    if (invalidEnds != null) {
      for (String id : invalidEnds) {
        diagram.cannotReconnectFunctionalExchange(ceId, newSourceId, id);
        diagram.cannotReconnectFunctionalExchange(ceId, newTargetId, id);
      }
    }
  }

  
}
