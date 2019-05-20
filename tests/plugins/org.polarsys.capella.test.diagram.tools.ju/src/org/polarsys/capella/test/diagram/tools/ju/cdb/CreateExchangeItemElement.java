/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.cdb;

import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram.DataType;
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram.ExchangeItemType;
import org.polarsys.capella.test.diagram.tools.ju.model.CDBCommunication;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.CDBProjectSettings;

/**
 *
 */
public class CreateExchangeItemElement extends CDBCommunication {

  public CreateExchangeItemElement(CDBProjectSettings settings) {
    super(settings);
  }

  @Override
  protected void testCDB() {
    cdb = CDBDiagram.createDiagram(context, settings.DATAPKG);
    testCreateExchangeItemElement(ExchangeItemType.EVENT);
    testCreateExchangeItemElement(ExchangeItemType.OPERATION);
    testCreateExchangeItemElement(ExchangeItemType.FLOW);
    testCreateExchangeItemElement(ExchangeItemType.DATA);
    testCreateExchangeItemElement(ExchangeItemType.UNDEFINED);
  }

  protected void testCreateExchangeItemElement(ExchangeItemType eiType) {
    String ei1 = cdb.createNodeExchangeItem(eiType);
    String ei2 = cdb.createNodeExchangeItem(eiType);

    String type1 = cdb.createDataType(DataType.BOOLEAN_TYPE);
    String type2 = cdb.createDataType(DataType.STRING_TYPE);

    cdb.createExchangeItemElement(ei1, type1);
    cdb.createExchangeItemElement(ei2, type1);
    cdb.createExchangeItemElement(ei2, type2);
  }
}
