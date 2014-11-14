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
package org.polarsys.capella.core.menu.dynamic;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.navigator.ICommonMenuConstants;

import org.polarsys.capella.common.menu.dynamic.AbstractActionProvider;
import org.polarsys.capella.common.menu.dynamic.contributions.ActionContributionProvider;

/**
 */
public class DynamicActionProvider extends AbstractActionProvider {
  /**
   * Dynamic creation action.
   */
  private DynamicCreationAction _dynamicAction;

  public DynamicActionProvider() {
    // Initialize the action provider to force it to load menu contributors.
    ActionContributionProvider.getInstance();
  }

  /**
   * @see org.polarsys.capella.common.mdsofa.rootasset.ui.workbench.action.navigator.AbstractActionProvider#initActions(org.eclipse.swt.widgets.Shell,
   *      org.eclipse.ui.IWorkbenchPage, org.eclipse.jface.viewers.ISelectionProvider)
   */
  @Override
  protected void initActions(Shell shell_p, IWorkbenchPage page_p, ISelectionProvider selectionProvider_p) {
    _dynamicAction = new DynamicCreationAction(shell_p, selectionProvider_p);
  }

  protected IContributionItem createContributionItem() {
    IMenuManager subMenuManager = new MenuManager(Messages.DynamicActionProvider_AddCapellaElement_Title, "Dynamic.Menu.ID"); //$NON-NLS-1$
    fillContextMenu(subMenuManager, _dynamicAction, null);
    return subMenuManager;
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
   */
  @Override
  public void fillContextMenu(IMenuManager menu_p) {
    // Fill actions located in GROUP_NEW
    menu_p.insertAfter(ICommonMenuConstants.GROUP_NEW, createContributionItem());
  }

  /**
   * @see org.polarsys.capella.common.mdsofa.rootasset.ui.workbench.action.navigator.AbstractActionProvider#fillActionBars(org.eclipse.ui.IActionBars)
   */
  @Override
  public void fillActionBars(IActionBars actionBars_p) {
    // Do nothing.
  }

  protected void fillContextMenu(IMenuManager menu_p, DynamicCreationAction action_p, String groupId_p) {
    // In this case, check really if the action is compatible with current selection.
    if (action_p.isSelectionCompatible()) {
      for (IContributionItem item : action_p.getStructuralDynamicActions()) {
        addContributionItem(menu_p, groupId_p, item);
      }

      // Add a separator between structural and non structural items.
      menu_p.add(new Separator());

      for (IContributionItem containedAction : action_p.getNonStructuralDynamicActions()) {
        addContributionItem(menu_p, groupId_p, containedAction);
      }

      // Add a separator between non structural and property value items.
      menu_p.add(new Separator());

      for (IContributionItem containedAction : action_p.getPropertyValueDynamicActions()) {
        addContributionItem(menu_p, groupId_p, containedAction);
      }

      // Add a separator between property value and extension items.
      menu_p.add(new Separator());

      for (IContributionItem containedAction : action_p.getExtensionDynamicActions()) {
        addContributionItem(menu_p, groupId_p, containedAction);
      }
    }
  }

  /**
   * Add given contribution item in specified menu.
   * @param menu_p
   * @param groupId_p
   * @param item_p
   */
  protected void addContributionItem(IMenuManager menu_p, String groupId_p, IContributionItem item_p) {
    // Append the action to a group if provided...
    if (null != groupId_p) {
      menu_p.appendToGroup(groupId_p, item_p);
    } else {
      menu_p.add(item_p);
    }
  }

  /**
   * Add given action in specified menu manager.
   * @param menu_p
   * @param groupId_p
   * @param action_p
   */
  protected void addAction(IMenuManager menu_p, String groupId_p, IAction action_p) {
    // Override the action contribution item to force the context menu to be
    // refreshed even if the selected object has not changed.
    addContributionItem(menu_p, groupId_p, new DynamicActionContributionItem(action_p));
  }
}
