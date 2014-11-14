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
package org.polarsys.capella.common.mdsofa.common.progress;

import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * A runner that executes runnable with progress reporting support.
 */
public class ProgressReporter {
  /**
   * An executed task default ticks count (in terms of reporting).
   */
  public static final int TASK_DEFAULT_TICKS_COUNT = 1000;

  /**
   * Causes the {@link IProgressRunnable#run(IProgressMonitor)} method of the given runnable to be invoked
   * by an eclipse job at the next reasonable opportunity. The caller continues to run in parallel,
   * and is not notified when the runnable has completed.<br>
   * The progress reporting is done through the progress monitor of the executing job (non-user one).
   * @param runnable_p
   * @see #syncExec(IProgressRunnable, IProgressMonitor)
   */
  public static void asyncExec(final IProgressRunnable runnable_p) {
    // Do nothing if there is nothing to do.
    if (null == runnable_p) {
      return;
    }
    // Create a new job.
    Job job = new WorkspaceJob(runnable_p.getReportingTitle()) {
      @Override
      public IStatus runInWorkspace(IProgressMonitor monitor_p) throws CoreException {
        boolean result = syncExec(runnable_p, monitor_p);
        return result ? Status.OK_STATUS : Status.CANCEL_STATUS;
      }
    };
    job.schedule();
  }

  /**
   * Causes the {@link IProgressRunnable#run(IProgressMonitor)} method of the given runnable to be invoked
   * by the calling thread. This one is suspended until the runnable completes.<br>
   * The progress reporting is done through the given progress monitor.
   * @param runnable_p
   * @param progressMonitor_p null to disable progress reporting.
   * @see #asyncExec(IProgressRunnable)
   */
  public static boolean syncExec(IProgressRunnable runnable_p, IProgressMonitor progressMonitor_p) {
    // Do nothing if there is nothing to do.
    if (null == runnable_p) {
      return true;
    }
    IProgressMonitor progressMonitor = progressMonitor_p;
    // If no progress monitor is provided, use null one.
    if (null == progressMonitor) {
      progressMonitor = new NullProgressMonitor();
    }
    // Invoke the run method.
    return runnable_p.run(progressMonitor);
  }
}
