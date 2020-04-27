/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
  public IStatus dispose(IContext context) {

    return Status.OK_STATUS;
  }

  protected HashMapSet<String, INotifyListener> getAllListeners(String kind, IContext context) {
    HashMapSet<String, INotifyListener> scope = null;
    if (!context.exists(NOTIFY_SCOPE)) {
      scope = new HashMapSet<String, INotifyListener>();
      context.put(NOTIFY_SCOPE, scope);
    }

    scope = (HashMapSet<String, INotifyListener>) context.get(NOTIFY_SCOPE);
    return scope;
  }

  protected Collection<INotifyListener> getListeners(String kind, IContext context) {
    return getAllListeners(kind, context).get(kind);
  }

  /**
   * {@inheritDoc}
   */
  public IStatus init(IContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public void addListener(String kind, INotifyListener listener, IContext context) {
    getAllListeners(kind, context).put(kind, listener);
  }

  /**
   * {@inheritDoc}
   */
  public void notify(String kind, INotifyChangeEvent event, IContext context) {
    for (INotifyListener listener : getListeners(kind, context)) {
      listener.notifyChanged(event, context);
    }
  }

}
