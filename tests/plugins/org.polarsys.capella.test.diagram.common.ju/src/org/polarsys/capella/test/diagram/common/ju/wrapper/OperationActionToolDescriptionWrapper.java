/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
import java.util.Collection;
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
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.tools.api.interpreter.InterpreterRegistry;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.tool.OperationAction;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.polarsys.capella.core.sirius.analysis.tool.StringUtil;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;

/**
 */
public class OperationActionToolDescriptionWrapper extends AbstractCommonToolWrapper {

  public OperationActionToolDescriptionWrapper(OperationAction tool, IDiagramCommandFactory commandFactory) {
    super(tool, commandFactory);
  }

  /**
   * @see org.polarsys.capella.test.common.ju.tool.AbstractCmdToolWrapper#createCommand()
   */
  @SuppressWarnings("unchecked")
  @Override
  public Command createCommand() {

    Command cmd = UnexecutableCommand.INSTANCE;

    if (isContextOk() && isPreconditionOk()) {
      Collection<DSemanticDecorator> col = (Collection<DSemanticDecorator>) _arguments.get(ArgumentType.COLLECTION);
      cmd = _diagramCommandFactory.buildOperationActionFromTool((OperationAction) _tool, col);
    }
    return cmd;
  }

  public boolean isPreconditionOk() {
    final String precondition = _tool.getPrecondition();
    if ((precondition != null) && !StringUtil.isEmpty(precondition)) {
      
      Collection<DSemanticDecorator> col = (Collection<DSemanticDecorator>) _arguments.get(ArgumentType.COLLECTION);
      EObject semantic = ((DDiagramElement)col.iterator().next()).getTarget();
       InterpreterRegistry interpreterRegistry = SiriusPlugin.getDefault().getInterpreterRegistry();
      final IInterpreter interpreter = interpreterRegistry.getInterpreter(semantic);

      interpreter.setVariable("views", col);
      interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, ((DDiagramElement)col.iterator().next()).getParentDiagram());

      RuntimeLoggerInterpreter decorate = RuntimeLoggerManager.INSTANCE.decorate(interpreter);
      EAttribute abstractToolDescription_Precondition = ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition();

      final boolean result = decorate.evaluateBoolean(semantic, _tool, abstractToolDescription_Precondition);
      if (!result) {
        return false;
      }

      interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
      interpreter.unSetVariable("views");
    }
    return true;
  }

  /**
   * @see org.polarsys.capella.test.common.ju.tool.AbstractCmdToolWrapper#getArgumentTypes()
   */
  @Override
  public List<ArgumentData> getArgumentTypes() {

    List<ArgumentData> ret = null;

    if (null == _argumentTypes) {
      List<ArgumentData> list = new ArrayList<ArgumentData>();
      Collections.addAll(list, new AbstractToolWrapper.ArgumentData(ArgumentType.COLLECTION, ViewpointPackage.Literals.DSEMANTIC_DECORATOR));
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
      // Do Nothing.
      // All is done by the command with such kind of tools;
    }

    return ret;
  }

}
