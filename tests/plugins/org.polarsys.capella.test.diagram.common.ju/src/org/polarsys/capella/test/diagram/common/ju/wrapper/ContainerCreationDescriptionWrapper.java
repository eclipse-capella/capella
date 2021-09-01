/*******************************************************************************
 * Copyright (c) 2006, 2021 THALES GLOBAL SERVICES.
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

import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.model.business.internal.helper.MappingHelper;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;

public class ContainerCreationDescriptionWrapper extends AbstractSingleSelectionWrapper {

  public ContainerCreationDescriptionWrapper(AbstractToolDescription tool, IDiagramCommandFactory commandFactory) {
    super(tool, commandFactory);
  }

  /**
   * @see org.polarsys.capella.test.common.ju.tool.AbstractCmdToolWrapper#createCommand()
   */
  @Override
  public Command createCommand() {

    Command cmd = UnexecutableCommand.INSTANCE;

    if (isContextOk()) {
      ContainerCreationDescription tool = (ContainerCreationDescription) _tool;

      EObject container = (EObject) _arguments.get(ArgumentType.CONTAINER_VIEW);

      if (container instanceof DNodeContainer) {
        cmd = _diagramCommandFactory.buildCreateContainerCommandFromTool((DNodeContainer) container, tool);
      } else {
        cmd = _diagramCommandFactory.buildCreateContainerCommandFromTool((DDiagram) container, tool);
      }

      if (!UnexecutableCommand.INSTANCE.equals(cmd)) {
        changeCmdContext(cmd);
      }
    }

    return cmd;
  }

  /**
   * @see org.polarsys.capella.test.common.ju.tool.AbstractCmdToolWrapper#isContextOk()
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
      ContainerCreationDescription tool = (ContainerCreationDescription) _tool;

      if (container instanceof DDiagramElementContainer) {
        DDiagramElementContainer viewNodeContainer = (DDiagramElementContainer) container;
        final ContainerMapping containerMapping = viewNodeContainer.getActualMapping();
        if (containerMapping != null) {
          final Iterator<ContainerMapping> it = tool.getContainerMappings().iterator();
          shouldCreate = false;
          while (it.hasNext()) {
            final ContainerMapping curMapping = it.next();
            if (MappingHelper.getAllNodeMappings(containerMapping).contains(curMapping)) {
              shouldCreate = true;
              break;
            } else if (MappingHelper.getAllContainerMappings(containerMapping).contains(curMapping)) {
              shouldCreate = true;
              break;
            }
          }
        } else { // No mapping
          shouldCreate = true;
        }
      } else if (container instanceof DDiagram) {
        // FIXME DDiagram case: to check, as previous case, must be done to be coherent
        shouldCreate = true;
      }
      ret = shouldCreate;
    }

    return ret;
  }

}
