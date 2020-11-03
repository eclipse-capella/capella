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

package org.polarsys.capella.core.transition.common.handlers.scope;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.rules.IRuleScope;
import org.polarsys.kitalpha.transposer.rules.handler.api.IRulesHandler;
import org.polarsys.kitalpha.transposer.rules.handler.exceptions.possibilities.MappingPossibilityResolutionException;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.common.MappingPossibility;

/**
 *
 */
public class RuleRelatedElementsScopeRetriever implements IScopeRetriever {

  /**
   * {@inheritDoc}
   */
  public IStatus init(IContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus dispose(IContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public Collection<? extends EObject> retrieveRelatedElements(EObject source, IContext context) {

    IRulesHandler ruleHandler = (IRulesHandler) context.get(ITransitionConstants.RULES_HANDLER);

    try {
      if (ruleHandler != null) {
        MappingPossibility mapping = ruleHandler.getApplicablePossibility(source);
        if (mapping != null) {
          IRule<?> rule = ruleHandler.getApplicablePossibility(source).getCompleteRule();
          if ((rule != null) && (rule instanceof IRuleScope)) {
            IRuleScope deeperRule = (IRuleScope) rule;
            return deeperRule.retrieveRelatedElements(source, context);
          }
        }
      }
    } catch (MappingPossibilityResolutionException exception) {
      //Nothing to report
    }

    return Collections.emptyList();
  }

  /**
   * {@inheritDoc}
   */
  public Collection<? extends EObject> retrieveSharedElements(IContext context) {
    return Collections.emptyList();
  }

}
