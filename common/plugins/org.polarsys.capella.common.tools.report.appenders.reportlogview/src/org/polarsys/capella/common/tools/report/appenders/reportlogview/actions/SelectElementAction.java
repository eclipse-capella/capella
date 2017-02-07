/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.tools.report.appenders.reportlogview.actions;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.ui.services.helper.EObjectImageProviderHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;

/**
 *
 */
public class SelectElementAction extends Action {
	
   private Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);
   
  /**
   * View id of project explorer
   */
  static final String __EXPLORER_VIEW_ID = "capella.project.explorer"; //$NON-NLS-1$

  EObject eObject;

  /**
   * @param eObject
   */
  public SelectElementAction(EObject eObject) {
    this.eObject = eObject;
  }

  @Override
  public ImageDescriptor getImageDescriptor() {
    return ImageDescriptor.createFromImage(EObjectImageProviderHelper.getImage(eObject));
  }

  @Override
  public String getText() {
    return EObjectLabelProviderHelper.getText(eObject);
  }

  /**
   * @see org.eclipse.jface.action.IAction#run()
   */
  @Override
  public void run() {
    selectElementInCapellaExplorer(eObject);
  }
  
  protected Object getFirstSelectedElement(ISelection selection) {
	    if (selection.isEmpty() || !(selection instanceof IStructuredSelection)) {
	      return null;
	    }
	    IStructuredSelection structuredSelection = (IStructuredSelection) selection;
	    return structuredSelection.getFirstElement();
  }

  /**
   * @param elem
   */
  public void selectElementInCapellaExplorer(EObject elem) {
    if (null != elem) {
      IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
      ISelection newSelection = new StructuredSelection(elem);
      Object firstElement = getFirstSelectedElement(newSelection);
     
      try {
    	  IViewPart explorerView = activePage.showView(__EXPLORER_VIEW_ID);
          if(firstElement instanceof DRepresentation){
        	  explorerView.getViewSite().getSelectionProvider().setSelection(
        			  new StructuredSelection(RepresentationHelper.getRepresentationDescriptor((DRepresentation)firstElement)));
          } else {
        	  // when it doesn't concern DRepresentations
        	  explorerView.getViewSite().getSelectionProvider().setSelection(newSelection);
          }   
      } catch (PartInitException exception) {
    	__logger.warn(new EmbeddedMessage(exception.getMessage(), IReportManagerDefaultComponents.UI), exception);
      } 
    }
  }
}
