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
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.OperationAction;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;

/**
 */
public class OperationActionToolDescriptionWrapper extends AbstractCommonToolWrapper {

  public OperationActionToolDescriptionWrapper(AbstractToolDescription tool, IDiagramCommandFactory commandFactory) {
    super(tool, commandFactory);
  }

  /**
   * @see org.polarsys.capella.test.common.ju.tool.AbstractCmdToolWrapper#createCommand()
   */
  @SuppressWarnings("unchecked")
  @Override
  public Command createCommand() {

    Command cmd = UnexecutableCommand.INSTANCE;

    if (isContextOk()) {
      AbstractToolDescription tool = _tool;
      OperationAction operationAction = findOperationAction(tool);
      Assert.assertNotNull("Warning ! Operation Action not found for tool " + tool.getName(), operationAction); //$NON-NLS-1$
      Collection<DSemanticDecorator> col = (Collection<DSemanticDecorator>) _arguments.get(ArgumentType.COLLECTION);
      cmd = _diagramCommandFactory.buildOperationActionFromTool(operationAction, col);
    }
    return cmd;
  }

  /**
   * Find an OperationAction in a tool eContents
   * @param eContents
   * @return the first OperationAction encounter in a tool eContents
   */
  private OperationAction findOperationAction(AbstractToolDescription tool) {
    boolean found = false;
    OperationAction operationActionResult = null;
    EList<EObject> eContents = tool.eContents();
    for (int i = 0; i < eContents.size() && !found; i++) {
      EObject content = eContents.get(i);
      if (content instanceof OperationAction) {
        operationActionResult = (OperationAction) content;
      }
    }
    return operationActionResult;
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
