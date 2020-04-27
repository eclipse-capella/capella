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

   void add(String scopeId, EObject object, IContext context);

   void addAll(String scopeId, Collection<? extends EObject> objects, IContext context);

   boolean contains(String scopeId, EObject object, IContext context);

   void remove(String scopeId, EObject object, IContext context);

   void removeAll(String scopeId, Collection<? extends EObject> objects, IContext context);

   Iterator<EObject> get(String scopeId, IContext context);

   Collection<EObject> getCollection(String scopeId, IContext context);

   void clear(String scopeId, IContext context);

}
