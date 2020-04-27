/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import java.util.Collection;
import java.util.List;

import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
import org.eclipse.ui.operations.RedoActionHandler;
import org.eclipse.ui.operations.UndoActionHandler;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.viewer.ICommandStackSelectionProvider;
import org.polarsys.capella.core.platform.sirius.ui.navigator.viewer.NavigatorEditingDomainDispatcher;

/**
 * The undo / redo actions provider.
 */
public class UndoRedoActionProvider extends CommonActionProvider implements ICommandStackSelectionProvider {
  /**
   * Undo action handler based on {@link IOperationHistory}.
   */
  private UndoActionHandler undoActionHandler;
  /**
   * Redo action handler based on {@link IOperationHistory}.
   */
  private RedoActionHandler redoActionHandler;

  /**
   * @see org.eclipse.ui.navigator.CommonActionProvider#init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
   */
  @Override
  public void init(ICommonActionExtensionSite site) {
    super.init(site);

    IWorkbenchPartSite workbenchPartSite = ((ICommonViewerWorkbenchSite) site.getViewSite()).getSite();
    // Create the undo action handler
    undoActionHandler = new UndoActionHandler(workbenchPartSite, null/* undoContext */);
    // Create the redo action handler
    redoActionHandler = new RedoActionHandler(workbenchPartSite, null/* undoContext */);

    NavigatorEditingDomainDispatcher.registerCommandStackSelectionProvider(this);
    updateActionBars();
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#fillActionBars(org.eclipse.ui.IActionBars)
   */
  @Override
  public void fillActionBars(IActionBars actionBars) {
    actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(), undoActionHandler);
    actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(), redoActionHandler);
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
   */
  @Override
  public void fillContextMenu(IMenuManager menu) {
    menu.appendToGroup(ICommonMenuConstants.GROUP_EDIT, undoActionHandler);
    menu.appendToGroup(ICommonMenuConstants.GROUP_EDIT, redoActionHandler);
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#updateActionBars()
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public void updateActionBars() {
    // Retrieve the editing domain from the current selection.
    EditingDomain editingDomain = null;
    ISelection selection = null;
    ActionContext ctx = getContext();
    if (null != ctx) {
      selection = ctx.getSelection();
    } else {
      selection = getActionSite().getViewSite().getSelectionProvider().getSelection();
    }
    if (selection instanceof IStructuredSelection) {
      List selectionList = ((IStructuredSelection) selection).toList();
      Collection selectedObjects = CapellaAdapterHelper.resolveEObjects(selectionList);

      //If all selected elements were EObjects, retrieve editing domain if elements are from the same session
      if (selectionList.size() == selectedObjects.size()) {
         editingDomain = TransactionHelper.getEditingDomain(selectedObjects);
      }
    }
    
    if (null != editingDomain) {
      // Get the appropriate undo context.
      IUndoContext undoContext = ((IWorkspaceCommandStack) editingDomain.getCommandStack()).getDefaultUndoContext();
      // Update handlers.
      if (undoActionHandler != null) {
        undoActionHandler.setContext(undoContext);
      }
      if (redoActionHandler != null) {
        redoActionHandler.setContext(undoContext);
      }
    }
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#dispose()
   */
  @Override
  public void dispose() {
    super.dispose();
    undoActionHandler.dispose();
    undoActionHandler = null;
    redoActionHandler.dispose();
    redoActionHandler = null;
    // Remove the listener used to refresh undo / redo actions states.
    NavigatorEditingDomainDispatcher.unregisterCommandStackSelectionProvider(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void commandStackSelectionChanged(ISelection selection) {
    updateActionBars();
  }
}
