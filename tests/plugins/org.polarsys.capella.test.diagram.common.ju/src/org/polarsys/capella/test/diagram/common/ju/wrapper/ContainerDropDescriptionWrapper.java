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
package org.polarsys.capella.test.diagram.common.ju.wrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.description.tool.ContainerDropDescription;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;

public class ContainerDropDescriptionWrapper extends AbstractSingleSelectionWrapper {

  public ContainerDropDescriptionWrapper(AbstractToolDescription tool, IDiagramCommandFactory commandFactory) {
    super(tool, commandFactory);
  }

  /**
   * @see org.polarsys.capella.test.common.ju.tool.AbstractCmdToolWrapper#createCommand()
   */
  @Override
  public Command createCommand() {

    Command cmd = UnexecutableCommand.INSTANCE;

    if (isContextOk()) {
      ContainerDropDescription tool = (ContainerDropDescription) _tool;

      DragAndDropTarget container = (DragAndDropTarget) _arguments.get(ArgumentType.CONTAINER_VIEW);
      // 2 versions of buildDropInContainerCommandFromTool
      Object droppedElt = _arguments.get(ArgumentType.DROPPEDELEMENT);

      // if the dropped element is given as a diagram element
      if (droppedElt instanceof DDiagramElement) {
        DDiagramElement elementToDrop = (DDiagramElement) droppedElt;
        cmd = _diagramCommandFactory.buildDropInContainerCommandFromTool(container, elementToDrop, tool);
      } else {

        // if the dropped element is given as the target of a diagram element
        EObject elementToDrop = (EObject) droppedElt;
        cmd = _diagramCommandFactory.buildDropInContainerCommandFromTool(container, elementToDrop, tool);
      }

    }
    return cmd;
  }

  /**
   * @see org.polarsys.capella.test.common.ju.tool.AbstractCmdToolWrapper#getArgumentTypes()
   */
  @Override
  public List<ArgumentData> getArgumentTypes() {

    List<ArgumentData> ret = null;

    if (null == _argumentTypes) {
      List<ArgumentData> list = new ArrayList<ArgumentData>();
      Collections.addAll(list, new AbstractToolWrapper.ArgumentData(ArgumentType.CONTAINER_VIEW, null),
          new AbstractToolWrapper.ArgumentData(ArgumentType.DROPPEDELEMENT, null));
      ret = Collections.unmodifiableList(list);
    } else {
      ret = _argumentTypes;
    }

    return ret;
  }

  /**
   * @see org.polarsys.capella.test.common.ju.tool.AbstractCmdToolWrapper#isContextOk()
   */
  @Override
  public boolean isContextOk() {
    return true;
  }

}
