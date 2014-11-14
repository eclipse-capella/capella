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
package org.polarsys.capella.core.transition.common.handlers.notify;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.polarsys.capella.core.transition.common.capellaHelpers.HashMapSet;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class DefaultNotifyHandler implements INotifyHandler {

  protected static final String NOTIFY_SCOPE = "NOTIFY_SCOPE"; //$NON-NLS-1$

  /**
   * {@inheritDoc}
   */
  public IStatus dispose(IContext context_p) {

    return Status.OK_STATUS;
  }

  protected HashMapSet<String, INotifyListener> getAllListeners(String kind_p, IContext context_p) {
    HashMapSet<String, INotifyListener> scope = null;
    if (!context_p.exists(NOTIFY_SCOPE)) {
      scope = new HashMapSet<String, INotifyListener>();
      context_p.put(NOTIFY_SCOPE, scope);
    }

    scope = (HashMapSet<String, INotifyListener>) context_p.get(NOTIFY_SCOPE);
    return scope;
  }

  protected Collection<INotifyListener> getListeners(String kind_p, IContext context_p) {
    return getAllListeners(kind_p, context_p).get(kind_p);
  }

  /**
   * {@inheritDoc}
   */
  public IStatus init(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public void addListener(String kind_p, INotifyListener listener_p, IContext context_p) {
    getAllListeners(kind_p, context_p).put(kind_p, listener_p);
  }

  /**
   * {@inheritDoc}
   */
  public void notify(String kind_p, INotifyChangeEvent event_p, IContext context_p) {
    for (INotifyListener listener : getListeners(kind_p, context_p)) {
      listener.notifyChanged(event_p, context_p);
    }
  }

}
