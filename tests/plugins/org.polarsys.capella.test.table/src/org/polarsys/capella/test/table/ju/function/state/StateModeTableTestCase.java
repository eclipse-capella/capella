/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.table.ju.function.state;

import org.eclipse.sirius.table.metamodel.table.DTable;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

public class StateModeTableTestCase extends TableStateModeFramework {
  @Override
  public void test() throws Exception {
    init();
    DTable table = createTable(context, SYSTEM_ANALYSIS, IDiagramNameConstants.STATE_AND_MODE_MATRIX);
    openTable(context, table.getName());

    showAllColumns(context, table);
    deleteCellValue(table, _state1, _sf1);
    createCellValue(table, _state1, _sf1);
    createCellValue(table, _state2, _sf1);
    deleteCellValue(table, _state2, _sf1);

    deleteCellValue(table, _state11, _sf11);
    createCellValue(table, _state11, _sf11);

    deleteCellValue(table, _state2, _sf2);
    createCellValue(table, _state2, _sf2);

    createCellValue(table, _state1, _cp1);
    createCellValue(table, _state2, _cp2);
    deleteCellValue(table, _state1, _cp1);
    deleteCellValue(table, _state2, _cp2);
    createCellValue(table, _mode1, _cp1);
    deleteCellValue(table, _mode1, _cp1);

    createCellValue(table, _state1, _fc1);
    deleteCellValue(table, _state1, _fc1);
    createCellValue(table, _mode1, _fc1);
    deleteCellValue(table, _mode1, _fc1);
  }
}
