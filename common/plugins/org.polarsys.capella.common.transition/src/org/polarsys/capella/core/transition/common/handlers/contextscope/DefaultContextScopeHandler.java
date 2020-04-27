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

package org.polarsys.capella.core.transition.common.handlers.contextscope;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class DefaultContextScopeHandler implements IContextScopeHandler {

  protected static final String REFERENCE_COMPONENT_SCOPE = "REFERENCE_COMPONENT_SCOPE"; //$NON-NLS-1$

  /**
   * {@inheritDoc}
   */
  public IStatus init(IContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus dispose(IContext context) {
    for (Object key : context.getKeys()) {
      if ((key instanceof String) && ((String) key).startsWith(REFERENCE_COMPONENT_SCOPE)) {
        Object value = context.get(key);
        if ((value != null) && (value instanceof Collection)) {
          if (!((Collection<?>) value).isEmpty()) {
            ((Collection<?>) context.get(key)).clear();
          }
        }
      }
    }
    return Status.OK_STATUS;
  }

  protected String getScopeId(String id) {
    return REFERENCE_COMPONENT_SCOPE + "_" + id;
  }

  protected Collection<EObject> getScope(String scopeId, IContext context) {
    String id = getScopeId(scopeId);
    Object scope = context.get(id);
    if (scope == null) {
      scope = new HashSet<EObject>();
      context.put(id, scope);
    }
    return (Collection<EObject>) scope;
  }

  /**
   * {@inheritDoc}
   */
  public void add(String scopeId, EObject object, IContext context) {

    if (valid(scopeId, object, context)) {
      getScope(scopeId, context).add(object);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void addAll(String scopeId, Collection<? extends EObject> objects, IContext context) {
    if (objects != null) {
      for (EObject object : objects) {
        if (valid(scopeId, object, context)) {
          getScope(scopeId, context).add(object);
        }
      }
    }
  }

  /**
   * @param scopeId
   * @param object
   * @param context
   * @return
   */
  protected boolean valid(String scopeId, EObject object, IContext context) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  public boolean contains(String scopeId, EObject object, IContext context) {
    return getScope(scopeId, context).contains(object);
  }

  /**
   * {@inheritDoc}
   */
  public void remove(String scopeId, EObject object, IContext context) {
    getScope(scopeId, context).remove(object);
  }

  /**
   * {@inheritDoc}
   */
  public Iterator<EObject> get(String scopeId, IContext context) {
    return getScope(scopeId, context).iterator();
  }

  /**
   * {@inheritDoc}
   */
  public void clear(String scopeId, IContext context) {
    getScope(scopeId, context).clear();
  }

  /**
   * {@inheritDoc}
   */
  public void removeAll(String scopeId, Collection<? extends EObject> objects, IContext context) {
    if (objects != null) {
      for (EObject object : objects) {
        if (valid(scopeId, object, context)) {
          getScope(scopeId, context).remove(object);
        }
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  public Collection<EObject> getCollection(String scopeId, IContext context) {
    return getScope(scopeId, context);
  }

}
