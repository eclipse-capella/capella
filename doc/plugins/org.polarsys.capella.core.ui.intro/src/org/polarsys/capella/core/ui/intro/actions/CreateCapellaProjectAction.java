/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.intro.actions;

import java.util.Properties;

import org.eclipse.ui.intro.IIntroSite;

import org.polarsys.capella.core.platform.sirius.ui.project.NewProjectAction;

/**
 * Create an intro action to create a capella project.
 */
public class CreateCapellaProjectAction extends AbstractIntroAction {
  /**
   * @see org.eclipse.ui.intro.config.IIntroAction#run(org.eclipse.ui.intro.IIntroSite, java.util.Properties)
   */
  public void run(IIntroSite site_p, Properties params_p) {
    NewProjectAction newProjectAction = new NewProjectAction();
    newProjectAction.setSite(site_p);
    newProjectAction.run(null);
  }
}
