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

package org.polarsys.capella.core.platform.sirius.ui.navigator.actions.providers;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.NewModelAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.SelectionHelper;


/**
 * The new model action provider.
 */
public class NewModelActionProvider extends CommonActionProvider {
  // The new model action.
  private NewModelAction _newModelAction;

  /**
   * @see org.eclipse.ui.navigator.CommonActionProvider#init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
   */
  @Override
  public void init(ICommonActionExtensionSite site_p) {
    super.init(site_p);
    ICommonViewerSite viewSite = site_p.getViewSite();
    if (viewSite instanceof ICommonViewerWorkbenchSite) {
      IWorkbenchWindow window = ((ICommonViewerWorkbenchSite) viewSite).getWorkbenchWindow();
      _newModelAction = new NewModelAction(window);
      SelectionHelper.registerToSelectionChanges(_newModelAction, viewSite.getSelectionProvider());
    }
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#dispose()
   */
  @Override
  public void dispose() {
    if (null != _newModelAction) {
      getActionSite().getViewSite().getSelectionProvider().removeSelectionChangedListener(_newModelAction);
      _newModelAction = null;
    }
    super.dispose();
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
   */
  @Override
  public void fillContextMenu(IMenuManager menu_p) {
    IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
    _newModelAction.selectionChanged(selection);
    menu_p.appendToGroup(ICommonMenuConstants.GROUP_NEW, _newModelAction);
  }

}
