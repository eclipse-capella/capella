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
package org.polarsys.capella.core.transition.system.topdown.rules.interaction;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractFunctionAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.core.transition.system.topdown.handlers.transformation.TopDownTransformationHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class AbstractCapabilityRule extends org.polarsys.capella.core.transition.system.rules.interaction.AbstractCapabilityRule {

  /**
   * {@inheritDoc}
   */
  @Override
  public EClass getTargetType(EObject element_p, IContext context_p) {

    if (OaPackage.Literals.OPERATIONAL_CAPABILITY.isInstance(element_p)) {
      return CtxPackage.Literals.CAPABILITY;

    } else if (CtxPackage.Literals.CAPABILITY.isInstance(element_p)) {
      return LaPackage.Literals.CAPABILITY_REALIZATION;

    } else if (LaPackage.Literals.CAPABILITY_REALIZATION.isInstance(element_p)) {
      return LaPackage.Literals.CAPABILITY_REALIZATION;
    }

    return super.getTargetType(element_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachContainement(EObject element_p, EObject result_p, IContext context_p) {
    super.attachContainement(element_p, result_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);

    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, InteractionPackage.Literals.ABSTRACT_CAPABILITY__AVAILABLE_IN_STATES,
        context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, InteractionPackage.Literals.ABSTRACT_CAPABILITY__EXTENDING, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, InteractionPackage.Literals.ABSTRACT_CAPABILITY__EXTENDS, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, InteractionPackage.Literals.ABSTRACT_CAPABILITY__INCLUDES, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, InteractionPackage.Literals.ABSTRACT_CAPABILITY__INCLUDING, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    AbstractCapability element = (AbstractCapability) source_p;

    if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, source_p, context_p)) {

      result_p.addAll(element.getOwnedAbstractFunctionAbstractCapabilityInvolvements());
      result_p.addAll(element.getOwnedFunctionalChainAbstractCapabilityInvolvements());

      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE,
          element.getOwnedFunctionalChainAbstractCapabilityInvolvements(), context_p);
      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE,
          element.getOwnedAbstractFunctionAbstractCapabilityInvolvements(), context_p);

      String transitionKind = (String) context_p.get(ITopDownConstants.TRANSITION_KIND);
      boolean functionalKind = ITopDownConstants.TRANSITION_TOPDOWN_FUNCTIONAL.equals(transitionKind);

      for (FunctionalChain chain : element.getOwnedFunctionalChains()) {
        if (functionalKind || TopDownTransformationHelper.getInstance(context_p).isTracedInTarget(chain, context_p)) {
          result_p.add(chain);
        }
        if (functionalKind) {
          ContextScopeHandlerHelper.getInstance(context_p).add(ITransitionConstants.SOURCE_SCOPE, chain, context_p);
        }
      }

      for (AbstractFunctionAbstractCapabilityInvolvement involvement : element.getOwnedAbstractFunctionAbstractCapabilityInvolvements()) {
        if (functionalKind || TopDownTransformationHelper.getInstance(context_p).isTracedInTarget(involvement.getInvolved(), context_p)) {
          result_p.add(involvement.getInvolved());
        }
        if (functionalKind) {
          ContextScopeHandlerHelper.getInstance(context_p).add(ITransitionConstants.SOURCE_SCOPE, involvement.getInvolved(), context_p);
        }
      }

      for (FunctionalChainAbstractCapabilityInvolvement involvement : element.getOwnedFunctionalChainAbstractCapabilityInvolvements()) {
        if (functionalKind || TopDownTransformationHelper.getInstance(context_p).isTracedInTarget(involvement.getInvolved(), context_p)) {
          result_p.add(involvement.getInvolved());
        }
        if (functionalKind) {
          ContextScopeHandlerHelper.getInstance(context_p).add(ITransitionConstants.SOURCE_SCOPE, involvement.getInvolved(), context_p);
        }
      }
    }

    if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, source_p, context_p)) {
      // Nothing here

    }
  }

}
