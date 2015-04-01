/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.wrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.InitInterpreterVariablesTask;
import org.eclipse.sirius.business.api.helper.task.TaskHelper;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationVariable;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.tools.api.command.ui.NoUICallback;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;
import org.polarsys.capella.core.sirius.analysis.CapellaServices;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType_Enum;

public abstract class AbstractSingleSelectionWrapper extends AbstractCommonToolWrapper {

  public AbstractSingleSelectionWrapper(AbstractToolDescription tool, IDiagramCommandFactory commandFactory) {
    super(tool, commandFactory);
  }

  /**
   * @see org.polarsys.capella.test.common.ju.tool.AbstractCmdToolWrapper#getArgumentTypes()
   */
  @Override
  public List<ArgumentData> getArgumentTypes() {

    List<ArgumentData> ret = null;

    if (null == _argumentTypes) {
      List<ArgumentData> list = new ArrayList<ArgumentData>();
      Collections.addAll(list, new AbstractToolWrapper.ArgumentData(ArgumentType_Enum.CONTAINER, DiagramPackage.Literals.ABSTRACT_DNODE),
          new AbstractToolWrapper.ArgumentData(ArgumentType_Enum.SEMANTIC_CONTAINER, null));
      ret = Collections.unmodifiableList(list);
    } else {
      ret = _argumentTypes;
    }

    return ret;
  }

  protected void changeCmdContext(Command cmd_p) {
    Assert.isNotNull(cmd_p);

    EObject container = (EObject) _arguments.get(ArgumentType_Enum.CONTAINER);
    EObject semanticContainer = (EObject) _arguments.get(ArgumentType_Enum.SEMANTIC_CONTAINER);

    SiriusCommand vpc = (SiriusCommand) cmd_p;

    // On a second hand, we modify the command context
    final IInterpreter interpreter = InterpreterUtil.getInterpreter(semanticContainer);
    final Map<AbstractVariable, Object> variables = new HashMap<AbstractVariable, Object>();

    vpc.getTasks().set(0, new InitInterpreterVariablesTask(variables, interpreter, null));

    EObject semanticModel = semanticContainer;

    ModelAccessor modelAccessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(semanticModel);
    TaskHelper taskHelper = new TaskHelper(modelAccessor, new NoUICallback());

    NodeCreationVariable ncv = null;
    ContainerViewVariable cvv = null;
    ModelOperation op = null;

    if (_tool instanceof ContainerCreationDescription) {
      ContainerCreationDescription tool = (ContainerCreationDescription) _tool;
      ncv = tool.getVariable();
      cvv = tool.getViewVariable();
      op = tool.getInitialOperation().getFirstModelOperations();
    } else if (_tool instanceof NodeCreationDescription) {
      NodeCreationDescription tool = (NodeCreationDescription) _tool;
      ncv = tool.getVariable();
      cvv = tool.getViewVariable();
      op = tool.getInitialOperation().getFirstModelOperations();
    } // does not need more, control was performed in previous step.

    variables.put(ncv, semanticContainer);
    variables.put(cvv, container);

    // TODO check compliance with sirius
    vpc.getTasks().set(2, taskHelper.buildTaskFromModelOperation(CapellaServices.getService().getDiagramContainer(container), semanticContainer, op));

    return;
  }
}
