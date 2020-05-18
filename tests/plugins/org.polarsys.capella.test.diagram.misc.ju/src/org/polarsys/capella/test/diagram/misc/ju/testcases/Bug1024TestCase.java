/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.misc.ju.testcases;

import java.util.Arrays;
import java.util.List;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.context.SessionContext;

/**
 * Test that the part PC 2 is not moved under PC 1 when the diagram is opened
 *
 */
public class Bug1024TestCase extends BasicTestCase {
  public static String PA__PC_2 = "e187b2fa-40cc-40a4-bfa9-4210b4d017fc"; //$NON-NLS-1$

  private String pABDiagramName = "[PAB] Physical System";
  private String projectTestName = "bug1204";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(projectTestName);
  }

  @Override
  public void test() throws Exception {
    Session session = getSession(projectTestName);
    assertNotNull(session);
    SessionContext context = new SessionContext(session);

    DiagramContext diagramContext = new OpenDiagramStep(context, pABDiagramName).run();
    DSemanticDecorator partView = diagramContext.getView(PA__PC_2);
    assertTrue("PC 2 should not be moved under PC 1", partView.eContainer() instanceof DDiagram);
  }
}