/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.explorer.activity.ui.actions;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.SelectionHelper;

/**
 * Provider that declares actions for the Project Explorer.
 */
public class ActivityExplorerActionProvider extends CommonActionProvider {
  /**
   * Action used to open the Activity Explorer.
   */
  private OpenActivityExplorerAction openActivityExplorerAction;

  /**
   * @see org.eclipse.ui.actions.ActionGroup#dispose()
   */
  @Override
  public void dispose() {
    ISelectionProvider selectionProvider = getActionSite().getViewSite().getSelectionProvider();
    if (null != openActivityExplorerAction) {
      selectionProvider.removeSelectionChangedListener(openActivityExplorerAction);
      openActivityExplorerAction = null;
    }
    super.dispose();
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#fillActionBars(org.eclipse.ui.IActionBars)
   */
  @Override
  public void fillActionBars(IActionBars actionBars) {
    // Do nothing.
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
   */
  @Override
  public void fillContextMenu(IMenuManager menu) {
    updateActionBars();
    menu.appendToGroup(ICommonMenuConstants.GROUP_OPEN, openActivityExplorerAction);
  }

  /**
   * @see org.eclipse.ui.navigator.CommonActionProvider#init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
   */
  @Override
  public void init(ICommonActionExtensionSite site) {
    super.init(site);
    ISelectionProvider selectionProvider = site.getViewSite().getSelectionProvider();
    openActivityExplorerAction = new OpenActivityExplorerAction();
    SelectionHelper.registerToSelectionChanges(openActivityExplorerAction, selectionProvider);
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#updateActionBars()
   */
  @Override
  public void updateActionBars() {
    IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
    openActivityExplorerAction.selectionChanged(selection);
  }
}
