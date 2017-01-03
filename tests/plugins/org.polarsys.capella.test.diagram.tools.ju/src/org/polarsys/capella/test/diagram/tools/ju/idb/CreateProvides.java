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
package org.polarsys.capella.test.diagram.tools.ju.idb;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.test.diagram.common.ju.context.IDBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class CreateProvides extends EmptyProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    IDBDiagram idb = IDBDiagram.createDiagram(context, LA__LOGICAL_SYSTEM);

    idb.createComponent(GenericModel.LC_1);
    idb.createInterface(GenericModel.INTERFACE_1);

    idb.createProvides(GenericModel.LC_1, GenericModel.INTERFACE_1, GenericModel.INTERFACE_PROVIDES_1,
        GenericModel.COMPONENT_PORT_1);

    idb.mustBeInstanceOf(GenericModel.COMPONENT_PORT_1, FaPackage.Literals.COMPONENT_PORT);

    idb.mustBeLinkedTo(GenericModel.COMPONENT_PORT_1, GenericModel.INTERFACE_1,
        InformationPackage.Literals.PORT__PROVIDED_INTERFACES);

    idb.createProvidesNotEnabled(GenericModel.COMPONENT_PORT_1, GenericModel.INTERFACE_1);

  }
}
