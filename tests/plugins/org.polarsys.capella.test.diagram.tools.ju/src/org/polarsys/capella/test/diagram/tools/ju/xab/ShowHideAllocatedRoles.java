/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.test.diagram.common.ju.context.OABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ShowHideAllocatedRoles extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    
    testOnXAB(context, OA__OAB_DIAGRAM, OA__OAB_ROLE1, OA__OAB_ENTITY1);
  }

  public void testOnXAB(SessionContext context, String diagramName, String roleId, String containerId) {
    OABDiagram diagram = OABDiagram.openDiagram(context, diagramName);
    
    diagram.removeRole(roleId, containerId);
    diagram.insertRole(roleId, containerId);
  }
  
  
}
