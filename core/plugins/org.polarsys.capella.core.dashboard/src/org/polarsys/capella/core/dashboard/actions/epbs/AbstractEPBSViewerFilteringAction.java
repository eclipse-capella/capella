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
package org.polarsys.capella.core.dashboard.actions.epbs;

import org.polarsys.capella.core.dashboard.actions.AbstractViewerFilteringAction;
import org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage;

/**
 */
public abstract class AbstractEPBSViewerFilteringAction extends AbstractViewerFilteringAction {
  /**
   * Default constructor.
   * @param capellaArchitecturePage_p
   */
  public AbstractEPBSViewerFilteringAction(AbstractCapellaArchitectureDashboardPage capellaArchitecturePage_p) {
    super(capellaArchitecturePage_p);
  }
}
