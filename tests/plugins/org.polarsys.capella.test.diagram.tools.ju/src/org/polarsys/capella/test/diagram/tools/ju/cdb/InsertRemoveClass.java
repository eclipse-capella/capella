/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.CDBCommunication;
import org.polarsys.capella.test.framework.context.SessionContext;

/**
 *
 */
public class InsertRemoveClass extends CDBCommunication {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    CDBDiagram diagramContext = CDBDiagram.createDiagram(context, SA__DATAPKG);

    diagramContext.insertType(SA__DATAPKG__CLASS1, SA__DATAPKG__CLASS2);
    diagramContext.removeType(SA__DATAPKG__CLASS1, SA__DATAPKG__CLASS2);
  }

  public static Test suite() {
    return new InsertRemoveClass();
  }
}
