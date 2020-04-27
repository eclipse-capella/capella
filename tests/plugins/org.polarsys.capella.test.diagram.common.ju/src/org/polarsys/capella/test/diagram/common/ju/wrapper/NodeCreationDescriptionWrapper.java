/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.ContainerMappingHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.MappingHelper;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;

public class NodeCreationDescriptionWrapper extends AbstractSingleSelectionWrapper {

  public NodeCreationDescriptionWrapper(AbstractToolDescription tool, IDiagramCommandFactory commandFactory) {
    super(tool, commandFactory);
  }

  /**
   * @see org.polarsys.capella.test.common.ju.tool.AbstractCmdToolWrapper#createCommand()
   */
  @Override
  public Command createCommand() {

    Command cmd = UnexecutableCommand.INSTANCE;

    if (isContextOk()) {
      NodeCreationDescription tool = (NodeCreationDescription) _tool;

      EObject container = (EObject) _arguments.get(ArgumentType.CONTAINER_VIEW);

      if (container instanceof DNodeContainer) {
        cmd = _diagramCommandFactory.buildCreateNodeCommandFromTool((DNodeContainer) container, tool);
      } else if (container instanceof DNodeList) {
        cmd = _diagramCommandFactory.buildCreateNodeCommandFromTool((DNodeList) container, tool);
      } else if (container instanceof DNode) {
        cmd = _diagramCommandFactory.buildCreateNodeCommandFromTool((DNode) container, tool);
      } else {
        cmd = _diagramCommandFactory.buildCreateNodeCommandFromTool((DDiagram) container, tool);
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
      NodeCreationDescription tool = (NodeCreationDescription) _tool;

      if (container instanceof DDiagramElementContainer) {
        DDiagramElementContainer viewNodeContainer = (DDiagramElementContainer) container;
        final ContainerMapping containerMapping = viewNodeContainer.getActualMapping();
        if (containerMapping != null) {
          final Iterator<NodeMapping> it = tool.getNodeMappings().iterator();
          while (it.hasNext()) {
            final NodeMapping curMapping = it.next();
            if (ContainerMappingHelper.getAllNodeMappings(containerMapping).contains(curMapping)) {
              shouldCreate = true;
              break;
            } else if (MappingHelper.getAllBorderedNodeMappings(containerMapping).contains(curMapping)) {
              shouldCreate = true;
              break;
            }
          }
        }
      } else if (container instanceof DNode) {
        DNode viewNodeContainer = (DNode) container;
        final NodeMapping nodeMapping = viewNodeContainer.getActualMapping();
        if (nodeMapping != null) {
          final Iterator<NodeMapping> it = tool.getNodeMappings().iterator();
          while (it.hasNext()) {
            final NodeMapping curMapping = it.next();
            if (MappingHelper.getAllBorderedNodeMappings(nodeMapping).contains(curMapping)) {
              shouldCreate = true;
              break;
            }
          }
          if (tool.getExtraMappings().contains(nodeMapping)) {
            shouldCreate = true;
          }
          // special case to create sub entities in OEBD diagram
          EObject target = ((DNode) container).getTarget();
          if (target instanceof Entity) {
            shouldCreate = true;
          }
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
