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
package org.polarsys.capella.common.ui.action;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;

import org.polarsys.capella.common.ui.MdeCommonUiActivator;
import org.polarsys.capella.common.tig.ef.ExecutionManager;
import org.polarsys.capella.common.tig.ef.command.AbstractNonDirtyingCommand;
import org.polarsys.capella.common.tig.ef.command.AbstractReadOnlyCommand;
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.tig.ef.command.ICommand;

/**
 * Base class to implement an action which executes an {@link ICommand} against a model. *
 * <p>
 * {@link AbstractReadWriteCommand}, {@link AbstractReadOnlyCommand}, {@link AbstractNonDirtyingCommand}
 */
public abstract class AbstractModelAction extends Action {
  /**
   * Get the command executed by this action.
   * @return a not <code>null</code> command.
   */
  protected abstract ICommand getCommand();

  /**
   * Get the execution manager used to execute the model changes.
   * @return
   */
  protected abstract ExecutionManager getExecutionManager();

  /**
   * Get selection.<br>
   * Default implementation returns an empty selection.
   * @return must not return <code>null</code>.
   */
  public ISelection getSelection() {
    return StructuredSelection.EMPTY;
  }

  /**
   * Perform command execution.
   * @param executionManager_p
   * @param command_p
   */
  protected void performCommandExecution(ExecutionManager executionManager_p, ICommand command_p) {
    executionManager_p.execute(command_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run() {
    ExecutionManager executionManager = getExecutionManager();
    // Preconditions.
    if (null == executionManager) {
      MdeCommonUiActivator.getDefault().log(IStatus.ERROR, "No Execution Manager found to run this action id:" + getId(), null); //$NON-NLS-1$
      return;
    }
    ICommand command = getCommand();
    // Preconditions.
    if (null == command) {
      MdeCommonUiActivator.getDefault().log(IStatus.ERROR, "No Command found to run this action id:" + getId(), null); //$NON-NLS-1$
      return;
    }
    performCommandExecution(executionManager, command);
  }
}
