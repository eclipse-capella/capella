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
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.eclipse.ui.actions.TextActionHandler;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
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
  private BaseSelectionListenerAction copyAction;
  private BaseSelectionListenerAction cutAction;
  private CapellaDeleteAction deleteAction;
  private BaseSelectionListenerAction moveDown;
  private BaseSelectionListenerAction moveUp;
  private BaseSelectionListenerAction sortContent;
  private BaseSelectionListenerAction sortSelection;

  private BaseSelectionListenerAction pasteAction;
  private RenameAction renameAction;

  // private ValidateAction validateAction;

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
    if (null != cutAction) {
      selectionProvider.removeSelectionChangedListener(cutAction);
      cutAction = null;
    }
    if (null != copyAction) {
      selectionProvider.removeSelectionChangedListener(copyAction);
      copyAction = null;
    }
    if (null != pasteAction) {
      selectionProvider.removeSelectionChangedListener(pasteAction);
      pasteAction = null;
    }
    if (null != deleteAction) {
      selectionProvider.removeSelectionChangedListener(deleteAction);
      // Call dispose to remove the property listener...
      deleteAction.dispose();
      deleteAction = null;
    }

    if (null != moveUp) {
      selectionProvider.removeSelectionChangedListener(moveUp);
      moveUp = null;
    }
    if (null != moveDown) {
      selectionProvider.removeSelectionChangedListener(moveDown);
      moveDown = null;
    }
    if (null != sortContent) {
      selectionProvider.removeSelectionChangedListener(sortContent);
      sortContent = null;
    }
    if (null != sortSelection) {
      selectionProvider.removeSelectionChangedListener(sortSelection);
      sortSelection = null;
    }
    if (null != renameAction) {
      selectionProvider.removeSelectionChangedListener(renameAction);
      renameAction = null;
    }
    super.dispose();
  }

  @Override
  public void fillActionBars(IActionBars actionBars) {
    actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(), cutAction);
    actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(), copyAction);
    actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(), pasteAction);
    actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), deleteAction);
    actionBars.setGlobalActionHandler(ActionFactory.RENAME.getId(), renameAction);

    // Handle Rename action with delegation for Cut, Copy, Paste...
    TextActionHandler textActionHandler = new TextActionHandler(actionBars); // hook handlers
    textActionHandler.setCutAction(cutAction);
    textActionHandler.setCopyAction(copyAction);
    textActionHandler.setPasteAction(pasteAction);
    textActionHandler.setDeleteAction(deleteAction);
    renameAction.setTextActionHandler(textActionHandler);
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
   */
  @Override
  public void fillContextMenu(IMenuManager menu) {
    menu.appendToGroup(ICommonMenuConstants.GROUP_EDIT, cutAction);
    menu.appendToGroup(ICommonMenuConstants.GROUP_EDIT, copyAction);
    menu.appendToGroup(ICommonMenuConstants.GROUP_EDIT, pasteAction);
    menu.appendToGroup(ICommonMenuConstants.GROUP_EDIT, deleteAction);
    menu.appendToGroup(ICommonMenuConstants.GROUP_EDIT, new Separator());
    menu.appendToGroup(ICommonMenuConstants.GROUP_EDIT, moveUp);
    menu.appendToGroup(ICommonMenuConstants.GROUP_EDIT, sortContent);
    menu.appendToGroup(ICommonMenuConstants.GROUP_EDIT, sortSelection);
    menu.appendToGroup(ICommonMenuConstants.GROUP_EDIT, moveDown);
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

    cutAction = new CapellaCutAction(site.getStructuredViewer());
    cutAction.setActionDefinitionId(IWorkbenchCommandConstants.EDIT_CUT);
    cutAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT));
    cutAction.setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT_DISABLED));
    SelectionHelper.registerToSelectionChanges(cutAction, selectionProvider);
    copyAction = new CapellaCopyAction(site.getStructuredViewer()) {
      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public void run() {
        super.run();
        // Force to refresh the paste action, to be able to paste the copied selection into the clipboard, directly
        // without changing the selection.
        pasteAction.selectionChanged(getStructuredSelection());
      }
    };
    copyAction.setActionDefinitionId(IWorkbenchCommandConstants.EDIT_COPY);
    copyAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
    copyAction.setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY_DISABLED));
    SelectionHelper.registerToSelectionChanges(copyAction, selectionProvider);

    pasteAction = new CapellaPasteAction();
    pasteAction.setActionDefinitionId(IWorkbenchCommandConstants.EDIT_PASTE);
    pasteAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
    pasteAction.setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE_DISABLED));
    SelectionHelper.registerToSelectionChanges(pasteAction, selectionProvider);

    deleteAction = new CapellaDeleteAction();
    deleteAction.setActionDefinitionId(IWorkbenchCommandConstants.EDIT_DELETE);
    deleteAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
    deleteAction.setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED));
    SelectionHelper.registerToSelectionChanges(deleteAction, selectionProvider);

    moveUp = new MoveUpAction();
    SelectionHelper.registerToSelectionChanges(moveUp, selectionProvider);

    moveDown = new MoveDownAction();
    SelectionHelper.registerToSelectionChanges(moveDown, selectionProvider);

    sortContent = new SortContentAction();
    sortContent.setImageDescriptor(CapellaUIResourcesPlugin.getDefault().getImageDescriptor(
        org.polarsys.capella.core.ui.resources.IImageKeys.CAPELLA_SORT_IMG_16));
    sortContent.setDisabledImageDescriptor(CapellaUIResourcesPlugin.getDefault().getImageDescriptor(
        org.polarsys.capella.core.ui.resources.IImageKeys.CAPELLA_SORT_DISABLED_IMG_16));
    SelectionHelper.registerToSelectionChanges(sortContent, selectionProvider);

    sortSelection = new SortSelectionAction();
    sortSelection.setImageDescriptor(CapellaUIResourcesPlugin.getDefault().getImageDescriptor(
        org.polarsys.capella.core.ui.resources.IImageKeys.CAPELLA_SORT_IMG_16));
    sortSelection.setDisabledImageDescriptor(CapellaUIResourcesPlugin.getDefault().getImageDescriptor(
        org.polarsys.capella.core.ui.resources.IImageKeys.CAPELLA_SORT_DISABLED_IMG_16));
    SelectionHelper.registerToSelectionChanges(sortSelection, selectionProvider);

    // Initialize the rename action.
    renameAction = new RenameAction(activePart);
    renameAction.setActionDefinitionId(IWorkbenchCommandConstants.FILE_RENAME);
    SelectionHelper.registerToSelectionChanges(renameAction, selectionProvider);
  }
}
