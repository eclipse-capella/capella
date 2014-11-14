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

import java.util.EventObject;

import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
import org.eclipse.ui.operations.RedoActionHandler;
import org.eclipse.ui.operations.UndoActionHandler;

import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;

/**
 * The undo / redo actions provider.
 */
public class UndoRedoActionProvider extends CommonActionProvider {
  /**
   * Undo action handler based on {@link IOperationHistory}.
   */
  private UndoActionHandler undoActionHandler;
  /**
   * Redo action handler based on {@link IOperationHistory}.
   */
  private RedoActionHandler redoActionHandler;
  /**
   * Command stack listener to refresh undo redo actions state.
   */
  private CommandStackListener _commandStackListener;

  /**
   * @see org.eclipse.ui.navigator.CommonActionProvider#init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
   */
  @Override
  public void init(ICommonActionExtensionSite site_p) {
    super.init(site_p);
    // Get the editing domain.
    TransactionalEditingDomain editingDomain = MDEAdapterFactory.getEditingDomain();
    // Create the undo / redo action group based on IOperationHistory as GMF also uses this one rather than EMF undo / redo actions.
    // Get the appropriate undo context.
    IUndoContext undoContext = ((IWorkspaceCommandStack) editingDomain.getCommandStack()).getDefaultUndoContext();
    IWorkbenchPartSite site = ((ICommonViewerWorkbenchSite) site_p.getViewSite()).getSite();
    // Create the undo action handler
    undoActionHandler = new UndoActionHandler(site, undoContext);
    // Create the redo action handler
    redoActionHandler = new RedoActionHandler(site, undoContext);
    // Create a command stack listener to update undo / redo handlers.
    _commandStackListener = new CommandStackListener() {
      /**
       * @see org.eclipse.emf.common.command.CommandStackListener#commandStackChanged(java.util.EventObject)
       */
      @SuppressWarnings("synthetic-access")
      public void commandStackChanged(EventObject event_p) {
        Command mostRecentCommand = ((CommandStack) event_p.getSource()).getMostRecentCommand();
        if (null != mostRecentCommand) {
          // Update action bars in an asynchronous way.
          getActionSite().getViewSite().getShell().getDisplay().asyncExec(new Runnable() {
            /**
             * @see java.lang.Runnable#run()
             */
            public void run() {
              updateActionBars();
            }
          });
        }
      }
    };
    // Add the listener to the command stack.
    editingDomain.getCommandStack().addCommandStackListener(_commandStackListener);
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#fillActionBars(org.eclipse.ui.IActionBars)
   */
  @Override
  public void fillActionBars(IActionBars actionBars_p) {
    actionBars_p.setGlobalActionHandler(ActionFactory.UNDO.getId(), undoActionHandler);
    actionBars_p.setGlobalActionHandler(ActionFactory.REDO.getId(), redoActionHandler);
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
   */
  @Override
  public void fillContextMenu(IMenuManager menu_p) {
    menu_p.appendToGroup(ICommonMenuConstants.GROUP_EDIT, undoActionHandler);
    menu_p.appendToGroup(ICommonMenuConstants.GROUP_EDIT, redoActionHandler);
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#updateActionBars()
   */
  @Override
  public void updateActionBars() {
    if (undoActionHandler != null) {
      undoActionHandler.update();
    }
    if (undoActionHandler != null) {
      redoActionHandler.update();
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
    MDEAdapterFactory.getEditingDomain().getCommandStack().removeCommandStackListener(_commandStackListener);
  }
}
