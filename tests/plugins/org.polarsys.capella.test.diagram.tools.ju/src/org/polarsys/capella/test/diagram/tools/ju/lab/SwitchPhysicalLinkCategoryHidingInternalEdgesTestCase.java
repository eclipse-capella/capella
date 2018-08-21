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
package org.polarsys.capella.test.diagram.tools.ju.lab;

import org.eclipse.sirius.business.api.session.Session;
import org.junit.Assert;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.SwitchTool;
import org.polarsys.capella.test.framework.context.SessionContext;

import junit.framework.Test;

/**
 * Test Show/Hide on a Physical Link with a Category.
 */
public class SwitchPhysicalLinkCategoryHidingInternalEdgesTestCase extends AbstractDiagramTestCase {
  
  private static String DIAGRAM_NAME = "[LAB] Logical System";
  private static String INTERNAL_PHYSICAL_PATH_IN_LA_4 = "aa81d163-ec47-43df-8da2-486db85b726f";
  private static String PORT_1_INTERNAL_PHYSICAL_PATH_IN_LA_4 = "c2d25edb-5ca3-48d8-abb0-29e7eeae00c3";
  private static String PORT_2_INTERNAL_PHYSICAL_PATH_IN_LA_4 = "d6f84c94-a424-460d-993e-089e85de38f9";
  private static String PL_2 = "1082b062-77af-4868-9739-5c4200a6c6ad";
  private static String PHYSICAL_LINK_CATEGORY_1 = "281554ed-9847-4717-83d9-c513d31b0f48";
  
  private static String LA_5 = "761bbcd3-695d-4d43-994a-f9d71a66e4bb";
  private static String PL_3 = "f78d4b2a-0d23-44ce-874e-948a59d23415";
  

  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    
    DiagramContext diagramContext = new OpenDiagramStep(context, DIAGRAM_NAME).run();
    
    // Switch to physical link category
    new SwitchTool(diagramContext, IToolNameConstants.TOOL_LAB_INSERT_REMOVE_PHYSICAL_LINKS_CATEGORIES)
    .insert(PHYSICAL_LINK_CATEGORY_1);

    diagramContext.hasView(PHYSICAL_LINK_CATEGORY_1);
    
    Assert.assertFalse("The internal physical path should be removed", diagramContext.hasEdge(PORT_1_INTERNAL_PHYSICAL_PATH_IN_LA_4, PORT_2_INTERNAL_PHYSICAL_PATH_IN_LA_4, INTERNAL_PHYSICAL_PATH_IN_LA_4));
    
    diagramContext.hasHiddenView(PORT_1_INTERNAL_PHYSICAL_PATH_IN_LA_4);
    
    diagramContext.hasHiddenView(PL_2);
    
    // Switch again to physical link
    new SwitchTool(diagramContext, IToolNameConstants.TOOL_LAB_INSERT_REMOVE_PHYSICAL_LINKS_CATEGORIES)
    .remove(PHYSICAL_LINK_CATEGORY_1);
    diagramContext.hasntView(PHYSICAL_LINK_CATEGORY_1);
    
    Assert.assertTrue("The internal physical path should be added", diagramContext.hasEdge(PORT_1_INTERNAL_PHYSICAL_PATH_IN_LA_4, PORT_2_INTERNAL_PHYSICAL_PATH_IN_LA_4, INTERNAL_PHYSICAL_PATH_IN_LA_4));
    
    diagramContext.hasView(PORT_1_INTERNAL_PHYSICAL_PATH_IN_LA_4);
    
    diagramContext.hasView(PL_2);
    
    // Test Actors tool
    // un-allocate an actor
    new InsertRemoveTool(diagramContext, IToolNameConstants.TOOL_LAB_INSERT_REMOVE_ACTORS)
    .remove(LA_5);
    diagramContext.hasntView(LA_5);
    diagramContext.hasntView(PL_3);
    Assert.assertFalse("The internal functional chain should be removed", 
        diagramContext.hasEdge(PORT_1_INTERNAL_PHYSICAL_PATH_IN_LA_4, 
            PORT_2_INTERNAL_PHYSICAL_PATH_IN_LA_4, 
            INTERNAL_PHYSICAL_PATH_IN_LA_4));
    
    // re-allocate an actor
    new InsertRemoveTool(diagramContext, IToolNameConstants.TOOL_LAB_INSERT_REMOVE_ACTORS)
    .insert(LA_5);
    diagramContext.hasView(LA_5);
    diagramContext.hasView(PL_3);
    Assert.assertTrue("The internal functional chain should be added", 
        diagramContext.hasEdge(PORT_1_INTERNAL_PHYSICAL_PATH_IN_LA_4, 
            PORT_2_INTERNAL_PHYSICAL_PATH_IN_LA_4, 
            INTERNAL_PHYSICAL_PATH_IN_LA_4));
  }

  @Override
  protected String getRequiredTestModel() {
        return "SwitchCategoryHidingInternalEdges";
  }
  
  public static Test suite() {
    return new SwitchPhysicalLinkCategoryHidingInternalEdgesTestCase();
  }
}
