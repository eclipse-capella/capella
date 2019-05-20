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
package org.polarsys.capella.test.diagram.tools.ju.cdb;

import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.CDBCommunication;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.CDBProjectSettings;

/**
 *
 */
public class CreateClassGroup extends CDBCommunication {

  public CreateClassGroup(CDBProjectSettings settings) {
    super(settings);
  }

  @Override
  protected void testCDB() {
    cdb = CDBDiagram.createDiagram(context, settings.DATAPKG);
    testCreateClassGroup();
  }

  protected void testCreateClassGroup() {
    String dPkg1 = cdb.createDataPackage();
    String dPkg2 = cdb.createDataPackage(dPkg1);

    cdb.createClass();
    cdb.createClass(dPkg1);
    cdb.createClass(dPkg2);

    cdb.createUnion();
    cdb.createUnion(dPkg1);
    cdb.createUnion(dPkg2);

    cdb.createCollection();
    cdb.createCollection(dPkg1);
    cdb.createCollection(dPkg2);
  }
}
