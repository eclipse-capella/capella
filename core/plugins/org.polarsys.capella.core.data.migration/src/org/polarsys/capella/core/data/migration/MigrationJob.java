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

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.polarsys.capella.core.data.migration.context.MigrationContext;

/**
 * Migration Job.
 * 
 */
public class MigrationJob extends WorkspaceJob {
  /**
   * Model file to migrate.
   */
  private MigrationContext _context;

  private AbstractMigrationRunnable _runnable;

  public MigrationJob(AbstractMigrationRunnable runnable, MigrationContext context) {
    super(context.getName());
    _context = context;
    _runnable = runnable;
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
      IStatus result = _runnable.run(_context);

      if (result.isOK()) {
        try { // refresh output file.
          _runnable.getFile().getProject().refreshLocal(IResource.DEPTH_INFINITE, new SubProgressMonitor(monitor, 5));
        } catch (CoreException exception) {
          result = new Status(IStatus.ERROR, Activator.PLUGIN_ID, exception.getMessage(), exception);
        }
      }

      return result;

    } finally {
      monitor.done();
      _runnable = null;
      _context = null;
    }
  }

}
