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

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.filter.AttributeValueFromSource;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.common.rules.IRuleUpdateAttribute;
import org.polarsys.kitalpha.transposer.rules.handler.api.IRulesHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.common.MappingPossibility;

/**
 * 
 * Hide differences on RealizationLink
 *
 */
public class AttributeUpdateFilterItem extends AttributeValueFromSource {

  /**
   * {@inheritDoc}
   */
  @Override
  public FilterAction getDestinationRole(IDifference difference_p, Role role_p, IContext context_p) {
    return super.getDestinationRole(difference_p, role_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getDescription(IDifference difference_p) {
    return "Propagation, related attribute is updated automatically";
  }

  /**
   * Returns whether the difference from attribute value should be merged or not
   * @return
   */
  @Override
  public boolean isMergeableAttribute(EAttribute attribute_p, EObject source_p, EObject target_p, Object oldValue_p, Object newValue_p, IContext context_p) {
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
        if ((rule != null) && (rule instanceof IRuleUpdateAttribute)) {
          IRuleUpdateAttribute deeperRule = (IRuleUpdateAttribute) rule;
          return deeperRule.isUpdatedAttribute(attribute_p);
        }
      }
    } catch (Exception exception_p) {
      // Nothing to report
    }
    return false;
  }

}
