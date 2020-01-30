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
package org.polarsys.capella.test.diagram.tools.ju.reuse;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.context.EABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ReuseConfigurationItemTestCase extends ReuseOfComponentsProject {

  @Override
  protected String getRequiredTestModel() {
    return "ReuseOfComponentsModel";
  }

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    EABDiagram diagram = EABDiagram.openDiagram(context, EPBS__EAB_DIAGRAM);
    String diagramId = diagram.getDiagramId();

    // Reuse Logical Actor
    diagram.reuseConfigurationItem(diagramId, EPBS__COTSCI_CI, EPBS__CSCI_CI);
    diagram.reuseConfigurationItem(diagramId, EPBS__COTSCI_CI, EPBS__CSCI_CI);
    diagram.reuseConfigurationItem(diagramId, EPBS__HWCI_CI, EPBS__INTERFACE_CI, EPBS__NDICI_CI, EPBS__PRIMEITEM_CI,
        EPBS__SYSTEM_CI);

    diagram.reuseConfigurationItem(EPBS__PART_COTSCI_CI, EPBS__CSCI_CI, EPBS__INTERFACE_CI, EPBS__NDICI_CI,
        EPBS__PRIMEITEM_CI, EPBS__SYSTEM_CI);

    diagram.reuseConfigurationItem(diagramId, EPBS__HWCI_CI);

    // show/hide reused Configuration Items
    diagram.removeConfigurationItems(diagram.getDiagramId(), EPBS__PART_COTSCI_CI, EPBS__PART_CSCI_CI);
    diagram.insertConfigurationItems(diagram.getDiagramId(), EPBS__PART_COTSCI_CI, EPBS__PART_CSCI_CI);
  }
}
