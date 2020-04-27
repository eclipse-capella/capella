/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.ui.refactoring;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.RenameArguments;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.tools.api.command.ui.NoUICallback;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.Messages;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.sirius.ui.helper.ResourceHelper;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

public class WorkspaceImagePathChange extends Change {

  /**
   * The hierarchical position of the renamed container relative to the workspace root. <br>
   * A project is at position 0. <br>
   * A folder inside a project is at position 1 (project/folder). <br>
   * In this example project/folderA/folderB, folderB is at position 2.
   * 
   */
  private int position;

  /**
   * The rename arguments
   */
  private RenameArguments renameArguments;

  /**
   * The container touched by the change
   */
  private IContainer container;

  /**
   * 
   * @param container
   * @param renameArguments
   */
  public WorkspaceImagePathChange(IContainer container, RenameArguments renameArguments) {
    this.container = container;
    this.renameArguments = renameArguments;
    this.position = getPosition();
  }

  @Override
  public String getName() {
    return org.polarsys.capella.core.sirius.ui.internal.Messages.update_workspace_image_paths;
  }

  @Override
  public void initializeValidationData(IProgressMonitor pm) {
    // Do nothing
  }

  @Override
  public RefactoringStatus isValid(IProgressMonitor pm) throws CoreException {
    return new RefactoringStatus();
  }

  @Override
  public Change perform(IProgressMonitor pm) throws CoreException {
    // This is the container to be renamed
    if (container.isAccessible()) {
      IProject project = container.getProject();
      // Get the existing sessions for the relevant project
      Collection<Session> existingSessions = SessionHelper.getExistingSessions(project);
      // If we are renaming a project with closed session
      if (existingSessions.isEmpty()) {
        // If session(s) are not open for the container, force open here
        openSession(project, pm);
        existingSessions = SessionHelper.getExistingSessions(project);
      }
      return performChange(existingSessions, pm);
    }
    return null;
  }

  private Change performChange(Collection<Session> sessions, IProgressMonitor pm) {

    for (Session session : sessions) {
      ExecutionManager manager = TransactionHelper.getExecutionManager(session);
      manager.execute(new AbstractReadWriteCommand() {

        @Override
        public void run() {
          if (renameArguments.getUpdateReferences()
              && (position > 0 || position == 0 && !ResourceHelper.collectImageFiles(container).isEmpty())) {
            // Get all diagrams from the session
            pm.subTask(org.polarsys.capella.core.sirius.ui.internal.Messages.collecting_diagrams);
            Collection<DRepresentationDescriptor> descriptors = DialectManager.INSTANCE.getAllRepresentationDescriptors(session);
            // Update the workspace images for each diagram
            for (DRepresentationDescriptor descriptor : descriptors) {
              pm.subTask(
                  NLS.bind(org.polarsys.capella.core.sirius.ui.internal.Messages.updating_diagram, descriptor.getName()));
              updateWorkspaceImagePath(descriptor.getRepresentation());
            }
          }
          // If it's an IProject rename => rename Capella model objects: Project & SystemEngineering
          if (position == 0) {
            renameRelevantCapellaObjects(session);
          }
        }
      });

      saveSession(session, pm);
    }
    return null;
  }
  
  private void renameRelevantCapellaObjects(Session session) {
    Project capellaProject = SessionHelper.getCapellaProject(session);
    if (capellaProject != null) {
      capellaProject.setName(renameArguments.getNewName());
      // Rename the model object SystemEngineering
      SystemEngineering systemEngineering = SystemEngineeringExt.getSystemEngineering(capellaProject);
      if (systemEngineering != null) {
        systemEngineering.setName(renameArguments.getNewName());
      }
    }
  }

  private void saveSession(Session session, IProgressMonitor pm) {
    pm.subTask(Messages.DAnalysisSessionImpl_saveMsg);
    session.save(pm);
  }

  private void updateWorkspaceImagePath(DRepresentation representation) {
    if (representation != null) {
      for (DRepresentationElement element : representation.getOwnedRepresentationElements()) {
        Iterator<EObject> iterator = element.eAllContents();
        while (iterator.hasNext()) {
          EObject eObject = iterator.next();
          if (ResourceHelper.isCustomizedWorkspaceImageWorkspacePath(eObject)) {
            updatePath((WorkspaceImage) eObject);
          }
        }
      }
    }
  }

  private void updatePath(WorkspaceImage workspaceImage) {
    String workspacePath = workspaceImage.getWorkspacePath();
    Path path = new Path(workspacePath);
    String segment = path.segment(position);
    String oldName = container.getName();
    if (oldName.equals(segment)) {
      workspacePath = workspacePath.replace(oldName, renameArguments.getNewName());
      workspaceImage.setWorkspacePath(workspacePath);
    }
  }

  private void openSession(IProject project, IProgressMonitor monitor) {
    Collection<IFile> airdFiles = ResourceHelper.getAirdFilesToOpen(project);
    for (IFile airdFile : airdFiles) {
      monitor.subTask(Messages.DAnalysisSessionImpl_openMsg);
      SessionManager.INSTANCE.openSession(EcoreUtil2.getURI(airdFile), monitor, new NoUICallback());
    }
  }

  private int getPosition() {
    if (container instanceof IProject) {
      return 0;
    }
    IPath location = container.getFullPath();
    if (location != null) {
      return location.segments().length - 1;
    }
    return -1;
  }

  @Override
  public Object getModifiedElement() {
    return null;
  }
}
