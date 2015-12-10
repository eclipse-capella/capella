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
package org.polarsys.capella.test.diagram.tools.ju.lab;

import junit.framework.Test;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.context.SessionContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveContainerCreation;

/**
 * Test the case of removing components and actors in LAB diagram.
 */
public class ShowHideComponentTestCase extends AbstractDiagramTestCase {
  public static String LC2 = "2283e062-6553-4eac-8796-b7a23750df18";
  public static String LA1 = "ed28653a-51f7-4e7d-914b-6e8a96c5c296";
  public static String LAB_DIAGRAM = "[LAB] Logical System - Logical Architecture Blank";

  @Override
  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    DiagramContext diagramContext = new OpenDiagramStep(context, LAB_DIAGRAM).run();

    new InsertRemoveContainerCreation(diagramContext, IToolNameConstants.TOOL_XAB_INSERT_REMOVE_COMPONENTS_MONOPART)
        .remove(LC2);
    diagramContext.hasntView(LC2);

    new InsertRemoveContainerCreation(diagramContext, IToolNameConstants.TOOL_LAB_INSERT_ACTOR).remove(LA1);
    diagramContext.hasntView(LA1);
  }

  public static Test suite() {
    return new ShowHideComponentTestCase();
  }

  @Override
  protected String getRequiredTestModel() {
    return "SwitchCategory";
  }
}
