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

import java.text.MessageFormat;
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
 * Test for Bug 1006 - Melody freezes when there is cycle with deployment links.
 *
 */
public class Bug1006TestCase extends BasicTestCase {

	private String diagramName = "[PAB] Physical System";
	private String projectTestName = "bug1006";
	
//	@Rule
//	public Timeout timeout= new Timeout(12, TimeUnit.SECONDS);
	
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
      
    DDiagram diagram = (DDiagram) DiagramHelper.getDRepresentation(session, diagramName);
    assertNotNull(MessageFormat.format("{0} diagram is not contained in the session associated to {1} file",
        diagramName, airdFile), diagram);
    
    SessionContext sessionContext = new SessionContext(session);
    final DiagramContext diagramContext = new DiagramContext(sessionContext, diagram);
    
    long time = System.currentTimeMillis();
    // Timeout for open diagram
    Thread t = new Thread() {
        public void run() {
            openDiagram(diagramContext);
        }
    };
    try {
      t.start();
      t.join(12000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
    if(System.currentTimeMillis() - time < 10000){
//      try {
//        Runtime.getRuntime().exec("taskkill /F /IM eclipse.exe /T");
//      } catch (Exception err) {
//        err.printStackTrace();
//      }
      assertTrue("Diagram should open in less than 10 seconds", false);      
    }
  }
  
  private void openDiagram(DiagramContext diagramContext) {
    try{
      diagramContext.open();      
    }catch(Exception ex){
      assertTrue(ex.getMessage(),false);
    }
  }
}
