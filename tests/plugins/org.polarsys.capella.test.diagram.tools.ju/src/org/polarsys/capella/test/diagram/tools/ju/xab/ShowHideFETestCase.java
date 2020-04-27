/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.xab;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DNode;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;

import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.context.SessionContext;

import junit.framework.Test;

/**
 * Test case for bug 1489 
 * Set up: Auto refresh is disabled, Filter "Hide Function Ports without Exchange" is activated
 * After an an Insert/Remove Functional Exchange, if the filtered port is not visible (visible = false), its associated
 * FE is not displayed
 */
public class ShowHideFETestCase extends XABDiagramsProject {
  public static String LAB_DIAGRAM = "[LAB] Logical System";
  public static String FUNCTIONAL_EXCHANGE_1 = "d5d56ac8-de61-4798-af66-34cf4b92edcf";
  public static String LOGICAL_FUNCTION_2 = "c8609243-8cc9-4c94-b53e-8312206a9a03";
  public static String FIP = "8cf549b0-05bc-4907-b826-8b7f20bdfffc";

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    DiagramHelper.setPreferenceAutoRefresh(false);
    DiagramHelper.setPrefereneRefreshOnOpening(false);
  }

  @Override
  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    XABDiagram xabDiagram = XABDiagram.openDiagram(context, LAB_DIAGRAM, Type.LA);

    xabDiagram.insertFunctionalExchange(FUNCTIONAL_EXCHANGE_1, LOGICAL_FUNCTION_2, false);

    DNode portView = (DNode) xabDiagram.getView(FIP);
    boolean visible = portView.isVisible();
    assertTrue("Filtered port must be visible", visible);
  }

  public static Test suite() {
    return new ShowHideFETestCase();
  }

  @Override
  protected String getRequiredTestModel() {
    return "ShowHideFEModel";
  }
}
