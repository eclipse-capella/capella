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
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram.ExchangeItemType;
import org.polarsys.capella.test.diagram.tools.ju.model.CDBCommunication;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.CDBProjectSettings;

/**
 *
 */
public class CreateExchangeItemGroup extends CDBCommunication {

  public CreateExchangeItemGroup(CDBProjectSettings settings) {
    super(settings);
  }

  @Override
  protected void testCDB() {
    cdb = CDBDiagram.createDiagram(context, settings.DATAPKG);
    testCreateExchangeItemGroup(ExchangeItemType.EVENT);
    testCreateExchangeItemGroup(ExchangeItemType.OPERATION);
    testCreateExchangeItemGroup(ExchangeItemType.FLOW);
    testCreateExchangeItemGroup(ExchangeItemType.DATA);
    testCreateExchangeItemGroup(ExchangeItemType.UNDEFINED);
  }

  protected void testCreateExchangeItemGroup(ExchangeItemType eiType) {
    String iPkg1 = cdb.createInterfacePackage();
    String interface1 = cdb.createInterface();
    String interface2 = cdb.createInterface(iPkg1);

    cdb.createNodeExchangeItem(eiType);
    cdb.createNodeExchangeItem(iPkg1, eiType);
    cdb.createExchangeItem(eiType);
    cdb.createExchangeItem(interface1, eiType);
    cdb.createExchangeItem(interface2, eiType);
    cdb.createExchangeItem(interface2, eiType);
    cdb.createExchangeItem(interface2, ExchangeItemType.DATA);
  }
}
