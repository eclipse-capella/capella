/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.framework.context.SessionContext;

/**
 * Test InitializeFromExistingDiagram tool and check that the layout is preserved.
 */
public class InitializeFromExistingDiagramTestCase extends AbstractDiagramTestCase {
  
  private static final String OAIB_DIAGRAM = "[SDFB] [CAPABILITY] Provide Audio and Video Intercommunication Means";
  
  private static final String LA__ROOT_LF = "0f580227-c543-436e-979f-ee70432656e2";
  
  @Override
  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    // Open the OAIB diagram
    final DiagramContext oAIBDiagramContext = new OpenDiagramStep(context, OAIB_DIAGRAM).run();
    assertNotNull(oAIBDiagramContext);
    
    // Create and initialize SDFB diagram
    XDFBDiagram xDFBDiagramContext = XDFBDiagram.createDiagram(context, LA__ROOT_LF);
    xDFBDiagramContext.initializationFromExistingDiagram(oAIBDiagramContext);
    
    // The check is done in the tool
  }

  public static Test suite() {
    return new InitializeFromExistingDiagramTestCase();
  }

  @Override
  protected String getRequiredTestModel() {
    return "In-Flight Entertainment System";
  }
  
  @Override
  protected void undoAllChanges() {
    // TODO Undo all changes does not work with this test
  }
  
}
