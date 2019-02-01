/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.cdb;

import java.util.List;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral;
import org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue;
import org.polarsys.capella.core.sirius.analysis.InformationServices;
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;

import junit.framework.Test;

/**
 * Test for each architecture phases some basic insert remove on ES diagrams
 */
public class InsertRemoveDataValues extends EmptyProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    // OperationalAnalysis
    testOn(context, OA__DATA);

    // SystemAnalysis
    testOn(context, SA__DATA);

    // LogicalArchitecture
    testOn(context, LA__DATA);

    // PhysicalArchitecture
    testOn(context, PA__DATA);
  }

  protected void testOn(SessionContext context, String sourceId) {
    CDBDiagram diagramContext = CDBDiagram.createDiagram(context, sourceId);

    // create an Enumeration with 3 literals
    diagramContext.createEnumeration("Enumeration 1");
    diagramContext.createEnumerationLiteral("EnumerationLiteral 1", "Enumeration 1");
    diagramContext.createEnumerationLiteral("EnumerationLiteral 2", "Enumeration 1");
    diagramContext.createEnumerationLiteral("EnumerationLiteral 3", "Enumeration 1");

    // create a BooleanType with a LiteralBooleanValue
    diagramContext.createBooleanType("BooleanType 1");
    diagramContext.createLiteralBooleanValue("LiteralBooleanValue 1", "BooleanType 1");

    // call the Insert/Remove DataValues tool function used for available DataValues
    List<DataValue> dataValuesToInsert = InformationServices.getService().getAvailableDataValuesToInsert(diagramContext.getDiagram());

    // check if it does not show EnumerationLiteral or LiteralBooleanValue
    for (DataValue dataValue : dataValuesToInsert) {
      if (dataValue instanceof EnumerationLiteral) {
        fail("An EnumerationLiteral is found in 'Insert/Remove DataValues' tool.");
      }
      else if (dataValue instanceof LiteralBooleanValue) {
        fail("A LiteralBooleanValue is found in 'Insert/Remove DataValues' tool.");
      }
    }
  }

  public static Test suite() {
    return new InsertRemoveDataValues();
  }
}
