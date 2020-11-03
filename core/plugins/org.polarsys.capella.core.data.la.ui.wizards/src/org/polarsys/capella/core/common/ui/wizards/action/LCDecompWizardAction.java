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

package org.polarsys.capella.core.common.ui.wizards.action;

import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IObjectActionDelegate;

import org.polarsys.capella.core.common.ui.wizards.LCDecompositionCommand;
import org.polarsys.capella.core.platform.sirius.ui.actions.AbstractPartToComponentAction;

/**
 * Edits the current selected Capella model element. It opens a Capella editor according to the current selected Capella model element.
 */
public class LCDecompWizardAction extends AbstractPartToComponentAction implements IObjectActionDelegate {
  /**
   * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
   */
  public void run(IAction action) {
    new LCDecompositionCommand(getSelectedElement()).run();
  }
}
