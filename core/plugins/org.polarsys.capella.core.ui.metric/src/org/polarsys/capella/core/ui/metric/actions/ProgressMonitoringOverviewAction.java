/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;
import org.polarsys.capella.core.ui.metric.IImageKeys;
import org.polarsys.capella.core.ui.metric.MetricActivator;
import org.polarsys.capella.core.ui.metric.MetricMessages;
import org.polarsys.capella.core.ui.metric.dialog.ProgressMonitoringOverviewDialog;
import org.polarsys.capella.core.ui.metric.utils.ProgressMonitoringPropagator;

public class ProgressMonitoringOverviewAction extends BaseSelectionListenerAction {
  /**
   * Constructor for Action1.
   */
  public ProgressMonitoringOverviewAction() {
    super(MetricMessages.progressMonitoring_overviewAction_lbl);
    setImageDescriptor(MetricActivator.getDefault().getImageDescriptor(IImageKeys.IMG_METRICS));
  }

  /**
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {
    EObject rootSemanticObject = ProgressMonitoringActionsHelper.getSelectedEObject(getStructuredSelection());
    if (null == rootSemanticObject) {
      return;
    }

    ProgressMonitoringOverviewDialog dialog =
        new ProgressMonitoringOverviewDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), MetricMessages.progressMonitoring_dialog_title,
            MetricMessages.progressMonitoring_dialog_msg, rootSemanticObject, ProgressMonitoringPropagator.getInstance().getTaggedObjects(rootSemanticObject));

    dialog.open();
  }
  
  @Override
  protected boolean updateSelection(IStructuredSelection selection) {
    boolean showProgressActions = false;
    if(selection != null && selection.size() == 1){
      Object selectedObj = selection.getFirstElement();
      if (selectedObj instanceof IFile) {
        Session session = SessionHelper.getSession((IFile) selectedObj);
        if ((null != session) && session.isOpen()) {
          Resource resource = session.getSessionResource();
          
          // TODO : Analyze if the method returning only one object is enough in the case of fragmented model.
          DAnalysis da = (DAnalysis) EcoreUtil.getObjectByType(resource.getContents(), ViewpointPackage.Literals.DANALYSIS);
          Collection<EObject> models = new ArrayList<EObject>();
          if (da != null) {
            models.addAll(da.getModels());
            for (DAnalysis refDa : da.getReferencedAnalysis()) {
              models.addAll(refDa.getModels());
            }
          }
          for (EObject model : models) {
            showProgressActions = showProgressActions || ProgressMonitoringPropagator.getInstance().isEnumerationPropertyTypeDefinedForProject(model);
          }
        }
      } else if(selectedObj instanceof EObject){
        return ProgressMonitoringPropagator.getInstance().isEnumerationPropertyTypeDefinedForProject((EObject) selectedObj);
      }
    }
    return false;
  }
}
