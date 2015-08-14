/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
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
  public void run(LinkedList<AbstractMigrationRunnable> runnables, final MigrationContext context,
      final boolean runInJob) {
    _runnables = new LinkedList<AbstractMigrationRunnable>(runnables);

    IRunnableWithProgress op = new IRunnableWithProgress() {

      public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        _monitor = monitor;
        _monitor.beginTask("Model migration", _runnables.size());

        context.setProgressMonitor(_monitor);

        if (runInJob) {
          executeNextJob(Status.OK_STATUS, context);

        } else {
          try {

            for (AbstractMigrationRunnable runnable : _runnables) {
              context.setProgressMonitor(new SubProgressMonitor(_monitor, 1));
              IStatus status = runnable.run(context);
              if (!checkStatusOK(status, context)) {
                logStatus(context, status);
                _runnables.clear();
                return;
              }
            }

          } finally {
            MigrationHelpers.getInstance().dispose();
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

  protected void executeNextJob(IStatus status, final MigrationContext context) {

    // Avoid to run next jobs if last is not OK :)
    if (checkStatusOK(status, context)) {
      if (!_runnables.isEmpty()) {
        AbstractMigrationRunnable firstJob = _runnables.removeFirst();

        Job job = new MigrationJob(firstJob, context);
        job.addJobChangeListener(new JobChangeAdapter() {
          @Override
          public void done(IJobChangeEvent event) {
            executeNextJob(event.getResult(), context);
          }
        });
        context.setProgressMonitor(new SubProgressMonitor(_monitor, 1));
        job.schedule();

        // for the automated tests, join the job to be sure that the scheduled jobs are runned in the right order
        try {
          job.join();
        } catch (InterruptedException exception) {
          exception.printStackTrace();
        }
      }

    } else {
      logStatus(context, status);
      MigrationHelpers.getInstance().dispose();
      _runnables.clear();
    }

  }

  private void logStatus(final MigrationContext context, final IStatus status) {
    /*
     * if (!context.isSkipConfirmation()) { context.getShell().getDisplay().asyncExec(new Runnable() {
     * 
     * @Override public void run() { MessageDialog.openError(context.getShell(), context.getName(),
     * status.getMessage()); } }); }
     */
  }
}
