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
package org.polarsys.capella.core.transition.common.handlers.traceability.config;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class TraceabilityConfiguration implements ITraceabilityConfiguration {

  private List<ITraceabilityHandler> _handlers;

  protected String getIdentifier(IContext context_p) {
    return getClass().getSimpleName();
  }

  protected void addHandler(IContext fContext_p, ITraceabilityHandler handler_p) {
    getDefinedHandlers(fContext_p).add(handler_p);
    handler_p.init(fContext_p);
  }

  protected void initHandlers(final IContext fContext_p) {
    //Nothing here
  }

  /**
   * {@inheritDoc}
   */
  public IStatus init(IContext context_p) {
    initHandlers(context_p);
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus dispose(IContext context_p) {
    if (_handlers != null) {
      for (ITraceabilityHandler handler : _handlers) {
        handler.dispose(context_p);
      }
      _handlers.clear();
      _handlers = null;
    }
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public List<ITraceabilityHandler> getDefinedHandlers(IContext context_p) {
    if (_handlers == null) {
      _handlers = new LinkedList<ITraceabilityHandler>();
    }
    return _handlers;
  }

  /**
   * {@inheritDoc}
   */
  public boolean useHandlerForAttachment(EObject source_p, EObject target_p, ITraceabilityHandler handler_p, IContext context_p) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  public boolean useHandlerForTracedElements(EObject source_p, ITraceabilityHandler handler_p, IContext context_p) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  public boolean useHandlerForSourceElements(EObject source_p, ITraceabilityHandler handler_p, IContext context_p) {
    return true;
  }

}
