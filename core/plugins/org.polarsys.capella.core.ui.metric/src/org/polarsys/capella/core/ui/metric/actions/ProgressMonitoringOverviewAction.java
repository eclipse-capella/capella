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
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.BaseSelectionListenerAction;

import org.polarsys.capella.core.ui.metric.IImageKeys;
import org.polarsys.capella.core.ui.metric.MetricActivator;
import org.polarsys.capella.core.ui.metric.MetricMessages;
import org.polarsys.capella.core.ui.metric.dialog.ProgressMonitoringOverviewDialog;
import org.polarsys.capella.core.ui.metric.utils.ProgressMonitoringPropagator;

public class ProgressMonitoringOverviewAction extends BaseSelectionListenerAction {
	
  /**  the root semantic object selected*/
	private EObject _rootSemanticObject;
	
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
	  
	  _rootSemanticObject = getRootSemanticObject();
    
	  ProgressMonitoringOverviewDialog dialog= new ProgressMonitoringOverviewDialog(
      PlatformUI.getWorkbench().getDisplay().getActiveShell(),
      MetricMessages.progressMonitoring_dialog_title,
      MetricMessages.progressMonitoring_dialog_msg,
      _rootSemanticObject,
      ProgressMonitoringPropagator.getInstance().getTaggedObjects(_rootSemanticObject)
	  );

	  dialog.open();
    
    // clean everything to avoid any memory leaks.
    _rootSemanticObject = null;
	}
	
  /**
   * get the Root semantic {@link EObject} corresponding to the
   * current selection
   * @return <code>null</code> whether selection does not fit any supported case .
   */
  protected EObject getRootSemanticObject() {
    
    EObject result = null;
    Object selectedObject = getStructuredSelection().getFirstElement();
    
    try {
      if (selectedObject instanceof IFile) {

        IFile file = (IFile) selectedObject;
        URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
        Session session = SessionManager.INSTANCE.getSession(uri);
        if (null != session && session.isOpen()) { // Session is open
          DAnalysis da = null;
          Resource resource = session.getSessionResource();
          da = (DAnalysis) resource.getContents().get(0);
          result = da.getModels().get(0);
        }
      } else if (selectedObject instanceof EObject) {
        result = (EObject) selectedObject;
      } else {
        result = null;
      }
    } catch (Exception exception_p) { // Old models raise up exception
      result = null;      
    }
    
    return result;
  }
}
