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

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.traceability.config.ExtendedTraceabilityConfiguration;
import org.polarsys.capella.core.transition.common.merge.ExtendedComparison;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class MatchConfiguration extends ExtendedTraceabilityConfiguration {

  public MatchConfiguration() {
  }

  @Override
  protected void initHandlers(IContext fContext_p) {
    addHandler(fContext_p, new MatchTraceabilityHandler((ExtendedComparison) fContext_p.get(ITransitionConstants.MERGE_COMPARISON), getIdentifier(fContext_p)) {

      @Override
      public Collection<EObject> retrieveTracedElements(EObject source_p, IContext context_p) {
        Collection<EObject> parent = super.retrieveTracedElements(source_p, context_p);
        if ((parent != null) && !(parent.isEmpty())) {
          return parent;
        }
        return Collections.singletonList(source_p);
      }

      @Override
      public Collection<EObject> retrieveSourceElements(EObject source_p, IContext context_p) {
        Collection<EObject> parent = super.retrieveSourceElements(source_p, context_p);
        if ((parent != null) && !(parent.isEmpty())) {
          return parent;
        }
        return Collections.singletonList(source_p);
      }

    });

  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getExtensionIdentifier(IContext context_p) {
    return "MATCH";
  }

}
