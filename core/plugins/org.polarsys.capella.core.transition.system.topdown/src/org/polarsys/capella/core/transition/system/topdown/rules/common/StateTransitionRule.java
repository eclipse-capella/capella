/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.topdown.rules.common;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.core.data.capellacommon.StateEvent;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.core.transition.system.topdown.handlers.transformation.TopDownTransformationHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class StateTransitionRule extends org.polarsys.capella.core.transition.system.rules.common.StateTransitionRule {

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    super.retrieveContainer(element_p, result_p, context_p);
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {

    super.retrieveGoDeep(source_p, result_p, context_p);

    // but we return children
    StateTransition element = (StateTransition) source_p;

    if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, element,
        context_p)) {

      // Transition only already transitioned functions
      for (AbstractEvent effect : element.getEffect()) {
        if (effect != null) {
          if (TopDownTransformationHelper.getInstance(context_p).isTracedInTarget(effect, context_p)) {
            ContextScopeHandlerHelper.getInstance(context_p).add(ITopDownConstants.CONTEXT_SCOPE__AVOID_DIFF_ELEMENTS,
                effect, context_p);
            result_p.add(effect);
          }
        }
      }

      // Transition only already transitioned functions
      for (AbstractEvent trigger : element.getTriggers()) {
        if (trigger != null) {
          if (trigger instanceof StateEvent) {
            result_p.add(trigger);
          }
          if (TopDownTransformationHelper.getInstance(context_p).isTracedInTarget(trigger, context_p)) {
            ContextScopeHandlerHelper.getInstance(context_p).add(ITopDownConstants.CONTEXT_SCOPE__AVOID_DIFF_ELEMENTS,
                trigger, context_p);

            result_p.add(trigger);
          }
        }
      }

    }

  }
}
