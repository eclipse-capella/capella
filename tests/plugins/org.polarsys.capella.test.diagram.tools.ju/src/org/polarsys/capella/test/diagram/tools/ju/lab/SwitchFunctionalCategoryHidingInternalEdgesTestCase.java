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
import org.polarsys.capella.test.diagram.common.ju.step.tools.SwitchTool;
import org.polarsys.capella.test.framework.context.SessionContext;

import junit.framework.Test;

/**
 * Test Show/Hide on a Physical Link with a Category.
 */
public class SwitchFunctionalCategoryHidingInternalEdgesTestCase extends AbstractDiagramTestCase {
  
  private static String DIAGRAM_NAME = "[LAB] Logical System";
  private static String INTERNAL_FUNCTIONAL_CHAIN_TO_BE_REMOVED = "4046880f-4b4b-4f89-b340-d50fe33b7844";
  private static String PORT_TO_BE_HIDDEN = "c4b057aa-daa7-4ac1-a3ce-5bd8419c4421";
  private static String PORT_2 = "33c30db4-aca7-4141-8a25-a4350d2274cc";
  private static String FUNCTIONAL_EXCHANGE_TO_BE_HIDDEN = "1489756a-75be-46bc-a797-0a53eeef67c9";
  private static String EXCHANGE_CATEGORY_TO_BE_SHOWN = "020642b3-59ca-4a33-a34a-da499d81f67f";
  

  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    
    DiagramContext diagramContext = new OpenDiagramStep(context, DIAGRAM_NAME).run();
    
    // Switch to exchange category
    new SwitchTool(diagramContext, IToolNameConstants.TOOL_LAB_INSERT_REMOVE_EXCHANGE_CATEGORIES)
    .insert(EXCHANGE_CATEGORY_TO_BE_SHOWN);

    diagramContext.hasView(EXCHANGE_CATEGORY_TO_BE_SHOWN);
    
    Assert.assertFalse("The internal physical path should be removed", diagramContext.hasEdge(PORT_TO_BE_HIDDEN, PORT_2, INTERNAL_FUNCTIONAL_CHAIN_TO_BE_REMOVED));
    
    diagramContext.hasHiddenView(PORT_TO_BE_HIDDEN);
    
    diagramContext.hasHiddenView(FUNCTIONAL_EXCHANGE_TO_BE_HIDDEN);
    
    // Switch again to functional exchange
    new SwitchTool(diagramContext, IToolNameConstants.TOOL_LAB_INSERT_REMOVE_EXCHANGE_CATEGORIES)
    .remove(EXCHANGE_CATEGORY_TO_BE_SHOWN);
    diagramContext.hasntView(EXCHANGE_CATEGORY_TO_BE_SHOWN);
    
    Assert.assertTrue("The internal physical path should be added", diagramContext.hasEdge(PORT_TO_BE_HIDDEN, PORT_2, INTERNAL_FUNCTIONAL_CHAIN_TO_BE_REMOVED));
    
    diagramContext.hasView(PORT_TO_BE_HIDDEN);
    
    diagramContext.hasView(FUNCTIONAL_EXCHANGE_TO_BE_HIDDEN);
  }

  @Override
  protected String getRequiredTestModel() {
        return "SwitchCategoryHidingInternalEdges";
  }
  
  public static Test suite() {
    return new SwitchFunctionalCategoryHidingInternalEdgesTestCase();
  }
}
