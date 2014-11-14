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
package org.polarsys.capella.core.transition.system.topdown.rules.fa;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.IContextScopeHandler;
import org.polarsys.capella.core.transition.system.topdown.handlers.transformation.TopDownTransformationHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class FunctionalChainRule extends org.polarsys.capella.core.transition.system.rules.fa.FunctionalChainRule {

  /**
   * {@inheritDoc}
   */
  @Override
  public EClass getTargetType(EObject element_p, IContext context_p) {
    return FaPackage.Literals.FUNCTIONAL_CHAIN;
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);
    FunctionalChain element = (FunctionalChain) source_p;

    if (!TopDownTransformationHelper.getInstance(context_p).isTracedInTarget(element, context_p)) {
      retrieveDeepValidChain(element, result_p, context_p);
    }
  }

  @Override
  protected void retrieveDeepValidChain(EObject source_p, List<EObject> result_p, IContext context_p) {
    FunctionalChain element = (FunctionalChain) source_p;

    super.retrieveDeepValidChain(source_p, result_p, context_p);

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context_p);
    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, source_p, context_p)) {
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getOwnedFunctionalChainInvolvements(), context_p);
    }

    for (Involvement involvement : element.getInvolvingInvolvements()) {
      org.polarsys.capella.core.data.capellacore.InvolverElement fc = involvement.getInvolver();

      //Add all involving Capabilities
      if (involvement instanceof FunctionalChainAbstractCapabilityInvolvement) {
        result_p.add(involvement);
        if ((fc != null) && (fc instanceof AbstractCapability)) {
          result_p.add(fc);
        }
      }
    }
  }

}
