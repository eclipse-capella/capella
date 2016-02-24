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
 * This test case tests the Insert/Remove Capabilities tool of MCB diagram
 */
public class InsertRemoveCapabilityTestCase extends AbstractDiagramTestCase {
  public static String MCB_DIAGRAM = "[MCB] Capabilities - Mission Capabilities Blank"; //$NON-NLS-1$ 
  public static String SA__CAPABILITIES__CAPABILITY_1 = "ba42a62f-953b-4266-bb0a-20a9e6116c37";  //$NON-NLS-1$ 

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    MCBDiagram diagramContext = MCBDiagram.openDiagram(context, MCB_DIAGRAM);

    diagramContext.removeCapability(SA__CAPABILITIES__CAPABILITY_1);
    diagramContext.hasntView(SA__CAPABILITIES__CAPABILITY_1);

    diagramContext.insertCapability(SA__CAPABILITIES__CAPABILITY_1);
    diagramContext.hasView(SA__CAPABILITIES__CAPABILITY_1);
  }

  public static Test suite() {
    return new InsertRemoveCapabilityTestCase();
  }

  @Override
  public String getRequiredTestModel() {
    return "MCBDiagramModel";
  }
}
