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
package org.polarsys.capella.test.diagram.common.ju.step.tools;

import junit.framework.Assert;

import org.eclipse.emf.common.command.Command;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramOpenExecutionContext;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramToolExecutionContext;
import org.polarsys.capella.test.diagram.common.ju.step.AbstractDiagramStepWithDelta;
import org.polarsys.capella.test.diagram.common.ju.wrapper.AbstractToolWrapper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.Messages;
import org.polarsys.capella.test.diagram.common.ju.wrapper.factory.ToolWrapperFactory;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ToolHelper;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 * Step case that create a DescriptionTool and execute it.
 */
public abstract class AbstractToolStep extends AbstractDiagramStepWithDelta {

  /**
   * The wrapper for the tool
   */
  protected AbstractToolWrapper _toolWrapper;

  /**
   * Constructor
   * @param context
   */
  public AbstractToolStep(DiagramToolExecutionContext context) {
    super(context, false);
  }

  /**
   * Public Constructor with Delta Functionality activated
   * @param context
   * @param checkDelta
   */
  public AbstractToolStep(DiagramToolExecutionContext context, boolean checkDelta) {
    super(context, checkDelta);
  }

  /**
   * Initialize the arguments needed to execute tool.
   */
  protected abstract void initToolArguments();

  /**
   * Implement a create and execute tool operation.
   */
  public void runTest() {

    boolean isArgumentOk = _toolWrapper.isArgumentsAreSet();
    Assert.assertTrue(Messages.toolWrapperArgumentErr, isArgumentOk);

    boolean isContextOk = _toolWrapper.isContextOk();
    Assert.assertTrue(NLS.bind(Messages.toolWrapperArgumentValueErr, ((DiagramToolExecutionContext) getExecutionContext()).getToolName()), isContextOk);


    TestHelper.getExecutionManager(getExecutionContext().getSession()).execute(new AbstractReadWriteCommand() {
      public void run() {
        Command cmd = _toolWrapper.createCommand();
        cmd.execute();
      }
    });

    return;
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#preTestRun()
   */
  @Override
  protected void preRunTest() {
    ToolHelper toolHelper = new ToolHelper(getExecutionContext().getSession(), ((DiagramOpenExecutionContext) getExecutionContext()).getDiagram());

    // Let's find the tool
    // WARNING : CHECK TOOL ID NOT TOOL LABEL
    AbstractToolDescription tool = toolHelper.getTool(((DiagramToolExecutionContext) getExecutionContext()).getToolName());
    Assert.assertNotNull(NLS.bind(Messages.toolDoesNotExist, ((DiagramToolExecutionContext) getExecutionContext()).getToolName()), tool);

    // Let's find it's wrapper
    // this case is treated as a test but it fully depends of the test
    // framework. It have to be interpreted as a log for test developer.
    _toolWrapper = ToolWrapperFactory.INSTANCE.createToolCommandWrapper(tool);
    Assert.assertNotNull(NLS.bind(Messages.toolWrapperNotAvailable, ((DiagramToolExecutionContext) getExecutionContext()).getToolName()), _toolWrapper);

    // Let's initialize interesting data for the tool wrapper
    initToolArguments();

    // Let's check if all is arguments are well set.
    boolean isArgumentOk = _toolWrapper.isArgumentsAreSet();
    Assert.assertTrue(Messages.toolWrapperArgumentErr, isArgumentOk);

    // Let's check the context
    boolean isContextOk = _toolWrapper.isContextOk();
    Assert.assertTrue(NLS.bind(Messages.toolWrapperArgumentValueErr, ((DiagramToolExecutionContext) getExecutionContext()).getToolName()), isContextOk);

    super.preRunTest();
  }

  /**
   * @return number of expected new elements after the invoked action
   */
  @Override
  protected int getNumberofExpectedNewElement() {
    return 0;
  }

  /**
   * Assert that the number of elements expected match the number of elements effectively created in a diagram
   */
  @Override
  protected void checkDeltaNumberOfElementsCreated() {
    int expected = 0;
    Assert.assertEquals(getNumberofExpectedNewElement(), expected);
  }

  public Object getResult() {
    return null;
  }
}
