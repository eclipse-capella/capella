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
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.test.diagram.common.ju.context.PABDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.diagram.common.ju.step.tools.RenameTool;
import org.polarsys.capella.test.framework.api.ModelProviderHelper;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.model.GenericModel;

public class ConstraintRename extends XABDiagramsProject {
  public static String RENAME_CONSTRAINT_TOOL = "Constraint Content";
  public static String NEW_NAME = "New Constraint";

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnXAB(session, context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA);
  }

  public void testOnXAB(Session session, SessionContext context, String diagramName, BlockArchitectureExt.Type type) {
    XABDiagram xabDiagram = XABDiagram.openDiagram(context, diagramName, type);
    xabDiagram.createConstraint(GenericModel.CONSTRAINT_1);
    Constraint constraint = (Constraint) xabDiagram.getSessionContext().getSemanticElement(GenericModel.CONSTRAINT_1);
    new RenameTool(xabDiagram, RENAME_CONSTRAINT_TOOL, constraint, NEW_NAME).run();
    assertTrue("The constraint has not been renamed", constraint.getName().equals(NEW_NAME));

    xabDiagram.close();

    GuiActions.flushASyncGuiJobs();
    GuiActions.flushASyncGuiThread();
    if (ModelProviderHelper.getInstance().getModelProvider().undoTestCaseChanges()) {
      undoAllChanges();
    }

    PABDiagram.openDiagram(context, PA__PAB_DIAGRAM);
  }
}
