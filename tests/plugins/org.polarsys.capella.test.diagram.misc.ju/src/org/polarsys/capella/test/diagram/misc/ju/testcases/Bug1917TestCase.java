/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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

import org.eclipse.core.resources.IFile;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.junit.Assert;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
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
	private String diagramName5 = "[PAB] Physical System 5";
	private String diagramName6 = "[PAB] Physical System 6";
	private String diagramName7 = "[PAB] Physical System 7";
	private String diagramName8 = "[PAB] Physical System 8";
	
	// [PAB] Physical System 1: Internal Link
	private String[] diag1InternalLinkIds = {"c0e58764-42d0-4b53-90b2-0e356d715f8a", "be17092c-0e29-4673-a0a6-f17bddf1ebe2"};
	
	// [PAB] Physical System 2: Internal Link
	private String[] diag2InternalLinkIds = {"87d246b5-c248-4d9f-bb47-b1aa3819b60f", "f32d3692-70ca-4760-847a-380c9d17cbcd"};
	
	// [PAB] Physical System 3: Internal Link
	private String[] diag3InternalLinkIds = {"b964db82-bc73-4580-a700-53c0c3134cb4", "da05514b-a0d0-4775-866a-f170ccc2b52c"};
	
	// [PAB] Physical System 4: Internal Link 1
	private String[] diag4InternalLink1Ids = {"f383c31a-f65b-4eb8-ba3f-a0d8f9d63025", "99c459dc-d9af-468f-90ac-351dd8843e1d"};
	
  // [PAB] Physical System 4: Internal Link 2
	private String[] diag4InternalLink2Ids = {"16016add-0b5e-46ef-80c5-232981d3fd98", "7b149ae3-2c4c-453c-9ba9-570c1c5a1798"};
	
	// [PAB] Physical System 5: Internal Link 1
	private String[] diag5InternalLink1Ids = {"99db633e-029b-4aef-9bef-b2f88e8aec3a", "906f126b-2009-4936-abcf-b3728d78cb00"};
	
	// [PAB] Physical System 5: Internal Link 2
	private String[] diag5InternalLink2Ids = {"e3c448ae-e128-4445-b71a-c9b4426c2769", "73400651-9742-4e96-b8c1-7cf9bdfb7cf3"};
	
  //[PAB] Physical System 6: Internal Link
	private String[] diag6InternalLinkIds = {"9d807763-412b-4d93-87e5-b0eaa3c6ab14", "bb76ad61-9d4f-497c-b86e-3e7e2109bfd5"};
	
  //[PAB] Physical System 7: Internal Link 1
	private String[] diag7InternalLink1Ids = {"107698e3-5665-464d-b136-69699d21d38a", "12bf742b-358d-40fd-8691-548b65698750"};
	
	//[PAB] Physical System 7: Internal Link 2
	private String[] diag7InternalLink2Ids = {"7f04edf3-6111-49a4-9de2-6e1e12eee8d9", "dfae386d-1637-4099-9612-0ae2680ff492"};
	
	//[PAB] Physical System 7: Internal Link 3
	private String[] diag7InternalLink3Ids = {"107698e3-5665-464d-b136-69699d21d38a", "3c73230b-d6f0-4a34-b3c5-7acf7407c488"};
	
	//[PAB] Physical System 7: Internal Link 4
	private String[] diag7InternalLink4Ids = {"6fa0b1a7-b072-4f44-ab0d-52f516f1449a", "2a14bec3-a1fe-41f3-99ea-848747830209"};
	
	//[PAB] Physical System 8: Internal Link 1
	private String[] diag8InternalLink1Ids = {"6c5ba9e1-8593-45a0-bcf3-76c2b7bf3d06", "2eb644da-e866-4e1c-ba53-f043a6d70b51"};
	
	//[PAB] Physical System 8: Internal Link 2
	private String[] diag8InternalLink2Ids = {"b5d33fd7-8c2b-4808-96f6-2e106c157503", "f626e973-b36f-4a76-86e3-acedbe9a2a18"};
	
	//[PAB] Physical System 8: Internal Link 3
	private String[] diag8InternalLink3Ids = {"f626e973-b36f-4a76-86e3-acedbe9a2a18", "3c6eccae-502a-48f1-98d9-5dd54cafb287"};
	
	//[PAB] Physical System 8: Internal Link 4
	private String[] diag8InternalLink4Ids = {"bfff6a9c-53ef-43f1-9333-3100a6bad3d8", "0081811c-c036-491b-b591-1528567efa63"};
	
	
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
    testInternalLink(session, diagramName1, diag1InternalLinkIds);
    
    testDiagram(session, diagramName2, 3);
    testInternalLink(session, diagramName2, diag2InternalLinkIds);
    
    testDiagram(session, diagramName3, 3);
    testInternalLink(session, diagramName3, diag3InternalLinkIds);
    
    testDiagram(session, diagramName4, 5);
    testInternalLink(session, diagramName4, diag4InternalLink1Ids);
    testInternalLink(session, diagramName4, diag4InternalLink2Ids);
    
    testDiagram(session, diagramName5, 5);
    testInternalLink(session, diagramName5, diag5InternalLink1Ids);
    testInternalLink(session, diagramName5, diag5InternalLink2Ids);
    
    testDiagram(session, diagramName6, 3);
    testInternalLink(session, diagramName6, diag6InternalLinkIds);
    
    testDiagram(session, diagramName7, 10);
    testInternalLink(session, diagramName7, diag7InternalLink1Ids);
    testInternalLink(session, diagramName7, diag7InternalLink2Ids);
    testInternalLink(session, diagramName7, diag7InternalLink3Ids);
    testInternalLink(session, diagramName7, diag7InternalLink4Ids);
    
    testDiagram(session, diagramName8, 9);
    testInternalLink(session, diagramName8, diag8InternalLink1Ids);
    testInternalLink(session, diagramName8, diag8InternalLink2Ids);
    testInternalLink(session, diagramName8, diag8InternalLink3Ids);
    testInternalLink(session, diagramName8, diag8InternalLink4Ids);
  }

  private void testDiagram(Session session, String diagramName, int expectedNbOfEdges) {
    DDiagram diagram = (DDiagram) DiagramHelper.getDRepresentation(session, diagramName);
    
    SessionContext sessionContext = new SessionContext(session);
    DiagramContext diagramContext = new DiagramContext(sessionContext, diagram);
    
    openDiagram(diagramContext);
    
    // Each PAB diagram is expected to have a number of edges representing the PhysicalPath, if this number change the test will fail.
    // When the test fail this mean we are creating more or less internal links for the PhysicalPath
    assertTrue(diagram.getEdges().size() == expectedNbOfEdges);
  }
  
  private void testInternalLink(Session session, String diagramName, String[] ids) {
    DDiagram diagram = (DDiagram) DiagramHelper.getDRepresentation(session, diagramName);
    SessionContext sessionContext = new SessionContext(session);
    DiagramContext diagramContext = new DiagramContext(sessionContext, diagram);
    
    EdgeTarget sourceNode = (EdgeTarget)diagramContext.getView(ids[0]);
    assertNotNull(sourceNode);
    
    EdgeTarget targetNode = (EdgeTarget)diagramContext.getView(ids[1]);
    assertNotNull(targetNode);
    
    DEdge internalLink = null;
    for (DEdge anEdge : DiagramServices.getDiagramServices().getOutgoingEdges(sourceNode)) {
      if (anEdge.getTarget() instanceof PhysicalPath) {

        if ((anEdge.getSourceNode() != null) && (anEdge.getTargetNode() != null)) {
          if (anEdge.getSourceNode().equals(sourceNode) && anEdge.getTargetNode().equals(targetNode)) {
            internalLink = anEdge;
            break;
          } else if (anEdge.getSourceNode().equals(targetNode) && anEdge.getTargetNode().equals(sourceNode)) {
            internalLink = anEdge;
            break;
          }
        }
      }
    }

    for (DEdge anEdge : DiagramServices.getDiagramServices().getIncomingEdges(sourceNode)) {
      if (anEdge.getTarget() instanceof PhysicalPath) {

        if ((anEdge.getSourceNode() != null) && (anEdge.getTargetNode() != null)) {
          if (anEdge.getSourceNode().equals(sourceNode) && anEdge.getTargetNode().equals(targetNode)) {
            internalLink = anEdge;
            break;
          } else if (anEdge.getSourceNode().equals(targetNode) && anEdge.getTargetNode().equals(sourceNode)) {
            internalLink = anEdge;
            break;
          }
        }
      }
    }
    
    assertNotNull(internalLink);
  }

  private void openDiagram(DiagramContext diagramContext) {
    try{
      diagramContext.open();      
    }catch(Exception ex){
      assertTrue(ex.getMessage(),false);
    }
  }
}
