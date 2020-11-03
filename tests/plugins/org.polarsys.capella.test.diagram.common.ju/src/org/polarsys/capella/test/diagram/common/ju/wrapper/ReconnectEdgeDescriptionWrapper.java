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
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.tools.api.interpreter.InterpreterRegistry;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.polarsys.capella.core.sirius.analysis.tool.StringUtil;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;

/**
 */
public class ReconnectEdgeDescriptionWrapper extends AbstractCommonToolWrapper {

  public ReconnectEdgeDescriptionWrapper(AbstractToolDescription tool, IDiagramCommandFactory commandFactory) {
    super(tool, commandFactory);
  }

  /**
   * @see org.polarsys.capella.test.common.ju.tool.AbstractCmdToolWrapper#createCommand()
   */
  @Override
  public Command createCommand() {

    Command cmd = UnexecutableCommand.INSTANCE;

    if (isContextOk()) {

      ReconnectEdgeDescription tool = (ReconnectEdgeDescription) _tool;

      DEdge edge = (DEdge) _arguments.get(ArgumentType.EDGE);

      EdgeTarget source = (EdgeTarget) _arguments.get(ArgumentType.SOURCE);
      EdgeTarget target = (EdgeTarget) _arguments.get(ArgumentType.TARGET);

      cmd = _diagramCommandFactory.buildReconnectEdgeCommandFromTool(tool, edge, source, target);
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
      Collections.addAll(list, new AbstractToolWrapper.ArgumentData(ArgumentType.SOURCE, DiagramPackage.Literals.EDGE_TARGET), // The old EdgeTarget
          new AbstractToolWrapper.ArgumentData(ArgumentType.TARGET, DiagramPackage.Literals.EDGE_TARGET), // The new EdgeTarget
          new AbstractToolWrapper.ArgumentData(ArgumentType.EDGE, DiagramPackage.Literals.DEDGE));
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

    boolean ret = true;

    // First of all Let's check is argument types are ok.
    if (!isArgumentsAreSet()) {
      ret = false;
    }

    // Now, let's perform job
    if (ret) {

      ReconnectEdgeDescription tool = (ReconnectEdgeDescription) _tool;
      EdgeTarget source = (EdgeTarget) _arguments.get(ArgumentType.SOURCE);
      EdgeTarget target = (EdgeTarget) _arguments.get(ArgumentType.TARGET);
      DEdge edge = (DEdge) _arguments.get(ArgumentType.EDGE);

      EObject semanticSource = ((DSemanticDecorator) source).getTarget();
      EObject semanticTarget = ((DSemanticDecorator) target).getTarget();
      EObject semanticElement = ((DSemanticDecorator) edge).getTarget();

      final String precondition = tool.getPrecondition();
      if ((precondition != null) && !StringUtil.isEmpty(precondition)) {

        InterpreterRegistry interpreterRegistry = SiriusPlugin.getDefault().getInterpreterRegistry();
        final IInterpreter interpreter = interpreterRegistry.getInterpreter(semanticElement);

        interpreter.setVariable(IInterpreterSiriusVariables.SOURCE, semanticSource);
        interpreter.setVariable(IInterpreterSiriusVariables.TARGET, semanticTarget);
        interpreter.setVariable(IInterpreterSiriusVariables.ELEMENT, semanticElement);

        interpreter.setVariable(IInterpreterSiriusVariables.SOURCE_VIEW, source);
        interpreter.setVariable(IInterpreterSiriusVariables.TARGET_VIEW, target);

        RuntimeLoggerInterpreter decorate = RuntimeLoggerManager.INSTANCE.decorate(interpreter);
        EAttribute abstractToolDescription_Precondition = ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition();

        final boolean result = decorate.evaluateBoolean(semanticElement, tool, abstractToolDescription_Precondition);
        if (!result) {
          ret = false;
        }

        interpreter.unSetVariable(IInterpreterSiriusVariables.SOURCE);
        interpreter.unSetVariable(IInterpreterSiriusVariables.TARGET);
        interpreter.unSetVariable(IInterpreterSiriusVariables.ELEMENT);
      }
    }

    return ret;
  }
}
