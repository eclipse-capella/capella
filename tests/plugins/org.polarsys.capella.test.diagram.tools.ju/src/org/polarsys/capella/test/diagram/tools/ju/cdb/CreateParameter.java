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
import org.polarsys.capella.test.diagram.tools.ju.model.CDBCommunication;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.CDBProjectSettings;

/**
 *
 */
public class CreateParameter extends CDBCommunication {

  public CreateParameter(CDBProjectSettings settings) {
    super(settings);
  }

  @Override
  protected void testCDB() {
    cdb = CDBDiagram.createDiagram(context, settings.DATAPKG);
    testCreateParameter();
  }

  protected void testCreateParameter() {
    String cls = cdb.createClass();
    String union = cdb.createUnion();

    String type1 = cdb.createDataType(DataType.BOOLEAN_TYPE);
    String type2 = cdb.createDataType(DataType.NUMERIC_TYPE);

    String service1 = cdb.createOperation(cls);
    String service2 = cdb.createOperation(cls);
    String service3 = cdb.createOperation(union);

    cdb.createParameter(service1, type1);
    cdb.createParameter(service2, type1);
    cdb.createParameter(service2, type1);
    cdb.createParameter(service2, type2);
    cdb.createParameter(service3, type1);
  }
}
