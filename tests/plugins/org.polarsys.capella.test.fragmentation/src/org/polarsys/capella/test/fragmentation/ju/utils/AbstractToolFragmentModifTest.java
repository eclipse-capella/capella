/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.fragmentation.ju.utils;

import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.test.diagram.common.ju.step.tools.AbstractToolStep;
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
  protected AbstractToolStep _command;

  /**
   * Constructor
   * @param toolName
   */
  public AbstractToolFragmentModifTest(SessionContext sessionContext, AbstractToolStep command) {
    super(sessionContext);
    _command = command;
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#preTestRun()
   */
  @Override
  protected void preRunTest() {

    super.preRunTest();

    // add listener to check ResourceSet modifications done by the command
    _nonAbusiveFragmentModifListener = new FragmentModificationListener(getExpectedFilesToBeModified());
    TransactionHelper.getEditingDomain(getExecutionContext().getSession())
        .addResourceSetListener(_nonAbusiveFragmentModifListener);

    return;
  }

  @Override
  protected void postRunTest() {

    super.postRunTest();
    // remove listener
    TransactionHelper.getEditingDomain(getExecutionContext().getSession())
        .removeResourceSetListener(_nonAbusiveFragmentModifListener);
  }

  protected abstract Set<IFile> getExpectedFilesToBeModified();

  @Override
  public Object getResult() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected void runTest() {
    _command.run();
  }
}
