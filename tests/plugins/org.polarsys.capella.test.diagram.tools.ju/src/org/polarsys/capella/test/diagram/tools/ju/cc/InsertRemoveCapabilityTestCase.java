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
package org.polarsys.capella.test.diagram.tools.ju.cc;

import junit.framework.Test;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.CCDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.SessionContext;

/**
 * This test case tests the Insert/Remove Capabilities tool of CC diagram
 */
public class InsertRemoveCapabilityTestCase extends AbstractDiagramTestCase {
  public static String CC_DIAGRAM = "[CC] Capability 1 - Contextual Capabilty"; //$NON-NLS-1$ 
  public static String SA__CAPABILITIES__CAPABILITY_2 = "e9e061b6-bb46-4916-a257-0d45a8b4f8c9";  //$NON-NLS-1$  

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    CCDiagram diagramContext = CCDiagram.openDiagram(context, CC_DIAGRAM);

    diagramContext.removeCapability(SA__CAPABILITIES__CAPABILITY_2);
    diagramContext.hasntView(SA__CAPABILITIES__CAPABILITY_2);

    diagramContext.insertCapability(SA__CAPABILITIES__CAPABILITY_2);
    diagramContext.hasView(SA__CAPABILITIES__CAPABILITY_2);
  }

  public static Test suite() {
    return new InsertRemoveCapabilityTestCase();
  }

  @Override
  public String getRequiredTestModel() {
    return "MCBDiagramModel";
  }
}
