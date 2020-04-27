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
   * Shared elements are elements which are not in the scopeElements but which will be shared by both ReplicableElement
   * and Replica Retrieve the sharedElements for the given element
   */
  public Collection<EObject> getSharedElements(Collection<EObject> elements, Collection<EObject> scopeElements,
      IContext context);

  /**
   * Some Shared elements are referenced by ReplicableElement but will be not referenced by the Replica (missing
   * dependencies, etc). This method returns only elements which can be referenced through the newly created
   * ReplicableElement
   */
  public Collection<EObject> getValidSharedElements(Collection<EObject> elements, Collection<EObject> scopeElements,
      IContext context);

  /**
   * Retrieve elements which are dependent of the given element but should be included in the scope these elements, not
   * included in the scope, will raise a warning to the user indicates him to include them (or not)
   */
  public Collection<EObject> getDependencies(Collection<EObject> elements, Collection<EObject> scopeElements,
      IContext context);

  /**
   * Retrieve elements which are related to the given elements These elements will be included (or not) by the user into
   * the scope
   */
  public Collection<EObject> getRelatedElements(Collection<EObject> elements, Collection<EObject> scopeElements,
      IContext context);

  /**
   * Retrieve all elements which will be included automatically in the scope for the given elements
   */
  public Collection<EObject> getScopeElements(Collection<EObject> elements, Collection<EObject> scopeElements,
      IContext context);

  /**
   * When the scope is computed, complementary elements according to the given elements will also be included
   */
  public Collection<EObject> getComplementaryScopeElements(Collection<EObject> elements,
      Collection<EObject> scopeElements, IContext context);

}
