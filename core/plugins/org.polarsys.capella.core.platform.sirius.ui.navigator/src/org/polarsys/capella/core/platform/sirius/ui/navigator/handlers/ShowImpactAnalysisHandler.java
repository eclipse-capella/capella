/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.navigator.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.polarsys.capella.common.ui.services.commands.AbstractUiHandler;
import org.polarsys.capella.common.ui.services.commands.ActionCommandDelegate;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.ImpactAnalysisAction;

/**
 * Handler used to open Impact Analysis dialog.
 */
public class ShowImpactAnalysisHandler extends AbstractUiHandler {
  /**
   * {@inheritDoc}
   */
  public Object execute(ExecutionEvent event) throws ExecutionException {
    ImpactAnalysisAction action = new ImpactAnalysisAction();
    ActionCommandDelegate delegate = new ActionCommandDelegate(event);
    if (action.selectionChanged(HandlerUtil.getCurrentSelection(event))) {
      action.run(delegate);
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
