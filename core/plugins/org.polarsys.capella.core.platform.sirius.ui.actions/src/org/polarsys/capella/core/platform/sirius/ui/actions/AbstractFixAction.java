/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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


package org.polarsys.capella.core.platform.sirius.ui.actions;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;

import org.polarsys.capella.common.ui.actions.AbstractTigAction;
import org.polarsys.capella.common.ef.command.ICommand;

/**
 * The action allowing to cut Capella elements.
 */
public abstract class AbstractFixAction extends AbstractTigAction {

  protected abstract ICommand createCommand(IProgressMonitor progressMonitor);

  /**
   * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
   */
  public void run(final IAction action) {

    IRunnableWithProgress runnable = new IRunnableWithProgress() {
      /**
       * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
       */
      @SuppressWarnings("synthetic-access")
      public void run(IProgressMonitor progressMonitor) throws InvocationTargetException, InterruptedException {
        progressMonitor.beginTask(action.getText() + " processing...", 1); //$NON-NLS-1$
        ICommand command = createCommand(progressMonitor);
        if (command != null) {
          progressMonitor.setTaskName(command.getName() + " processing..."); //$NON-NLS-1$
          getExecutionManager().execute(command);
        }
        progressMonitor.worked(1);
      }
    };

    try {
      new ProgressMonitorDialog(getActiveShell()).run(true, false, runnable);
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }

  }

}
