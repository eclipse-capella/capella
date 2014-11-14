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
package org.polarsys.capella.core.platform.sirius.ui.navigator.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import org.polarsys.capella.common.ui.services.commands.AbstractUiHandler;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.ImpactAnalysisAction;

/**
 * Handler used to open Impact Analysis dialog.
 */
public class ShowImpactAnalysisHandler extends AbstractUiHandler {
  /**
   * {@inheritDoc}
   */
  public Object execute(ExecutionEvent event_p) throws ExecutionException {
    // Get the active part.
    IWorkbenchPart activePart = (IWorkbenchPart) getVariableValue(event_p, ACTIVE_PART_VARIABLE);
    // Open Impact Analysis Handler.
    ImpactAnalysisAction action = new ImpactAnalysisAction();
    if (action.selectionChanged(activePart.getSite().getSelectionProvider().getSelection())) {
      action.run(null);
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEnabled() {
    IWorkbenchPart activePart = null;
    IWorkbench workbench = PlatformUI.getWorkbench();
    if (workbench != null) {
      IWorkbenchWindow windows = workbench.getActiveWorkbenchWindow();
      if (windows != null) {
        IWorkbenchPage page = windows.getActivePage();
        if (page != null) {
          activePart = page.getActivePart();
        }
      }
    }

    return (null != activePart) ? new ImpactAnalysisAction().selectionChanged(activePart.getSite().getSelectionProvider().getSelection()) : false;
  }
}
