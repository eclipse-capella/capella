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
package org.polarsys.capella.test.diagram.tools.ju.lab;

import junit.framework.Test;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.SessionContext;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;

/**
 * Test the case of removing components and actors in LAB diagram.
 */
public class ShowHideComponentTestCase extends AbstractDiagramTestCase {
  public static String LC2 = "a40b9c14-b6b4-48a6-b804-e467480b7d7c";
  public static String LA1 = "109f1f79-b2d0-4848-9812-5103c4979bce";
  public static String LAB_DIAGRAM = "[LAB] Logical System - Logical Architecture Blank";

  @Override
  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    XABDiagram xabDiagram = XABDiagram.openDiagram(context, LAB_DIAGRAM, Type.LA);
    xabDiagram.removeComponent(LC2);
    xabDiagram.removeActor(LA1);
  }

  public static Test suite() {
    return new ShowHideComponentTestCase();
  }

  @Override
  protected String getRequiredTestModel() {
    return "SwitchCategory";
  }
}
