/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.diagram.ui.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.polarsys.capella.common.ui.services.commands.AbstractUiHandler;
import org.polarsys.capella.core.transition.diagram.ui.Activator;

/**
 *
 */
public class AbstractRunnableHandler extends AbstractUiHandler {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object execute(final ExecutionEvent event_p) throws ExecutionException {
    IRunnableWithProgress runnable = createRunnable(event_p);

    if (runnable != null) {
      try {
        new ProgressMonitorDialog(getActiveShell(event_p)).run(false, false, runnable);
      } catch (Exception exception_p) {
        throw new RuntimeException(exception_p);
      }
    }

    return event_p;
  }

  protected IRunnableWithProgress createRunnable(ExecutionEvent event_p) {
    return null;
  }

  protected Shell getActiveShell(ExecutionEvent event_p) {
    IWorkbenchPart part = (IWorkbenchPart) getVariableValue(event_p, ACTIVE_PART_VARIABLE);
    if (part == null) {
      return Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell();
    }
    return part.getSite().getShell();
  }
}
