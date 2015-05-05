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
package org.polarsys.capella.test.diagram.tools.ju.cdb;

import junit.framework.Test;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.diagram.helpers.naming.DiagramDescriptionConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.context.SessionContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateContainerTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.diagram.tools.ju.model.CDBCommunication;
import org.polarsys.capella.test.diagram.tools.ju.model.GenericModel;

/**
 *
 */
public class CreateClass extends CDBCommunication {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    DiagramContext diagramContext = new CreateDiagramStep(context, SA__DATAPKG, DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME).run();

    new OpenDiagramStep(diagramContext).run();

    new CreateContainerTool(diagramContext, IToolNameConstants.TOOL_CDB_CREATE_CLASS, GenericModel.CLASS_1, diagramContext.getDiagramId()).run();
    new CreateContainerTool(diagramContext, IToolNameConstants.TOOL_CDB_CREATE_CLASS, GenericModel.CLASS_2, diagramContext.getDiagramId()).run();

    new InsertRemoveTool(diagramContext, IToolNameConstants.TOOL_CDB_INSERT_REMOVE_TYPE).remove(GenericModel.CLASS_1, GenericModel.CLASS_2);
    new InsertRemoveTool(diagramContext, IToolNameConstants.TOOL_CDB_INSERT_REMOVE_TYPE).insert(GenericModel.CLASS_1, GenericModel.CLASS_2);
  }

  public static Test suite() {
    return new CreateClass();
  }
}
