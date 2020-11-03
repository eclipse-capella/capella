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
package org.polarsys.capella.test.diagram.tools.ju.cdb;

import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.CDBCommunication;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.CDBProjectSettings;

/**
 *
 */
public class InsertRemoveInterfacePackages extends CDBCommunication {

  public InsertRemoveInterfacePackages(CDBProjectSettings settings) {
    super(settings);
  }

  @Override
  protected void testCDB() {
    cdb = CDBDiagram.createDiagram(context, settings.DATAPKG);
    testInsertRemoveInterfacePackage();
  }

  protected void testInsertRemoveInterfacePackage() {
    String iPkg1 = cdb.createInterfacePackage();
    String iPkg2 = cdb.createInterfacePackage(iPkg1);
    String interface1 = cdb.createInterface(iPkg1);
    String interface2 = cdb.createInterface(iPkg2);

    cdb.removeInterfacePkg(iPkg1);
    cdb.insertInterfacePkg(iPkg1);

    cdb.removeInterfacePkg(iPkg1, iPkg2);
    cdb.insertInterfacePkg(iPkg2);
    cdb.insertType(interface2);
    cdb.insertInterfacePkg(iPkg1);
    cdb.insertType(interface1);
  }
}
