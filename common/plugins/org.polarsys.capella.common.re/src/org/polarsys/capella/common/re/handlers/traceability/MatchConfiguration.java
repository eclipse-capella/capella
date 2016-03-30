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
  protected void initHandlers(IContext fContext) {
    addHandler(fContext, new MatchTraceabilityHandler((ExtendedComparison) fContext.get(ITransitionConstants.MERGE_COMPARISON), getIdentifier(fContext)) {

      @Override
      public Collection<EObject> retrieveTracedElements(EObject source, IContext context) {
        Collection<EObject> parent = super.retrieveTracedElements(source, context);
        if ((parent != null) && !(parent.isEmpty())) {
          return parent;
        }
        return Collections.singletonList(source);
      }

      @Override
      public Collection<EObject> retrieveSourceElements(EObject source, IContext context) {
        Collection<EObject> parent = super.retrieveSourceElements(source, context);
        if ((parent != null) && !(parent.isEmpty())) {
          return parent;
        }
        return Collections.singletonList(source);
      }

    });

  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getExtensionIdentifier(IContext context) {
    return "MATCH";
  }

}
