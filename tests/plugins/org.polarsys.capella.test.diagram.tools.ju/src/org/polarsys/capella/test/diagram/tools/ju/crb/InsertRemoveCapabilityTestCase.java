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
package org.polarsys.capella.test.diagram.tools.ju.crb;

import junit.framework.Test;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.CRBDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.SessionContext;

/**
 * This test case tests the Insert/Remove Capabilities tool of CRB diagram
 */
public class InsertRemoveCapabilityTestCase extends AbstractDiagramTestCase {
  public static String CRB_DIAGRAM = "[CRB] Capabilities - Capability Realization Blank"; //$NON-NLS-1$ 
  public static String LA__CAPABILITIES__CAPABILITYREALIZATION_1 = "2f74a886-f84f-49d1-94e8-432e37a06d06";  //$NON-NLS-1$    

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    CRBDiagram diagramContext = CRBDiagram.openDiagram(context, CRB_DIAGRAM);

    diagramContext.removeCapability(LA__CAPABILITIES__CAPABILITYREALIZATION_1);
    diagramContext.hasntView(LA__CAPABILITIES__CAPABILITYREALIZATION_1);

    diagramContext.insertCapability(LA__CAPABILITIES__CAPABILITYREALIZATION_1);
    diagramContext.hasView(LA__CAPABILITIES__CAPABILITYREALIZATION_1);
  }

  public static Test suite() {
    return new InsertRemoveCapabilityTestCase();
  }

  @Override
  public String getRequiredTestModel() {
    return "MCBDiagramModel";
  }
}
