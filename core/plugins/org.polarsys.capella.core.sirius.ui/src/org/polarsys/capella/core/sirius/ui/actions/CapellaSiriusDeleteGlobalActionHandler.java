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
package org.polarsys.capella.core.sirius.ui.actions;

import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionContext;
import org.eclipse.sirius.diagram.tools.internal.delete.SiriusDeleteGlobalActionHandler;
import org.polarsys.capella.core.sirius.ui.SiriusUIPlugin;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;

/**
 * Attaches an undo context to the command provided by SiriusDeleteGlobalActionHandler.
 */
public class CapellaSiriusDeleteGlobalActionHandler extends SiriusDeleteGlobalActionHandler {

  
  @Override
  public ICommand getCommand(IGlobalActionContext cntxt) {
    ICommand command = super.getCommand(cntxt);
    if (command != null){
      IUndoContext undoContext = (IUndoContext) cntxt.getActivePart().getAdapter(IUndoContext.class);
      if (undoContext != null){      
        command.addContext(undoContext);
      } else {
        SiriusUIPlugin.getDefault().log(IStatus.ERROR, "No undo context delete action. Flushing history to prevent corruption.", null); //$NON-NLS-1$
        MDEAdapterFactory.getEditingDomain().getCommandStack().flush();
      }
    }
    return command;
  }
}
