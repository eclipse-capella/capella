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
package org.polarsys.capella.core.projection.common.rules.oa;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.model.helpers.PortExt;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.rules.fa.Rule_Connection;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public class Rule_CommunicationMean extends Rule_Connection {

  /**
   * @param sourceType_p
   * @param targetType_p
   */
  public Rule_CommunicationMean() {
    super(OaPackage.Literals.COMMUNICATION_MEAN, FaPackage.Literals.COMPONENT_EXCHANGE);
  }

  @Override
  protected EObject transformDirectElement(EObject element_p, IContext context_p) {
    CommunicationMean mean = (CommunicationMean) element_p;

    // Should be deleted in 1.7..

    // Try to retrieve an existing but not traced ComponentExchange
    for (ComponentExchangeFunctionalExchangeAllocation allocation : mean.getOutgoingComponentExchangeFunctionalExchangeAllocations()) {
      for (EObject eTgt : Query.retrieveTransformedElements(allocation, getTransfo(), FaPackage.Literals.COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION)) {
        if (eTgt instanceof ComponentExchangeFunctionalExchangeAllocation) {
          ComponentExchangeFunctionalExchangeAllocation allocT = (ComponentExchangeFunctionalExchangeAllocation) eTgt;
          if (allocT.getAllocatingComponentExchange() != null) {
            return allocT.getAllocatingComponentExchange();
          }
        }
      }
    }

    return super.transformDirectElement(element_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);

    if (isFirstAttach(element_p, result_p, context_p)) {
      ComponentExchange transfoSource = (ComponentExchange) element_p;

      ComponentExchange transfoTarget = (ComponentExchange) result_p;

      InformationsExchanger outputNode;
      try {
        outputNode = transfoTarget.getSource();
      } catch (ClassCastException exception_p) {
        // in case we are in a team context, source is not null and returns an ActorImpl instance that is cannot be casted to
        // InformationsExchanger.
        // The workaround consists in catching this useless exception
        // FIXME find the reason why source returns an ActorImpl instance in a Team context.
        outputNode = null;
      }
      if (outputNode == null) {
        ComponentPort outputPort = PortExt.createOutFlowPort("out" + transfoSource.getName()); //$NON-NLS-1$
        transfoTarget.setSource(outputPort);
        Component action = (Component) transfoSource.getSource();
        Component transformedAction = (Component) Query.retrieveFirstTransformedElement(action, context_p.getTransfo());
        TigerRelationshipHelper.attachElementByRel(transformedAction, outputPort, CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES);
      }
      InformationsExchanger inputNode;
      try {
        inputNode = transfoTarget.getTarget();
      } catch (Exception exception_p) {
        // FIXME find the reason why source returns an ActorImpl instance in a Team context.
        inputNode = null;
      }
      if (inputNode == null) {
        ComponentPort inputPort = PortExt.createInFlowPort("in" + transfoSource.getName()); //$NON-NLS-1$
        transfoTarget.setTarget(inputPort);
        Component action = (Component) transfoSource.getTarget();
        Component transformedAction = (Component) Query.retrieveFirstTransformedElement(action, context_p.getTransfo());
        TigerRelationshipHelper.attachElementByRel(transformedAction, inputPort, CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES);
      }

    }

    TigerRelationshipHelper.attachToBestElement(element_p, ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__CONVOYED_INFORMATIONS,
        context_p.getTransfo());
  }

}
