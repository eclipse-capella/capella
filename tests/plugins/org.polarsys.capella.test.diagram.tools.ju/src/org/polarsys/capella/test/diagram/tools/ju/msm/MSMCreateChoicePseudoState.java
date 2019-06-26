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
package org.polarsys.capella.test.diagram.tools.ju.msm;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.test.diagram.common.ju.context.MSMDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.DiagramToolsModel;
import org.polarsys.capella.test.framework.context.SessionContext;

public class MSMCreateChoicePseudoState extends DiagramToolsModel {

  private final String diagramName = "SA Mode State Machine Test Diagram";

  @Override
  public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    MSMDiagram diagram = MSMDiagram.openDiagram(context, this.diagramName, Type.SA);

    diagram.createChoice(diagram.getDiagramId());
  }
}
