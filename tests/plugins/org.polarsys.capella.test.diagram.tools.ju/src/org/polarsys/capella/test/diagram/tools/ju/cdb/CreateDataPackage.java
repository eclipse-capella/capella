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
public class CreateDataPackage extends CDBCommunication {

  public CreateDataPackage(CDBProjectSettings settings) {
    super(settings);
  }

  @Override
  protected void testCDB() {
    cdb = CDBDiagram.createDiagram(context, settings.DATAPKG);
    testCreateDataPackage();
  }

  protected void testCreateDataPackage() {
    String dPkg1 = cdb.createDataPackage();
    cdb.createDataPackage(dPkg1);
  }
}
