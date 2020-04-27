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
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram.DataType;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.diagram.tools.ju.model.CDBCommunication;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.CDBProjectSettings;

/**
 *
 */
public class InsertRemoveProperties extends CDBCommunication {

  public InsertRemoveProperties(CDBProjectSettings settings) {
    super(settings);
  }

  @Override
  protected void testCDB() {
    cdb = CDBDiagram.createDiagram(context, settings.DATAPKG);
    insertRemoveProperty();
  }

  protected void insertRemoveProperty() {
    String cls = cdb.createClass();
    String union = cdb.createUnion();

    String type = cdb.createDataType(DataType.BOOLEAN_TYPE);

    String prop1 = cdb.createProperty(cls, type);
    String prop2 = cdb.createProperty(union, type);

    DiagramHelper.setSynchronized(cdb.getDiagram(), false);

    cdb.removeProperty(prop1, cls);
    cdb.insertProperty(prop1, cls);

    cdb.removeProperty(prop2, union);
    cdb.insertProperty(prop2, union);

    DiagramHelper.setSynchronized(cdb.getDiagram(), true);
  }
}
