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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.tools.api.command.TableCommandFactoryService;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.InvalidArgumentException;

/**
 * Base class on Table Command for different actions viz. Hide Line, Hide Column, Show/Hide Lines, Show/Hide Columns,
 * Show All Lines and Show All Columns
 */
public abstract class AbstractTableActionCommand {

  /** The table on which the action has to be performed */
  protected final DTable _table;

  /** The table command factory */
  protected final ITableCommandFactory _tableCommandFactory;

  /**
   * @param table_p
   */
  protected AbstractTableActionCommand(DTable table_p) {
    _table = table_p;
    _tableCommandFactory = TableCommandFactoryService.getInstance().getNewProvider()
        .getCommandFactory(TransactionHelper.getEditingDomain(_table));
    // This needs to be explicity set as "TableCommandFactory" class is not initializing the commandTaskHelper in
    // constructor
    _tableCommandFactory.setModelAccessor(SiriusPlugin.getDefault().getModelAccessorRegistry()
        .getModelAccessor(TransactionHelper.getEditingDomain(_table).getResourceSet()));
  }

  /**
   * Executes the command corresponding to the action.
   * 
   * @throws InvalidArgumentException
   */
  abstract public void execute();

  /**
   * check is the context defined by the arguments allows to create the corresponding action
   * 
   * @return
   */
  abstract public boolean isContextOk();

  /**
   * Returns all the table elements (lines/columns) of the table
   * 
   * @param attribute_p
   *          the attribute to check which elements to return
   * @return list of table elements
   */
  protected List<DTableElement> getAllElements(EAttribute attribute_p) {
    List<DTableElement> list = new ArrayList<DTableElement>();
    switch (attribute_p.getFeatureID()) {
    case TablePackage.DLINE__VISIBLE:
      list.addAll(getAllLines(_table.getLines()));
      break;
    case TablePackage.DCOLUMN__VISIBLE:
      list.addAll(_table.getColumns());
      break;
    default:
      break;
    }
    return Collections.unmodifiableList(list);
  }

  /**
   * @param lines_p
   *          the lines
   * @return the list of lines inside a line container recursively
   */
  protected List<DLine> getAllLines(final List<DLine> lines_p) {
    final List<DLine> result = new ArrayList<DLine>();
    for (final DLine dLine : lines_p) {
      result.add(dLine);
      result.addAll(getAllLines(dLine.getLines()));
    }
    return result;
  }
}
