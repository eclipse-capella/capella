/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.diagram.tools.ju.model.CDBCommunication;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.CDBProjectSettings;

/**
 *
 */
public class InsertRemoveOperations extends CDBCommunication {

  public InsertRemoveOperations(CDBProjectSettings settings) {
    super(settings);
  }

  @Override
  protected void testCDB() {
    cdb = CDBDiagram.createDiagram(context, settings.DATAPKG);
    insertRemoveOperations();
  }

  protected void insertRemoveOperations() {
    String cls = cdb.createClass();
    String union = cdb.createUnion();

    String op1 = cdb.createOperation(cls);
    String op2 = cdb.createOperation(union);

    DiagramHelper.setSynchronized(cdb.getDiagram(), false);

    cdb.removeOperation(op1, cls);
    cdb.insertOperation(op1, cls);

    cdb.removeOperation(op2, union);
    cdb.insertOperation(op2, union);

    DiagramHelper.setSynchronized(cdb.getDiagram(), true);
  }
}
