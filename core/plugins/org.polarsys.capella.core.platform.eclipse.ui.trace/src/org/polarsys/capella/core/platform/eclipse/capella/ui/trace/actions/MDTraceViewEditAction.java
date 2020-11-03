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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.actions;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IObjectActionDelegate;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.ui.actions.AbstractTigAction;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.commands.MDTraceViewEditCommand;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.commands.MDTraceViewListener;

public class MDTraceViewEditAction extends AbstractTigAction implements IObjectActionDelegate {

  private MDTraceViewEditCommand _command;

  /**
   * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
   */
  public void run(IAction action) {
	TransactionalEditingDomain domain = TransactionHelper.getEditingDomain(getSelectedElement());
    MDTraceViewListener listener = new MDTraceViewListener();
    domain.addResourceSetListener(listener);

    _command = new MDTraceViewEditCommand(getSelectedElement(), listener);
    getExecutionManager().execute(_command);

    domain.removeResourceSetListener(listener);
  }

  /**
   * for test purpose
   * @return the command
   */
  public MDTraceViewEditCommand getCommand() {
    return _command;
  }
}
