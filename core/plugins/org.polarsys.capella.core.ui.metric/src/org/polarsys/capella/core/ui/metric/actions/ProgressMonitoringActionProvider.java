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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.SelectionHelper;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;
import org.polarsys.capella.core.ui.metric.MetricMessages;
import org.polarsys.capella.core.ui.metric.utils.ProgressMonitoringPropagator;

public class ProgressMonitoringActionProvider extends CommonActionProvider {

  public final static String PROGRESS_MENU_ID = "org.polarsys.capella.core.ui.metric.menu"; //$NON-NLS-1$

  public ProgressMonitoringActionProvider() {
    // Do nothing
  }

  /**
   * Progress Monitoring Set action.
   */
  private ProgressMonitoringSetAction _progressMonitoringSetAction;
  /**
   * Progress Monitoring Overview action.
   */
  private ProgressMonitoringOverviewAction _progressMonitoringOverviewAction;

  /**
   * Metric action.
   */
  private MetricAction _metricAction;

  /**
   * @see org.eclipse.ui.actions.ActionGroup#dispose()
   */
  @Override
  public void dispose() {
    dispose(_progressMonitoringSetAction);
    dispose(_progressMonitoringOverviewAction);
    dispose(_metricAction);

    _progressMonitoringSetAction = null;
    _progressMonitoringOverviewAction = null;
    _metricAction = null;

    super.dispose();

    return;
  }

  /** for internal use */
  private void dispose(BaseSelectionListenerAction action_p) {
    ISelectionProvider selectionProvider = getActionSite().getViewSite().getSelectionProvider();
    if (null != action_p) {
      selectionProvider.removeSelectionChangedListener(action_p);
    }

  }

  /**
   * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
   */
  @Override
  public void fillContextMenu(IMenuManager menu_p) {

    IMenuManager subMenuManager = new MenuManager(MetricMessages.progressMonitoring_menu_lbl, PROGRESS_MENU_ID);
    menu_p.appendToGroup(ICommonMenuConstants.GROUP_PROPERTIES, subMenuManager);

    subMenuManager.add(new ActionContributionItem(_progressMonitoringSetAction));

    subMenuManager.add(new ActionContributionItem(_progressMonitoringOverviewAction));

    subMenuManager.add(new ActionContributionItem(_metricAction));

    Object object = _progressMonitoringSetAction.getStructuredSelection().getFirstElement();

    // FIXME TO FACTORIZE...

    //
    // METRICS action
    //
    boolean showMetric = false;

    if ((null != object) && (object instanceof IFile)) {

      Session session = SessionHelper.getSession((IFile) object);

      if (null != session) {
        DAnalysisSession daSession = (DAnalysisSession) session;
        showMetric = ((null != daSession) && daSession.isOpen());
      }

    } else if ((null != object) && (object instanceof EObject)) {
      EObject eObject = (EObject) object;
      EClass eclass = eObject.eClass();

      if (CapellamodellerPackage.Literals.SYSTEM_ENGINEERING.isSuperTypeOf(eclass) || CsPackage.Literals.BLOCK_ARCHITECTURE.isSuperTypeOf(eclass)) {
        showMetric = true;
      }

    }

    _metricAction.setEnabled(showMetric);

    //
    // PROGRESS action
    //
    boolean showProgressActions = false;

    if ((null != object) && (object instanceof IFile)) {

      Session session = SessionHelper.getSession((IFile) object);

      if ((null != session) && session.isOpen()) {
        Resource resource = session.getSessionResource();

        // TODO : Analyze if the method returning only one object is enough in the case of fragmented model.
        DAnalysis da = (DAnalysis) EcoreUtil.getObjectByType(resource.getContents(), ViewpointPackage.Literals.DANALYSIS);
        Collection<EObject> models = new ArrayList<EObject>();
        models.addAll(da.getModels());
        for (DAnalysis refDa : da.getReferencedAnalysis()) {
          models.addAll(refDa.getModels());
        }

        for (EObject model : models) {
          showProgressActions = showProgressActions || ProgressMonitoringPropagator.getInstance().isEnumerationPropertyTypeDefinedForProject(model);
        }
      }
    } else {
      showProgressActions = (null != object) && ProgressMonitoringPropagator.getInstance().isEnumerationPropertyTypeDefinedForProject((EObject) object);
    }

    _progressMonitoringSetAction.setEnabled(showProgressActions);
    _progressMonitoringOverviewAction.setEnabled(showProgressActions);

    // The sub menu itself

    subMenuManager.setVisible(showMetric || showProgressActions);

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

    _progressMonitoringSetAction = new ProgressMonitoringSetAction();
    SelectionHelper.registerToSelectionChanges(_progressMonitoringSetAction, selectionProvider);

    _progressMonitoringOverviewAction = new ProgressMonitoringOverviewAction();
    SelectionHelper.registerToSelectionChanges(_progressMonitoringOverviewAction, selectionProvider);

    return;
  }

}
