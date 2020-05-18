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
package org.polarsys.capella.core.model.obfuscator.actions;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.polarsys.capella.common.mdsofa.common.misc.Couple;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.SelectionHelper;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

/**
 * Add Obfuscation actions to the Capella Explorer.
 */
public class ObfuscatorActionProvider extends CommonActionProvider {
  /**
   * Obfuscate a model action.
   */
  private ObfuscateSessionAction _obfuscateSessionAction;

  /**
   * @see org.eclipse.ui.actions.ActionGroup#dispose()
   */
  @Override
  public void dispose() {   
    ISelectionProvider selectionProvider = getActionSite().getViewSite().getSelectionProvider();
    if (null != _obfuscateSessionAction) {
      selectionProvider.removeSelectionChangedListener(_obfuscateSessionAction);
      _obfuscateSessionAction = null;
    }
    super.dispose(); //test
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
    menu_p.appendToGroup(ICommonMenuConstants.GROUP_GENERATE, _obfuscateSessionAction);
  }

  /**
   * @see org.eclipse.ui.navigator.CommonActionProvider#init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
   */
  @Override
  public void init(ICommonActionExtensionSite site_p) {
    super.init(site_p);
    ISelectionProvider selectionProvider = site_p.getViewSite().getSelectionProvider();
    _obfuscateSessionAction = new ObfuscateSessionAction();
    SelectionHelper.registerToSelectionChanges(_obfuscateSessionAction, selectionProvider);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updateActionBars() {
    IStructuredSelection structuredSelection = (IStructuredSelection) getContext().getSelection();
    List<Couple<Session, IFile>> sessions = SessionHelper.getSessionsFromSelection(structuredSelection);
    boolean canObfuscate = (sessions.size() == structuredSelection.size());
    _obfuscateSessionAction.setEnabled(canObfuscate);
  }

}
