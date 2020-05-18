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
import org.junit.Assert;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class SwitchFunctionalExchangesCategories extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testSwitchFunctionalExchangesCategories(context);
    testSwitchFunctionalExchangesCategories_BetweenParentFunctions(context);
  }

  void testSwitchFunctionalExchangesCategories(SessionContext context) {
    testOnXAB(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA,
        SA__SAB_EXCHANGE_CATEGORY1, SA__SAB_FUNCTIONAL_EXCHANGE1,
        SA__SAB_FUNCTIONAL_CHAIN1, SA__SAB_SYSTEM_FUNCTION1_FIP2,
        SA__SAB_SYSTEM_FUNCTION1_FOP1);
    testOnXAB(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA,
        LA__LAB_EXCHANGE_CATEGORY1, LA__LAB_FUNCTIONAL_EXCHANGE1,
        LA__LAB_FUNCTIONAL_CHAIN1, LA__LAB_LOGICAL_FUNCTION1_FIP1,
        LA__LAB_LOGICAL_FUNCTION1_FOP1);
    testOnXAB(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA,
        PA__PAB_EXCHANGE_CATEGORY1, PA__PAB_FUNCTIONAL_EXCHANGE1,
        PA__PAB_FUNCTIONAL_CHAIN2, PA__PAB_PHYSICAL_FUNCTION2_FIP1,
        PA__PAB_PHYSICAL_FUNCTION2_FOP1);
  }

  void testSwitchFunctionalExchangesCategories_BetweenParentFunctions(SessionContext context) {
    testOnXAB_BetweenParentFunctions(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA,
        SA__SAB_EXCHANGE_CATEGORY4, SA__SAB_FUNCTIONAL_EXCHANGE4);
    testOnXAB_BetweenParentFunctions(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA,
        LA__LAB_EXCHANGE_CATEGORY4, LA__LAB_FUNCTIONAL_EXCHANGE4);
    testOnXAB_BetweenParentFunctions(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA,
        PA__PAB_EXCHANGE_CATEGORY5, PA__PAB_FUNCTIONAL_EXCHANGE5);
  }

  public void testOnXAB(SessionContext context, String diagramName, BlockArchitectureExt.Type type,
      String categoryExchangeId, String exchangeId, String functionalChainId, String source, String target) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);

    diagram.switchFunctionalExchangesCategories(categoryExchangeId, true);

    diagram.refreshDiagram();

    diagram.hasntView(exchangeId);

    Assert.assertFalse("The internal functional chain should be removed",
        diagram.hasEdge(source, target, functionalChainId));

    diagram.switchFunctionalExchangesCategories(categoryExchangeId, false);

    diagram.hasntView(categoryExchangeId);

    // The internal functional chain should be added
    Assert.assertTrue("The internal functional chain should be added",
        diagram.hasEdge(source, target, functionalChainId));

  }

  public void testOnXAB_BetweenParentFunctions(SessionContext context, String diagramName,
      BlockArchitectureExt.Type type, String categoryExchangeId, String exchangeId) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);

    diagram.switchFunctionalExchangesCategories(categoryExchangeId, true);

    diagram.hasView(categoryExchangeId);

    diagram.hasntView(exchangeId);
    
    diagram.switchFunctionalExchangesCategories(categoryExchangeId, false);

    diagram.hasView(exchangeId);

    diagram.hasntView(categoryExchangeId);
    
    diagram.refreshDiagram();
    
    diagram.hasntView(categoryExchangeId); 
  }

  
}
