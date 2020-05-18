/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.activities.IActivityManager;
import org.eclipse.ui.activities.IIdentifier;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.polarsys.capella.common.menu.dynamic.contributions.ActionContributionProvider;
import org.polarsys.capella.common.ui.menu.dynamic.AbstractActionProvider;

/**
 */
public class DynamicActionProvider extends AbstractActionProvider {
  private static final String ADD_NEW_ELEMENT_MENU_ID = "capella.project.element.menu";
  /**
   * Dynamic creation action.
   */
  protected DynamicCreationAction dynamicAction;

  public DynamicActionProvider() {
    // Initialize the action provider to force it to load menu contributors.
    ActionContributionProvider.getInstance();
  }

  /**
   * @see org.polarsys.capella.common.mdsofa.rootasset.ui.workbench.action.navigator.AbstractActionProvider#initActions(org.eclipse.swt.widgets.Shell,
   *      org.eclipse.ui.IWorkbenchPage, org.eclipse.jface.viewers.ISelectionProvider)
   */
  @Override
  protected void initActions(Shell shell, IWorkbenchPage page, ISelectionProvider selectionProvider) {
    dynamicAction = new DynamicCreationAction(shell, selectionProvider);
  }

  protected IContributionItem createContributionItem() {
    IMenuManager subMenuManager = new MenuManager(Messages.DynamicActionProvider_AddCapellaElement_Title,
        ADD_NEW_ELEMENT_MENU_ID);

    IActivityManager activityManager = PlatformUI.getWorkbench().getActivitySupport().getActivityManager();
    IIdentifier identifier = activityManager.getIdentifier(ADD_NEW_ELEMENT_MENU_ID);

    if (identifier.isEnabled()) {
      fillContextMenu(subMenuManager, dynamicAction, null);
    }

    return subMenuManager;
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
   */
  @Override
  public void fillContextMenu(IMenuManager menu) {
    // Fill actions located in GROUP_NEW
    menu.insertAfter(ICommonMenuConstants.GROUP_NEW, createContributionItem());
  }

  /**
   * @see org.polarsys.capella.common.mdsofa.rootasset.ui.workbench.action.navigator.AbstractActionProvider#fillActionBars(org.eclipse.ui.IActionBars)
   */
  @Override
  public void fillActionBars(IActionBars actionBars) {
    // Do nothing.
  }

  protected void fillContextMenu(IMenuManager menu, DynamicCreationAction action, String groupId) {
    // In this case, check really if the action is compatible with current selection.
    if (action.isSelectionCompatible()) {
      for (IContributionItem item : action.getStructuralDynamicActions()) {
        addContributionItem(menu, groupId, item);
      }

      // Add a separator between structural and non structural items.
      menu.add(new Separator());

      for (IContributionItem containedAction : action.getNonStructuralDynamicActions()) {
        addContributionItem(menu, groupId, containedAction);
      }

      // Add a separator between non structural and property value items.
      menu.add(new Separator());

      for (IContributionItem containedAction : action.getPropertyValueDynamicActions()) {
        addContributionItem(menu, groupId, containedAction);
      }

      // Add a separator between property value and extension items.
      menu.add(new Separator());

      for (IContributionItem containedAction : action.getExtensionDynamicActions()) {
        addContributionItem(menu, groupId, containedAction);
      }
    }
  }

  /**
   * Add given contribution item in specified menu.
   * 
   * @param menu
   * @param groupId
   * @param item
   */
  protected void addContributionItem(IMenuManager menu, String groupId, IContributionItem item) {
    // Append the action to a group if provided...
    if (null != groupId) {
      menu.appendToGroup(groupId, item);
    } else {
      menu.add(item);
    }
  }

  /**
   * Add given action in specified menu manager.
   * 
   * @param menu
   * @param groupId
   * @param action
   */
  protected void addAction(IMenuManager menu, String groupId, IAction action) {
    // Override the action contribution item to force the context menu to be
    // refreshed even if the selected object has not changed.
    addContributionItem(menu, groupId, new DynamicActionContributionItem(action));
  }
}
