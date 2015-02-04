/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.framework.actions.headless;

import org.polarsys.capella.core.libraries.ui.wizard.newLibrary.NewLibraryProjectWizard;
import org.polarsys.capella.core.platform.sirius.ui.project.Messages;
import org.polarsys.capella.core.platform.sirius.ui.project.NewModelWizardPage;

/**
 * @author Erwan Brottier
 */
public class HeadlessNewLibraryWizard extends NewLibraryProjectWizard {

  protected String eclipseProjectName;

  public HeadlessNewLibraryWizard(String eclipseProjectName_p) {
    super();
    eclipseProjectName = eclipseProjectName_p;
  }

  @Override
  protected String getEclipseProjectName() {
    return eclipseProjectName;
  }

  @Override
  protected NewModelWizardPage createModelPage() {
    HeadlessNewModelWizardPage modelPage = new HeadlessNewModelWizardPage(
        "model.creation.page", getEclipseProjectName()); //$NON-NLS-1$
    modelPage.setDescription(Messages.getString("NewModelWizard.description")); //$NON-NLS-1$
    modelPage.setTitle(Messages.getString("NewModelWizard.title")); //$NON-NLS-1$
    return modelPage;
  }

}
