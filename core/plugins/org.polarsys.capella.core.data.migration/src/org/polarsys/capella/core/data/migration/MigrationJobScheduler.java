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
package org.polarsys.capella.core.data.migration;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.StatusManager;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.tools.report.util.LogExt;
import org.polarsys.capella.core.data.migration.context.MigrationContext;

/**
 * 
 */
public class MigrationJobScheduler {

  LinkedList<AbstractMigrationRunnable> _runnables;

  IProgressMonitor _monitor;

  /**
   * @param runnables
   * @param runInJob
   */
  public void run(LinkedList<AbstractMigrationRunnable> runnables, final MigrationContext context, final boolean runInJob, final boolean checkVersion) {
    _runnables = new LinkedList<AbstractMigrationRunnable>(runnables);
    
    if (_runnables.isEmpty()) {
      logStatus(context, Status.CANCEL_STATUS);
      return;
    }
    
    IRunnableWithProgress op = new IRunnableWithProgress() {

      public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        _monitor = monitor;
        _monitor.beginTask(context.getName(), _runnables.size());
        
        context.setProgressMonitor(_monitor);

        if (runInJob) {
          executeNextJob(Status.OK_STATUS, context, checkVersion);
        } else {
          IStatus status = Status.OK_STATUS;
          try {
            for (AbstractMigrationRunnable runnable : _runnables) {
              context.setProgressMonitor(SubMonitor.convert(_monitor, 1));
              status = runnable.run(context, checkVersion);
              if (!checkStatusOK(status, context)) {
                _runnables.clear();
                return;
              }
            }

          } finally {
            logStatus(context, status);
            MigrationHelpers.getInstance().dispose(context);
          }
        }
      }
    };

    IWorkbench wb = PlatformUI.getWorkbench();
    IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
    Shell shell = win != null ? win.getShell() : null;
    try {
      new ProgressMonitorDialog(shell).run(true, true, op);

    } catch (InvocationTargetException exception1) {

    } catch (InterruptedException exception1) {

    }

  }

  /**
   * @param status
   * @param context
   * @return
   */
  protected boolean checkStatusOK(IStatus status, MigrationContext context) {
    return ((status == null) || status.isOK()) && !(context.getProgressMonitor().isCanceled());
  }

  protected void executeNextJob(IStatus status, final MigrationContext context, final boolean checkVersion) {

    // Avoid to run next jobs if last is not OK :)
    if (checkStatusOK(status, context)) {
      if (!_runnables.isEmpty()) {
        AbstractMigrationRunnable firstJob = _runnables.removeFirst();

        Job job = new MigrationJob(firstJob, context, checkVersion);
        job.addJobChangeListener(new JobChangeAdapter() {
          @Override
          public void done(IJobChangeEvent event) {
            IStatus jobStatus = event.getResult();
            IStatus internalStatus = (IStatus) event.getJob().getProperty(MigrationJob.RESULT_PROPERTY);
            if ((internalStatus != null) && !internalStatus.isOK()) {
              jobStatus = internalStatus;
            }
            executeNextJob(jobStatus, context, checkVersion);
          }
        });
        context.setProgressMonitor(SubMonitor.convert(_monitor, 1));
        job.schedule();

        // for the automated tests, join the job to be sure that the scheduled jobs are runned in the right order
        try {
          job.join();
        } catch (InterruptedException exception) {
          exception.printStackTrace();
        }

      } else {
        logStatus(context, status);
        MigrationHelpers.getInstance().dispose(context);
      }

    } else {
      logStatus(context, status);
      MigrationHelpers.getInstance().dispose(context);
      _runnables.clear();
    }

  }

  protected void logStatus(MigrationContext context, IStatus status) {
    if (status.isOK()) {
      status = Status.info(NLS.bind(Messages.MigrationAction_MigrationOK, context.getName()));
    }
    
    StatusManager.getManager().handle(status, StatusManager.LOG);
    LogExt.log(IReportManagerDefaultComponents.MODEL, status);

    if (!context.isSkipConfirmation()) {
      if ((status.getSeverity() == IStatus.ERROR) || (status.getSeverity() == IStatus.WARNING)) {
        StatusManager.getManager().handle(status, StatusManager.BLOCK);
      }
    }
  }
}
