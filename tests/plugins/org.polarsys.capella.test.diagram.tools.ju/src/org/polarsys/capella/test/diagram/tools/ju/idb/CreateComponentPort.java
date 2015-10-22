/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.idb;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.context.SessionContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateContainerTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateNodeTool;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.diagram.tools.ju.model.GenericModel;

public class CreateComponentPort extends EmptyProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    DiagramContext diagramContext = new CreateDiagramStep(context, LA__LOGICAL_SYSTEM,
        IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME).run();

    new OpenDiagramStep(diagramContext).run();

    new CreateContainerTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_COMPONENT, GenericModel.LC_1,
        diagramContext.getDiagramId()).run();

    new CreateNodeTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_STANDARD_PORT, GenericModel.COMPONENT_PORT_1,
        GenericModel.LC_1).run();

    mustBeInstanceOf(diagramContext, GenericModel.COMPONENT_PORT_1, FaPackage.Literals.COMPONENT_PORT);

  }
}
