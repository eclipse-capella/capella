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

public class CreateFunctionalExchange extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
 
    testCreateFunctionalExchange(context);
    testCreateFunctionalExchange_Gather(context);
    testCreateFunctionalExchange_Route(context);
    testCreateFunctionalExchange_Duplicate(context);
    testCreateFunctionalExchange_Select(context);
    testCreateFunctionalExchange_Split(context);
    testCreateFunctionalExchange_FunctionToInPort(context);
    testCreateFunctionalExchange_OutPortToFunction(context);
    testCreateFunctionalExchange_OutPortToInPort(context);
  }

  public void testCreateFunctionalExchange(SessionContext context) {
    testOnXAB(context, OA__OAB_DIAGRAM, BlockArchitectureExt.Type.OA,
        OA__OAB_FUNCTION_1, OA__OAB_FUNCTION_2);
    testOnXAB(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA,
        SA__SAB_FUNCTION_1, SA__SAB_FUNCTION_2);
    testOnXAB(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA,
        LA__LAB_FUNCTION_1, LA__LAB_FUNCTION_2);
    testOnXAB(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA,
        PA__PAB_FUNCTION_1, PA__PAB_FUNCTION_2);
  }

  public void testCreateFunctionalExchange_Gather(SessionContext context) {
    testOnXAB(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA,
        SA__SAB_FUNCTION_1, SA__SAB_FUNCTION_GATHER);
    testOnXAB(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA,
        LA__LAB_FUNCTION_1, LA__LAB_FUNCTION_GATHER);
    testOnXAB(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA,
        PA__PAB_FUNCTION_1, PA__PAB_FUNCTION_GATHER);
  }

  public void testCreateFunctionalExchange_Route(SessionContext context) {
    testOnXAB(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA,
        SA__SAB_FUNCTION_1, SA__SAB_FUNCTION_ROUTE);
    testOnXAB(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA,
        LA__LAB_FUNCTION_1, LA__LAB_FUNCTION_ROUTE);
    testOnXAB(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA,
        PA__PAB_FUNCTION_1, PA__PAB_FUNCTION_ROUTE);
  }

  public void testCreateFunctionalExchange_Duplicate(SessionContext context) {
    testOnXAB(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA,
        LA__LAB_FUNCTION_1, LA__LAB_FUNCTION_DUPLICATE);
    testOnXAB(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA,
        PA__PAB_FUNCTION_1, PA__PAB_FUNCTION_DUPLICATE);
  }

  public void testCreateFunctionalExchange_Select(SessionContext context) {
    testOnXAB(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA,
        SA__SAB_FUNCTION_1, SA__SAB_FUNCTION_SELECT);
    testOnXAB(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA,
        LA__LAB_FUNCTION_1, LA__LAB_FUNCTION_SELECT);
    testOnXAB(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA,
        PA__PAB_FUNCTION_1, PA__PAB_FUNCTION_SELECT);
  }

  public void testCreateFunctionalExchange_Split(SessionContext context) {
    testOnXAB(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA,
        SA__SAB_FUNCTION_1, SA__SAB_FUNCTION_SPLIT);
    testOnXAB(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA,
        LA__LAB_FUNCTION_1, LA__LAB_FUNCTION_SPLIT);
    testOnXAB(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA,
        PA__PAB_FUNCTION_1, PA__PAB_FUNCTION_SPLIT);
  }

  public void testCreateFunctionalExchange_FunctionToInPort(SessionContext context) {
    testOnXAB_SourceTarget(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA,
        SA__SAB_FUNCTION_1, SA__SAB_SYSTEM_FUNCTION3_FIP1);
    testOnXAB_SourceTarget(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA,
        LA__LAB_FUNCTION_1, LA__LAB_LOGICAL_FUNCTION3_FIP1);
    testOnXAB_SourceTarget(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA,
        PA__PAB_FUNCTION_1, PA__PAB_PHYSICAL_FUNCTION3_FIP1);
  }
  
  public void testCreateFunctionalExchange_OutPortToFunction(SessionContext context) {
    testOnXAB_SourceTarget(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA,
        SA__SAB_SYSTEM_FUNCTION3_FOP1, SA__SAB_FUNCTION_1);
    testOnXAB_SourceTarget(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA,
        LA__LAB_LOGICAL_FUNCTION3_FOP1, LA__LAB_FUNCTION_1);
    testOnXAB_SourceTarget(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA,
        PA__PAB_PHYSICAL_FUNCTION3_FOP1, PA__PAB_FUNCTION_1);
  }
  
  public void testCreateFunctionalExchange_OutPortToInPort(SessionContext context) {
    testOnXAB_SourceTarget(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA,
        SA__SAB_SYSTEM_FUNCTION3_FOP1, SA__SAB_SYSTEM_FUNCTION2_FIP1);
    testOnXAB_SourceTarget(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA,
        LA__LAB_LOGICAL_FUNCTION3_FOP1, LA__LAB_LOGICAL_FUNCTION2_FIP1);
    testOnXAB_SourceTarget(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA,
        PA__PAB_PHYSICAL_FUNCTION3_FOP1, PA__PAB_PHYSICAL_FUNCTION2_FIP1);
  }

  public void testOnXAB(SessionContext context, String diagramName, BlockArchitectureExt.Type type,
      String sourceId, String targetId) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);

    diagram.createFunctionalExchange(sourceId, targetId, GenericModel.FUNCTIONAL_EXCHANGE_1 + targetId);
    diagram.createFunctionalExchange(targetId, sourceId, GenericModel.FUNCTIONAL_EXCHANGE_2 + targetId);
  }
  
  public void testOnXAB_SourceTarget(SessionContext context, String diagramName, BlockArchitectureExt.Type type,
      String sourceId, String targetId) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);

    diagram.createFunctionalExchange(sourceId, targetId, GenericModel.FUNCTIONAL_EXCHANGE_1 + targetId);
  }
  
  
}
