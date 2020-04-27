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

package org.polarsys.capella.core.platform.sirius.ui.navigator.actions;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.BaseSelectionListenerAction;

import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.platform.sirius.ui.project.NewModelWizard;

/**
 * The action allowing to create a new Capella model from a selected Capella project through a dedicated wizard.
 */
public class NewModelAction extends BaseSelectionListenerAction {
  // The workbench window.
  private IWorkbenchWindow _window;

  /**
   * Constructs the action allowing to create a new Capella model from a selected Capella project.
   * @param window_p The workbench window.
   */
  public NewModelAction(IWorkbenchWindow window_p) {
    super("New Model..."); //$NON-NLS-1$
    _window = window_p;
  }

  /**
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {
    NewModelWizard wizard = new NewModelWizard();
    wizard.init(_window.getWorkbench(), getStructuredSelection());
    WizardDialog dialog = new WizardDialog(_window.getShell(), wizard);
    dialog.open();
  }

  /**
   * @see org.eclipse.ui.actions.BaseSelectionListenerAction#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
   */
  @Override
  protected boolean updateSelection(IStructuredSelection selection_p) {
    boolean result = false;
    if (1 == selection_p.size()) {
      Object selectedElement = selection_p.getFirstElement();
      if (selectedElement instanceof Project) {
        result = true;
      }
    }
    return result;
  }
}
