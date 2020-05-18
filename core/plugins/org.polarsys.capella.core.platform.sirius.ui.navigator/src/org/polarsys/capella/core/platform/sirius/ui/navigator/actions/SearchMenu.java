/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.internal.navigator.NavigatorFilterService;
import org.eclipse.ui.navigator.INavigatorContentService;
import org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPlugin;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;

public class SearchMenu extends MenuManager {
  CapellaCommonNavigator navigator;
  NavigatorFilterService capellaNavigatorFilterService;
  
  SearchInDescriptionAction searchInDescriptionAction;
  SearchCaseSensitiveAction searchCaseSensitiveAction;

  public SearchMenu(CapellaCommonNavigator navigator) {
    super(Messages.SearchOptions_Menu_Title);

    setImageDescriptor(CapellaNavigatorPlugin.getDefault().getImageDescriptor("search.gif")); //$NON-NLS-1$
    this.navigator = navigator;
    INavigatorContentService capellaNavigatorContentService = navigator.getNavigatorContentService();
    capellaNavigatorFilterService = (NavigatorFilterService) capellaNavigatorContentService.getFilterService();

    addActions();
  }

  private void addActions() {
    searchInDescriptionAction = new SearchInDescriptionAction(navigator);
    this.add(searchInDescriptionAction);
    
    searchCaseSensitiveAction = new SearchCaseSensitiveAction(navigator);
    this.add(searchCaseSensitiveAction);

  }
  
  @Override
  public void dispose() {
    super.dispose();
    if (searchInDescriptionAction != null) {
      searchInDescriptionAction.dispose();
    }
    if (searchCaseSensitiveAction != null) {
      searchCaseSensitiveAction.dispose();
    }
  }
  
  public SearchInDescriptionAction getSearchInDescriptionAction() {
    return searchInDescriptionAction;
  }

}
