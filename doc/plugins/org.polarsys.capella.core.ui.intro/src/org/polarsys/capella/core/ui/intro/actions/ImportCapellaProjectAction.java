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

import org.eclipse.ui.actions.ImportResourcesAction;
import org.eclipse.ui.intro.IIntroSite;

/**
 * Create an intro action to import a capella project.
 */
public class ImportCapellaProjectAction extends AbstractIntroAction {
  /**
   * @see org.eclipse.ui.intro.config.IIntroAction#run(org.eclipse.ui.intro.IIntroSite, java.util.Properties)
   */
  public void run(IIntroSite site_p, Properties params_p) {
    ImportResourcesAction importAction = new ImportResourcesAction(site_p.getWorkbenchWindow());
    importAction.run();
    importAction.dispose();
  }
}
