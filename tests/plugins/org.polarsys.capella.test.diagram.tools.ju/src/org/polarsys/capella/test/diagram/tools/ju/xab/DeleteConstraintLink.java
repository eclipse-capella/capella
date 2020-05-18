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

import java.util.List;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DEdge;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;

import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.diagram.common.ju.step.tools.DeleteElementTool;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.context.SessionContext;

public class DeleteConstraintLink extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnXAB(context, EPBS__EAB_DIAGRAM, BlockArchitectureExt.Type.EPBS,
        EPBS__EAB_CONSTRAINT);
    testOnXAB(context, OA__OAB_DIAGRAM, BlockArchitectureExt.Type.OA,
        OA__OAB_CONSTRAINT);
    testOnXAB(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA,
        SA__SAB_CONSTRAINT);
    testOnXAB(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA,
        LA__LAB_CONSTRAINT);
    testOnXAB(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA,
        PA__PAB_CONSTRAINT);
  }

  public void testOnXAB(SessionContext context, String diagramName, BlockArchitectureExt.Type type,
      String constraintId) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);

    final CDBDiagram cdbDiagram = CDBDiagram.createDiagram(context, LA__DATA);

    List<DEdge> edgeList = DiagramHelper.getEdges(diagram.getDiagram(), constraintId);
    for (DEdge edge : edgeList) {
      new DeleteElementTool(diagram, cdbDiagram, IToolNameConstants.TOOL_CDB_DELETE_CONSTRAINT_ELEMENT)
          .delete(edge);
    }

    edgeList = DiagramHelper.getEdges(diagram.getDiagram(), constraintId);
    assertTrue("Constraint link is not removed from the diagram after a deletion", edgeList.size() == 0);
  }

  
}
