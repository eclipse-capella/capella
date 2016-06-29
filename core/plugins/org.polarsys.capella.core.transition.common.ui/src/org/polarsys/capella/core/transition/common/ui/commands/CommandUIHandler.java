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
package org.polarsys.capella.core.transition.common.ui.commands;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.transition.common.commands.CommandHandler;

public abstract class CommandUIHandler extends CommandHandler {

  private Object executeCommand(ExecutionEvent event) throws ExecutionException {
    return super.execute(event);
  }

  public Object execute(final ExecutionEvent event) throws ExecutionException {
    IRunnableWithProgress runnable = new IRunnableWithProgress() {

      @SuppressWarnings("synthetic-access")
      public void run(IProgressMonitor progressMonitor) throws InvocationTargetException, InterruptedException {
        try {
          executeCommand(event);
        } catch (ExecutionException e) {
          e.printStackTrace();
        }
        progressMonitor.worked(1);
      }
    };

    try {
      new ProgressMonitorDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell()).run(false, false, runnable);
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }

    return event;
  }

}
