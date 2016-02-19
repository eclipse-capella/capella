/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.test.diagram.common.ju.context.SessionContext;
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

    CDBDiagram diagramContext = CDBDiagram.createDiagram(context, SA__DATAPKG);

    diagramContext.createClass(GenericModel.CLASS_1);
    diagramContext.createClass(GenericModel.CLASS_2);

    diagramContext.removeType(GenericModel.CLASS_1, GenericModel.CLASS_2);
    diagramContext.insertType(GenericModel.CLASS_1, GenericModel.CLASS_2);
  }

  public static Test suite() {
    return new CreateClass();
  }
}
