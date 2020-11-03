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
import org.polarsys.capella.test.diagram.common.ju.context.EABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ShowHideEABConfigurationItems extends XABDiagramsProject {
  public static final String CONFIGURATION_ITEM = "CONFIGURATION_ITEM";

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnXAB(context, EPBS__EAB_DIAGRAM, EPBS__EAB_CONFIGURATION_ITEM1, EPBS__EAB_CONFIGURATION_ITEM2);
  }

  public void testOnXAB(SessionContext context, String diagramName, String... ids) {
    EABDiagram diagram = EABDiagram.openDiagram(context, diagramName);
    diagram.removeConfigurationItems(diagram.getDiagramId(), ids);
    diagram.insertConfigurationItems(diagram.getDiagramId(), ids);
  }

  
}
