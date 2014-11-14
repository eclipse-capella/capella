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

package org.polarsys.capella.core.platform.sirius.ui.project;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

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
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallback;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.ProjectApproach;
import org.polarsys.capella.core.model.skeleton.EngineeringDomain;
import org.polarsys.capella.core.model.skeleton.impl.cmd.CreateCapellaProjectCmd;
import org.polarsys.capella.core.platform.sirius.ui.project.internal.CapellaProjectContentsLocationArea;
import org.polarsys.capella.core.platform.sirius.ui.project.internal.ProjectContentsLocationArea;
import org.polarsys.capella.core.platform.sirius.ui.project.internal.WizardNewProjectCreationPage;
import org.polarsys.capella.core.platform.sirius.ui.project.operations.CreateCapellaProjectOperation;
import org.polarsys.capella.core.platform.sirius.ui.session.CapellaSessionHelper;
import org.polarsys.capella.core.preferences.configuration.project.nature.ConfigurationProjectNature;
import org.polarsys.capella.core.preferences.configuration.project.ui.wizards.ReferecedConfigurationProjectSelectionPage;
import org.polarsys.capella.core.sirius.ui.actions.OpenSessionAction;
import org.polarsys.capella.core.sirius.ui.runnable.OpenRepresentationsRunnable;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.tig.ef.command.AbstractNonDirtyingCommand;

/**
 * The wizard allowing to initialize a new Capella project.
 */
public class NewProjectWizard extends BasicNewResourceWizard {
  /**
   * Step tick count
   */
  protected static final int STEP_TICK_COUNT = 100;

  // Log4j reference logger.
  private static final Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);

  // Reuse of the new project page provided by the platform UI dialogs.
  protected WizardNewProjectCreationPage _localProjectDescriptionPage;
  // The New Model wizard page.
  protected NewModelWizardPage _modelPage;

  /**
   * Created session associated to created project.
   */
  protected Session _session;

  private SortedMap<Viewpoint, Boolean> _viewpointsMap;

  private WizardPage _viewpointWizardPage;

  private ReferecedConfigurationProjectSelectionPage referencedConfigurationProjectPage;

  /**
   * Constructs the wizard allowing to initialize a new Capella project.
   */
  public NewProjectWizard() {
    _viewpointsMap = new TreeMap<Viewpoint, Boolean>(new ViewpointRegistry.ViewpointComparator());
  }

  /**
   * Create a page to enter local project description and ends by a viewpoint selection page.<br>
   * Other pages from the project one to viewpoint one are contributed by {@link #createLogicPages()}. {@inheritDoc}
   */
  @Override
  public void addPages() {
    _localProjectDescriptionPage = createLocalProjectDescriptionPage();
    addPage(_localProjectDescriptionPage);

    createLogicPages();

    _viewpointWizardPage = ViewpointSelection.createWizardPage(CreateCapellaProjectCmd.XMI_EXTENSION, _viewpointsMap);
    addPage(_viewpointWizardPage);

    createReferencedConfigurationProjectsPage();

  }

  /**
 * 
 */
  private void createReferencedConfigurationProjectsPage() {
    referencedConfigurationProjectPage =
        new ReferecedConfigurationProjectSelectionPage(Messages.getString("NewModelWizard.title"), new String[] { ConfigurationProjectNature.NATURE_ID });
    addPage(referencedConfigurationProjectPage);

  }

  /**
   * Create logic pages between the first one and the last one of this wizard.<br>
   * Default implementation creates a model page i.e {@link NewModelWizardPage}.
   */
  protected void createLogicPages() {
    _modelPage = createModelPage();
    addPage(_modelPage);
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

  protected Session createAirdResource(IProject eclipseProject_p, String projectName_p, IProgressMonitor monitor_p) {
    // Builds the .aird filename.
    URI airdResourceURI = buildAirdFileName(eclipseProject_p, projectName_p);
    // Get the semantic model.
    String capellaModellerFilename = projectName_p + ICommonConstants.POINT_CHARACTER + CreateCapellaProjectCmd.XMI_EXTENSION;
    IPath capellaModellerPath = new Path(capellaModellerFilename);
    IFile capellaModelerFile = eclipseProject_p.getFile(capellaModellerPath);
    return createAirdSession(Collections.singletonList(capellaModelerFile), airdResourceURI, monitor_p);
  }

  protected Session createAirdSession(List<IFile> capellaModelerFiles_p, URI airdResourceURI_p, IProgressMonitor monitor_p) {
    Session session = null;
    try {
      session = CapellaSessionHelper.createLocalSession(capellaModelerFiles_p, airdResourceURI_p, monitor_p);
    } catch (InterruptedException ex) {
      // Do nothing.
    } catch (InvocationTargetException ex) {
      __logger.debug(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.UI));
    } catch (PartInitException ex) {
      __logger.debug(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.UI));
    } catch (IOException ex) {
      __logger.debug(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.UI));
    }
    return session;
  }

  /**
   * @param eclipseProject_p
   * @param projectName_p
   * @return
   */
  protected URI buildAirdFileName(IProject eclipseProject_p, String projectName_p) {
    IPath path = eclipseProject_p.getFullPath();
    String airdFilename = projectName_p + ICommonConstants.POINT_CHARACTER + CapellaResourceHelper.AIRD_FILE_EXTENSION;
    path = path.append(airdFilename);
    URI airdResourceURI = URI.createPlatformResourceURI(path.toString(), true);
    return airdResourceURI;
  }

  /**
   * Create local project description page.
   */
  protected WizardNewProjectCreationPage createLocalProjectDescriptionPage() {
    WizardNewProjectCreationPage mainPage = new WizardNewProjectCreationPage("CapellaProjNewPage") {//$NON-NLS-1$
          /**
           * @see org.polarsys.capella.core.platform.sirius.ui.project.internal.WizardNewProjectCreationPage#handleDefaultProjectLocation(org.eclipse.swt.widgets.Composite)
           */
          @Override
          protected ProjectContentsLocationArea handleDefaultProjectLocation(Composite parent_p) {
            return new CapellaProjectContentsLocationArea(getErrorReporter(), parent_p);
          }
        };
    // Force to call the setInitialProjectName to take into account a contribution through defaultProjectLocationProvider extension-point.
    mainPage.setInitialProjectName(null);
    mainPage.setDescription(Messages.getString("NewProjectWizard.description")); //$NON-NLS-1$
    mainPage.setTitle(Messages.getString("NewProjectWizard.title")); //$NON-NLS-1$
    return mainPage;
  }

  // Creates and returns the new Capella project.
  protected Project createCapellaProject(IProject eclipseProject_p, IProgressMonitor monitor_p) {
    // Run the new model creation operation.
    try {
      // Create the Capella project.
      CreateCapellaProjectOperation projectOp = new CreateCapellaProjectOperation(eclipseProject_p, eclipseProject_p.getName(), getProjectApproach());

      projectOp.run(monitor_p);
      // Get created capella project.
      Project capellaProject = projectOp.getProject();
      return capellaProject;
    } catch (InterruptedException ex) {
      __logger.debug(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.UI));
    } catch (InvocationTargetException ex) {
      __logger.debug(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.UI));
    }
    return null;
  }

  protected ProjectApproach getProjectApproach() {
    return _localProjectDescriptionPage.getProjectApproachSelection();
  }

  /**
   * Create model page.
   */
  protected NewModelWizardPage createModelPage() {
    NewModelWizardPage modelPage = new NewModelWizardPage("model.creation.page", null); //$NON-NLS-1$
    modelPage.setDescription(Messages.getString("NewModelWizard.description")); //$NON-NLS-1$
    modelPage.setTitle(Messages.getString("NewModelWizard.title")); //$NON-NLS-1$
    return modelPage;
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

        if ((referencedConfigurationProjectPage != null) && !referencedConfigurationProjectPage.getSelectedConfigurationsProjects().isEmpty()) {
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
   * @param project_p
   * @param description
   * @return
   */
  protected IProjectDescription addConfigurationProjectReferences(IProject project_p, IProjectDescription description) {
    List<IProject> selectedConfigurationProject = referencedConfigurationProjectPage.getSelectedConfigurationsProjects();

    IProject[] references = description.getReferencedProjects();

    IProject[] referencedProjects = new IProject[references.length + selectedConfigurationProject.size()];
    for (int i = 0; i < references.length; i++) {
      referencedProjects[i] = references[i];
    }

    for (int i = references.length; i < selectedConfigurationProject.size(); i++) {
      referencedProjects[i] = selectedConfigurationProject.get(i);
    }

    description.setReferencedProjects(referencedProjects);

    return description;

  }

  // Creates the project description.
  public IProjectDescription createProjectDescription(IProject projectHandle_p, IPath projectPath_p, boolean withConfigProject) {
    // Set project name and location.
    IWorkspace workspace = ResourcesPlugin.getWorkspace();
    String name = projectHandle_p.getName();
    IProjectDescription description = workspace.newProjectDescription(name);

    // Creates omitted path nodes & set the location into the description.
    if (null != projectPath_p) {
      description.setLocation(projectPath_p.append(name));
    }
    // Add the project nature.
    if (!description.hasNature(CapellaNature.ID)) {
      String[] newNatures = new String[] { CapellaNature.ID };
      description.setNatureIds(newNatures);
    }
    if (withConfigProject) {
      description = addConfigurationProjectReferences(projectHandle_p, description);
    }

    return description;

  }

  // Make root diagram the welcome page
  public List<DRepresentation> doGetRepresentations(final Session currentSession_p) {
    List<DRepresentation> representationsToOpen = new ArrayList<DRepresentation>();

    Collection<DView> ownedViews = currentSession_p.getOwnedViews();
    Iterator<DView> viewsIterator = ownedViews.iterator();
    while (viewsIterator.hasNext()) {
      DView view = viewsIterator.next();
      EList<DRepresentation> allRepresentations = view.getAllRepresentations();

      for (DRepresentation representation : allRepresentations) {
        if (representation instanceof DSemanticDiagram) {
          DSemanticDiagram semanticDiagram = (DSemanticDiagram) representation;
          EObject target = semanticDiagram.getTarget();
          if ((null != target) && (target instanceof SystemEngineering)) {
            representationsToOpen.add(semanticDiagram);
          }
        }
      }
    }
    return representationsToOpen;
  }

  /**
   * Get the local project description page i.e the first page of this wizard.
   * @return the localProjectDescriptionPage
   */
  protected IWizardPage getLocalProjectDescriptionPage() {
    return _localProjectDescriptionPage;
  }

  /**
   * @see org.eclipse.jface.wizard.Wizard#getNextPage(org.eclipse.jface.wizard.IWizardPage)
   */
  @Override
  public IWizardPage getNextPage(IWizardPage page_p) {
    IWizardPage nextPage = super.getNextPage(page_p);
    // Set the project name as model name.
    if ((null != _modelPage) && (_modelPage == nextPage)) {
      String projectName = getEclipseProjectName();
      _modelPage.setModelNameFieldValue(projectName);
    }
    return nextPage;
  }

  /**
   * Gets the current session.
   * @return The current session.
   */
  public Session getSession() {
    return _session;
  }

  /**
   * @see org.eclipse.ui.wizards.newresource.BasicNewResourceWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
   */
  @Override
  public void init(IWorkbench workbench_p, IStructuredSelection currentSelection_p) {
    super.init(workbench_p, currentSelection_p);
    setWindowTitle(Messages.getString("NewProjectWizard.window.title")); //$NON-NLS-1$
    ImageDescriptor descriptor = CapellaProjectActivator.getDefault().getImageDescriptor("projectWizard.png"); //$NON-NLS-1$
    if (null == descriptor) {
      descriptor = ImageDescriptor.getMissingImageDescriptor();
    }
    setDefaultPageImageDescriptor(descriptor);
    setNeedsProgressMonitor(true);
  }

  /**
   * Creates a project with a <code>CapellaNature</code> association.
   * @return <code>true</code> to indicate the finish request was accepted, and <code>false</code> to indicate that the finish request was refused
   * @see org.eclipse.jface.wizard.IWizard#performFinish()
   */
  @Override
  public boolean performFinish() {
    try {
      getContainer().run(false, false, new IRunnableWithProgress() {
        /**
         * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
         */
        @SuppressWarnings("synthetic-access")
        public void run(IProgressMonitor monitor_p) throws InvocationTargetException, InterruptedException {
          try {
            int stepCount = 7;
            SubMonitor progress =
                SubMonitor.convert(monitor_p, Messages.getString("NewProjectWizard.CreateProject_Title") + getEclipseProjectName(), STEP_TICK_COUNT * stepCount); //$NON-NLS-1$
            // 1 - Creates the Eclipse Project with the Capella Project nature.
            IProject eclipseProject = null;
            try {
              eclipseProject = createNewEclipseProject(getEclipseProjectName(), progress.newChild(STEP_TICK_COUNT));
            } catch (Exception ex) {
              __logger.debug(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.MODEL));
            }

            // 2 - Creates the Capella project.
            Project capellaProject = createCapellaProject(eclipseProject, progress.newChild(STEP_TICK_COUNT));
            if (null != capellaProject) {
              // 3 - Creates the default skeleton structure of the Capella model.
              createCapellaModelSkeleton(capellaProject, progress.newChild(STEP_TICK_COUNT));

              // 4- Creates the aird resource.
              _session = createAirdResource(eclipseProject, getEclipseProjectName(), progress.newChild(STEP_TICK_COUNT));
              if (null == _session) {
                throw new InterruptedException("Cannot create the session"); //$NON-NLS-1$
              }

              // 5- Selected Viewpoints handling.
              // Check which page was used to complete the wizard.
              IWizardPage completingPage = getContainer().getCurrentPage();
              Set<Viewpoint> viewpoints = null;
              if (completingPage == _viewpointWizardPage) {
                // Get user selected viewpoints.
                viewpoints = new HashSet<Viewpoint>(0);
                Iterator<Entry<Viewpoint, Boolean>> iterator = _viewpointsMap.entrySet().iterator();
                // Iterate over the viewpoints map to get the active ones (i.e checked in the UI).
                while (iterator.hasNext()) {
                  Map.Entry<Viewpoint, Boolean> entry = iterator.next();
                  if (entry.getValue().booleanValue()) {
                    viewpoints.add(entry.getKey());
                  }
                }
              } else {
                viewpoints = ViewpointSelection.getViewpoints(CreateCapellaProjectCmd.XMI_EXTENSION);
              }
              final Iterator<Viewpoint> viewpointIterator = viewpoints.iterator();
              // Select the viewpoints in TED way as of Sirius 4.15
              MDEAdapterFactory.getExecutionManager().execute(new AbstractNonDirtyingCommand() {
                /**
                 * @see java.lang.Runnable#run()
                 */
                public void run() {
                  ViewpointSelectionCallback viewpointSelectionCallback = new ViewpointSelectionCallback();
                  // Activate all viewpoints
                  while (viewpointIterator.hasNext()) {
                    Viewpoint viewpoint = viewpointIterator.next();
                    viewpointSelectionCallback.selectViewpoint(viewpoint, _session);
                  }
                }
              });
              progress.worked(STEP_TICK_COUNT);

              // 6 - Open Capella Overview.
              if (!OpenSessionAction.openCapellaDashboard(_session)) {
                List<DRepresentation> representations = doGetRepresentations(_session);
                if ((null != representations) && !representations.isEmpty()) {
                  IRunnableWithProgress openRepresentationOperation = new OpenRepresentationsRunnable(representations, true);
                  openRepresentationOperation.run(progress.newChild(STEP_TICK_COUNT));
                }
              }
              // Indicate that the work remaining is just one step now.
              progress.setWorkRemaining(STEP_TICK_COUNT);

              // 7- After opening representation with auto-layout, the session is dirty.
              // Save it to give a non dirty open project to the end-user.
              // Encapsulate the save operation in a WorkspaceModifyOperation to avoid a ProgressDialog.
              WorkspaceModifyOperation saveSessionOperation = new WorkspaceModifyOperation() {
                @Override
                protected void execute(IProgressMonitor monitor__p) throws CoreException, InvocationTargetException, InterruptedException {
                  SubMonitor progressSave = SubMonitor.convert(monitor__p, Messages.getString("NewProjectWizard.SaveCreatedSession_Title"), 1); //$NON-NLS-1$
                  if (SessionStatus.DIRTY.equals(_session.getStatus())) {
                    _session.save(monitor__p);
                  }
                  progressSave.worked(1);
                }
              };
              saveSessionOperation.run(progress.newChild(STEP_TICK_COUNT));
              // initialize the corresponding model
              // Should be done in a listener but event opened is triggered while the melodymodeller file has not been created (in the case of a project
              // creation)
              ILibraryManager.INSTANCE.getAbstractModel(_session);
            }
          } finally {
            monitor_p.done();
          }
        }
      });
    } catch (InvocationTargetException exception_p) {
      StringBuilder loggerMessage = new StringBuilder("NewProjectWizard.performFinish(..) _ "); //$NON-NLS-1$
      __logger.warn(loggerMessage.toString(), exception_p);
      return false;
    } catch (InterruptedException exception_p) {
      StringBuilder loggerMessage = new StringBuilder("NewProjectWizard.performFinish(..) _ "); //$NON-NLS-1$
      __logger.warn(loggerMessage.toString(), exception_p);
      return false;
    }
    return true;
  }

  /**
   * Create Capella Model Skeleton.
   * @param monitor_p
   * @throws InterruptedException
   * @throws InvocationTargetException
   */
  protected void createCapellaModelSkeleton(final Project capellaProject_p, SubMonitor monitor_p) throws InvocationTargetException, InterruptedException {
    IRunnableWithProgress createSkeletonOperation = new IRunnableWithProgress() {
      /**
       * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
       */
      public void run(IProgressMonitor monitor__p) throws InvocationTargetException, InterruptedException {
        SubMonitor progress_l = SubMonitor.convert(monitor__p, Messages.getString("NewProjectWizard.CreateCapellaModelSkeleton_Title"), STEP_TICK_COUNT); //$NON-NLS-1$
        EngineeringDomain engDomain = EngineeringDomain.Software;
        if (_modelPage.isEpbsSelected()) {
          engDomain = EngineeringDomain.System;
        }
        _modelPage.setMdeProject(capellaProject_p);
        progress_l.worked(20); // arbitrary value.
        _modelPage.createNewModel(engDomain, _modelPage.getModelName(), progress_l.newChild(80)); // arbitrary value.
      }
    };
    createSkeletonOperation.run(monitor_p);

  }

  /**
   * @return
   */
  protected String getEclipseProjectName() {
    return _localProjectDescriptionPage.getProjectName();
  }
}
