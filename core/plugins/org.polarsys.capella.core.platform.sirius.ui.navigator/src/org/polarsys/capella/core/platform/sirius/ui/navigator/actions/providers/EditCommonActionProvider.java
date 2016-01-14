/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.platform.sirius.ui.navigator.actions.providers;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
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
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.SortSelectionAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.move.MoveDownAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.move.MoveUpAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.core.ui.resources.CapellaUIResourcesPlugin;

/**
 * The edit contribution actions provider.
 */
public class EditCommonActionProvider extends CommonActionProvider {
  private BaseSelectionListenerAction _copyAction;
  private BaseSelectionListenerAction _cutAction;
  private CapellaDeleteAction _deleteAction;
  private BaseSelectionListenerAction _moveDown;
  private BaseSelectionListenerAction _moveUp;
  private BaseSelectionListenerAction _sortContent;
  private BaseSelectionListenerAction _sortSelection;

  private BaseSelectionListenerAction _pasteAction;
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
    if (null != _sortSelection) {
      selectionProvider.removeSelectionChangedListener(_sortSelection);
      _sortSelection = null;
    }
    if (null != _renameAction) {
      selectionProvider.removeSelectionChangedListener(_renameAction);
      _renameAction = null;
    }
    super.dispose();
  }

  @Override
  public void fillActionBars(IActionBars actionBars) {
    actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(), _cutAction);
    actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(), _copyAction);
    actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(), _pasteAction);
    actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), _deleteAction);
    actionBars.setGlobalActionHandler(ActionFactory.RENAME.getId(), _renameAction);

    // Handle Rename action with delegation for Cut, Copy, Paste...
    TextActionHandler textActionHandler = new TextActionHandler(actionBars); // hook handlers
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
  public void fillContextMenu(IMenuManager menu) {
    menu.appendToGroup(ICommonMenuConstants.GROUP_EDIT, _cutAction);
    menu.appendToGroup(ICommonMenuConstants.GROUP_EDIT, _copyAction);
    menu.appendToGroup(ICommonMenuConstants.GROUP_EDIT, _pasteAction);
    menu.appendToGroup(ICommonMenuConstants.GROUP_EDIT, _deleteAction);
    menu.appendToGroup(ICommonMenuConstants.GROUP_EDIT, new Separator());
    menu.appendToGroup(ICommonMenuConstants.GROUP_EDIT, _moveUp);
    menu.appendToGroup(ICommonMenuConstants.GROUP_EDIT, _sortContent);
    menu.appendToGroup(ICommonMenuConstants.GROUP_EDIT, _sortSelection);
    menu.appendToGroup(ICommonMenuConstants.GROUP_EDIT, _moveDown);
    menu.appendToGroup(ICommonMenuConstants.GROUP_EDIT, new Separator());

  }

  /**
   * @see org.eclipse.ui.navigator.CommonActionProvider#init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
   */
  @Override
  public void init(ICommonActionExtensionSite site) {
    super.init(site);
    ICommonViewerSite commonViewSite = site.getViewSite();
    if (!(commonViewSite instanceof ICommonViewerWorkbenchSite)) {
      return;
    }
    ICommonViewerWorkbenchSite commonViewerWorkbenchSite = (ICommonViewerWorkbenchSite) commonViewSite;

    // Gets the active part.
    CapellaCommonNavigator activePart = (CapellaCommonNavigator) commonViewerWorkbenchSite.getPart();

    // Gets the shared images.
    ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
    ISelectionProvider selectionProvider = commonViewSite.getSelectionProvider();

    _cutAction = new CapellaCutAction(site.getStructuredViewer());
    _cutAction.setActionDefinitionId(IWorkbenchActionDefinitionIds.CUT);
    _cutAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT));
    _cutAction.setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT_DISABLED));
    SelectionHelper.registerToSelectionChanges(_cutAction, selectionProvider);
    _copyAction = new CapellaCopyAction(site.getStructuredViewer()) {
      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public void run() {
        super.run();
        // Force to refresh the paste action, to be able to paste the copied selection into the clipboard, directly
        // without changing the selection.
        _pasteAction.selectionChanged(getStructuredSelection());
      }
    };
    _copyAction.setActionDefinitionId(IWorkbenchActionDefinitionIds.COPY);
    _copyAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
    _copyAction.setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY_DISABLED));
    SelectionHelper.registerToSelectionChanges(_copyAction, selectionProvider);

    _pasteAction = new CapellaPasteAction();
    _pasteAction.setActionDefinitionId(IWorkbenchActionDefinitionIds.PASTE);
    _pasteAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
    _pasteAction.setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE_DISABLED));
    SelectionHelper.registerToSelectionChanges(_pasteAction, selectionProvider);

    _deleteAction = new CapellaDeleteAction();
    _deleteAction.setActionDefinitionId(IWorkbenchActionDefinitionIds.DELETE);
    _deleteAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
    _deleteAction.setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED));
    SelectionHelper.registerToSelectionChanges(_deleteAction, selectionProvider);

    _moveUp = new MoveUpAction();
    SelectionHelper.registerToSelectionChanges(_moveUp, selectionProvider);

    _moveDown = new MoveDownAction();
    SelectionHelper.registerToSelectionChanges(_moveDown, selectionProvider);

    _sortContent = new SortContentAction();
    _sortContent.setImageDescriptor(CapellaUIResourcesPlugin.getDefault().getImageDescriptor(
        org.polarsys.capella.core.ui.resources.IImageKeys.CAPELLA_SORT_IMG_16));
    _sortContent.setDisabledImageDescriptor(CapellaUIResourcesPlugin.getDefault().getImageDescriptor(
        org.polarsys.capella.core.ui.resources.IImageKeys.CAPELLA_SORT_DISABLED_IMG_16));
    SelectionHelper.registerToSelectionChanges(_sortContent, selectionProvider);

    _sortSelection = new SortSelectionAction();
    _sortSelection.setImageDescriptor(CapellaUIResourcesPlugin.getDefault().getImageDescriptor(
        org.polarsys.capella.core.ui.resources.IImageKeys.CAPELLA_SORT_IMG_16));
    _sortSelection.setDisabledImageDescriptor(CapellaUIResourcesPlugin.getDefault().getImageDescriptor(
        org.polarsys.capella.core.ui.resources.IImageKeys.CAPELLA_SORT_DISABLED_IMG_16));
    SelectionHelper.registerToSelectionChanges(_sortSelection, selectionProvider);

    // Initialize the rename action.
    _renameAction = new RenameAction(activePart);
    _renameAction.setActionDefinitionId(IWorkbenchActionDefinitionIds.RENAME);
    SelectionHelper.registerToSelectionChanges(_renameAction, selectionProvider);
  }
}
