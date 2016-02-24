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
package org.polarsys.capella.test.diagram.tools.ju.cm;

import junit.framework.Test;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.CMDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.SessionContext;

/**
 * This test case tests the Insert/Remove Actors tool of CM diagram
 */
public class InsertRemoveActorTestCase extends AbstractDiagramTestCase {
  public static String CM_DIAGRAM = "[CM] Mission 1 - Contextual Mission"; //$NON-NLS-1$ 
  public static String SA__ACTORS__ACTOR_1 = "3a277fb8-1289-426c-95a4-db179bea3545"; //$NON-NLS-1$ 

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    CMDiagram diagramContext = CMDiagram.openDiagram(context, CM_DIAGRAM);

    diagramContext.removeActor(SA__ACTORS__ACTOR_1);
    diagramContext.hasntView(SA__ACTORS__ACTOR_1);

    diagramContext.insertActor(SA__ACTORS__ACTOR_1);
    diagramContext.hasView(SA__ACTORS__ACTOR_1);
  }

  public static Test suite() {
    return new InsertRemoveActorTestCase();
  }

  @Override
  public String getRequiredTestModel() {
    return "MCBDiagramModel";
  }
}
