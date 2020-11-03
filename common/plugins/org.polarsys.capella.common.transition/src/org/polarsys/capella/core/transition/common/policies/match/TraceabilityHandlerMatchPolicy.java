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

package org.polarsys.capella.core.transition.common.policies.match;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityTraceHandler;
import org.polarsys.capella.core.transition.common.merge.scope.ITargetModelScope;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * A match policy for diff/merge within pairs of corresponding elements.
 */
public class TraceabilityHandlerMatchPolicy extends ContextMatchPolicy {

  /**
   * Constructor
   * 
   * @param a
   *          non-null mapping of corresponding elements whose further modifications will impact this policy
   */
  public TraceabilityHandlerMatchPolicy(IContext context) {
    super(context);
  }

  /**
   * Some elements should not be matched even if they are traced to an element from the model source.
   * 
   * @param element
   * @param scope
   * @param context
   * @return
   */
  public boolean isMatchable(EObject element, ITreeDataScope<EObject> scope, IContext context) {
    return true;
  }

  /**
   * @see org.polarsys.capella.common.consonance.policies.IMatchPolicy#getId(org.eclipse.emf.ecore.EObject,
   *      org.polarsys.capella.common.consonance.scopes.IModelScope)
   */
  @Override
  public Comparable<?> getMatchID(EObject element, ITreeDataScope<EObject> scope) {
    Collection<EObject> bounds = new ArrayList<EObject>();

    IContext context = getContext();

    ITraceabilityHandler sourceHandler = (ITraceabilityHandler) context
        .get(ITransitionConstants.TRACEABILITY_SOURCE_MERGE_HANDLER);
    ITraceabilityHandler targetHandler = (ITraceabilityHandler) context
        .get(ITransitionConstants.TRACEABILITY_TARGET_MERGE_HANDLER);
    ITraceabilityHandler handler = null;

    if (scope instanceof ITargetModelScope) {
      handler = targetHandler;
    } else {
      handler = sourceHandler;
    }

    context.put(ITransitionConstants.TRACEABILITY_HANDLER, handler);

    if (handler instanceof ITraceabilityTraceHandler) {
      ITraceabilityTraceHandler tHandler = (ITraceabilityTraceHandler) handler;
      if (tHandler.isTrace(element, context)) {
        return "TRACE-TO-" + tHandler.getSourceElement(element, context);
      }
    }

    if (isMatchable(element, scope, context)) {

      bounds = handler.retrieveSourceElements(element, context);

      if (bounds.size() > 0) {
        EObject commonSource = bounds.iterator().next();

        // To '''support''' (for now) some SID linked to elements, we need to retrieve the same id from the transformed
        // scope and the target scope.
        if (scope instanceof ITargetModelScope) {
          Collection<EObject> transformedElements = ((ITargetModelScope) scope)
              .retrieveTransformedElementsFromTarget(element);
          // Retrieve the same id than the common transformed element of sourceElements.
          if (transformedElements.size() > 0) {
            bounds = sourceHandler.retrieveSourceElements(transformedElements.iterator().next(), context);
            if (bounds.size() > 0) {
              commonSource = bounds.iterator().next();
            }
          }
        }

        System.out.println("ID = " + handler.getId(commonSource, context) + " " + commonSource);
        return "TRACED-FROM-" + handler.getId(commonSource, context);
      }

    } else {
      return "UNMATCHABLE-ELEMENT-" + handler.getId(element, context);
    }

    System.out.println("ID = " + handler.getId(element, context) + " " + element);
    return handler.getId(element, context);
  }
}
