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
package org.polarsys.capella.core.refinement.commands.actions;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;

import org.polarsys.capella.common.ui.actions.AbstractTigAction;
import org.polarsys.capella.core.refinement.commands.AddNewSubScenarioCommand;

/**
 */
public class AddNewSubScenarioAction extends AbstractTigAction {
  private static final String PROGRESS_BAR_NAME = "Add new sub Scenario processing..."; //$NON-NLS-1$

  /**
   * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
   */
  public void run(IAction action) {
    IRunnableWithProgress runnable = new IRunnableWithProgress() {
      /**
       * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
       */
      @SuppressWarnings("synthetic-access")
      public void run(IProgressMonitor progressMonitor) throws InvocationTargetException, InterruptedException {
        progressMonitor.beginTask(PROGRESS_BAR_NAME, IProgressMonitor.UNKNOWN);
        getExecutionManager().execute(new AddNewSubScenarioCommand(getSelectedElement(), progressMonitor));
      }
    };
    try {
      // Temporary workaround until a fix. Set to 'false' the first
      // parameter to run command from UI Thread.
      new ProgressMonitorDialog(getActiveShell()).run(false, false, runnable);
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
  }
}
