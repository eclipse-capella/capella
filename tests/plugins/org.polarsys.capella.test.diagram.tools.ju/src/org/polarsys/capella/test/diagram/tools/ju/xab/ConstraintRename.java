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
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.diagram.common.ju.step.tools.RenameTool;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ToolHelper;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class ConstraintRename extends XABDiagramsProject {
  public static String RENAME_CONSTRAINT_TOOL = "Constraint Content";
  public static String NEW_NAME = "New Constraint";

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnXAB(session, context, EPBS__EAB_DIAGRAM, BlockArchitectureExt.Type.EPBS);
    testOnXAB(session, context, OA__OAB_DIAGRAM, BlockArchitectureExt.Type.OA);
    testOnXAB(session, context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA);
    testOnXAB(session, context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA);
    testOnXAB(session, context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA);
  }

  public void testOnXAB(Session session, SessionContext context, String diagramName, BlockArchitectureExt.Type type) {
    XABDiagram xabDiagram = XABDiagram.openDiagram(context, diagramName, type);

    final CDBDiagram cdbDiagram = CDBDiagram.createDiagram(context, LA__DATA);
    ToolHelper toolhelper = new ToolHelper(session, cdbDiagram.getDiagram());
    AbstractToolDescription tool = toolhelper.getTool(RENAME_CONSTRAINT_TOOL);
    assertTrue("Rename tool has not been found", tool != null && tool instanceof DirectEditLabel);

    DirectEditLabel renameTool = (DirectEditLabel) tool;
    assertTrue("Constraint's name should be displayed when rename tool is applied",
        renameTool.getInputLabelExpression() != null && renameTool.getInputLabelExpression().equals("feature:name"));

    xabDiagram.createConstraint(GenericModel.CONSTRAINT_1);
    Constraint constraint = (Constraint) xabDiagram.getSessionContext().getSemanticElement(GenericModel.CONSTRAINT_1);
    new RenameTool(xabDiagram, RENAME_CONSTRAINT_TOOL, constraint, NEW_NAME) {
      @Override
      protected AbstractToolDescription getRenameTool() {
        ToolHelper toolhelper = new ToolHelper(session, cdbDiagram.getDiagram());
        AbstractToolDescription tool = toolhelper.getTool(RENAME_CONSTRAINT_TOOL);
        return tool;
      }
    }.run();
    assertTrue("The constraint has not been renamed", constraint.getName().equals(NEW_NAME));
  }
}
