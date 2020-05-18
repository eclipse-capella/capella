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

package org.polarsys.capella.core.platform.sirius.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IActionDelegate;

import org.polarsys.capella.common.ui.actions.AbstractTigAction;

/**
 */

// XXX : to be deleted ... remplaced by a new validation menu
public class CheckAction extends AbstractTigAction implements IActionDelegate {
  /**
   * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
   */
  public void run(IAction arg0) {
    // Create the validate action.
    CapellaValidateAction capellaValidateAction = new CapellaValidateAction();
    // Update its selection.
    capellaValidateAction.updateSelection(new StructuredSelection(getSelectedElement()));
    // Then run it.
    // The action handles by itself the call to the execution manager.
    capellaValidateAction.run();
  }
}
