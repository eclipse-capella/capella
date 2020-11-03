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
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram.DataType;
import org.polarsys.capella.test.diagram.tools.ju.model.CDBCommunication;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.CDBProjectSettings;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.OA_CDBProjectSettings;

/**
 *
 */
public class InsertRemoveDataTypes extends CDBCommunication {

  public InsertRemoveDataTypes(CDBProjectSettings settings) {
    super(settings);
  }

  @Override
  protected void testCDB() {
    cdb = CDBDiagram.createDiagram(context, settings.DATAPKG);
    testInsertRemoveDataType(DataType.BOOLEAN_TYPE);
    testInsertRemoveDataType(DataType.ENUMERATION);
    testInsertRemoveDataType(DataType.NUMERIC_TYPE);
    testInsertRemoveDataType(DataType.PHYSICAL_QUANTITY);
    testInsertRemoveDataType(DataType.STRING_TYPE);

    if (!(settings instanceof OA_CDBProjectSettings)) {
      testInsertRemovePredefinedDataType();
    }

    testInsertRemoveClass();
    testInsertRemoveUnion();
    testInsertRemoveCollection();
  }

  protected void testInsertRemoveDataType(DataType dataType) {
    String type1 = cdb.createDataType(dataType);
    String type2 = cdb.createDataType(dataType);

    cdb.removeType(type1, type2);
    cdb.insertType(type1, type2);
  }

  protected void testInsertRemovePredefinedDataType() {
    cdb.insertType(settings.PREDEFINED_DATATYPE_BOOLEAN, settings.PREDEFINED_DATATYPE_CHAR,
        settings.PREDEFINED_DATATYPE_STRING);
    cdb.removeType(settings.PREDEFINED_DATATYPE_BOOLEAN, settings.PREDEFINED_DATATYPE_CHAR,
        settings.PREDEFINED_DATATYPE_STRING);
  }

  protected void testInsertRemoveClass() {
    String type1 = cdb.createClass();
    String type2 = cdb.createClass();

    cdb.removeType(type1, type2);
    cdb.insertType(type1, type2);
  }

  protected void testInsertRemoveUnion() {
    String type1 = cdb.createUnion();
    String type2 = cdb.createUnion();

    cdb.removeType(type1, type2);
    cdb.insertType(type1, type2);
  }

  protected void testInsertRemoveCollection() {
    String type1 = cdb.createCollection();
    String type2 = cdb.createCollection();

    cdb.removeType(type1, type2);
    cdb.insertType(type1, type2);
  }
}
