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
package org.polarsys.capella.common.re.merge.scope;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.common.merge.scope.ITargetModelScope;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class ReTargetScope extends ReSourceScope implements ITargetModelScope {

  @Override
  protected boolean isSource() {
    return false;
  }

  /**
   * @param element_p
   * @param handler_p
   * @param elements_p
   * @param context_p
   */
  public ReTargetScope(CatalogElement element_p, ITraceabilityHandler handler_p, Collection<? extends EObject> elements_p, IContext context_p) {
    super(element_p, handler_p, elements_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<EObject> retrieveTransformedElementsFromTarget(EObject targetElement_p) {
    return null;
  }

}
