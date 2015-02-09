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
package org.polarsys.capella.common.re.handlers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.expressions.EvaluationContext;
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

  protected Collection<Object> getInitialSelection(Object evaluationContext_p) {
    IEvaluationContext context = (IEvaluationContext) evaluationContext_p;
    return getSemanticObjects((Collection<Object>) context.getDefaultVariable());
  }

  public Collection<Object> getSemanticObjects(Collection<Object> elements_p) {
    Collection<Object> result = new ArrayList<Object>();
    for (Object object : elements_p) {
      Object semantic = resolveSemanticObject(object);
      if (semantic != null) {
        result.add(semantic);
      }
    }
    return result;
  }

  public Object resolveSemanticObject(Object object_p) {
    Object semantic = null;

    if (object_p != null) {
      if (object_p instanceof EObject) {
        semantic = object_p;

      } else if (object_p instanceof IAdaptable) {
        Object adapter = ((IAdaptable) object_p).getAdapter(EObject.class);
        if (adapter instanceof EObject) {
          semantic = adapter;
        }
      }
    }
    return semantic;
  }

  protected abstract ICommand createCommand(Collection<Object> selection_p, IProgressMonitor progressMonitor_p);

  /**
   * {@inheritDoc}
   */
  public Object execute(final ExecutionEvent event_p) throws ExecutionException {
    try {
      LongRunningListenersRegistry.getInstance().operationStarting(getClass());
      ICommand cmd = createCommand(getSelection(event_p), new NullProgressMonitor());
      if (cmd instanceof TransitionCommand) {
        try {
          ((TransitionCommand) cmd).setName(event_p.getCommand().getDescription());
        } catch (NotDefinedException ex) {
          // silent exception
        }
      }
      TransactionHelper.getExecutionManager((Collection<? extends EObject>) getSemanticObjects(getSelection(event_p))).execute(cmd);
    } finally {
      LongRunningListenersRegistry.getInstance().operationEnded(getClass());
    }

    return null;
  }

  protected Collection<Object> getSelection(ExecutionEvent event_p) {
    EvaluationContext context = (EvaluationContext) event_p.getApplicationContext();
    Object ae = context.getDefaultVariable();
    if (ae instanceof Collection) {
      return (Collection) ae;
    }
    return Collections.emptyList();
  }
}
