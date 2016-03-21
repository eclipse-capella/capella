/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.re.handlers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.helpers.operations.LongRunningListenersRegistry;
import org.polarsys.capella.core.transition.common.commands.TransitionCommand;

/**
 *
 */
public abstract class AbstractReHandler extends AbstractHandler {

  protected Collection<Object> getInitialSelection(Object evaluationContext) {
    IEvaluationContext context = (IEvaluationContext) evaluationContext;
    return getSemanticObjects((Collection<Object>) context.getDefaultVariable());
  }

  public Collection<Object> getSemanticObjects(Collection<Object> elements) {
    Collection<Object> result = new ArrayList<Object>();
    for (Object object : elements) {
      Object semantic = resolveSemanticObject(object);
      if (semantic != null) {
        result.add(semantic);
      }
    }
    return result;
  }

  public Object resolveSemanticObject(Object object) {
    Object semantic = null;

    if (object != null) {
      if (object instanceof EObject) {
        semantic = object;

      } else if (object instanceof IAdaptable) {
        Object adapter = ((IAdaptable) object).getAdapter(EObject.class);
        if (adapter instanceof EObject) {
          semantic = adapter;
        }
      }
    }
    return semantic;
  }

  protected abstract ICommand createCommand(Collection<Object> selection, IProgressMonitor progressMonitor);

  /**
   * {@inheritDoc}
   */
  public Object execute(final ExecutionEvent event) throws ExecutionException {
    try {
      LongRunningListenersRegistry.getInstance().operationStarting(getClass());
      ICommand cmd = createCommand(getSelection(event), new NullProgressMonitor());
      if (cmd instanceof TransitionCommand) {
        try {
          ((TransitionCommand) cmd).setName(event.getCommand().getDescription());
        } catch (NotDefinedException ex) {
          // silent exception
        }
      }
      TransactionHelper.getExecutionManager((Collection<? extends EObject>) getSemanticObjects(getSelection(event))).execute(cmd);
    } finally {
      LongRunningListenersRegistry.getInstance().operationEnded(getClass());
    }

    return null;
  }

  protected Collection<Object> getSelection(ExecutionEvent event) {
    IEvaluationContext context = (IEvaluationContext) event.getApplicationContext();
    Object ae = context.getDefaultVariable();
    if (ae instanceof Collection) {
      return (Collection) ae;
    }
    return Collections.emptyList();
  }
}
