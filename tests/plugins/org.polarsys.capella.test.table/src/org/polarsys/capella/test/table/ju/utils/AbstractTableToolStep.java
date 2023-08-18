/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.table.ju.utils;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramComponentizationManager;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.MenuItemDescription;
import org.eclipse.sirius.viewpoint.description.tool.PopupMenu;
import org.junit.Assert;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.test.diagram.common.ju.wrapper.AbstractToolWrapper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.Messages;
import org.polarsys.capella.test.diagram.common.ju.wrapper.factory.ToolWrapperFactory;
import org.polarsys.capella.test.framework.api.step.AbstractTestStep;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 * Step case that create a DescriptionTool and execute it.
 */
public abstract class AbstractTableToolStep extends AbstractTestStep {

  protected String toolName;
  protected String toolLabel;
  protected AbstractToolWrapper _toolWrapper;
  protected SessionContext sessionContext;
  protected DTable table;
  protected EClass toolEClass;

  public AbstractTableToolStep(SessionContext executionContext, EClass toolEClass, DTable t) {
    this(executionContext, null, toolEClass, t);
  }

  public AbstractTableToolStep(SessionContext executionContext, String toolName, EClass toolEClass, DTable t) {
    super(executionContext);
    this.sessionContext = executionContext;
    this.table = t;
    this.toolName = toolName;
    this.toolEClass = toolEClass;
  }

  /**
   * Initialize the arguments needed to execute tool.
   */
  protected abstract void initToolArguments();

  private void initializeToolAndCheckArguments() {

    Session currentSession = getExecutionContext().getSession();

    TableToolHelper helper = new TableToolHelper(currentSession, table);
    AbstractToolDescription tool = (toolName == null) ? helper.getTool(toolEClass)
        : helper.getTool(toolEClass, toolName);
    Assert.assertNotNull(NLS.bind(Messages.toolDoesNotExist, toolEClass), tool);

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
  @Override
  protected void runTest() {

    TestHelper.getExecutionManager(getExecutionContext().getSession()).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        Command cmd = _toolWrapper.createCommand();

        Assert.assertTrue(!UnexecutableCommand.INSTANCE.equals(cmd));
        cmd.execute();

      }
    });
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

  public AbstractToolDescription getTool(final String toolName) {
    TableDescription desc = table.getDescription();
    final List<AbstractToolDescription> tools = new DiagramComponentizationManager()
        .getAllTools(sessionContext.getSession().getSelectedViewpoints(true), (DiagramDescription) desc);
    AbstractToolDescription theAbstractToolDescription = null;

    for (AbstractToolDescription current : tools) {
      if (current.getName().equals(toolName)) {
        theAbstractToolDescription = current;
        break;
      }

      if (current instanceof PopupMenu) {
        for (MenuItemDescription item : ((PopupMenu) current).getMenuItemDescription()) {
          if (item.getName().equals(toolName)) {
            theAbstractToolDescription = item;
            break;
          }
        }
      }
    }

    return theAbstractToolDescription;
  }
}
