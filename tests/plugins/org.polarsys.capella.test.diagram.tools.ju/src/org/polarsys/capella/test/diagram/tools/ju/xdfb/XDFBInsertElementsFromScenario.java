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
package org.polarsys.capella.test.diagram.tools.ju.xdfb;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.XDFBToolsTestingModel;
import org.polarsys.capella.test.framework.context.SessionContext;

public class XDFBInsertElementsFromScenario extends XDFBToolsTestingModel {
  @Override
  public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnXDFBDiagram(context, OA__ROOT_OA_DATA_FLOW_DIAGRAM_NAME, Type.OA);
    testOnXDFBDiagram(context, SA__ROOT_SF_DATA_FLOW_DIAGRAM_NAME, Type.SA);
    testOnXDFBDiagram(context, LA__ROOT_LF_DATA_FLOW_DIAGRAM_NAME, Type.LA);
    testOnXDFBDiagram(context, PA__ROOT_PF_DATA_FLOW_DIAGRAM_NAME, Type.PA);
  }

  private void testOnXDFBDiagram(SessionContext context, String diagramName, final Type diagramType) {

    XDFBDiagram xdfb = XDFBDiagram.openDiagram(context, diagramName, diagramType);

    String[] scenarioIds = new String[1];

    switch (diagramType) {

    case OA:
      scenarioIds[0] = XDFBToolsTestingModel.OA__OPERATIONAL_ACTIVITY_TEST_SCENARIO_ID;
      break;

    case SA:
      scenarioIds[0] = XDFBToolsTestingModel.SA__SYSTEM_ANALYSIS_TEST_SCENARIO_ID;
      break;

    case LA:
      scenarioIds[0] = XDFBToolsTestingModel.LA__LOGICAL_ANALYSIS_TEST_SCENARIO_ID;
      break;

    case PA:
      scenarioIds[0] = XDFBToolsTestingModel.PA__PHYSICAL_ANALYSIS_TEST_FUNCTION_SCENARIO_ID;
      break;
    default:
      break;
    }

    xdfb.insertElementsFromScenario(scenarioIds);
  }
}
