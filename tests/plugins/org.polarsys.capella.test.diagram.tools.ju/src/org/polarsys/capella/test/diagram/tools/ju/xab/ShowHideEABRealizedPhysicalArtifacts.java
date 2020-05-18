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
import org.polarsys.capella.test.diagram.common.ju.context.EABDiagram;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ShowHideEABRealizedPhysicalArtifacts extends XABDiagramsProject {
  public static final String CONFIGURATION_ITEM = "CONFIGURATION_ITEM";

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnXAB(context, EPBS__EAB_DIAGRAM, EPBS__EAB_CONFIGURATION_ITEM2, PA__PAB_PHYSICAL_COMPONENT_PC1);
  }

  public void testOnXAB(SessionContext context, String diagramName, String containerId, String... ids) {
    EABDiagram diagram = EABDiagram.openDiagram(context, diagramName);
    
    DiagramHelper.setSynchronized(diagram.getDiagram(), false);
    diagram.removeRealizedPhysicalArtifacts(containerId, ids);
    diagram.insertRealizedPhysicalArtifacts(containerId, ids);
    DiagramHelper.setSynchronized(diagram.getDiagram(), true);
  }

  
}
