/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.context.SessionContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;

import junit.framework.Test;

/**
 * Test Show/Hide on a Physical Link with a Category.
 */
public class ShowHidePhysicalLinkWithCategoryTestCase extends AbstractDiagramTestCase {
  
  private static String PAB_PHYSICAL_SYSTEM = "[PAB] Physical System";
  private static String PA__PHYSICALPKG__PC__PHYSICALLINK1 = "156fe1f4-e3da-48cb-ab02-f135c2ca1d62";
  

  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    
    DiagramContext diagramContext = new OpenDiagramStep(context, PAB_PHYSICAL_SYSTEM).run();
    
    // Ensure that Physical Link is shown
    diagramContext.hasView(PA__PHYSICALPKG__PC__PHYSICALLINK1);
    
    // Hide the Physical Link
    DSemanticDecorator pl1 = diagramContext.getView(PA__PHYSICALPKG__PC__PHYSICALLINK1);
    assertTrue(pl1 instanceof DDiagramElement);
    hideDiagramElement((DDiagramElement)pl1);
    
    // Check that the Physical Link is hidden
    diagramContext.hasHiddenView(PA__PHYSICALPKG__PC__PHYSICALLINK1);
    
    // Refresh the Diagram
    diagramContext.refreshDiagram();
    
    // Check that the Physical Link is still hidden after a refresh
    diagramContext.hasHiddenView(PA__PHYSICALPKG__PC__PHYSICALLINK1);
  }

  @Override
  protected String getRequiredTestModel() {
        return "ShowHideExchangesAndLinks";
  }
  
  public static Test suite() {
    return new ShowHidePhysicalLinkWithCategoryTestCase();
  }
}
