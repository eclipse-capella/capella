/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.eclipse.sirius.table.metamodel.table.DTargetColumn;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.description.ColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.DeleteColumnTool;
import org.eclipse.sirius.table.metamodel.table.description.DeleteLineTool;
import org.eclipse.sirius.table.metamodel.table.description.DeleteTool;
import org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.table.metamodel.table.description.TableMapping;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.polarsys.capella.test.diagram.common.ju.wrapper.AbstractToolWrapper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;

/**
 * Wrapper for Delete Line/Column Tool
 */
public class DeleteTableElementToolWrapper extends AbstractTableToolWrapper {

  /**
   * @param tool_p
   * @param commandFactory_p
   */
  public DeleteTableElementToolWrapper(AbstractToolDescription tool_p, ITableCommandFactory commandFactory_p) {
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

      DTableElement tableElement = (DTableElement) container;
      cmd = _tableCommandFactory.buildDeleteTableElement(tableElement);
    }
    return cmd;
  }

  /**
   * @see org.polarsys.capella.test.diagram.common.ju.wrapper.AbstractToolWrapper#getArgumentTypes()
   */
  @Override
  public List<ArgumentData> getArgumentTypes() {
    List<ArgumentData> ret = null;

    if (null == _argumentTypes) {
      List<ArgumentData> list = new ArrayList<ArgumentData>();
      Collections.addAll(list, new AbstractToolWrapper.ArgumentData(ArgumentType.CONTAINER_VIEW, TablePackage.Literals.DTABLE_ELEMENT));
      ret = Collections.unmodifiableList(list);
    } else {
      ret = _argumentTypes;
    }

    return ret;
  }

  /**
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
      DTable table = getTable(container);
      TableDescription desc = table.getDescription();

      boolean shouldCreate = false;
      TableMapping currMapping = null;
      if (_tool instanceof DeleteLineTool) {
        currMapping = ((DeleteLineTool) _tool).getMapping();
        if (desc.getOwnedLineMappings().contains(currMapping)) {
          shouldCreate = true;
        }
        for (LineMapping mapping : desc.getOwnedLineMappings()) {
          if (mapping.getAllSubLines().contains(currMapping)) {
            shouldCreate = true;
            break;
          }
        }
      } else if (_tool instanceof DeleteColumnTool) {
        if (desc instanceof CrossTableDescription) {
          currMapping = ((DeleteColumnTool) _tool).getMapping();
          CrossTableDescription crossDesc = (CrossTableDescription) desc;
          if (crossDesc.getOwnedColumnMappings().contains(currMapping)) {
            shouldCreate = true;
          }
        }
      }
      // This is going to happen through destroying the semantic element
      if (!shouldCreate && getDeleteTool((DTableElement) container) == null) {
        shouldCreate = true;
      }
      ret = shouldCreate;
    }
    return ret;
  }

  /**
   * Gets the delete tool for the table element (line/column)
   * @param element
   * @return
   */
  private DeleteTool getDeleteTool(final DTableElement element) {
    DeleteTool result = null;
    if (element instanceof DLine) {
      result = ((DLine) element).getOriginMapping().getDelete();
    } else if (element instanceof DTargetColumn) {
      final ColumnMapping columnMapping = ((DTargetColumn) element).getOriginMapping();
      if (columnMapping instanceof ElementColumnMapping) {
        result = ((ElementColumnMapping) columnMapping).getDelete();
      }
    }
    return result;
  }

  /**
   * Gets the table from the table element recursively
   * @param container_p
   * @return
   */
  private DTable getTable(EObject container_p) {
    if (container_p instanceof DTable) {
      return (DTable) container_p;
    }
    return getTable(container_p.eContainer());
  }

}
