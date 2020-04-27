/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.libraries.ui.wizard.newLibrary;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.polarsys.capella.core.libraries.ui.Activator;
import org.polarsys.capella.core.platform.sirius.ui.project.NewProjectWizard;
import org.polarsys.capella.core.platform.sirius.ui.project.internal.WizardNewProjectCreationPage;
import org.polarsys.capella.core.platform.sirius.ui.project.operations.SessionCreationHelper;

public class NewLibraryProjectWizard extends NewProjectWizard {

  @Override
  protected SessionCreationHelper createSessionCreationHelper() {
    return new LibrarySessionCreationHelper(modelPage.isEpbsSelected(), modelPage.isOpaSelected(), getProjectApproach());
  }

  @Override
  // overrides just to replace GUI text (title and image)
  public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
    super.init(workbench, currentSelection);
    setWindowTitle("New Capella Library"); //$NON-NLS-1$
    ImageDescriptor descriptor = Activator.getDefault().getImageDescriptor("libraryWizard.png"); //$NON-NLS-1$
    if (null == descriptor) {
      descriptor = ImageDescriptor.getMissingImageDescriptor();
    }
    setDefaultPageImageDescriptor(descriptor);
  }

  @Override
  // overrides just to replace GUI text (title and description)
  protected WizardNewProjectCreationPage createLocalProjectDescriptionPage() {
    WizardNewProjectCreationPage mainPage = super.createLocalProjectDescriptionPage();
    mainPage.setTitle("Capella Library"); //$NON-NLS-1$
    mainPage.setDescription("Create a new Capella project"); //$NON-NLS-1$
    return mainPage;
  }

}
