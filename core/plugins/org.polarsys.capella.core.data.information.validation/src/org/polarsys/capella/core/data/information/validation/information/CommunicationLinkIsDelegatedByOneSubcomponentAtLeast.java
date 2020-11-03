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
package org.polarsys.capella.core.data.information.validation.information;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.helpers.information.services.CommunicationLinkExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Model Validation�shall check that Exchange Items referenced by a Communication Link is delegated to one or many subcomponents of the source Component.
 */
public class CommunicationLinkIsDelegatedByOneSubcomponentAtLeast extends AbstractValidationRule {

	@Override
	public IStatus validate(IValidationContext ctx) {	
      EObject eObj = ctx.getTarget();
      if (eObj instanceof CommunicationLink) {
    	  CommunicationLink link = (CommunicationLink) eObj;
    	  ExchangeItem item = link.getExchangeItem();
    	  Component component = CommunicationLinkExt.getSource(link);
    	  List<Component> subComponents = ComponentExt.getSubUsedComponents(component, false);
    	  if (subComponents.size() > 0) {
    		  boolean oneSubfoundAtLeast = false;    	  
    		  for (Component subComponent : subComponents) {
    			  if (getExchangeItemsForLinks(subComponent, link).contains(item)) {
    				  oneSubfoundAtLeast = true;
    				  break;
    			  }
    		  }
    		  if (!oneSubfoundAtLeast) {
    			  return ctx.createFailureStatus(link.getKind(), CapellaElementExt.getCapellaExplorerLabel(component), CapellaElementExt.getCapellaExplorerLabel(item)); 
    		  }    		  
    	  }
	  }        
      return ctx.createSuccessStatus();
	}
	private List<ExchangeItem> getExchangeItemsForLinks(Component component, CommunicationLink link) {
		List<ExchangeItem> exchangeItems = new ArrayList<ExchangeItem>();
		List<CommunicationLink> links = new ArrayList<CommunicationLink>();
		if (CommunicationLinkExt.isSender(link)) {
			//add outgoing links
			links.addAll(component.getTransmit());
			links.addAll(component.getSend());
			links.addAll(component.getProduce());
			links.addAll(component.getCall());
			links.addAll(component.getWrite());			
		}
		if (CommunicationLinkExt.isReceiver(link)) {
			//add incoming links
			links.addAll(component.getAcquire());
			links.addAll(component.getReceive());
			links.addAll(component.getConsume());
			links.addAll(component.getExecute());
			links.addAll(component.getAccess());		
		}		
		for (CommunicationLink communicationLink : links) {
			exchangeItems.add(communicationLink.getExchangeItem());
		}
		return exchangeItems;
	}
}
