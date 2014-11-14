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
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.api.diff.IMergeableDifference;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.filter.AbstractFilterItem;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.system.topdown.handlers.transformation.TopDownTransformationHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * A filter for the ReferenceValuePresence when associated to a PresenceElement
 */
public class UnwantedElementPresenceFilterItem extends AbstractFilterItem {

  private boolean isTrace(EObject element_p, IContext context_p) {
    return TopDownTransformationHelper.getInstance(context_p).isTrace(element_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getDescription(IDifference difference_p) {
    return "Not automatically propagated. Elements in scope can be merged without it.";
  }

  protected Collection<EObject> getScopeUntracedElements(IContext context_p) {
    if (!context_p.exists("USE")) {
      context_p.put("USE", new HashSet<EObject>());

      HashSet<EObject> result = (HashSet<EObject>) context_p.get("USE");
      Iterator<EObject> scopeElements = ContextScopeHandlerHelper.getInstance(context_p).get(ITransitionConstants.SOURCE_SCOPE, context_p);
      while (scopeElements.hasNext()) {
        EObject scopeElement = scopeElements.next();
        if (!TopDownTransformationHelper.getInstance(context_p).isTracedInTarget(scopeElement, context_p)) {
          result.add(scopeElement);
        }
      }

      return result;

    }
    return (HashSet<EObject>) context_p.get("USE");

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FilterAction getDestinationRole(IDifference difference_p, Role role_p, IContext context_p) {
    if (true) {
      return null;
    }

    if (role_p == Role.REFERENCE) {

      ITraceabilityHandler sourceHandler = (ITraceabilityHandler) context_p.get(ITransitionConstants.TRACEABILITY_SOURCE_MERGE_HANDLER);

      if (difference_p instanceof IElementPresence) {
        IElementPresence element = (IElementPresence) difference_p;
        EObject toMerge = ((IElementPresence) difference_p).getElementMatch().get(Role.REFERENCE);
        if (isTrace(toMerge, context_p)) {
          return null;
        }

        IMergeableDifference diff = element;

        Collection<EObject> elements = new HashSet<EObject>();
        if (toMerge != null) {
          elements.addAll(sourceHandler.retrieveSourceElements(toMerge, context_p));
        }

        for (IMergeableDifference diffa : diff.getRequiresDependencies(Role.REFERENCE)) {
          if (diffa instanceof IElementPresence) {
            toMerge = ((IElementPresence) diffa).getElementMatch().get(Role.REFERENCE);
            if (toMerge != null) {
              elements.addAll(sourceHandler.retrieveSourceElements(toMerge, context_p));
            }
          }
        }

        boolean toRemove = true;
        Collection<EObject> unwanted = getScopeUntracedElements(context_p);
        if (!elements.isEmpty()) {
          for (EObject unwantedElement : unwanted) {
            if (elements.contains(unwantedElement)) {
              toRemove = false;
              break;
            }
          }
        }

        if (toRemove) {
          //return FilterAction.NO_ACTION;
        }
      }
    }
    return null;
  }
}
