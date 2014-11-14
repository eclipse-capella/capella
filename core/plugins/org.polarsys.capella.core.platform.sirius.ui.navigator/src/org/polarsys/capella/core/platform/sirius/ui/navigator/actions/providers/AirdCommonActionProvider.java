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

package org.polarsys.capella.core.platform.sirius.ui.navigator.actions.providers;

import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.SelectionHelper;
import org.polarsys.capella.core.sirius.ui.actions.CloseSessionAction;
import org.polarsys.capella.core.sirius.ui.actions.OpenSessionAction;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

/**
 * The AIRD action provider.
 */
public class AirdCommonActionProvider extends CommonActionProvider {
  // The action to create a new session.
  private OpenSessionAction _openSessionAction;
  // The save action is driven by CapellaSaveable.

  private CloseSessionAction _closeSessionAction;
  private IWorkbenchAction _saveAction;

  /**
   * @see org.eclipse.ui.actions.ActionGroup#dispose()
   */
  @Override
  public void dispose() {
    ISelectionProvider selectionProvider = getActionSite().getViewSite().getSelectionProvider();
    if (null != _openSessionAction) {
      selectionProvider.removeSelectionChangedListener(_openSessionAction);
      _openSessionAction = null;
    }
    if (null != _closeSessionAction) {
      selectionProvider.removeSelectionChangedListener(_closeSessionAction);
      _closeSessionAction = null;
    }
    if (null != _saveAction) {
      _saveAction.dispose();
      _saveAction = null;
    }
    super.dispose();
  }

  /**
   * @see org.eclipse.ui.navigator.CommonActionProvider#init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
   */
  @Override
  public void init(ICommonActionExtensionSite site_p) {
    super.init(site_p);
    ISelectionProvider selectionProvider = site_p.getViewSite().getSelectionProvider();
    _openSessionAction = new OpenSessionAction();
    SelectionHelper.registerToSelectionChanges(_openSessionAction, selectionProvider);

    _closeSessionAction = new CloseSessionAction();
    SelectionHelper.registerToSelectionChanges(_closeSessionAction, selectionProvider);
    ICommonViewerSite commonViewSite = site_p.getViewSite();
    if (!(commonViewSite instanceof ICommonViewerWorkbenchSite)) {
      return;
    }
    ICommonViewerWorkbenchSite commonViewerWorkbenchSite = (ICommonViewerWorkbenchSite) commonViewSite;
    _saveAction = ActionFactory.SAVE.create(commonViewerWorkbenchSite.getWorkbenchWindow());
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#fillActionBars(org.eclipse.ui.IActionBars)
   */
  @Override
  public void fillActionBars(IActionBars actionBars_p) {
    if (_openSessionAction.isEnabled()) {
      actionBars_p.setGlobalActionHandler(ICommonActionConstants.OPEN, _openSessionAction);
    }
    actionBars_p.setGlobalActionHandler(ActionFactory.CLOSE.getId(), _closeSessionAction);
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
   */
  @Override
  public void fillContextMenu(IMenuManager menu_p) {
    updateActionBars();
    menu_p.appendToGroup(ICommonMenuConstants.GROUP_OPEN, _openSessionAction);
    menu_p.appendToGroup(ICommonMenuConstants.GROUP_BUILD, _closeSessionAction);
    menu_p.appendToGroup(ICommonMenuConstants.GROUP_BUILD, _saveAction);
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#updateActionBars()
   */
  @Override
  public void updateActionBars() {
    IStructuredSelection structuredSelection = (IStructuredSelection) getContext().getSelection();
    Iterator<?> selectedElements = structuredSelection.iterator();
    boolean canOpen = true;
    boolean canClose = true;
    boolean canSelectViewpoints = true;
    while (selectedElements.hasNext()) {
      Object selectedElement = selectedElements.next();
      if ((selectedElement instanceof IFile) && ((IFile) selectedElement).getFileExtension().equals(CapellaResourceHelper.AIRD_FILE_EXTENSION)) {
        // Update open session.
        Session session = SessionHelper.getSessionForDiagramFile((IFile) selectedElement);
        if (null == session) {
          canSelectViewpoints &= false;
          canClose &= false;
        } else {
          canOpen &= false;
        }
      }
    }
    _openSessionAction.setEnabled(canOpen);
    _closeSessionAction.setEnabled(canClose);
  }
}
