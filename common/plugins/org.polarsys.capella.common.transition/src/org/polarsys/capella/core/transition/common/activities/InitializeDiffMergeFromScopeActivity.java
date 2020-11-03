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

package org.polarsys.capella.core.transition.common.activities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
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
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
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
   * @param context
   * @param activityParams
   * @return
   */
  @Override
  protected IStatus initializeReferenceScope(IContext context, ActivityParameters activityParams) {
    EObject sourceTop = (EObject) context.get(ITransitionConstants.TRANSITION_SOURCE_ROOT);
    context.put(ITransitionConstants.MERGE_REFERENCE_CONTAINER, sourceTop);

    List<EObject> rootSource = new ArrayList<EObject>();
    rootSource.add((EObject) context.get(ITransitionConstants.MERGE_REFERENCE_CONTAINER));

    IEditableModelScope sourceScope = new ReferenceModelScope(rootSource, context);

    context.put(ITransitionConstants.MERGE_REFERENCE_SCOPE, sourceScope);

    ((PartialRootedModelScope) sourceScope).build(getReferenceFilter(context));

    return Status.OK_STATUS;
  }

  @Override
  protected IHandler createDefaultTraceabilitySourceHandler(IContext context) {
    return ITraceabilityHandler.DEFAULT;
  }

  @Override
  protected IModelScopeFilter getReferenceFilter(final IContext context) {
    // With an initialization of diffMerge from scope, we filter build of scope with
    // all elements which contain one of scope elements.
    // In fact, without a transformation, each container should be present in the scope in order to diffMerge correctly.

    return new IModelScopeFilter() {

      public boolean accepts(EObject source) {
        IRulesHandler ruleHandler = (IRulesHandler) context.get(ITransitionConstants.RULES_HANDLER);

        boolean result = true;
        try {
          MappingPossibility mapping = ruleHandler.getApplicablePossibility(source);
          if (mapping != null) {
            IRule<?> rule = ruleHandler.getApplicablePossibility(source).getCompleteRule();
            if ((rule != null) && (rule instanceof IRuleTransformation)) {
              IRuleTransformation deeperRule = (IRuleTransformation) rule;
              result = deeperRule.transformRequired(source, context).isOK();
            }
          }
        } catch (MappingPossibilityResolutionException exception) {
        }

        if (result) {
          result = false;
          Collection<EObject> scope = ScopeHandlerHelper.getInstance(context).getScope(context);
          for (EObject element : scope) {
            if (element.equals(source) || EcoreUtil2.isContainedBy(element, source)) {
              result = true;
              break;
            }
          }
        }

        if (!result) {
          LogHelper.getInstance().warn(NLS.bind("{0} is removed from scope", LogHelper.getInstance().getText(source)), "okok");
        }
        return result;
      }
    };
  }

  @Override
  protected IModelScopeFilter getTargetFilter(final IContext context) {
    // No specific rule here.

    return new IModelScopeFilter() {
      public boolean accepts(EObject element) {
        return true;
      }
    };
  }

}
