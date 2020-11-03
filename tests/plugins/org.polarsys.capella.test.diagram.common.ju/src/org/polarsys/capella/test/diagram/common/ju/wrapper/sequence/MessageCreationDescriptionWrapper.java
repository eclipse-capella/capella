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
package org.polarsys.capella.test.diagram.common.ju.wrapper.sequence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.MappingHelper;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.sequence.business.internal.tool.ToolCommandBuilder;
import org.eclipse.sirius.diagram.sequence.description.tool.MessageCreationTool;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.polarsys.capella.test.diagram.common.ju.wrapper.AbstractToolWrapper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.EdgeCreationDescriptionWrapper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;

/**
 */
public class MessageCreationDescriptionWrapper extends EdgeCreationDescriptionWrapper {

  /**
   * @param tool_p
   * @param commandFactory_p
   */
  public MessageCreationDescriptionWrapper(AbstractToolDescription tool_p, IDiagramCommandFactory commandFactory_p) {
    super(tool_p, commandFactory_p);
  }

  @Override
  public Command createCommand() {
    Command cmd = UnexecutableCommand.INSTANCE;
    EdgeTarget source = (EdgeTarget) _arguments.get(ArgumentType.SOURCE);
    EdgeTarget target = (EdgeTarget) _arguments.get(ArgumentType.TARGET);
    EventEnd startingEndPredecessor = (EventEnd) _arguments.get(ArgumentType.STARTINGENDPREDECESSOR);
    EventEnd finishingEndPredecessor = (EventEnd) _arguments.get(ArgumentType.FINISHINGENDPREDECESSOR);

    MessageCreationTool tool = (MessageCreationTool) _tool;

    cmd = ToolCommandBuilder.buildCreateMessageCommand(source, target, tool, startingEndPredecessor,
        finishingEndPredecessor);

    return cmd;

  }

  /**
   * @see org.polarsys.capella.test.diagram.common.ju.wrapper.EdgeCreationDescriptionWrapper#isContextOk()
   */
  @Override
  public boolean isContextOk() {
    boolean ret = super.isContextOk();
    if (!ret) {
      EdgeCreationDescription tool = (EdgeCreationDescription) _tool;
      DMappingBased source = (DMappingBased) _arguments.get(ArgumentType.SOURCE);
      DMappingBased target = (DMappingBased) _arguments.get(ArgumentType.TARGET);

      boolean sourceOk = false;
      boolean targetOk = false;
      AbstractNodeMapping sourceMapping = (AbstractNodeMapping) source.getMapping();
      if (sourceMapping != null) {
        Iterator<EdgeMapping> iterMappings = tool.getEdgeMappings().iterator();
        while (iterMappings.hasNext()) {
          EdgeMapping edgeMapping = iterMappings.next();
          if (isMappingOk(edgeMapping.getSourceMapping(), source)) {
            if (target == null) {
              return true;
            }
            sourceOk = true;
            if (isMappingOk(edgeMapping.getTargetMapping(), target)) {
              return true;
            }
          } else if ((target != null) && isMappingOk(edgeMapping.getTargetMapping(), target)) {
            targetOk = true;
          }
        }
      }
      ret = sourceOk && targetOk;
    }
    return ret;
  }

  /**
   * Checks whether the mappingBased_p diagram element (Actual Mapping or BorderedNodeMapping) contains source/target
   * mappings of the edge mapping of the tool
   * 
   * @param mappings_p
   *          source/target mappings of the edge mapping of the tool
   * @param mappingBased_p
   *          source or target of the edge
   * @return true if mapping exists
   */
  private boolean isMappingOk(EList<DiagramElementMapping> mappings_p, DMappingBased mappingBased_p) {
    for (DiagramElementMapping mapping : mappings_p) {
      if (mappingBased_p instanceof DNode) {
        DNode viewNodeContainer = (DNode) mappingBased_p;
        final NodeMapping nodeMapping = viewNodeContainer.getActualMapping();
        if (nodeMapping != null) {
          if (nodeMapping.equals(mapping)) {
            return true;
          }
          if (MappingHelper.getAllBorderedNodeMappings(nodeMapping).contains(mapping)) {
            return true;
          }
        }
      }

    }
    return false;
  }

  /**
   * @see org.polarsys.capella.test.common.ju.tool.AbstractCmdToolWrapper #getArgumentTypes()
   */
  @Override
  public List<ArgumentData> getArgumentTypes() {

    List<ArgumentData> ret = null;

    if (null == _argumentTypes) {
      List<ArgumentData> list = new ArrayList<ArgumentData>();
      Collections.addAll(list,
          new AbstractToolWrapper.ArgumentData(ArgumentType.SOURCE, DiagramPackage.Literals.EDGE_TARGET),
          new AbstractToolWrapper.ArgumentData(ArgumentType.TARGET, DiagramPackage.Literals.EDGE_TARGET),
          new AbstractToolWrapper.ArgumentData(ArgumentType.STARTINGENDPREDECESSOR, null),
          new AbstractToolWrapper.ArgumentData(ArgumentType.FINISHINGENDPREDECESSOR, null));
      ret = Collections.unmodifiableList(list);
    } else {
      ret = _argumentTypes;
    }

    return ret;
  }
}
