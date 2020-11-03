/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import junit.framework.Test;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.SwitchTool;
import org.polarsys.capella.test.diagram.tools.ju.model.SwitchCategory;
import org.polarsys.capella.test.framework.context.SessionContext;

/**
 * Test Show/Hide category. Use the switch tool to:
 * <ol>
 * <li> Show the Exchange Category</li>
 * <li> Hide the Exchange Category</li>
 * </ol>
 */
public class SwitchCategoryTestCase extends SwitchCategory {

  @Override
  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    DiagramContext diagramContext = new OpenDiagramStep(context, SDFB_ROOT_SYSTEM_FUNCTION).run();

    // Before show category -> FE is displayed, ExchangeCategory is not displayed, 
    diagramContext.hasView(SA__FUNCTIONPKG__SF__FUNTIONALEXCHANGE1);
    diagramContext.hasntView(SA__FUNCTIONPKG__CATEGORY1);
    
    // Show the ExchangeCategory
    new SwitchTool(diagramContext, IToolNameConstants.TOOL_SDFB_SHOW_HIDE_FUNCTIONAL_EXCH_CATEGORIES)
        .insert(SA__FUNCTIONPKG__CATEGORY1);

    // After show category -> FE is not displayed, ExchangeCategory is displayed
    diagramContext.hasntView(SA__FUNCTIONPKG__SF__FUNTIONALEXCHANGE1);
    diagramContext.hasView(SA__FUNCTIONPKG__CATEGORY1);

    // Hide the ExchangeCategory
    new SwitchTool(diagramContext, IToolNameConstants.TOOL_SDFB_SHOW_HIDE_FUNCTIONAL_EXCH_CATEGORIES)
        .remove(SA__FUNCTIONPKG__CATEGORY1);
    
    // Same state as in the beginning
    diagramContext.hasView(SA__FUNCTIONPKG__SF__FUNTIONALEXCHANGE1);
    diagramContext.hasntView(SA__FUNCTIONPKG__CATEGORY1);
  }

  public static Test suite() {
    return new SwitchCategoryTestCase();
  }
}
