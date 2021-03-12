/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.handlers;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;

public class DeleteHandler extends AbstractHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {

    IEvaluationContext context = (IEvaluationContext) event.getApplicationContext();
    ISelection selection = (ISelection) context.getVariable("selection");

    if (selection instanceof IStructuredSelection) {
      List<?> elementsToRemove = ((IStructuredSelection) selection).toList();
      Collection<EObject> eObjectsToRemove = CapellaAdapterHelper.resolveDescriptorsOrBusinessObjects(elementsToRemove);
      if (!eObjectsToRemove.isEmpty()) {

        ExecutionManager executionManager = TransactionHelper.getExecutionManager(eObjectsToRemove);
        CapellaDeleteCommand deleteCommand = new CapellaDeleteCommand(executionManager, eObjectsToRemove, true,
            withConfirmDeletion(), true);
        deleteCommand.setPreventProtectedElementsDeletion(true);

        if (deleteCommand.canExecute()) {
          deleteCommand.execute();
        }
      }
    }
    return null;
  }

  protected boolean withConfirmDeletion() {
    return true;
  }
}
