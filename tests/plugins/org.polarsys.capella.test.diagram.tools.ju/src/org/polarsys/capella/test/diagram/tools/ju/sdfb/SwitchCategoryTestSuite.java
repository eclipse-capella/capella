/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.sdfb;

import junit.framework.Test;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.context.SessionContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.diagram.tools.ju.model.SwitchCategory;

/**
 * Test the case when a category is displayed at the parent function level. Then The switch category/functional exchange tool should display the functional
 * exchange of the children functions at the parent function.
 */
public class SwitchCategoryTestSuite extends SwitchCategory {

  @Override
  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    DiagramContext diagramContext = new OpenDiagramStep(context, SDFB_ROOT_SYSTEM_FUNCTION).run();

    new InsertRemoveTool(diagramContext, IToolNameConstants.TOOL_SDFB_SHOW_HIDE_FUNCTIONAL_EXCH_CATEGORIES).insert(SA__FUNCTIONPKG__CATEGORY1);

    diagramContext.hasHiddenView(SA__FUNCTIONPKG__SF__FUNTIONALEXCHANGE1);

  }

  public static Test suite() {
    return new SwitchCategoryTestSuite();
  }
}
