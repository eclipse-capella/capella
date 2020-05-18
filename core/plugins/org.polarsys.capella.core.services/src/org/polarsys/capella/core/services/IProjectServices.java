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

package org.polarsys.capella.core.services;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import org.polarsys.capella.core.data.capellamodeller.Project;

/**
 * The Capella project services.
 */
public interface IProjectServices {
  /**
   * Creates the Capella project through the target platform.
   * @param project_p The parent Eclipse project.
   * @param name_p The project name.
   * @param monitor_p The progress monitor.
   * @return The Capella project.
   */
  public Project createProject(IProject project_p, String name_p, IProgressMonitor monitor_p);

  /**
   * Loads the specified Capella project through the target platform.
   * @param projectPath_p The path of the Capella project to load.
   * @param monitor_p The progress monitor.
   * @return The Capella project or <code>null</code> if not loaded.
   */
  public Project load(String projectPath_p, IProgressMonitor monitor_p);

  /**
   * Closes the specified Capella project through the target platform.
   * @param project_p The Capella project to close.
   * @param monitor_p The progress monitor.
   */
  public void close(Project project_p, IProgressMonitor monitor_p);

  /**
   * Saves the specified Capella project through the target platform.
   * @param project_p The Capella project to save.
   * @param monitor_p The progress monitor.
   */
  public void save(Project project_p, IProgressMonitor monitor_p);

  /**
   * Gets the editing domain.
   * @return The current editing domain.
   */
  public TransactionalEditingDomain getEditingDomain();

  /**
   * Check if the specified resource is a Capella resource.
   * @param resource_p The resource to check.
   * @return <code>True</code> if it's a Capella resource else <code>false</code>.
   */
  public boolean isCapellaResource(IResource resource_p);
}
