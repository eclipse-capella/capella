/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.platform.sirius.ui.navigator.actions.providers;

import java.util.Arrays;
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
  private OpenSessionAction openSessionAction;
  // The save action is driven by CapellaSaveable.

  private CloseSessionAction closeSessionAction;
  private IWorkbenchAction saveAction;

  /**
   * @see org.eclipse.ui.actions.ActionGroup#dispose()
   */
  @Override
  public void dispose() {
    ISelectionProvider selectionProvider = getActionSite().getViewSite().getSelectionProvider();
    if (null != openSessionAction) {
      selectionProvider.removeSelectionChangedListener(openSessionAction);
      openSessionAction = null;
    }
    if (null != closeSessionAction) {
      selectionProvider.removeSelectionChangedListener(closeSessionAction);
      closeSessionAction = null;
    }
    if (null != saveAction) {
      saveAction.dispose();
      saveAction = null;
    }
    super.dispose();
  }

  /**
   * @see org.eclipse.ui.navigator.CommonActionProvider#init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
   */
  @Override
  public void init(ICommonActionExtensionSite site) {
    super.init(site);
    ISelectionProvider selectionProvider = site.getViewSite().getSelectionProvider();
    openSessionAction = new OpenSessionAction();
    
    SelectionHelper.registerToSelectionChanges(openSessionAction, selectionProvider);

    closeSessionAction = new CloseSessionAction();
    SelectionHelper.registerToSelectionChanges(closeSessionAction, selectionProvider);
    ICommonViewerSite commonViewSite = site.getViewSite();
    if (!(commonViewSite instanceof ICommonViewerWorkbenchSite)) {
      return;
    }
    ICommonViewerWorkbenchSite commonViewerWorkbenchSite = (ICommonViewerWorkbenchSite) commonViewSite;
    saveAction = ActionFactory.SAVE.create(commonViewerWorkbenchSite.getWorkbenchWindow());
  }


  /**
   * @see org.eclipse.ui.actions.ActionGroup#fillActionBars(org.eclipse.ui.IActionBars)
   */
  @Override
  public void fillActionBars(IActionBars actionBars) {
    if (openSessionAction.isEnabled()) {
      actionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, openSessionAction);
    }
    actionBars.setGlobalActionHandler(ActionFactory.CLOSE.getId(), closeSessionAction);
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
   */
  @Override
  public void fillContextMenu(IMenuManager menu) {
    updateActionBars();
    menu.appendToGroup(ICommonMenuConstants.GROUP_OPEN, openSessionAction);
    menu.appendToGroup(ICommonMenuConstants.GROUP_BUILD, closeSessionAction);
    menu.appendToGroup(ICommonMenuConstants.GROUP_BUILD, saveAction);
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
      if ((selectedElement instanceof IFile) && ((IFile)selectedElement).getFileExtension().equals(CapellaResourceHelper.AIRD_FILE_EXTENSION)) {
        // Update open session.
        IFile airdFile = (IFile) selectedElement;
        Session session = SessionHelper.getSessionForDiagramFile(airdFile);
        if (null == session) {
          canSelectViewpoints &= false;
          canClose &= false;
        } else {
          canOpen &= false;
        }
        
        // if the aird selected is not inside a Capella project, we don't open the dashboard
        if(!CapellaResourceHelper.isProjectOfType(airdFile.getProject(), Arrays.asList(CapellaResourceHelper.CAPELLA_PROJECT_NATURE, CapellaResourceHelper.CAPELLA_LIBRARY_PROJECT_NATURE))){
          openSessionAction.setOpenActivityExplorer(false);
        } else {
        	boolean toOpen = org.eclipse.amalgam.explorer.activity.ui.api.actions.OpenSessionAction.getActivityExplorerPreference();
        	openSessionAction.setOpenActivityExplorer(toOpen);        }
      }
    }
    openSessionAction.setEnabled(canOpen);
    closeSessionAction.setEnabled(canClose);
  }
}
