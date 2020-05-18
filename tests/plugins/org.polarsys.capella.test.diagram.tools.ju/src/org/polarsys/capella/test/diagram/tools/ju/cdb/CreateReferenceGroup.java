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
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram.ReferenceType;
import org.polarsys.capella.test.diagram.tools.ju.model.CDBCommunication;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.CDBProjectSettings;

/**
 *
 */
public class CreateReferenceGroup extends CDBCommunication {

  public CreateReferenceGroup(CDBProjectSettings settings) {
    super(settings);
  }

  @Override
  protected void testCDB() {
    cdb = CDBDiagram.createDiagram(context, settings.DATAPKG);
    testReference(CDBDiagram.ReferenceType.NUMERIC_REFERENCE);
    testReference(CDBDiagram.ReferenceType.LITERAL_NUMERIC_VALUE);
    testReference(CDBDiagram.ReferenceType.UNARY_EXPRESSION);
    testReference(CDBDiagram.ReferenceType.BINARY_EXPRESSION);
    testReference(CDBDiagram.ReferenceType.LITERAL_STRING_VALUE);
    testReference(CDBDiagram.ReferenceType.STRING_REFERENCE);
    testReference(CDBDiagram.ReferenceType.BOOLEAN_REFERENCE);
    testReference(CDBDiagram.ReferenceType.COMPLEX_VALUE);
    testReference(CDBDiagram.ReferenceType.COMPLEX_VALUE_REFERENCE);
    testReference(CDBDiagram.ReferenceType.ENUMERATION_REFERENCE);
    testReference(CDBDiagram.ReferenceType.COLLECTION_VALUE);
    testReference(CDBDiagram.ReferenceType.COLLECTION_VALUE_REFERENCE);
  }

  protected void testReference(ReferenceType reference) {
    cdb.createReference(reference);
    cdb.createReference(reference);
  }
}
