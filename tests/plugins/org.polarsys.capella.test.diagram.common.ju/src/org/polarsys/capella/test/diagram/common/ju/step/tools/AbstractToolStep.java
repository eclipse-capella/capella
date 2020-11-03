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
package org.polarsys.capella.test.diagram.common.ju.step.tools;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.junit.Assert;
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
  public AbstractToolStep(DiagramContext diagramContext, String toolName) {

    super(diagramContext);
    this.toolName = toolName;
  }

  // Deprecated because the tool's label should not be used for queries
  @Deprecated
  public AbstractToolStep(DiagramContext context, String toolName_p, String toolLabel_p) {
    super(context);
    toolName = toolName_p;
    toolLabel = toolLabel_p;
  }

  /**
   * Initialize the arguments needed to execute tool.
   */
  protected abstract void initToolArguments();

  private void initializeToolAndCheckArguments() {

    Session currentSession = getExecutionContext().getSession();
    DDiagram currentDiagram = getDiagramContext().getDiagram();

    ToolHelper toolHelper = new ToolHelper(currentSession, currentDiagram);

    // Let's find the tool
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

    String diagramIdentifier = currentDiagram.getDescription().getName();
    Assert.assertNotNull(NLS.bind(Messages.toolDoesNotExist, toolName, diagramIdentifier), tool);

    // Let's find it's wrapper
    // this case is treated as a test but it fully depends of the test
    // framework. It have to be interpreted as a log for test developer.
    _toolWrapper = ToolWrapperFactory.INSTANCE.createToolCommandWrapper(tool);
    Assert.assertNotNull(NLS.bind(Messages.toolWrapperNotAvailable, toolName), _toolWrapper);

    // Let's initialize the arguments needed by the tool
    initToolArguments();

    // Let's check if all is arguments are well set.
    IStatus isArgumentOk = _toolWrapper.checkArguments();
    Assert.assertTrue(NLS.bind(Messages.toolWrapperArgumentErr, isArgumentOk.toString()), isArgumentOk.isOK());

  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#preTestRun()
   */
  @Override
  protected void preRunTest() {

    initializeToolAndCheckArguments();

    // Let's check the context
    boolean isContextOk = _toolWrapper.isContextOk();
    Assert.assertTrue(NLS.bind(Messages.toolWrapperArgumentValueErr, toolName), isContextOk);
  }

  /**
   * Implement a create and execute tool operation.
   */
  protected void runTest() {

    TestHelper.getExecutionManager(getExecutionContext().getSession()).execute(new AbstractReadWriteCommand() {
      public void run() {
        Command cmd = _toolWrapper.createCommand();

        Assert.assertTrue(NLS.bind(Messages.toolPreconditionFailed, toolName), !UnexecutableCommand.INSTANCE.equals(cmd));
        cmd.execute();
      }
    });
  }

  public void contextOk() {
    initializeToolAndCheckArguments();

    // Context should be OK
    boolean isContextOk = _toolWrapper.isContextOk();
    Assert.assertTrue(NLS.bind(Messages.toolWrapperArgumentValueFailedErr, toolName), isContextOk);
  }

  public void shouldFail() {

    initializeToolAndCheckArguments();

    // Context should not be OK
    boolean isContextOk = _toolWrapper.isContextOk();
    Assert.assertFalse(NLS.bind(Messages.toolWrapperArgumentValueFailedErr, toolName), isContextOk);
  }

  public void cannotRun() {

    try {

      preRunTest();

      TestHelper.getExecutionManager(getExecutionContext().getSession()).execute(new AbstractReadWriteCommand() {
        @Override
        public void run() {
          Command cmd = _toolWrapper.createCommand();
          Assert.assertTrue(UnexecutableCommand.INSTANCE.equals(cmd));
        }
      });

    } finally {
      dispose();
    }
  }
}
