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
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram.DataLiteral;
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram.DataType;
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram.ExchangeItemType;
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram.ReferenceType;
import org.polarsys.capella.test.diagram.tools.ju.model.CDBCommunication;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.CDBProjectSettings;

/**
 *
 */
public class CDBScenarios extends CDBCommunication {

  public CDBScenarios(CDBProjectSettings settings) {
    super(settings);
  }

  @Override
  protected void testCDB() {
    cdb = CDBDiagram.createDiagram(context, settings.DATAPKG);
    test1();
  }

  protected void test1() {
    String iPkg1 = cdb.createInterfacePackage();
    String iPkg2 = cdb.createInterfacePackage(iPkg1);
    String interf1 = cdb.createInterface(iPkg1);
    String ei1 = cdb.createExchangeItem(ExchangeItemType.EVENT);
    String interf2 = cdb.createInterface();
    String interf3 = cdb.createInterface(iPkg2);
    String ei2 = cdb.createExchangeItem(interf1, ExchangeItemType.EVENT);
    String ei3 = cdb.createExchangeItem(interf2, ExchangeItemType.DATA);
    String ei4 = cdb.createExchangeItem(interf3, ExchangeItemType.FLOW);
    String ei5 = cdb.createExchangeItem(interf3, ExchangeItemType.OPERATION);
    String ei6 = cdb.createExchangeItemNode(iPkg1, ExchangeItemType.EVENT);

    String booleanType = cdb.createDataType(DataType.BOOLEAN_TYPE);
    String booleanLiteral = cdb.createDataLiteral(booleanType, DataLiteral.BOOLEAN_LITERAL);
    String enumeration = cdb.createDataType(DataType.ENUMERATION);
    String enumerationLiteral = cdb.createDataLiteral(enumeration, DataLiteral.ENUMERATION_LITERAL);

    cdb.removeType(booleanType, enumeration);
    cdb.insertType(booleanType, enumeration);

    cdb.hasViews(booleanLiteral, enumerationLiteral);

    cdb.createReference(ReferenceType.BINARY_EXPRESSION);
    cdb.createReference(ReferenceType.LITERAL_STRING_VALUE);

    cdb.createExchangeItemElement(ei1, booleanType);

    cdb.insertExchangeItemAllocations(interf1, ei1);

    cdb.removeInterfacePkg(iPkg1);
    cdb.hasView(iPkg2);
    cdb.hasntViews(interf1);

    cdb.insertInterfacePkg(iPkg1);
    cdb.insertInterface(interf1);

    String pkg1 = cdb.createDataPackage();
    String pkg2 = cdb.createDataPackage(pkg1);
    String pkg3 = cdb.createDataPackage(pkg2);

    String class1 = cdb.createClass();
    String class2 = cdb.createClass(pkg1);
    String class3 = cdb.createClass(pkg3);

    cdb.removeDataPkg(pkg1);
    cdb.hasViews(pkg2, pkg3, class3);
    cdb.hasntViews(class2);
  }
}
