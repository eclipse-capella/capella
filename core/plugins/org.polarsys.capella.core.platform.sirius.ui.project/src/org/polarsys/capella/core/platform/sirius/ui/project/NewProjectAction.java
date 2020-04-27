/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.platform.sirius.ui.project;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.PlatformUI;

/**
 * The new project action.
 */
public class NewProjectAction implements IViewActionDelegate {
  // The action site.
  private IWorkbenchSite _site;

  /**
   * Constructs the new project action.
   */
  public NewProjectAction() {
    // Do nothing.
  }

  /**
   * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
   */
  public void init(IViewPart view_p) {
    _site = view_p.getViewSite();
  }

  /**
   * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
   */
  public void run(IAction action_p) {
    NewProjectWizard wizard = new NewProjectWizard();
    wizard.init(PlatformUI.getWorkbench(), StructuredSelection.EMPTY);
    WizardDialog dialog = new WizardDialog(_site.getShell(), wizard);
    dialog.open();
  }

  /**
   * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
   */
  public void selectionChanged(IAction action_p, ISelection selection_p) {
    // Do nothing.
  }

  /**
   * Set site.
   * @param site_p the site to set
   */
  public void setSite(IWorkbenchSite site_p) {
    _site = site_p;
  }
}
