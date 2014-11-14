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
package org.polarsys.capella.core.transition.system.topdown.policies.match;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityTraceHandler;
import org.polarsys.capella.core.transition.common.merge.scope.TargetModelScope;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * A match policy for diff/merge within pairs of corresponding elements.
 */
public class TopDownMatchPolicy extends org.polarsys.capella.core.transition.common.policies.match.TraceabilityHandlerMatchPolicy {

  /**
   * Constructor
   * @param a non-null mapping of corresponding elements whose further modifications will impact this policy
   */
  public TopDownMatchPolicy(IContext context_p2) {
    super(context_p2);
  }

  @Override
  public Comparable<?> getMatchId(EObject element_p, IModelScope scope_p) {
    EObject bound = null;

    IContext context = getContext();

    ITraceabilityHandler sourceHandler = (ITraceabilityHandler) context.get(ITransitionConstants.TRACEABILITY_SOURCE_MERGE_HANDLER);
    ITraceabilityHandler targetHandler = (ITraceabilityHandler) context.get(ITransitionConstants.TRACEABILITY_TARGET_MERGE_HANDLER);
    ITraceabilityHandler handler = null;

    if (scope_p instanceof TargetModelScope) {
      handler = targetHandler;

    } else {
      handler = sourceHandler;

    }

    context.put(ITransitionConstants.TRACEABILITY_HANDLER, handler);

    String ID = null;

    if (handler instanceof ITraceabilityTraceHandler) {
      ITraceabilityTraceHandler tHandler = (ITraceabilityTraceHandler) handler;
      if (tHandler.isTrace(element_p, context)) {
        ID = getTraceIdentifier(element_p, context, scope_p, tHandler);
      }
    }

    if (ID == null) {
      ID = getIdentifier(element_p, context, scope_p, handler);
    }

    if (ID == null) {
      ID = handler.getId(element_p, context);
    }

    return ID;
  }

  /**
   * @param element_p
   * @param context_p
   * @param scope_p
   * @param handler_p
   */
  private String getTraceIdentifier(EObject element_p, IContext context_p, IModelScope scope_p, ITraceabilityTraceHandler handler_p) {
    IContext context = getContext();
    String ID = "";
    EObject target = handler_p.getTargetElement(element_p, context);
    EObject source = handler_p.getSourceElement(element_p, context);

    if ((target != null) && (source != null)) {
      ID = "TRACE_BETWEEN_" + getMatchId(target, scope_p) + " " + getMatchId(source, scope_p) + " " + element_p.eClass().getName();

    }

    return ID;
  }

  /**
   * @param element_p
   * @param context_p
   * @param scope_p 
   * @param handler_p
   * @return
   */
  private String getIdentifier(EObject element_p, IContext context_p, IModelScope scope_p, ITraceabilityHandler handler_p) {

    String ID = "";

    if (element_p == null) {
      return ID;
    }
    if (element_p.eClass() == null) {
      return ID;
    }

    if (!isMatchable(element_p, scope_p, context_p)) {
      ID += "UNMATCHABLE-ELEMENT-";
    }

    //If into an architecture, we add ArchitectureIdentifier
    BlockArchitecture elementArch = BlockArchitectureExt.getRootBlockArchitecture(element_p);
    if (elementArch != null) {
      ID += elementArch.eClass().getName() + "_";
    }

    EObject sourceElement = element_p;
    String relationBetweenElements = element_p.eClass().getName();

    //Retrieve identifier
    Collection<EObject> sources = handler_p.retrieveSourceElements(element_p, context_p);

    if (sources.isEmpty()) {

      //Default solution if no traceability
      if (element_p instanceof FunctionInputPort) {
        FunctionInputPort port = (FunctionInputPort) element_p;
        sources = new HashSet<EObject>();
        for (FunctionalExchange exchange : port.getIncomingFunctionalExchanges()) {
          sources.addAll(handler_p.retrieveSourceElements(exchange, context_p));
        }
      }

      if (element_p instanceof FunctionOutputPort) {
        FunctionOutputPort port = (FunctionOutputPort) element_p;
        sources = new HashSet<EObject>();
        for (FunctionalExchange exchange : port.getOutgoingFunctionalExchanges()) {
          sources.addAll(handler_p.retrieveSourceElements(exchange, context_p));
        }
      }

      if (element_p instanceof ComponentPort) {
        ComponentPort port = (ComponentPort) element_p;
        sources = new HashSet<EObject>();
        for (ComponentExchange exchange : port.getComponentExchanges()) {
          if (port.equals(ComponentExchangeExt.getSourcePort(exchange))) {
            relationBetweenElements = "sourceOf";
          } else {
            relationBetweenElements = "targetOf";
          }
          sources.addAll(handler_p.retrieveSourceElements(exchange, context_p));
        }
      }

    }

    if (!sources.isEmpty()) {
      sourceElement = sources.iterator().next();
    }

    if (sourceElement != null) {
      String sourceElementId = handler_p.getId(sourceElement, context_p);

      if (!isUnique(element_p)) {
        ID += sourceElementId;
        ID += relationBetweenElements;
        ID += sourceElement.eClass().getName();
      }
    }

    ID += element_p.eClass().getName();

    if (sourceElement instanceof ActivityAllocation) {
      if (element_p instanceof ComponentFunctionalAllocation) {
        ID += getIdentifier(((ComponentFunctionalAllocation) element_p).getSourceElement(), context_p, scope_p, handler_p);
      }
    }
    return ID;
  }

  /**
   * @param element_p
   * @return
   */
  protected boolean isUnique(EObject element_p) {
    //I guess we should remove isBlockArchitecture test. It should be ok in all cases.
    if (element_p.eContainer() instanceof BlockArchitecture) {
      return !element_p.eContainingFeature().isMany();
    }
    return false;
  }
}
