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

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.polarsys.capella.core.data.migration.context.MigrationContext;

/**
 * Migration Job.
 */
public class MigrationJob extends WorkspaceJob {

  public static final QualifiedName RESULT_PROPERTY = new QualifiedName(Activator.PLUGIN_ID, "result"); //$NON-NLS-1$

  private boolean checkVersion;
  private MigrationContext context;
  private AbstractMigrationRunnable runnable;


  public MigrationJob(AbstractMigrationRunnable runnable, MigrationContext context, boolean checkVersion) {
    super(context.getName());
    this.context = context;
    this.runnable = runnable;
    this.checkVersion = checkVersion;
    // Set the concurrent access to the workspace root, fragments can be hosted in several projects.
    setRule(runnable.getFile().getProject().getWorkspace().getRoot());
    // Display a progress dialog.
    setUser(true);
    setSystem(false);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus runInWorkspace(IProgressMonitor monitor) {
    monitor.beginTask(getName(), 100);
    try {
      // Migration processing (>95% of migration is here)
      IStatus result = runnable.run(context, checkVersion);

      if (result.isOK()) {
        try { // refresh output file.
          runnable.getFile().getProject().refreshLocal(IResource.DEPTH_INFINITE, SubMonitor.convert(monitor, 5));
        } catch (CoreException exception) {
          result = new Status(IStatus.ERROR, Activator.PLUGIN_ID, exception.getMessage(), exception);
        }
      }

      // According to context.isSkipConfirmation(), we want to display or not the returned IStatus.
      // If a job is returning an IStatus.ERROR, the message will be automatically displayed without a
      // StatusManager.BLOCK state
      // so we need to return an OK_STATUS and display it (or not) later (see MigrationJobScheduler.logStatus)
      setProperty(RESULT_PROPERTY, result);
      return Status.OK_STATUS;

    } finally {
      monitor.done();
      runnable = null;
      context = null;
    }
  }
}
