/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.ExceptionHandler;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.ui.statushandlers.StatusManager;

/**
 * A transaction exception handler that forwards exceptions
 * to the eclipse status manager.
 */
public class StatusManagerExceptionHandler implements ExceptionHandler {

  private final int style;

  /**
   * Uses the StatusManager.SHOW style to handle exceptions.
   */
  public StatusManagerExceptionHandler() {
    this(StatusManager.SHOW);
  }

  /**
   * Uses the given StatusManager style to handle exceptions.
   */
  public StatusManagerExceptionHandler(int style) {
    this.style = style;
  }

  @Override
  public void handleException(Exception e) {
    IStatus status = extractStatus(e);
    if (status == null) {
      status = createStatus(e);
    }
    StatusManager.getManager().handle(status, style);
  }

  /**
   * Creates the fallback status to use if extractStatus could not find a a status for the exception.
   */
  protected IStatus createStatus(Exception e) {
    return new Status(IStatus.ERROR, MdeCommonUiActivator.PLUGIN_ID, e.getLocalizedMessage(), e);
  }

  /**
   * Extracts a status from the given exception. May return null.
   */
  protected IStatus extractStatus(Exception e) {

    if (e instanceof RollbackException) {

      // Show the first non-ok status in EMF transaction result multi status
      IStatus status = ((RollbackException)e).getStatus();
      if (status.isMultiStatus()) {
       for (IStatus child : status.getChildren()) {
         if (!child.isOK()) {
           return child;
         }
       }
      }

    } else if (e instanceof CoreException){
      CoreException ce = (CoreException) e;
      IStatus exceptionStatus = ce.getStatus();
      return new Status(exceptionStatus.getSeverity(), MdeCommonUiActivator.PLUGIN_ID, ce.getLocalizedMessage(), ce);
    }
    return null;
  }

  /**
   * Install this handler as the stack error handler, executes the command
   * and restores the previous error handler after the command has been executed
   */
  public void installAndExecute(TransactionalCommandStack stack, Command c) {
    ExceptionHandler oldHandler = null;
    try {
      oldHandler = stack.getExceptionHandler();
      stack.setExceptionHandler(this);
      stack.execute(c);
    } finally {
      stack.setExceptionHandler(oldHandler);
    }
  }

}
