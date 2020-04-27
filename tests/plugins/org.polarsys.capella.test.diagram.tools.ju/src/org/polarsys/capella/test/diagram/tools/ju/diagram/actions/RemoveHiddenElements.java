/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.diagram.actions;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.GuiActions;

import junit.framework.Test;

/**
 * Verify that after the "Delete hidden elements" action, the hidden element is no more on the diagram
 */
public class RemoveHiddenElements extends AbstractDiagramTestCase {
  public static String HIDDEN_LC_ID = "8854b79a-23de-4a86-b525-9a42a434d954";
  public static String SAB_DIAGRAM = "[LAB] Logical System";
  
  @Override
  public String getRequiredTestModel() {
    return "DiagramAction";
  }
  
  @Override
  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    
    GuiActions.deleteHiddenElements(session, true);
    DiagramContext diagramContext = new OpenDiagramStep(context, SAB_DIAGRAM).run();
    diagramContext.hasntView(HIDDEN_LC_ID);
  }

  public static Test suite() {
    return new RemoveHiddenElements();
  }
}
