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
package org.polarsys.capella.core.platform.sirius.ui.project.internal;

import org.eclipse.core.internal.resources.InternalWorkspaceJob;
import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.internal.utils.Policy;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * @see InternalWorkspaceJob This is a copy of InternalWorkspaceJob that does not have a reference to the workspace. Batches the activity of a
 *      job as a single operation, without obtaining the workspace lock.
 */
public abstract class RunInWorkspaceJob extends Job {

  public RunInWorkspaceJob(String name) {
    super(name);

  }

  @Override
  public final IStatus run(IProgressMonitor monitor) {
    monitor = Policy.monitorFor(monitor);
    Workspace workspace = (Workspace) ResourcesPlugin.getWorkspace();
    // Do nothing if workspace is still not open
    if (!workspace.isOpen()) {
      return Status.OK_STATUS;
    }
    try {
      int depth = -1;
      try {

				// workspace.getWorkManager().checkIn(null, monitor); 
        //will let the WorkManager unbalanced (e.g. workManager.isBalanced() ==
				// false)
				// => we use prepareOperation and beginOpreation instead
				workspace.prepareOperation(null, monitor);
				workspace.beginOperation(true);

        depth = workspace.getWorkManager().beginUnprotected();
        return runInWorkspace(monitor);
      } catch (OperationCanceledException e) {
        workspace.getWorkManager().operationCanceled();
        return Status.CANCEL_STATUS;
      } finally {
        if (depth >= 0) {
          workspace.getWorkManager().endUnprotected(depth);
					
        }
				workspace.endOperation(null, false);

        workspace = null;
      }
    } catch (CoreException e) {
      return e.getStatus();
    }
  }

  protected abstract IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException;
}
