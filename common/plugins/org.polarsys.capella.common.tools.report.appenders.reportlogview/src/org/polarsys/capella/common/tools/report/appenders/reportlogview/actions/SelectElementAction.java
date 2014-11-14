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
package org.polarsys.capella.common.tools.report.appenders.reportlogview.actions;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;

/**
 *
 */
public class SelectElementAction extends Action {

  /**
   * View id of project explorer
   */
  static final String __EXPLORER_VIEW_ID = "capella.project.explorer"; //$NON-NLS-1$

  EObject eObject;

  /**
   * @param contentProvider_p
   */
  public SelectElementAction(EObject eObject_p) {
    eObject = eObject_p;
  }

  @Override
  public ImageDescriptor getImageDescriptor() {
    return ImageDescriptor.createFromImage(EObjectLabelProviderHelper.getImage(eObject));
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
    selectInExplorer(eObject);
  }

  /**
   * @param elem_p
   */
  void selectInExplorer(EObject elem_p) {
    if (null != elem_p) {
      IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
      try {
        IViewPart explorerView = activePage.showView(__EXPLORER_VIEW_ID);
        ISelection newSelection = new StructuredSelection(elem_p);
        explorerView.getViewSite().getSelectionProvider().setSelection(newSelection);
      } catch (PartInitException exception_p) {
        // nothing
      }
    }
  }
}
