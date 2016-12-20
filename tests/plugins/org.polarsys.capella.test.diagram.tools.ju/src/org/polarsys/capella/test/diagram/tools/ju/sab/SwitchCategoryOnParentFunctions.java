/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.sab;

import junit.framework.Test;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.SwitchTool;
import org.polarsys.capella.test.framework.context.SessionContext;

/**
 * This test case tests the switch category tool on a category between parent functions allocated to a System on an SAB diagram.
 */
public class SwitchCategoryOnParentFunctions extends AbstractDiagramTestCase {
  public static String CATEGORY1 = "fb142def-5b68-4dd1-b155-96b7416b7941";
  public static String SAB_DIAGRAM = "[SAB] System - System Architecture Blank";
  
  @Override
  public String getRequiredTestModel() {
    return "SABDiagramModel";
  }
  
  @Override
  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    DiagramContext diagramContext = new OpenDiagramStep(context, SAB_DIAGRAM).run();

    new SwitchTool(diagramContext, IToolNameConstants.TOOL_SDFB_SHOW_HIDE_FUNCTIONAL_EXCH_CATEGORIES)
        .insert(CATEGORY1);

    diagramContext.hasView(CATEGORY1);

  }

  public static Test suite() {
    return new SwitchCategoryOnParentFunctions();
  }
}
