/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.step.tools;

import junit.framework.Assert;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.AbstractDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.wrapper.AbstractToolWrapper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.Messages;
import org.polarsys.capella.test.diagram.common.ju.wrapper.factory.ToolWrapperFactory;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ToolHelper;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 * Step case that create a DescriptionTool and execute it.
 */
public abstract class AbstractToolStep<A> extends AbstractDiagramStep<A> {

  protected String toolName;
  protected String toolLabel;

  /**
   * The wrapper for the tool
   */
  protected AbstractToolWrapper _toolWrapper;

  /**
   * Constructor
   * 
   * @param context
   */
  public AbstractToolStep(DiagramContext context, String toolName_p) {
    super(context);
    toolName = toolName_p;
  }

  public AbstractToolStep(DiagramContext context, String toolName_p, String toolLabel_p) {
    super(context);
    toolName = toolName_p;
    toolLabel = toolLabel_p;
  }

  /**
   * Initialize the arguments needed to execute tool.
   */
  protected abstract void initToolArguments();

  public void cannotRun() {

    try {
      preRunTest();

      boolean isArgumentOk = _toolWrapper.isArgumentsAreSet();
      Assert.assertTrue(Messages.toolWrapperArgumentErr, isArgumentOk);

      boolean isContextOk = _toolWrapper.isContextOk();
      Assert.assertTrue(NLS.bind(Messages.toolWrapperArgumentValueErr, toolName), isContextOk);

      TestHelper.getExecutionManager(getExecutionContext().getSession()).execute(new AbstractReadWriteCommand() {
        public void run() {
          Command cmd = _toolWrapper.createCommand();
          Assert.assertTrue(UnexecutableCommand.INSTANCE.equals(cmd));

        }
      });

    } finally {
      dispose();
    }

  }

  /**
   * Implement a create and execute tool operation.
   */
  @Override
  protected void runTest() {

    boolean isArgumentOk = _toolWrapper.isArgumentsAreSet();
    Assert.assertTrue(Messages.toolWrapperArgumentErr, isArgumentOk);

    boolean isContextOk = _toolWrapper.isContextOk();
    Assert.assertTrue(NLS.bind(Messages.toolWrapperArgumentValueErr, toolName), isContextOk);

    TestHelper.getExecutionManager(getExecutionContext().getSession()).execute(new AbstractReadWriteCommand() {
      public void run() {
        Command cmd = _toolWrapper.createCommand();
        Assert.assertTrue(!UnexecutableCommand.INSTANCE.equals(cmd));
        cmd.execute();
      }
    });

  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#preTestRun()
   */
  @Override
  protected void preRunTest() {
    ToolHelper toolHelper = new ToolHelper(getExecutionContext().getSession(), getExecutionContext().getDiagram());

    // Let's find the tool
    // WARNING : CHECK TOOL ID NOT TOOL LABEL
    AbstractToolDescription tool = null;
    if (toolLabel != null) {
      tool = toolHelper.getToolByLabel(toolName, toolLabel);
    }
    if (tool == null) {
      tool = toolHelper.getTool(toolName);
    }
    if (tool == null) {
      tool = toolHelper.getToolByLabel(toolName);
    }
    if (tool == null) {
      tool = toolHelper.getToolByLabel(toolLabel);
    }

    Assert.assertNotNull(
        NLS.bind(Messages.toolDoesNotExist, toolName, getExecutionContext().getDiagram().getDescription().getName()),
        tool);

    // Let's find it's wrapper
    // this case is treated as a test but it fully depends of the test
    // framework. It have to be interpreted as a log for test developer.
    _toolWrapper = ToolWrapperFactory.INSTANCE.createToolCommandWrapper(tool);
    Assert.assertNotNull(NLS.bind(Messages.toolWrapperNotAvailable, toolName), _toolWrapper);

    // Let's initialize interesting data for the tool wrapper
    initToolArguments();

    // Let's check if all is arguments are well set.
    boolean isArgumentOk = _toolWrapper.isArgumentsAreSet();
    Assert.assertTrue(Messages.toolWrapperArgumentErr, isArgumentOk);

    // Let's check the context
    boolean isContextOk = _toolWrapper.isContextOk();
    Assert.assertTrue(NLS.bind(Messages.toolWrapperArgumentValueErr, toolName), isContextOk);

    super.preRunTest();
  }

}
