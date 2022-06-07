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
package org.polarsys.capella.test.model.ju.rename;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.commands.Category;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.IParameter;
import org.eclipse.core.commands.ParameterType;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.ltk.core.refactoring.CheckConditionsOperation;
import org.eclipse.ltk.core.refactoring.PerformRefactoringOperation;
import org.eclipse.ltk.core.refactoring.Refactoring;
import org.eclipse.ltk.core.refactoring.RefactoringContext;
import org.eclipse.ltk.core.refactoring.RefactoringContribution;
import org.eclipse.ltk.core.refactoring.RefactoringCore;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.resource.RenameResourceDescriptor;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.RenameResourceAction;
import org.eclipse.ui.commands.ICommandService;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;
import org.polarsys.capella.test.framework.api.ModelProviderHelper;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.IFileRequestor;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;
import org.polarsys.capella.test.model.ju.model.RenameModel;

public class RenameProjectWithOpenedSessionTestCase extends RenameModel {

  private boolean isRenamedProject = false;
  private String newProjectName;
  
  private static final String LIB_IMAGE_PROJECT = "images_library";
  
  private static final String LOCAL_IMAGE_PATH = "/images/Capella128x128.png";
  private static final String LIB_IMAGE_PATH = LIB_IMAGE_PROJECT + "/Capella256x256.png";

  @Override
  public List<String> getRequiredTestModels() {
    if (isRenamedProject) {
      return Arrays.asList(newProjectName, LIB_IMAGE_PROJECT);
    }
    
    ArrayList<String> lstProjects = new ArrayList<String>();
    for (String project: super.getRequiredTestModels()) {
        lstProjects.add(project);
    }
    lstProjects.add(LIB_IMAGE_PROJECT);
    return lstProjects;
  }
  
  /**
   * {@inheritDoc}
   * 
   * @throws Exception
   */
  @Override
  public void test() throws Exception {

    String oldProjectName = getRequiredTestModels().get(0);

    IFile file = getCapellaFileForLoadedModel(oldProjectName);
    if (file.exists()) {
      newProjectName = oldProjectName + "_renamed";

      Session sessionBeforeRename = getSessionForTestModel(oldProjectName);
      assertTrue("Session is not loaded for old project", sessionBeforeRename != null);

      IProject oldProject = file.getProject();

      RefactoringContribution contribution= RefactoringCore.getRefactoringContribution(RenameResourceDescriptor.ID);
      RenameResourceDescriptor descriptor= (RenameResourceDescriptor) contribution.createDescriptor();
      descriptor.setResourcePath(oldProject.getFullPath());
      descriptor.setNewName(newProjectName);
      descriptor.setUpdateReferences(true);
      
      RefactoringStatus status= new RefactoringStatus();
      final RefactoringContext context= descriptor.createRefactoringContext(status);
      try {
          final Refactoring refactoring= context != null ? context.getRefactoring() : null;
          assertTrue(status.isOK());

          PerformRefactoringOperation op= new PerformRefactoringOperation(refactoring, CheckConditionsOperation.ALL_CONDITIONS);
          op.run(null);
          RefactoringStatus validationStatus= op.getValidationStatus();
          assertFalse(validationStatus.hasFatalError());
          assertFalse(validationStatus.hasError());
      } catch (Exception e) {
          System.out.println(e.getLocalizedMessage());
      } finally {
          if (context != null)
              context.dispose();
      }

      isRenamedProject = true;

      IProject renamedProject = IResourceHelpers.getEclipseProjectInWorkspace(newProjectName);
      assertNotNull(renamedProject);
      assertTrue("Renamed project does not exist", renamedProject.exists());

      // Open renamed project, get the session, model and SE element
      renamedProject.open(new NullProgressMonitor());
      IFile airdFile = new IFileRequestor().search(renamedProject, CapellaResourceHelper.AIRD_FILE_EXTENSION).get(0);
      Session sessionAfterRename = SessionManager.INSTANCE.getSession(EcoreUtil2.getURI(airdFile), new NullProgressMonitor());

      assertTrue("Session is not loaded for new project", sessionAfterRename != null);
      sessionAfterRename.open(new NullProgressMonitor());
      Project capellaProject = SessionHelper.getCapellaProject(sessionAfterRename);
      capellaProject.getOwnedModelRoots().forEach(root -> {
          if (root instanceof SystemEngineering) {
              String rootSEDescription = ((SystemEngineering)root).getDescription();
              // Check updated path
              assertTrue("SystemEngineering element description does not contain new project path", rootSEDescription.contains(newProjectName + LOCAL_IMAGE_PATH));
              assertTrue("SystemEngineering element description still contain old project path", !rootSEDescription.contains(oldProjectName + LOCAL_IMAGE_PATH));
              // Check reference to external image and path not modified
              assertTrue("SystemEngineering element description does not contain lib image path that should not be updated", rootSEDescription.contains(LIB_IMAGE_PATH));
          }
      });
      
      GuiActions.closeSession(sessionAfterRename);
      GuiActions.deleteEclipseProject(renamedProject);

      IProject libImageProject = IResourceHelpers.getEclipseProjectInWorkspace(LIB_IMAGE_PROJECT);
      assertTrue("Lib image project is not present", libImageProject.exists());
      GuiActions.deleteEclipseProject(libImageProject);
      
    }
  }

  @Override
  protected void tearDown() throws Exception {
    // Do nothing as the projects have already been deleted
  }
}
