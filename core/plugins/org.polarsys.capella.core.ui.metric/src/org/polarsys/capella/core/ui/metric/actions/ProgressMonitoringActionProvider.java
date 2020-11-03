/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.metric.actions;


import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.SelectionHelper;
import org.polarsys.capella.core.ui.metric.MetricMessages;


public class ProgressMonitoringActionProvider extends CommonActionProvider {

  public final static String PROGRESS_MENU_ID = "org.polarsys.capella.core.ui.metric.menu"; //$NON-NLS-1$

  public ProgressMonitoringActionProvider() {
    // Do nothing
  }

  /**
   * Progress Monitoring Set action.
   */
  private ProgressMonitoringSetAction progressMonitoringSetAction;
  
  /**
   * Progress Monitoring Overview action.
   */
  private ProgressMonitoringOverviewAction progressMonitoringOverviewAction;

  /**
   * Metric action.
   */
  private MetricAction metricAction;
  
  /**
   * @see org.eclipse.ui.navigator.CommonActionProvider#init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
   */
  @Override
  public void init(ICommonActionExtensionSite site) {

    super.init(site);

    ISelectionProvider selectionProvider = site.getViewSite().getSelectionProvider();

    metricAction = new MetricAction();
    SelectionHelper.registerToSelectionChanges(metricAction, selectionProvider);

    progressMonitoringSetAction = new ProgressMonitoringSetAction();
    SelectionHelper.registerToSelectionChanges(progressMonitoringSetAction, selectionProvider);

    progressMonitoringOverviewAction = new ProgressMonitoringOverviewAction();
    SelectionHelper.registerToSelectionChanges(progressMonitoringOverviewAction, selectionProvider);
  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
   */
  @Override
  public void fillContextMenu(IMenuManager menu_p) {

    IMenuManager subMenuManager = new MenuManager(MetricMessages.progressMonitoring_menu_lbl, PROGRESS_MENU_ID);
    menu_p.appendToGroup(ICommonMenuConstants.GROUP_PROPERTIES, subMenuManager);

    subMenuManager.add(new ActionContributionItem(progressMonitoringSetAction));

    subMenuManager.add(new ActionContributionItem(progressMonitoringOverviewAction));

    subMenuManager.add(new ActionContributionItem(metricAction));

    // The sub menu itself
    subMenuManager.setVisible(metricAction.isEnabled()|| progressMonitoringSetAction.isEnabled() || progressMonitoringOverviewAction.isEnabled());
  }
  
  /**
   * @see org.eclipse.ui.actions.ActionGroup#dispose()
   */
  @Override
  public void dispose() {
    dispose(progressMonitoringSetAction);
    dispose(progressMonitoringOverviewAction);
    dispose(metricAction);

    progressMonitoringSetAction = null;
    progressMonitoringOverviewAction = null;
    metricAction = null;

    super.dispose();
  }

  /** For internal use */
  private void dispose(BaseSelectionListenerAction action) {
    if (null != action) {
      ISelectionProvider selectionProvider = getActionSite().getViewSite().getSelectionProvider();
      if(selectionProvider != null){
        selectionProvider.removeSelectionChangedListener(action);
      }
    }
  }
}
