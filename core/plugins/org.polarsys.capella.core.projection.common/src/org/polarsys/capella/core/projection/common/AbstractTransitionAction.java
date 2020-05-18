/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.common;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.tools.report.appenders.usage.UsageMonitoringLogger;
import org.polarsys.capella.common.tools.report.appenders.usage.util.UsageMonitoring.EventStatus;
import org.polarsys.capella.common.ui.actions.AbstractTigAction;

/**
 */
public abstract class AbstractTransitionAction extends AbstractTigAction {

  protected abstract ICommand createCommand(Collection<EObject> elements_p, IProgressMonitor progressMonitor_p);

  /**
   * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
   */
  public void run(final IAction action) {

    IRunnableWithProgress runnable = new IRunnableWithProgress() {
      /**
       * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
       */
      @SuppressWarnings({ "synthetic-access", "unchecked", "rawtypes" })
      public void run(IProgressMonitor progressMonitor_p) throws InvocationTargetException, InterruptedException {
        ICommand command = createCommand((Collection) getSelectedElements(), progressMonitor_p);
        if (command != null) {
          progressMonitor_p.beginTask(command.getName() + " processing...", 1); //$NON-NLS-1$
          
          String eventName = "Transition";
          String eventContext = command.getName();
          
          try {
        	  UsageMonitoringLogger.getInstance().log(eventName, eventContext, EventStatus.NONE);
        	  getExecutionManager().execute(command);
        	  progressMonitor_p.worked(1);
        	  
        	  UsageMonitoringLogger.getInstance().log(eventName, eventContext, EventStatus.OK);
          } catch (Exception e) {
        	  UsageMonitoringLogger.getInstance().log(eventName, eventContext, EventStatus.ERROR);
        	  throw e;
          }
        }

      }
    };

    try {
      // Pb Sirius. Temporary Workaround until fix. Passage to false of first parameter to run the command from the GUI thread.
      new ProgressMonitorDialog(getActiveShell()).run(false, false, runnable);
    } catch (Exception exception_p) {
      throw new RuntimeException(exception_p);
    }

  }
}
