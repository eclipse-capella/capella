/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.xab;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ShowHidePhysicalPath extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    
    testOnXAB(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA, SA__SAB_PHYSICAL_PATH1);
    testOnXAB(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA, LA__LAB_PHYSICAL_PATH1);
    testOnXAB(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA, PA__PAB_PHYSICAL_PATH1);
  }

  public void testOnXAB(SessionContext context, String diagramName, BlockArchitectureExt.Type type, String path) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);
    
    diagram.removePhysicalPath(path);
    diagram.insertPhysicalPath(path);
  }
  
  
}
