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
import org.polarsys.capella.test.diagram.common.ju.availableXDFBDiagramTools.XDFBInsertRemoveTools;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.XDFBToolsTestingModel;
import org.polarsys.capella.test.framework.context.SessionContext;

public class XDFBSwitchFunctionalExchangeCategory extends XDFBToolsTestingModel {

  @Override
  public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnXDFBDiagram(context, SA__ROOT_SF_DATA_FLOW_DIAGRAM_NAME, Type.SA);
    testOnXDFBDiagram(context, LA__ROOT_LF_DATA_FLOW_DIAGRAM_NAME, Type.LA);
    testOnXDFBDiagram(context, PA__ROOT_PF_DATA_FLOW_DIAGRAM_NAME, Type.PA);
  }

  private void testOnXDFBDiagram(SessionContext context, String diagramName, Type diagramType) {

    XDFBDiagram xdfb = XDFBDiagram.openDiagram(context, diagramName, diagramType);

    testSwitchFunctionalExchangesCategories(xdfb, diagramType);
    testSwitchFunctionalExchangesCategoriesOnChild(xdfb, diagramType);
  }

  private void testSwitchFunctionalExchangesCategories(XDFBDiagram xdfb, Type diagramType) {
    String diagramId = xdfb.getDiagramId();

    String exchangeCategoryID = null;
    String function5Id = null;
    String function6Id = null;

    switch (diagramType) {
    case SA:
      exchangeCategoryID = SA__EXCHANGE_CATEGORY_1;
      function5Id = SA__TEST_SCENARIO_FUNCTION_5_ID;
      function6Id = SA__TEST_SCENARIO_FUNCTION_6_ID;
      break;
    case LA:
      exchangeCategoryID = LA__EXCHANGE_CATEGORY_1;
      function5Id = LA__TEST_SCENARIO_FUNCTION_5_ID;
      function6Id = LA__TEST_SCENARIO_FUNCTION_6_ID;
      break;
    case PA:
      exchangeCategoryID = PA__EXCHANGE_CATEGORY_1;
      function5Id = PA__TEST_SCENARIO_FUNCTION_5_ID;
      function6Id = PA__TEST_SCENARIO_FUNCTION_6_ID;
      break;
    default:
      break;
    }

    // The functional exchange will be automatically displayed
    xdfb.showElements(diagramId, XDFBInsertRemoveTools.INSERT_REMOVE_FUNCTIONS, function5Id, function6Id);

    xdfb.switchInFunctionalExchangesCategories(exchangeCategoryID);
    xdfb.switchOutFunctionalExchangesCategories(exchangeCategoryID);
  }

  private void testSwitchFunctionalExchangesCategoriesOnChild(XDFBDiagram xdfb, Type diagramType) {
    String diagramId = xdfb.getDiagramId();

    String exchangeCategoryID = null;
    String parentId = null;
    String childId = null;
    String targetId = null;

    switch (diagramType) {
    case SA:
      exchangeCategoryID = SA__EXCHANGE_CATEGORY_SUB_FUNC;
      parentId = SA__TEST_SCENARIO_FUNCTION_PARENT_ID;
      childId = SA__TEST_SCENARIO_FUNCTION_CHILD_ID;
      targetId = SA__TEST_SCENARIO_FUNCTION_TARGET_ID;
      break;
    case LA:
      exchangeCategoryID = LA__EXCHANGE_CATEGORY_SUB_FUNC;
      parentId = LA__TEST_SCENARIO_FUNCTION_PARENT_ID;
      childId = LA__TEST_SCENARIO_FUNCTION_CHILD_ID;
      targetId = LA__TEST_SCENARIO_FUNCTION_TARGET_ID;
      break;
    case PA:
      exchangeCategoryID = PA__EXCHANGE_CATEGORY_SUB_FUNC;
      parentId = PA__TEST_SCENARIO_FUNCTION_PARENT_ID;
      childId = PA__TEST_SCENARIO_FUNCTION_CHILD_ID;
      targetId = PA__TEST_SCENARIO_FUNCTION_TARGET_ID;
      break;
    default:
      break;
    }

    // The functional exchange will be automatically displayed
    xdfb.showElements(diagramId, XDFBInsertRemoveTools.INSERT_REMOVE_FUNCTIONS, parentId, childId, targetId);

    xdfb.switchInFunctionalExchangesCategories(exchangeCategoryID);
    xdfb.hideElements(diagramId, XDFBInsertRemoveTools.INSERT_REMOVE_FUNCTIONS, childId);
    
    xdfb.switchOutFunctionalExchangesCategories(exchangeCategoryID);
    xdfb.switchInFunctionalExchangesCategories(exchangeCategoryID);
  }
}
