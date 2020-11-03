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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.description.CreateLineTool;
import org.eclipse.sirius.table.metamodel.table.description.CreateTool;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.polarsys.capella.test.diagram.common.ju.wrapper.AbstractToolWrapper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;

/**
 * Wrapper for Create Line Tool
 */
public class CreateLineToolWrapper extends AbstractTableToolWrapper {

  /**
   * @param tool_p
   * @param commandFactory_p
   */
  public CreateLineToolWrapper(AbstractToolDescription tool_p, ITableCommandFactory commandFactory_p) {
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
      EObject semanticContainer = (EObject) _arguments.get(ArgumentType.CONTAINER);

      EClass toolEClass = _tool.eClass();
      if (toolEClass == DescriptionPackage.Literals.CREATE_LINE_TOOL) {
        DLine line = (DLine) container;
        cmd = _tableCommandFactory.buildCreateLineCommandFromTool(line, semanticContainer, (CreateTool) _tool);
      }
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
      Collections.addAll(list, new AbstractToolWrapper.ArgumentData(ArgumentType.CONTAINER_VIEW, TablePackage.Literals.LINE_CONTAINER),
          new AbstractToolWrapper.ArgumentData(ArgumentType.CONTAINER, null));
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
      List<CreateLineTool> tools = new ArrayList<CreateLineTool>(desc.getOwnedCreateLine());
      boolean shouldCreate = false;
      LineMapping currMapping = ((CreateLineTool) _tool).getMapping();
      if (desc.getOwnedLineMappings().contains(currMapping)) {
        shouldCreate = true;
      }
      for (LineMapping mapping : desc.getOwnedLineMappings()) {
        if (mapping != null) {
          tools.addAll(mapping.getCreate());
        }
        if (mapping.getAllSubLines().contains(currMapping)) {
          shouldCreate = true;
        }
      }
      ret = shouldCreate;
    }
    return ret;
  }

  private DTable getTable(EObject container_p) {
    if (container_p instanceof DTable) {
      return (DTable) container_p;
    }
    return getTable(container_p.eContainer());
  }

}
