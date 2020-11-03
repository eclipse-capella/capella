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
package org.polarsys.capella.test.diagram.tools.ju.xdfb;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.test.diagram.common.ju.availableXDFBDiagramTools.XDFBInsertRemoveTools;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.XDFBToolsTestingModel;
import org.polarsys.capella.test.framework.context.SessionContext;

public class XDFBInsertPropertyValuesAndGroups extends XDFBToolsTestingModel {

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

    String propertyValueId = null;
    String propertyValueGroupId = null;
    String targetFunctionId = null;

    switch (diagramType) {
    case OA:
      propertyValueId = OA__PROPERTY_VALUE_ID;
      propertyValueGroupId = OA__PROPERTY_VALUE_GROUP_ID;
      targetFunctionId = OA__TEST_SCENARIO_OPERATIONAL_ACTIVITY_4;
      break;
    case SA:
      propertyValueId = SA__PROPERTY_VALUE_ID;
      propertyValueGroupId = SA__PROPERTY_VALUE_GROUP_ID;
      targetFunctionId = SA__TEST_SCENARIO_FUNCTION_5_ID;
      break;
    case LA:
      propertyValueId = LA__PROPERTY_VALUE_ID;
      propertyValueGroupId = LA__PROPERTY_VALUE_GROUP_ID;
      targetFunctionId = LA__TEST_SCENARIO_FUNCTION_5_ID;
      break;
    case PA:
      propertyValueId = PA__PROPERTY_VALUE_ID;
      propertyValueGroupId = PA__PROPERTY_VALUE_GROUP_ID;
      targetFunctionId = PA__TEST_SCENARIO_FUNCTION_5_ID;
      break;
    default:
      break;
    }

    xdfb.showElements(diagramName, XDFBInsertRemoveTools.INSERT_REMOVE_FUNCTIONS, targetFunctionId);

    xdfb.showElements(targetFunctionId, XDFBInsertRemoveTools.INSERT_REMOVE_PROPERTY_VALUES, propertyValueId);
    xdfb.hideElements(targetFunctionId, XDFBInsertRemoveTools.INSERT_REMOVE_PROPERTY_VALUES, propertyValueId);

    xdfb.showElements(targetFunctionId, XDFBInsertRemoveTools.INSERT_REMOVE_PROPERTY_VALUE_GROUPS,
        propertyValueGroupId);
    xdfb.hideElements(targetFunctionId, XDFBInsertRemoveTools.INSERT_REMOVE_PROPERTY_VALUE_GROUPS,
        propertyValueGroupId);
  }
}
