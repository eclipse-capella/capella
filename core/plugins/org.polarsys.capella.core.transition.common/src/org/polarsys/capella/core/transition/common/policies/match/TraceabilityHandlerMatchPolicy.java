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
package org.polarsys.capella.core.transition.common.policies.match;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
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
   * @param a non-null mapping of corresponding elements whose further modifications will impact this policy
   */
  public TraceabilityHandlerMatchPolicy(IContext context_p) {
    super(context_p);
  }

  /**
   * Some elements should not be matched even if they are traced to an element from the model source.
   * @param element_p
   * @param scope_p
   * @param context_p
   * @return
   */
  public boolean isMatchable(EObject element_p, IModelScope scope_p, IContext context_p) {
    return true;
  }

  /**
   * @see org.polarsys.capella.common.consonance.policies.IMatchPolicy#getId(org.eclipse.emf.ecore.EObject,
   *      org.polarsys.capella.common.consonance.scopes.IModelScope)
   */
  @Override
  public Comparable<?> getMatchID(EObject element_p, IModelScope scope_p) {
    Collection<EObject> bounds = new ArrayList<EObject>();

    IContext context = getContext();

    ITraceabilityHandler sourceHandler = (ITraceabilityHandler) context.get(ITransitionConstants.TRACEABILITY_SOURCE_MERGE_HANDLER);
    ITraceabilityHandler targetHandler = (ITraceabilityHandler) context.get(ITransitionConstants.TRACEABILITY_TARGET_MERGE_HANDLER);
    ITraceabilityHandler handler = null;

    if (scope_p instanceof ITargetModelScope) {
      handler = targetHandler;
    } else {
      handler = sourceHandler;
    }

    context.put(ITransitionConstants.TRACEABILITY_HANDLER, handler);

    if (handler instanceof ITraceabilityTraceHandler) {
      ITraceabilityTraceHandler tHandler = (ITraceabilityTraceHandler) handler;
      if (tHandler.isTrace(element_p, context)) {
        return "TRACE-TO-" + tHandler.getSourceElement(element_p, context);
      }
    }

    if (isMatchable(element_p, scope_p, context)) {

      bounds = handler.retrieveSourceElements(element_p, context);

      if (bounds.size() > 0) {
        EObject commonSource = bounds.iterator().next();

        //To '''support''' (for now) some SID linked to elements, we need to retrieve the same id from the transformed scope and the target scope.
        if (scope_p instanceof ITargetModelScope) {
          Collection<EObject> transformedElements = ((ITargetModelScope) scope_p).retrieveTransformedElementsFromTarget(element_p);
          //Retrieve the same id than the common transformed element of sourceElements.
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
      return "UNMATCHABLE-ELEMENT-" + handler.getId(element_p, context);
    }

    System.out.println("ID = " + handler.getId(element_p, context) + " " + element_p);
    return handler.getId(element_p, context);
  }
}
