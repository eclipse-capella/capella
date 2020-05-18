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
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram.ExchangeItemType;
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram.ReferenceType;
import org.polarsys.capella.test.diagram.tools.ju.model.CDBCommunication;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.CDBProjectSettings;

public class DragAndDropTest extends CDBCommunication {

  public DragAndDropTest(CDBProjectSettings settings) {
    super(settings);
  }

  @Override
  protected void testCDB() {
    cdb = CDBDiagram.createDiagram(context, settings.DATAPKG);
    testDragAndDrop();
  }

  protected void testDragAndDrop() {
    String diagramId = cdb.getDiagramId();
    CDBDiagram cdbSetup = CDBDiagram.createDiagram(context, settings.DATAPKG);

    String id1 = cdbSetup.createDataType(DataType.NUMERIC_TYPE);
    cdb.dragAndDropDataTypeFromExplorer(id1, diagramId);

    String id2 = cdbSetup.createDataType(DataType.BOOLEAN_TYPE);
    cdb.dragAndDropBooleanTypeFromExplorer(id2, diagramId);

    String id3 = cdbSetup.createClass();
    cdb.dragAndDropClassFromExplorer(id3, diagramId);

    String id4 = cdbSetup.createCollection();
    cdb.dragAndDropCollectionFromExplorer(id4, diagramId);

    String id5 = cdbSetup.createDataType(DataType.ENUMERATION);
    cdb.dragAndDropEnumerationFromExplorer(id5, diagramId);

    String id6 = cdbSetup.createConstraint("c1");
    cdb.dragAndDropConstraintFromExplorer(id6, diagramId);

    String id7 = cdbSetup.createDataPackage();
    cdb.dragAndDropDataPkgFromExplorer(id7, diagramId);

    String id8 = cdbSetup.createNodeDataType(DataType.UNIT);
    cdb.dragAndDropUnitFromExplorer(id8, diagramId);

    String id9 = cdb.createClass();
    String dataPkgId1 = cdb.createDataPackage();
    cdb.dragAndDropClassIntoDataPkg(id9, dataPkgId1);

    String id10 = cdb.createCollection();
    cdb.dragAndDropCollectionIntoDataPkg(id10, dataPkgId1);

    String id11 = cdb.createDataType(DataType.NUMERIC_TYPE);
    cdb.dragAndDropDataTypeIntoDataPkg(id11, dataPkgId1);
    cdb.dragAndDropDataTypeIntoDataPkg(id1, dataPkgId1);

    String dataPkgId2 = cdb.createDataPackage();
    cdb.dragAndDropDataPkgIntoDataPkg(dataPkgId1, dataPkgId2);

    String id12 = cdbSetup.createReference(ReferenceType.BINARY_EXPRESSION);
    cdb.dragAndDropDataValueFromExplorer(id12, diagramId);
    cdb.dragAndDropDataValueIntoClass(id12, id3);
    cdb.dragAndDropUnitIntoDataPkg(id8, dataPkgId2);

    String id13 = cdbSetup.createInterface();
    cdb.dragAndDropInterfaceFromExplorer(id13, diagramId);

    String id14 = cdbSetup.createExchangeItem(ExchangeItemType.DATA);
    cdb.dragAndDropExchangeItemFromExplorer(id14, diagramId);

    String id15 = cdbSetup.createInterfacePackage();
    cdb.dragAndDropInterfacePkgFromExplorer(id15, diagramId);

    String id16 = cdbSetup.createInterface();
    cdb.dragAndDropInterfaceIntoInterfacePkg(id16, id15);

    String id17 = cdbSetup.createInterfacePackage();
    String id18 = cdbSetup.createExchangeItemNode(id17, ExchangeItemType.EVENT);
    cdb.dragAndDropInterfacePkgIntoInterfacePkg(id17, id15);
    cdb.dragAndDropExchangeItemIntoInterfacePkg(id18, id17);
  }
}
