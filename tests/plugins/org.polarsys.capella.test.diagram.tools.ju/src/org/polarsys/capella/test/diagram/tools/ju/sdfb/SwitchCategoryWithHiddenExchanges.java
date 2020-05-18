/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
 * Hidden exchanges associated with a displayed categories should be removed from diagram.
 *
 */
public class SwitchCategoryWithHiddenExchanges extends SwitchCategory {

  @Override
  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    DiagramContext diagramContext = new OpenDiagramStep(context, LAB_LOGICAL_SYSTEM).run();
    // Show the ExchangeCategory
    new SwitchTool(diagramContext, IToolNameConstants.TOOL_SDFB_SHOW_HIDE_FUNCTIONAL_EXCH_CATEGORIES)
        .insert(EXCHANGECATEGORY_2);

    // After show category -> FEs are not displayed, ExchangeCategory is displayed
    diagramContext.hasView(EXCHANGECATEGORY_2);
    diagramContext.hasntView(FUNCTIONALEXCHANGE_3);
    diagramContext.hasntView(FUNCTIONALEXCHANGE_4);
    diagramContext.hasntView(FUNCTIONALEXCHANGE_5);

  }

  public static Test suite() {
    return new SwitchCategoryWithHiddenExchanges();
  }
}
