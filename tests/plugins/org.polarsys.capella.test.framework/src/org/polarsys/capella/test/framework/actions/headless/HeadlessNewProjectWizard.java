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
package org.polarsys.capella.test.framework.actions.headless;

import org.polarsys.capella.core.platform.sirius.ui.project.Messages;
import org.polarsys.capella.core.platform.sirius.ui.project.NewModelWizardPage;
import org.polarsys.capella.core.platform.sirius.ui.project.NewProjectWizard;

/**
 * @author Erwan Brottier
 */
public class HeadlessNewProjectWizard extends NewProjectWizard {

  protected String eclipseProjectName;

  public HeadlessNewProjectWizard(String eclipseProjectName_p) {
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
