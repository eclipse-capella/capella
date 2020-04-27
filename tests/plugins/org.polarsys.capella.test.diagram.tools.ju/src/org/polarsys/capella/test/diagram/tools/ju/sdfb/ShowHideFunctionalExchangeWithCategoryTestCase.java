/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.sdfb;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.framework.context.SessionContext;

import junit.framework.Test;

/**
 * Test Show/Hide on a Functional Exchange with a Category.
 */
public class ShowHideFunctionalExchangeWithCategoryTestCase extends AbstractDiagramTestCase {
  
  private static String SDFB_ROOT_SYSTEM_FUNCTION = "[SDFB] Root System Function";
  private static String SA__FUNCTIONPKG__SF__FUNTIONALEXCHANGE1 = "1d58be7c-a879-4ee1-bfb3-7ab823618d73";
  

  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    
    DiagramContext diagramContext = new OpenDiagramStep(context, SDFB_ROOT_SYSTEM_FUNCTION).run();
    
    // Ensure that Functional Exchange is shown
    diagramContext.hasView(SA__FUNCTIONPKG__SF__FUNTIONALEXCHANGE1);
    
    // Hide the Functional Exchange
    DSemanticDecorator fe1 = diagramContext.getView(SA__FUNCTIONPKG__SF__FUNTIONALEXCHANGE1);
    assertTrue(fe1 instanceof DDiagramElement);
    hideDiagramElement((DDiagramElement)fe1);
    
    // Check that the Functional Exchange is hidden
    diagramContext.hasHiddenView(SA__FUNCTIONPKG__SF__FUNTIONALEXCHANGE1);
    
    // Refresh the Diagram
    diagramContext.refreshDiagram();
    
    // Check that the Functional Exchange is still hidden after a refresh
    diagramContext.hasHiddenView(SA__FUNCTIONPKG__SF__FUNTIONALEXCHANGE1);
  }

  @Override
  protected String getRequiredTestModel() {
        return "ShowHideExchangesAndLinks";
  }
  
  public static Test suite() {
    return new ShowHideFunctionalExchangeWithCategoryTestCase ();
  }
}
