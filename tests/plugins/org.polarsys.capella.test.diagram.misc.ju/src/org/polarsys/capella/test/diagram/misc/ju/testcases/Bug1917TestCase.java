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
	
	// [PAB] Physical System 1: Internal Link
	private String[] diag1_internalLink_ids = {"c0e58764-42d0-4b53-90b2-0e356d715f8a", "be17092c-0e29-4673-a0a6-f17bddf1ebe2"};
	
	// [PAB] Physical System 2: Internal Link
	private String[] diag2_internalLink_ids = {"87d246b5-c248-4d9f-bb47-b1aa3819b60f", "f32d3692-70ca-4760-847a-380c9d17cbcd"};
	
	// [PAB] Physical System 3: Internal Link
	private String[] diag3_internalLink_ids = {"b964db82-bc73-4580-a700-53c0c3134cb4", "da05514b-a0d0-4775-866a-f170ccc2b52c"};
	
	// [PAB] Physical System 4: Internal Link 1
	private String[] diag4_internalLink1_ids = {"f383c31a-f65b-4eb8-ba3f-a0d8f9d63025", "99c459dc-d9af-468f-90ac-351dd8843e1d"};
	
  // [PAB] Physical System 4: Internal Link 2
	private String[] diag4_internalLink2_ids = {"16016add-0b5e-46ef-80c5-232981d3fd98", "7b149ae3-2c4c-453c-9ba9-570c1c5a1798"};
	
	// [PAB] Physical System 5: Internal Link 1
	private String[] diag5_internalLink1_ids = {"99db633e-029b-4aef-9bef-b2f88e8aec3a", "906f126b-2009-4936-abcf-b3728d78cb00"};
	
	// [PAB] Physical System 5: Internal Link 2
	private String[] diag5_internalLink2_ids = {"e3c448ae-e128-4445-b71a-c9b4426c2769", "73400651-9742-4e96-b8c1-7cf9bdfb7cf3"};
	
	
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
    testInternalLink(session, diagramName1, diag1_internalLink_ids);
    
    testDiagram(session, diagramName2, 3);
    testInternalLink(session, diagramName2, diag2_internalLink_ids);
    
    testDiagram(session, diagramName3, 3);
    testInternalLink(session, diagramName3, diag3_internalLink_ids);
    
    testDiagram(session, diagramName4, 5);
    testInternalLink(session, diagramName4, diag4_internalLink1_ids);
    testInternalLink(session, diagramName4, diag4_internalLink2_ids);
    
    testDiagram(session, diagramName5, 5);
    testInternalLink(session, diagramName5, diag5_internalLink1_ids);
    testInternalLink(session, diagramName5, diag5_internalLink2_ids);
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
      if ((anEdge.getTarget() != null) && (anEdge.getTarget() instanceof PhysicalPath)) {

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
      if ((anEdge.getTarget() != null) && (anEdge.getTarget() instanceof PhysicalPath)) {

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
