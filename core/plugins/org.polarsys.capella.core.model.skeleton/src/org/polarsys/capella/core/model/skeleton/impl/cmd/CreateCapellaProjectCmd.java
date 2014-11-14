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

package org.polarsys.capella.core.model.skeleton.impl.cmd;

import java.util.Collections;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerFactory;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.ProjectApproach;

/**
 * The command allowing to create the Capella project root.
 */
public class CreateCapellaProjectCmd extends AbstractReadWriteCommand {
  /** The Capella model filename extension. */
  public static final String XMI_EXTENSION = CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION;

  // cache of newly-created project.
  private IProject _eclipseProject;
  // The project name.
  private String _name;
  // The transactional editing domain.
  private TransactionalEditingDomain _domain;
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
  public CreateCapellaProjectCmd(IProject eclipseProject_p, String name_p, TransactionalEditingDomain domain_p, ProjectApproach approach_p) {
    _eclipseProject = eclipseProject_p;
    _name = name_p;
    _domain = domain_p;
    _approach = approach_p;
  }

  /**
   * @see java.lang.Runnable#run()
   */
  public void run() {
    // Creates the XMI serialization file.
    String xmiFilename = getXmiFileName();

    String fullPath = _eclipseProject.getFullPath().toString() + "/" + xmiFilename; //$NON-NLS-1$
    URI capellaModelURI = URI.createPlatformResourceURI(fullPath, true);

    // create a resource
    ResourceSet set = _domain.getResourceSet();
    Resource xmiResource = set.createResource(capellaModelURI);

    // create a resource doesn't load it, so we need to load it to set correct default EMF options like ExtendedMetaData
    try {
      xmiResource.save(Collections.emptyMap()); // we don't have specific options since resource is empty
      xmiResource = set.getResource(capellaModelURI, true); // without saving first, load can't work since file doens't exist
    } catch (Exception e) {
      // we couldn't do this
    }

    _mdeProject = createProject(_name);
    addDefaultAttributes(_mdeProject);

    xmiResource.getContents().add(_mdeProject);
  }

  protected Project createProject(String name_p) {
    return CapellamodellerFactory.eINSTANCE.createProject(name_p);
  }

  protected void addDefaultAttributes(Project project_p) {

    // Set it its approach. (i.e Singleton Components or Reusable Components).
    CapellaProjectHelper.setProjectWithApproach(project_p, _approach);
    // Add the project progress monitoring constants
    CapellaProjectHelper.addProjectProgressStatus(project_p);

  }

  /**
   * @see org.polarsys.capella.common.tig.ef.command.AbstractCommand#getName()
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

  // Gets the Sirius model complete file name.
  private String getXmiFileName() {
    return _name + "." + XMI_EXTENSION; //$NON-NLS-1$
  }
}
