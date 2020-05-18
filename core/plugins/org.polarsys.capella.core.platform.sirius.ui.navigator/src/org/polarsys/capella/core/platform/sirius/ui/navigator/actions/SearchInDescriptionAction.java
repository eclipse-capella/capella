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

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IPropertyListener;
import org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPlugin;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;

public class SearchInDescriptionAction extends Action implements IPropertyListener {

  CapellaCommonNavigator navigator;

  public SearchInDescriptionAction(CapellaCommonNavigator navigator) {
    super(Messages.SearchOptions_InDesc_Title);
    setToolTipText(Messages.SearchOptions_InDesc_Tooltip);
    setImageDescriptor(CapellaNavigatorPlugin.getDefault().getImageDescriptor("SearchInDescription.gif"));
    
    this.navigator = navigator;
    setChecked(navigator.isSearchInDescriptionEnabled());
    navigator.addPropertyListener(this);
  }

  @Override
  public void run() {
    navigator.setSearchInDescriptionEnabled(!navigator.isSearchInDescriptionEnabled());
  }

  @Override
  public void propertyChanged(Object source, int propId) {
    if (CapellaCommonNavigator.IS_SEARCH_IN_DESCRIPTION_ENABLED_PROPERTY == propId) {
      setChecked(navigator.isSearchInDescriptionEnabled());
      navigator.refreshViewer();
    }
  }

  public void dispose() {
    navigator.removePropertyListener(this);
  }
}
