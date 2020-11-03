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
import org.polarsys.capella.test.diagram.common.ju.context.EABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ManageEABRealizedPhysicalArtifacts extends XABDiagramsProject {
  public static final String CONFIGURATION_ITEM = "CONFIGURATION_ITEM";

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnXAB(context, EPBS__EAB_DIAGRAM, EPBS__EAB_CONFIGURATION_ITEM1, PA__PAB_PHYSICAL_LINK_PL1,
        PA__PAB_COMPONENT_PC6);
  }

  public void testOnXAB(SessionContext context, String diagramName, String containerId, String... ids) {
    EABDiagram diagram = EABDiagram.openDiagram(context, diagramName);

    diagram.manageRealizedPhysicalArtifacts(containerId, ids);
  }

}
