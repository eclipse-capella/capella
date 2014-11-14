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
