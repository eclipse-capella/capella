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
package org.polarsys.capella.test.diagram.tools.ju.mcb;

import junit.framework.Test;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.MCBDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.SessionContext;

/**
 * This test case tests the Insert/Remove Actors tool of MCB diagram
 */
public class InsertRemoveMissionTestCase extends AbstractDiagramTestCase {
  public static String MCB_DIAGRAM = "[MCB] Capabilities - Mission Capabilities Blank"; //$NON-NLS-1$ 
  public static String SA__MISSIONS__MISSION_1 = "505f1f08-04d7-41bd-92ca-507246f999f2";  //$NON-NLS-1$ 

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    MCBDiagram diagramContext = MCBDiagram.openDiagram(context, MCB_DIAGRAM);

    diagramContext.removeMission(SA__MISSIONS__MISSION_1);
    diagramContext.hasntView(SA__MISSIONS__MISSION_1);

    diagramContext.insertMission(SA__MISSIONS__MISSION_1);
    diagramContext.hasView(SA__MISSIONS__MISSION_1);
  }

  public static Test suite() {
    return new InsertRemoveMissionTestCase();
  }

  @Override
  public String getRequiredTestModel() {
    return "MCBDiagramModel";
  }
}
