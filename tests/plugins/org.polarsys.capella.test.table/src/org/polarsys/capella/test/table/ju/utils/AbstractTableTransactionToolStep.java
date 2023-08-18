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

import static org.junit.Assert.assertNotNull;

import org.polarsys.capella.test.framework.api.step.AbstractTestStep;
import org.polarsys.capella.test.framework.context.SessionContext;

public abstract class AbstractTableTransactionToolStep extends AbstractTestStep {
  protected AbstractTableActionCommand _actionCommand;

  protected String tableEltHiddenErrMsg = "Table Element {0} should be hidden";
  protected String tableEltVisibleErrMsg = "Table Element {0} should be visible";

  public AbstractTableTransactionToolStep(SessionContext executionContext) {
    super(executionContext);
  }

  protected abstract AbstractTableActionCommand getTableTransactionalCommand();

  @Override
  protected void preRunTest() {
    super.preRunTest();

    _actionCommand = getTableTransactionalCommand();
    assertNotNull(_actionCommand);
  }

  @Override
  protected void runTest() {
    _actionCommand.execute();
  }
}

