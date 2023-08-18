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

import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 * Shows all the table elements (lines/columns) in a table
 */
public class ShowAllTableElementsCommand extends AbstractTableActionCommand {

  /** The attribute (visible) for the table element */
  private final EAttribute _attribute;

  /**
   * @param table_p
   * @param attribute_p
   */
  public ShowAllTableElementsCommand(DTable table_p, EAttribute attribute_p) {
    super(table_p);
    _attribute = attribute_p;
  }

  /**
   * @see org.polarsys.capella.test.common.internal.tool.table.AbstractTableActionCommand#execute()
   */
  @SuppressWarnings("boxing")
  @Override
  public void execute() {
    Assert.isTrue(isContextOk());
    for (final DTableElement element : getAllElements()) {
      Command cmd = _tableCommandFactory.buildSetValue(element, _attribute.getName(), true);
      executeCmd(cmd);
    }
  }

  /**
   * Executes the transactional command
   * @param cmd_p
   */
  private void executeCmd(final Command cmd_p) {
    TestHelper.getExecutionManager(_table).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        cmd_p.execute();
      }
    });
  }

  /**
   * @see org.polarsys.capella.test.common.internal.tool.table.AbstractTableActionCommand#isContextOk()
   */
  @Override
  public boolean isContextOk() {
    boolean ret = false;
    List<DTableElement> allElements = getAllElements();
    if (allElements == null || allElements.isEmpty()) {
      return false;
    }
    for (DTableElement element : allElements) {
      for (EAttribute attr : element.eClass().getEAllAttributes()) {
        if (_attribute.equals(attr)) {
          ret = true;
          break;
        }
      }
    }
    return ret;
  }

  /**
   * Gets all the elements dynamically (lines/columns) based on the attribute
   * @return
   */
  private List<DTableElement> getAllElements() {
    return getAllElements(_attribute);
  }
}
