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
package org.polarsys.capella.core.transition.system.topdown.handlers.filter;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.filter.ReferenceValueFromSource;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.common.rules.IRuleUpdateReference;
import org.polarsys.kitalpha.transposer.rules.handler.api.IRulesHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.common.MappingPossibility;

/**
 * A filter for the ReferenceValuePresence when associated to a PresenceElement
 */
public class ReferencePropagationFilterItem extends ReferenceValueFromSource {

  @Override
  public boolean isMergeableReference(EReference attribute_p, EObject source_p, EObject target_p, Object oldValue_p, Object newValue_p, IContext context_p) {
    ITraceabilityHandler sourceHandler = (ITraceabilityHandler) context_p.get(ITransitionConstants.TRACEABILITY_SOURCE_MERGE_HANDLER);
    ITraceabilityHandler targetHandler = (ITraceabilityHandler) context_p.get(ITransitionConstants.TRACEABILITY_TARGET_MERGE_HANDLER);

    Collection<EObject> sources = sourceHandler.retrieveSourceElements(source_p, context_p);
    Collection<EObject> targets = targetHandler.retrieveSourceElements(target_p, context_p);
    sources.retainAll(targets);

    IRulesHandler ruleHandler = (IRulesHandler) context_p.get(ITransitionConstants.RULES_HANDLER);

    try {
      MappingPossibility mapping = ruleHandler.getApplicablePossibility(source_p);
      if (mapping != null) {
        IRule<?> rule = ruleHandler.getApplicablePossibility(source_p).getCompleteRule();
        if ((rule != null) && (rule instanceof IRuleUpdateReference)) {
          IRuleUpdateReference deeperRule = (IRuleUpdateReference) rule;
          return deeperRule.isUpdatedReference(attribute_p);
        }
      }
    } catch (Exception exception_p) {
      // Nothing to report
    }
    return false;
  }
}
