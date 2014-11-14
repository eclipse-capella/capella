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
package org.polarsys.capella.core.platform.sirius.ui.project.operations;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.PartInitException;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.platform.sirius.ui.project.CapellaNature;
import org.polarsys.capella.core.platform.sirius.ui.project.Messages;
import org.polarsys.capella.core.platform.sirius.ui.session.CapellaSessionHelper;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

/**
 * This class allows to create a new capella project
 */
public class SessionCreationHelper {

  private static final Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.MODEL);

  /**
   * Create a default URI for aird file name for a project named projectName located in the given eclipse project
   */
  public static URI buildAirdFileName(IProject eclipseProject_p, String projectName_p) {
    IPath path = eclipseProject_p.getFullPath();
    String airdFilename = projectName_p + ICommonConstants.POINT_CHARACTER + CapellaResourceHelper.AIRD_FILE_EXTENSION;
    path = path.append(airdFilename);
    URI airdResourceURI = URI.createPlatformResourceURI(path.toString(), true);
    return airdResourceURI;
  }

  /**
   * Create an aird resource (and so on session) for the given semantic resource file
   */
  protected Session createAirdResource(IProject eclipseProject_p, IFile semanticResource_p, String projectName_p, IProgressMonitor monitor_p) {
    // Builds the .aird filename.
    URI airdResourceURI = buildAirdFileName(eclipseProject_p, projectName_p);
    return createAirdSession(Collections.singletonList(semanticResource_p), airdResourceURI, monitor_p);
  }

  /**
   * Create an aird session with all given semantic files
   */
  public Session createAirdSession(List<IFile> semanticFiles_p, URI airdResourceURI_p, IProgressMonitor monitor_p) {
    Session session = null;
    try {
      session = CapellaSessionHelper.createLocalSession(semanticFiles_p, airdResourceURI_p, monitor_p);
    } catch (InterruptedException ex) {
      // Do nothing.
    } catch (InvocationTargetException ex) {
    } catch (PartInitException ex) {
    } catch (IOException ex) {
    }
    return session;
  }

  /**
   * Create a new eclipse project stored in the given location
   */
  public IProject createNewEclipseProject(String projectName_p, IPath location_p, Collection<IProject> referencedProjects_p, IProgressMonitor monitor_p) {
    IProject project = null;
    SubMonitor progress = SubMonitor.convert(monitor_p, 2);
    try {
      IWorkspace workspace = ResourcesPlugin.getWorkspace();
      IWorkspaceRoot workspaceRoot = workspace.getRoot();
      project = workspaceRoot.getProject(projectName_p);
      if (!project.exists()) {

        IProjectDescription description = createProjectDescription(project, location_p, referencedProjects_p);

        // Creates project.
        project.create(description, progress.newChild(1));

      }
      // Ensure the progress monitor work remaining count.
      project.open(progress.newChild(1));

    } catch (CoreException exception_p) {
    } finally {
      progress.done();
    }
    return project;

  }

  /**
   * Create a project description with given parameters
   */
  protected IProjectDescription createProjectDescription(IProject project_p, IPath projectPath_p, Collection<IProject> referencedProjects_p) {
    // Set project name and location.
    IWorkspace workspace = ResourcesPlugin.getWorkspace();
    String name = project_p.getName();
    IProjectDescription description = workspace.newProjectDescription(name);

    // Creates omitted path nodes & set the location into the description.
    if (null != projectPath_p) {
      description.setLocation(projectPath_p.append(name));
    }

    setNatureProject(project_p, description);
    description = addReferencedProjects(project_p, description, referencedProjects_p);

    return description;

  }

  /**
   * Method to set nature of the given description while project description creation method 
   */
  protected void setNatureProject(IProject project_p, IProjectDescription description_p) {
    // Add the project nature.
    if (!description_p.hasNature(CapellaNature.ID)) {
      String[] newNatures = new String[] { CapellaNature.ID };
      description_p.setNatureIds(newNatures);
    }
  }

  /**
   * Add referenced projects to the given description
   */
  protected IProjectDescription addReferencedProjects(IProject project_p, IProjectDescription description, Collection<IProject> referencedProjects_p) {
    IProject[] references = description.getReferencedProjects();

    int i = 0;
    IProject[] referencedProjects = new IProject[references.length + referencedProjects_p.size()];
    for (i = 0; i < references.length; i++) {
      referencedProjects[i] = references[i];
    }

    Iterator<IProject> projects = referencedProjects_p.iterator();
    while (projects.hasNext()) {
      referencedProjects[i] = projects.next();
      i++;
    }

    description.setReferencedProjects(referencedProjects);

    return description;

  }

  /**
   * Create the semantic resource in the given project
   */
  protected IFile createSemanticResource(final IProject eclipseProject_p, final IProgressMonitor monitor_p) {

    SubMonitor progress = SubMonitor.convert(monitor_p, 4);
    ExecutionManager manager = ExecutionManagerRegistry.getInstance().addNewManager();
    TransactionalEditingDomain domain = manager.getEditingDomain();

    ICommand command = null;

    try {

      progress.beginTask("Create an empty resource", 1);
      Resource semanticResource = CapellaResourceHelper.createCapellaResource(eclipseProject_p, eclipseProject_p.getName(), domain);
      progress.worked(1);

      progress.beginTask("Create semantic root element", 1);
      command = createInitialElementsCommand(semanticResource, eclipseProject_p.getName(), monitor_p);
      if (command != null) {
        manager.execute(command);
      }
      progress.worked(1);

      progress.beginTask("Create initial skeletton", 1);
      command = updateInitialElementsCommand(semanticResource, eclipseProject_p.getName(), monitor_p);
      if (command != null) {
        manager.execute(command);
      }
      progress.worked(1);

      try {
        progress.beginTask("Save semantic model", 1);
        semanticResource.save(Collections.emptyMap());
      } catch (Exception e) {
        // we couldn't do this
      }
      progress.worked(1);

      IFile semanticFile = EcoreUtil2.getFile(semanticResource);
      return semanticFile;

    } finally {
      domain.dispose();
      progress.done();
      ExecutionManagerRegistry.getInstance().removeManager(manager);
    }

  }

  /**
   * Create root semantic element in the given resource
   */
  protected ICommand createInitialElementsCommand(Resource semanticResource_p, String name_p, IProgressMonitor monitor_p) {
    return null;
  }

  /**
   * Update root semantic element in the given resource
   * (for instance, initialize sub elements)
   */
  protected ICommand updateInitialElementsCommand(Resource semanticResource_p, String name_p, IProgressMonitor monitor_p) {
    return null;
  }

  /**
   * Create and open a new project
   */
  public Session createFullProject(String projectName_p, IPath newPath_p, Collection<IProject> referencedProjects_p,
      final Collection<Viewpoint> selectedViewpoints_p, IProgressMonitor monitor_p) throws InvocationTargetException, InterruptedException {

    try {

      int stepCount = 7;
      SubMonitor progress = SubMonitor.convert(monitor_p, Messages.getString("NewProjectWizard.CreateProject_Title") + projectName_p, stepCount); //$NON-NLS-1$

      // 1 - Creates the Eclipse Project
      IProject eclipseProject = null;

      eclipseProject = createNewEclipseProject(projectName_p, newPath_p, referencedProjects_p, progress.newChild(1));

      IFile semanticFile = createSemanticResource(eclipseProject, progress.newChild(1));

      // 4- Creates the aird resource.
      Session session = createAirdResource(eclipseProject, semanticFile, projectName_p, progress.newChild(1));
      if (null == session) {
        throw new InterruptedException("Cannot create the session"); //$NON-NLS-1$
      }

      //open session 
      session.open(progress.newChild(1));

      // initialize the corresponding model
      // should be done in a listener but event opened is triggered while the semantic file has not been created (in the case of a project creation)
      // FIXME should not be useful anymore. now we open session after having added semantic resources.
      ILibraryManager.INSTANCE.getModel(session.getTransactionalEditingDomain());

      // 5- Selected Viewpoints handling.
      SessionHelper.activateViewpoints(session, selectedViewpoints_p);
      progress.worked(1);

      try {
        // 6- Save it to give a non dirty open project to the end-user.
        if (SessionStatus.DIRTY.equals(session.getStatus())) {
          session.save(progress.newChild(1));
        }
      } catch (Exception ex) {
        __logger.debug(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.MODEL));

      }

      // Open the editing session.
      IEditingSession editingSession = SessionUIManager.INSTANCE.getOrCreateUISession(session);
      editingSession.open();

      return session;

    } finally {
      monitor_p.done();
    }
  }

}
