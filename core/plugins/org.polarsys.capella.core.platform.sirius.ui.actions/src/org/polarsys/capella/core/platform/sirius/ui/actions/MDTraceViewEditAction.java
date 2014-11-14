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
package org.polarsys.capella.core.platform.sirius.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IObjectActionDelegate;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.ui.actions.AbstractTigAction;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.commands.MDTraceViewEditCommand;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.commands.MDTraceViewListener;

public class MDTraceViewEditAction extends AbstractTigAction implements IObjectActionDelegate {

  private MDTraceViewEditCommand _command;

  /**
   * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
   */
  public void run(IAction action) {

    MDTraceViewListener listener = new MDTraceViewListener();
    MDEAdapterFactory.getEditingDomain().addResourceSetListener(listener);

    _command = new MDTraceViewEditCommand(getSelectedElement(), listener);
    getExecutionManager().execute(_command);

    MDEAdapterFactory.getEditingDomain().removeResourceSetListener(listener);
  }

  /**
   * for test purpose
   * @return the command
   */
  public MDTraceViewEditCommand getCommand() {
    return _command;
  }
}
