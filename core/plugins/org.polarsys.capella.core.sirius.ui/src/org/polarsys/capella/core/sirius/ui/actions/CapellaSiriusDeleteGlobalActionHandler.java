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
package org.polarsys.capella.core.sirius.ui.actions;

import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionContext;
import org.eclipse.sirius.diagram.ui.tools.internal.delete.SiriusDeleteGlobalActionHandler;
import org.eclipse.ui.IWorkbenchPart;
import org.polarsys.capella.core.sirius.ui.SiriusUIPlugin;

/**
 * Attaches an undo context to the command provided by SiriusDeleteGlobalActionHandler.
 */
public class CapellaSiriusDeleteGlobalActionHandler extends SiriusDeleteGlobalActionHandler {

  /**
   * @see org.eclipse.sirius.diagram.ui.tools.internal.delete.SiriusDeleteGlobalActionHandler#getCommand(IGlobalActionContext)
   */
  @Override
  public ICommand getCommand(IGlobalActionContext cntxt) {
    ICommand command = super.getCommand(cntxt);
    if (command != null) {
      IUndoContext undoContext = (IUndoContext) cntxt.getActivePart().getAdapter(IUndoContext.class);
      if (undoContext != null) {
        command.addContext(undoContext);
      } else {
        Platform.getLog(SiriusUIPlugin.class).log(Status.error("No undo context delete action. Flushing history to prevent corruption.", null)); //$NON-NLS-1$
        getEditingDomain(cntxt).getCommandStack().flush();
      }
    }
    return command;
  }

  /**
   * @see org.eclipse.sirius.diagram.ui.tools.internal.delete.SiriusDeleteGlobalActionHandler#getEditingDomain(IGlobalActionContext)
   * 
   * This method was duplicated (without any change) for visibility reason
   */
  private TransactionalEditingDomain getEditingDomain(IGlobalActionContext cntxt) {
    IWorkbenchPart part = cntxt.getActivePart();
    TransactionalEditingDomain result = null;

    IEditingDomainProvider provider = (IEditingDomainProvider) part.getAdapter(IEditingDomainProvider.class);
    if (provider != null) {
      EditingDomain domain = provider.getEditingDomain();
      if (domain instanceof TransactionalEditingDomain) {
        result = (TransactionalEditingDomain) domain;
      }
    }

    return result;
  }
}
