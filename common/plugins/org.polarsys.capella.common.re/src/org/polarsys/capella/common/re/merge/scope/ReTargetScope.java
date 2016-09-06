/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.common.merge.scope.ITargetModelScope;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class ReTargetScope extends ReScope implements ITargetModelScope {

  @Override
  protected boolean isSource() {
    return false;
  }

  /**
   * @param element
   * @param handler
   * @param elements
   * @param context
   */
  public ReTargetScope(CatalogElement element, ITraceabilityHandler handler, Collection<? extends EObject> elements,
      IContext context) {
    super(element, handler, elements, context);
    setOriginator("Resulting model");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<EObject> retrieveTransformedElementsFromTarget(EObject targetElement) {
    return null;
  }
}
