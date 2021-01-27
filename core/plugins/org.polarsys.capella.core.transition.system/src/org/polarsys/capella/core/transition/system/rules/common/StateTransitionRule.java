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

package org.polarsys.capella.core.transition.system.rules.common;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

public class StateTransitionRule extends AbstractCapellaElementRule {

  public StateTransitionRule() {
    super();
    registerUpdatedAttribute(CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGER_DESCRIPTION);
    registerUpdatedAttribute(CapellacommonPackage.Literals.STATE_TRANSITION__KIND);

    registerUpdatedReference(CapellacommonPackage.Literals.STATE_TRANSITION__SOURCE);
    registerUpdatedReference(CapellacommonPackage.Literals.STATE_TRANSITION__TARGET);
    registerUpdatedReference(CapellacommonPackage.Literals.STATE_TRANSITION__EFFECT);
    registerUpdatedReference(CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGERS);
    registerUpdatedReference(CapellacommonPackage.Literals.STATE_TRANSITION__GUARD);
  }

  @Override
  protected EClass getSourceType() {
    return CapellacommonPackage.Literals.STATE_TRANSITION;
  }

  @Override
  protected void premicesRelated(EObject element, ArrayList<IPremise> needed) {
    super.premicesRelated(element, needed);
    needed.addAll(createDefaultPrecedencePremices(element, CapellacommonPackage.Literals.STATE_TRANSITION__EFFECT));
    needed.addAll(createDefaultPrecedencePremices(element, CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGERS));
    needed.addAll(createDefaultPrecedencePremices(element, CapellacommonPackage.Literals.STATE_TRANSITION__GUARD));
    needed.addAll(createDefaultPrecedencePremices(element, CapellacommonPackage.Literals.STATE_TRANSITION__SOURCE));
    needed.addAll(createDefaultPrecedencePremices(element, CapellacommonPackage.Literals.STATE_TRANSITION__TARGET));
  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, CapellacommonPackage.Literals.STATE_TRANSITION__GUARD, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, CapellacommonPackage.Literals.STATE_TRANSITION__SOURCE, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, CapellacommonPackage.Literals.STATE_TRANSITION__TARGET, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, CapellacommonPackage.Literals.STATE_TRANSITION__EFFECT, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGERS, context);
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);

    if (source instanceof StateTransition) {
      StateTransition element = (StateTransition) source;
      result.add(element.getGuard());
      if (ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.SOURCE_SCOPE, element,
          context)) {
        // Add effect elements to scope
        for (AbstractEvent effect : element.getEffect()) {
          if (shouldAddEffectInScope(effect)) {
            ContextScopeHandlerHelper.getInstance(context).add(ITransitionConstants.SOURCE_SCOPE, effect, context);
            result.add(effect);
          }
        }
        // Add trigger elements to scope
        for (AbstractEvent trigger : element.getTriggers()) {
          if (shouldAddTriggerInScope(trigger)) {
            ContextScopeHandlerHelper.getInstance(context).add(ITransitionConstants.SOURCE_SCOPE, trigger, context);
            result.add(trigger);
          }
        }
      }
    }
  }

  /**
   * By default, only triggering EIs should be added in to the scope
   * 
   * @param effect
   * @return
   */
  protected boolean shouldAddEffectInScope(AbstractEvent effect) {
    return effect instanceof ExchangeItem;
  }

  /**
   * By default, only effected EIs should be added in to the scope
   * 
   * @param trigger
   * @return
   */
  protected boolean shouldAddTriggerInScope(AbstractEvent trigger) {
    return trigger instanceof ExchangeItem;
  }
}
