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

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * This handler allow to create the scope incrementally according to user choices
 */
public interface IDependenciesHandler extends IHandler {

  /**
   * Shared elements are elements which are not in the scopeElements but which will be shared by both ReplicableElement and Replica
   * Retrieve the sharedElements for the given element_p
   */
  public Collection<EObject> getSharedElements(Collection<EObject> elements_p, Collection<EObject> scopeElements, IContext context_p);

  /**
   * Retrieve elements which are dependent of the given element_p but should be included in the scope
   * these elements, not included in the scope, will raise a warning to the user indicates him to include them (or not)
   */
  public Collection<EObject> getDependencies(Collection<EObject> elements_p, Collection<EObject> scopeElements, IContext context_p);

  /**
   * Retrieve elements which are related to the given element_p
   * These elements will be included (or not) by the user into the scope
   */
  public Collection<EObject> getRelatedElements(Collection<EObject> elements_p, Collection<EObject> scopeElements, IContext context_p);

  /**
   * Retrieve all elements which will be included automatically in the scope for the given elements_p
   */
  public Collection<EObject> getScopeElements(Collection<EObject> elements_p, Collection<EObject> scopeElements, IContext context_p);

  /**
   * When the scope is computed, complementary elements according to the given elements_p will also be included
   */
  public Collection<EObject> getComplementaryScopeElements(Collection<EObject> elements_p, Collection<EObject> scopeElements, IContext context_p);

}
