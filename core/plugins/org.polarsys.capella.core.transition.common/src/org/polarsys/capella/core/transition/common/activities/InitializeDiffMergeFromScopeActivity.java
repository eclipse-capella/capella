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
package org.polarsys.capella.core.transition.common.activities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;

import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.log.LogHelper;
import org.polarsys.capella.core.transition.common.handlers.scope.ScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.common.merge.scope.IModelScopeFilter;
import org.polarsys.capella.core.transition.common.merge.scope.PartialRootedModelScope;
import org.polarsys.capella.core.transition.common.merge.scope.ReferenceModelScope;
import org.polarsys.capella.core.transition.common.rules.IRuleTransformation;
import org.polarsys.kitalpha.transposer.rules.handler.api.IRulesHandler;
import org.polarsys.kitalpha.transposer.rules.handler.exceptions.possibilities.MappingPossibilityResolutionException;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.common.MappingPossibility;

/**
 * 
 */
public class InitializeDiffMergeFromScopeActivity extends InitializeDiffMergeFromTransformationActivity {

  public static final String ID = "org.polarsys.capella.core.transition.common.activities.InitializeDiffMergeFromScopeActivity"; //$NON-NLS-1$

  /**
   * @param context_p
   * @param activityParams_p
   * @return
   */
  @Override
  protected IStatus initializeReferenceScope(IContext context_p, ActivityParameters activityParams_p) {
    EObject sourceTop = (EObject) context_p.get(ITransitionConstants.TRANSITION_SOURCE_ROOT);
    context_p.put(ITransitionConstants.MERGE_REFERENCE_CONTAINER, sourceTop);

    List<EObject> rootSource = new ArrayList<EObject>();
    rootSource.add((EObject) context_p.get(ITransitionConstants.MERGE_REFERENCE_CONTAINER));

    IFeaturedModelScope sourceScope = new ReferenceModelScope(rootSource, context_p);

    context_p.put(ITransitionConstants.MERGE_REFERENCE_SCOPE, sourceScope);

    ((PartialRootedModelScope) sourceScope).build(getReferenceFilter(context_p));

    return Status.OK_STATUS;
  }

  @Override
  protected IHandler createDefaultTraceabilitySourceHandler() {
    return ITraceabilityHandler.DEFAULT;
  }

  @Override
  protected IModelScopeFilter getReferenceFilter(final IContext context_p) {
    // With an initialization of diffMerge from scope, we filter build of scope with
    // all elements which contain one of scope elements.
    // In fact, without a transformation, each container should be present in the scope in order to diffMerge correctly.

    return new IModelScopeFilter() {

      public boolean accepts(EObject source_p) {
        IRulesHandler ruleHandler = (IRulesHandler) context_p.get(ITransitionConstants.RULES_HANDLER);

        boolean result = true;
        try {
          MappingPossibility mapping = ruleHandler.getApplicablePossibility(source_p);
          if (mapping != null) {
            IRule<?> rule = ruleHandler.getApplicablePossibility(source_p).getCompleteRule();
            if ((rule != null) && (rule instanceof IRuleTransformation)) {
              IRuleTransformation deeperRule = (IRuleTransformation) rule;
              result = deeperRule.transformRequired(source_p, context_p).isOK();
            }
          }
        } catch (MappingPossibilityResolutionException exception_p) {
        }

        if (result) {
          result = false;
          Collection<EObject> scope = ScopeHandlerHelper.getInstance(context_p).getScope(context_p);
          for (EObject element : scope) {
            if (element.equals(source_p) || EcoreUtil2.isContainedBy(element, source_p)) {
              result = true;
              break;
            }
          }
        }

        if (!result) {
          LogHelper.getInstance().warn(NLS.bind("{0} is removed from scope", LogHelper.getInstance().getText(source_p)), "okok");
        }
        return result;
      }
    };
  }

  @Override
  protected IModelScopeFilter getTargetFilter(final IContext context_p) {
    // No specific rule here.

    return new IModelScopeFilter() {
      public boolean accepts(EObject element_p) {
        return true;
      }
    };
  }

}
