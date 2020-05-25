/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerSite;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.OpenPropertiesAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.SelectionHelper;

/**
 * Action Provider for {@link ModelElement}.
 */
public class ModelElementActionProvider extends CommonActionProvider {


  private OpenPropertiesAction _openPropertiesAction;


  /**
   * @see org.eclipse.ui.actions.ActionGroup#dispose()
   */
  @Override
  public void dispose() {
    super.dispose();
    
    if (null != _openPropertiesAction) {
      ISelectionProvider selectionProvider = getActionSite().getViewSite().getSelectionProvider();
      selectionProvider.removeSelectionChangedListener(_openPropertiesAction);
      _openPropertiesAction = null;
    }
  }

  @Override
  public void fillActionBars(IActionBars actionBars) {
    super.fillActionBars(actionBars);
    actionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, _openPropertiesAction);
    actionBars.setGlobalActionHandler(ActionFactory.PROPERTIES.getId(), _openPropertiesAction);
  }
  /**
   * @see org.eclipse.ui.navigator.CommonActionProvider#init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
   */
  @Override
  public void init(ICommonActionExtensionSite site_p) {
    super.init(site_p);
    
    ICommonViewerSite commonViewSite = site_p.getViewSite();
    _openPropertiesAction = new OpenPropertiesAction();
    SelectionHelper.registerToSelectionChanges(_openPropertiesAction, commonViewSite.getSelectionProvider());

  }
}
