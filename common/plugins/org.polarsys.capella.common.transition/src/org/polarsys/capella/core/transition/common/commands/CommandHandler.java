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

package org.polarsys.capella.core.transition.common.commands;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.helpers.operations.LongRunningListenersRegistry;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;

public abstract class CommandHandler extends AbstractHandler {

  protected Collection<?> getInitialSelection(Object evaluationContext) {
    IEvaluationContext context = (IEvaluationContext) evaluationContext;
    return CapellaAdapterHelper.resolveSemanticObjects((Collection<?>) context.getDefaultVariable());
  }
  
  /**
   * Use method in CapellaAdapterHelper instead
   */
  @Deprecated
  public Collection<EObject> getSemanticObjects(Collection<?> elements) {
    return CapellaAdapterHelper.resolveSemanticObjects(elements);
  }

  /**
   * Use method in CapellaAdapterHelper instead
   */
  @Deprecated
  public EObject resolveSemanticObject(Object object) {
    return CapellaAdapterHelper.resolveBusinessObject(object);
  }

  protected abstract ICommand createCommand(Collection<?> selection, IProgressMonitor progressMonitor);

  public Object execute(final ExecutionEvent event) throws ExecutionException {
    try {
      return execute(getSelection(event), event.getCommand().getDescription());
    } catch (NotDefinedException ex) {
      return execute(getSelection(event));
    }
  }

  public Object execute(Collection<?> selection) throws ExecutionException {
    return execute(selection, ICommonConstants.EMPTY_STRING);
  }

  public Object execute(Collection<?> selection, String name) throws ExecutionException {
    Collection<? extends EObject> selectedSemanticObjects = CapellaAdapterHelper.resolveSemanticObjects(selection);
    
    if (!selectedSemanticObjects.isEmpty()) {
      ExecutionManager executionManager = TransactionHelper.getExecutionManager(selectedSemanticObjects);
      
      if (executionManager != null) {
        try {
          LongRunningListenersRegistry.getInstance().operationStarting(getClass());
          ICommand cmd = createCommand(selection, new NullProgressMonitor());
          if (cmd instanceof LauncherCommand) {
            ((LauncherCommand) cmd).setName(name);
          }
          executionManager.execute(cmd);
        } finally {
          LongRunningListenersRegistry.getInstance().operationEnded(getClass());
        }
      }
    }
    return null;
  }

  protected Collection<?> getSelection(ExecutionEvent event) {
    IEvaluationContext context = (IEvaluationContext) event.getApplicationContext();
    Object selection = context.getDefaultVariable();
    if (selection instanceof Collection) {
      return (Collection<?>) selection;
    }
    return Collections.emptyList();
  }
}
