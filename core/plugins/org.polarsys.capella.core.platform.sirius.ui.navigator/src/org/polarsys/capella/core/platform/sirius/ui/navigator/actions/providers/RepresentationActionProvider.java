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
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.CloneAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.SelectionHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.move.representation.MoveRepresentationMenuManager;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.core.sirius.ui.actions.DeleteRepresentationAction;
import org.polarsys.capella.core.sirius.ui.actions.OpenRepresentationsAction;
import org.polarsys.capella.core.sirius.ui.actions.RenameRepresentationAction;

/**
 * The representation action provider.
 */
public class RepresentationActionProvider extends CommonActionProvider {
  // The action to delete selected representation.
  private DeleteRepresentationAction _deleteRepresentationAction;
  // The action to rename selected representation.
  private RenameRepresentationAction _renameRepresentationAction;
  // The action to open representations.
  private OpenRepresentationsAction _openRepresentation;
  // The action to move representations.
  private MoveRepresentationMenuManager moveRepresentationMenu;
  // The action to clone a representation.
  private CloneAction _cloneAction;

  /**
   * Constructs the representation action provider.
   */
  public RepresentationActionProvider() {
    // Do nothing.
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
    // Gets the active part.
    ICommonViewerWorkbenchSite commonViewerWorkbenchSite = (ICommonViewerWorkbenchSite) commonViewSite;
    CapellaCommonNavigator activePart = (CapellaCommonNavigator) commonViewerWorkbenchSite.getPart();

    ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
    ISelectionProvider selectionProvider = commonViewSite.getSelectionProvider();
    _deleteRepresentationAction = new DeleteRepresentationAction();
    _deleteRepresentationAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
    _deleteRepresentationAction.setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED));
    SelectionHelper.registerToSelectionChanges(_deleteRepresentationAction, selectionProvider);

    _renameRepresentationAction = new RenameRepresentationAction();
    SelectionHelper.registerToSelectionChanges(_renameRepresentationAction, selectionProvider);

    _openRepresentation = new OpenRepresentationsAction();
    SelectionHelper.registerToSelectionChanges(_openRepresentation, selectionProvider);

    moveRepresentationMenu = new MoveRepresentationMenuManager();
    SelectionHelper.registerToSelectionChanges(moveRepresentationMenu, selectionProvider);

    _cloneAction = new CloneAction(activePart.getCommonViewer());
    _cloneAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
    SelectionHelper.registerToSelectionChanges(_cloneAction, selectionProvider);
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#fillActionBars(org.eclipse.ui.IActionBars)
   */
  @Override
  public void fillActionBars(IActionBars actionBars_p) {
    // Make sure action contained list are freed at each selection time.
    moveRepresentationMenu.dispose();
    actionBars_p.setGlobalActionHandler(ICommonActionConstants.OPEN, _openRepresentation);
    actionBars_p.setGlobalActionHandler(ActionFactory.DELETE.getId(), _deleteRepresentationAction);
    actionBars_p.setGlobalActionHandler(ActionFactory.RENAME.getId(), _renameRepresentationAction);
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
   */
  @Override
  public void fillContextMenu(IMenuManager menu_p) {
    menu_p.appendToGroup(ICommonMenuConstants.GROUP_OPEN, _openRepresentation);

    menu_p.appendToGroup(ICommonMenuConstants.GROUP_EDIT, _cloneAction);
    menu_p.appendToGroup(ICommonMenuConstants.GROUP_EDIT, _deleteRepresentationAction);
    menu_p.appendToGroup(ICommonMenuConstants.GROUP_PORT, new Separator());
    menu_p.appendToGroup(ICommonMenuConstants.GROUP_PORT, moveRepresentationMenu);
    
    menu_p.appendToGroup(ICommonMenuConstants.GROUP_PORT, _renameRepresentationAction);
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#dispose()
   */
  @Override
  public void dispose() {
    ISelectionProvider selectionProvider = getActionSite().getViewSite().getSelectionProvider();
    if (null != _renameRepresentationAction) {
      selectionProvider.removeSelectionChangedListener(_renameRepresentationAction);
      _renameRepresentationAction = null;
    }
    if (null != _deleteRepresentationAction) {
      selectionProvider.removeSelectionChangedListener(_deleteRepresentationAction);
      _deleteRepresentationAction = null;
    }
    if (null != _openRepresentation) {
      selectionProvider.removeSelectionChangedListener(_openRepresentation);
      _openRepresentation = null;
    }
    if (null != moveRepresentationMenu) {
      selectionProvider.removeSelectionChangedListener(moveRepresentationMenu);
      moveRepresentationMenu.dispose();
      moveRepresentationMenu = null;
    }
    if (null != _cloneAction) {
      selectionProvider.removeSelectionChangedListener(_cloneAction);
      _cloneAction = null;
    }

    super.dispose();
  }
}
