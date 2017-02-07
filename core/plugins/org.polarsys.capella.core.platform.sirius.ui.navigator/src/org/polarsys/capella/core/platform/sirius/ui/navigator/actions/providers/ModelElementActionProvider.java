/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions.providers;

import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerSite;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.ui.services.helper.EObjectImageProviderHelper;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPlugin;
import org.polarsys.capella.core.platform.sirius.ui.navigator.IImageKeys;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.ImpactAnalysisAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.NavigateAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.SelectionHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.ShowInDiagramAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.internal.navigate.NavigationAdvisor;

/**
 * Action Provider for {@link ModelElement}.
 */
public class ModelElementActionProvider extends CommonActionProvider {

  /**
   * Show impact analysis tool.
   */
  private ImpactAnalysisAction _impactAnalysisAction;

  /**
   * Show in diagram action.
   */
  private ShowInDiagramAction _showInDiagramAction;

  /**
   * Add a dynamic action in a menu manager.
   * @param menu_p
   * @param groupId_p
   * @param action_p
   */
  protected void addAction(IMenuManager menu_p, String groupId_p, IAction action_p) {
    // Override the action contribution item to force the context menu to be
    // refreshed even if the selected object has not changed.
    ActionContributionItem item = new ActionContributionItem(action_p) {
      @Override
      public boolean isDirty() {
        return true;
      }

      @Override
      public boolean isDynamic() {
        return true;
      }
    };
    // Append the action to a group if provided...
    if (null != groupId_p) {
      menu_p.appendToGroup(groupId_p, item);
    } else {
      menu_p.add(item);
    }
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#dispose()
   */
  @Override
  public void dispose() {
    ISelectionProvider selectionProvider = getActionSite().getViewSite().getSelectionProvider();
    if (null != _showInDiagramAction) {
      selectionProvider.removeSelectionChangedListener(_showInDiagramAction);
      _showInDiagramAction = null;
    }
    if (null != _impactAnalysisAction) {
      selectionProvider.removeSelectionChangedListener(_impactAnalysisAction);
      _impactAnalysisAction = null;
    }
    super.dispose();
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
   */
  @Override
  public void fillContextMenu(IMenuManager menu_p) {
    // Fill with show in diagram.
    menu_p.appendToGroup(ICommonMenuConstants.GROUP_SHOW, _showInDiagramAction);

    // Fill a new Sub Menu Manger allowing to navigate to related elements against the selected one.
    // Fill actions located in GROUP_GOTO.
    IMenuManager subMenuManager = new MenuManager(Messages.ModelElementActionProvider_Goto_Menu_Title, "Dynamic.Goto") { //$NON-NLS-1$

          /**
           * {@inheritDoc}
           */
          @Override
          public void fill(Menu parent_p, int index_p) {
            super.fill(parent_p, index_p);
            // That's a pity but I don't find another way to provide the icon.
            for (MenuItem item : parent_p.getItems()) {
              if (Messages.ModelElementActionProvider_Goto_Menu_Title.equals(item.getText())) {
                item.setImage(CapellaNavigatorPlugin.getDefault().getImage(IImageKeys.IMG_SHOW_IN_CAPELLA_EXPLORER));
                // Item found stop searching...
                break;
              }
            }
          }

        };
    fillGotoMenuManager(subMenuManager);
    menu_p.insertAfter(ICommonMenuConstants.GROUP_GOTO, subMenuManager);

    // Fill with show impact analysis tool.
    menu_p.insertAfter("group.accelerator", _impactAnalysisAction); //$NON-NLS-1$
  }

  /**
   * Fill Goto Menu Manager.
   * @param menu_p
   */
  protected void fillGotoMenuManager(IMenuManager menu_p) {
    // In this case, check really if the action is compatible with current selection.
    IStructuredSelection selection = (IStructuredSelection) getActionSite().getViewSite().getSelectionProvider().getSelection();
    if (CapellaResourceHelper.isSemanticElements(selection.toList())) {

      EObject element = (EObject) selection.getFirstElement();
      Set<EObject> navigableElements = NavigationAdvisor.getInstance().getNavigableElements(element);
      if (!navigableElements.isEmpty()) {
        StructuredViewer viewer = getActionSite().getStructuredViewer();
        for (EObject currentModelElement : navigableElements) {
          // Create a navigate action that enables this navigation.
          NavigateAction action = new NavigateAction(currentModelElement, viewer);
          action.setText(EObjectLabelProviderHelper.getText(currentModelElement));
          action.setImageDescriptor(ImageDescriptor.createFromImage(EObjectImageProviderHelper.getImage(currentModelElement)));
          // Add this action in given menu manager.
          addAction(menu_p, null, action);
        }
      }
    }

  }

  /**
   * @see org.eclipse.ui.navigator.CommonActionProvider#init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
   */
  @Override
  public void init(ICommonActionExtensionSite site_p) {
    super.init(site_p);
    ICommonViewerSite commonViewSite = site_p.getViewSite();
    // Show in diagram.
    _showInDiagramAction = new ShowInDiagramAction();
    _showInDiagramAction.setImageDescriptor(CapellaNavigatorPlugin.getDefault().getImageDescriptor(IImageKeys.IMG_SHOW_IN_DIAGRAM));
    SelectionHelper.registerToSelectionChanges(_showInDiagramAction, commonViewSite.getSelectionProvider());
    // Show impact analysis tool.
    _impactAnalysisAction = new ImpactAnalysisAction();
    _impactAnalysisAction.setImageDescriptor(CapellaNavigatorPlugin.getDefault().getImageDescriptor(IImageKeys.IMG_ENABLED_IMPACT_ANALYSIS));
    _impactAnalysisAction.setDisabledImageDescriptor(CapellaNavigatorPlugin.getDefault().getImageDescriptor(IImageKeys.IMG_DISABLED_IMPACT_ANALYSIS));
    SelectionHelper.registerToSelectionChanges(_impactAnalysisAction, commonViewSite.getSelectionProvider());
  }
}
