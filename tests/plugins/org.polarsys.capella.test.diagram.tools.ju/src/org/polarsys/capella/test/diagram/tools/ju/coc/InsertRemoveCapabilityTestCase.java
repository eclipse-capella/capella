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
package org.polarsys.capella.test.diagram.tools.ju.coc;

import junit.framework.Test;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.COCDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.SessionContext;

/**
 * This test case tests the Insert/Remove Capabilities tool of COC diagram
 */
public class InsertRemoveCapabilityTestCase extends AbstractDiagramTestCase {
  public static String COC_DIAGRAM = "[COC] OperationalCapability 1 - Contextual Operational Capabilty"; //$NON-NLS-1$ 
  public static String OA__OPERATIONAL_CAPABILITIES__OPERATIONALCAPABILITY_2 = "91ad972f-4246-49ce-ba51-a6d94bebff6d";  //$NON-NLS-1$   

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    
    COCDiagram diagramContext = COCDiagram.openDiagram(context, COC_DIAGRAM);

    diagramContext.removeCapability(OA__OPERATIONAL_CAPABILITIES__OPERATIONALCAPABILITY_2);
    diagramContext.hasntView(OA__OPERATIONAL_CAPABILITIES__OPERATIONALCAPABILITY_2);

    diagramContext.insertCapability(OA__OPERATIONAL_CAPABILITIES__OPERATIONALCAPABILITY_2);
    diagramContext.hasView(OA__OPERATIONAL_CAPABILITIES__OPERATIONALCAPABILITY_2);
  }

  public static Test suite() {
    return new InsertRemoveCapabilityTestCase();
  }

  @Override
  public String getRequiredTestModel() {
    return "MCBDiagramModel";
  }
}
