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
public class SwitchPhysicalLinkCategoryHidingInternalEdgesTestCase extends AbstractDiagramTestCase {
  
  private static String DIAGRAM_NAME = "[SAB] System";
  private static String INTERNAL_PHYSICAL_PATH_TO_BE_REMOVED = "eb401899-08cc-4c54-8233-8ff6d3f4899d";
  private static String PORT_TO_BE_HIDDEN = "a5a40ad5-57c9-49ae-a681-f3461b9670b1";
  private static String PORT_2 = "802a25ea-22ea-4608-a9de-4d885d3f7992";
  private static String PHYSICAL_LINK_TO_BE_HIDDEN = "c7b15680-b118-482b-9373-ad230a29cc56";
  private static String PHYSICAL_LINK_CATEGORY_TO_BE_SHOWN = "3f49fcea-3c7f-4057-a6f6-f310b085863a";
  

  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    
    DiagramContext diagramContext = new OpenDiagramStep(context, DIAGRAM_NAME).run();
    
    // Switch to physical link category
    new SwitchTool(diagramContext, IToolNameConstants.TOOL_SAB_INSERT_REMOVE_PHYSICAL_LINKS_CATEGORIES)
    .insert(PHYSICAL_LINK_CATEGORY_TO_BE_SHOWN);

    diagramContext.hasView(PHYSICAL_LINK_CATEGORY_TO_BE_SHOWN);
    
    Assert.assertFalse("The internal physical path should be removed", diagramContext.hasEdge(PORT_TO_BE_HIDDEN, PORT_2, INTERNAL_PHYSICAL_PATH_TO_BE_REMOVED));
    
    diagramContext.hasHiddenView(PORT_TO_BE_HIDDEN);
    
    diagramContext.hasHiddenView(PHYSICAL_LINK_TO_BE_HIDDEN);
    
    // Switch again to physical link
    new SwitchTool(diagramContext, IToolNameConstants.TOOL_SAB_INSERT_REMOVE_PHYSICAL_LINKS_CATEGORIES)
    .remove(PHYSICAL_LINK_CATEGORY_TO_BE_SHOWN);
    diagramContext.hasntView(PHYSICAL_LINK_CATEGORY_TO_BE_SHOWN);
    
    Assert.assertTrue("The internal physical path should be added", diagramContext.hasEdge(PORT_TO_BE_HIDDEN, PORT_2, INTERNAL_PHYSICAL_PATH_TO_BE_REMOVED));
    
    diagramContext.hasView(PORT_TO_BE_HIDDEN);
    
    diagramContext.hasView(PHYSICAL_LINK_TO_BE_HIDDEN);
  }

  @Override
  protected String getRequiredTestModel() {
        return "SwitchCategoryHidingInternalEdges";
  }
  
  public static Test suite() {
    return new SwitchPhysicalLinkCategoryHidingInternalEdgesTestCase();
  }
}
