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
package org.polarsys.capella.core.platform.sirius.ui.project;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.ProjectApproach;
import org.polarsys.capella.core.platform.sirius.ui.project.internal.CapellaProjectContentsLocationArea;
import org.polarsys.capella.core.platform.sirius.ui.project.internal.ProjectContentsLocationArea;
import org.polarsys.capella.core.platform.sirius.ui.project.internal.WizardNewProjectCreationPage;
import org.polarsys.capella.core.platform.sirius.ui.project.operations.ProjectSessionCreationHelper;
import org.polarsys.capella.core.platform.sirius.ui.project.operations.SessionCreationHelper;
import org.polarsys.capella.core.preferences.configuration.project.nature.ConfigurationProjectNature;
import org.polarsys.capella.core.preferences.configuration.project.ui.wizards.ReferecedConfigurationProjectSelectionPage;
import org.polarsys.capella.core.sirius.ui.actions.OpenSessionAction;

/**
 * The wizard allowing to initialize a new Capella project.
 */
public class NewProjectWizard extends BasicNewResourceWizard {
  /**
   * Step tick count
   */
  protected static final int STEP_TICK_COUNT = 100;

  // Log4j reference logger.
  private static final Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);

  // Reuse of the new project page provided by the platform UI dialogs.
  protected WizardNewProjectCreationPage _localProjectDescriptionPage;

  // The New Model wizard page.
  protected NewModelWizardPage modelPage;

  private SortedMap<Viewpoint, Boolean> viewpointsMap;

  private WizardPage viewpointWizardPage;

  private ReferecedConfigurationProjectSelectionPage referencedConfigurationProjectPage;

  /**
   * Constructs the wizard allowing to initialize a new Capella project.
   */
  public NewProjectWizard() {
    viewpointsMap = new TreeMap<Viewpoint, Boolean>(new ViewpointRegistry.ViewpointComparator());
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

    viewpointWizardPage = ViewpointSelection.createWizardPage(CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION, viewpointsMap);
    addPage(viewpointWizardPage);

    createReferencedConfigurationProjectsPage();
  }

  /**
  * 
  */
  protected void createReferencedConfigurationProjectsPage() {
    referencedConfigurationProjectPage =
        new ReferecedConfigurationProjectSelectionPage(Messages.getString("NewModelWizard.title"), new String[] { ConfigurationProjectNature.NATURE_ID });
    addPage(referencedConfigurationProjectPage);

  }

  /**
   * Create logic pages between the first one and the last one of this wizard.<br>
   * Default implementation creates a model page i.e {@link NewModelWizardPage}.
   */
  protected void createLogicPages() {
    modelPage = createModelPage();
    addPage(modelPage);
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

  protected ProjectApproach getProjectApproach() {
    return _localProjectDescriptionPage.getProjectApproachSelection();
  }

  /**
   * Create model page.
   */
  protected NewModelWizardPage createModelPage() {
    NewModelWizardPage modelPage = new NewModelWizardPage("model.creation.page"); //$NON-NLS-1$
    modelPage.setDescription(Messages.getString("NewModelWizard.description")); //$NON-NLS-1$
    modelPage.setTitle(Messages.getString("NewModelWizard.title")); //$NON-NLS-1$
    return modelPage;
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
  public IWizardPage getNextPage(IWizardPage page) {
    IWizardPage nextPage = super.getNextPage(page);
    // Set the project name as model name.
    if ((null != modelPage) && (modelPage == nextPage)) {
      String projectName = getEclipseProjectName();
      modelPage.setModelNameFieldValue(projectName);
    }
    return nextPage;
  }

  /**
   * @see org.eclipse.ui.wizards.newresource.BasicNewResourceWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
   */
  @Override
  public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
    super.init(workbench, currentSelection);
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
        public void run(final IProgressMonitor monitor_p) throws InvocationTargetException, InterruptedException {

          // Get a project description.
          IPath defaultPath = Platform.getLocation();
          IPath newPath = _localProjectDescriptionPage.getLocationPath();
          if (defaultPath.equals(newPath)) {
            newPath = null;
          }

          List<IProject> referencedProjects = referencedConfigurationProjectPage.getSelectedConfigurationsProjects();
          SessionCreationHelper helper = createSessionCreationHelper();
          Session session = helper.createFullProject(getEclipseProjectName(), newPath, referencedProjects, getSelectedViewpoints(), monitor_p);

          OpenSessionAction.openActivityExplorer(session);
        }
      });
    } catch (InvocationTargetException exception_p) {
      StringBuilder loggerMessage = new StringBuilder("NewProjectWizard.performFinish(..) _ "); //$NON-NLS-1$
      logger.warn(loggerMessage.toString(), exception_p);
      return false;
    } catch (InterruptedException exception_p) {
      StringBuilder loggerMessage = new StringBuilder("NewProjectWizard.performFinish(..) _ "); //$NON-NLS-1$
      logger.warn(loggerMessage.toString(), exception_p);
      return false;
    }
    return true;
  }

  protected SessionCreationHelper createSessionCreationHelper() {
    return new ProjectSessionCreationHelper(modelPage.isEpbsSelected(), modelPage.isOpaSelected(), getProjectApproach());
  }

  /**
   * @return
   */
  protected Set<Viewpoint> getSelectedViewpoints() {

    Set<Viewpoint> viewpoints = null;
    if (viewpointWizardPage.isPageComplete()) {
      // Get user selected viewpoints.
      viewpoints = new HashSet<Viewpoint>(0);
      Iterator<Entry<Viewpoint, Boolean>> iterator = viewpointsMap.entrySet().iterator();
      // Iterate over the viewpoints map to get the active ones (i.e checked in the UI).
      while (iterator.hasNext()) {
        Map.Entry<Viewpoint, Boolean> entry = iterator.next();
        if (entry.getValue().booleanValue()) {
          viewpoints.add(entry.getKey());
        }
      }
    } else {
      viewpoints = ViewpointSelection.getViewpoints(CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION);
    }

    return viewpoints;
  }

  /**
   * @return
   */
  protected String getEclipseProjectName() {
    return _localProjectDescriptionPage.getProjectName();
  }
}
