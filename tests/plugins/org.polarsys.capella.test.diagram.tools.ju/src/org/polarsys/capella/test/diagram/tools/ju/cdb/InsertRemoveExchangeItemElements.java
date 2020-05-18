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
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.diagram.tools.ju.model.CDBCommunication;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.CDBProjectSettings;

/**
 *
 */
public class InsertRemoveExchangeItemElements extends CDBCommunication {

  public InsertRemoveExchangeItemElements(CDBProjectSettings settings) {
    super(settings);
  }

  @Override
  protected void testCDB() {
    cdb = CDBDiagram.createDiagram(context, settings.DATAPKG);
    testInsertRemoveExchangeItemElements(ExchangeItemType.EVENT);
    testInsertRemoveExchangeItemElements(ExchangeItemType.OPERATION);
    testInsertRemoveExchangeItemElements(ExchangeItemType.FLOW);
    testInsertRemoveExchangeItemElements(ExchangeItemType.DATA);
    testInsertRemoveExchangeItemElements(ExchangeItemType.UNDEFINED);
  }

  protected void testInsertRemoveExchangeItemElements(ExchangeItemType eiType) {
    String ei = cdb.createNodeExchangeItem(eiType);
    String type = cdb.createDataType(DataType.BOOLEAN_TYPE);

    String elem = cdb.createExchangeItemElement(ei, type);

    DiagramHelper.setSynchronized(cdb.getDiagram(), false);

    cdb.removeExchangeItemElement(elem, ei);
    cdb.insertExchangeItemElement(elem, ei);

    DiagramHelper.setSynchronized(cdb.getDiagram(), true);
  }
}
