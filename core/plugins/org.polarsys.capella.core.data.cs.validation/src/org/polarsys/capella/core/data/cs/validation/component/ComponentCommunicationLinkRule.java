/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.cs.validation.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule ensure that a component can't have same kind of communication link targeting to same exchange item with the same protocol. 
 */
public class ComponentCommunicationLinkRule extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {
  	Collection<IStatus> statuses = new ArrayList<IStatus>();
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Component) {
      	Component comp = (Component) eObj;
        // map of exchagneItem and its incoming communication links
        Map<ExchangeItem, Map<String, List<CommunicationLink>>> communicationLinkMap = new HashMap<ExchangeItem, Map<String, List<CommunicationLink>>>();
        for (CommunicationLink communicationLink : comp.getOwnedCommunicationLinks()) {
          AbstractExchangeItem exchangeItem = communicationLink.getExchangeItem();
          Map<String, List<CommunicationLink>> signature2Links = communicationLinkMap.get(exchangeItem);
          if (signature2Links == null) {
          	signature2Links = new HashMap<String, List<CommunicationLink>>();
          	communicationLinkMap.put((ExchangeItem)exchangeItem, signature2Links);
          }
          String signature = communicationLink.getKind().toString()+communicationLink.getProtocol();
          List<CommunicationLink> links = signature2Links.get(signature);
          if (links == null) {
          	links = new ArrayList<CommunicationLink>();
          	signature2Links.put(signature, links);
          }
          links.add(communicationLink);
        }
        // create error message if same kind of communication link target is same ExchageItem 
        for (ExchangeItem exchangeItem : communicationLinkMap.keySet()) {
        	Map<String, List<CommunicationLink>> signature2Links = communicationLinkMap.get(exchangeItem);
        	for (String signature : signature2Links.keySet()) {
        		List<CommunicationLink> links = signature2Links.get(signature);
        		if (links.size() > 1) {
        			CommunicationLink firstLink = links.get(0);
        			statuses.add(ctx.createFailureStatus(
        					CapellaElementExt.getCapellaExplorerLabel(comp),
        					links.size(),
        					firstLink.getKind(),
        					firstLink.getProtocol(),
                  CapellaElementExt.getCapellaExplorerLabel(exchangeItem)));
        		}
        	}        	
        }
      }
    }
	  if (statuses.size() > 0) {
	  	return ConstraintStatus.createMultiStatus(ctx, statuses);
	  } else {
	  	return ctx.createSuccessStatus();
	  }	  	
  }

}
