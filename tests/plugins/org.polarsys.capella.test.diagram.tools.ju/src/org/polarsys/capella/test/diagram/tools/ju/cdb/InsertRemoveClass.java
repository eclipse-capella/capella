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
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.context.SessionContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.tools.ju.model.CDBCommunication;
import org.polarsys.capella.test.diagram.tools.ju.step.cdb.InsertRemoveTypeTool;

/**
 *
 */
public class InsertRemoveClass extends CDBCommunication {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    DiagramContext diagramContext = new CreateDiagramStep(context, SA__DATAPKG, DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME).run();

    new OpenDiagramStep(diagramContext).run();

    new InsertRemoveTypeTool(diagramContext).insert(SA__DATAPKG__CLASS1, SA__DATAPKG__CLASS2);
    new InsertRemoveTypeTool(diagramContext).remove(SA__DATAPKG__CLASS1, SA__DATAPKG__CLASS2);
  }

  public static Test suite() {
    return new InsertRemoveClass();
  }
}
