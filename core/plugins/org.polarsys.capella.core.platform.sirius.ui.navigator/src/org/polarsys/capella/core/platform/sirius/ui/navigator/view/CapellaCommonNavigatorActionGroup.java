/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.navigator.view;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.internal.navigator.CommonNavigatorActionGroup;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.LinkHelperService;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.QuickFiltersMenu;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.SearchMenu;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.SelectionHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.move.MoveDownAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.move.MoveUpAction;

public class CapellaCommonNavigatorActionGroup extends CommonNavigatorActionGroup {
  CapellaCommonNavigator navigator;
  
  private MoveDownAction moveDownAction;
  private MoveUpAction moveUpAction;
  
  private QuickFiltersMenu quickFiltersMenu;
  private SearchMenu searchMenu;

  public CapellaCommonNavigatorActionGroup(CapellaCommonNavigator aNavigator, CommonViewer aViewer,
      LinkHelperService linkHelperService) {
    super(aNavigator, aViewer, linkHelperService);
    this.navigator = aNavigator;
    
    ISelectionProvider selectionProvider = navigator.getViewSite().getSelectionProvider();
    
    moveUpAction = new MoveUpAction();
    SelectionHelper.registerToSelectionChanges(moveUpAction, selectionProvider);
    
    moveDownAction = new MoveDownAction();
    SelectionHelper.registerToSelectionChanges(moveDownAction, selectionProvider);
    
    // Update enablement state.
    IStructuredSelection selection = (IStructuredSelection) selectionProvider
        .getSelection();
    moveUpAction.selectionChanged(selection);
    moveDownAction.selectionChanged(selection);
    
    quickFiltersMenu = new QuickFiltersMenu(navigator);
    
    searchMenu = new SearchMenu(navigator);
  }

  @Override
  protected void fillToolBar(IToolBarManager toolBar) {

    toolBar.add(moveUpAction);
    toolBar.add(moveDownAction);
    
    toolBar.add(new Separator());
    super.fillToolBar(toolBar);
  }

  @Override
  protected void fillViewMenu(IMenuManager menu) {
    // Add the Quick Filters menu on top
    menu.insertBefore(IWorkbenchActionConstants.MB_ADDITIONS, quickFiltersMenu);
    
    super.fillViewMenu(menu);
    
    menu.add(new Separator());
    menu.add(searchMenu);
   
  }

  @Override
  public void dispose() {
    super.dispose();
    
    // TODO: To refactor: the MoveUpAction or AbstractCommandActionHandler should have its proper "dispose" method
    ISelectionProvider selectionProvider = navigator.getViewSite().getSelectionProvider();
    if (null != moveUpAction) {
      selectionProvider.removeSelectionChangedListener(moveUpAction);
      moveUpAction = null;
    }
    if (null != moveDownAction) {
      selectionProvider.removeSelectionChangedListener(moveDownAction);
      moveDownAction = null;
    }
    
    quickFiltersMenu.dispose();
    searchMenu.dispose();
  }
}
