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

  public static AbstractFunction getSourceFunction(FunctionalExchange exchange_p) {
    return FunctionExt.getRelatedFunction(exchange_p.getSource());
  }

  public static AbstractFunction getTargetFunction(FunctionalExchange exchange_p) {
    return FunctionExt.getRelatedFunction(exchange_p.getTarget());
  }

  /**
   * Create a functional exchange between both activityNodes.
   * and create port if not used as parameters and required (not on OA)
   * @param sourceNode_p
   * @param targetNode_p
   * @return
   */
  public static FunctionalExchange createFunctionalExchange(ActivityNode sourceNode_p, ActivityNode targetNode_p) {
    ActivityNode sourceNode = sourceNode_p;
    ActivityNode targetNode = targetNode_p;

    AbstractFunction sourceFunction = FunctionExt.getRelatedFunction(sourceNode_p);
    AbstractFunction targetFunction = FunctionExt.getRelatedFunction(targetNode_p);

    if (!((sourceNode_p instanceof FunctionPort) || (sourceNode_p instanceof OperationalActivity))) {
      sourceNode = FaFactory.eINSTANCE.createFunctionOutputPort();
      sourceFunction.getOutputs().add((FunctionOutputPort) sourceNode);
      CapellaElementExt.creationService(sourceNode);
    }

    if (!((targetNode_p instanceof FunctionPort) || (targetNode_p instanceof OperationalActivity))) {
      targetNode = FaFactory.eINSTANCE.createFunctionInputPort();
      targetFunction.getInputs().add((FunctionInputPort) targetNode);
      CapellaElementExt.creationService(targetNode);
    }

    FunctionalExchange exchange = FaFactory.eINSTANCE.createFunctionalExchange();
    exchange.setSource(sourceNode);
    exchange.setTarget(targetNode);
    FunctionalExchangeExt.attachToDefaultContainer(exchange);
    CapellaElementExt.creationService(exchange);

    return exchange;
  }

  /**
   * Attach the given component exchange to the given abstract functional block
   * @param exchange_p
   * @param container_p
   * @return
   */
  public static boolean attachTo(FunctionalExchange exchange_p, AbstractFunction container_p) {
    if ((container_p != null) && !container_p.equals(exchange_p.eContainer())) {
      (container_p).getOwnedFunctionalExchanges().add(exchange_p);
      return true;
    }
    return false;
  }

  /**
   * Move the given component exchange to common ancestor
   * @param exchange_p
   * @return whether the component exchange has been moved
   */
  public static boolean attachToDefaultContainer(FunctionalExchange exchange_p) {
    return attachTo(exchange_p, getDefaultContainer(exchange_p));
  }

  /**
   * Return the best container between both given elements
   * @param sourcePart_p a part or a component
   * @param targetPart_p a part or a component
   * @return a not null element
   */
  public static AbstractFunction getDefaultContainer(AbstractFunction sourceFunction_p, AbstractFunction targetFunction_p) {
    EObject container = EcoreUtil2.getCommonAncestor(sourceFunction_p, targetFunction_p);
    if ((container != null) && !(container instanceof AbstractFunction)) {
      container = EcoreUtil2.getFirstContainer(container, FaPackage.Literals.ABSTRACT_FUNCTION);
    }
    if ((container == null) || !(container instanceof AbstractFunction)) {
      container = BlockArchitectureExt.getRootFunction(BlockArchitectureExt.getRootBlockArchitecture(sourceFunction_p));
    }
    return (AbstractFunction) container;
  }

  /**
   * The best container is the common ancestor of source/target parts.
   * if no parts, we use common ancestor of components (which can happen in OA or if user has deleted parts)
   * @param exchange_p
   * @return a not null element
   */
  public static AbstractFunction getDefaultContainer(FunctionalExchange exchange_p) {
    AbstractFunction source = getSourceFunction(exchange_p);
    AbstractFunction target = getTargetFunction(exchange_p);
    return getDefaultContainer(source, target);
  }
}
