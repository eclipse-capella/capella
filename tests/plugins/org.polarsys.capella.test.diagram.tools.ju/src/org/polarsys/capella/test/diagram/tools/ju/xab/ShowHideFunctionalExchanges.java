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
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ShowHideFunctionalExchanges extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnXAB(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA,
        SA__SAB_FUNCTIONAL_EXCHANGE1, SA__SAB_FUNCTION_1,
        SA__SAB_FUNCTION_2, SA__SAB_A2);
    testOnXAB(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA,
        LA__LAB_FUNCTIONAL_EXCHANGE1, LA__LAB_FUNCTION_1,
        LA__LAB_FUNCTION_2, LA__LAB_A2);
    testOnXAB(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA,
        PA__PAB_FUNCTIONAL_EXCHANGE1, PA__PAB_FUNCTION_1,
        PA__PAB_FUNCTION_2, PA__PAB_COMPONENT_PC4);
  }

  public void testOnXAB(SessionContext context, String diagramName, BlockArchitectureExt.Type type,
      String exchangeId, String containerId, String targetId, String targetContainerId, String... checkIds) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);

    // remove link from the diagram
    DiagramHelper.setSynchronized(diagram.getDiagram(), false);

    diagram.removeFunctionalExchange(exchangeId, containerId, false);

    diagram.removeAllocatedFunction(targetId, targetContainerId);

    diagram.hasntView(exchangeId);
    diagram.hasntViews(checkIds);

    // insert link the diagram
    diagram.insertFunctionalExchange(exchangeId, containerId, false);
    DiagramHelper.setSynchronized(diagram.getDiagram(), true);

    diagram.hasView(targetId);
    diagram.hasViews(checkIds);
  }

  
}
