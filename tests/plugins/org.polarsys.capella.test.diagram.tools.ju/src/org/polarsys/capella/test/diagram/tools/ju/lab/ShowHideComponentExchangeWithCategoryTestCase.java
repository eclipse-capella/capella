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
package org.polarsys.capella.test.diagram.tools.ju.lab;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.context.SessionContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;

import junit.framework.Test;

/**
 * Test Show/Hide on a Component Exchange with a Category.
 */
public class ShowHideComponentExchangeWithCategoryTestCase extends AbstractDiagramTestCase {
  
  private static String LAB_LOGICAL_SYSTEM = "[LAB] Logical System";
  private static String LA__LOGICALPKG__LC__COMPONENTEXCHANGE1 = "7017325b-f207-42e4-9896-64ca24b3eed1";
  

  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    
    DiagramContext diagramContext = new OpenDiagramStep(context, LAB_LOGICAL_SYSTEM).run();
    
    // Ensure that Component Exchange is shown
    diagramContext.hasView(LA__LOGICALPKG__LC__COMPONENTEXCHANGE1);
    
    // Hide the Component Exchange
    DSemanticDecorator ce1 = diagramContext.getView(LA__LOGICALPKG__LC__COMPONENTEXCHANGE1);
    assertTrue(ce1 instanceof DDiagramElement);
    hideDiagramElement((DDiagramElement)ce1);
    
    // Check that the Component Exchange is hidden
    diagramContext.hasHiddenView(LA__LOGICALPKG__LC__COMPONENTEXCHANGE1);
    
    // Refresh the Diagram
    diagramContext.refreshDiagram();
    
    // Check that the Component Exchange is still hidden after a refresh
    diagramContext.hasHiddenView(LA__LOGICALPKG__LC__COMPONENTEXCHANGE1);
  }

  @Override
  protected String getRequiredTestModel() {
        return "ShowHideExchangesAndLinks";
  }
  
  public static Test suite() {
    return new ShowHideComponentExchangeWithCategoryTestCase();
  }
}
