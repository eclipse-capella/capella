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
package org.polarsys.capella.test.table.ju.utils;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 * Wrapper for hide line/column from a table
 */
public class HideTableElementCommand extends AbstractTableActionCommand {

  /** The table element to hide */
  private final DTableElement _tableElement;
  /** The attribute (visible) of the table element */
  private final EAttribute _attribute;

  /**
   * @param table_p
   * @param tableElement_p
   * @param attribute_p
   */
  public HideTableElementCommand(DTable table_p, DTableElement tableElement_p, EAttribute attribute_p) {
    super(table_p);
    _tableElement = tableElement_p;
    _attribute = attribute_p;
  }

  /**
   * @see org.polarsys.capella.test.common.internal.tool.table.AbstractTableActionCommand#execute()
   */
  @Override
  public void execute() {
    Assert.isNotNull(_tableElement);
    Assert.isTrue(isContextOk());
    final Command cmd = _tableCommandFactory.buildSetValue(_tableElement, _attribute.getName(), Boolean.FALSE);
    TestHelper.getExecutionManager(_tableElement).execute(new AbstractReadWriteCommand() {

      @Override
      public void run() {
        cmd.execute();
      }
    });
  }

  /**
   * @see org.polarsys.capella.test.common.internal.tool.table.AbstractTableActionCommand#isContextOk()
   */
  @Override
  public boolean isContextOk() {
    boolean ret = false;
    if (_tableElement == null) {
      return false;
    }
    for (EAttribute attr : _tableElement.eClass().getEAllAttributes()) {
      if (_attribute.equals(attr)) {
        ret = true;
        break;
      }
    }
    return ret;
  }
}
