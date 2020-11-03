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
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram.DataLiteral;
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram.DataType;
import org.polarsys.capella.test.diagram.tools.ju.model.CDBCommunication;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.CDBProjectSettings;

/**
 *
 */
public class CreateDataTypeGroup extends CDBCommunication {

  public CreateDataTypeGroup(CDBProjectSettings settings) {
    super(settings);
  }

  @Override
  protected void testCDB() {
    cdb = CDBDiagram.createDiagram(context, settings.DATAPKG);
    testDataTypeWithLiteral(DataType.BOOLEAN_TYPE, CDBDiagram.DataLiteral.BOOLEAN_LITERAL);
    testDataTypeWithLiteral(DataType.ENUMERATION, CDBDiagram.DataLiteral.ENUMERATION_LITERAL);
    testDataType(DataType.NUMERIC_TYPE);
    testDataType(DataType.PHYSICAL_QUANTITY);
    testDataType(DataType.STRING_TYPE);
    testNodeDataType(DataType.UNIT);
  }

  protected void testDataType(DataType dataType) {
    cdb.createDataType(dataType);
    cdb.createDataType(dataType);
  }

  protected void testNodeDataType(DataType dataType) {
    cdb.createNodeDataType(dataType);
    cdb.createNodeDataType(dataType);
  }

  protected void testDataTypeWithLiteral(DataType dataType, DataLiteral literal) {
    String type1 = cdb.createDataType(dataType);
    String type2 = cdb.createDataType(dataType);
    cdb.createDataLiteral(type1, literal);
    cdb.createDataLiteral(type2, literal);
  }
}
