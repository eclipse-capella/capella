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
  public IStatus init(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus dispose(IContext context_p) {
    for (Object key : context_p.getKeys()) {
      if ((key instanceof String) && (key != null)) {
        if (((String) key).startsWith(REFERENCE_COMPONENT_SCOPE)) {
          Object value = context_p.get(key);
          if ((value != null) && (value instanceof Collection)) {
            if (!((Collection<?>) value).isEmpty()) {
              ((Collection<?>) context_p.get(key)).clear();
            }
          }
        }
      }
    }
    return Status.OK_STATUS;
  }

  protected String getScopeId(String id_p) {
    return REFERENCE_COMPONENT_SCOPE + "_" + id_p;
  }

  protected Collection<EObject> getScope(String scopeId_p, IContext context_p) {
    String id = getScopeId(scopeId_p);
    Object scope = context_p.get(id);
    if (scope == null) {
      scope = new HashSet<EObject>();
      context_p.put(id, scope);
    }
    return (Collection<EObject>) scope;
  }

  /**
   * {@inheritDoc}
   */
  public void add(String scopeId_p, EObject object_p, IContext context_p) {

    if (valid(scopeId_p, object_p, context_p)) {
      getScope(scopeId_p, context_p).add(object_p);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void addAll(String scopeId_p, Collection<? extends EObject> objects_p, IContext context_p) {
    if (objects_p != null) {
      for (EObject object : objects_p) {
        if (valid(scopeId_p, object, context_p)) {
          getScope(scopeId_p, context_p).add(object);
        }
      }
    }
  }

  /**
   * @param scopeId_p
   * @param object_p
   * @param context_p
   * @return
   */
  protected boolean valid(String scopeId_p, EObject object_p, IContext context_p) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  public boolean contains(String scopeId_p, EObject object_p, IContext context_p) {
    return getScope(scopeId_p, context_p).contains(object_p);
  }

  /**
   * {@inheritDoc}
   */
  public void remove(String scopeId_p, EObject object_p, IContext context_p) {
    getScope(scopeId_p, context_p).remove(object_p);
  }

  /**
   * {@inheritDoc}
   */
  public Iterator<EObject> get(String scopeId_p, IContext context_p) {
    return getScope(scopeId_p, context_p).iterator();
  }

  /**
   * {@inheritDoc}
   */
  public void clear(String scopeId_p, IContext context_p) {
    getScope(scopeId_p, context_p).clear();
  }

  /**
   * {@inheritDoc}
   */
  public void removeAll(String scopeId_p, Collection<? extends EObject> objects_p, IContext context_p) {
    if (objects_p != null) {
      for (EObject object : objects_p) {
        if (valid(scopeId_p, object, context_p)) {
          getScope(scopeId_p, context_p).remove(object);
        }
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  public Collection<EObject> getCollection(String scopeId_p, IContext context_p) {
    return getScope(scopeId_p, context_p);
  }

}
