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
package org.polarsys.capella.test.table.ju.state.contextual;

import org.eclipse.sirius.table.metamodel.table.DTable;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

public class ModeTableTestCase extends TableContextualStateModeFramework {
  @Override
  public void test() throws Exception {
    init();
    DTable table = createTable(context, STATE_MACHINE_2, IDiagramNameConstants.CONTEXTUAL_STATE_AND_MODE_MATRIX);
    openTable(context, table.getName());

    showAllColumns(context, table);
    deleteCellValue(table, _sf1, _mode1);
    createCellValue(table, _sf1, _mode1);
    createCellValue(table, _sf1, _mode2);
    deleteCellValue(table, _sf1, _mode2);
    
    createCellValue(table, _cp1, _mode1);
    deleteCellValue(table, _cp1, _mode1);
    
    createCellValue(table, _fc1, _mode1);
    deleteCellValue(table, _fc1, _mode1); 
  }
}
