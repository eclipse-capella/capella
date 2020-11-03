/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.semantic.browser.actions;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.polarsys.capella.common.ui.toolkit.browser.action.BrowserActionFactory;
import org.polarsys.capella.common.ui.toolkit.browser.view.ISemanticBrowserViewPart;

public class SemanticBrowserActionFactory {

  public void createBackAction(ISemanticBrowserViewPart semanticBrowserViewPart) {
    IViewSite viewSite = semanticBrowserViewPart.getViewSite();
    IWorkbenchAction backAction = BrowserActionFactory.BACKWARD_HISTORY.create(viewSite.getWorkbenchWindow(),
        semanticBrowserViewPart);
    backAction.setActionDefinitionId("org.polarsys.capella.core.ui.semantic.browser.backwardNavigation"); //$NON-NLS-1$
    getToolBarManager(semanticBrowserViewPart).add(backAction);
  }

  public void createForwardAction(ISemanticBrowserViewPart semanticBrowserViewPart) {
    IViewSite viewSite = semanticBrowserViewPart.getViewSite();
    IWorkbenchAction forwardAction = BrowserActionFactory.FORWARD_HISTORY.create(viewSite.getWorkbenchWindow(),
        semanticBrowserViewPart);
    forwardAction.setActionDefinitionId("org.polarsys.capella.core.ui.semantic.browser.forwardNavigation"); //$NON-NLS-1$
    getToolBarManager(semanticBrowserViewPart).add(forwardAction);
  }

  public void createShowDiagramsAction(ISemanticBrowserViewPart semanticBrowserViewPart) {
    ShowDiagramsAction showDiagramsAction = new ShowDiagramsAction(semanticBrowserViewPart);
    getToolBarManager(semanticBrowserViewPart).add(showDiagramsAction);
  }

  public void createLimitateTreeExpansionAction(ISemanticBrowserViewPart semanticBrowserViewPart) {
    LimitateTreeExpansionAction limitateTreeExpansionAction = new LimitateTreeExpansionAction(semanticBrowserViewPart);
    getToolBarManager(semanticBrowserViewPart).add(limitateTreeExpansionAction);
  }

  public void createRefreshAction(ISemanticBrowserViewPart semanticBrowserViewPart) {
    RefreshAction refreshAction = new RefreshAction(semanticBrowserViewPart);
    getToolBarManager(semanticBrowserViewPart).add(refreshAction);
  }

  public void createListenToSelectionEventsAction(ISemanticBrowserViewPart semanticBrowserViewPart, boolean isCheced) {
    ListenToSelectionEventsAction listenToSelectionEventsAction = new ListenToSelectionEventsAction(
        semanticBrowserViewPart, isCheced);
    getToolBarManager(semanticBrowserViewPart).add(listenToSelectionEventsAction);
  }

  private IToolBarManager getToolBarManager(ISemanticBrowserViewPart semanticBrowserViewPart) {
    IViewSite viewSite = semanticBrowserViewPart.getViewSite();
    IActionBars actionBars = viewSite.getActionBars();
    return actionBars.getToolBarManager();
  }
}
