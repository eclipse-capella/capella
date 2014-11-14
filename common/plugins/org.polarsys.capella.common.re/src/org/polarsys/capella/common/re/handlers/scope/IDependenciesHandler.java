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
package org.polarsys.capella.common.re.handlers.scope;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public interface IDependenciesHandler extends IHandler {

  public IStatus getDependenciesStatus(Collection<EObject> elements_p, Collection<EObject> scopeElements, IContext context_p);

  public Collection getSharedElements(Collection<EObject> elements_p, Collection<EObject> scopeElements, IContext context_p);

  public Collection getDependencies(Collection<EObject> elements_p, Collection<EObject> scopeElements, IContext context_p);

  public Collection getRelatedElements(Collection<EObject> elements_p, Collection<EObject> scopeElements, IContext context_p);

  public Collection getScopeElements(Collection<EObject> elements_p, Collection<EObject> scopeElements, IContext context_p);

  public Collection getComplementaryScopeElements(Collection<EObject> elements_p, Collection<EObject> scopeElements, IContext context_p);

}
