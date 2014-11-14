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
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * 
 * Some elements such as owned functions are related to the reference component.
 * To avoid in a IRule to recompute for each elements if it related to the reference component
 * we use a temporary "scope" stored into the current IContext.
 * 
 * A rule which retrieve any elements related to the reference scope should register them into this scope
 *
 */
public interface IContextScopeHandler extends IHandler {

  public void add(String scopeId_p, EObject object_p, IContext context_p);

  public void addAll(String scopeId_p, Collection<? extends EObject> objects_p, IContext context_p);

  public boolean contains(String scopeId_p, EObject object_p, IContext context_p);

  public void remove(String scopeId_p, EObject object_p, IContext context_p);

  public void removeAll(String scopeId_p, Collection<? extends EObject> objects_p, IContext context_p);

  public Iterator<EObject> get(String scopeId_p, IContext context_p);

  public Collection<EObject> getCollection(String scopeId_p, IContext context_p);

  public void clear(String scopeId_p, IContext context_p);

}
