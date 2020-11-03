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

import java.util.List;

import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral;
import org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue;
import org.polarsys.capella.core.sirius.analysis.InformationServices;
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram.DataLiteral;
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram.DataType;
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram.ReferenceType;
import org.polarsys.capella.test.diagram.tools.ju.model.CDBCommunication;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.CDBProjectSettings;

/**
 *
 */
public class InsertRemoveDataValues extends CDBCommunication {

  public InsertRemoveDataValues(CDBProjectSettings settings) {
    super(settings);
  }

  @Override
  protected void testCDB() {
    cdb = CDBDiagram.createDiagram(context, settings.DATAPKG);
    test1(ReferenceType.NUMERIC_REFERENCE);
    test1(ReferenceType.LITERAL_NUMERIC_VALUE);
    test1(ReferenceType.UNARY_EXPRESSION);
    test1(ReferenceType.BINARY_EXPRESSION);
    test1(ReferenceType.LITERAL_STRING_VALUE);
    test1(ReferenceType.STRING_REFERENCE);
    test1(ReferenceType.BOOLEAN_REFERENCE);
    test1(ReferenceType.COMPLEX_VALUE);
    test1(ReferenceType.COMPLEX_VALUE_REFERENCE);
    test1(ReferenceType.ENUMERATION_REFERENCE);
    test1(ReferenceType.COLLECTION_VALUE);
    test1(ReferenceType.COLLECTION_VALUE_REFERENCE);
    test2();
    test3();
  }

  protected void test1(ReferenceType reference) {
    String ref1 = cdb.createReference(reference);
    String ref2 = cdb.createReference(reference);

    cdb.removeDataValues(ref1, cdb.getDiagramId());
    cdb.insertDataValues(ref1, cdb.getDiagramId());
    cdb.removeDataValues(ref2, cdb.getDiagramId());
    cdb.insertDataValues(ref2, cdb.getDiagramId());
  }

  protected void test2() {

  }

  protected void test3() {
    // create an Enumeration with 3 literals
    String enumeration1 = cdb.createDataType(DataType.ENUMERATION);
    cdb.createDataLiteral(enumeration1, DataLiteral.ENUMERATION_LITERAL);
    cdb.createDataLiteral(enumeration1, DataLiteral.ENUMERATION_LITERAL);
    cdb.createDataLiteral(enumeration1, DataLiteral.ENUMERATION_LITERAL);

    // create a BooleanType with a LiteralBooleanValue
    String boolean1 = cdb.createDataType(DataType.BOOLEAN_TYPE);
    cdb.createDataLiteral(boolean1, DataLiteral.BOOLEAN_LITERAL);

    // call the Insert/Remove DataValues tool function used for available DataValues
    List<DataValue> dataValuesToInsert = InformationServices.getService()
        .getAvailableDataValuesToInsert(cdb.getDiagram());

    // check if it does not show EnumerationLiteral or LiteralBooleanValue
    for (DataValue dataValue : dataValuesToInsert) {
      if (dataValue instanceof EnumerationLiteral) {
        fail("An EnumerationLiteral is found in 'Insert/Remove DataValues' tool.");
      } else if (dataValue instanceof LiteralBooleanValue) {
        fail("A LiteralBooleanValue is found in 'Insert/Remove DataValues' tool.");
      }
    }
  }
}
