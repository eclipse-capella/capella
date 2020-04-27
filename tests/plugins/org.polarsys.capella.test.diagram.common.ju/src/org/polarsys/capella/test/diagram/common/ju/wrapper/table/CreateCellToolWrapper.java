/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.common.ju.wrapper.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.polarsys.capella.test.diagram.common.ju.wrapper.AbstractToolWrapper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;

/**
 * Wrapper for Create Cell Tool
 * <P>
 * Takes the value "X" for creating a link between elements in line and column
 * <P>
 * and empty string "" for deleting the link
 */
public class CreateCellToolWrapper extends AbstractTableToolWrapper {

  /**
   * @param tool_p
   * @param commandFactory_p
   */
  public CreateCellToolWrapper(AbstractToolDescription tool_p, ITableCommandFactory commandFactory_p) {
    super(tool_p, commandFactory_p);
  }

  /**
   * @see org.polarsys.capella.test.diagram.common.ju.wrapper.AbstractToolWrapper#createCommand()
   */
  @Override
  public Command createCommand() {
    Command cmd = UnexecutableCommand.INSTANCE;

    if (isContextOk()) {

      EObject container = (EObject) _arguments.get(ArgumentType.CONTAINER_VIEW);

      EClass toolEClass = _tool.eClass();
      if (toolEClass == DescriptionPackage.Literals.CREATE_CELL_TOOL) {
        Object cellValue = _arguments.get(ArgumentType.TABLE_CELL_MASK);
        cmd = _tableCommandFactory.buildSetCellValueFromTool((DCell) container, cellValue);
      }
    }
    return cmd;
  }

  /**
   * TODO if possible refine the argument types (add enums if required)
   * @see org.polarsys.capella.test.diagram.common.ju.wrapper.AbstractToolWrapper#getArgumentTypes()
   */
  @Override
  public List<ArgumentData> getArgumentTypes() {
    List<ArgumentData> ret = null;

    if (null == _argumentTypes) {
      List<ArgumentData> list = new ArrayList<ArgumentData>();
      Collections.addAll(list, new AbstractToolWrapper.ArgumentData(ArgumentType.CONTAINER_VIEW, TablePackage.Literals.DCELL),
          new AbstractToolWrapper.ArgumentData(ArgumentType.TABLE_CELL_MASK));
      ret = Collections.unmodifiableList(list);
    } else {
      ret = _argumentTypes;
    }

    return ret;
  }

  /**
   * TODO refine the check
   * @see org.polarsys.capella.test.diagram.common.ju.wrapper.AbstractToolWrapper#isContextOk()
   */
  @Override
  public boolean isContextOk() {
    boolean ret = true;

    if (!isArgumentsAreSet()) {
      ret = false;
    }

    // Now, let's perform job
    if (ret) {

      EObject container = (EObject) _arguments.get(ArgumentType.CONTAINER_VIEW);

      boolean shouldCreate = false;
      if (container instanceof DCell) {
        DCell cell = (DCell) container;
        if (TableHelper.getCreateCellTool(cell.getLine(), cell.getColumn()) != null) {
          shouldCreate = true;
        }
      }
      ret = shouldCreate;
    }
    return ret;
  }

}
