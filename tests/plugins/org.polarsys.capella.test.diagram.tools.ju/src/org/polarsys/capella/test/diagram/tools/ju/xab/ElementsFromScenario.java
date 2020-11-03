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

public class ElementsFromScenario extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    String[] oaSelectedScenarios = { OA__SCENARIO_OES1, OA__SCENARIO_OAS1 };
    String[] oaInsertedElements = { OA__OAB_ENTITY5, OA__OAB_ENTITY6,
        OA__OAB_OA7, OA__OAB_OA8, OA__OAB_COMMUNICATION_MEAN2,
        OA__OAS_I1 };

    String[] saSelectedScenarios = { SA__SCENARIO_ES1 };
    String[] saInsertedElements = { SA__SAB_SYSTEM_ACTOR4,
        SA__SAB_SYSTEM_FUNCTION4, SA__SAB_COMPONENT_EXCHANGE2,
        SA__SAB_FUNCTIONAL_EXCHANGE_ES1, SA__SAB_FUNCTIONAL_EXCHANGE_ES1,
        SA__SAB_A3 };

    String[] laSelectedScenarios = { LA__CR_FUNCTION_SCENARIO1 };
    String[] laInsertedElements = { LA__LAB_FUNCTION_5 };

    String[] paSelectedScenarios = { PA__SCENARIO_FS1, PA__SCENARIO_ES1 };
    String[] paInsertedElements = { PA__PAB_FUNCTION_4, PA__PAB_PHYSICAL_ACTOR2,
        PA__PAB_PHYSICAL_FUNCTION5, PA__PAB_PHYSICAL_LINK2,
        PA__PAB_PHYSICAL_COMPONENT9, PA__PAB_PC_COMPONENT10,
        PA__PAB_PHYSICAL_FUNCTION6, PA__PAB_FUNCTIONAL_EXCHANGE_ES1 };

    testOnXAB(context, OA__OAB_DIAGRAM, BlockArchitectureExt.Type.OA,
        oaSelectedScenarios, oaInsertedElements);
    testOnXAB(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA,
        saSelectedScenarios, saInsertedElements);
    testOnXAB(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA,
        laSelectedScenarios, laInsertedElements);
    testOnXAB(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA,
        paSelectedScenarios, paInsertedElements);
  }

  public void testOnXAB(SessionContext context, String diagramName, BlockArchitectureExt.Type type,
      String[] scenarioFSIds, String[] insertedElemIds) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);

    diagram.selectElementsFromScenario(scenarioFSIds, insertedElemIds);
  }

  
}
