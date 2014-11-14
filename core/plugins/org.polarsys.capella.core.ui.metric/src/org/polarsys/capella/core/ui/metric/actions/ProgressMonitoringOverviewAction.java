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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
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
}
