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
package org.polarsys.capella.test.diagram.common.ju.wrapper.sequence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.sequence.business.internal.tool.ToolCommandBuilder;
import org.eclipse.sirius.diagram.sequence.description.tool.OperandCreationTool;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.polarsys.capella.core.data.interaction.CombinedFragment;
import org.polarsys.capella.core.data.interaction.InteractionOperatorKind;
import org.polarsys.capella.test.diagram.common.ju.wrapper.AbstractToolWrapper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.NodeCreationDescriptionWrapper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;

/**
 * Wrapper for Operand<br>
 * To be completed and improved
 */

public class OperandCreationDescriptionWrapper extends NodeCreationDescriptionWrapper {

  public OperandCreationDescriptionWrapper(AbstractToolDescription tool, IDiagramCommandFactory commandFactory) {
    super(tool, commandFactory);
  }

  /**
   * @see org.polarsys.capella.test.common.ju.tool.AbstractCmdToolWrapper#createCommand()
   */
  @Override
  public Command createCommand() {

    Command cmd = UnexecutableCommand.INSTANCE;

    if (isContextOk()) {
      OperandCreationTool tool = (OperandCreationTool) _tool;

      DDiagramElementContainer container = (DDiagramElementContainer) _arguments.get(ArgumentType.CONTAINER_VIEW);

      EventEnd startingEndPredecessor = (EventEnd) _arguments.get(ArgumentType.STARTINGENDPREDECESSOR);
      EventEnd finishingEndPredecessor = (EventEnd) _arguments.get(ArgumentType.FINISHINGENDPREDECESSOR);
      cmd = ToolCommandBuilder.buildCreateOperantCommandFromTool(container, tool, startingEndPredecessor, finishingEndPredecessor);

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
      Collections.addAll(list, new AbstractToolWrapper.ArgumentData(ArgumentType.CONTAINER_VIEW, DiagramPackage.Literals.ABSTRACT_DNODE),
          new AbstractToolWrapper.ArgumentData(ArgumentType.STARTINGENDPREDECESSOR, null), new AbstractToolWrapper.ArgumentData(
              ArgumentType.FINISHINGENDPREDECESSOR, null));
      ret = Collections.unmodifiableList(list);
    } else {
      ret = _argumentTypes;
    }

    return ret;
  }

  @Override
  public boolean isContextOk() {

    CombinedFragment currentCF = (CombinedFragment) ((DSemanticDecorator) _arguments.get(ArgumentType.CONTAINER_VIEW)).getTarget();

    if (currentCF.getOperator().equals(InteractionOperatorKind.LOOP)) {
      return false;
    }

    return true;
  }

}
