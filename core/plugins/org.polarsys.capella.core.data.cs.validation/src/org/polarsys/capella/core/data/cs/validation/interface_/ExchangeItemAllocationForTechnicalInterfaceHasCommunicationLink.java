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
package org.polarsys.capella.core.data.cs.validation.interface_;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule checks that  for each Exchange Item Allocation of a technical Interface, there is a Communication Link associated to the related EIA.
 */
public class ExchangeItemAllocationForTechnicalInterfaceHasCommunicationLink extends AbstractValidationRule {
	
	@Override
	public IStatus validate(IValidationContext ctx) {	
      EObject eObj = ctx.getTarget();      
      if (eObj instanceof ExchangeItemAllocation) {
    	  ExchangeItemAllocation allocation = (ExchangeItemAllocation) eObj;    	  
    	  Interface interfaze = allocation.getAllocatingInterface();
    	  if (!interfaze.isStructural()) {    		      		  
    		  List<Component> userComponents = interfaze.getUserComponents();
    		  List<Component> implementorComponents = interfaze.getImplementorComponents();
    		  if (userComponents.size() > 0 && implementorComponents.size() > 0) {
    			  Component userComponent = userComponents.get(0);
    			  Component implementorComponent = implementorComponents.get(0);
    			  ExchangeItem item = allocation.getAllocatedItem();
    			  boolean hasNoError = 
    					  hasLinkTowardsItem(item, userComponent.getTransmit(), implementorComponent.getAcquire()) ||
    					  hasLinkTowardsItem(item, userComponent.getSend(), implementorComponent.getReceive()) ||
    					  hasLinkTowardsItem(item, userComponent.getProduce(), implementorComponent.getConsume()) ||
    					  hasLinkTowardsItem(item, userComponent.getCall(), implementorComponent.getExecute()) ||
    					  hasLinkTowardsItem(item, userComponent.getWrite(), implementorComponent.getAccess());
    			  if (!hasNoError) {
    				  return ctx.createFailureStatus(
    						  CapellaElementExt.getCapellaExplorerLabel(userComponent),
    						  CapellaElementExt.getCapellaExplorerLabel(implementorComponent),
    						  CapellaElementExt.getCapellaExplorerLabel(item),
    						  CapellaElementExt.getCapellaExplorerLabel(interfaze));
    			  }
    		  }
    	  }
      }        
      return ctx.createSuccessStatus();
	} 
	private boolean hasLinkTowardsItem(ExchangeItem item, List<CommunicationLink> linksFromLeft, List<CommunicationLink> linksFromRight) {
		return doesLinkListTargetItem(item, linksFromLeft) && doesLinkListTargetItem(item, linksFromRight);
	}
	private boolean doesLinkListTargetItem(ExchangeItem item, List<CommunicationLink> links) {
		for (CommunicationLink link : links) {
			if (link.getExchangeItem() == item) {
				return true;
			}
		}
		return false;
	}
}