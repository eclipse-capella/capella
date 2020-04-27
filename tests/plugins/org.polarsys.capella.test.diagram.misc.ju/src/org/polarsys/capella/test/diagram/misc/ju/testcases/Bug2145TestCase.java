/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.misc.ju.testcases;

import java.util.Arrays;
import java.util.List;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.LineStyle;
import org.eclipse.sirius.diagram.NodeStyle;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.context.SessionContext;

/**
 * Test that functions are not dashed (https://bugs.polarsys.org/show_bug.cgi?id=2145)
 *
 */
public class Bug2145TestCase extends BasicTestCase {
	
	  private String LAB__LC2__LF2 = "40d6033f-e751-4101-a195-bae2c91e0da2"; //$NON-NLS-1$
	  private String PAB__PC2__LF1 = "23e61aa6-b59f-4eee-aa57-a194ec92f2e1"; //$NON-NLS-1$
	  private String LAB3__LOGICALSYSTEM__LF3 = "f4235f74-9c07-408e-97cb-37d64da94713"; //$NON-NLS-1$
	  private String LAB3__LOGICALSYSTEM__LF4 = "f8b8b6ef-cb6e-4253-b6ec-adef276421e8"; //$NON-NLS-1$

	  private String pABDiagramName = "[PAB] 1";
	  private String lABDiagramName = "[LAB] 2";
	  private String lAB3DiagramName = "[LAB] 3";
	  private String projectTestName = "DashFunctionBug";

	  @Override
	  public List<String> getRequiredTestModels() {
	    return Arrays.asList(projectTestName);
	  }

	  @Override
	  public void test() throws Exception {
	    Session session = getSession(projectTestName);
	    assertNotNull(session);
	    SessionContext context = new SessionContext(session);

	    // LAB
	    DiagramContext lABdiagramContext = new OpenDiagramStep(context, lABDiagramName).run();
	    DNode lF2View = (DNode)lABdiagramContext.getView(LAB__LC2__LF2);
	    assertTrue("LF2 should not be dashed", getBorderLineStyle(lF2View) == LineStyle.SOLID);
	    
	    DiagramContext lAB3diagramContext = new OpenDiagramStep(context, lAB3DiagramName).run();
      DNode lF3View = (DNode)lAB3diagramContext.getView(LAB3__LOGICALSYSTEM__LF3);
      assertTrue("LF3 border should be dashed", getBorderLineStyle(lF3View) == LineStyle.DASH);
      DNode lF4View = (DNode)lAB3diagramContext.getView(LAB3__LOGICALSYSTEM__LF4);
      assertTrue("LF4 border should be solid", getBorderLineStyle(lF4View) == LineStyle.SOLID);
	    
	    // PAB
	    DiagramContext pABdiagramContext = new OpenDiagramStep(context, pABDiagramName).run();
	    DNode lF1View = (DNode)pABdiagramContext.getView(PAB__PC2__LF1);
	    assertTrue("LF1 should not be dashed", getBorderLineStyle(lF1View) == LineStyle.SOLID);
	  }
	  
	  private int getBorderLineStyle(DNode node) {
		  NodeStyle ownedStyle = node.getOwnedStyle();
		  LineStyle borderLineStyle = ownedStyle.getBorderLineStyle();
		  return borderLineStyle.getValue();
	  }
}