/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.lab;

import junit.framework.Test;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.diagram.common.ju.step.tools.RenameTool;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ToolHelper;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

/**
 * This test case tests the rename tool of Constraints in LAB diagram
 */
public class ConstraintRenameTestCase extends EmptyProject {
  public static String LAB_DIAGRAM = "[LAB] Logical System - Logical Architecture Blank";
  public static String RENAME_CONSTRAINT_TOOL = "Constraint Content";
  public static String NEW_NAME = "New Constraint";

  @Override
  public void test() throws Exception {
    final Session session = getSessionForTestModel(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    XABDiagram xabDiagram = XABDiagram.createDiagram(context, EmptyProject.LA__LOGICAL_SYSTEM);

    final CDBDiagram cdbDiagram = CDBDiagram.createDiagram(context, EmptyProject.LA__DATA);
    ToolHelper toolhelper = new ToolHelper(session, cdbDiagram.getDiagram());
    AbstractToolDescription tool = toolhelper.getTool(RENAME_CONSTRAINT_TOOL);
    assertTrue("Rename tool has not been found", tool != null && tool instanceof DirectEditLabel);

    DirectEditLabel renameTool = (DirectEditLabel) tool;
    assertTrue("Constraint's name should be displayed when rename tool is applied",
        renameTool.getInputLabelExpression() != null && renameTool.getInputLabelExpression().equals("feature:name"));

    xabDiagram.createConstraint(GenericModel.CONSTRAINT_1);
    Constraint constraint = (Constraint) xabDiagram.getSemanticElement(GenericModel.CONSTRAINT_1);
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

  public static Test suite() {
    return new ConstraintRenameTestCase();
  }
}
