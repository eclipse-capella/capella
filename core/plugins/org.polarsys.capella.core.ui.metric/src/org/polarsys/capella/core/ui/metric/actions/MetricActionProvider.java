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
package org.polarsys.capella.core.ui.metric.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.SelectionHelper;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

/**
 * Add Metric actions to the Capella Explorer.
 */  
@Deprecated
public class MetricActionProvider extends CommonActionProvider {

  /**
   * Metric action.
   */
  private MetricAction _metricAction;

  /**
   * @see org.eclipse.ui.actions.ActionGroup#dispose()
   */
  @Override
  public void dispose() {
    ISelectionProvider selectionProvider = getActionSite().getViewSite().getSelectionProvider();
    if (null != _metricAction) {
      selectionProvider.removeSelectionChangedListener(_metricAction);
      _metricAction = null;
    }
    super.dispose();
  }
  
  /**
   * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
   */
  @Override
  public void fillContextMenu(IMenuManager menu_p) {
    menu_p.appendToGroup(ICommonMenuConstants.GROUP_ADDITIONS, _metricAction);
    
    //In the case of aird, check is the corresponding Session exist and is open
    // In order to enable/disable this menu contribution
    Object object = _metricAction.getStructuredSelection().getFirstElement();

    if (null != object && object instanceof IFile) {
      
      boolean enable = false;
      
      Session session = SessionHelper.getSession((IFile) object);
      
      if (null != session) {
        DAnalysisSession daSession = (DAnalysisSession) session;
        enable = ( null != daSession && daSession.isOpen() );
      }
      
      _metricAction.setEnabled(enable);
    }
    
    return;
  }

  /**
   * @see org.eclipse.ui.navigator.CommonActionProvider#init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
   */
  @Override
  public void init(ICommonActionExtensionSite site_p) {
    super.init(site_p);
    ISelectionProvider selectionProvider = site_p.getViewSite().getSelectionProvider();
    _metricAction = new MetricAction();
    SelectionHelper.registerToSelectionChanges(_metricAction, selectionProvider);
  }
}
