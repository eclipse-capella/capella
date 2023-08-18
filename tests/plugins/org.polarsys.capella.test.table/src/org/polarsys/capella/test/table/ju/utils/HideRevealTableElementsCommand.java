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
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 * Hide/Reveal the table elements (lines/columns) of a table
 */
public abstract class HideRevealTableElementsCommand extends AbstractTableActionCommand {

  /** The visible elements selected from the check box tree during manual test */
  private final List<DTableElement> _newVisibleElements;
  /** Attribute DLINE__VISIBLE or DCOLUMN__VISIBLE */
  private final EAttribute _attribute;

  /**
   * @param table_p
   * @param attribute_p
   */
  public HideRevealTableElementsCommand(DTable table_p, EAttribute attribute_p) {
    super(table_p);
    _newVisibleElements = getVisibleElements();
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
      final boolean visible = _newVisibleElements.contains(element);
      // XOR operator
      // a ^ b => true if a and b are true or false
      // simultanously

      // Here, we want to update visibility only if it has
      // changed
      if (visible ^ isVisibleElement(element)) {
        Command cmd = _tableCommandFactory.buildSetValue(element, _attribute.getName(), visible);
        executeCmd(cmd);
      }
    }
  }

  /**
   * Executes the command
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
    if (_newVisibleElements == null || _newVisibleElements.isEmpty()) {
      return false;
    }
    for (DTableElement element : _newVisibleElements) {
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

  /**
   * Checks the visibility property of the element
   * @param element_p
   * @return
   */
  private boolean isVisibleElement(DTableElement element_p) {
    return (element_p instanceof DLine) ? ((DLine) element_p).isVisible() : (element_p instanceof DColumn) ? ((DColumn) element_p).isVisible() : false;
  }

  /**
   * Gets the visible elements. Simulated Check box tree wizard where the elements are selected to be visible
   * @return
   */
  public abstract List<DTableElement> getVisibleElements();
}
