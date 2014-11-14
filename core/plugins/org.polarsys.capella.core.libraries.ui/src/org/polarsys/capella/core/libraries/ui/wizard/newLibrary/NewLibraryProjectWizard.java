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
package org.polarsys.capella.core.libraries.ui.wizard.newLibrary;

import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;

import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.libraries.nature.LibraryNature;
import org.polarsys.capella.core.libraries.ui.Activator;
import org.polarsys.capella.core.platform.sirius.ui.project.NewProjectWizard;
import org.polarsys.capella.core.platform.sirius.ui.project.internal.CapellaProjectContentsLocationArea;
import org.polarsys.capella.core.platform.sirius.ui.project.internal.ProjectContentsLocationArea;
import org.polarsys.capella.core.platform.sirius.ui.project.internal.WizardNewProjectCreationPage;

/**
 */
public class NewLibraryProjectWizard extends NewProjectWizard {

  private static final Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);

  // Same as NewProjectWizard.createProjectDescription except the project nature.
  @Override
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
    if (!description.hasNature(LibraryNature.ID)) {
      String[] newNatures = new String[] { LibraryNature.ID };
      description.setNatureIds(newNatures);
    }
    if (withConfigProject) {
      description = addConfigurationProjectReferences(projectHandle_p, description);
    }
    return description;

  }

  @Override
  // overrides just to replace GUI text (title and image)
  public void init(IWorkbench workbench_p, IStructuredSelection currentSelection_p) {
    super.init(workbench_p, currentSelection_p);
    setWindowTitle("New Capella Library"); //$NON-NLS-1$
    ImageDescriptor descriptor = Activator.getDefault().getImageDescriptor("libraryWizard.png"); //$NON-NLS-1$
    if (null == descriptor) {
      descriptor = ImageDescriptor.getMissingImageDescriptor();
    }
    setDefaultPageImageDescriptor(descriptor);
    setNeedsProgressMonitor(true);
  }

  @Override
  // overrides just to replace GUI text (title and description)
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
    mainPage.setTitle("Capella Library"); //$NON-NLS-1$
    mainPage.setDescription("Create a new Capella project"); //$NON-NLS-1$
    return mainPage;
  }

  // Creates and returns the new Capella project.
  @Override
  protected Project createCapellaProject(IProject eclipseProject_p, IProgressMonitor monitor_p) {
    // Run the new model creation operation.
    try {
      // Create the Capella project.
      CreateCapellaLibraryOperation projectOp = new CreateCapellaLibraryOperation(eclipseProject_p, eclipseProject_p.getName(), getProjectApproach());

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

}
