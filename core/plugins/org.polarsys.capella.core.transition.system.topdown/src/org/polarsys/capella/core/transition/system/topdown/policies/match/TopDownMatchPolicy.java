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
package org.polarsys.capella.core.transition.system.topdown.policies.match;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
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
public class TopDownMatchPolicy
    extends org.polarsys.capella.core.transition.common.policies.match.TraceabilityHandlerMatchPolicy {

  /**
   * Constructor
   * 
   * @param a
   *          non-null mapping of corresponding elements whose further modifications will impact this policy
   */
  public TopDownMatchPolicy(IContext context) {
    super(context);
  }

  @Override
  public Comparable<?> getMatchID(EObject element, ITreeDataScope<EObject> scope) {

    IContext context = getContext();

    ITraceabilityHandler sourceHandler = (ITraceabilityHandler) context
        .get(ITransitionConstants.TRACEABILITY_SOURCE_MERGE_HANDLER);
    ITraceabilityHandler targetHandler = (ITraceabilityHandler) context
        .get(ITransitionConstants.TRACEABILITY_TARGET_MERGE_HANDLER);
    ITraceabilityHandler handler = null;

    if (scope instanceof TargetModelScope) {
      handler = targetHandler;

    } else {
      handler = sourceHandler;

    }

    context.put(ITransitionConstants.TRACEABILITY_HANDLER, handler);

    String ID = null;

    if (handler instanceof ITraceabilityTraceHandler) {
      ITraceabilityTraceHandler tHandler = (ITraceabilityTraceHandler) handler;
      if (tHandler.isTrace(element, context)) {
        ID = getTraceIdentifier(element, context, scope, tHandler);
      }
    }

    if (ID == null) {
      ID = getIdentifier(element, context, scope, handler);
    }

    if (ID == null) {
      ID = handler.getId(element, context);
    }

    return ID;
  }

  /**
   * @param element
   * @param context
   * @param scope
   * @param handler
   */
  private String getTraceIdentifier(EObject element, IContext context, ITreeDataScope<EObject> scope,
      ITraceabilityTraceHandler handler) {
    IContext icontext = getContext();
    String ID = "";
    EObject target = handler.getTargetElement(element, icontext);
    EObject source = handler.getSourceElement(element, icontext);

    if ((target != null) && (source != null)) {
      ID = "TRACE_BETWEEN_" + getMatchID(target, scope) + " " + getMatchID(source, scope) + " "
          + element.eClass().getName();

    }

    return ID;
  }

  /**
   * @param element
   * @param context
   * @param scope
   * @param handler
   * @return
   */
  private String getIdentifier(EObject element, IContext context, ITreeDataScope<EObject> scope, ITraceabilityHandler handler) {

    String ID = "";

    if (element == null) {
      return ID;
    }
    if (element.eClass() == null) {
      return ID;
    }

    if (!isMatchable(element, scope, context)) {
      ID += "UNMATCHABLE-ELEMENT-";
    }

    SystemEngineering engineering = (SystemEngineering) EcoreUtil2.getFirstContainer(element, CapellamodellerPackage.Literals.SYSTEM_ENGINEERING);
    if (engineering != null) {
      ID += engineering.getId() + "_";
    }

    // If into an architecture, we add ArchitectureIdentifier
    BlockArchitecture elementArch = BlockArchitectureExt.getRootBlockArchitecture(element);
    if (elementArch != null) {
      ID += elementArch.eClass().getName() + "_";
    }

    EObject sourceElement = element;
    String relationBetweenElements = element.eClass().getName();

    // Retrieve identifier
    Collection<EObject> sources = handler.retrieveSourceElements(element, context);

    if (sources.isEmpty()) {

      // Default solution if no traceability
      if (element instanceof FunctionInputPort) {
        FunctionInputPort port = (FunctionInputPort) element;
        sources = new HashSet<EObject>();
        for (FunctionalExchange exchange : port.getIncomingFunctionalExchanges()) {
          sources.addAll(handler.retrieveSourceElements(exchange, context));
        }
      }

      if (element instanceof FunctionOutputPort) {
        FunctionOutputPort port = (FunctionOutputPort) element;
        sources = new HashSet<EObject>();
        for (FunctionalExchange exchange : port.getOutgoingFunctionalExchanges()) {
          sources.addAll(handler.retrieveSourceElements(exchange, context));
        }
      }

      if (element instanceof ComponentPort) {
        ComponentPort port = (ComponentPort) element;
        sources = new HashSet<EObject>();
        for (ComponentExchange exchange : port.getComponentExchanges()) {
          if (port.equals(ComponentExchangeExt.getSourcePort(exchange))) {
            relationBetweenElements = "sourceOf";
          } else {
            relationBetweenElements = "targetOf";
          }
          sources.addAll(handler.retrieveSourceElements(exchange, context));
        }
      }

    }

    if (!sources.isEmpty()) {
      sourceElement = sources.iterator().next();
    }

    if (sourceElement != null) {
      String sourceElementId = handler.getId(sourceElement, context);

      if (!isUnique(element)) {
        ID += sourceElementId;
        ID += relationBetweenElements;
        ID += sourceElement.eClass().getName();
      }
    }

    ID += element.eClass().getName();

    if (sourceElement instanceof ActivityAllocation) {
      if (element instanceof ComponentFunctionalAllocation) {
        ID += getIdentifier(((ComponentFunctionalAllocation) element).getSourceElement(), context, scope, handler);
      }
    }
    return ID;
  }

  /**
   * @param element
   * @return
   */
  protected boolean isUnique(EObject element) {
    // I guess we should remove isBlockArchitecture test. It should be ok in all cases.
    if (element.eContainer() instanceof BlockArchitecture) {
      return !element.eContainingFeature().isMany();
    }
    return false;
  }
}
