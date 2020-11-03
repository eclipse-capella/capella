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

  protected String getIdentifier(IContext context) {
    return getClass().getSimpleName();
  }

  protected void addHandler(IContext fContext, ITraceabilityHandler handler) {
    getDefinedHandlers(fContext).add(handler);
    handler.init(fContext);
  }

  /**
   * Retrieve the traceability handler matching the given class
   */
  public ITraceabilityHandler getDefinedHandler(IContext context, Class clazz) {
    for (ITraceabilityHandler handler : getDefinedHandlers(context)) {
      if (clazz.isInstance(handler)) {
        return handler;
      }
    }
    return null;
  }

  protected void initHandlers(final IContext fContext) {
    // Nothing here
  }

  /**
   * {@inheritDoc}
   */
  public IStatus init(IContext context) {
    initHandlers(context);
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus dispose(IContext context) {
    if (_handlers != null) {
      for (ITraceabilityHandler handler : _handlers) {
        handler.dispose(context);
      }
      _handlers.clear();
      _handlers = null;
    }
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public List<ITraceabilityHandler> getDefinedHandlers(IContext context) {
    if (_handlers == null) {
      _handlers = new LinkedList<ITraceabilityHandler>();
    }
    return _handlers;
  }

  /**
   * {@inheritDoc}
   */
  public boolean useHandlerForAttachment(EObject source, EObject target, ITraceabilityHandler handler, IContext context) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  public boolean useHandlerForTracedElements(EObject source, ITraceabilityHandler handler, IContext context) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  public boolean useHandlerForSourceElements(EObject source, ITraceabilityHandler handler, IContext context) {
    return true;
  }

}
