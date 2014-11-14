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
package org.polarsys.capella.core.dashboard.actions;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;

import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.SelectionHelper;

/**
 * Provider that declares actions for the Capella Project Explorer.
 */
public class CapellaDashboardActionProvider extends CommonActionProvider {
  /**
   * Action used to open a Capella Dashboard.
   */
  private OpenCapellaDashboardAction _openDashboardAction;

  /**
   * @see org.eclipse.ui.actions.ActionGroup#dispose()
   */
  @Override
  public void dispose() {
    ISelectionProvider selectionProvider = getActionSite().getViewSite().getSelectionProvider();
    if (null != _openDashboardAction) {
      selectionProvider.removeSelectionChangedListener(_openDashboardAction);
      _openDashboardAction = null;
    }
    super.dispose();
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#fillActionBars(org.eclipse.ui.IActionBars)
   */
  @Override
  public void fillActionBars(IActionBars actionBars_p) {
    // Do nothing.
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
   */
  @Override
  public void fillContextMenu(IMenuManager menu_p) {
    updateActionBars();
    menu_p.appendToGroup(ICommonMenuConstants.GROUP_OPEN, _openDashboardAction);
  }

  /**
   * @see org.eclipse.ui.navigator.CommonActionProvider#init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
   */
  @Override
  public void init(ICommonActionExtensionSite site_p) {
    super.init(site_p);
    ISelectionProvider selectionProvider = site_p.getViewSite().getSelectionProvider();
    _openDashboardAction = new OpenCapellaDashboardAction();
    SelectionHelper.registerToSelectionChanges(_openDashboardAction, selectionProvider);
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#updateActionBars()
   */
  @Override
  public void updateActionBars() {
    IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
    _openDashboardAction.selectionChanged(selection);
  }
}
