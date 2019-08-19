/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.xab;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ReuseComponents extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    String [] laReuseIds = {LA__LAB_LC1};
    String [] paReuseIds = {PA__PAB_COMPONENT_PC6, PA__PAB_COMPONENT_PC7};
    
    testOnXAB(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA,
        LA__LAB_LOGICAL_SYSTEM_PART, laReuseIds);
    testOnXAB(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA,
        PA__PAB_DIAGRAM, paReuseIds);
  }

  public void testOnXAB(SessionContext context, String diagramName, BlockArchitectureExt.Type type, String containerId, String ...reuseIds) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);
    
    context.setReusableComponents(PROJECT_APPROACH_ID);
    
    diagram.reuseComponent(containerId, reuseIds);
    
    context.setSingletonComponents(PROJECT_APPROACH_ID);
  }
  
  
}
