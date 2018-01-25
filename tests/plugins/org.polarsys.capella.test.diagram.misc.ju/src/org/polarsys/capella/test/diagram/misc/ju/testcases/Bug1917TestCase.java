/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.junit.Assert;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.GuiActions;


/**
 * Test for Bug 1917 - Enhance creation and display of Physical Paths.
 *
 */
public class Bug1917TestCase extends BasicTestCase {

  private String projectTestName = "bug1917";
	private String diagramName1 = "[PAB] Physical System 1";
	private String diagramName2 = "[PAB] Physical System 2";
	private String diagramName3 = "[PAB] Physical System 3";
	private String diagramName4 = "[PAB] Physical System 4";
	
	
	
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
    
    testDiagram(session, diagramName1, 3);
    testDiagram(session, diagramName2, 3);
    testDiagram(session, diagramName3, 3);
    testDiagram(session, diagramName4, 5);
  }
  
  private void testDiagram(Session session, String diagramName, int expectedNbOfEdges) {
    DDiagram diagram = (DDiagram) DiagramHelper.getDRepresentation(session, diagramName);
    
    SessionContext sessionContext = new SessionContext(session);
    DiagramContext diagramContext = new DiagramContext(sessionContext, diagram);
    
    openDiagram(diagramContext);
    
    // Each PAB diagram is expected to have a number of edges representing the PhysicalPath, is this number change the test will fail.
    // When the test fail this mean we are creating more or less internal links for the PhysicalPath
    assertTrue(diagram.getEdges().size() == expectedNbOfEdges);
  }

  private void openDiagram(DiagramContext diagramContext) {
    try{
      diagramContext.open();      
    }catch(Exception ex){
      assertTrue(ex.getMessage(),false);
    }
  }
}
