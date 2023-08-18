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
package org.polarsys.capella.test.table.ju.function;

import org.eclipse.sirius.table.metamodel.table.DTable;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

public class FunctionTableTestCase extends FunctionTableTestFramework {

  @Override
  public void test() throws Exception {
    init();
    createTable(context, diagramID, IDiagramNameConstants.SYSTEM_FUNCTIONS_OPERATIONAL_ACTIVITIES_DIAGRAM_NAME);
    DTable table = openTable(context, tableName);

    createCellValue(table, _sf1, _oa1);
    createCellValue(table, _sf11, _oa11);
    createCellValue(table, _sf2, _oa2);
    createCellValue(table, _sf21, _oa21);
    createCellValue(table, _sf3, _oa3);
  }
}
