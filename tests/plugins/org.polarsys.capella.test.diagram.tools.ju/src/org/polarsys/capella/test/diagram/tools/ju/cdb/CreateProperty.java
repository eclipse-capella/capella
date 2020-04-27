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
public class CreateProperty extends CDBCommunication {

  public CreateProperty(CDBProjectSettings settings) {
    super(settings);
  }

  @Override
  protected void testCDB() {
    cdb = CDBDiagram.createDiagram(context, settings.DATAPKG);
    testCreateProperty();
  }

  protected void testCreateProperty() {
    String cls = cdb.createClass();
    String union = cdb.createUnion();

    String type1 = cdb.createDataType(DataType.BOOLEAN_TYPE);
    String type2 = cdb.createDataType(DataType.STRING_TYPE);
    String type3 = cdb.createDataType(DataType.NUMERIC_TYPE);

    cdb.createProperty(cls, type1);
    cdb.createProperty(cls, type1);
    cdb.createProperty(cls, type2);
    cdb.createProperty(cls, type3);
    cdb.createProperty(union, type2);
    cdb.createProperty(union, type3);
  }
}
