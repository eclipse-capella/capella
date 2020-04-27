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

import org.polarsys.capella.core.platform.sirius.ui.project.NewModelWizardPage;

public class HeadlessNewModelWizardPage extends NewModelWizardPage {

  protected String eclipseProjectName;

  public HeadlessNewModelWizardPage(String pageName_p, String eclipseProjectName_p) {
    super(pageName_p);
    eclipseProjectName = eclipseProjectName_p;
  }

  @Override
  public String getModelName() {
    return eclipseProjectName;
  }
}
