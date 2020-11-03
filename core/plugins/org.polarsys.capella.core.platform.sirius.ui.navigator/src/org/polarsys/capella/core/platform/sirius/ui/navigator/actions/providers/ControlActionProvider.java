/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.platform.sirius.ui.navigator.actions.providers;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.SelectionHelper;
import org.polarsys.capella.core.sirius.ui.actions.DesignerControlAction;

/**
 * The control action provider.
 */
public class ControlActionProvider extends CommonActionProvider {
  // The control action
  private DesignerControlAction _controlAction;

  /**
   * @see org.eclipse.ui.navigator.CommonActionProvider#init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
   */
  @Override
  public void init(ICommonActionExtensionSite site_p) {
    super.init(site_p);
    _controlAction = new DesignerControlAction();
    SelectionHelper.registerToSelectionChanges(_controlAction, site_p.getViewSite().getSelectionProvider());
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#dispose()
   */
  @Override
  public void dispose() {
    if (null != _controlAction) {
      getActionSite().getViewSite().getSelectionProvider().removeSelectionChangedListener(_controlAction);
      _controlAction = null;
    }
    super.dispose();
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
   */
  @Override
  public void fillContextMenu(IMenuManager menu_p) {
    // Make sure, actions are refreshed even if selection hasn't changed
    updateActionBars();
    menu_p.appendToGroup(ICommonMenuConstants.GROUP_PROPERTIES, _controlAction);
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#updateActionBars()
   */
  @Override
  public void updateActionBars() {
    if (null != _controlAction) {
      _controlAction.updateSelection((IStructuredSelection) getContext().getSelection());
    }
  }
}
