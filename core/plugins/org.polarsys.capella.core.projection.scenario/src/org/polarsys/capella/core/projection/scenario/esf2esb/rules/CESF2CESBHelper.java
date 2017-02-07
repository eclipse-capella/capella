/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.scenario.esf2esb.rules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.projection.common.resolver.ResolverFinalizer;
import org.polarsys.capella.core.projection.scenario.CommonScenarioHelper;
import org.polarsys.capella.core.projection.scenario.Messages;
import org.polarsys.capella.core.tiger.IResolver;
import org.polarsys.capella.core.tiger.ITransfo;

/**
 *
 */
public class CESF2CESBHelper {

  public static AbstractEventOperation getRelatedConnection(AbstractEventOperation operation_p, SequenceMessage relatedMessage, ITransfo transfo_p) {
    List<EObject> allocatings = new ArrayList<EObject>();

    if (operation_p instanceof FunctionalExchange) {
      FunctionalExchange exchange = (FunctionalExchange) operation_p;

      // Retrieve a connection for functional exchange for any messages
      ComponentExchange connection = null;// CESF2CESBFinalizer.getRelatedComponentExchange(exchange, relatedMessage);

      for (ComponentExchangeFunctionalExchangeAllocation allocation : exchange.getIncomingComponentExchangeFunctionalExchangeRealizations()) {
        if (allocation.getAllocatingComponentExchange() != null) {
          // maybe filter them with source and target of connection and message covered instance roles ?
          allocatings.add(allocation.getAllocatingComponentExchange());
        }
      }

      if (allocatings.size() > 0) {

        // Retrieve a resolver
        IResolver resolver = ResolverFinalizer.getResolver(transfo_p);
        String message = ICommonConstants.EMPTY_STRING;

        if (resolver != null) {

          if (relatedMessage != null) {
            message =
                NLS.bind(Messages.Rule_Event_FunctionalExchangeMultiAllocatedConveyed, EObjectLabelProviderHelper.getText(relatedMessage),
                    EObjectLabelProviderHelper.getText(exchange));
          } else {
            message = NLS.bind(Messages.Rule_Event_FunctionalExchangeMultiAllocated, EObjectLabelProviderHelper.getText(exchange));
          }

          message += Messages.Rule_Event_SelectionComponentExchange;

          List<EObject> result =
              resolver.resolve(relatedMessage, allocatings, CommonScenarioHelper.getTitle(transfo_p), message, false, transfo_p, new EObject[] {
                                                                                                                                                relatedMessage,
                                                                                                                                                exchange });

          if (result.size() > 0) {
            connection = (ComponentExchange) result.get(0);
          }

          return connection;
        }

      }

    }

    return null;
  }

}
