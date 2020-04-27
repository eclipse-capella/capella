/*******************************************************************************
 * Copyright (c) 2006, 2015 Corporation and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    IBM - Initial API and implementation 
 *    Thales - Contributor
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions.providers;

import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionGroup;
import org.eclipse.ui.actions.MoveResourceAction;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
import org.eclipse.ui.operations.UndoRedoActionGroup;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.RenameResourceAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.internal.ResourceSelectionUtil;

/**
 * Copied from org.eclipse.ui.internal.navigator.resources.actions.RefactorActionProvider to override RenameAction.
 */
public class RefactorActionProvider extends CommonActionProvider {
  /**
   * Copied from org.eclipse.ui.internal.navigator.resources.actions.RefactorActionGroup
   */
  class RefactorActionGroup extends ActionGroup {
    private RenameResourceAction _renameAction;
    private MoveResourceAction _moveAction;
    private Shell _shell;
    private Tree _tree;

    /**
     * @param aShell
     * @param aTree
     */
    public RefactorActionGroup(Shell aShell, Tree aTree) {
      _shell = aShell;
      _tree = aTree;
      makeActions();
    }

    @Override
    public void fillActionBars(IActionBars actionBars) {
      updateActionBars();

      actionBars.setGlobalActionHandler(ActionFactory.MOVE.getId(), _moveAction);
      actionBars.setGlobalActionHandler(ActionFactory.RENAME.getId(), _renameAction);
    }

    @Override
    public void fillContextMenu(IMenuManager menu) {
      IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();

      boolean anyResourceSelected = !selection.isEmpty() && ResourceSelectionUtil.allResourcesAreOfType(selection, IResource.PROJECT | IResource.FOLDER | IResource.FILE);

      if (anyResourceSelected) {
        _moveAction.selectionChanged(selection);
        menu.appendToGroup(ICommonMenuConstants.GROUP_REORGANIZE, _moveAction);
        _renameAction.selectionChanged(selection);
        menu.insertAfter(_moveAction.getId(), _renameAction);
      }
    }

    /**
     * Handles a key pressed event by invoking the appropriate action.
     * 
     * @param event
     *          The Key Event
     */
    public void handleKeyPressed(KeyEvent event) {

      if ((event.keyCode == SWT.F2) && (event.stateMask == 0)) {
        if (_renameAction.isEnabled()) {
          _renameAction.run();
        }

        // Swallow the event.
        event.doit = false;
      }
    }

    protected void makeActions() {

      _moveAction = new MoveResourceAction(_shell);
      _moveAction.setActionDefinitionId(IWorkbenchCommandConstants.FILE_MOVE);

      _renameAction = new RenameResourceAction(_shell, _tree);
      _renameAction.setActionDefinitionId(IWorkbenchCommandConstants.FILE_RENAME);
    }

    @Override
    public void updateActionBars() {
      IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();

      _moveAction.selectionChanged(selection);
      _renameAction.selectionChanged(selection);
    }

  }

  /**
   * Undo Redo action group.
   */
  private UndoRedoActionGroup _undoRedoGroup;
  /**
   * Refactor action group that contains the rename action.
   */
  private RefactorActionGroup _refactorGroup;

  /**
   * View site.
   */
  private ICommonActionExtensionSite _site;

  /**
   * @see org.eclipse.ui.actions.ActionGroup#dispose()
   */
  @Override
  public void dispose() {
    _undoRedoGroup.dispose();
    _refactorGroup.dispose();
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#fillActionBars(org.eclipse.ui.IActionBars)
   */
  @Override
  public void fillActionBars(IActionBars actionBars) {
    _undoRedoGroup.fillActionBars(actionBars);
    _refactorGroup.fillActionBars(actionBars);
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
   */
  @Override
  public void fillContextMenu(IMenuManager menu) {
    _undoRedoGroup.fillContextMenu(menu);
    _refactorGroup.fillContextMenu(menu);
  }

  /**
   * @see org.eclipse.ui.navigator.CommonActionProvider#init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
   */
  @Override
  public void init(ICommonActionExtensionSite anActionSite) {
    _site = anActionSite;
    _refactorGroup = new RefactorActionGroup(_site.getViewSite().getShell(), (Tree) _site.getStructuredViewer().getControl());

    IUndoContext workspaceContext = (IUndoContext) ResourcesPlugin.getWorkspace().getAdapter(IUndoContext.class);
    _undoRedoGroup = new UndoRedoActionGroup(((ICommonViewerWorkbenchSite) anActionSite.getViewSite()).getSite(), workspaceContext, true);
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#setContext(org.eclipse.ui.actions.ActionContext)
   */
  @Override
  public void setContext(ActionContext context) {
    _undoRedoGroup.setContext(context);
    _refactorGroup.setContext(context);
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#updateActionBars()
   */
  @Override
  public void updateActionBars() {
    _undoRedoGroup.updateActionBars();
    _refactorGroup.updateActionBars();
  }
}