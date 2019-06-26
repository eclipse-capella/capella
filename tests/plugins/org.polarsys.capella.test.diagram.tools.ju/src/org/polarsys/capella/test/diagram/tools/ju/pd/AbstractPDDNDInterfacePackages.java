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
package org.polarsys.capella.test.diagram.tools.ju.pd;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.context.PDDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.DiagramToolsModel;
import org.polarsys.capella.test.framework.context.SessionContext;

abstract class AbstractPDDNDInterfacePackages extends DiagramToolsModel {

  // Child classes will give values to these variables
  protected String diagramName;

  protected String interfacePkg1ID;
  protected String interfacePkg2ID;

  @Override
  public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    PDDiagram pd = PDDiagram.openDiagram(context, diagramName);
    String diagramId = pd.getDiagramId();

    pd.dragAndDropInterfacePackage(diagramId, interfacePkg1ID);
    pd.dragAndDropInterfacePackage(diagramId, interfacePkg2ID);
  }

}
