/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.diagram.DDiagram;
import org.junit.Assert;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.GuiActions;


/**
 * For a diagram, with, as contextual element , a FC which involves internal functions
 * Test that a refresh doesn't make the diagram dirty
 */
public class RefreshFCContextualElementsTest extends BasicTestCase {

	private String projectTestName = "testRefreshContextualElements";
	
	@Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(projectTestName);
  }
	
	@Override
  public void test() throws Exception {
    Session session = getSession(projectTestName);
    assertNotNull(session);
    
    IFile airdFile = getAirdFileForLoadedModel(projectTestName);
    Assert.assertNotNull(airdFile);
    GuiActions.openSession(airdFile, true);
      
    testDiagram(session, airdFile, "[SAB] System");
    testDiagram(session, airdFile, "[LAB] Structure");
    testDiagram(session, airdFile, "[PAB] Physical System");

  }

  protected void testDiagram(Session session, IFile airdFile, String diagramName) {
    DDiagram diagram = (DDiagram) DiagramHelper.getDRepresentation(session, diagramName);
    assertNotNull(MessageFormat.format("{0} diagram is not contained in the session associated to {1} file",
        diagramName, airdFile), diagram);
    
    SessionContext sessionContext = new SessionContext(session);
    final DiagramContext diagramContext = new DiagramContext(sessionContext, diagram);
    
    diagramContext.open();
    assertEquals(SessionStatus.SYNC, session.getStatus());

    diagramContext.refreshDiagram();
    assertEquals(SessionStatus.SYNC, session.getStatus());

    diagramContext.close();
  }
  
}
