/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.misc.ju.testcases;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.sirius.analysis.FaServices;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.context.SessionContext;

/**
 * Test for Bug 2579 - Problem with Insert CE on Types in multi-part mode (reusable mode).
 *
 */
public class Bug2579TestCase extends BasicTestCase {

  private String projectTestName = "Bug2579";
  private String diagramName = "[PAB] Bug";
  private String viewID = "e77ee990-b9f4-45e7-96ff-6fd649caac9e";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(projectTestName);
  }

  @Override
  public void test() throws Exception {

    Session session = getSession(projectTestName);
    assertNotNull(session);
    SessionContext context = new SessionContext(session);

    DiagramContext diagramContext = new OpenDiagramStep(context, diagramName).run();
    DSemanticDecorator partView = diagramContext.getView(viewID);
    assertNotNull(partView);
    assertTrue(partView instanceof DNodeContainer);

    Collection<ComponentExchange> availableConnectionsToInsert = FaServices.getFaServices()
        .getAvailableConnectionsToInsert((DNodeContainer) partView);
    assertEquals(6, availableConnectionsToInsert.size());
  }
}
