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
public class SwitchFunctionalCategoryHidingInternalEdgesTestCase extends AbstractDiagramTestCase {
  
  private static String DIAGRAM_NAME = "[LAB] Logical System";
  private static String FUNCTIONAL_EXCHANGE_2 = "1489756a-75be-46bc-a797-0a53eeef67c9";
  private static String EXCHANGE_CATEGORY_1 = "020642b3-59ca-4a33-a34a-da499d81f67f";
  
  private static String LOGICAL_SYSTEM_CONTAINER = "2671b6e9-5f9a-40bc-8f2a-2ff5ee0fe029";
  private static String LOGICAL_FUNCTION_5 = "704851d2-9622-4e9f-8ddc-7c45c3027f93";
  private static String FUNCTIONAL_EXCHANGE_3 = "3144ef94-bf0a-40bd-a56a-9c81e0dc032f";
  private static String INTERNAL_FUNCTIONAL_CHAIN_IN_LOGICAL_FUNCTION_4 = "4046880f-4b4b-4f89-b340-d50fe33b7844";
  private static String PORT_OUT_OF_INTERNAL_FUNCTIONAL_CHAIN_IN_LOGICAL_FUNCTION_4 = "c4b057aa-daa7-4ac1-a3ce-5bd8419c4421";
  private static String PORT_IN_OF_INTERNAL_FUNCTIONAL_CHAIN_IN_LOGICAL_FUNCTION_4 = "33c30db4-aca7-4141-8a25-a4350d2274cc";

  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    
    DiagramContext diagramContext = new OpenDiagramStep(context, DIAGRAM_NAME).run();
    
    // Switch to exchange category
    new SwitchTool(diagramContext, IToolNameConstants.TOOL_LAB_INSERT_REMOVE_EXCHANGE_CATEGORIES)
    .insert(EXCHANGE_CATEGORY_1);

    diagramContext.hasView(EXCHANGE_CATEGORY_1);
    
    Assert.assertFalse("The internal functional chain should be removed", 
        diagramContext.hasEdge(PORT_OUT_OF_INTERNAL_FUNCTIONAL_CHAIN_IN_LOGICAL_FUNCTION_4, 
            PORT_IN_OF_INTERNAL_FUNCTIONAL_CHAIN_IN_LOGICAL_FUNCTION_4, 
            INTERNAL_FUNCTIONAL_CHAIN_IN_LOGICAL_FUNCTION_4));
    
    diagramContext.hasHiddenView(PORT_OUT_OF_INTERNAL_FUNCTIONAL_CHAIN_IN_LOGICAL_FUNCTION_4);
    
    diagramContext.hasHiddenView(FUNCTIONAL_EXCHANGE_2);
    
    // Switch again to functional exchange
    new SwitchTool(diagramContext, IToolNameConstants.TOOL_LAB_INSERT_REMOVE_EXCHANGE_CATEGORIES)
    .remove(EXCHANGE_CATEGORY_1);
    diagramContext.hasntView(EXCHANGE_CATEGORY_1);
    
    Assert.assertTrue("The internal functional chain should be added", 
        diagramContext.hasEdge(PORT_OUT_OF_INTERNAL_FUNCTIONAL_CHAIN_IN_LOGICAL_FUNCTION_4, 
            PORT_IN_OF_INTERNAL_FUNCTIONAL_CHAIN_IN_LOGICAL_FUNCTION_4, 
            INTERNAL_FUNCTIONAL_CHAIN_IN_LOGICAL_FUNCTION_4));
    
    diagramContext.hasView(PORT_OUT_OF_INTERNAL_FUNCTIONAL_CHAIN_IN_LOGICAL_FUNCTION_4);
    
    diagramContext.hasView(FUNCTIONAL_EXCHANGE_2);
    
    
    // Test Allocated Functions tool
    // un-allocate
    new InsertRemoveTool(diagramContext, IToolNameConstants.TOOL_LAB_INSERT_REMOVE_ALLOCATED_FUNCTIONS, LOGICAL_SYSTEM_CONTAINER)
    .remove(LOGICAL_FUNCTION_5);
    diagramContext.hasntView(LOGICAL_FUNCTION_5);
    diagramContext.hasntView(FUNCTIONAL_EXCHANGE_3);
    Assert.assertFalse("The internal functional chain should be removed", 
        diagramContext.hasEdge(PORT_OUT_OF_INTERNAL_FUNCTIONAL_CHAIN_IN_LOGICAL_FUNCTION_4, 
            PORT_IN_OF_INTERNAL_FUNCTIONAL_CHAIN_IN_LOGICAL_FUNCTION_4, 
            INTERNAL_FUNCTIONAL_CHAIN_IN_LOGICAL_FUNCTION_4));
    
    // re-allocate
    new InsertRemoveTool(diagramContext, IToolNameConstants.TOOL_LAB_INSERT_REMOVE_ALLOCATED_FUNCTIONS, LOGICAL_SYSTEM_CONTAINER)
    .insert(LOGICAL_FUNCTION_5);
    diagramContext.hasView(LOGICAL_FUNCTION_5);
    diagramContext.hasView(FUNCTIONAL_EXCHANGE_3);
    Assert.assertTrue("The internal functional chain should be added", 
        diagramContext.hasEdge(PORT_OUT_OF_INTERNAL_FUNCTIONAL_CHAIN_IN_LOGICAL_FUNCTION_4, 
            PORT_IN_OF_INTERNAL_FUNCTIONAL_CHAIN_IN_LOGICAL_FUNCTION_4, 
            INTERNAL_FUNCTIONAL_CHAIN_IN_LOGICAL_FUNCTION_4));
  }

  @Override
  protected String getRequiredTestModel() {
        return "SwitchCategoryHidingInternalEdges";
  }
  
  public static Test suite() {
    return new SwitchFunctionalCategoryHidingInternalEdgesTestCase();
  }
}
