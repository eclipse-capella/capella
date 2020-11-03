/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.context.SessionContext;

/**
 * Test that the port size computation expression should be correct
 *
 */
public class InterfacePortSizeTestCase extends BasicTestCase {
  public static String LA__CP_1 = "edd60521-edea-4436-9464-629c10c3e2c0"; //$NON-NLS-1$
  public static String LA__CP_2 = "8f9456ac-a1ab-4812-94a1-0b65789f51a7"; //$NON-NLS-1$

  private String cDIDiagramName = "[CDI] Logical System";
  private String cEIDiagramName = "[CEI] Logical System";
  private String cIIDiagramName = "[CII] Logical System";
  private String iDBDiagramName = "[IDB] Logical System";

  private String projectTestName = "testPortSize";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(projectTestName);
  }

  @Override
  public void test() throws Exception {
    Session session = getSession(projectTestName);
    assertNotNull(session);
    SessionContext context = new SessionContext(session);

    checkPortSize(context, cDIDiagramName);
    checkPortSize(context, cEIDiagramName);
    checkPortSize(context, cIIDiagramName);
    checkPortSize(context, iDBDiagramName);
  }

  private void checkPortSize(SessionContext context, String diagramName) {
    DiagramContext diagramContext = new OpenDiagramStep(context, diagramName).run();
    DSemanticDecorator requiredPortView = diagramContext.getView(LA__CP_1);
    assertTrue("View of required port not found", requiredPortView != null && requiredPortView instanceof DEdge);
    DEdge requiredPortEdge = (DEdge) requiredPortView;
    EdgeTarget requiredPort = requiredPortEdge.getSourceNode();
    assertTrue("Size of port is not correct", requiredPort instanceof DNode
        && ((DNode) requiredPort).getHeight() == 3 && ((DNode) requiredPort).getWidth() == 3);

    DSemanticDecorator providedPortView = diagramContext.getView(LA__CP_2);
    assertTrue("View of provided port not found", requiredPortView != null && requiredPortView instanceof DEdge);
    DEdge providedPortEdge = (DEdge) providedPortView;
    EdgeTarget providedPort = providedPortEdge.getSourceNode();
    assertTrue("Size of port is not correct", providedPort instanceof DNode
        && ((DNode) providedPort).getHeight() == 3 && ((DNode) providedPort).getWidth() == 3);
  }
}