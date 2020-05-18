/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.topdown.rules.fa;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.core.transition.system.topdown.handlers.transformation.TopDownTransformationHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ComponentFunctionalAllocationRule extends org.polarsys.capella.core.transition.system.rules.fa.ComponentFunctionalAllocationRule {

  @Override
  protected EObject getTarget(EObject source_p, IContext context_p) {
    EObject target = super.getTarget(source_p, context_p);

    String transitionKind = (String) context_p.get(ITopDownConstants.TRANSITION_KIND);
    if (ITopDownConstants.TRANSITION_TOPDOWN_OE2ACTOR.equals(transitionKind) || ITopDownConstants.TRANSITION_TOPDOWN_OE2SYSTEM.equals(transitionKind)) {
      if (!(OptionsHandlerHelper.getInstance(context_p).getBooleanValue(context_p, ITopDownConstants.OPTIONS_SCOPE,
          ITopDownConstants.OPTIONS_TRANSITION__FUNCTIONAL, ITopDownConstants.OPTIONS_TRANSITION__FUNCTIONAL_DEFAULT))) {
        if (!TopDownTransformationHelper.getInstance(context_p).isTracedInTarget(target, context_p)) {
          return null;
        }
      }
    }
    return target;
  }

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
  }

}
