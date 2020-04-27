/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.fragmentation.ju.utils;

import static org.junit.Assert.fail;

import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.test.framework.api.step.AbstractTestStep;
import org.polarsys.capella.test.framework.context.SessionContext;

/**
 * Special case of AbstractExecuteToolCmdTest for Non Abusive Fragment Modification tests
 * <p>
 * A special listener is added on MDEAdapterFactory ExecutionManager
 * <p>
 * There is a test on files to be modified in this listener
 */
public abstract class AbstractToolFragmentModifTest extends AbstractTestStep {
  FragmentModificationListener _nonAbusiveFragmentModifListener;
  protected AbstractTestStep _command;
  protected Object resultCmd;

  /**
   * Constructor
   * @param toolName
   */
  public AbstractToolFragmentModifTest(SessionContext sessionContext, AbstractTestStep command) {
    super(sessionContext);
    _command = command;
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#preTestRun()
   */
  @Override
  protected void preRunTest() {
    // set the files in ReadOnly mode
    try {
      FragmentUtils.setIFileListReadOnly(getExpectedFilesToBeModified());
    } catch (CoreException e) {
      fail(e.getMessage());
    }

    super.preRunTest();

    // add listener to check ResourceSet modifications done by the command
    _nonAbusiveFragmentModifListener = new FragmentModificationListener(getExpectedFilesToBeModified());
    TransactionHelper.getEditingDomain(getExecutionContext().getSession())
        .addResourceSetListener(_nonAbusiveFragmentModifListener);

    return;
  }

  @Override
  protected void postRunTest() {
    // set the files in Write mode
    try {
      FragmentUtils.setIFileListWrite(getExpectedFilesToBeModified());
    } catch (CoreException e) {
      fail(e.getMessage());
    }

    super.postRunTest();
    // remove listener
    TransactionHelper.getEditingDomain(getExecutionContext().getSession())
        .removeResourceSetListener(_nonAbusiveFragmentModifListener);
  }

  protected abstract Set<IFile> getExpectedFilesToBeModified();

  @Override
  public Object getResult() {
    return resultCmd;
  }

  @Override
  protected void runTest() {
    resultCmd = _command.run();
  }
}
