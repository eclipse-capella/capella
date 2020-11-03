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

package org.polarsys.capella.core.model.skeleton.impl.cmd;

import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerFactory;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.ProjectApproach;

/**
 * The command allowing to create the Capella project root.
 */
public class CreateCapellaProjectCmd extends AbstractReadWriteCommand {

  // cache of newly-created project.
  private Resource _resource;

  // The project name.
  private String _name;

  // The Capella project.
  private Project _mdeProject;
  /**
   * Project Approach.
   */
  private ProjectApproach _approach;

  /**
   * Constructs the command to create the Capella project with the specified name.
   * @param eclipseProject_p The parent Eclipse project.
   * @param name_p The Capella project name.
   * @param domain_p The transactional editing domain.
   */
  public CreateCapellaProjectCmd(Resource resource_p, String name_p, ProjectApproach approach_p) {
    _resource = resource_p;
    _approach = approach_p;
    _name = name_p;
  }

  /**
   * @see java.lang.Runnable#run()
   */
  public void run() {

    _mdeProject = createProject(_name);
    addDefaultAttributes(_mdeProject);

    _resource.getContents().add(_mdeProject);
  }

  protected Project createProject(String name_p) {
    return CapellamodellerFactory.eINSTANCE.createProject(name_p);
  }

  protected void addDefaultAttributes(Project project_p) {

    // Set it its approach. (i.e Singleton Components or Reusable Components).
    CapellaProjectHelper.setProjectWithApproach(project_p, _approach);
    // Add the project progress monitoring constants
    CapellaProjectHelper.addProjectProgressStatus(project_p);
    // Add the model information for libraries references
    CapellaProjectHelper.addModelInformation(project_p);

  }

  /**
   * @see org.polarsys.capella.common.ef.command.AbstractCommand#getName()
   */
  @Override
  public String getName() {
    return "Create Capella project"; //$NON-NLS-1$
  }

  /**
   * Get the Capella project.
   * @return The Capella project.
   */
  public Project getProject() {
    return _mdeProject;
  }

}
