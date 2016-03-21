/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.re.handlers.traceability;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.handlers.traceability.config.ExtendedTraceabilityConfiguration;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class ReConfiguration extends ExtendedTraceabilityConfiguration {

  CatalogElement _origin;
  CatalogElement _root;

  public ReConfiguration(CatalogElement root) {
    _root = root;
  }

  /**
   * @param source_p
   * @param target_p
   */
  public ReConfiguration(CatalogElement origin, CatalogElement replica) {
    _origin = origin;
    _root = replica;
  }

  @Override
  protected void initHandlers(IContext fContext) {
    addHandler(fContext, new ReTraceabilityHandler(_origin, _root, getIdentifier(fContext)) {

      /**
       * {@inheritDoc}
       */
      @Override
      protected void addMappings(EObject sourceElement, EObject targetElement, IContext context) {
        super.addMappings(sourceElement, targetElement, context);
      }

    });
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getExtensionIdentifier(IContext context) {
    return "RE3";
  }

}
