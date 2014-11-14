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

package org.polarsys.capella.core.platform.sirius.ui.actions;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;

import org.polarsys.capella.common.ui.actions.AbstractTigAction;
import org.polarsys.capella.common.tig.ef.command.ICommand;

/**
 * The action allowing to cut Capella elements.
 */
public abstract class AbstractFixAction extends AbstractTigAction {

  protected abstract ICommand createCommand(IProgressMonitor progressMonitor_p);

  /**
   * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
   */
  public void run(final IAction action) {

    IRunnableWithProgress runnable = new IRunnableWithProgress() {
      /**
       * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
       */
      @SuppressWarnings("synthetic-access")
      public void run(IProgressMonitor progressMonitor_p) throws InvocationTargetException, InterruptedException {
        progressMonitor_p.beginTask(action.getText() + " processing...", 1); //$NON-NLS-1$
        ICommand command = createCommand(progressMonitor_p);
        if (command != null) {
          progressMonitor_p.setTaskName(command.getName() + " processing..."); //$NON-NLS-1$
          getExecutionManager().execute(command);
        }
        progressMonitor_p.worked(1);
      }
    };

    try {
      new ProgressMonitorDialog(getActiveShell()).run(true, false, runnable);
    } catch (Exception exception_p) {
      throw new RuntimeException(exception_p);
    }

  }

}
