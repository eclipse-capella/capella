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

import org.eclipse.emf.edit.ui.action.CopyAction;
import org.eclipse.emf.edit.ui.action.CutAction;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.TextActionHandler;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
import org.eclipse.ui.texteditor.IWorkbenchActionDefinitionIds;

import org.polarsys.capella.core.platform.sirius.ui.actions.CapellaCopyAction;
import org.polarsys.capella.core.platform.sirius.ui.actions.CapellaCutAction;
import org.polarsys.capella.core.platform.sirius.ui.actions.CapellaDeleteAction;
import org.polarsys.capella.core.platform.sirius.ui.actions.CapellaPasteAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.RenameAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.SelectionHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.SortContentAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.move.MoveDownAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.move.MoveUpAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.core.ui.resources.CapellaUIResourcesPlugin;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.tig.ef.ExecutionManager;

/**
 * The edit contribution actions provider.
 */
public class EditCommonActionProvider extends CommonActionProvider {
  private CopyAction _copyAction;
  private CutAction _cutAction;
  private CapellaDeleteAction _deleteAction;
  private MoveDownAction _moveDown;
  private MoveUpAction _moveUp;
  private SortContentAction _sortContent;

  private CapellaPasteAction _pasteAction;
  private RenameAction _renameAction;

  // private ValidateAction _validateAction;

  /**
   * Constructs the edit contribution actions provider.
   */
  public EditCommonActionProvider() {
    // Do nothing.
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#dispose()
   */
  @Override
  public void dispose() {
    ISelectionProvider selectionProvider = getActionSite().getViewSite().getSelectionProvider();
    if (null != _cutAction) {
      selectionProvider.removeSelectionChangedListener(_cutAction);
      _cutAction = null;
    }
    if (null != _copyAction) {
      selectionProvider.removeSelectionChangedListener(_copyAction);
      _copyAction = null;
    }
    if (null != _pasteAction) {
      selectionProvider.removeSelectionChangedListener(_pasteAction);
      _pasteAction = null;
    }
    if (null != _deleteAction) {
      selectionProvider.removeSelectionChangedListener(_deleteAction);
      // Call dispose to remove the property listener...
      _deleteAction.dispose();
      _deleteAction = null;
    }

    if (null != _moveUp) {
      selectionProvider.removeSelectionChangedListener(_moveUp);
      _moveUp = null;
    }
    if (null != _moveDown) {
      selectionProvider.removeSelectionChangedListener(_moveDown);
      _moveDown = null;
    }
    if (null != _sortContent) {
      selectionProvider.removeSelectionChangedListener(_sortContent);
      _sortContent = null;
    }
    if (null != _renameAction) {
      selectionProvider.removeSelectionChangedListener(_renameAction);
      _renameAction = null;
    }
    super.dispose();
  }

  @Override
  public void fillActionBars(IActionBars actionBars_p) {
    actionBars_p.setGlobalActionHandler(ActionFactory.CUT.getId(), _cutAction);
    actionBars_p.setGlobalActionHandler(ActionFactory.COPY.getId(), _copyAction);
    actionBars_p.setGlobalActionHandler(ActionFactory.PASTE.getId(), _pasteAction);
    actionBars_p.setGlobalActionHandler(ActionFactory.DELETE.getId(), _deleteAction);
    actionBars_p.setGlobalActionHandler(ActionFactory.RENAME.getId(), _renameAction);

    // Handle Rename action with delegation for Cut, Copy, Paste...
    TextActionHandler textActionHandler = new TextActionHandler(actionBars_p); // hook handlers
    textActionHandler.setCutAction(_cutAction);
    textActionHandler.setCopyAction(_copyAction);
    textActionHandler.setPasteAction(_pasteAction);
    textActionHandler.setDeleteAction(_deleteAction);
    _renameAction.setTextActionHandler(textActionHandler);
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
   */
  @Override
  public void fillContextMenu(IMenuManager menu_p) {
    menu_p.appendToGroup(ICommonMenuConstants.GROUP_EDIT, _cutAction);
    menu_p.appendToGroup(ICommonMenuConstants.GROUP_EDIT, _copyAction);
    menu_p.appendToGroup(ICommonMenuConstants.GROUP_EDIT, _pasteAction);
    menu_p.appendToGroup(ICommonMenuConstants.GROUP_EDIT, _deleteAction);
    menu_p.appendToGroup(ICommonMenuConstants.GROUP_EDIT, new Separator());
    menu_p.appendToGroup(ICommonMenuConstants.GROUP_EDIT, _moveUp);
    menu_p.appendToGroup(ICommonMenuConstants.GROUP_EDIT, _sortContent);
    menu_p.appendToGroup(ICommonMenuConstants.GROUP_EDIT, _moveDown);
    menu_p.appendToGroup(ICommonMenuConstants.GROUP_EDIT, new Separator());

  }

  /**
   * @see org.eclipse.ui.navigator.CommonActionProvider#init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
   */
  @Override
  public void init(ICommonActionExtensionSite site_p) {
    super.init(site_p);
    ICommonViewerSite commonViewSite = site_p.getViewSite();
    if (!(commonViewSite instanceof ICommonViewerWorkbenchSite)) {
      return;
    }
    ICommonViewerWorkbenchSite commonViewerWorkbenchSite = (ICommonViewerWorkbenchSite) commonViewSite;

    // Gets the active part.
    CapellaCommonNavigator activePart = (CapellaCommonNavigator) commonViewerWorkbenchSite.getPart();
    // Gets the editing domain.
    ExecutionManager mgr = MDEAdapterFactory.getExecutionManager();
    TransactionalEditingDomain editingDomain = MDEAdapterFactory.getEditingDomain();

    // Gets the shared images.
    ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
    ISelectionProvider selectionProvider = commonViewSite.getSelectionProvider();

    _cutAction = new CapellaCutAction(editingDomain, site_p.getStructuredViewer());
    _cutAction.setActionDefinitionId(IWorkbenchActionDefinitionIds.CUT);
    _cutAction.setActiveWorkbenchPart(activePart);
    _cutAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT));
    _cutAction.setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT_DISABLED));
    SelectionHelper.registerToSelectionChanges(_cutAction, selectionProvider);
    _copyAction = new CapellaCopyAction(editingDomain, site_p.getStructuredViewer()) {
      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public void run() {
        super.run();
        // Force to refresh the paste action, to be able to paste the copied selection into the clipboard, directly without changing the selection.
        _pasteAction.selectionChanged(getStructuredSelection());
      }
    };
    _copyAction.setActionDefinitionId(IWorkbenchActionDefinitionIds.COPY);
    _copyAction.setActiveWorkbenchPart(activePart);
    _copyAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
    _copyAction.setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY_DISABLED));
    SelectionHelper.registerToSelectionChanges(_copyAction, selectionProvider);

    _pasteAction = new CapellaPasteAction(editingDomain);
    _pasteAction.setActionDefinitionId(IWorkbenchActionDefinitionIds.PASTE);
    _pasteAction.setActiveWorkbenchPart(activePart);
    _pasteAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
    _pasteAction.setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE_DISABLED));
    SelectionHelper.registerToSelectionChanges(_pasteAction, selectionProvider);

    _deleteAction = new CapellaDeleteAction(mgr);
    _deleteAction.setActionDefinitionId(IWorkbenchActionDefinitionIds.DELETE);
    _deleteAction.setActiveWorkbenchPart(activePart);
    _deleteAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
    _deleteAction.setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED));
    SelectionHelper.registerToSelectionChanges(_deleteAction, selectionProvider);

    _moveUp = new MoveUpAction(editingDomain);
    SelectionHelper.registerToSelectionChanges(_moveUp, selectionProvider);

    _moveDown = new MoveDownAction(editingDomain);
    SelectionHelper.registerToSelectionChanges(_moveDown, selectionProvider);

    _sortContent = new SortContentAction();
    _sortContent.setImageDescriptor(CapellaUIResourcesPlugin.getDefault().getImageDescriptor(
        org.polarsys.capella.core.ui.resources.IImageKeys.CAPELLA_SORT_IMG_16));
    _sortContent.setDisabledImageDescriptor(CapellaUIResourcesPlugin.getDefault().getImageDescriptor(
        org.polarsys.capella.core.ui.resources.IImageKeys.CAPELLA_SORT_DISABLED_IMG_16));
    SelectionHelper.registerToSelectionChanges(_sortContent, selectionProvider);

    // Initialize the rename action.
    _renameAction = new RenameAction(editingDomain, activePart);
    _renameAction.setActionDefinitionId(IWorkbenchActionDefinitionIds.RENAME);
    SelectionHelper.registerToSelectionChanges(_renameAction, selectionProvider);
  }
}
