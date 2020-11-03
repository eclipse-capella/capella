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
package org.polarsys.capella.core.preferences.configuration.project;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;
import org.polarsys.capella.core.preferences.configuration.project.nature.ConfigurationProjectNature;
import org.polarsys.capella.core.preferences.configuration.project.ui.wizards.ReferecedConfigurationProjectSelectionPage;
import org.polarsys.capella.core.preferences.project.configuration.project.ConfigurationPlugin;

/**
 *
 */
public class ProjectWizard extends BasicNewResourceWizard {

  private static final int STEP_TICK_COUNT = 100;

  private static final String CAPELLA_PROJECT_NATURE_ID = "org.polarsys.capella.project.nature";

  // Reuse of the new project page provided by the platform UI dialogs.
  protected WizardProjectCreationPage _localProjectDescriptionPage;

  // The New Model wizard page.
  protected NewModelWizardPage _modelPage;

  private ReferecedConfigurationProjectSelectionPage referencersProjectsPage;

  public static ProjectScope projectScope;

  /**
   * Create a page to enter local project description and ends by a viewpoint selection page.<br>
   * Other pages from the project one to viewpoint one are contributed by {@link #createReferencersProjectsPages()}. {@inheritDoc}
   */
  @Override
  public void addPages() {
    _localProjectDescriptionPage = createLocalProjectDescriptionPage();
    addPage(_localProjectDescriptionPage);
  }

  /**
   * Create logic pages between the first one and the last one of this wizard.<br>
   * Default implementation creates a model page i.e {@link NewModelWizardPage}.
   */
  protected void createReferencersProjectsPages() {

    referencersProjectsPage =
        new ReferecedConfigurationProjectSelectionPage("Capella Configuration Project", "Referencers Capella Project",
            ConfigurationPlugin.getImageDescriptor(ConfigurationPlugin.PROJECT_WIZARD_CONFIGURATION_FOLDER_IMG),
            new String[] { CAPELLA_PROJECT_NATURE_ID });
    addPage(referencersProjectsPage);

  }

  /**
   * Create model page.
   */
  protected NewModelWizardPage createProjectModelPage() {
    NewModelWizardPage modelPage = new NewModelWizardPage("model.creation.page", null); //$NON-NLS-1$
    modelPage.setDescription("Capella Configuration Project"); //$NON-NLS-1$
    modelPage.setTitle("Configuration Project"); //$NON-NLS-1$
    return modelPage;
  }

  /**
   * Create local project description page.
   */
  protected WizardProjectCreationPage createLocalProjectDescriptionPage() {
    WizardProjectCreationPage mainPage = new WizardProjectCreationPage("CapellaProjNewPage", getSelection()) {//$NON-NLS-1$
          /**
           * @see org.polarsys.capella.core.preferences.configuration.project.WizardProjectCreationPage.sirius.ui.project.internal.WizardNewProjectCreationPage#handleDefaultProjectLocation(org.eclipse.swt.widgets.Composite)
           */
          @Override
          protected ProjectContentsLocationArea handleDefaultProjectLocation(Composite parent_p) {
            return new ConfigurationProjectContentsLocationArea(getErrorReporter(), parent_p);
          }
        };
    // Force to call the setInitialProjectName to take into account a contribution through defaultProjectLocationProvider extension-point.
    mainPage.setInitialProjectName(null);
    mainPage.setDescription("Create a new project configuration"); //$NON-NLS-1$
    mainPage.setTitle("Capella Configuration Project"); //$NON-NLS-1$
    mainPage.setImageDescriptor(ConfigurationPlugin.getWizadrConfigurationProjectIcon());
    return mainPage;
  }

  /**
   * @see org.eclipse.jface.wizard.Wizard#canFinish()
   */
  @Override
  public boolean canFinish() {
    // We can finish if current page can finish.
    IWizardPage currentPage = getContainer().getCurrentPage();
    return currentPage.isPageComplete();
  }

  @Override
  public boolean performFinish() {

    try {
      getContainer().run(false, false, new IRunnableWithProgress() {

        @Override
        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
          int stepCount = 7;
          SubMonitor progress = SubMonitor.convert(monitor, "NewProjectWizard.CreateProject_Title" + getEclipseProjectName(), STEP_TICK_COUNT * stepCount); //$NON-NLS-1$
          // 1 - Creates the Eclipse Project with the Capella Project nature.
          try {
            createNewEclipseProject(getEclipseProjectName(), progress.newChild(STEP_TICK_COUNT));
          } catch (Exception ex) {
            ex.printStackTrace();
          }

        }

      });
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return true;
  }

  // Creates a new project resource with the name selected in the wizard page. Project creation is wrapped in a <code>WorkspaceModifyOperation</code>.
  public IProject createNewEclipseProject(String projectName_p, IProgressMonitor monitor_p) throws Exception {
    IProject project = null;
    SubMonitor progress = SubMonitor.convert(monitor_p, STEP_TICK_COUNT);
    try {
      IWorkspace workspace = ResourcesPlugin.getWorkspace();
      IWorkspaceRoot workspaceRoot = workspace.getRoot();
      project = workspaceRoot.getProject(projectName_p);
      if (!project.exists()) {
        // Get a project description.
        IPath defaultPath = Platform.getLocation();
        IPath newPath = _localProjectDescriptionPage.getLocationPath();
        if (defaultPath.equals(newPath)) {
          newPath = null;
        }

        IProjectDescription description = null;

        if ((referencersProjectsPage != null) && !referencersProjectsPage.getSelectedConfigurationsProjects().isEmpty()) {
          description = createProjectDescription(project, newPath, true);
        } else {
          description = createProjectDescription(project, newPath, false);
        }

        // Creates project.
        project.create(description, progress.newChild(STEP_TICK_COUNT / 2));
      }

      // Ensure the progress monitor work remaining count.
      progress.setWorkRemaining(STEP_TICK_COUNT / 2);
      project.open(progress.newChild(STEP_TICK_COUNT / 2));

    } finally {
      progress.done();
    }
    return project;
  }

  /**
   * Creates a folder and all parent folders if not existing. Project must exist. <code>org.eclipse.ui.dialogs.ContainerGenerator</code> is too heavy (creates a
   * runnable)
   */
  public static void createFolder(IFolder folder, boolean force, boolean local, IProgressMonitor monitor) throws CoreException {
    if (!folder.exists()) {
      IContainer parent = folder.getParent();
      if (parent instanceof IFolder) {
        createFolder((IFolder) parent, force, local, null);
      }
      folder.create(force, local, monitor);
    }
  }

  // Creates the project description.
  public IProjectDescription createProjectDescription(IProject projectHandle_p, IPath projectPath_p, boolean hasReferencersProjects) {
    // Set project name and location.
    IWorkspace workspace = ResourcesPlugin.getWorkspace();
    String name = projectHandle_p.getName();
    IProjectDescription description = workspace.newProjectDescription(name);

    // Creates omitted path nodes & set the location into the description.
    if (null != projectPath_p) {
      description.setLocation(projectPath_p.append(name));
    }
    // Add the project nature.
    if (!description.hasNature(ConfigurationProjectNature.NATURE_ID)) {
      String[] newNatures = new String[] { ConfigurationProjectNature.NATURE_ID };
      description.setNatureIds(newNatures);
    }
    if (hasReferencersProjects) {
      addConfigurationProjectReferences(projectHandle_p, description);
    }
    return description;

  }

  /**
   * @param project_p
   * @param description
   * @return
   */
  private IProjectDescription addConfigurationProjectReferences(IProject project_p, IProjectDescription description) {
    List<IProject> selectedConfigurationProject = referencersProjectsPage.getSelectedConfigurationsProjects();

    try {
      for (Object element : selectedConfigurationProject) {
        IProject capellaProject = (IProject) element;
        final IProjectDescription capellaProjectDescription = capellaProject.getDescription();
        IProject[] referencesT = capellaProjectDescription.getReferencedProjects();

        List<IProject> references = new ArrayList<IProject>();
        for (IProject iProject : referencesT) {
          references.add(iProject);

        }
        references.add(project_p);

        IProject[] referencedProjects = new IProject[references.size()];
        for (int i = 0; i < (referencedProjects.length); i++) {
          referencedProjects[i] = references.get(i);

        }
        capellaProjectDescription.setReferencedProjects(referencedProjects);

        project_p.refreshLocal(IResource.DEPTH_INFINITE, null);
      }

    } catch (CoreException exception_p) {
      StringBuilder loggerMessage = new StringBuilder("ProjectWizard.addConfigurationProjectReferences(..) _ "); //$NON-NLS-1$
    }
    return description;

  }

  /**
   * @return
   */
  protected String getEclipseProjectName() {
    return _localProjectDescriptionPage.getProjectName();
  }

}
