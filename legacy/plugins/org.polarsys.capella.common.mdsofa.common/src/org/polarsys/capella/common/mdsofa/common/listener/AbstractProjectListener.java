/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.mdsofa.common.listener;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspaceRoot;

/**
 * Base class to implement a listener which is notified when projects are added, changed or removed from the workspace.
 */
public abstract class AbstractProjectListener implements IResourceChangeListener {
  /**
   * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
   */
  public void resourceChanged(IResourceChangeEvent event_p) {
    IResourceDelta resourceDelta = event_p.getDelta();
    // Pre-condition.
    if (null == resourceDelta) {
      return;
    }
    IResource resource = resourceDelta.getResource();
    // Select only changes performed on the workspace.
    if ((IResourceDelta.CHANGED == resourceDelta.getKind()) && (resource instanceof IWorkspaceRoot)) {
      IResourceDelta[] childrenResourceDeltas = resourceDelta.getAffectedChildren();
      // Loop over modified resources within the workspace.
      for (IResourceDelta currentChildResourceDelta : childrenResourceDeltas) {
        // Get the modification kind.
        int kind = currentChildResourceDelta.getKind();
        // Get the modified resource.
        IResource childResource = currentChildResourceDelta.getResource();
        // Get from it an IProject.
        IProject project = getProject(childResource);
        // If it is a project, interesting in removed or changed operation.
        if (null != project) {
          switch (kind) {
            case IResourceDelta.ADDED:
              handleProjectAdded(project, currentChildResourceDelta);
            break;
            case IResourceDelta.CHANGED:
              // A change can be a close event.
              int flags = currentChildResourceDelta.getFlags();
              boolean isProjectOpened = project.isOpen();
              // Detailed flag must be the OPEN event.
              if ((IResourceDelta.OPEN == flags)) {
                if (isProjectOpened) {
                  handleProjectAdded(project, currentChildResourceDelta);
                } else {
                  handleProjectClosed(project, currentChildResourceDelta);
                }
              } else {
                handleProjectChanged(project, currentChildResourceDelta);
              }
            break;
            case IResourceDelta.REMOVED:
              handleProjectRemoved(project, currentChildResourceDelta);
            break;
          }
        }
      }
    }
  }

  /**
   * Handle project added.
   * @param project_p
   * @param resourceDelta_p
   */
  protected abstract void handleProjectAdded(IProject project_p, IResourceDelta resourceDelta_p);

  /**
   * Handle project changed.
   * @param project_p
   * @param resourceDelta_p
   */
  protected abstract void handleProjectChanged(IProject project_p, IResourceDelta resourceDelta_p);

  /**
   * Handle project removed.
   * @param project_p
   * @param resourceDelta_p
   */
  protected abstract void handleProjectRemoved(IProject project_p, IResourceDelta resourceDelta_p);

  /**
   * Handle project closed.
   * @param project_p
   * @param resourceDelta_p
   */
  protected abstract void handleProjectClosed(IProject project_p, IResourceDelta resourceDelta_p);

  /**
   * Get the <code>IProject</code> for given resource.
   * @param resource_p
   * @return
   */
  protected IProject getProject(IResource resource_p) {
    IProject result = null;
    if ((null != resource_p) && (resource_p instanceof IProject)) {
      result = (IProject) resource_p;
    }
    return result;
  }
}
