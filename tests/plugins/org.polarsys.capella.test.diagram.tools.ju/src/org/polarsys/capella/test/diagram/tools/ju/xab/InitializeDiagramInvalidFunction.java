/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.sirius.analysis.CapellaServices;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class InitializeDiagramInvalidFunction extends AbstractDiagramTestCase {

  @Override
  public void test() throws Exception {
    checkPrecondition();
    checkRefresh();
    checkLAB();
    checkPAB();
  }

  private void checkPrecondition() {
    Session session = getSession(getRequiredTestModels().get(0));
    SessionContext context = new SessionContext(session);
    XABDiagram sab = XABDiagram.openDiagram(context, "SAB_Structure", BlockArchitectureExt.Type.SA);

    DSemanticDecorator view = sab.getView("03673239-0975-4eb7-bf85-86e8e2babfd0");
    SystemFunction function = context.getSemanticElement("c4fa8eff-dc88-4f1e-bd18-a0ee4502eae5");
    assertFalse(CapellaServices.getService().isAllocatedFunction(function,
        (((DSemanticDecorator) sab.getDiagram()).getTarget()), (DSemanticDecorator) sab.getDiagram()));

    assertTrue(CapellaServices.getService().isAllocatedFunction(function, view.getTarget(), view));
  }

  private void checkLAB() {
    Session session = getSession(getRequiredTestModels().get(0));
    SessionContext context = new SessionContext(session);
    XABDiagram sab = XABDiagram.openDiagram(context, "SAB_Structure", BlockArchitectureExt.Type.SA);
    XABDiagram lab = XABDiagram.openDiagram(context, "LAB_Empty", BlockArchitectureExt.Type.LA);
    lab.initializationFromExistingDiagram(sab);

    // Diagram shall contain Root System
    DDiagramElement system = lab.getDiagram().getOwnedDiagramElements().stream()
        .filter(DNodeContainer.class::isInstance).findFirst().get();
    system.getName().equals("System");

    // Functions shall not be added to the root of diagram
    assertTrue(lab.getDiagram().getOwnedDiagramElements().stream().filter(DNode.class::isInstance).count() == 0);

  }

  private void checkPAB() {
    Session session = getSession(getRequiredTestModels().get(0));
    SessionContext context = new SessionContext(session);
    XABDiagram lab = XABDiagram.openDiagram(context, "Structure", BlockArchitectureExt.Type.LA);
    XABDiagram pab = XABDiagram.openDiagram(context, "PAB_Empty", BlockArchitectureExt.Type.PA);
    pab.initializationFromExistingDiagram(lab);

    // Diagram shall not contain Root System, but only the ssss one.
    DDiagramElement system = pab.getDiagram().getOwnedDiagramElements().stream()
        .filter(DNodeContainer.class::isInstance).findFirst().get();
    system.getName().equals("ssss");

    // Functions shall not be added to the root of diagram
    assertTrue(pab.getDiagram().getOwnedDiagramElements().stream().filter(DNode.class::isInstance).count() == 0);
  }

  private void checkRefresh() {

    // Ensure that existing model with some functions in the blank of diagram are properly cleaned after a refresh
    Session session = getSession(getRequiredTestModels().get(0));
    SessionContext context = new SessionContext(session);
    XABDiagram diagram = XABDiagram.openDiagram(context, "Broken Structure", BlockArchitectureExt.Type.LA);
    DDiagramElement function = diagram.getDiagram().getOwnedDiagramElements().stream().filter(DNode.class::isInstance)
        .findFirst().get();

    if (function != null) {
      assertTrue(diagram.getDiagram().getOwnedDiagramElements().contains(function));
      context.getExecutionManager().execute(new AbstractReadWriteCommand() {

        @Override
        public void run() {
          DialectManager.INSTANCE.refresh(diagram.getDiagram(), new NullProgressMonitor());
        }
      });
      assertTrue(!diagram.getDiagram().getOwnedDiagramElements().contains(function));
    }
  }

  @Override
  protected void undoAllChanges() {
    // Undo all changes does not work with this test
  }

  @Override
  protected String getRequiredTestModel() {
    return "ModelFC";
  }

}
