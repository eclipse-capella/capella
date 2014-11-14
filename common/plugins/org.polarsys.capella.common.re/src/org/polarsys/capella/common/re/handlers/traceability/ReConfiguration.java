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

  public ReConfiguration(CatalogElement root_p) {
    _root = root_p;
  }

  /**
   * @param source_p
   * @param target_p
   */
  public ReConfiguration(CatalogElement origin_p, CatalogElement replica_p) {
    _origin = origin_p;
    _root = replica_p;
  }

  @Override
  protected void initHandlers(IContext fContext_p) {
    addHandler(fContext_p, new ReTraceabilityHandler(_origin, _root, getIdentifier(fContext_p)) {

      /**
       * {@inheritDoc}
       */
      @Override
      protected void addMappings(EObject sourceElement_p, EObject targetElement_p, IContext context_p) {
        super.addMappings(sourceElement_p, targetElement_p, context_p);
      }

    });
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getExtensionIdentifier(IContext context_p) {
    return "RE3";
  }

}
