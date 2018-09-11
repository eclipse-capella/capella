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
package org.polarsys.capella.test.diagram.tools.ju.pab;

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
  
  private static String DIAGRAM_NAME = "[PAB] Physical System";
  private static String INTERNAL_PHYSICAL_PATH_IN_PC_4 = "feb40332-79f6-4000-abcc-19f8dd26b24f";
  private static String PORT_1_INTERNAL_PHYSICAL_PATH_IN_PC_4 = "00bfeece-ae9e-4fa8-8f55-b2750e558053";
  private static String PORT_2_INTERNAL_PHYSICAL_PATH_IN_PC_4 = "4e1e67fe-fdb6-4ab0-b2c5-fd2ba9e9b4c8";
  private static String PL_2 = "96d793b6-dcc3-456d-bf78-08d50db6bb17";
  private static String PHYSICAL_LINK_CATEGORY_1 = "56b465d6-262e-4b56-9921-04fd8cf41456";
  
  private static String PC_5 = "3f42f218-7b61-4089-a3f1-7c4c18b79316";
  private static String PL_3 = "e5ab44e1-788a-4b78-a34e-b2f465e9f086";

  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    
    DiagramContext diagramContext = new OpenDiagramStep(context, DIAGRAM_NAME).run();
    
    // Switch to physical link category
    new SwitchTool(diagramContext, IToolNameConstants.TOOL_PAB_INSERT_REMOVE_PHYSICAL_LINKS_CATEGORIES)
    .insert(PHYSICAL_LINK_CATEGORY_1);

    diagramContext.hasView(PHYSICAL_LINK_CATEGORY_1);
    
    Assert.assertFalse("The internal physical path should be removed", diagramContext.hasEdge(PORT_1_INTERNAL_PHYSICAL_PATH_IN_PC_4, PORT_2_INTERNAL_PHYSICAL_PATH_IN_PC_4, INTERNAL_PHYSICAL_PATH_IN_PC_4));
    
    diagramContext.hasHiddenView(PORT_1_INTERNAL_PHYSICAL_PATH_IN_PC_4);
    
    // The physical link is removed after switching
    diagramContext.hasntView(PL_2);
    
    // Switch again to physical link
    new SwitchTool(diagramContext, IToolNameConstants.TOOL_PAB_INSERT_REMOVE_PHYSICAL_LINKS_CATEGORIES)
    .remove(PHYSICAL_LINK_CATEGORY_1);
    diagramContext.hasntView(PHYSICAL_LINK_CATEGORY_1);
    
    Assert.assertTrue("The internal physical path should be added", diagramContext.hasEdge(PORT_1_INTERNAL_PHYSICAL_PATH_IN_PC_4, PORT_2_INTERNAL_PHYSICAL_PATH_IN_PC_4, INTERNAL_PHYSICAL_PATH_IN_PC_4));
    
    diagramContext.hasView(PORT_1_INTERNAL_PHYSICAL_PATH_IN_PC_4);
    
    diagramContext.hasView(PL_2);
    
    // Test Node PCs tool
    // un-allocate an PC
    // TODO IToolNameConstants.TOOL_PAB_INSERT_REMOVE_NODE_PCS is deprecated
//    new InsertRemoveTool(diagramContext, IToolNameConstants.TOOL_PAB_INSERT_REMOVE_NODE_PCS)
//    .remove(PC_5);
//    diagramContext.hasntView(PC_5);
//    diagramContext.hasntView(PL_3);
//    Assert.assertFalse("The internal functional chain should be removed", 
//        diagramContext.hasEdge(PORT_1_INTERNAL_PHYSICAL_PATH_IN_PC_4, 
//            PORT_2_INTERNAL_PHYSICAL_PATH_IN_PC_4, 
//            INTERNAL_PHYSICAL_PATH_IN_PC_4));
//    
//    // re-allocate an PC
//    new InsertRemoveTool(diagramContext, IToolNameConstants.TOOL_PAB_INSERT_REMOVE_NODE_PCS)
//    .insert(PC_5);
//    diagramContext.hasView(PC_5);
//    diagramContext.hasView(PL_3);
//    Assert.assertTrue("The internal functional chain should be added", 
//        diagramContext.hasEdge(PORT_1_INTERNAL_PHYSICAL_PATH_IN_PC_4, 
//            PORT_2_INTERNAL_PHYSICAL_PATH_IN_PC_4, 
//            INTERNAL_PHYSICAL_PATH_IN_PC_4));
  }

  @Override
  protected String getRequiredTestModel() {
        return "SwitchCategoryHidingInternalEdges";
  }
  
  public static Test suite() {
    return new SwitchPhysicalLinkCategoryHidingInternalEdgesTestCase();
  }
}
