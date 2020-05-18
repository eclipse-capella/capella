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
package org.polarsys.capella.test.table.ju.utils;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.command.Command;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTargetColumn;
import org.polarsys.capella.common.helpers.TransactionHelper;

/**
 * Wrapper to create a cell from an intersection
 */
public class CreateCellFromIntersectionCommand extends AbstractTableActionCommand {

  /** The table element to hide */
  private final DLine _line;
  /** The attribute (visible) of the table element */
  private final DColumn _column;

  private final String _cellValue;

  /**
   * @param table_p
   * @param tableElement_p
   * @param attribute_p
   */
  public CreateCellFromIntersectionCommand(DTable table_p, DLine line_p, DColumn column_p, String cellValue_p) {
    super(table_p);
    _line = line_p;
    _column = column_p;
    _cellValue = cellValue_p;
  }

  /**
   * @see org.polarsys.capella.test.common.internal.tool.table.AbstractTableActionCommand#execute()
   */
  @Override
  public void execute() {
    Assert.isTrue(isContextOk());
    final Command cmd = _tableCommandFactory.buildCreateCellFromTool(_line, (DTargetColumn) _column, _cellValue);
    TransactionHelper.getEditingDomain(_line).getCommandStack().execute(cmd);
  }

  /**
   * @see org.polarsys.capella.test.common.internal.tool.table.AbstractTableActionCommand#isContextOk()
   */
  @Override
  public boolean isContextOk() {
    boolean result = true;
    if ((_line == null) || (_column == null)) {
      result = false;
    }
    return result;
  }
}
