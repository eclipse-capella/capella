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
package org.polarsys.capella.test.diagram.tools.ju.sab;

import org.eclipse.sirius.business.api.session.Session;
import org.junit.Assert;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.SwitchTool;
import org.polarsys.capella.test.framework.context.SessionContext;

import junit.framework.Test;

/**
 * Test Show/Hide on a Physical Link with a Category.
 */
public class SwitchFunctionalCategoryHidingInternalEdgesTestCase extends AbstractDiagramTestCase {
  
  private static String DIAGRAM_NAME = "[SAB] System";
  private static String INTERNAL_FUNCTIONAL_CHAIN_IN_SYSTEM_FUNCTION_4 = "0a2d13d6-16d9-4db2-b9db-086fbb5dc598";
  private static String PORT_OUT_INTERNAL_FUNCTIONAL_CHAIN_IN_SYSTEM_FUNCTION_4 = "95d0dc89-dc2b-4aea-9a89-603800737958";
  private static String PORT_IN_INTERNAL_FUNCTIONAL_CHAIN_IN_SYSTEM_FUNCTION_4 = "7e826b22-79f2-4ba7-b48b-b37a6f502dad";
  private static String FUNCTIONAL_EXCHANGE_2 = "7342d8d0-26bd-48fb-bf7a-c3b2a4508e25";
  private static String EXCHANGE_CATEGORY_1 = "6c4433a6-b67c-4c5c-9e0d-1bba6879e1bf";
  

  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    
    DiagramContext diagramContext = new OpenDiagramStep(context, DIAGRAM_NAME).run();
    
    // Switch to exchange category
    new SwitchTool(diagramContext, IToolNameConstants.TOOL_SAB_INSERT_REMOVE_EXCHANGE_CATEGORIES)
    .insert(EXCHANGE_CATEGORY_1);

    diagramContext.hasView(EXCHANGE_CATEGORY_1);
    
    Assert.assertFalse("The internal functional chain should be removed", 
        diagramContext.hasEdge(PORT_OUT_INTERNAL_FUNCTIONAL_CHAIN_IN_SYSTEM_FUNCTION_4, 
            PORT_IN_INTERNAL_FUNCTIONAL_CHAIN_IN_SYSTEM_FUNCTION_4, 
            INTERNAL_FUNCTIONAL_CHAIN_IN_SYSTEM_FUNCTION_4));
    
    diagramContext.hasHiddenView(PORT_OUT_INTERNAL_FUNCTIONAL_CHAIN_IN_SYSTEM_FUNCTION_4);
    
    diagramContext.hasHiddenView(FUNCTIONAL_EXCHANGE_2);
    
    // Switch again to functional exchange
    new SwitchTool(diagramContext, IToolNameConstants.TOOL_SAB_INSERT_REMOVE_EXCHANGE_CATEGORIES)
    .remove(EXCHANGE_CATEGORY_1);
    diagramContext.hasntView(EXCHANGE_CATEGORY_1);
    
    Assert.assertTrue("The internal functional chain should be added", 
        diagramContext.hasEdge(PORT_OUT_INTERNAL_FUNCTIONAL_CHAIN_IN_SYSTEM_FUNCTION_4, 
            PORT_IN_INTERNAL_FUNCTIONAL_CHAIN_IN_SYSTEM_FUNCTION_4, 
            INTERNAL_FUNCTIONAL_CHAIN_IN_SYSTEM_FUNCTION_4));
    
    diagramContext.hasView(PORT_OUT_INTERNAL_FUNCTIONAL_CHAIN_IN_SYSTEM_FUNCTION_4);
    
    diagramContext.hasView(FUNCTIONAL_EXCHANGE_2);
  }

  @Override
  protected String getRequiredTestModel() {
        return "SwitchCategoryHidingInternalEdges";
  }
  
  public static Test suite() {
    return new SwitchFunctionalCategoryHidingInternalEdgesTestCase();
  }
}
