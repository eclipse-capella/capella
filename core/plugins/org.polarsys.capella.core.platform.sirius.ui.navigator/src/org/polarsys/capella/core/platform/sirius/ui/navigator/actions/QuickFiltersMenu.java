/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.internal.navigator.NavigatorFilterService;
import org.eclipse.ui.navigator.ICommonFilterDescriptor;
import org.eclipse.ui.navigator.INavigatorContentService;
import org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPlugin;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;

public class QuickFiltersMenu extends MenuManager {
  CapellaCommonNavigator navigator;
  NavigatorFilterService capellaNavigatorFilterService;

  Action selectAllAction;
  Action deselectAllAction;
  Action selectDefaultAction;

  public QuickFiltersMenu(CapellaCommonNavigator navigator) {
    super(Messages.QuickFiltersMenu_Title);
    setImageDescriptor(CapellaNavigatorPlugin.getDefault().getImageDescriptor("QuickFiltersMenu.png"));

    this.navigator = navigator;
    INavigatorContentService capellaNavigatorContentService = navigator.getNavigatorContentService();
    capellaNavigatorFilterService = (NavigatorFilterService) capellaNavigatorContentService.getFilterService();

    addActions();
  }

  private void addActions() {
    // TODO: consider to create a dedicated class
    selectAllAction = new Action(Messages.QuickFiltersMenu_SelectAll_Title, IAction.AS_RADIO_BUTTON) {
      @Override
      public void run() {
        ICommonFilterDescriptor[] visibleFilterDescriptors = capellaNavigatorFilterService
            .getVisibleFilterDescriptors();

        String[] filterIDsToActivate = new String[visibleFilterDescriptors.length];
        for (int i = 0; i < visibleFilterDescriptors.length; i++) {
          filterIDsToActivate[i] = visibleFilterDescriptors[i].getId();
        }

        capellaNavigatorFilterService.activateFilterIdsAndUpdateViewer(filterIDsToActivate);
      }
    };

    this.add(selectAllAction);

    // TODO: consider to create a dedicated class
    deselectAllAction = new Action(Messages.QuickFiltersMenu_DeselectAll_Title, IAction.AS_RADIO_BUTTON) {
      @Override
      public void run() {
        capellaNavigatorFilterService.activateFilterIdsAndUpdateViewer(new String[0]);
      }
    };

    this.add(deselectAllAction);

    // TODO: consider to create a dedicated class
    selectDefaultAction = new Action(Messages.QuickFiltersMenu_SelectDefault_Title, IAction.AS_RADIO_BUTTON) {
      @Override
      public void run() {
        ICommonFilterDescriptor[] visibleFilterDescriptors = capellaNavigatorFilterService
            .getVisibleFilterDescriptors();

        List<String> defaultFilterIds = new ArrayList<>();
        for (int i = 0; i < visibleFilterDescriptors.length; i++) {
          if (visibleFilterDescriptors[i].isActiveByDefault()) {
            defaultFilterIds.add(visibleFilterDescriptors[i].getId());
          }
        }

        capellaNavigatorFilterService.activateFilterIdsAndUpdateViewer(defaultFilterIds.toArray(new String[] {}));
      }
    };

    this.add(selectDefaultAction);
  }

}
