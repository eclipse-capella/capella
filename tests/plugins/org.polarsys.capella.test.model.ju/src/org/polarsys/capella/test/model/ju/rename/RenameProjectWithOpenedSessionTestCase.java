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
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.RenameResourceAction;
import org.eclipse.ui.commands.ICommandService;
import org.polarsys.capella.test.framework.api.ModelProviderHelper;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;
import org.polarsys.capella.test.model.ju.model.RenameModel;

public class RenameProjectWithOpenedSessionTestCase extends RenameModel {

  private static final String LTK_RENAME_ID = "org.eclipse.ltk.ui.refactoring.commands.renameResource";

  private boolean isRenamedProject = false;
  private String newProjectName;

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
      newProjectName = "renamed_" + oldProjectName;

      Session sessionBeforeRename = getSessionForTestModel(oldProjectName);
      assertTrue("Session is not loaded for old project", sessionBeforeRename != null);

      IProject oldProject = file.getProject();

      // Undefine the Command in order not to open the input dialog when running the rename action.
      RenameCommandStateToggle cmdStateToggle = new RenameCommandStateToggle();
      cmdStateToggle.undefine();
      try {
        renameResource(oldProject);
      } catch (Exception e) {
        assertTrue("Exception while renaming the project\n" + e.getMessage(), false);
      }

      isRenamedProject = true;

      // Define the Command again
      cmdStateToggle.define();

      IProject renamedProject = IResourceHelpers.getEclipseProjectInWorkspace(newProjectName);
      assertNotNull(renamedProject);
      assertTrue("Renamed project does not exist", renamedProject.exists());

      assertTrue(sessionBeforeRename.getAllSessionResources().size() == 0);
      assertTrue(sessionBeforeRename.getSemanticResources().size() == 0);
    }
  }

  private void renameResource(IResource resource) {
    RenameResourceAction renameResourceAction = new RenameResourceAction(getShellProvider()) {
      @Override
      protected String queryNewResourceName(IResource resource) {
        return newProjectName;
      }
    };

    renameResourceAction.selectionChanged(new StructuredSelection(resource));
    renameResourceAction.run();
  }

  @Override
  public List<String> getRequiredTestModels() {
    if (isRenamedProject) {
      return Arrays.asList(newProjectName);
    }
    return super.getRequiredTestModels();
  }

  @Override
  protected void tearDown() throws Exception {
    // Release test models except the renamed one because session is already unloaded
    List<String> projectNamesToLoad = getRequiredTestModels();
    if (projectNamesToLoad != null) {
      for (String modelName : projectNamesToLoad) {
        if (!modelName.equals(newProjectName)) {
          ModelProviderHelper.getInstance().getModelProvider().releaseTestModel(modelName, this);
        }
      }
    }
  }

  private IShellProvider getShellProvider() {
    return new IShellProvider() {

      @Override
      public Shell getShell() {
        return null;
      }
    };
  }

  /**
   * A helper class to toggle the definition state of the rename command.
   * 
   * @return
   */
  private class RenameCommandStateToggle {

    private String name;
    private Category category;
    private IParameter[] parameters;
    private ParameterType returnType;
    private String description;

    // This attribute to ensure that the method was undefined using an instance of this class before calling define
    // again.
    private boolean undefined = false;

    protected void define() {
      ICommandService commandService = PlatformUI.getWorkbench().getService(ICommandService.class);
      Command cmd = commandService.getCommand(LTK_RENAME_ID);
      if (cmd != null && undefined && !cmd.isDefined()) {
        undefined = false;
        cmd.define(name, description, category, parameters, returnType);
      }
    }

    protected void undefine() {
      ICommandService commandService = PlatformUI.getWorkbench().getService(ICommandService.class);
      Command cmd = commandService.getCommand(LTK_RENAME_ID);
      if (cmd != null && !undefined && cmd.isDefined()) {
        undefined = true;
        try {
          name = cmd.getName();
          category = cmd.getCategory();
          parameters = cmd.getParameters();
          returnType = cmd.getReturnType();
          description = cmd.getDescription();
          cmd.undefine();
        } catch (NotDefinedException e) {
          // Ignore exception
        }
      }
    }
  }
}
