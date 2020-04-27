/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.model.helpers;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.common.data.activity.ActivityNode;

/**
 *
 */
public class FunctionalExchangeExt {

  public static AbstractFunction getSourceFunction(FunctionalExchange exchange) {
    return FunctionExt.getRelatedFunction(exchange.getSource());
  }

  public static AbstractFunction getTargetFunction(FunctionalExchange exchange) {
    return FunctionExt.getRelatedFunction(exchange.getTarget());
  }

  /**
   * Create a functional exchange between both activityNodes.
   * and create port if not used as parameters and required (not on OA)
   * @param sourceNode
   * @param targetNode
   * @return
   */
  public static FunctionalExchange createFunctionalExchange(ActivityNode sourceNode, ActivityNode targetNode) {
    ActivityNode exchangeSourceNode = sourceNode;
    ActivityNode exchangeTargetNode = targetNode;

    AbstractFunction sourceFunction = FunctionExt.getRelatedFunction(sourceNode);
    AbstractFunction targetFunction = FunctionExt.getRelatedFunction(targetNode);

    if (!((sourceNode instanceof FunctionPort) || (sourceNode instanceof OperationalActivity))) {
      exchangeSourceNode = FaFactory.eINSTANCE.createFunctionOutputPort();
      sourceFunction.getOutputs().add((FunctionOutputPort) exchangeSourceNode);
      CapellaElementExt.creationService(exchangeSourceNode);
    }

    if (!((targetNode instanceof FunctionPort) || (targetNode instanceof OperationalActivity))) {
      exchangeTargetNode = FaFactory.eINSTANCE.createFunctionInputPort();
      targetFunction.getInputs().add((FunctionInputPort) exchangeTargetNode);
      CapellaElementExt.creationService(exchangeTargetNode);
    }

    FunctionalExchange exchange = FaFactory.eINSTANCE.createFunctionalExchange();
    exchange.setSource(exchangeSourceNode);
    exchange.setTarget(exchangeTargetNode);
    FunctionalExchangeExt.attachToDefaultContainer(exchange);
    CapellaElementExt.creationService(exchange);

    return exchange;
  }

  /**
   * Attach the given component exchange to the given abstract functional block
   * @param exchange
   * @param container
   * @return
   */
  public static boolean attachTo(FunctionalExchange exchange, AbstractFunction container) {
    if ((container != null) && !container.equals(exchange.eContainer())) {
      (container).getOwnedFunctionalExchanges().add(exchange);
      return true;
    }
    return false;
  }

  /**
   * Move the given component exchange to common ancestor
   * @param exchange
   * @return whether the component exchange has been moved
   */
  public static boolean attachToDefaultContainer(FunctionalExchange exchange) {
    return attachTo(exchange, getDefaultContainer(exchange));
  }

  /**
   * Return the best container between both given elements
   * @param sourcePart_p a part or a component
   * @param targetPart_p a part or a component
   * @return a not null element
   */
  public static AbstractFunction getDefaultContainer(AbstractFunction sourceFunction, AbstractFunction targetFunction) {
    EObject container = EcoreUtil2.getCommonAncestor(sourceFunction, targetFunction);
    if ((container != null) && !(container instanceof AbstractFunction)) {
      container = EcoreUtil2.getFirstContainer(container, FaPackage.Literals.ABSTRACT_FUNCTION);
    }
    if ((container == null) || !(container instanceof AbstractFunction)) {
      container = BlockArchitectureExt.getRootFunction(BlockArchitectureExt.getRootBlockArchitecture(sourceFunction));
    }
    return (AbstractFunction) container;
  }

  /**
   * The best container is the common ancestor of source/target parts.
   * if no parts, we use common ancestor of components (which can happen in OA or if user has deleted parts)
   * @param exchange
   * @return a not null element
   */
  public static AbstractFunction getDefaultContainer(FunctionalExchange exchange) {
    AbstractFunction source = getSourceFunction(exchange);
    AbstractFunction target = getTargetFunction(exchange);
    return getDefaultContainer(source, target);
  }
}
